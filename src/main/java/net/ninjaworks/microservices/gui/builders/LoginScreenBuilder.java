package net.ninjaworks.microservices.gui.builders;

import net.ninjaworks.microservices.frontend.NOutput;
import net.ninjaworks.microservices.gui.AdminGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class LoginScreenBuilder implements ActionListener {

    static JTextField nameField;
    static JPasswordField passwdField;
    static JLabel successLabel;

    public static JPanel build() {

        JLabel titleLabel = new JLabel("Please provide your login information.");
        titleLabel.setBounds(575, 100, 500, 25);
        titleLabel.setForeground(Color.GREEN);

        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setBounds(575, 150, 185, 25);
        nameLabel.setForeground(Color.GREEN);
        nameField = new JTextField();
        nameField.setBounds(575, 175, 185, 25);
        nameField.addActionListener(new LoginScreenBuilder());

        JLabel passwdLabel = new JLabel("Password:");
        passwdLabel.setBounds(575, 200, 185, 25);
        passwdLabel.setForeground(Color.GREEN);
        passwdField = new JPasswordField();
        passwdField.setBounds(575, 225, 185, 25);
        passwdField.addActionListener(new LoginScreenBuilder());

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(625, 260, 100, 25);
        loginButton.addActionListener(new LoginScreenBuilder());
        loginButton.setForeground(Color.GREEN);
        loginButton.setBackground(Color.GRAY);

        successLabel = new JLabel();
        successLabel.setBounds(640, 280, 100, 25);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        panel.setLayout(null);
        panel.add(titleLabel);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(passwdLabel);
        panel.add(passwdField);
        panel.add(loginButton);
        panel.add(successLabel);
        panel.setBackground(Color.DARK_GRAY);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userField = nameField.getText();
        String passField = String.valueOf(passwdField.getPassword());

        if (userField.equals("Frisby") && passField.equals("hi")) {
            successLabel.setText("Login successful!");
            AdminGUI.loggedIn = true;
        } else {
            successLabel.setText("Login failed.");
            AdminGUI.loggedIn = false;
        }
        AdminGUI.tryLogin();
    }
}
