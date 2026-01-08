package de.moebelhaus.kunden;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KundenPanel extends JPanel {

    private DefaultTableModel model;

    public KundenPanel() {
        setLayout(new BorderLayout());

        JTextField sucheField = new JTextField();
        JButton suchenBtn = new JButton("Suchen");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Kunden suchen:"), BorderLayout.WEST);
        top.add(sucheField, BorderLayout.CENTER);
        top.add(suchenBtn, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{"ID", "Vorname", "Nachname", "Email"}, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        suchenBtn.addActionListener(e -> {
            model.setRowCount(0);
            List<Kunde> kunden = KundeDAO.searchKunden(sucheField.getText());
            for (Kunde k : kunden) {
                model.addRow(new Object[]{
                        k.getId(), k.getVorname(), k.getNachname(), k.getEmail()
                });
            }
        });
    }
}
