import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

class Accounts extends JFrame implements ActionListener {
    JTextArea t1 = new JTextArea();
    JTextArea t2 = new JTextArea();  // Will be used for expenses
    JButton back = new JButton("Back");
    JButton load = new JButton("Load");

    Accounts() {
        setTitle("WELCOME TO YOUR ACCOUNTS");
        setSize(555, 444);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Window close confirmation
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        Accounts.this,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        // 1. Load Button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(load, gbc);

        // 2. TextAreas
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(new JScrollPane(t1), gbc); // For incomes only

        gbc.gridx = 1;
        add(new JScrollPane(t2), gbc); // For expenses only (empty for now)

        // 3. Back Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(back, gbc);

        // Action Listeners
        back.addActionListener(this);
        load.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == back) {
            new Dashboard();
            dispose();
        } else if (o == load) {
            loadIncomes();
            loadExpense();

        }
    }

    void loadIncomes() {
        t1.setText("");
        double total = 0.0;

        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        t1.append("Amount\tDate\n");
        t1.append("--------------------------\n");

        try (BufferedReader br = new BufferedReader(new FileReader("income.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String type = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    LocalDate date = LocalDate.parse(parts[3].trim());
                    int incomeDay = date.getDayOfMonth();

                    if (type.equalsIgnoreCase("Fixed")) {
                        if (incomeDay <= currentDay) {
                            LocalDate adjustedDate = LocalDate.of(currentYear, currentMonth, incomeDay);
                            t1.append(amount + "\t" + adjustedDate + "\n");
                            total += amount;
                        }
                    } else if (type.equalsIgnoreCase("Temp")) {
                        if (date.getMonthValue() == currentMonth && date.getYear() == currentYear) {
                            t1.append(amount + "\t" + date + "\n");
                            total += amount;
                        }
                    }
                }
            }

            t1.append("--------------------------\n");
            t1.append("Total Income: " + total + "\n");

        } catch (Exception ex) {
            ex.printStackTrace();
            t1.setText("Error reading income.txt"+  ex.getMessage());
        }
    }

    void loadExpense(){
t2.setText("");
        double total = 0.0;

        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        t2.append("Amount\tDate\n");
        t2.append("--------------------------\n");

        try (BufferedReader br = new BufferedReader(new FileReader("expense.txt"))){
            String line ;
            while ((line = br.readLine()) != null) {
                String[] part = line.split(",");
                String type = part[0].trim();
                double amount = Double.parseDouble(part[1].trim());
                LocalDate date = LocalDate.parse(part[3].trim());
                int expenseDay = date.getDayOfMonth();
                if (type.equalsIgnoreCase("fixed")){
                    if (expenseDay <= currentDay) {
                        LocalDate adjustedDate = LocalDate.of(currentYear, currentMonth, expenseDay);
                        t2.append(amount + "\t" + adjustedDate + "\n");
                        total += amount;
                    }}
                    else if (type.equalsIgnoreCase("Temp")) {
                        if (date.getMonthValue() == currentMonth && date.getYear() == currentYear) {
                            t2.append(amount + "\t" + date + "\n");
                            total += amount;
                        }
                    }
            }
            t2.append("--------------------------\n");
            t2.append("Total expense: " + total + "\n");

        }
        catch (Exception ex) {
            ex.printStackTrace();
            t2.setText("Error reading expense.txt"+  ex.getMessage());
        }

    }
}