
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.*;

public class UpdateStock extends JFrame implements ActionListener, ChangeListener {

    private JTextField stockNo = new JTextField(10);
    private JTextField nameTf = new JTextField(10);
    private JTextField descriptionTf = new JTextField(10);
    private JTextField priceTf = new JTextField(10);
    private JTextField quantityTf = new JTextField(10);
    private JTextArea information = new JTextArea(5, 25);
    private JButton check = new JButton("Check Stock");
    private JButton updateQuantity = new JButton("Update Quantity");
    private JButton updateDescription = new JButton("Update Description");
    private JButton order = new JButton("Orders");
    private JButton newItem = new JButton("New");
    private JButton deleteItem = new JButton("Delete");
    private JButton clear = new JButton("Clear");
    private JButton back = new JButton("Back");
    private JButton exit = new JButton("Exit");
//    DecimalFormat pounds = new DecimalFormat("#,##0.00");
//    Can't use pounds formatting as it's messing up newItem and 
    private JSpinner itemSp = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
    private JPanel gridTf = new JPanel(new GridLayout(7, 2, 0, 1));
    private JPanel buttonGrid = new JPanel(new GridLayout(1, 2));
    private JPanel buttonGrid1 = new JPanel(new GridLayout(1, 2));
    private JPanel buttonGrid2 = new JPanel(new GridLayout(1, 2));
    private JPanel textGrid = new JPanel(new GridLayout(1, 2));
//    JPanel left = new JPanel();
//    JPanel right = new JPanel();
    private JPanel middle = new JPanel();
    private JPanel bottom = new JPanel();
    private JPanel top = new JPanel();
    private ImageIcon iconLogo = new ImageIcon("res/images/logo1.png");
    private JLabel logoImage = new JLabel(iconLogo);
    UIManager UI = new UIManager();
    Border newBorder = BorderFactory.createRaisedBevelBorder();
        

    public UpdateStock() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/others/b.png"));

        itemSp.addChangeListener(this);
        stockNo.addActionListener(this);

        lookFormat(check, 0);
        lookFormat(updateQuantity, KeyEvent.VK_Q);
        lookFormat(updateDescription, KeyEvent.VK_U);       
        lookFormat(order, KeyEvent.VK_O);
        lookFormat(newItem, KeyEvent.VK_N);
        lookFormat(clear, KeyEvent.VK_C);
        lookFormat(back, KeyEvent.VK_K);
        lookFormat(deleteItem, KeyEvent.VK_D);
        lookFormat(exit, KeyEvent.VK_E);
        
        
        setLayout(new BorderLayout());
        setSize(470, 580);
        setLocationRelativeTo(null);
        setTitle("Update Stock");
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        gridTf.add(Box.createRigidArea(new Dimension(0, 10)));
        gridTf.add(new JLabel("<html><font color='white'>    All fields are editable</font></html>"));//really!?!?!?!
        gridTf.add(Box.createRigidArea(new Dimension(0, 10)));
        gridTf.add(new JLabel("<html><font color='white'>    Product Number </font></html>"));
        gridTf.add(stockNo);
        gridTf.add(check);
        gridTf.add(new JLabel("<html><font color='white'>Please select quantity  </font></html>"));
        gridTf.add(itemSp);
        gridTf.add(updateQuantity);
        gridTf.add(new JLabel("<html><font color='white'>    Name </font></html>"));
        gridTf.add(nameTf);
        gridTf.add(updateDescription);

//        gridTf.add(Box.createRigidArea(new Dimension(0, 10)));
        gridTf.add(new JLabel("<html><font color='white'>    Description </font></html>"));
        gridTf.add(descriptionTf);
//        gridTf.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonGrid.add(back);
        buttonGrid.add(clear);
        gridTf.add(buttonGrid);
        textGrid.add(new JLabel("<html><font color='white'>Price</font></html>", JLabel.LEFT));
        textGrid.add(new JLabel("<html><font color='white'>£</font></html>", JLabel.RIGHT));
        gridTf.add(textGrid);
        textGrid.setBackground(Color.DARK_GRAY);
        gridTf.add(priceTf);
        buttonGrid1.add(newItem);
        buttonGrid1.add(order);
        gridTf.add(buttonGrid1);
        gridTf.add(new JLabel("<html><font color='white'>    Quantity </font></html>"));
        gridTf.add(quantityTf);
        buttonGrid2.add(deleteItem);
        buttonGrid2.add(exit);
        gridTf.add(buttonGrid2);
//        gridTf.add(Box.createRigidArea(new Dimension(0, 10)));
//        gridTf.add(Box.createRigidArea(new Dimension(0, 10)));

        top.add(gridTf);

        middle.add(logoImage);
        bottom.add(information);
        information.setEditable(false);
        bottom.add(Box.createRigidArea(new Dimension(0, 100)));
        gridTf.setBackground(Color.DARK_GRAY);
        top.setBackground(Color.DARK_GRAY);
        middle.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);

        add("South", bottom);
        add("North", top);
//        add("East", right);
        add("Center", middle);
//        add("West", left);
//        this.getContentPane().setBackground(Color.red);
//        UI.put("OptionPane.background", Color.DARK_GRAY);
//        UI.put("Panel.background", Color.DARK_GRAY);
//        UI.put("OptionPane.messageForeground", Color.white);
        setResizable(false);
        setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        String key = stockNo.getText();
        String name = StockData.getName(key);
        String description = StockData.getDescription(key);
        String pricePounds = "" + StockData.getPrice(key);
        String quantity = "" + StockData.getQuantity(key);
        iconLogo = new ImageIcon("res/images/" + stockNo.getText() + ".png");
        JLabel iconJLabel = new JLabel(iconLogo);
        int itemInt = Integer.parseInt("" + itemSp.getValue());
        int quantityInt = Integer.parseInt("" + StockData.getQuantity(key));


        if (e.getSource() == check || e.getSource() == stockNo) {
            if (name == null) {
                JOptionPane.showMessageDialog(null, "          No such item in stock");
                stockNo.setText("");
            } else {
                middle.removeAll();
                middle.setVisible(false);
                information.setText("");
                itemSp.setValue(0);
                nameTf.setText(name);
                descriptionTf.setText(description);
                priceTf.setText(pricePounds);
                quantityTf.setText(quantity);
                middle.add(iconJLabel);
                middle.setVisible(true);
                setVisible(true);

            }

        } else if (e.getSource() == updateQuantity) {// put condition for empty program number!!!!!
            if (name == null) {
                JOptionPane.showMessageDialog(null, "          Select a item to update");
            } else {
                if (Integer.parseInt("" + itemSp.getValue()) > 0) {
                    StockData.update(stockNo.getText(), Integer.parseInt("" + itemSp.getValue()));
                    information.setText("          You updated the item " + name + "\n\n"
                            + "          Quantity added = " + itemSp.getValue());
                    quantityTf.setText("" + StockData.getQuantity(key));

                } else if (Integer.parseInt("" + itemSp.getValue()) < 0) {
                    if (quantityInt + itemInt < 0) {
                        JOptionPane.showMessageDialog(null, "          Can't have negative stock \n"
                                + "            There's " + quantity + " in stock");

                    } else {
                        Integer removed = Integer.parseInt("" + itemSp.getValue());
                        removed = removed * -1;
                        StockData.update(stockNo.getText(), Integer.parseInt("" + itemSp.getValue()));
                        information.setText("\n          You updated the item " + name + "\n\n          "
                                + "Quantity removed = " + removed);
                        quantityTf.setText("" + StockData.getQuantity(key));


                    }
                } else {
                    JOptionPane.showMessageDialog(null, "          Please select a quantity");
                }

            }
            clear.doClick();
            information.setText("\r\n                    Item has been updated");
            stockNo.requestFocus();
        } else if (e.getSource() == updateDescription) {
            if (name == null) {
                JOptionPane.showMessageDialog(null, "          Select a item to update");
            } else {
                StockData.updateDescription(key, nameTf.getText(), descriptionTf.getText(), priceTf.getText());
                clear.doClick();
                information.setText("\r\n                    Item has been updated");
                stockNo.requestFocus();
            }
        } else if (e.getSource() == order) {
            super.dispose();
            Orders display = new Orders(null);
            display.showEmployee();
        } else if (e.getSource() == newItem) {


            if (nameTf.getText().equals("") || descriptionTf.getText().equals("") || priceTf.getText().equals("") || quantityTf.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "To add a new product fill in the name,\n"
                        + "description, price, quantity and press new.");//don't allow any field to be empty
            } else if (!"".equals(stockNo.getText())) {

                JOptionPane.showMessageDialog(null, "Please leave product number empty.");
                System.out.println("This " + stockNo.getText());
                stockNo.requestFocus();
            } else {
                String password = JOptionPane.showInputDialog("Enter Password");
                if (password == null) {
                    System.out.println("null error");// or do nothing !
                } else if (password.equals("bigsales")) {
                    String newKey = "" + StockData.getNewKey();
                    StockData.addNewItem(newKey, nameTf.getText(), descriptionTf.getText(), priceTf.getText(), quantityTf.getText());
                    stockNo.setText(newKey);
                    check.doClick();
                    information.setText("\r\n  The new product has been added to the Stock");
                } else {
                    int decision = JOptionPane.showConfirmDialog(null, "Wrong Password!\n   Try again?", "Wrong Password", 0);
                    if (decision == 0) {
                        newItem.doClick();
                    } else {
                        stockNo.requestFocus();
                    }
                }
            }

        } else if (e.getSource() == deleteItem) {
            int decision = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Item", 0);
            if (decision == 0) {
                StockData.deleteItem(key);
                clear.doClick();
                information.setText("\r\n        The Item ' " + name + " ' has been deleted.");
            } else {
                information.setText("          The Item was not deleted.");
            }



        } else if (e.getSource() == back) {
            super.dispose();
            new Master();
        } else if (e.getSource() == clear) {
            middle.removeAll();
            middle.setVisible(false);
            information.setText("");
            stockNo.setText("");
            nameTf.setText("");
            descriptionTf.setText("");
            priceTf.setText("");
            quantityTf.setText("");
            itemSp.setValue(0);
            middle.add(logoImage);
            middle.setVisible(true);
            setVisible(true);
        } else if (e.getSource() == exit) {
            super.dispose();

        }

    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        int x = Integer.parseInt("" + itemSp.getValue());
        int a = x * -1;
        if (x == 1) {
            information.setText("\n          You will add 1 item");
            //make it singular and plural
        } else if (x > 1) {
            information.setText("\n          You will add " + itemSp.getValue() + " items");
        } else if (x == 1) {
            information.setText("\n          You will remove 1 item");
        } else if (x == -1) {
            information.setText("\n          You will remove " + a + " items");
        } else if (x < -1) {
            information.setText("\n          You will remove " + a + " items");
        } else {
            information.setText("\n          Please select a quantity");

        }
    }

    private void lookFormat(JButton newButton, int shortcut) {
        newButton.setBackground(Color.DARK_GRAY);
        newButton.setForeground(Color.white);
        newButton.setBorder(newBorder);
        newButton.setMnemonic(shortcut);
        newButton.addActionListener(this);
    }
}
