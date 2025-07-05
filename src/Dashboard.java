import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Dashboard extends Frame implements ActionListener, MouseListener {

    Label addincome = new Label("Add Income");
    Label viewincome = new Label("view Income");
    Label addexpense = new Label("Add Expense");
    Label viewexpense = new Label("view Expense");

    PopupMenu popupMenu1 = new PopupMenu();//add income
    MenuItem fixedincome1 = new MenuItem("add Fix Income");
    MenuItem tempincome1 = new MenuItem("add Temprory Income");

    PopupMenu popupMenu2 = new PopupMenu();//view income
    MenuItem fixedincome2 = new MenuItem("view Fix Income");
    MenuItem tempincome2 = new MenuItem("view Temprory Income");

    PopupMenu popupMenu3 = new PopupMenu();//add exp
    MenuItem tempExp3 = new MenuItem("add Temprory Expense");
    MenuItem fixedexp3 = new MenuItem("add Fix Expense");

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
        //add exp
        add(addexpense);add(popupMenu3);popupMenu3.add(fixedexp3);popupMenu3.add(tempExp3);
        //view exp
        add(viewexpense);add(popupMenu4);popupMenu4.add(fixedexp4);popupMenu4.add(tempExp4);

        addincome.addMouseListener(this);
        viewincome.addMouseListener(this);
        addexpense.addMouseListener(this);
        viewexpense.addMouseListener(this);

fixedincome1.addActionListener(this);tempincome1.addActionListener(this);
fixedincome2.addActionListener(this);tempincome2.addActionListener(this);
fixedexp3.addActionListener(this);tempExp3.addActionListener(this);
fixedexp4.addActionListener(this);tempExp4.addActionListener(this);
    }

    public void mouseClicked (MouseEvent e){
        Object o = e.getSource();
        if (o==addincome){
            popupMenu1.show(addincome,0,addincome.getHeight());
        }
        if (o==viewincome){
            popupMenu2.show(viewincome,0,viewincome.getHeight());
        }
        if (o==addexpense){
            popupMenu3.show(addexpense,0,addexpense.getHeight());
        }
        if (o==viewexpense){
            popupMenu4.show(viewexpense,0,viewexpense.getHeight());
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    //task if clicked
    public void actionPerformed (ActionEvent e){
        Object o = e.getSource();
        if (o==fixedincome1){
            new AddFixedIncome();
            dispose();
        }
        if (o==tempincome1){
            new AddTempIncome();
            dispose();
        }
        // to view income
        if (o==fixedincome2){
            new ViewFixedIncome();
        }
        if (o==tempincome2){
            new ViewTempIncome();
        }
        //to add exp
        if (o==tempExp3){

        }
        if (o==fixedexp3){
            new AddFixedExp();
        }
        //to view exp
        if (o==tempExp4){
            System.out.println("hello");
        }
        if (o==fixedexp4){
            new ViewFixedExp();
        }
    }
}
