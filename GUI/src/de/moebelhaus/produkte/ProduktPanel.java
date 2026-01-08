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

        if (produkte.isEmpty()) {
            add(new JLabel("Keine Produkte vorhanden."));
            return;
        }

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

        JLabel nameLabel = new JLabel(p.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel preisLabel = new JLabel(String.format("Preis: %.2f €", p.getBasispreis()));
        preisLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton detailsButton = new JButton("Details");
        detailsButton.setBackground(new Color(255, 204, 0)); // IKEA-Gelb
        detailsButton.setFocusPainted(false);
        detailsButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // (Vorbereitung für später)
        detailsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Produkt-ID: " + p.getProduktId(),
                    "Produktdetails",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        card.add(nameLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(preisLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(detailsButton);

        return card;
    }
}
