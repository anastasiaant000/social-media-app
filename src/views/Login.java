package org.example.views;

import org.example.models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel implements ActionListener {

    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login(JPanel loginSignupFrame, JFrame window) throws HeadlessException {
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(350, 300));
        GridBagConstraints constraints = new GridBagConstraints();

        usernameLabel = new JLabel("Username: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(usernameField, constraints);

        passwordLabel = new JLabel("Password: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        passwordField.setEchoChar('*');
        add(passwordField, constraints);

        loginButton = new JButton("Login");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(loginButton, constraints);

        loginButton.addActionListener(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getPassword().length == 0) {
                Utilities.infoBox("Fields cannot be empty", "Login Error");
            } else {
                //TODO May be removed
                var userCheck = Profile.userExits(usernameField.getText());
                if (!userCheck) {
                    Utilities.infoBox("User doesn't exist", "Login Error");
                } else {
                    var currUser = Profile.getUser(usernameField.getText());
                    var currUserPassword = new String(passwordField.getPassword());
                    if (currUserPassword.equals(currUser.getPassword())
                            && usernameField.getText().equals(currUser.getUsername())) {
                        usernameField.setText("");
                        passwordField.setText("");

                        MainScreenFrame mainScreenFrame = new MainScreenFrame(currUser);
                        window.add(mainScreenFrame);
                        loginSignupFrame.setVisible(false);
                        loginSignupFrame.revalidate();
                        loginSignupFrame.repaint();

                    } else {
                        Utilities.infoBox("Wrong Credentials", "Login Error");
                    }
                }
            }
        });

        setSize(300, 150);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
