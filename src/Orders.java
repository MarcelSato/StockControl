
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Orders extends JFrame implements ActionListener {

    private JTextArea information = new JTextArea(40, 60);
    private JButton orders = new JButton("Orders");
    private JButton payment = new JButton("Payment Details");
    private JButton exit = new JButton("Exit");
    private JButton exitToMaster = new JButton("Exit");
    private JButton back = new JButton("Back");
    private BasketData basketData;
    private BufferedReader inFile;
    private JScrollPane scroll = new JScrollPane(information);
    private String customerFile;
    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private JPanel middle = new JPanel();
    private JPanel bottom = new JPanel();

    public Orders(final BasketData basketData) {
        this.basketData = basketData;
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/others/b.png"));
        Border newBorder = BorderFactory.createRaisedBevelBorder();

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setLayout(new BorderLayout());
        setSize(720, 720);
        setLocationRelativeTo(null);
        setTitle("Order Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        orders.setBorder(newBorder);
        orders.setBackground(Color.DARK_GRAY);
        orders.setForeground(Color.white);
        orders.setMnemonic(KeyEvent.VK_O);
        payment.setBorder(newBorder);
        payment.setBackground(Color.DARK_GRAY);
        payment.setForeground(Color.white);
        payment.setMnemonic(KeyEvent.VK_P);
        exit.setBorder(newBorder);
        exit.setBackground(Color.DARK_GRAY);
        exit.setForeground(Color.white);
        exit.setMnemonic(KeyEvent.VK_E);
        exitToMaster.setBorder(newBorder);
        exitToMaster.setBackground(Color.DARK_GRAY);
        exitToMaster.setForeground(Color.white);
        exitToMaster.setMnemonic(KeyEvent.VK_E);
        back.setBorder(newBorder);
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.white);
        back.setMnemonic(KeyEvent.VK_K);

    }

    public void sendFile(String a) {
        customerFile = a;
    }

    public void showEmployee() {

        information.setText("----------------------------------------------------------"
                + "               Select a file to open               "
                + "----------------------------------------------------------");
        information.setEditable(false);
        middle.add(scroll);
        bottom.add(orders);
        orders.addActionListener(this);
        bottom.add(payment);
        payment.addActionListener(this);
        bottom.add(back);
        back.addActionListener(this);
        bottom.add(exit);
        exit.addActionListener(this);
        add("Center", middle);
        add("West", left);
        add("East", right);
        add("South", bottom);
        left.setBackground(Color.DARK_GRAY);
        right.setBackground(Color.DARK_GRAY);
        middle.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);
        scroll.setViewportView(information);
    }

    public void showCustomer() {

        information.setText("Select a file to open");
        middle.add(scroll);
        bottom.add(exitToMaster);
        exitToMaster.addActionListener(this);
        add("Center", middle);
        add("West", left);
        add("East", right);
        add("South", bottom);
        left.setBackground(Color.DARK_GRAY);
        right.setBackground(Color.DARK_GRAY);
        middle.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);
        scroll.setViewportView(information);
        try {
            inFile = new BufferedReader(new FileReader(customerFile));
            information.setText("");
            String line;
            while ((line = inFile.readLine()) != null) {
                information.append(line + "\n");
            }
            inFile.close();
        } catch (IOException a) {
            JOptionPane.showMessageDialog(null, "File not found");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orders) {
            try {
                JFileChooser chooser = new JFileChooser("res/invoices/");
                chooser.showOpenDialog(this);
//                if(JFileChooser.CANCEL_OPTION){ // try chooser options
//                    
//                }
                File file = chooser.getSelectedFile();
                try {
                    inFile = new BufferedReader(new FileReader(file));
                    information.setText("");
                    String line;
                    while ((line = inFile.readLine()) != null) {
                        information.append(line + "\n");
                    }
                    inFile.close();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(null, "File not found");
                }
                information.setCaretPosition(0);
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "No files were chosen.");
            }
        } else if (e.getSource() == payment) {
            try {
                JFileChooser chooser = new JFileChooser("res/payment_details/");
                chooser.showOpenDialog(this);
                File file = chooser.getSelectedFile();
                try {
                    inFile = new BufferedReader(new FileReader(file));
                    information.setText("");
                    String line;
                    while ((line = inFile.readLine()) != null) {
                        information.append(line + "\n");
                    }
                    inFile.close();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(null, "File not found");
                }
                information.setCaretPosition(0);
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "No files were chosen.");
            }
        } else if (e.getSource() == back) {
            super.dispose();
            new UpdateStock();
        } else if (e.getSource() == exitToMaster) {
            int decision = JOptionPane.showConfirmDialog(null, "Are you done?", "Exit", 0);
            if (decision == 1) {
                super.dispose();
                new Master();
            } else {
                super.dispose();
            }
        } else if (e.getSource() == exit) {
            super.dispose();
        }
    }
}
