
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
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

public final class PurchaseItem extends JFrame implements ActionListener {

    private JTextField stockTf = new JTextField(10);
    private JSpinner itemSp = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
    private JTextArea nameTf = new JTextArea(1, 10);
    private JTextArea descriptionTf = new JTextArea(1, 10);
    private JTextArea priceTf = new JTextArea(1, 10);
    private JTextArea quantityTf = new JTextArea(1, 10);
    private JButton check = new JButton("Check Stock");
    private JButton add = new JButton("Add to Basket");
    private JButton clear = new JButton("Clear");
    private JButton back = new JButton("Back");
    private JButton exit = new JButton("Exit");
    private JButton basket = new JButton("Basket");
    private JButton productList = new JButton("Product List");
    private JButton image = new JButton();
    private JPanel mainLeft = new JPanel(new GridLayout(7, 3, 0, 1));
    private JPanel mainRight = new JPanel(new GridLayout(7, 1));
    private JPanel mainMiddle = new JPanel(new GridLayout(1, 2));
    private JPanel middle = new JPanel();
    private JPanel bottom = new JPanel();
    private JPanel top = new JPanel();
    private DecimalFormat pounds = new DecimalFormat("#,##0.00");
    private double totalPrice = 0.0;
    private BasketData basketData;
    UIManager UI = new UIManager();
    private String imageLocation;
    private ImageIcon iconLogo = new ImageIcon("res/images/logo1.png");
    private JLabel logoImage = new JLabel(iconLogo);
    private Border newBorder = BorderFactory.createRaisedBevelBorder();

    public PurchaseItem(BasketData basketData) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/others/b.png"));
        
        this.basketData = basketData;
        stockTf.addActionListener(this);
        image.addActionListener(this);
        setLayout(new BorderLayout());
        setSize(470, 550);
        setLocationRelativeTo(null);
        setTitle("Purchase Item");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        mainLeft.add(new JLabel("<html><font color='white'>     Product Number</font></html>"));
        mainLeft.add(stockTf);
        mainLeft.add(check);
        lookFormat(check,1);
        mainLeft.add(new JLabel("<html><font color='white'>     Please select quantity  </font></html>"));
        mainLeft.add(itemSp);
        mainLeft.add(add);
        lookFormat(add, KeyEvent.VK_A);
        nameTf.setEditable(false);
        mainLeft.add(new JLabel("<html><font color='white'>     Name</font></html>"));
        mainLeft.add(nameTf);
        mainLeft.add(basket);
        lookFormat(basket, KeyEvent.VK_B);
        descriptionTf.setEditable(false);
        mainLeft.add(new JLabel("<html><font color='white'>     Description</font></html>"));
        mainLeft.add(descriptionTf);
        mainLeft.add(productList);
        lookFormat(productList, KeyEvent.VK_L);
        mainLeft.add(new JLabel("<html><font color='white'>     Price</font></html>"));
        mainLeft.add(priceTf);
        priceTf.setEditable(false);
        mainMiddle.add(clear);
        lookFormat(clear, KeyEvent.VK_C);
        mainMiddle.add(back);
        lookFormat(back, KeyEvent.VK_K);
        mainLeft.add(mainMiddle);
        mainLeft.add(new JLabel("<html><font color='white'>     Quantity</font></html>"));
        mainLeft.add(quantityTf);
        quantityTf.setEditable(false);
        mainLeft.add(exit);
        lookFormat(exit, KeyEvent.VK_E);
        

        top.add(mainLeft);
        top.add(mainRight);

        bottom.add(Box.createRigidArea(new Dimension(0, 100)));
        mainLeft.setBackground(Color.DARK_GRAY);
        top.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        middle.setBackground(Color.DARK_GRAY);

        bottom.add(logoImage);
        add("South", bottom);
        add("North", top);
//        add("East", right);
        add("Center", middle);
//        add("West", left);
        UI.put("OptionPane.background", Color.DARK_GRAY);
        UI.put("Panel.background", Color.DARK_GRAY);
        UI.put("OptionPane.messageForeground", Color.white);
//                UI.put("Panel.messageForeground", Color.white);

        setResizable(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String key = stockTf.getText();
        String name = StockData.getName(key);
        String description = StockData.getDescription(key);
        String price = "" + pounds.format(StockData.getPrice(key));
        String quantity = "" + StockData.getQuantity(key);
        int itemInt = Integer.parseInt("" + itemSp.getValue());
        int quantityInt = Integer.parseInt("" + StockData.getQuantity(key));

        if (e.getSource() == check || e.getSource() == stockTf) {
            if (name == null) {
                JOptionPane.showMessageDialog(null, "Please enter a product number");
                stockTf.setText("");
                stockTf.requestFocus();
            } else {
                                                                                        // Try to change image storage and display method
                imageLocation = "res/images/" + stockTf.getText() + ".png";
                Image myImage = getToolkit().createImage(imageLocation);
                ImageIcon productImage = new ImageIcon(myImage);
                image.setBorder(null);
                image.setBackground(Color.DARK_GRAY);
                image.setFocusPainted(false);
                itemSp.setValue(1);
                bottom.removeAll();
                middle.removeAll();
                bottom.add(new JLabel("<html><font color='white'>Click on the picture for a large image</font></html>"));
                middle.add(image);
                middle.setVisible(true);
                image.setIcon(productImage);
                image.setVisible(true);
                bottom.add(Box.createRigidArea(new Dimension(0, 10)));
                nameTf.setText(name);
                descriptionTf.setText(description);
                priceTf.setText(price);
                quantityTf.setText(quantity);
                setVisible(true);
                itemSp.requestFocus();
            }
        } else if (e.getSource() == add) {
            try {
                int quantityAdded = Integer.parseInt("" + itemSp.getValue());
                int itemLimit = 5;
                int qInBasket = basketData.getBasketQuantity(Integer.parseInt(key));
                int stockMinusQ = StockData.getQuantity(key) - qInBasket;
                System.out.println("this is stockMinusQ " + stockMinusQ);
                System.out.println("this is basket " + qInBasket);
                System.out.println("this is stock " + StockData.getQuantity(key));
                if (name == null) {
                    JOptionPane.showMessageDialog(null, "Please check the item availability first");
                    stockTf.setText("");
                    stockTf.requestFocus();
                } else if (itemInt > stockMinusQ || itemInt == 0) {
                    JOptionPane.showMessageDialog(null, "This item is either out of stock or not available in the quantity required", "Your order could not be completed", JOptionPane.INFORMATION_MESSAGE);
                    stockTf.setText("");
                    stockTf.requestFocus();
                } else {
                    if (basketData.keyActualSize() == basketData.getMaxProducts()) {
                        boolean found = false;
                        for (int i = 0; i < basketData.getKeys().size(); ++i) {
int keyInt = Integer.parseInt("" + basketData.getKeys().get(i));
int quantInt = Integer.parseInt("" + basketData.getQuantities().get(i));
                            if (basketData.getKeys().get(i).equals(Integer.parseInt(key)) && quantInt + itemInt > itemLimit) {
                                found = true;
                                JOptionPane.showMessageDialog(null, "Maximum number of items reached");
                                itemSp.requestFocus();
                            }
                        }
                        if (!found) {
                            if ((basketData.getKeys()).contains(key)) {
                                int decision = JOptionPane.showConfirmDialog(null, "You just added " + quantityAdded
                                        + " X " + name + " to your Basket\nCheckout?");
                                if (decision == 0) {
                                    basketData.update(key, quantityAdded);
                                    new Basket(basketData);
                                    super.dispose();
                                } else if (decision == 1) {
                                    basketData.update(key, quantityAdded);
                                    bottom.removeAll();
                                    middle.removeAll();
                                    middle.setVisible(false);
                                    bottom.setVisible(false);
                                    itemSp.setValue(0);
                                    nameTf.setText("");
                                    descriptionTf.setText("");
                                    priceTf.setText("");
                                    quantityTf.setText("");
                                    stockTf.setText("");
                                    bottom.add(logoImage);
                                    bottom.setVisible(true);
                                    setVisible(true);
                                    stockTf.requestFocus();
                                    
                                } else {
                                    JOptionPane.showMessageDialog(null, "Your item was not added");
                                    stockTf.setText("");
                                    stockTf.requestFocus();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Maximum number of different items per order reached!");
                                itemSp.setValue(0);
                                nameTf.setText("");
                                descriptionTf.setText("");
                                priceTf.setText("");
                                quantityTf.setText("");
                                bottom.remove(logoImage);
                                bottom.removeAll();
                                bottom.setVisible(false);
                                stockTf.setText("");
                                stockTf.requestFocus();
                            }
                        }
                    } else { //start of second loop
                        boolean found = false;
                        for (int i = 0; i < basketData.getKeys().size(); ++i) {
                            int keyInt = Integer.parseInt("" + basketData.getKeys().get(i));
                            int quantInt = Integer.parseInt("" + basketData.getQuantities().get(i));
                            if (basketData.getKeys().get(i).equals(Integer.parseInt(key)) && quantInt + itemInt > itemLimit) {
                                found = true;
                                JOptionPane.showMessageDialog(null, "Maximum number of items reached");
                                itemSp.requestFocus();
                            } else if (basketData.getKeys().get(i).equals("empty")) {
                                System.out.println("OK , empty found it !!!!    " + basketData.getKeys().get(i));
                            }
                        }
                        if (!found) {
                            int decision = JOptionPane.showConfirmDialog(null, "You just added " + quantityAdded
                                    + " X " + name + " to your Basket\nCheckout?");
                            if (decision == 0) {
                                basketData.update(key, quantityAdded);
                                new Basket(basketData);
                                super.dispose();
                            } else if (decision == 1) {
                                basketData.update(key, quantityAdded);
                                bottom.removeAll();
                                middle.removeAll();
                                middle.setVisible(false);
                                bottom.setVisible(false);
                                itemSp.setValue(0);
                                nameTf.setText("");
                                descriptionTf.setText("");
                                priceTf.setText("");
                                quantityTf.setText("");
//                                bottom.remove(logoImage);
//                                bottom.removeAll();
//                                bottom.setVisible(false);
                                stockTf.setText("");
                                bottom.add(logoImage);
                                bottom.setVisible(true);
                                setVisible(true);
                                stockTf.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(null, "Your item was not added");
                                itemSp.requestFocus();
                            }
                        }
                    } //end of second loop 
                }
            } catch (NullPointerException xe) {
                System.out.println("Null error caught!!!! after Add pressed");
                //Maybe clear the arrays is the best solution right now
            }
        } else if (e.getSource() == back) {
            int decision = JOptionPane.showConfirmDialog(null, "All the data in the basket will be lost,\nContinue?", "Back to Main Screen", 0);
            if (decision == 0) {
                super.dispose();
                new Master();
            } else {
                stockTf.setText("");
                stockTf.requestFocus();
            }
        } else if (e.getSource() == productList) {
            new StockList(basketData);

        } else if (e.getSource() == clear) {
            bottom.removeAll();
            middle.removeAll();
            bottom.add(logoImage);
            bottom.setVisible(true);
            stockTf.setText("");
            itemSp.setValue(0);
            nameTf.setText("");
            descriptionTf.setText("");
            priceTf.setText("");
            quantityTf.setText("");
            stockTf.requestFocus();
        } else if (e.getSource() == basket) {
            new Basket(basketData);
            super.dispose();
        } else if (e.getSource() == image) {
            BigImage sendImage = new BigImage();
            sendImage.bigImage("res/images/" + stockTf.getText() + "big.png");
            sendImage.showImage();
        } else if (e.getSource() == exit) {
            super.dispose();
        }
    }

    public void lookFormat(JButton newButton, int shortcut) {
        newButton.setBackground(Color.DARK_GRAY);
        newButton.setForeground(Color.white);
        newButton.setBorder(newBorder);
        newButton.addActionListener(this);
        newButton.setMnemonic(shortcut);
    }
    
    
}
