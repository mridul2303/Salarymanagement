import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Signup extends JFrame implements ActionListener {

    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(20);

    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(20);

    JButton setButton = new JButton("Set Username and Password");
    JButton backButton = new JButton("← Back");

    JLabel messageLabel = new JLabel("");

    public Signup() {
        setTitle("Signup Page");
        setSize(450, 300);
        setLocationRelativeTo(null); // center
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        messageLabel.setForeground(Color.RED);

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameLabel, gbc);

        // Username Field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        // Password Field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        // Message Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(messageLabel, gbc);

        // Set Button
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(setButton, gbc);

        // Back Button
        gbc.gridy = 4;
        add(backButton, gbc);

        // Listeners
        setButton.addActionListener(this);
        backButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        Signup.this,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (e.getSource() == setButton) {
            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Username or password cannot be empty.");
                return;
            }

            try {
                BufferedReader br = new BufferedReader(new FileReader("users.txt"));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(username)) {
                        messageLabel.setText("Username already exists!");
                        br.close();
                        return;
                    }
                }
                br.close();

                FileWriter fw = new FileWriter("users.txt", true);
                fw.write(username + "," + password + "\n");
                fw.close();

                JOptionPane.showMessageDialog(this, "Registered Successfully!");
                new Login(); // You must have Login class
                dispose();

            } catch (IOException ex) {
                ex.printStackTrace();
                messageLabel.setText("Error while writing data.");
            }

        } else if (e.getSource() == backButton) {
            new Firstpage(); // You must have Firstpage class
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signup::new);
    }
}