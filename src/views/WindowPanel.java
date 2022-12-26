package org.example.views;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JFrame {

    public WindowPanel() {

        setTitle("SN-aueb");
        add(new LoginSignupFrame(this));
        setMinimumSize(new Dimension(600, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
