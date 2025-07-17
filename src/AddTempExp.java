import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class AddTempExp extends Frame implements ActionListener {

    // UI Components
    Label amountLabel = new Label("Amount");
    Label dateLabel = new Label("Date");
    Label expenseTypeLabel = new Label("Expense Type");

    TextField amountField = new TextField(15);
    TextField dateField = new TextField(15);
    TextField expenseTypeField = new TextField(15);

    Button addButton = new Button("Add Expense");
    Button backButton = new Button("← Back");

    // Constructor
    public AddTempExp() {
        setTitle("Add Temporary Expense");
        setSize(444, 555);
        setLayout(new FlowLayout());
        setVisible(true);

        // Adding components
        add(expenseTypeLabel);
        add(expenseTypeField);
        add(amountLabel);
        add(amountField);
        add(dateLabel);
        add(dateField);
        add(addButton);
        add(backButton);

        // Event Listeners
        addButton.addActionListener(this);
        backButton.addActionListener(this);

        // Handle window close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Closes only this window
            }
        });
    }

    // Action Handling
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == backButton) {
            new Dashboard();  // Go back to dashboard
            dispose();        // Close current window
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
}