package views;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;

public class LoginSignupFrame extends JPanel {
    private JTabbedPane tabbedPane;
    private JPanel loginTab, registerTab;

    public LoginSignupFrame(JFrame window) {
        setLayout(new GridLayout(1, 1));

        
        loginTab = new JPanel();
        Login login = new Login(this,window);
        loginTab.add(login);
        loginTab.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();



        
        registerTab = new JPanel();
        registerTab.add(new Signup());
        registerTab.setLayout(new GridBagLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", loginTab);
        tabbedPane.addTab("Register", registerTab);

        add(tabbedPane);

    }


}