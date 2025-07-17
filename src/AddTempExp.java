import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class AddTempExp extends JFrame implements ActionListener {

    JLabel expenseTypeLabel = new JLabel("Expense Type:");
    JLabel amountLabel = new JLabel("Amount:");
    JLabel dateLabel = new JLabel("Enter Date (yyyy-MM-dd):");

    JTextField expenseTypeField = new JTextField(15);
    JTextField amountField = new JTextField(15);
    JTextField dateField = new JTextField(15);

    JButton addButton = new JButton("Add Expense");
    JButton backButton = new JButton("← Back");

    public AddTempExp() {
        setTitle("Add Temporary Expense");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0 - Expense Type
        gbc.gridx = 0; gbc.gridy = 0;
        add(expenseTypeLabel, gbc);
        gbc.gridx = 1;
        add(expenseTypeField, gbc);

        // Row 1 - Amount
        gbc.gridx = 0; gbc.gridy = 1;
        add(amountLabel, gbc);
        gbc.gridx = 1;
        add(amountField, gbc);

        // Row 2 - Date
        gbc.gridx = 0; gbc.gridy = 2;
        add(dateLabel, gbc);
        gbc.gridx = 1;
        add(dateField, gbc);

        // Row 3 - Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        add(backButton, gbc);
        gbc.gridx = 1;
        add(addButton, gbc);

        addButton.addActionListener(this);
        backButton.addActionListener(this);

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
        Object source = e.getSource();

        if (source == backButton) {
            new Dashboard();
            dispose();
        }

        if (source == addButton) {
            try (FileWriter fw = new FileWriter("expense.txt", true)) {
                fw.write("Temp," + amountField.getText() + "," +
                        expenseTypeField.getText() + "," +
                        dateField.getText() + "\n");

                JOptionPane.showMessageDialog(null, "Temporary Expense added successfully!");

                // Clear fields
                expenseTypeField.setText("");
                amountField.setText("");
                dateField.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving data.");
            }
        }
    }

    public static void main(String[] args) {
        new AddTempExp();
    }
}