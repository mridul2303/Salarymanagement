import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class AddTempIncome extends Frame implements ActionListener {
    Label source = new Label("Source");
    Label amount = new Label("Amount");
    Label date = new Label("Enter Date (yyyy-MM-dd):");

    TextField s = new TextField(15);
    TextField a = new TextField(15);
    TextField d = new TextField(15);

    Button addincome = new Button("Add Income");
    Button back = new Button("← Back");

    AddTempIncome() {
        setSize(500, 400);
        setVisible(true);
        setLayout(new FlowLayout());

        add(source);
        add(s);
        add(amount);
        add(a);
        add(date);
        add(d);
        add(addincome);
        add(back);

        addincome.addActionListener(this);
        back.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == addincome) {
            try (FileWriter fw = new FileWriter("income.txt", true)) {
                fw.write("Temp," + a.getText() + "," + s.getText() + "," + d.getText() + "\n");
                JOptionPane.showMessageDialog(null, "Temprory income added successfully!");
                s.setText("");
                a.setText("");
                d.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving data.");
            }
        }
        if (o==back){
            new Dashboard();
            dispose();
        }
    }
}
