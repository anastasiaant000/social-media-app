package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Signup extends JPanel implements ActionListener {
    // Components of the Form
    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel dob;
    private JTextField dobField;
    private JLabel biographyLabel;
    private JTextArea biographyTextArea;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JCheckBox term;
    private JButton sub;
    private JScrollPane scrollPane;


    // constructor, to initialize the components
    // with default values.
    public Signup() {
//        setBounds(300, 90, 600, 600);
        setMinimumSize(new Dimension(600, 450));
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();


        nameLabel = new JLabel("Name");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(nameLabel, constraints);

        nameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(nameField, constraints);

        lastNameLabel = new JLabel("Last Name");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(lastNameLabel, constraints);

        lastNameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(lastNameField, constraints);

        emailLabel = new JLabel("Email");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(emailLabel, constraints);

        emailField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(emailField, constraints);

        dob = new JLabel("Birthday");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(dob, constraints);

        dobField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(5, 5, 5, 5);
        dobField.setToolTipText("dd/MM/yyyy");
        add(dobField, constraints);

        biographyLabel = new JLabel("Biography");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(biographyLabel, constraints);

        biographyTextArea = new JTextArea(3, 20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 5, 5, 5);
        scrollPane = new JScrollPane(biographyTextArea);
        biographyTextArea.setLineWrap(true);
        add(scrollPane, constraints);

        usernameLabel = new JLabel("Username");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(usernameField, constraints);

        passwordLabel = new JLabel("Password");
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.insets = new Insets(5, 5, 5, 5);
        passwordField.setEchoChar('*');
//        passwordField.putClientProperty("JPasswordField.cutCopyAllowed",true);
        add(passwordField, constraints);

        term = new JCheckBox("Accept Terms And Conditions.");
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(term, constraints);

        sub = new JButton("Submit");
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.insets = new Insets(5, 5, 5, 5);
        sub.addActionListener(this);
        add(sub, constraints);

        setVisible(true);
        sub.addActionListener(e -> {
            if (usernameField.getText().isEmpty()
                    || nameField.getText().isEmpty()
                    || lastNameField.getText().isEmpty()
                    || emailField.getText().isEmpty()
                    || dobField.getText().isEmpty()
                    || biographyTextArea.getText().isEmpty()
                    || passwordField.getPassword().length == 0) {
                Utilities.infoBox("All fields are mandatory", "Missing Fields");
            } else {
                //TODO add checks if username exists, mail validity,date validity etc
                try {
                    Profile newUser = new Profile(usernameField.getText(),
                            nameField.getText(),
                            lastNameField.getText(),
                            new SimpleDateFormat("dd/MM/yyyy").parse(dobField.getText()),
                            new String(passwordField.getPassword()),
                            biographyTextArea.getText(),
                            emailField.getText());
                    Profile.createUser(newUser);
                    usernameField.setText("");
                    nameField.setText("");
                    lastNameField.setText("");
                    emailField.setText("");
                    dobField.setText("");
                    biographyTextArea.setText("");
                    passwordField.setText("");
                    Utilities.infoBox("Registration Successful, please proceed to Login page", "Registration Completed");

                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
