package views;
import models.Profile;
import javax.swing.*;
import java.awt.*;

public class MainScreenFrame extends JPanel {
    private JTabbedPane tabbedPane;
    private JPanel profileTab, postsTab,messagesTab;

    public MainScreenFrame(Profile profile) {
        setLayout(new GridLayout(1, 1));

        // add components to Profile here
        profileTab = new JPanel();
        profileTab.add(new UserProfilePanel(profile));
        profileTab.setLayout(new GridBagLayout());

        // add components to Posts here
        postsTab = new JPanel();
        postsTab.add(new AllPostPanel(profile));
        postsTab.setLayout(new GridBagLayout());

        // add components to Messages here
        messagesTab = new JPanel();
        messagesTab.add(new MessageListPanel(Profile.getUsersUsernames(profile.getUsername())));
        messagesTab.setLayout(new GridBagLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Profile", profileTab);
        tabbedPane.addTab("Posts", postsTab);
        tabbedPane.addTab("Messages", messagesTab);

        add(tabbedPane);


    }
}
