import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;

class Form extends Frame implements ActionListener {

    Label username = new Label("username");
    TextField t1 = new TextField(15);
    Label password = new Label("password");
    TextField t2= new TextField(15);
    Button set = new Button("Set username and password");
    Form()
    {
        add(username);add(t1);add(password);add(t2);
        add(set);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500,400);
        set.addActionListener(this);

        //to close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    public void actionPerformed(ActionEvent e){
        String username = t1.getText();
        String password = t2.getText();

        try {
            FileWriter fw = new FileWriter("users.txt", true);
            fw.write(username + "," + password + "\n");
            fw.close();

            System.out.println("Registered Successfully!");
            new Login();
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
