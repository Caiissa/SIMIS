package de.moebelhaus.lager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LagerPanel extends JPanel {

    private final DefaultTableModel masterModel;
    private final DefaultTableModel detailModel;

    private final JTable masterTable;
    private final JTable detailTable;

    public LagerPanel() {
        setLayout(new BorderLayout());

        // =========================
        // Suchleiste
        // =========================
        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout(10, 0));
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.add(new JLabel("Bestand suchen (Produkt/Farbe/Material/VarianteID):"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        // =========================
        // Master: Varianten gesamt
        // =========================
        masterModel = new DefaultTableModel(
                new Object[]{"VarianteID", "Produkt", "Farbe", "Größe", "Material", "Bestand gesamt"}, 0
        );
        masterTable = new JTable(masterModel);
        masterTable.setFillsViewportHeight(true);

        JScrollPane masterScroll = new JScrollPane(masterTable);

        // =========================
        // Detail: Filialbestände
        // =========================
        detailModel = new DefaultTableModel(
                new Object[]{"FilialeID", "Filiale", "Bestand"}, 0
        );
        detailTable = new JTable(detailModel);
        detailTable.setFillsViewportHeight(true);

        JScrollPane detailScroll = new JScrollPane(detailTable);

        // =========================
        // SplitPane (links/rechts)
        // =========================
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, masterScroll, detailScroll);
        split.setDividerLocation(700);
        add(split, BorderLayout.CENTER);

        // =========================
        // Events
        // =========================
        suchenBtn.addActionListener(e -> ladeMaster(sucheField.getText()));

        // Enter im Suchfeld startet Suche
        sucheField.addActionListener(e -> ladeMaster(sucheField.getText()));

        // Auswahl in Master -> Detail laden
        masterTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting() && masterTable.getSelectedRow() != -1) {
                int varianteId = (int) masterTable.getValueAt(masterTable.getSelectedRow(), 0);
                ladeDetail(varianteId);
            }
        });

        // Initial laden
        ladeMaster("");
    }

    private void ladeMaster(String suchtext) {
        masterModel.setRowCount(0);
        detailModel.setRowCount(0);

        List<LagerbestandRow> rows = LagerbestandDAO.searchBestand(suchtext);

        for (LagerbestandRow r : rows) {
            masterModel.addRow(new Object[]{
                    r.getVarianteId(),
                    r.getProduktName(),
                    r.getFarbe(),
                    r.getGroesse(),
                    r.getMaterial(),
                    r.getBestandTotal()
            });
        }
    }

    private void ladeDetail(int varianteId) {
        detailModel.setRowCount(0);

        List<FilialBestandRow> rows = LagerbestandDAO.getBestandJeFiliale(varianteId);

        // Falls es keine Einträge gibt, trotzdem sauber anzeigen
        if (rows.isEmpty()) {
            detailModel.addRow(new Object[]{null, "Keine Lagerdaten vorhanden", 0});
            return;
        }

        for (FilialBestandRow r : rows) {
            detailModel.addRow(new Object[]{
                    r.getFilialeId(),
                    r.getFilialName(),
                    r.getBestand()
            });
        }
    }
}
