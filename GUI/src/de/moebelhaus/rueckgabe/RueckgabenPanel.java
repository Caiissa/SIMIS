package de.moebelhaus.rueckgabe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RueckgabenPanel extends JPanel {

    private DefaultTableModel model;

    public RueckgabenPanel() {
        setLayout(new BorderLayout());

        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Rückgaben suchen:"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{
                        "Rückgabe-ID", "Lagernd", "Verkaufen",
                        "Kunde-ID", "Datum", "Grund", "Erstattung (€)"
                }, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadRueckgaben("");

        suchenBtn.addActionListener(e -> {
            model.setRowCount(0);
            List<Rueckgabe> rueckgaben =
                    RueckgabeDAO.searchRueckgaben(sucheField.getText());

            for (Rueckgabe r : rueckgaben) {
                model.addRow(new Object[]{
                        r.getRueckgabeID(),
                        r.isLagernd(),
                        r.isVerkaufen(),
                        r.getKundeID(),
                        r.getDatum(),
                        r.getGrund(),
                        r.getBetragErstattung()
                });
            }
        });
    }

    private void loadRueckgaben(String suchtext) {
        model.setRowCount(0);
        List<Rueckgabe> rueckgaben =
                RueckgabeDAO.searchRueckgaben(suchtext);

        for (Rueckgabe r : rueckgaben) {
            model.addRow(new Object[]{
                    r.getRueckgabeID(),
                    r.isLagernd(),
                    r.isVerkaufen(),
                    r.getKundeID(),
                    r.getDatum(),
                    r.getGrund(),
                    r.getBetragErstattung()
            });
        }
    }
}
