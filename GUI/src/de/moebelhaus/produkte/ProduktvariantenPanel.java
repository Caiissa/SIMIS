package de.moebelhaus.produkte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProduktvariantenPanel extends JPanel {

    private DefaultTableModel model;

    public ProduktvariantenPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new Object[]{"ID", "Farbe", "Größe", "Material", "Preis (€)", "Verfügbar"}, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void ladeVarianten(int produktId) {
        model.setRowCount(0);

        List<Produktvariante> varianten =
                ProduktvarianteDAO.getVariantenByProdukt(produktId);

        for (Produktvariante v : varianten) {
            model.addRow(new Object[]{
                    v.getVarianteId(),
                    v.getFarbe(),
                    v.getGroesse(),
                    v.getMaterial(),
                    String.format("%.2f", v.getPreis()),
                    v.isVerfuegbar() ? "Ja" : "Nein"
            });
        }
    }
}
