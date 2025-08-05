import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Accounts extends JFrame implements ActionListener {
    double inctotal = 0.0;
    double extotal = 0.0;
    double availbalance = 0.0;
    JTextArea t1 = new JTextArea();
    JTextArea t2 = new JTextArea();  // Will be used for expenses
    JButton back = new JButton("Back");
    JButton load = new JButton("Load");
    JLabel ab = new JLabel("Available Balance");
    JTextField balance = new JTextField(15);

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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(load, gbc);

// 2. TextAreas - bigger space
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        add(new JScrollPane(t1), gbc); // Incomes

        gbc.gridx = 1;
        add(new JScrollPane(t2), gbc); // Expenses

// 3. Available Balance label and field at bottom
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(ab, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(balance, gbc);

// 4. Back Button at bottom center
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(back, gbc);

        t1.setEditable(false);
        t2.setEditable(false);
        balance.setEditable(false);

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
            loadAvailBal();

        }


    }

    void loadIncomes() {
        t1.setText("");

        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        t1.append("Amount\tDate\n");
        t1.append("----------------------------------------------\n");

        try (BufferedReader br = new BufferedReader(new FileReader("income.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    double amount = Double.parseDouble(parts[1]);
                    String type = parts[2];
                    t1.append(amount + "\t " + type + "\n");
                    inctotal += amount;
                }
            }

            t1.append("--------------------------------------------\n");
            t1.append("Total Income: " + inctotal + "\n");

        } catch (Exception ex) {
            ex.printStackTrace();
            t1.setText("Error reading income.txt"+  ex.getMessage());
        }
    }

    public void loadExpense() {
        t2.setText(""); // Clear text area
        extotal = 0.0;

        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        t2.append("Amount\tDate\n");
        t2.append("------------------------------------\n");


        try ( BufferedReader br = new BufferedReader(new FileReader("expense.txt"))){

String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    double amount = Double.parseDouble(parts[1]);
                    String type = parts[2];
                    extotal += amount;
                    t2.append(amount + "\t" + type + "\n");
                }

            }
            t2.append("------------------------------------\n");
            t2.append("Total Expense: " + extotal + "\n");

        }
        catch (Exception ex) {
            ex.printStackTrace();
            t2.setText("Error reading expense.txt"+  ex.getMessage());
        }
    }
    void loadAvailBal(){
        availbalance = inctotal - extotal;
        balance.setText(String.valueOf(availbalance));
    }}