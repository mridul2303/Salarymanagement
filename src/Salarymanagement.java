import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Salarymanagement {
    public static void main(String[] args) {
        new Firstpage();
    }
}
class Firstpage extends Frame implements ActionListener
{
    Button signup = new Button("Singup");
    Button login = new Button("Login");
    Firstpage(){
        add(login);add(signup);
        login.addActionListener(this);
        signup.addActionListener(this);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500,400);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

    }
    public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
        if (o==login){
            new Login();
            dispose();
        }
        if (o==signup){
            new Signup();
            dispose();
        }

    }
}



