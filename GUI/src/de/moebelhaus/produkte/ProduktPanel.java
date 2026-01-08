package de.moebelhaus.produkte;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProduktPanel extends JPanel {

    public ProduktPanel() {
        setBackground(Color.WHITE);
        setLayout(new GridLayout(0, 3, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Produkt> produkte = ProduktDAO.getAlleProdukte();

        for (Produkt p : produkte) {
            add(createProduktCard(p));
        }
    }

    private JPanel createProduktCard(Produkt p) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel name = new JLabel(p.getName());
        name.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel bestand = new JLabel("Bestand: " + p.getBestand());
        bestand.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton details = new JButton("Details");
        details.setBackground(new Color(255, 204, 0)); // IKEA-Gelb
        details.setFocusPainted(false);

        card.add(name);
        card.add(Box.createVerticalStrut(10));
        card.add(bestand);
        card.add(Box.createVerticalStrut(15));
        card.add(details);

        return card;
    }
}
