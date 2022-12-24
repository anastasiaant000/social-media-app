

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserProfilePanel extends JPanel {
    public UserProfilePanel(Profile profile) {
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(350, 300));
        GridBagConstraints constraints = new GridBagConstraints();

//        // Add a profile picture
//        ImageIcon profilePic = new ImageIcon("org/example/views/profile-pic.png");
//        JLabel picLabel = new JLabel(profilePic);
//        add(picLabel);

        JLabel nameLabel = new JLabel("Name");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(nameLabel, constraints);

        JTextField nameField = new JTextField(profile.getName(), 20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        nameField.setEditable(false);
        add(nameField, constraints);

        JLabel lastNameLabel = new JLabel("Last Name");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(lastNameLabel, constraints);

        JTextField lastNameField = new JTextField(profile.getLastName(), 20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        lastNameField.setEditable(false);
        add(lastNameField, constraints);

        JLabel emailLabel = new JLabel("Email");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(emailLabel, constraints);

        JTextField emailField = new JTextField(profile.getEmail(), 20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 5, 5, 5);
        emailField.setEditable(false);
        add(emailField, constraints);

        JLabel dob = new JLabel("Birthday");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(dob, constraints);

        JTextField dobField = new JTextField(profile.getDob().toString(), 20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        dobField.setToolTipText("dd/MM/yyyy");
        dobField.setEditable(false);
        add(dobField, constraints);

        JLabel biographyLabel = new JLabel("Biography");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(biographyLabel, constraints);

        JTextArea biographyTextArea = new JTextArea(profile.getBio(), 3, 20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 5, 5, 5);
        JScrollPane scrollPane = new JScrollPane(biographyTextArea);
        biographyTextArea.setLineWrap(true);
        biographyTextArea.setEditable(false);
        add(scrollPane, constraints);

        JLabel usernameLabel = new JLabel("Username");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(usernameLabel, constraints);

        JTextField usernameField = new JTextField(profile.getUsername(), 20);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.insets = new Insets(5, 5, 5, 5);
        usernameField.setEditable(false);
        add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password");
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(profile.getPassword(), 20);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.insets = new Insets(5, 5, 5, 5);
        passwordField.setEchoChar('*');
        passwordField.setEditable(false);
//        passwordField.putClientProperty("JPasswordField.cutCopyAllowed",true);
        add(passwordField, constraints);

        JButton edit = new JButton("Edit");
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(edit, constraints);

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setEditable(true);
                lastNameField.setEditable(true);
                emailField.setEditable(true);
                dobField.setEditable(true);
                biographyTextArea.setEditable(true);
                usernameField.setEditable(true);
                passwordField.setEditable(true);
                edit.setText("Update");


                edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Profile updateProfileData = null;
                        try {
                            updateProfileData = new Profile(usernameField.getText(),
                                    nameField.getText(),
                                    lastNameField.getText(),
                                    new SimpleDateFormat("dd/MM/yyyy").parse(dobField.getText()),
                                    new String(passwordField.getPassword()),
                                    biographyTextArea.getText(),
                                    emailField.getText());
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        var x = Profile.updateUser(updateProfileData);
                        Utilities.infoBox("Profile details updated successfully", "Update Completed");
                        nameField.setEditable(false);
                        lastNameField.setEditable(false);
                        emailField.setEditable(false);
                        dobField.setEditable(false);
                        biographyTextArea.setEditable(false);
                        usernameField.setEditable(false);
                        passwordField.setEditable(false);
                        edit.setText("Edit");
                        repaint();
                        revalidate();

                    }
                });


            }
        });

//        JLabel friendlistLabel = new JLabel("Friends");
//        constraints.gridx = 0;
//        constraints.gridy = 7;
//        constraints.insets = new Insets(5, 5, 5, 5);
//        add(friendlistLabel, constraints);
//
//        // Add a list of the user's friends
//        JList<String> friendsList = new JList<>(new String[]{"Alice", "Bob", "Charlie"});
//        constraints.gridx = 1;
//        constraints.gridy = 7;
//        constraints.insets = new Insets(5, 5, 5, 5);
//        add(friendsList, constraints);


    }
}
