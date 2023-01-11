package views;

import models.Posts;
import models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostPanel extends JPanel {
    private JTextField titleField;
    private JTextArea contentArea;
    private JButton submitButton;
    private Profile posterProfile;

    public JButton getSubmitButton() {
        return submitButton;
    }

    public PostPanel(Profile profile) {
        posterProfile = profile;
        setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Post Title:"));
        titleField = new JTextField(20);
        titlePanel.add(titleField);
        add(titlePanel, BorderLayout.NORTH);

        contentArea = new JTextArea(10, 30);
        add(new JScrollPane(contentArea), BorderLayout.CENTER);

        submitButton = new JButton("Submit Post");
        submitButton.addActionListener(new SubmitListener());
        add(submitButton, BorderLayout.SOUTH);
    }

    private class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String content = contentArea.getText();

            // Submit the post to database
            Posts.createPost(new Posts(posterProfile, title, content));
            Utilities.infoBox(PostPanel.this, "Post submitted successfully!", "Post Submittion");
        }
    }

    public static void main(String[] args) {
        var user = Profile.getUser("topas404");

        PostPanel postPanel = new PostPanel(user);
        JFrame frame = new JFrame("Posts frame");
        frame.add(postPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        java socket server with a map of users
    }

}
