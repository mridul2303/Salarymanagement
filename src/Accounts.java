import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Accounts extends JFrame implements ActionListener {
    //JLabel heading = new JLabel("WELCOME TO YOUR ACCOUNTS");
    JTextArea t1 = new JTextArea("fs");
    JTextArea t2 = new JTextArea("");
    JButton back = new JButton("Back");

    Accounts(){
        setTitle("WELCOME TO  YOUR ACCOUNTS");
        setSize(555,444);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        Accounts.this,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    dispose(); // Or System.exit(0)
                }
            }
        });

        //add t1
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(t1,gbc);

        setVisible(true);

    }

    public void actionPerformed (ActionEvent e){


    }}
