package views;

import models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MessageListPanel extends JPanel {
    public MessageListPanel(List<String> profiles, Profile profile) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel title = new JLabel("Select a user to start a chat");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(title, constraints);

        // Create a JList to hold the names of the profiles
        JList<String> profilesList = new JList<>(profiles.toArray(new String[0]));
        // Add the list to a scroll pane so that it can scroll if there are too many profiles to fit on the screen
        JScrollPane scrollPane = new JScrollPane(profilesList);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setMinimumSize(new Dimension(300, 300));
        add(scrollPane, constraints);

        profilesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2) {

                    // Double-click detected
                    int index = profilesList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        //TODO here we will start the seperate chat client
                        Object o = list.getModel().getElementAt(index);

                        ChatClient chatClient = new ChatClient(profile.getUsername(),o.toString());
                    }
                }
            }
        });
    }

    
    
}
