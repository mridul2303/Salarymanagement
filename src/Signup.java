import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

class Signup extends Frame implements ActionListener {

    Label username = new Label("username");
    TextField t1 = new TextField(15);
    Label password = new Label("password");
    TextField t2 = new TextField(15);
    Button set = new Button("Set username and password");
    Button back = new Button("← Back");

    Signup() {
        add(username); add(t1);
        add(password); add(t2);
        add(set);
        add(back);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500, 400);

        set.addActionListener(this);
        back.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        String username = t1.getText().trim();
        String password = t2.getText().trim();

        if (o == set) {
            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Username or password cannot be empty.");
                return;
            }

            try {
                // Check if username already exists
                BufferedReader br = new BufferedReader(new FileReader("users.txt"));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(username)) {
                        System.out.println("Username already exists. Choose another.");
                        br.close();
                        return;
                    }
                }
                br.close();

                // If not exists, write to file
                FileWriter fw = new FileWriter("users.txt", true);
                fw.write(username + "," + password + "\n");
                fw.close();

                System.out.println("Registered Successfully!");
                new Login();
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (o==back) {
new Firstpage();
dispose();
        }
    }
}