package de.moebelhaus.lager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LagerPanel extends JPanel {

    private DefaultTableModel model;

    public LagerPanel() {
        setLayout(new BorderLayout());

        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Lager durchsuchen:"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{"Lager-ID", "Bestellung-ID", "Menge verfügbar"}, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadLager("");

        suchenBtn.addActionListener(e -> {
            model.setRowCount(0);
            List<Lager> lagerListe = LagerDAO.searchLager(sucheField.getText());

            for (Lager l : lagerListe) {
                model.addRow(new Object[]{
                        l.getLagerID(),
                        l.getBestellungID(),
                        l.getMengeVerfuegbar()
                });
            }
        });
    }

    private void loadLager(String suchtext) {
        model.setRowCount(0);
        List<Lager> lagerListe = LagerDAO.searchLager(suchtext);

        for (Lager l : lagerListe) {
            model.addRow(new Object[]{
                    l.getLagerID(),
                    l.getBestellungID(),
                    l.getMengeVerfuegbar()
            });
        }
    }
}
