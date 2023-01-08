import javax.swing.*;
import java.awt.*;

public class Utilities {
    public static void infoBox(Component parentComponent,String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(parentComponent, infoMessage, "Message: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
