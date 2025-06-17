import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.cert.Extension;

public class SalaryManagement {
    public static void main(String[] args) {
        new Firstpage();
    }
}
class Firstpage extends Frame implements ActionListener
{
    Button Signup = new Button("Singup");
    Button login = new Button("Login");
    Firstpage(){
        add(login);add(Signup);
        login.addActionListener(this);
        Signup.addActionListener(this);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500,400);

    }
    public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
        if (o==login){
            new Login();
            dispose();
        }

    }
}



