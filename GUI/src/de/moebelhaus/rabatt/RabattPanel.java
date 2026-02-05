package de.moebelhaus.rabatt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RabattPanel extends JPanel {

    private DefaultTableModel model;

    public RabattPanel() {
        setLayout(new BorderLayout());

        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Rabatte suchen:"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{"Aktion-ID", "Rabatt %", "Startdatum", "Enddatum", "Bezeichnung"}, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadRabatte("");

        suchenBtn.addActionListener(e -> {
            model.setRowCount(0);
            List<Rabatt> rabatte =
                    RabattDAO.searchRabatte(sucheField.getText());

            for (Rabatt r : rabatte) {
                model.addRow(new Object[]{
                        r.getAktionID(),
                        r.getRabattProzent(),
                        r.getStartDatum(),
                        r.getEndDatum(),
                        r.getBezeichnung()
                });
            }
        });
    }

    private void loadRabatte(String suchtext) {
        model.setRowCount(0);
        List<Rabatt> rabatte =
                RabattDAO.searchRabatte(suchtext);

        for (Rabatt r : rabatte) {
            model.addRow(new Object[]{
                    r.getAktionID(),
                    r.getRabattProzent(),
                    r.getStartDatum(),
                    r.getEndDatum(),
                    r.getBezeichnung()
            });
        }
    }
}
