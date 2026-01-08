package de.moebelhaus.produkte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProduktPanel extends JPanel {

    private DefaultTableModel model;

    public ProduktPanel() {
        setLayout(new BorderLayout());

        // 🔍 Suchleiste
        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout(10, 0));
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.add(new JLabel("Produkt suchen:"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        // 📋 Tabelle
        model = new DefaultTableModel(
                new Object[]{"ID", "Name", "Basispreis (€)", "Aktiv"}, 0
        );

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // 🔄 Button-Logik
        suchenBtn.addActionListener(e -> ladeProdukte(sucheField.getText()));

        // Initial: alle Produkte laden
        ladeProdukte("");
    }

    private void ladeProdukte(String suchtext) {
        model.setRowCount(0);

        List<Produkt> produkte = ProduktDAO.searchProdukte(suchtext);

        for (Produkt p : produkte) {
            model.addRow(new Object[]{
                    p.getProduktId(),
                    p.getName(),
                    String.format("%.2f", p.getBasispreis()),
                    p.isAktiv() ? "Ja" : "Nein"
            });
        }
    }
}
