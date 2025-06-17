import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        String user = t1.getText();
        String pass = t2.getText();
        new Login(user,pass);
        dispose();
    }
}
