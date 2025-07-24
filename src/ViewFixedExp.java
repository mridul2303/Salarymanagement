import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ViewFixedExp extends JFrame implements ActionListener {
    JTextArea area = new JTextArea(20, 50);
    JButton load = new JButton("Load Fixed Expense");
    JButton back = new JButton("← Back");
    JButton edit = new JButton("edit");
    JButton save = new JButton("save");

    public ViewFixedExp() {
        setTitle("View FIxed Expenses");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null); // center the frame

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(area);

        gbc.insets = new Insets(10, 10, 10, 10);
        // Load button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(load, gbc);

        // Text area
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(scroll, gbc);

        // Back button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(back, gbc);

        // Edit button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(edit, gbc);

        // Save button
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(save, gbc);

        load.addActionListener(this);
        back.addActionListener(this);
        edit.addActionListener(this);
        save.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // or System.exit(0);
            }
        });

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
        if (o == edit) {
            area.setEditable(true);
        }

        if (o == save) {
            if (!area.isEditable()) {
                JOptionPane.showMessageDialog(this, "Edit the data first before saving.");
            } else {
                try {
                    File original = new File("expense.txt");
                    File tempFile = new File("temp.txt");

                    BufferedReader br = new BufferedReader(new FileReader(original));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

                    String line;

                    // Keep existing "Fixed" entries
                    while ((line = br.readLine()) != null) {
                        if (!line.startsWith("Fixed")) {
                            bw.write(line);
                            bw.newLine();
                        }
                    }

                    // Append edited Temp Income entries from JTextArea
                    String[] lines = area.getText().split("\n");
                    for (int i = 2; i < lines.length; i++) { // assuming 0 & 1 are headers
                        String[] parts = lines[i].split("\t");
                        if (parts.length >= 4) {
                            String amount = parts[1].trim();
                            String source = parts[2].trim();
                            String date = parts[3].trim();
                            bw.write("Fixed," + amount + "," + source + "," + date);
                            bw.newLine();
                        }
                    }

                    br.close();
                    bw.close();

                    // Replace original file with temp
                    original.delete();
                    tempFile.renameTo(original);

                    area.setEditable(false);
                    JOptionPane.showMessageDialog(this, "Changes saved successfully.");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error saving changes: " + ex.getMessage());
                }
            }
        }

    }        }
