
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class CheckStock extends JFrame implements ActionListener {

    JTextField stockNo = new JTextField(12);
    JTextArea nameTxt = new JTextArea(1, 12);
    JTextArea descriptionTxt = new JTextArea(1, 12);
    JTextArea priceTxt = new JTextArea(1, 12);
    JTextArea quantityTxt = new JTextArea(1, 12);
    JPanel leftGrid = new JPanel(new GridLayout(5, 1, 0, 5));
    JPanel rightGrid = new JPanel(new GridLayout(5, 1));
    JButton check = new JButton("Check Stock");
    JButton list = new JButton("Product list");
    JButton back = new JButton("Back");
    JButton exit = new JButton("Exit");
    DecimalFormat pounds = new DecimalFormat("#,##0.00");
    public CheckStock() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/others/b.png"));
        Border newBorder = BorderFactory.createRaisedBevelBorder();
        setLayout(new BorderLayout());
        setSize(300, 190);
        setLocationRelativeTo(null);
        setTitle("Check Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        stockNo.addActionListener(this);
        check.setBackground(Color.DARK_GRAY);
        check.setForeground(Color.white);
        check.addActionListener(this);
        check.setBorder(newBorder);
        list.setBackground(Color.DARK_GRAY);
        list.setForeground(Color.white);
        list.addActionListener(this);
        list.setBorder(newBorder);
        list.setMnemonic(KeyEvent.VK_L);
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.setMnemonic(KeyEvent.VK_K);
        back.setBorder(newBorder);
        exit.setBackground(Color.DARK_GRAY);
        exit.setForeground(Color.white);
        exit.addActionListener(this);
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setBorder(newBorder);

        JPanel top = new JPanel();
        
        add("North", top);
        JPanel middle = new JPanel();
        leftGrid.add(new JLabel("<html><font color='white'>Enter Stock Number:</font></html>"));
        leftGrid.add(new JLabel("<html><font color='white'>Product Name</font></html>"));
        leftGrid.add(new JLabel("<html><font color='white'>Description</font></html>"));
        leftGrid.add(new JLabel("<html><font color='white'>Price</font></html>"));
        leftGrid.add(new JLabel("<html><font color='white'>Quantity</font></html>"));
        rightGrid.add(stockNo);
        rightGrid.add(nameTxt);
        nameTxt.setEditable(false);
        rightGrid.add(descriptionTxt);
        descriptionTxt.setEditable(false);
        rightGrid.add(priceTxt);
        priceTxt.setEditable(false);
        rightGrid.add(quantityTxt);
        quantityTxt.setEditable(false);
        middle.add(leftGrid);
        middle.add(rightGrid);
        add("Center", middle);
        
        JPanel bottom = new JPanel();
        bottom.add(check);
        bottom.add(list);
        bottom.add(back);
        bottom.add(exit);
        add("South", bottom);
        leftGrid.setBackground(Color.DARK_GRAY);
        top.setBackground(Color.DARK_GRAY);
        middle.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);
        setResizable(true);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        String key = stockNo.getText();
        String name = StockData.getName(key);
        String description = StockData.getDescription(key);
        if (e.getSource() == check || e.getSource() == stockNo){
        if (name == null) {
            nameTxt.setText("No such item");
            descriptionTxt.setText("in stock");
            priceTxt.setText("");
            quantityTxt.setText("");
        } else {
            nameTxt.setText("");
            priceTxt.setText("");
            quantityTxt.setText("");
            nameTxt.setText("  " + name);
            descriptionTxt.setText("  " + description);
            priceTxt.append("  " + pounds.format(StockData.getPrice(key)));
            quantityTxt.append("  " + StockData.getQuantity(key));
            }
        } else if ( e.getSource() == back){
            super.dispose();
            new Master();
        } else if (e.getSource() == list ){
            new StockList(null);
        } else {
            super.dispose();
        }
    }
}
