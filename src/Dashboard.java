import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener, MouseListener {

    JLabel addIncome = new JLabel("➕ Add Income");
    JLabel viewIncome = new JLabel("📄 View Income");
    JLabel addExpense = new JLabel("➖ Add Expense");
    JLabel viewExpense = new JLabel("📊 View Expense");

    // Popup menus
    JPopupMenu popupIncomeAdd = new JPopupMenu();
    JMenuItem fixedIncomeAdd = new JMenuItem("Add Fixed Income");
    JMenuItem tempIncomeAdd = new JMenuItem("Add Temporary Income");

    JPopupMenu popupIncomeView = new JPopupMenu();
    JMenuItem fixedIncomeView = new JMenuItem("View Fixed Income");
    JMenuItem tempIncomeView = new JMenuItem("View Temporary Income");

    JPopupMenu popupExpenseAdd = new JPopupMenu();
    JMenuItem fixedExpenseAdd = new JMenuItem("Add Fixed Expense");
    JMenuItem tempExpenseAdd = new JMenuItem("Add Temporary Expense");

    JPopupMenu popupExpenseView = new JPopupMenu();
    JMenuItem fixedExpenseView = new JMenuItem("View Fixed Expense");
    JMenuItem tempExpenseView = new JMenuItem("View Temporary Expense");

    public Dashboard() {
        setTitle("Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null); // Center screen
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Custom close handler
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        Dashboard.this,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    dispose(); // Or System.exit(0)
                }
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addIncome, gbc);

        gbc.gridy = 1;
        add(viewIncome, gbc);

        gbc.gridy = 2;
        add(addExpense, gbc);

        gbc.gridy = 3;
        add(viewExpense, gbc);

        // Add MouseListener
        addIncome.addMouseListener(this);
        viewIncome.addMouseListener(this);
        addExpense.addMouseListener(this);
        viewExpense.addMouseListener(this);

        // Attach menu items
        popupIncomeAdd.add(fixedIncomeAdd);
        popupIncomeAdd.add(tempIncomeAdd);

        popupIncomeView.add(fixedIncomeView);
        popupIncomeView.add(tempIncomeView);

        popupExpenseAdd.add(fixedExpenseAdd);
        popupExpenseAdd.add(tempExpenseAdd);

        popupExpenseView.add(fixedExpenseView);
        popupExpenseView.add(tempExpenseView);

        // Add ActionListeners
        fixedIncomeAdd.addActionListener(this);
        tempIncomeAdd.addActionListener(this);
        fixedIncomeView.addActionListener(this);
        tempIncomeView.addActionListener(this);
        fixedExpenseAdd.addActionListener(this);
        tempExpenseAdd.addActionListener(this);
        fixedExpenseView.addActionListener(this);
        tempExpenseView.addActionListener(this);

        setVisible(true);
    }

    // Show popup menus when labels are clicked
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == addIncome) {
            popupIncomeAdd.show(addIncome, 0, addIncome.getHeight());
        } else if (source == viewIncome) {
            popupIncomeView.show(viewIncome, 0, viewIncome.getHeight());
        } else if (source == addExpense) {
            popupExpenseAdd.show(addExpense, 0, addExpense.getHeight());
        } else if (source == viewExpense) {
            popupExpenseView.show(viewExpense, 0, viewExpense.getHeight());
        }
    }

    // Required MouseListener methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        ((JLabel)e.getSource()).setForeground(Color.BLUE);
    }
    public void mouseExited(MouseEvent e) {
        ((JLabel)e.getSource()).setForeground(Color.BLACK);
    }

    // Action handlers
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == fixedIncomeAdd) {
            new AddFixedIncome();
            dispose();
        } else if (o == tempIncomeAdd) {
            new AddTempIncome();
            dispose();
        } else if (o == fixedIncomeView) {
            new ViewFixedIncome();
        } else if (o == tempIncomeView) {
            new ViewTempIncome();
        } else if (o == fixedExpenseAdd) {
            new AddFixedExp();
        } else if (o == tempExpenseAdd) {
            new AddTempExp();
        } else if (o == fixedExpenseView) {
            new ViewFixedExp();
        } else if (o == tempExpenseView) {
            new ViewTempExp();
        }
    }
}