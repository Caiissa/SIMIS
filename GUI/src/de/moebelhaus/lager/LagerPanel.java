package de.moebelhaus.lager;

import de.moebelhaus.produkte.Produkt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LagerPanel extends JPanel {

    private JTable lagerTable;
    private JTable produktTable;

    private DefaultTableModel lagerModel;
    private DefaultTableModel produktModel;

    private LagerDAO lagerDAO;

    public LagerPanel() {
        lagerDAO = new LagerDAO();
        setLayout(new BorderLayout());

        // ===== Lager Tabelle =====
        lagerModel = new DefaultTableModel(
                new String[]{"LagerID", "BestellungID", "Menge verfügbar"}, 0
        );
        lagerTable = new JTable(lagerModel);
        lagerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane lagerScroll = new JScrollPane(lagerTable);

        // ===== Produkt Tabelle =====
        produktModel = new DefaultTableModel(
                new String[]{"ProduktID", "Name", "Basispreis", "Aktiv"}, 0
        );

        produktTable = new JTable(produktModel);

        JScrollPane produktScroll = new JScrollPane(produktTable);

        // ===== Split =====
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                lagerScroll,
                produktScroll
        );
        splitPane.setDividerLocation(400);
        add(splitPane, BorderLayout.CENTER);

        // ===== Daten laden =====
        ladeLager();

        // ===== Listener =====
        lagerTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = lagerTable.getSelectedRow();
                if (row != -1) {
                    int lagerID = (int) lagerModel.getValueAt(row, 0);
                    ladeProdukte(lagerID);
                }
            }
        });
    }

    private void ladeLager() {
        lagerModel.setRowCount(0);
        List<Lager> lagerListe = lagerDAO.getAlleLager();

        for (Lager l : lagerListe) {
            lagerModel.addRow(new Object[]{
                    l.getLagerID(),
                    l.getBestellungID(),
                    l.getMengeVerfuegbar()
            });
        }
    }

    private void ladeProdukte(int lagerID) {
        produktModel.setRowCount(0);
        List<Produkt> produkte = lagerDAO.getProdukteZuLager(lagerID);

        for (Produkt p : produkte) {
            produktModel.addRow(new Object[]{
                    p.getProduktId(),
                    p.getName(),
                    p.getBasispreis(),
                    p.isAktiv()
            });
        }
    }

}
