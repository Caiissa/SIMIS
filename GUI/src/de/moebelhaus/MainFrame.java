package de.moebelhaus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("Möbelhaus – Bestand");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        ladeDaten();
    }

    private void initUI() {
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Bestand"}, 0
        );

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void ladeDaten() {
        List<Produkt> produkte = ProduktDAO.getAlleProdukte();

        for (Produkt p : produkte) {
            tableModel.addRow(
                    new Object[]{p.getId(), p.getName(), p.getBestand()}
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
