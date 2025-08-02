import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class AddFixedExp extends JFrame implements ActionListener {
    JLabel heading = new JLabel("Add Fixed Expense", JLabel.CENTER);
    JLabel amount = new JLabel("Amount:");
    JLabel date = new JLabel("Date (YYYY-MM-DD):");
    JLabel expenseHead = new JLabel("Expense Type:");

    JTextField a = new JTextField(15);
    JTextField d = new JTextField(15);
    JTextField et = new JTextField(15);

    JButton add = new JButton("Add Expense");
    JButton back = new JButton("← Back");

    AddFixedExp() {
        setTitle("Add Fixed Expense");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Add components
        add(heading); add(new JLabel());  // Heading with spacing
        add(expenseHead); add(et);
        add(amount); add(a);
        add(date); add(d);
        add(add); add(back);

        add.addActionListener(this);
        back.addActionListener(this);

        // Handle window close
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setLocationRelativeTo(null); // center screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == back) {
            new Dashboard();
            dispose();
        }
        if (o == add) {
            String amt = a.getText().trim();
            String type = et.getText().trim();
            String dt = d.getText().trim();

            if (amt.isEmpty() || type.isEmpty() || dt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            try (FileWriter fw = new FileWriter("expense.txt", true)) {
                fw.write("Fixed," + amt + "," + type + "," + dt + "\n");
                JOptionPane.showMessageDialog(this, "Fixed Expense added successfully!");
                et.setText("");
                a.setText("");
                d.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving data.");
            }
        }
    }
}