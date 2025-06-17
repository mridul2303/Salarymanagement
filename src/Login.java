import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

class Login extends Frame implements ActionListener {
    Label username = new Label("username");
    TextField t1 = new TextField(15);//for username
    Label password = new Label("password");
    TextField t2= new TextField(15);//for password
    TextField t3= new TextField(15);

    Button login = new Button("Login");

    Login(){
        add(username);add(t1);add(password);t2.setEchoChar('*');add(t2);
        add(login);add(t3);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500,400);
        login.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e){
        String username = t1.getText();
        String password = t2.getText();
        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    found = true;
                    break;
                }
            }
            br.close();


            if (found) {
                System.out.println("Login Successful!");
                new Dashboard(username); // Pass username to dashboard
                dispose();
            } else {
               t3.setText("Invalid credentials!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
