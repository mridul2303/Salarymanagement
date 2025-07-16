import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Login extends JFrame implements ActionListener {

    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(20);

    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(20);

    JCheckBox showPassword = new JCheckBox("Show Password");

    JLabel messageLabel = new JLabel("");
    JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
    JButton loginButton = new JButton("Login");
    JButton backButton = new JButton("← Back");
    JButton signupButton = new JButton("Sign Up");

    public Login() {
        setTitle("Login Page");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // center

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        forgotPasswordLabel.setForeground(Color.BLUE.darker());
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Username Label
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        add(usernameLabel, gbc);

        // Username Field
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        // Password Label
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        // Password Field
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        // Show Password Checkbox
        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        add(showPassword, gbc);

        // Message Label
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        messageLabel.setForeground(Color.RED);
        add(messageLabel, gbc);

        // Forgot Password
        gbc.gridy = 4;
        add(forgotPasswordLabel, gbc);

        // Login and Back Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        gbc.gridy = 5;
        add(buttonPanel, gbc);

        // Signup Button
        gbc.gridy = 6;
        add(signupButton, gbc);

        // Listeners
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });

        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                messageLabel.setText("Contact admin to reset password.");
            }
        });

        loginButton.addActionListener(this);
        backButton.addActionListener(this);
        signupButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (e.getSource() == loginButton) {
            boolean found = false;

            try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                        found = true;
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                messageLabel.setText("Error reading user data.");
                return;
            }

            if (found) {
                messageLabel.setForeground(new Color(0, 128, 0));
                messageLabel.setText("Login Successful!");
                new Dashboard(); // You must define Dashboard
                dispose();
            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Invalid credentials!");
            }

        } else if (e.getSource() == backButton) {
            new Firstpage(); // You must define Firstpage
            dispose();
        } else if (e.getSource() == signupButton) {
            new Signup(); // You must define Signup
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}