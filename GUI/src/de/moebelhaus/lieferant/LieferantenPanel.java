package de.moebelhaus.lieferant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LieferantenPanel extends JPanel {

    private DefaultTableModel model;

    public LieferantenPanel() {
        setLayout(new BorderLayout());

        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Lieferanten suchen:"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{"ID", "Name", "E-Mail", "Adresse", "Telefon", "Kontaktperson"}, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadLieferanten("");

        suchenBtn.addActionListener(e -> {
            model.setRowCount(0);
            List<Lieferant> lieferanten =
                    LieferantDAO.searchLieferanten(sucheField.getText());

            for (Lieferant l : lieferanten) {
                model.addRow(new Object[]{
                        l.getLieferantID(),
                        l.getName(),
                        l.getEmail(),
                        l.getAdresse(),
                        l.getTelNumber(),
                        l.getKntPerson()
                });
            }
        });
    }

    private void loadLieferanten(String suchtext) {
        model.setRowCount(0);
        List<Lieferant> lieferanten =
                LieferantDAO.searchLieferanten(suchtext);

        for (Lieferant l : lieferanten) {
            model.addRow(new Object[]{
                    l.getLieferantID(),
                    l.getName(),
                    l.getEmail(),
                    l.getAdresse(),
                    l.getTelNumber(),
                    l.getKntPerson()
            });
        }
    }
}
