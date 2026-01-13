package de.moebelhaus.login;

import de.moebelhaus.main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("SIMIS – Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        panel.add(new JLabel("Benutzername:"));
        panel.add(userField);
        panel.add(new JLabel("Passwort:"));
        panel.add(passField);
        panel.add(new JLabel());
        panel.add(loginBtn);

        passField.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.equals("0") && pass.equals("0")) {
                dispose();
                new MainFrame();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Falsche Zugangsdaten!",
                        "Login fehlgeschlagen",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.equals("0") && pass.equals("0")) {
                dispose();
                new MainFrame();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Falsche Zugangsdaten!",
                        "Login fehlgeschlagen",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        add(panel);
        setVisible(true);
    }
}
