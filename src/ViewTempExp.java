import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ViewTempExp extends JFrame implements ActionListener {
    JTextArea area = new JTextArea(20, 50);
    JButton load = new JButton("Load Temporary Expense");
    JButton back = new JButton("← Back");

    public ViewTempExp() {
        setTitle("View Temporary Expenses");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null); // center the frame

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(area);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(load, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;
        panel.add(back, gbc);

        load.addActionListener(this);
        back.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // or System.exit(0);
            }
        });

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == load) {
            area.setText(""); // clear previous content
            try (BufferedReader br = new BufferedReader(new FileReader("expense.txt"))) {
                String line;
                int count = 1;
                area.append("S.No\tAmount\tExpenseHead\tDate\n");
                area.append("----------------------------------------\n");
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4 && parts[0].equalsIgnoreCase("Temp")) {
                        area.append(count + "\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3] + "\n");
                        count++;
                    }
                }
                if (count == 1) {
                    area.append("No temporary expense records found.\n");
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

    public static void main(String[] args) {
        new ViewTempExp();
    }
}
