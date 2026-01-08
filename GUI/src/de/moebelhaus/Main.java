package de.moebelhaus;

import de.moebelhaus.login.LoginFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(LoginFrame::new);
    }
}
