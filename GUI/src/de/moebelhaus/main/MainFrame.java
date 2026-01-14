package de.moebelhaus.main;

import de.moebelhaus.bestellung.BestellungsPanel;
import de.moebelhaus.filiale.FilialenPanel;
import de.moebelhaus.kunden.KundenPanel;
import de.moebelhaus.lieferant.LieferantenPanel;
import de.moebelhaus.produkte.ProduktPanel;
import de.moebelhaus.lager.LagerPanel;
import de.moebelhaus.rabatt.RabattPanel;
import de.moebelhaus.rueckgabe.RueckgabenPanel;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPanel;

    public MainFrame() {
        setTitle("SIMIS – Verwaltungssoftware");
        setSize(1900, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createMenu(), BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        showPanel(new JLabel("Willkommen im SIMIS Verwaltungssystem"));

        setVisible(true);
    }

    private JPanel createMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setPreferredSize(new Dimension(200, 0));

        JButton kundenBtn = new JButton("Kunden");
        JButton produkteBtn = new JButton("Produkte");
        JButton lagerBtn = new JButton("Lager");
        JButton bestellungBtn = new JButton("Bestellung");
        JButton filialenBtn = new JButton("Filialen");
        JButton lieferantBtn = new JButton("Lieferant");
        JButton rabattBtn = new JButton("Rabatt");
        JButton rueckgabeBtn = new JButton("Rueckgabe");

        kundenBtn.addActionListener(e -> showPanel(new KundenPanel()));
        produkteBtn.addActionListener(e -> showPanel(new ProduktPanel()));
        lagerBtn.addActionListener(e -> showPanel(new LagerPanel()));
        bestellungBtn.addActionListener(e -> {showPanel(new BestellungsPanel());});
        filialenBtn.addActionListener(e -> showPanel(new FilialenPanel()));
        lieferantBtn.addActionListener(e -> showPanel(new LieferantenPanel()));
        rabattBtn.addActionListener(e -> showPanel(new RabattPanel()));
        rueckgabeBtn.addActionListener(e -> showPanel(new RueckgabenPanel()));

        menu.add(kundenBtn);
        menu.add(produkteBtn);
        menu.add(lagerBtn);
        menu.add(bestellungBtn);
        menu.add(filialenBtn);
        menu.add(lieferantBtn);
        menu.add(rabattBtn);
        menu.add(rueckgabeBtn);


        return menu;
    }

    private void showPanel(Component panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
