import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Dashboard extends Frame implements ActionListener {
    Button addincome = new Button("Add Income");
    Button viewincome = new Button("view Income");
    Button addexpense = new Button("Add Expense");
    Button viewexpense = new Button("view Expense");

    PopupMenu popupMenu1 = new PopupMenu();//add income
    MenuItem fixedincome1 = new MenuItem("add Fix Income");
    MenuItem tempincome1 = new MenuItem("add Temprory Income");

    PopupMenu popupMenu2 = new PopupMenu();//view income
    MenuItem fixedincome2 = new MenuItem("view Fix Income");
    MenuItem tempincome2 = new MenuItem("view Temprory Income");

    PopupMenu popupMenu3 = new PopupMenu();//add exp
    MenuItem tempExp3 = new MenuItem("add Temprory Expense");
    MenuItem fixemp3 = new MenuItem("add Fix Expense");

    PopupMenu popupMenu4 = new PopupMenu();//view exp
    MenuItem tempExp4 = new MenuItem("View Temprory Expense");
    MenuItem fixedexp4 = new MenuItem("View Fix Expense");




    Dashboard(){
        setVisible(true);
        setSize(400,500);
        setLayout(new FlowLayout());

        //add income
        add(addincome); add(popupMenu1); popupMenu1.add(fixedincome1);popupMenu1.add(tempincome1);
        //view income
        add(viewincome);add(popupMenu2);popupMenu2.add(fixedincome2);popupMenu2.add(tempincome2);
        //add exmp
        add(addexpense);add(popupMenu3);popupMenu3.add(fixemp3);popupMenu3.add(tempExp3);
        //view exp
        add(viewexpense);add(popupMenu4);popupMenu4.add(fixedexp4);popupMenu4.add(tempExp4);

        addincome.addActionListener(this);viewincome.addActionListener(this);
        addexpense.addActionListener(this);viewexpense.addActionListener(this);

    }
    public void actionPerformed (ActionEvent e){


    }
}
