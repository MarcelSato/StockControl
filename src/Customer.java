
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Customer extends JFrame implements ActionListener {

    private JTextField title = new JTextField(5);
    private JTextField name = new JTextField(20);
    private JTextField surname = new JTextField(20);
    private JTextField address = new JTextField(20);
    private JTextField address1 = new JTextField(20);
    private JTextField email = new JTextField(20);
    private JTextField cardNumber = new JTextField(20);
    private JTextField cardCvv = new JTextField(3);
    private JTextField dateTime = new JTextField(20);
    private JPanel leftGrid = new JPanel(new GridLayout(5, 2));
    private JPanel leftGrid1 = new JPanel(new GridLayout(5, 2, 0, 5));
    private JPanel rightGrid = new JPanel(new GridLayout(4, 1));
    private JPanel rightGrid1 = new JPanel(new GridLayout(4, 1, 0, 5));
    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private JButton cancel = new JButton("Cancel");
    private JButton buy = new JButton("Buy");
    private JButton exit = new JButton("Exit");
    private JButton back = new JButton("Back");
    private BasketData basketData;
    private DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    
    public Customer(final BasketData basketData) {
        this.basketData = basketData;
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/others/b.png"));
        Border newBorder = BorderFactory.createRaisedBevelBorder();
        setLayout(new BorderLayout());
        setSize(720, 200);
        setLocationRelativeTo(null);
        setTitle("Customer Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        Date date = new Date();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JPanel bottom = new JPanel();
        leftGrid1.add(new JLabel("<html><font color='white'>Title</font><html>"));
        leftGrid1.add(new JLabel("<html><font color='white'>Name</font><html>"));
        leftGrid1.add(new JLabel("<html><font color='white'>Surname</font><html>"));
        leftGrid1.add(new JLabel("<html><font color='white'>Address</font><html>"));
        leftGrid1.add(new JLabel("<html><font color='white'>Post Code</font><html>"));
        leftGrid.add(title);
        leftGrid.add(name);
        leftGrid.add(surname);
        leftGrid.add(address);
        leftGrid.add(address1);
        rightGrid1.add(new JLabel("<html><font color='white'>Email</font><html>"));
        rightGrid1.add(new JLabel("<html><font color='white'>Card Number</font><html>"));
        rightGrid1.add(new JLabel("<html><font color='white'>CVV2 - 3 digit code</font><html>"));
        rightGrid1.add(new JLabel("<html><font color='white'>Date & Time</font><html>"));
        rightGrid.add(email);
        rightGrid.add(cardNumber);
        rightGrid.add(cardCvv);
        rightGrid.add(dateTime);
        dateTime.setText(dateFormat.format(date));
        bottom.add(cancel);
        cancel.addActionListener(this);
        cancel.setBorder(newBorder);
        cancel.setBackground(Color.DARK_GRAY);
        cancel.setForeground(Color.white);
        cancel.setMnemonic(KeyEvent.VK_C);
        bottom.add(buy);
        buy.addActionListener(this);
        buy.setBorder(newBorder);
        buy.setBackground(Color.DARK_GRAY);
        buy.setForeground(Color.white);
        buy.setMnemonic(KeyEvent.VK_B);
        bottom.add(back);
        back.addActionListener(this);
        back.setBorder(newBorder);
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.white);
        back.setMnemonic(KeyEvent.VK_K);
        bottom.add(exit);
        exit.addActionListener(this);
        exit.setBorder(newBorder);
        exit.setBackground(Color.DARK_GRAY);
        exit.setForeground(Color.white);
        exit.setMnemonic(KeyEvent.VK_E);
        left.add(leftGrid1);
        left.add(leftGrid);
        right.add(rightGrid1);
        right.add(rightGrid);
        add("West", left);
        add("East", right);
        add("South", bottom);
        setResizable(true);
        setVisible(true);
	   //get current date time with Calendar()
	   Calendar cal = Calendar.getInstance();
    }
    FileWriter outFile;
    PrintWriter printFile;

    public void actionPerformed(ActionEvent e) {
        String titleStr = title.getText();
        String nameStr = name.getText();
        String surnameStr = surname.getText();
        String cardStr = cardNumber.getText();
        String cardCvvStr = cardCvv.getText();
        String emailStr = email.getText();
        String addressStr = address.getText();
        String address1Str = address1.getText();
        String fileName = "res/invoices/" + surnameStr + "'s_Invoice_on-"+dateTime.getText()+".txt";
        Double totalPrice = 0.0;
        
        if (e.getSource() == buy) { // not allow double saving , once is saved need to close window
            if (title.getText().equals("") || name.getText().equals("")|| surname.getText().equals("") || address.getText().equals("")
                    || address1.getText().equals("") || email.getText().equals("") || cardNumber.getText().equals("")|| cardCvv.getText().equals("")){
                JOptionPane.showMessageDialog(null, "All fields must be filled!");
                title.requestFocus();
            } else {
            try {

                //record order details
                outFile = new FileWriter(fileName, true);
                printFile = new PrintWriter(outFile);
                printFile.println("------------------------------------------------------------------          " + 
                        titleStr + " " + surnameStr + 
                        " Invoice          ----------------------------------------------------------------" + "\n\r\n\r\n\r\n\r"
                        + "          JAVA Electronics\n          123 Greenwich High Street\n"
                        + "          SE12 345\n          tel.: 020812345678\n\r\n\r");
                for (int i = 0; i < basketData.getKeys().size(); i++) {
                    
                    String key = "" + (basketData.getKeys().get(i));
                    int qInBasket =Integer.parseInt(""+basketData.getQuantities().get(i));
                    Double singlePrice = StockData.getPrice(key);
                    totalPrice += qInBasket * StockData.getPrice(key);
                    if (Integer.parseInt(basketData.getKeys().get(i)+"") == 0 ){
                    
                    } else {
                    printFile.append("          " + StockData.getName(key));
                    printFile.append("          unit price -    £" + singlePrice);
                    printFile.println("          quantity - " + qInBasket);
//                    printFile.println("");
//                    printFile.println("");
                    }
//                    updateStock(key, qInBasket);
                    StockData.update(key, -qInBasket);
                
                }

                printFile.println("          Total price = " + pounds.format(totalPrice) + "\r\n\r\n");
                printFile.println("          Your order will be dispatched to:");
                printFile.println("          " + titleStr + " " + nameStr + " " + surnameStr + "\n\r");
                printFile.println("          " + addressStr);
                printFile.println("          " + address1Str);
                printFile.println("\n\r\n\r\n\r\n\r\n\r\n          Thanks for your custom " + titleStr + " " + surnameStr);
                printFile.close();
                //record card details on a separate file on a different folder
                outFile = new FileWriter("res/payment_details/" + surnameStr + "_Card_Details_on_"+dateTime.getText()+".txt");
                printFile = new PrintWriter(outFile);
                printFile.println("\n\n          Card Number - " + cardStr + "\r\n");
                printFile.println("          CVV - 3 digits - " + cardCvvStr + "\r\n");
                printFile.println("          Value to charge = £" + totalPrice + "\r\n");
                printFile.println("          Please confirm payment by email - " + emailStr);
                printFile.close();

                JOptionPane.showMessageDialog(null, "Your order has been recorded and will be dispatched soon");

            } catch (IOException se) {
                System.out.println("FileWriting error!!!");
            }
            basketData.empty();
            Orders customer = new Orders(basketData);
            customer.sendFile(fileName);
            customer.showCustomer();
            
            super.dispose();
        }
        } else if (e.getSource() == cancel) {
            int decision = JOptionPane.showConfirmDialog(null, "Your order will be cancelled\n          Continue?",
                    "Cancel Order", 0);
            if ( decision == 0) {
                super.dispose();
            } else {
                title.requestFocus();
            }
        } else if (e.getSource() == back) {
            super.dispose();
            new Basket(basketData);
        } else if (e.getSource() == exit) {
            super.dispose();
        }

    }
   
}
