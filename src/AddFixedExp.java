import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class AddFixedExp extends Frame implements ActionListener {
    Label amount = new Label("Amount");
    Label date = new Label("Date");
    Label expenseHead = new Label("Expense Type");

    TextField a = new TextField(15);
    TextField d = new TextField(15);
    TextField et = new TextField(15);

    Button add = new Button("Add Expense");
    Button back = new Button("← Back");

    AddFixedExp(){
        setSize(444,555);
        setLayout(new FlowLayout());
        setVisible(true);
        
        add(expenseHead);add(et);add(amount);add(a);add(date);add(d);
        add(add);add(back);
        add.addActionListener(this);
        back.addActionListener(this);

    }
    public void actionPerformed (ActionEvent e){
        Object o = e.getSource();
        if (o==back){
            new Dashboard();
            dispose();
        }
        if (o==add){
            try (FileWriter fw = new FileWriter("expense.txt", true)) {
                fw.write("Fixed," + a.getText() + "," + et.getText() + "," + d.getText() + "\n");
                JOptionPane.showMessageDialog(null, "Fixed Expense added successfully!");
                et.setText("");
                a.setText("");
                d.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving data.");
            }
        }
    }
}
