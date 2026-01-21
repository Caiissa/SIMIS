#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdint.h>
#include <stdbool.h>
#include <stddef.h>

#include "pico/stdlib.h"
#include "hardware/spi.h"
#include "hardware/pwm.h"
#include "hardware/clocks.h"
#include "hardware/gpio.h"

#include "hal/displays/st7735.h"
#include "demos/spiel.h"

#define W ST7735_TFTWIDTH
#define H ST7735_TFTHEIGHT

#define MORSE_SHOW_MS     5000

// Feedback-Dauer (WRONG länger als CORRECT)
#define FEEDBACK_OK_MS    2000
#define FEEDBACK_BAD_MS   4000

// Buttons (Pull-Up, gedrückt = LOW)
#define BTN_OPT1 10
#define BTN_OPT2 11
#define BTN_OPT3 14
#define BTN_OPT4 15

#define DEBOUNCE_MS 150

// Auswahl-Layout
#define CHOICE_X      20
#define CHOICE_Y0     70
#define CHOICE_DY     14
#define CHOICE_LINE_H 12
#define CHOICE_PAD_Y  2

// Buzzer
#define BUZZER_PIN 27

// Morse Audio
#define MORSE_FREQ_HZ  800
#define MORSE_VOL      0.05f
#define DOT_MS         120
#define DASH_MS        (DOT_MS * 3)
#define SYMBOL_GAP_MS  DOT_MS

typedef struct {
    char ch;
    const char *morse;
} morse_entry_t;

// ---------- Display Init ----------
static void init_display_for_game(void)
{
    gpio_init(2);
    gpio_set_dir(2, GPIO_OUT);
    gpio_put(2, 1);

    spi_init(spi0, 40 * 1000 * 1000);

    gpio_set_function(16, GPIO_FUNC_SPI);
    gpio_set_function(18, GPIO_FUNC_SPI);
    gpio_set_function(19, GPIO_FUNC_SPI);

    st7735_init(spi0, 6, 17, 3, 0, false);
}

// ---------- Morse Tabelle ----------
static const morse_entry_t MORSE_TABLE[] = {
    {'A', ".-"},   {'B', "-..."}, {'C', "-.-."}, {'D', "-.."},  {'E', "."},
    {'F', "..-."}, {'G', "--."},  {'H', "...."}, {'I', ".."},   {'J', ".---"},
    {'K', "-.-"},  {'L', ".-.."}, {'M', "--"},   {'N', "-."},   {'O', "---"},
    {'P', ".--."}, {'Q', "--.-"}, {'R', ".-."},  {'S', "..."},  {'T', "-"},
    {'U', "..-"},  {'V', "...-"}, {'W', ".--"},  {'X', "-..-"}, {'Y', "-.--"},
    {'Z', "--.."},
    {'0', "-----"},{'1', ".----"},{'2', "..---"},{'3', "...--"},{'4', "....-"},
    {'5', "....."},{'6', "-...."},{'7', "--..."},{'8', "---.."},{'9', "----."}
};
static const int MORSE_COUNT = (int)(sizeof(MORSE_TABLE) / sizeof(MORSE_TABLE[0]));

// ---------- RNG ----------
static uint32_t rng_state = 1;

static void rng_seed(uint32_t seed) {
    if (seed == 0) seed = 1;
    rng_state = seed;
}

static uint32_t rng_u32(void) {
    rng_state = 1664525u * rng_state + 1013904223u;
    return rng_state;
}

static int rng_range(int n) {
    return (int)(rng_u32() % (uint32_t)n);
}

// ---------- Buzzer (PWM) ----------
static uint buzzer_slice;
static uint buzzer_chan;

static void buzzer_init(void) {
    gpio_set_function(BUZZER_PIN, GPIO_FUNC_PWM);
    buzzer_slice = pwm_gpio_to_slice_num(BUZZER_PIN);
    buzzer_chan  = pwm_gpio_to_channel(BUZZER_PIN);

    pwm_config cfg = pwm_get_default_config();
    pwm_init(buzzer_slice, &cfg, true);
    pwm_set_chan_level(buzzer_slice, buzzer_chan, 0);
}

// Slide-Style: PWM Clock ~ 1MHz, wrap = 1e6 / f
static void buzzer_beep(uint freq_hz, float volume, uint duration_ms) {
    if (freq_hz == 0 || duration_ms == 0) return;
    if (volume < 0) volume = 0;
    if (volume > 1) volume = 1;

    pwm_set_clkdiv(buzzer_slice, 125.0f);

    uint32_t wrap = (1000000u / freq_hz);
    if (wrap < 10) wrap = 10;
    if (wrap > 65535) wrap = 65535;

    pwm_set_wrap(buzzer_slice, (uint16_t)wrap);

    uint16_t level = (uint16_t)(wrap * 0.5f * volume);
    pwm_set_chan_level(buzzer_slice, buzzer_chan, level);

    pwm_set_enabled(buzzer_slice, true);
    sleep_ms(duration_ms);

    pwm_set_enabled(buzzer_slice, false);
    pwm_set_chan_level(buzzer_slice, buzzer_chan, 0);
}

static void play_morse_audio(const char *morse) {
    if (!morse) return;
    for (size_t i = 0; morse[i] != '\0'; i++) {
        if (morse[i] == '.') {
            buzzer_beep(MORSE_FREQ_HZ, MORSE_VOL, DOT_MS);
            sleep_ms(SYMBOL_GAP_MS);
        } else if (morse[i] == '-') {
            buzzer_beep(MORSE_FREQ_HZ, MORSE_VOL, DASH_MS);
            sleep_ms(SYMBOL_GAP_MS);
        }
    }
}

// ---------- Buttons ----------
static void buttons_init(void) {
    const uint pins[4] = {BTN_OPT1, BTN_OPT2, BTN_OPT3, BTN_OPT4};
    for (int i = 0; i < 4; i++) {
        gpio_init(pins[i]);
        gpio_set_dir(pins[i], GPIO_IN);
        gpio_pull_up(pins[i]); // gedrückt -> LOW
    }
}

// Liefert -1 wenn keine Taste gedrückt wurde, sonst 0..3 für Option
static int read_option_button_edge(void) {
    static bool prev[4] = {true, true, true, true}; // Pull-up => true = nicht gedrückt
    static uint32_t last_edge_ms = 0;

    uint32_t now_ms = to_ms_since_boot(get_absolute_time());
    if (now_ms - last_edge_ms < DEBOUNCE_MS) {
        // kurz blockieren gegen Prellen
    }

    bool cur[4];
    cur[0] = gpio_get(BTN_OPT1);
    cur[1] = gpio_get(BTN_OPT2);
    cur[2] = gpio_get(BTN_OPT3);
    cur[3] = gpio_get(BTN_OPT4);

    // Edge: HIGH -> LOW (nicht gedrückt -> gedrückt)
    for (int i = 0; i < 4; i++) {
        bool pressed_edge = (prev[i] == true && cur[i] == false);
        prev[i] = cur[i];

        if (pressed_edge) {
            last_edge_ms = now_ms;
            return i; // 0..3
        }
    }

    return -1;
}

// ---------- Framebuffer (nur für Highlight-Zeilen) ----------
static uint16_t fb[W * H];

static void fb_fill_rect(int x, int y, int w, int h, uint16_t c) {
    if (w <= 0 || h <= 0) return;

    int x0 = x; if (x0 < 0) x0 = 0;
    int y0 = y; if (y0 < 0) y0 = 0;
    int x1 = x + w - 1; if (x1 >= W) x1 = W - 1;
    int y1 = y + h - 1; if (y1 >= H) y1 = H - 1;

    for (int yy = y0; yy <= y1; yy++) {
        uint16_t *row = &fb[yy * W];
        for (int xx = x0; xx <= x1; xx++) row[xx] = c;
    }
}

static void fb_clear(uint16_t c) {
    for (int i = 0; i < W * H; i++) fb[i] = c;
}

static void fb_flush_rect(int x, int y, int w, int h) {
    if (w <= 0 || h <= 0) return;

    int x0 = x; if (x0 < 0) x0 = 0;
    int y0 = y; if (y0 < 0) y0 = 0;
    int x1 = x + w - 1; if (x1 >= W) x1 = W - 1;
    int y1 = y + h - 1; if (y1 >= H) y1 = H - 1;

    int ww = (x1 - x0 + 1);
    int hh = (y1 - y0 + 1);

    st7735_set_addr_window((uint8_t)x0, (uint8_t)y0, (uint8_t)x1, (uint8_t)y1);

    uint8_t line[W * 2];

    for (int yy = 0; yy < hh; yy++) {
        const uint16_t *src = &fb[(y0 + yy) * W + x0];

        for (int xx = 0; xx < ww; xx++) {
            uint16_t c = src[xx];
            line[2 * xx + 0] = (uint8_t)(c >> 8);
            line[2 * xx + 1] = (uint8_t)(c & 0xFF);
        }

        st7735_write_data_buffer(line, (size_t)(ww * 2));
    }
}

// ---------- UI helpers ----------
static void draw_center_text(int y, const char *s, uint16_t fg, uint16_t bg) {
    int len = (int)strlen(s);
    int x = (W - len * 6) / 2;
    if (x < 0) x = 0;
    st7735_draw_string(x, y, s, fg, bg);
}

static void draw_choice_line(int idx, bool is_selected,
                             uint16_t FG, uint16_t BG, uint16_t HI,
                             const char choices[4])
{
    int y = CHOICE_Y0 + idx * CHOICE_DY;
    int rect_y = y - CHOICE_PAD_Y;

    fb_fill_rect(0, rect_y, W, CHOICE_LINE_H, BG);

    if (is_selected) {
        fb_fill_rect(CHOICE_X - 2, rect_y, W - (CHOICE_X - 2) - 2, CHOICE_LINE_H, HI);
    }

    fb_flush_rect(0, rect_y, W, CHOICE_LINE_H);

    char opt[4];
    snprintf(opt, sizeof(opt), "%c", choices[idx]);
    if (is_selected) st7735_draw_string(CHOICE_X, y, opt, BG, HI);
    else             st7735_draw_string(CHOICE_X, y, opt, FG, BG);
}

// ---------- Spielzustand ----------
typedef enum {
    PHASE_SHOW_MORSE = 0,
    PHASE_SHOW_CHOICES = 1,
    PHASE_FEEDBACK = 2
} phase_t;

static int score = 0;
static int round_no = 0;

static char correct_char = 'A';
static const char *correct_morse = ".-";

static char choices[4];
static int correct_pos = 0;

static phase_t phase = PHASE_SHOW_MORSE;
static uint32_t phase_start_ms = 0;

static bool morse_played = false;

static void new_round(void) {
    round_no++;
    phase = PHASE_SHOW_MORSE;
    phase_start_ms = to_ms_since_boot(get_absolute_time());
    morse_played = false;

    int correct_idx = rng_range(MORSE_COUNT);
    correct_char = MORSE_TABLE[correct_idx].ch;
    correct_morse = MORSE_TABLE[correct_idx].morse;

    // Optionen generieren
    choices[0] = correct_char;
    int filled = 1;
    while (filled < 4) {
        int wi = rng_range(MORSE_COUNT);
        char wc = MORSE_TABLE[wi].ch;

        bool exists = false;
        for (int k = 0; k < filled; k++) {
            if (choices[k] == wc) { exists = true; break; }
        }
        if (!exists) choices[filled++] = wc;
    }

    // Shuffle
    for (int i = 3; i > 0; i--) {
        int j = rng_range(i + 1);
        char tmp = choices[i];
        choices[i] = choices[j];
        choices[j] = tmp;
    }

    // correct_pos finden
    correct_pos = 0;
    for (int i = 0; i < 4; i++) {
        if (choices[i] == correct_char) { correct_pos = i; break; }
    }
}

// ---------- Screens ----------
static void render_morse_screen(uint16_t FG, uint16_t BG, uint16_t ACC) {
    st7735_fill_screen(BG);

    char header[32];
    snprintf(header, sizeof(header), "Morse Decoder #%d", round_no);
    st7735_draw_string(2, 2, header, ACC, BG);

    draw_center_text(40, "Decode this:", FG, BG);
    draw_center_text(70, correct_morse, FG, BG);
    st7735_draw_string(2, H - 18, "Wait 5s for options...", ACC, BG);
}

static void render_choices_screen(uint16_t FG, uint16_t BG, uint16_t ACC, uint16_t HI) {
    st7735_fill_screen(BG);

    char top[32];
    snprintf(top, sizeof(top), "Score: %d", score);
    st7735_draw_string(2, 2, top, ACC, BG);

    draw_center_text(25, "Press a button for answer", FG, BG);
    draw_center_text(45, correct_morse, FG, BG);

    st7735_draw_string(2, H - 18, "10/11/14/15=Opt 1234", ACC, BG);

    fb_fill_rect(0, CHOICE_Y0 - CHOICE_PAD_Y, W, (4 * CHOICE_DY) + CHOICE_LINE_H, BG);

    // Wir highlighten nichts dynamisch, aber zeichnen alle Zeilen normal
    for (int i = 0; i < 4; i++) {
        draw_choice_line(i, false, FG, BG, HI, choices);
    }
}

static void render_feedback_screen(bool ok, char pick,
                                   uint16_t FG, uint16_t BG,
                                   uint16_t ACC, uint16_t BAD)
{
    st7735_fill_screen(BG);

    char top[32];
    snprintf(top, sizeof(top), "Score: %d", score);
    st7735_draw_string(2, 2, top, ACC, BG);

    draw_center_text(45, ok ? "CORRECT" : "WRONG", ok ? ACC : BAD, BG);

    char line1[32];
    snprintf(line1, sizeof(line1), "You picked: %c", pick);
    draw_center_text(70, line1, FG, BG);

    char line2[32];
    snprintf(line2, sizeof(line2), "Correct: %c", correct_char);
    draw_center_text(85, line2, FG, BG);

    char mline[40];
    snprintf(mline, sizeof(mline), "Morse: %s", correct_morse);
    draw_center_text(105, mline, FG, BG);
}

// ---------- Main ----------
void spiel_run(void) {
    stdio_init_all();

    init_display_for_game();
    st7735_begin();

    buzzer_init();
    buttons_init();

    const uint16_t BG  = st7735_rgb(0, 0, 0);
    const uint16_t FG  = st7735_rgb(255, 255, 255);
    const uint16_t ACC = st7735_rgb(80, 255, 80);
    const uint16_t BAD = st7735_rgb(255, 80, 80);
    const uint16_t HI  = st7735_rgb(60, 60, 180);

    rng_seed(time_us_32());

    score = 0;
    round_no = 0;

    fb_clear(BG);
    new_round();

    phase_t last_phase = (phase_t)(-1);

    bool last_answer_ok = false;
    char last_pick = '?';

    while (true) {
        uint32_t now_ms = to_ms_since_boot(get_absolute_time());

        // Render nur bei Phase-Wechsel
        if (phase != last_phase) {
            if (phase == PHASE_SHOW_MORSE) {
                render_morse_screen(FG, BG, ACC);
            } else if (phase == PHASE_SHOW_CHOICES) {
                render_choices_screen(FG, BG, ACC, HI);
            } else {
                render_feedback_screen(last_answer_ok, last_pick, FG, BG, ACC, BAD);
            }
            last_phase = phase;
        }

        if (phase == PHASE_SHOW_MORSE) {

            // Morse 1x spielen
            if (!morse_played) {
                morse_played = true;
                play_morse_audio(correct_morse);
            }

            if ((now_ms - phase_start_ms) >= MORSE_SHOW_MS) {
                phase = PHASE_SHOW_CHOICES;
                phase_start_ms = now_ms;
                last_phase = (phase_t)(-1);
            }
        }
        else if (phase == PHASE_SHOW_CHOICES) {

            int opt = read_option_button_edge(); // -1 oder 0..3
            if (opt >= 0) {
                // pick = choices[opt]
                last_pick = choices[opt];

                bool ok = (opt == correct_pos);
                last_answer_ok = ok;

                if (ok) score++;

                phase = PHASE_FEEDBACK;
                phase_start_ms = now_ms;
                last_phase = (phase_t)(-1);
            }
        }
        else { // PHASE_FEEDBACK
            uint32_t dur = last_answer_ok ? FEEDBACK_OK_MS : FEEDBACK_BAD_MS;
            if ((now_ms - phase_start_ms) >= dur) {
                new_round();
                last_phase = (phase_t)(-1);
            }
        }

        sleep_ms(5);
    }
}
