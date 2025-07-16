import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SalarymanagementProject {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Firstpage::new);
    }
}

class Firstpage extends JFrame implements ActionListener {

    JButton loginButton = new JButton("Login");
    JButton signupButton = new JButton("Signup");

    public Firstpage() {
        setTitle("Welcome to Salary Management System");
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // spacing

        JLabel heading = new JLabel("Welcome");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(heading, gbc);

        // Login Button
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(loginButton, gbc);

        // Signup Button
        gbc.gridx = 1;
        add(signupButton, gbc);

        // Event listeners
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);

        // Window close with confirmation
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        Firstpage.this,
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
        Object source = e.getSource();
        if (source == loginButton) {
            new Login(); // Must have Login class
            dispose();
        } else if (source == signupButton) {
            new Signup(); // Must have Signup class
            dispose();
        }
    }
}



