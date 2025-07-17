import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class AddFixedIncome extends JFrame implements ActionListener {
    JLabel heading = new JLabel("Add Fixed Income");
    JLabel source = new JLabel("Source:");
    JLabel amount = new JLabel("Amount:");
    JLabel date = new JLabel("Enter Date (yyyy-MM-dd):");

    JTextField s = new JTextField(15);
    JTextField a = new JTextField(15);
    JTextField d = new JTextField(15);

    JButton addincome = new JButton("Add Income");
    JButton back = new JButton("← Back");

    AddFixedIncome() {
        setTitle("Add Fixed Income");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(heading, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        add(source, gbc);
        gbc.gridx = 1;
        add(s, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(amount, gbc);
        gbc.gridx = 1;
        add(a, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(date, gbc);
        gbc.gridx = 1;
        add(d, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(addincome, gbc);
        gbc.gridx = 1;
        add(back, gbc);

        addincome.addActionListener(this);
        back.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setLocationRelativeTo(null); // center screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == addincome) {
            String amt = a.getText().trim();
            String src = s.getText().trim();
            String dt = d.getText().trim();

            if (amt.isEmpty() || src.isEmpty() || dt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            try (FileWriter fw = new FileWriter("income.txt", true)) {
                fw.write("Fixed," + amt + "," + src + "," + dt + "\n");
                JOptionPane.showMessageDialog(this, "Fixed income added successfully!");
                s.setText("");
                a.setText("");
                d.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving data.");
            }
        }

        if (o == back) {
            new Dashboard();
            dispose();
        }
    }
}