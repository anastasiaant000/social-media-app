package views;

import models.Profile;

import javax.swing.*;
import java.awt.*;

public class MainScreenFrame extends JPanel {
    private JTabbedPane tabbedPane;
    private JPanel profileTab, postsTab,messagesTab;

    public MainScreenFrame(Profile profile) {
        setLayout(new GridLayout(1, 1));

       
        profileTab = new JPanel();
        profileTab.add(new UserProfilePanel(profile));
        profileTab.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

       
        postsTab = new JPanel();
        postsTab.add(new Signup());
        postsTab.setLayout(new GridBagLayout());

        
        messagesTab = new JPanel();
        messagesTab.add(new Signup());
        messagesTab.setLayout(new GridBagLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Profile", profileTab);
        tabbedPane.addTab("Posts", postsTab);
        tabbedPane.addTab("Messages", messagesTab);

        add(tabbedPane);


    }
}
