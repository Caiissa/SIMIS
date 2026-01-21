#include "demos/Abgabe_09.h"       // Eigene Headerdatei (Projekt-spezifisch)
#include "pico/stdlib.h"            // Standardbibliothek für Raspberry Pi Pico
#include "hardware/gpio.h"          // Zugriff auf GPIO-Funktionen
#include <stdio.h>                  // Standard-Input/Output für printf()
#include "hal/leds/ws2812.h"        // Bibliothek für WS2812-LED-Steuerung
//
#include "demos/display.h"
#include "hal/displays/st7735.h"
#include "pico/stdlib.h"

// Definition der GPIO-Pins, an denen die Taster angeschlossen sind
#define BUTTON_PIN 15
#define BUTTON_PIN_2 10
#define BUTTON_PIN_3 11
#define BUTTON_PIN_4 14

// Funktion für eine Vorwärts-Lauflicht-Animation
void anim_lauflicht_vor(WS2812 *ws, uint16_t num_leds) {
    for (uint16_t i = 0; i < num_leds; i++) {
        ws2812_clear(ws);                        // Alle LEDs ausschalten
        ws2812_set_pixel_color_rgb(ws, i, 255, 0, 0); // Aktuelle LED rot setzen
        ws2812_show(ws);                         // LED-Streifen aktualisieren
        sleep_ms(80);                            // Kurze Pause zwischen den Schritten
    }
}


// Funktion für eine Rückwärts-Lauflicht-Animation
void anim_lauflicht_zurueck(WS2812 *ws, uint16_t num_leds) {
    for (int i = (int)num_leds - 1; i >= 0; i--) {
        ws2812_clear(ws);                        // Alle LEDs ausschalten
        ws2812_set_pixel_color_rgb(ws, i, 0, 255, 0); // Aktuelle LED grün setzen
        ws2812_show(ws);                         // LED-Streifen aktualisieren
        sleep_ms(80);                            // Kurze Pause
    }
}

// Funktion: alle LEDs gemeinsam dreimal blinken lassen (blau)
void anim_blink_all(WS2812 *ws, uint16_t num_leds) {
    for (int k = 0; k < 3; k++) {
        // an
        ws2812_clear(ws);                        // Alle LEDs ausschalten
        for (uint16_t i = 0; i < num_leds; i++) {
            ws2812_set_pixel_color_rgb(ws, i, 0, 0, 255); // Alle LEDs blau setzen
        }
        ws2812_show(ws);                         // Anzeige aktualisieren
        sleep_ms(150);                           // Kurze Pause (LEDs an)

        // aus
        ws2812_clear(ws);                        // Alle LEDs ausschalten
        ws2812_show(ws);                         // Anzeige aktualisieren
        sleep_ms(150);                           // Kurze Pause (LEDs aus)
    }
}

// Funktion: „Wipe“-Effekt – LEDs nacheinander gelb einschalten
void anim_wipe(WS2812 *ws, uint16_t num_leds) {
    ws2812_clear(ws);                            // Zuerst alles ausschalten
    for (uint16_t i = 0; i < num_leds; i++) {
        ws2812_set_pixel_color_rgb(ws, i, 255, 255, 0); // Aktuelle LED gelb setzen
        ws2812_show(ws);                         // Anzeige aktualisieren
        sleep_ms(80);                            // Kurze Pause
    }
}

// Hauptfunktion (Einstiegspunkt)
void abgabe_09_execute(void)
{
    WS2812 ws;                                   // Struktur für WS2812-Treiber
    const uint16_t num_leds = 4;                 // Anzahl der LEDs im Streifen
    const uint8_t pin = 1;                       // Datenleitung des LED-Streifens (GPIO 1)

    ws2812_init_auto_sm(&ws, num_leds, pin);     // WS2812 mit Standard-State-Machine initialisieren
    ws2812_begin(&ws);                           // LED-Streifen aktivieren

    // GPIO-Pins für die Taster initialisieren
    gpio_init(BUTTON_PIN);
    gpio_init(BUTTON_PIN_2);
    gpio_init(BUTTON_PIN_3);
    gpio_init(BUTTON_PIN_4);

    // Pins als Eingang konfigurieren
    gpio_set_dir(BUTTON_PIN, GPIO_IN);
    gpio_set_dir(BUTTON_PIN_2, GPIO_IN);
    gpio_set_dir(BUTTON_PIN_3, GPIO_IN);
    gpio_set_dir(BUTTON_PIN_4, GPIO_IN);

    // Interne Pull-Up-Widerstände aktivieren (Pins sind HIGH, wenn Taster nicht gedrückt)
    gpio_pull_up(BUTTON_PIN);
    gpio_pull_up(BUTTON_PIN_2);
    gpio_pull_up(BUTTON_PIN_3);
    gpio_pull_up(BUTTON_PIN_4);

    // Ausgabe von Statusinformationen über UART
    printf("Read button state (GPIO %d,%d,%d,%d) started.\n", BUTTON_PIN,BUTTON_PIN_2,BUTTON_PIN_3,BUTTON_PIN_4);
    printf("Press the button (connected to GND).\n\n");

    
    // --------------------------------------------------------------
    // Aufgabe 1 (auskommentierter Beispielcode zum Test der Buttons)
    // --------------------------------------------------------------
   /* while (true)
    {
        // Zustand der Taster abfragen
        bool button_state = gpio_get(BUTTON_PIN);
        bool button_state_2 = gpio_get(BUTTON_PIN_2);
        bool button_state_3 = gpio_get(BUTTON_PIN_3);
        bool button_state_4 = gpio_get(BUTTON_PIN_4);

        if (!button_state)
        {
            printf("Button is pressed!(15)\n"); // Button 15 gedrückt
        }
        else if (!button_state_2)
        {
            printf("Button is pressed!(10)\n"); // Button 10 gedrückt
        }
        else if (!button_state_3)
        {
            printf("Button is pressed!(11)\n"); // Button 11 gedrückt
        }
        else if (!button_state_4)
        {
            printf("Button is pressed!(14)\n"); // Button 14 gedrückt
        }
        else
        {
            // Kein Taster gedrückt
        }

        // Kurze Pause gegen Prellen und CPU-Last
        sleep_ms(100);
    }*/

    // --------------------------------------------------------------
    // Aufgabe 2 (Test: Taster steuern einzelne LEDs)
    // --------------------------------------------------------------
    /*while (true)
    {
        // Tasterzustände lesen
        bool button_state = gpio_get(BUTTON_PIN);
        bool button_state_2 = gpio_get(BUTTON_PIN_2);
        bool button_state_3 = gpio_get(BUTTON_PIN_3);
        bool button_state_4 = gpio_get(BUTTON_PIN_4);

        if (!button_state)
        {
            printf("Button is pressed!(15)\n");
            ws2812_clear(&ws);                              // LEDs löschen
            ws2812_set_pixel_color_rgb(&ws, 0, 255, 0, 0);  // LED 0 rot
            ws2812_show(&ws);                               // Anzeige aktualisieren
            sleep_ms(200);
        }
        else if (!button_state_2)
        {
            printf("Button is pressed!(10)\n");
            ws2812_clear(&ws);
            ws2812_set_pixel_color_rgb(&ws, 1, 0, 255, 0);  // LED 1 grün
            ws2812_show(&ws);
            sleep_ms(200);  
        }
        else if (!button_state_3)
        {
            printf("Button is pressed!(11)\n");
            ws2812_clear(&ws);
            ws2812_set_pixel_color_rgb(&ws, 2, 0, 0, 255);  // LED 2 blau
            ws2812_show(&ws);
            sleep_ms(200);
        }
        else if (!button_state_4)
        {
            printf("Button is pressed!(14)\n");
            ws2812_clear(&ws);
            ws2812_set_pixel_color_rgb(&ws, 3, 255, 255, 0); // LED 3 gelb
            ws2812_show(&ws);
            sleep_ms(200);
        }
        else
        {
            // Kein Taster gedrückt
        }

        // Kurze Pause, um CPU zu entlasten
        sleep_ms(100);
    }*/
    
    // --------------------------------------------------------------
    // Aufgabe 3 (aktive Version): Animationen per Taster auslösen
    // --------------------------------------------------------------
    while (true)
    {
        // Tasterzustände abfragen
        bool button_state = gpio_get(BUTTON_PIN);
        bool button_state_2 = gpio_get(BUTTON_PIN_2);
        bool button_state_3 = gpio_get(BUTTON_PIN_3);
        bool button_state_4 = gpio_get(BUTTON_PIN_4);

        if (!button_state)
        {
            printf("Button is pressed!(15)\n");  // Ausgabe auf Konsole
            anim_lauflicht_vor(&ws, num_leds);   // Animation: Lauflicht vorwärts
        }
        else if (!button_state_2)
        {
            printf("Button is pressed!(10)\n");
            anim_lauflicht_zurueck(&ws, num_leds); // Animation: Lauflicht rückwärts
        }
        else if (!button_state_3)
        {
            printf("Button is pressed!(11)\n");
            anim_blink_all(&ws, num_leds);         // Animation: Alle LEDs blinken
        }
        else if (!button_state_4)
        {
            printf("Button is pressed!(14)\n");
            anim_wipe(&ws, num_leds);              // Animation: Wipe-Effekt
        }
        else
        {
            // Kein Taster gedrückt
        }

        // Kurze Pause, um CPU zu entlasten und Prellen zu vermeiden
        sleep_ms(100);
    }
}
