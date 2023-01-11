package views;
import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JFrame {

    public WindowPanel() {

        setTitle("SN-AUEB");
        add(new LoginSignupFrame(this));
        setMinimumSize(new Dimension(700, 900));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
