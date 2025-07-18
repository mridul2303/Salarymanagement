import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ViewFixedExp extends JFrame implements ActionListener {
    JTextArea area = new JTextArea(20, 50);
    JButton load = new JButton("Load Fixed Expense");
    JButton back = new JButton("← Back");

    public ViewFixedExp() {
        setTitle("View Fixed Expense");
        setSize(650, 500);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing

        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        // Load Button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(load, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(back, gbc);

        // TextArea inside ScrollPane
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, gbc);


        load.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);

        // Confirm before close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        ViewFixedExp.this,
                        "Are you sure you want to close?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == load) {
            area.setText(""); // clear previous content
            try (BufferedReader br = new BufferedReader(new FileReader("expense.txt"))) {
                String line;
                Double total = 0.0;
                int count = 1;
                area.append("S.No\tAmount\tExpenseHead\tDate\n");
                area.append("--------------------------------------------------------\n");
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4 && parts[0].equalsIgnoreCase("Fixed")) {
                        area.append(count + "\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3] + "\n");
                        total += Double.parseDouble(parts[1]);
                    count++;
                    }
                }
                if (count == 1) {
                    area.append("No fixed expense records found.\n");
                }
                else {
                    area.append("-----------------------------------------------\n");
                    area.append("Total Fixed Expense:: "+ total+"\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                area.setText("Error reading expense.txt");
            }
        }
        if (o == back) {
            new Dashboard();
            dispose();
        }
    }
}