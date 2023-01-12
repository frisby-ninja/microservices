package net.ninjaworks.microservices.gui.builders;

import javax.swing.*;
import java.awt.*;

public class FrameBuilder {
    public static JFrame build(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.setIconImage(new ImageIcon(
                System.getProperty("user.dir").replace("\\\\", "/") +
                        "/src/main/resources/assets/gui-icon.png").getImage());
        frame.pack();
        return frame;
    }
}
