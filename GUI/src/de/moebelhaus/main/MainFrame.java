package de.moebelhaus.main;

import de.moebelhaus.kunden.KundenPanel;
import de.moebelhaus.produkte.ProduktPanel;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPanel;

    public MainFrame() {
        setTitle("SIMIS – Verwaltungssoftware");
        setSize(1100, 700);
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

        kundenBtn.addActionListener(e -> showPanel(new KundenPanel()));
        produkteBtn.addActionListener(e -> showPanel(new ProduktPanel()));


        menu.add(kundenBtn);
        menu.add(produkteBtn);

        return menu;
    }

    private void showPanel(Component panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
