import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class AddTempIncome extends JFrame implements ActionListener {
    JLabel source = new JLabel("Source");
    JLabel amount = new JLabel("Amount");
    JLabel date = new JLabel("Enter Date (yyyy-MM-dd):");

    JTextField s = new JTextField(15);
    JTextField a = new JTextField(15);
    JTextField d = new JTextField(15);

    JButton addincome = new JButton("Add Income");
    JButton back = new JButton("← Back");

    AddTempIncome() {
        setTitle("Add Temporary Income");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing

        gbc.gridx = 0; gbc.gridy = 0;
        add(source, gbc);
        gbc.gridx = 1;
        add(s, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(amount, gbc);
        gbc.gridx = 1;
        add(a, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(date, gbc);
        gbc.gridx = 1;
        add(d, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(addincome, gbc);
        gbc.gridx = 1;
        add(back, gbc);

        addincome.addActionListener(this);
        back.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == addincome) {
            try (FileWriter fw = new FileWriter("income.txt", true)) {
                fw.write("Temp," + a.getText() + "," + s.getText() + "," + d.getText() + "\n");
                JOptionPane.showMessageDialog(this, "Temporary income added successfully!");
                s.setText("");
                a.setText("");
                d.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving data.");
            }
        } else if (o == back) {
            new Dashboard();
            dispose();
        }
    }
}