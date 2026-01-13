package de.moebelhaus.filiale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FilialenPanel extends JPanel {

    private DefaultTableModel model;

    public FilialenPanel() {
        setLayout(new BorderLayout());

        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Filialen suchen:"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{
                        "Filiale-ID", "Name", "Adresse", "PLZ", "Ort",
                        "Telefon", "Öffnungszeiten", "Menge verfügbar"
                }, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadFilialen("");

        suchenBtn.addActionListener(e -> {
            model.setRowCount(0);
            List<Filiale> filialen =
                    FilialeDAO.searchFilialen(sucheField.getText());

            for (Filiale f : filialen) {
                model.addRow(new Object[]{
                        f.getFilialeID(),
                        f.getName(),
                        f.getAdresse(),
                        f.getPlz(),
                        f.getOrt(),
                        f.getTelefon(),
                        f.getOeffnungszeiten(),
                        f.getMengeVerfuegbar()
                });
            }
        });
    }

    private void loadFilialen(String suchtext) {
        model.setRowCount(0);
        List<Filiale> filialen =
                FilialeDAO.searchFilialen(suchtext);

        for (Filiale f : filialen) {
            model.addRow(new Object[]{
                    f.getFilialeID(),
                    f.getName(),
                    f.getAdresse(),
                    f.getPlz(),
                    f.getOrt(),
                    f.getTelefon(),
                    f.getOeffnungszeiten(),
                    f.getMengeVerfuegbar()
            });
        }
    }
}
