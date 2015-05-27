
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public final class Basket extends JFrame implements ActionListener {

    private JTextArea information = new JTextArea(5, 10);
    private JTextArea information1 = new JTextArea(5, 10);
    private JTextArea information2 = new JTextArea(5, 10);
    private JSpinner quantChange = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
    private JSpinner quantChange1 = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
    private JSpinner quantChange2 = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
    private JSpinner quantChange3 = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
    private JSpinner quantChange4 = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
    private JTextArea totalPrice = new JTextArea(1, 8);
    private JPanel textGrid = new JPanel(new GridLayout(1, 7, 0, 5));
    private JPanel textGrid1 = new JPanel(new GridLayout(1, 3, 0, 50));
    private  JPanel buttonGrid = new JPanel(new GridLayout(5, 2));
    private JPanel middleGrid = new JPanel(new GridLayout(1, 1)); // !!!
    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private JButton tempButton = new JButton("Check Arrays"); //need to remove this
    private JButton checkout = new JButton("Checkout");
    private JButton exit = new JButton("Exit");
    private JButton back = new JButton("Back");
    private JButton refresh = new JButton("Refresh");
    private JButton removeBtn = new JButton("<html><font color='white'>Remove</font></html>");
    private  JButton removeBtn1 = new JButton("<html><font color='white'>Remove</font></html>");
    private JButton removeBtn2 = new JButton("<html><font color='white'>Remove</font></html>");
    private JButton removeBtn3 = new JButton("<html><font color='white'>Remove</font></html>");
    private JButton removeBtn4 = new JButton("<html><font color='white'>Remove</font></html>");
    private JButton updateBtn = new JButton("<html><font color='white'>Update</font></html>");
    private JButton updateBtn1 = new JButton("<html><font color='white'>Update</font></html>");
    private JButton updateBtn2 = new JButton("<html><font color='white'>Update</font></html>");
    private JButton updateBtn3 = new JButton("<html><font color='white'>Update</font></html>");
    private JButton updateBtn4 = new JButton("<html><font color='white'>Update</font></html>");
//    Image myImage = getToolkit().createImage("res/images/11.png");
//    ImageIcon myIcon = new ImageIcon(myImage);
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    private BasketData basketData;
    int x = 0;
    Border newBorder = BorderFactory.createRaisedBevelBorder();
    
    public Basket(final BasketData basketData) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/others/b.png"));
        
        this.basketData = basketData;
        setLayout(new BorderLayout());
        setSize(550, 200);
        setLocationRelativeTo(null);
        setTitle("Basket");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        bottom.add(new JLabel("<html><font color='white'>Total</font></html>"));
        bottom.add(totalPrice);
        totalPrice.setEditable(false);
//        bottom.add(tempButton); // temporary button to check arrays contents
//        tempButton.addActionListener(this);
        bottom.add(checkout);
        lookFormat(checkout, KeyEvent.VK_C);
        bottom.add(refresh);
        lookFormat(refresh, KeyEvent.VK_R);       
        bottom.add(back);
        lookFormat(back, KeyEvent.VK_K);
        bottom.add(exit);
        lookFormat(exit, KeyEvent.VK_E);
        //        checkout.setIcon(myIcon); ??
        //        checkout.setBorder(null); ??
        top.add(new JLabel("<html><font color='white'>Product Name</font></html>"));
        top.add(Box.createRigidArea(new Dimension(20, 10)));
        top.add(new JLabel("<html><font color='white'>Unit Price</font></html>"));
        top.add(Box.createRigidArea(new Dimension(30, 10)));
        top.add(new JLabel("<html><font color='white'> Item Total</font></html>"));
        top.add(Box.createRigidArea(new Dimension(70, 10)));
        top.add(new JLabel("<html><font color='white'>Quantity</font></html>"));
        top.add(Box.createRigidArea(new Dimension(120, 10)));
        textGrid1.add(information);
        information.setEditable(false);
        information.setPreferredSize(new Dimension(5, 10));
        textGrid1.add(information1);
        information1.setEditable(false);
        information1.setPreferredSize(new Dimension(5, 10));
        textGrid1.add(information2);
        information2.setEditable(false);
        information2.setPreferredSize(new Dimension(5, 10));
        textGrid1.setBorder(newBorder);
        buttonGrid.add(quantChange);
        quantChange.setPreferredSize(new Dimension(5, 10));
        quantChange.setVisible(false);
        formatButton(removeBtn);
        formatButton(updateBtn);
        buttonGrid.add(updateBtn);
        buttonGrid.add(removeBtn);
        buttonGrid.add(quantChange1);
        quantChange1.setPreferredSize(new Dimension(5, 10));
        quantChange1.setVisible(false);
        formatButton(removeBtn1);
        formatButton(updateBtn1);
        buttonGrid.add(updateBtn1);
        buttonGrid.add(removeBtn1);
        buttonGrid.add(quantChange2);
        quantChange2.setPreferredSize(new Dimension(5, 10));
        quantChange2.setVisible(false);
        formatButton(removeBtn2);
        formatButton(updateBtn2);
        buttonGrid.add(updateBtn2);
        buttonGrid.add(removeBtn2);
        buttonGrid.add(quantChange3);
        quantChange3.setPreferredSize(new Dimension(5, 10));
        quantChange3.setVisible(false);
        formatButton(removeBtn3);
        formatButton(updateBtn3);
        buttonGrid.add(updateBtn3);
        buttonGrid.add(removeBtn3);
        buttonGrid.add(quantChange4);
        quantChange4.setPreferredSize(new Dimension(5, 10));
        quantChange4.setVisible(false);
        formatButton(removeBtn4);
        formatButton(updateBtn4);
        buttonGrid.add(updateBtn4);
        buttonGrid.add(removeBtn4);

        JPanel middle = new JPanel();
        middle.add(textGrid1);
        middle.add(buttonGrid);

//        middle.add(middleGrid);
        add("North", top);
        add("Center", middle);
        add("South", bottom);
        add("East", left);
        add("West", right);

        getContentPane().setBackground(Color.DARK_GRAY);
        textGrid.setBackground(Color.DARK_GRAY);
        textGrid1.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);
        left.setBackground(Color.DARK_GRAY);
        right.setBackground(Color.DARK_GRAY);
        setResizable(true);
        setVisible(true);


        information.setText("");
        information1.setText("");
        information2.setText("");
        int totalQInBasket = 0;
        int arrayCounter = 0;
        int stockSize = 100;
        Double totalFinal = 0.0;
        int keysInt = 1;
        for (int j = 0; j < basketData.getKeys().size(); ++j) {
            int keyInt = Integer.parseInt("" + basketData.getKeys().get(j));
            if ( keyInt == stockSize || keyInt == 0 /*|| basketData.keys.get(j) == basketData
                     .keysToDisplay.get(j)*/) {
            } else {
                ++x;
                System.out.println("Loop worked  !   "
                        + "" + keyInt);
                ++arrayCounter;
            }

            String k = "" + (basketData.getKeys().get(j));

            String qInBasketString = "" + (basketData.getQuantities().get(j));
            int qInBasket = Integer.parseInt(qInBasketString);
            totalQInBasket += qInBasket;
            Double singlePrice = StockData.getPrice(k);
            Double totalPrice2 = 0.0;
            totalPrice2 += qInBasket * StockData.getPrice(k);
            if (Integer.parseInt("" + basketData.getQuantities().get(j)) == 0) {
            } else {
                information.append(StockData.getName(k) + "\n");
                information1.append("£" + singlePrice + "\n");
                information2.append("£" + totalPrice2 + "\n");
                totalFinal += totalPrice2;
                if (keysInt == 1) {
                    quantChange.setValue(qInBasket);
                    quantChange.setVisible(true);
                    removeBtn.setVisible(true);
                    updateBtn.setVisible(true);
                } else if (keysInt == 2) {
                    quantChange1.setValue(qInBasket);
                    quantChange1.setVisible(true);
                    removeBtn1.setVisible(true);
                    updateBtn1.setVisible(true);
                } else if (keysInt == 3) {
                    quantChange2.setValue(qInBasket);
                    quantChange2.setVisible(true);
                    removeBtn2.setVisible(true);
                    updateBtn2.setVisible(true);
                } else if (keysInt == 4) {
                    quantChange3.setValue(qInBasket);
                    quantChange3.setVisible(true);
                    removeBtn3.setVisible(true);
                    updateBtn3.setVisible(true);
                } else if (keysInt == 5) {
                    quantChange4.setValue(qInBasket);
                    quantChange4.setVisible(true);
                    removeBtn4.setVisible(true);
                    updateBtn4.setVisible(true);
                }
                keysInt++;
            }
        }
        totalPrice.setText(pounds.format(totalFinal));
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == checkout) {
            new Customer(basketData);
            super.dispose();
        } else if (e.getSource() == tempButton) {
            System.out.println("" + x);
            System.out.println("Keys\n" + basketData.getKeys());
            System.out.println("Quantities\n" + basketData.getQuantities());
        } else if (e.getSource() == back) {

            new PurchaseItem(basketData);
            super.dispose();
        } else if (e.getSource() == exit) {
            super.dispose();

        } else if (e.getSource() == refresh) {
            clearAll();


            information.setText("");
            information1.setText("");
            information2.setText("");
            int totalQInBasket = 0;
            int arrayCounter = 0;
            Double totalFinal = 0.0;
            int keysInt = 1;
            for (int j = 0; j < basketData.getKeys().size(); ++j) {


                String k = "" + (basketData.getKeys().get(j));

                String qInBasketString = "" + (basketData.getQuantities().get(j));
                int qInBasket = Integer.parseInt(qInBasketString);
                totalQInBasket += qInBasket;
                Double singlePrice = StockData.getPrice(k);
                Double totalPrice2 = 0.0;
                totalPrice2 += qInBasket * StockData.getPrice(k);
//                if (qInBasket > 0) {
                if (Integer.parseInt("" + basketData.getQuantities().get(j)) == 0) {
                } else {
                    information.append(StockData.getName(k) + "\n");
                    information1.append("£" + singlePrice + "\n");
                    information2.append("£" + totalPrice2 + "\n");
                    totalFinal += totalPrice2;
                    if (keysInt == 1) {
                        quantChange.setValue(qInBasket);
                        removeBtn.setVisible(true);
                        updateBtn.setVisible(true);
                        quantChange.setVisible(true);
                    } else if (keysInt == 2) {
                        quantChange1.setValue(qInBasket);
                        removeBtn1.setVisible(true);
                        updateBtn1.setVisible(true);
                        quantChange1.setVisible(true);
                    } else if (keysInt == 3) {
                        quantChange2.setValue(qInBasket);
                        removeBtn2.setVisible(true);
                        updateBtn2.setVisible(true);
                        quantChange2.setVisible(true);
                    } else if (keysInt == 4) {
                        quantChange3.setValue(qInBasket);
                        removeBtn3.setVisible(true);
                        updateBtn3.setVisible(true);
                        quantChange3.setVisible(true);
                    } else if (keysInt == 5) {
                        quantChange4.setValue(qInBasket);
                        removeBtn4.setVisible(true);
                        updateBtn4.setVisible(true);
                        quantChange4.setVisible(true);
                    }
                    keysInt++;
                }
            }
            totalPrice.setText(pounds.format(totalFinal));


        }
        else if (e.getSource() == removeBtn) {

            removeItem(1);

        } else if (e.getSource() == removeBtn1) {

            removeItem(2);

        } else if (e.getSource() == removeBtn2) {

            removeItem(3);

        } else if (e.getSource() == removeBtn3) {

            removeItem(4);

        } else if (e.getSource() == removeBtn4) {

            removeItem(5);

        } else if (e.getSource() == updateBtn) {


            int qInBasket = Integer.parseInt("" + quantChange.getValue());
            updateBasket(1, qInBasket);

        } else if (e.getSource() == updateBtn1) {
            int qInBasket = Integer.parseInt("" + quantChange1.getValue());
            updateBasket(2, qInBasket);

        } else if (e.getSource() == updateBtn2) {
            int qInBasket = Integer.parseInt("" + quantChange2.getValue());
            updateBasket(3, qInBasket);

        } else if (e.getSource() == updateBtn3) {
            int qInBasket = Integer.parseInt("" + quantChange3.getValue());
            updateBasket(4, qInBasket);

        } else if (e.getSource() == updateBtn4) {
            int qInBasket = Integer.parseInt("" + quantChange4.getValue());
            updateBasket(5, qInBasket);

        }

//        end of buttons

    }

    private void removeItem(int itemToRemove) {
        int found = 0;
        for (int i = 0; i < basketData.getKeys().size(); ++i) {
            int keyInt = Integer.parseInt("" + basketData.getKeys().get(i));
            if (keyInt > 0) {
                found++;
            }
            if (found == itemToRemove) {
                basketData.getKeys().set(i, 0);
                basketData.getQuantities().set(i, 0);
                break;
            }
        }
        refresh.doClick();
    }

    private void updateItem(int itemToAdd, int quantityToAdd) {
        int found = 0;

        for (int i = 0; i < basketData.getKeys().size(); ++i) {
            int keyInt = Integer.parseInt("" + basketData.getKeys().get(i));
            if (keyInt > 0) {
                found++;
            }
            if (found == itemToAdd) {

                basketData.getQuantities().set(i, quantityToAdd);
                break;
            }
        }

        refresh.doClick();
    }

    public int getQuantity(int a) { // not in use but returns quantity for respective key
        int found = 0;
        int quantity = 0;

        for (int i = 0; i < basketData.getKeys().size(); ++i) {
            int keyInt = Integer.parseInt("" + basketData.getKeys().get(i));
            if (keyInt > 0) {
                found++;
            }
            if (found == a) {

//                int key = basketData.keys.get(i);
                quantity =Integer.parseInt("" + basketData.getQuantities().get(i));
                System.out.println("Quantity " + quantity);
                break;
            }
        }

        return quantity;
    }

    public int getKey(int a) {
        int found = 0;
        int key = 0;

        for (int i = 0; i < basketData.getKeys().size(); ++i) {
            int keyInt = Integer.parseInt(""+basketData.getKeys().get(i));
            if (keyInt > 0) {
                found++;
            }
            if (found == a) {
//                basketData.keys.set(i, 0);

                key = keyInt;

                break;
            }
        }
        System.out.println("Key " + key);
        return key;
    }

    private void updateBasket(int field, int qInBasket) {
        int key = getKey(field);
        int stockCheck = StockData.getQuantity("" + key);
        int result = stockCheck - qInBasket;
        if (result == 0 || result > 0) {

            updateItem(field, qInBasket);
            // Array is updated

        } else if (result < 0) {
            // Array is not updated
        }
    }

    public void formatButton(JButton newButton) {
        newButton.addActionListener(this);
        newButton.setBorder(newBorder);
        newButton.setPreferredSize(new Dimension(56, 16));
        newButton.setBackground(Color.DARK_GRAY);
        newButton.setVisible(false);
    }

    public void lookFormat(JButton newLook, int shortcut) {
        newLook.setBorder(newBorder);
        newLook.setBackground(Color.DARK_GRAY);
        newLook.setForeground(Color.white);
        newLook.setMnemonic(shortcut);
        newLook.addActionListener(this);
    }

    private void clearAll() {
        information.setText("");
        information1.setText("");
        information2.setText("");
        quantChange.setValue(1);
        quantChange.setVisible(false);
        quantChange1.setValue(1);
        quantChange1.setVisible(false);
        quantChange2.setValue(1);
        quantChange2.setVisible(false);
        quantChange3.setValue(1);
        quantChange3.setVisible(false);
        quantChange4.setValue(1);
        quantChange4.setVisible(false);
        removeBtn.setVisible(false);
        removeBtn1.setVisible(false);
        removeBtn2.setVisible(false);
        removeBtn3.setVisible(false);
        removeBtn4.setVisible(false);
        updateBtn.setVisible(false);
        updateBtn1.setVisible(false);
        updateBtn2.setVisible(false);
        updateBtn3.setVisible(false);
        updateBtn4.setVisible(false);
    }
}
