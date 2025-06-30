import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class ViewFixedIncome extends Frame implements ActionListener {
    TextArea area = new TextArea(20, 50);
    Button load = new Button("Load Fixed Income");
    Button back = new Button("← Back");

    ViewFixedIncome() {
        setTitle("View Fixed Income");
        setSize(600, 500);
        setLayout(new FlowLayout());

        add(load);
        add(area);
        add(back);

        load.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == load) {
            area.setText(""); // clear previous content
            try (BufferedReader br = new BufferedReader(new FileReader("income.txt"))) {
                String line;
                int count = 1;
                area.append("S.No\tAmount\tSource\tDate\n");
                area.append("----------------------------------------\n");
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4 && parts[0].equalsIgnoreCase("Fixed")) {
                        area.append(count + "\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3] + "\n");
                        count++;
                    }
                }
                if (count == 1) {
                    area.append("No fixed income records found.\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                area.setText("Error reading income.txt");
            }
        }
        if (o==back){
            new Dashboard();
            dispose();
        }
    }
}