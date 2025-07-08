import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class ViewTempExp extends Frame implements ActionListener {
    TextArea area = new TextArea(20, 50);
    Button load = new Button("Load Temprory Expense");
    Button back = new Button("← Back");

    ViewTempExp() {
        setTitle("View Temprory Expense");
        setSize(600, 500);
        setLayout(new FlowLayout());

        add(load);
        add(area);
        add(back);

        load.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // or System.exit(0);
            }
        });
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
                    area.append("No temprory expense records found.\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                area.setText("Error reading expense.txt");
            }
        }
        if (o==back){
            new Dashboard();
            dispose();
        }
    }
}
