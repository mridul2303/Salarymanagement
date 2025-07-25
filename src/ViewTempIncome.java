import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ViewTempIncome extends JFrame implements ActionListener {
    JTextArea area = new JTextArea(20, 50);
    JButton load = new JButton("Load Temp Income");
    JButton back = new JButton("← Back");
    JButton edit = new JButton("Edit");
    JButton save = new JButton("Save");

    ViewTempIncome() {
        setTitle("View Temp Income");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

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

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == load) {
            area.setText("");
            area.setEditable(false);
            try (BufferedReader br = new BufferedReader(new FileReader("income.txt"))) {
                String line;
                int count = 1;
                area.append("S.No\tAmount\tSource\tDate\n");
                area.append("----------------------------------------------\n");
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");

                    if (parts.length == 4 && parts[0].trim().equalsIgnoreCase("Temp")) {
                        String amount = parts[1].trim();
                        String source = parts[2].trim();
                        String date = parts[3].trim();
                        area.append(count + "\t" + amount + "\t" + source + "\t" + date + "\n");
                        count++;
                    }
                }
                if (count == 1) {
                    area.append("No temporary income records found.\n");
                }
            } catch (Exception ex) {
                area.setText("Error reading income.txt");
                ex.printStackTrace();
            }
        }

        if (o == edit) {
            area.setEditable(true);
        }

        if (o == save) {
            if (!area.isEditable()) {
                JOptionPane.showMessageDialog(this, "Click Edit before saving.");
                return;
            }

            try {
                String[] lines = area.getText().split("\n");
                File original = new File("income.txt");
                File temp = new File("temp_income.txt");

                BufferedReader br = new BufferedReader(new FileReader(original));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

                // Copy non-temp lines from original file
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.startsWith("Temp")) {
                        bw.write(line);
                        bw.newLine();
                    }
                }

                // Now write edited Temp lines
                for (int i = 2; i < lines.length; i++) { // Skip header
                    String l = lines[i].trim();
                    if (l.isEmpty()) continue;

                    String[] parts = l.split("\t");
                    if (parts.length >= 4) {
                        String amount = parts[1].trim();
                        String source = parts[2].trim();
                        String date = parts[3].trim();
                        bw.write("Temp," + amount + "," + source + "," + date);
                        bw.newLine();
                    }
                }

                br.close();
                bw.close();

                // Replace original file with temp
                original.delete();
                temp.renameTo(original);

                JOptionPane.showMessageDialog(this, "Changes saved to income.txt");
                area.setEditable(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving income.txt: " + ex.getMessage());
            }
        }

        if (o == back) {
            new Dashboard();
            dispose();
        }
    }
}

