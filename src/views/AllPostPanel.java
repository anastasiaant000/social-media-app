package views;

import models.PostLikes;
import models.Posts;
import models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

public class AllPostPanel extends JPanel {

    private JPanel postsListPanel;
    private JButton createPostButton;
    private List<Posts> posts;

    public AllPostPanel(Profile profile) {

        JPanel container = new JPanel();
        container.setMaximumSize(new Dimension(100, 100));
        setMinimumSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        setBackground(Color.CYAN);

        updatePanel(container, profile);

        createPostButton = new JButton("Create Post");
        createPostButton.addActionListener(e -> {
            PostPanel newPost = new PostPanel(profile);
            JFrame frame = new JFrame("Posts frame");
            frame.add(newPost);
            frame.setMinimumSize(new Dimension(200, 150));
            frame.pack();
            frame.setVisible(true);
            newPost.getSubmitButton().addActionListener(e1 -> {
                updatePanel(container, profile);
                frame.setVisible(false);

            });
        });

        add(container);
        add(createPostButton);

    }

    private void updatePanel(JPanel container, Profile profile) {
        // Remove all components, but don't remove the scroll pane
        container.removeAll();

        postsListPanel = new JPanel();
        postsListPanel.setLayout(new BoxLayout(postsListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(postsListPanel);

        posts = Posts.getAllPosts();
        //Reverse the posts list, so we get always the newest post first
        Collections.reverse(posts);
        for (Posts post : posts) {
            var singlePost = new PostLayout(post.getPostTitle(), post.getPoster().getUsername(),
                    post.getPostLikes(post.getPostTitle()), post.getPostText());
            singlePost.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getClickCount() == 2) {
                        PostLikes.addLike(new PostLikes(post, profile.getUsername(),
                                post.getPoster().getUsername()));
                        updatePanel(container, profile);

                    }

                }
            });

            postsListPanel.add(singlePost);
            postsListPanel.add(new JSeparator());

        }
        container.add(scrollPane);
        container.revalidate();
        container.repaint();
    }

    public static void main(String[] args) {
        var user = Profile.getUser("topas404");

        AllPostPanel allPostPanel = new AllPostPanel(user);

        JFrame frame = new JFrame("Posts frame");
        frame.add(allPostPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static class PostLayout extends JPanel {
        private String title;
        private String poster;
        private int likes;
        private String text;

        public String getTitle() {
            return title;
        }

        public String getPoster() {
            return poster;
        }

        public int getLikes() {
            return likes;
        }

        public String getText() {
            return text;
        }

        public PostLayout(String title, String poster, int likes, String text) {
            this.title = title;
            this.poster = poster;
            this.likes = likes;
            this.text = text;

            setLayout(new GridLayout(2, 2));
            setMaximumSize(new Dimension(300, 200));


            // Create the title label
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.PLAIN, 13));

            // Create the poster label
            JLabel posterLabel = new JLabel(poster);
            posterLabel.setFont(posterLabel.getFont().deriveFont(Font.ITALIC, 9));

            // Create the likes label
            JLabel likesLabel = new JLabel("Likes: " + likes);
            likesLabel.setFont(likesLabel.getFont().deriveFont(Font.ITALIC, 9));

            // Create the message label
            JTextArea messageLabel = new JTextArea(text);
            messageLabel.setEditable(false);
            messageLabel.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(messageLabel);


            // Add the labels to the panel
            add(titleLabel, BorderLayout.NORTH);
            add(posterLabel, BorderLayout.EAST);
            add(scrollPane, BorderLayout.CENTER);
            add(likesLabel, BorderLayout.AFTER_LAST_LINE);
        }


    }
}
