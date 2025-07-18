import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ViewFixedIncome extends JFrame implements ActionListener {
    JTextArea area = new JTextArea(20, 50);
    JButton load = new JButton("Load Fixed Income");
    JButton back = new JButton("← Back");

    public ViewFixedIncome() {
        setTitle("View Fixed Income");
        setSize(650, 550);
        setLocationRelativeTo(null); // center the window
        setLayout(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane(area);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // margin around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(load, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(back, gbc);

        load.addActionListener(this);
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
                Double total =0.0;
                int count = 1;
                area.append("S.No\tAmount\tSource\tDate\n");
                area.append("---------------------------------------------\n");
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4 && parts[0].equalsIgnoreCase("Fixed")) {
                        area.append(count + "\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3] + "\n");
                        total = total + Double.parseDouble(parts[1]);
                        count++;
                    }
                }
                if (count == 1) {
                    area.append("No fixed income records found.\n");
                }
                else{
                    area.append("-----------------------------------------------\n");
                    area.append("Total Fixed Income: " + total + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                area.setText("Error reading income.txt");
            }
        }

        if (o == back) {
            new Dashboard();
            dispose();
        }
    }
}