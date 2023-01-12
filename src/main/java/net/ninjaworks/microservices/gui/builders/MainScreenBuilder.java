package net.ninjaworks.microservices.gui.builders;

import net.ninjaworks.microservices.gui.AdminGUI;

import javax.swing.*;
import java.awt.*;

public class MainScreenBuilder {

    static JLabel serversConnectedLabel;

    public static JPanel build() {

        JLabel titleLabel = new JLabel("Welcome back! Here's the latest info:");
        titleLabel.setBounds(600, 50, 200, 25);
        titleLabel.setForeground(Color.GREEN);

        serversConnectedLabel = new JLabel("Servers currently connected: "
                + AdminGUI.serversConnected);
        serversConnectedLabel.setBounds(100, 100, 500, 25);
        serversConnectedLabel.setForeground(Color.GREEN);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        panel.setLayout(null);
        panel.add(titleLabel);
        panel.add(serversConnectedLabel);
        panel.setBackground(Color.DARK_GRAY);

        return panel;
    }
}
