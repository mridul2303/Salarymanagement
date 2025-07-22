import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ViewFixedIncome extends JFrame implements ActionListener {
    JTextArea area = new JTextArea(20, 50);
    JButton load = new JButton("Load Fixed Income");
    JButton save = new JButton("Save");
    JButton edit = new JButton("Edit");
    JButton back = new JButton("← Back");

    public ViewFixedIncome() {
        setTitle("View Fixed Income");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane(area);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(load, gbc);

        gbc.gridx = 1;
        add(edit, gbc);

        gbc.gridx = 2;
        add(save, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(back, gbc);

        load.addActionListener(this);
        edit.addActionListener(this);
        save.addActionListener(this);
        back.addActionListener(this);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == load) {
            area.setText("");
            try (BufferedReader br = new BufferedReader(new FileReader("income.txt"))) {
                String line;
                Double total = 0.0;
                int count = 1;
                area.append("S.No\tAmount\tSource\tDate\n");
                area.append("---------------------------------------------\n");
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4 && parts[0].equalsIgnoreCase("Fixed")) {
                        area.append(count + "\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3] + "\n");
                        total += Double.parseDouble(parts[1]);
                        count++;
                    }
                }
                if (count == 1) {
                    area.append("No fixed income records found.\n");
                } else {
                    area.append("---------------------------------------------\n");
                    area.append("Total Fixed Income: " + total + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                area.setText("Error reading income.txt");
            }
        }

        if (o == edit) {
            area.setEditable(true);
            JOptionPane.showMessageDialog(this, "You can now edit the Fixed Income records.\nClick Save after making changes.");
        }

        if (o == save) {
            if (!area.isEditable()) {
                JOptionPane.showMessageDialog(this, "Click Edit before saving.");
                return;
            }

            try {
                String[] lines = area.getText().split("\\R");
                File original = new File("income.txt");
                File temp = new File("temp_income.txt");

                BufferedReader br = new BufferedReader(new FileReader(original));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

                // Copy non-Fixed lines
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.startsWith("Fixed")) {
                        bw.write(line);
                        bw.newLine();
                    }
                }

                // Write edited Fixed lines
                for (int i = 2; i < lines.length; i++) { // Skip headers
                    String l = lines[i].trim();
                    if (l.isEmpty() || l.startsWith("-") || l.startsWith("Total")) continue;

                    String[] parts = l.split("\t");
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

                // Replace old file
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