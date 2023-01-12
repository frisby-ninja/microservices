package net.ninjaworks.microservices.gui;

import net.ninjaworks.microservices.gui.builders.FrameBuilder;
import net.ninjaworks.microservices.gui.builders.LoginScreenBuilder;
import net.ninjaworks.microservices.gui.builders.MainScreenBuilder;
import net.ninjaworks.microservices.jobs.JobGiver;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AdminGUI {

    static JFrame frame = FrameBuilder.build("Admin GUI");
    public static boolean loggedIn;
    public static int serversConnected = 0;

    public static void load() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }

        frame.add(LoginScreenBuilder.build(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void tryLogin() {
        if(loggedIn) {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.add(MainScreenBuilder.build(), BorderLayout.CENTER);
        }
    }

    public static void refreshMainScreen() {
        if(!(JobGiver.getAllConnectedPCs() == null)) {
            serversConnected = JobGiver.getAllConnectedPCs().length;
        } else {
            serversConnected = 0;
        }
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(MainScreenBuilder.build(), BorderLayout.CENTER);
    }
}
