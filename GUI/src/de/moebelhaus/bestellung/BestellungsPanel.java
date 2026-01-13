package de.moebelhaus.bestellung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BestellungsPanel extends JPanel {

    private DefaultTableModel model;

    public BestellungsPanel() {
        setLayout(new BorderLayout());

        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Bestellungen suchen:"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{"Bestellung-ID", "Filiale-ID", "Datum", "Menge", "Status"}, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBestellungen("");

        suchenBtn.addActionListener(e -> {
            model.setRowCount(0);
            List<Bestellung> bestellungen =
                    BestellungDAO.searchBestellungen(sucheField.getText());

            for (Bestellung b : bestellungen) {
                model.addRow(new Object[]{
                        b.getBestellungID(),
                        b.getFilialeID(),
                        b.getDatumBestellung(),
                        b.getMenge(),
                        b.getStatus()
                });
            }
        });
    }

    private void loadBestellungen(String suchtext) {
        model.setRowCount(0);
        List<Bestellung> bestellungen =
                BestellungDAO.searchBestellungen(suchtext);

        for (Bestellung b : bestellungen) {
            model.addRow(new Object[]{
                    b.getBestellungID(),
                    b.getFilialeID(),
                    b.getDatumBestellung(),
                    b.getMenge(),
                    b.getStatus()
            });
        }
    }
}
