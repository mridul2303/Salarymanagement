import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Login extends Frame implements ActionListener {
    Label username = new Label("username");
    TextField t1 = new TextField(15);//for username
    Label password = new Label("password");
    TextField t2= new TextField(15);//for password
    TextField t3= new TextField(15);

    Button login = new Button("Login");
    String saveduser, savedpass;

    Login(String user, String pass){
        saveduser = user;
        savedpass = pass;
        add(username);add(t1);add(password);t2.setEchoChar('*');add(t2);
        add(login);add(t3);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500,400);
        login.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e){
if (t1.getText().equals(saveduser) && t2.getText().equals(savedpass)){

}
else {
    t3.setText("❌ Invalid username or password.");
}
    }
}
