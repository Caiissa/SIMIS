package de.moebelhaus.produkte;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProduktPanel extends JPanel {

    private DefaultTableModel produktModel;
    private DefaultTableModel variantenModel;

    private JTable produktTable;
    private JTable variantenTable;

    public ProduktPanel() {
        setLayout(new BorderLayout());

        // ===============================
        // 🔍 Suchleiste für Produkte
        // ===============================
        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(new JLabel("Produkt suchen:"), BorderLayout.WEST);
        topPanel.add(sucheField, BorderLayout.CENTER);
        topPanel.add(suchenBtn, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ===============================
        // 📋 Produkt-Tabelle (links)
        // ===============================
        produktModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Basispreis (€)", "Aktiv"}, 0
        );

        produktTable = new JTable(produktModel);
        JScrollPane produktScrollPane = new JScrollPane(produktTable);

        // ===============================
        // 📋 Varianten-Tabelle (rechts)
        // ===============================
        variantenModel = new DefaultTableModel(
                new Object[]{"ID", "Farbe", "Größe", "Material", "Preis (€)", "Verfügbar"}, 0
        );

        variantenTable = new JTable(variantenModel);
        JScrollPane variantenScrollPane = new JScrollPane(variantenTable);

        // ===============================
        // ↔ SplitPane (Produkte | Varianten)
        // ===============================
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                produktScrollPane,
                variantenScrollPane
        );
        splitPane.setDividerLocation(500);

        add(splitPane, BorderLayout.CENTER);

        // ===============================
        // 🔄 Events
        // ===============================

        // Suche
        suchenBtn.addActionListener(e ->
                ladeProdukte(sucheField.getText())
        );

        // Produktauswahl → Varianten laden
        produktTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()
                                && produktTable.getSelectedRow() != -1) {

                            int produktId = (int) produktTable.getValueAt(
                                    produktTable.getSelectedRow(), 0
                            );
                            ladeVarianten(produktId);
                        }
                    }
                }
        );

        // Initial: alle Produkte laden
        ladeProdukte("");
    }

    // ===============================
    // 📦 Produkte laden
    // ===============================
    private void ladeProdukte(String suchtext) {
        produktModel.setRowCount(0);
        variantenModel.setRowCount(0); // Varianten leeren

        List<Produkt> produkte = ProduktDAO.searchProdukte(suchtext);

        for (Produkt p : produkte) {
            produktModel.addRow(new Object[]{
                    p.getProduktId(),
                    p.getName(),
                    String.format("%.2f", p.getBasispreis()),
                    p.isAktiv() ? "Ja" : "Nein"
            });
        }
    }

    // ===============================
    // 🧩 Varianten laden
    // ===============================
    private void ladeVarianten(int produktId) {
        variantenModel.setRowCount(0);

        List<Produktvariante> varianten =
                ProduktvarianteDAO.getVariantenByProdukt(produktId);

        for (Produktvariante v : varianten) {
            variantenModel.addRow(new Object[]{
                    v.getVarianteId(),
                    v.getFarbe(),
                    v.getGroesse(),
                    v.getMaterial(),
                    String.format("%.2f", v.getPreis()),
                    v.isVerfuegbar() ? "Ja" : "Nein"
            });
        }
    }
}
