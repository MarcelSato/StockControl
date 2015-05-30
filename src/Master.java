
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/*
*
*   created by Marcel Sato
*   University of Greenwich Project
*   DB has been changed from ACCESS to mySQL
*   Next step try to implement Spring management
*
*/

public class Master extends JFrame implements ActionListener {

    private JButton check = new JButton("Check Stock");
    private JButton purchase = new JButton("Purchase Item");
    private JButton stock = new JButton("Update Stock");
    private JButton quit = new JButton("Exit");
    BasketData basketData = new BasketData();

    public static void main(String[] args) {

        StockData connect = new StockData();

        try {
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
    } 
    catch (UnsupportedLookAndFeelException e) {
            try {
                // handle exception
                 UIManager.setLookAndFeel(
                     UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
        
//    new SwingApplication(); //Create and show the GUI.
        Master jf = new Master();
    }

    public Master() {
        Border newBorder = BorderFactory.createRaisedBevelBorder();
        setLayout(new BorderLayout());
        setSize(450, 320);
        setLocationRelativeTo(null);
        setTitle("Master");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/others/b.png"));
        ImageIcon icon = new ImageIcon("res/images/logo1.png");
        JLabel iconJLabel = new JLabel(icon);
        JPanel gridRight = new JPanel(new GridLayout(6, 1, 0, 1));
        JPanel top = new JPanel();
        top.add(new JLabel("<html><font color='white'>Select an option by clicking one of the buttons below</font></html>"));
        add("North", top);
        JPanel bottom = new JPanel();
        bottom.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        left.add(Box.createRigidArea(new Dimension(0, 10)));
        left.add(iconJLabel);
        right.add(Box.createRigidArea(new Dimension(0, 10)));
        gridRight.add(Box.createRigidArea(new Dimension(0, 10)));
        gridRight.add(check);
        check.addActionListener(this);
        check.setBackground(Color.DARK_GRAY);
        check.setForeground(Color.white);
        check.setMnemonic(KeyEvent.VK_C);
        check.setBorder(newBorder);
        check.setFocusPainted(false);// remove Selection Box on button
        gridRight.add(purchase);
        purchase.addActionListener(this);
        purchase.setBackground(Color.DARK_GRAY);
        purchase.setForeground(Color.white);
        purchase.setMnemonic(KeyEvent.VK_P);
        purchase.setBorder(newBorder);
        gridRight.add(stock);
        stock.addActionListener(this);
        stock.setBackground(Color.DARK_GRAY);
        stock.setForeground(Color.white);
        stock.setMnemonic(KeyEvent.VK_U);
        stock.setBorder(newBorder);
        gridRight.add(quit);
        quit.addActionListener(this);
        quit.setBackground(Color.DARK_GRAY);
        quit.setForeground(Color.white);
        quit.setMnemonic(KeyEvent.VK_E);
        quit.setBorder(newBorder);
        gridRight.add(Box.createRigidArea(new Dimension(0, 10)));
        add("Center", gridRight);
        add("South", bottom);
        add("East", right);
        add("West", left);
        top.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);
        left.setBackground(Color.DARK_GRAY);
        right.setBackground(Color.DARK_GRAY);
        gridRight.setBackground(Color.DARK_GRAY);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setResizable(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == check) {
            new CheckStock();
            super.dispose();
        } else if (e.getSource() == purchase) {
            new PurchaseItem(basketData);
            super.dispose();
        } else if (e.getSource() == stock) {
            String password = JOptionPane.showInputDialog("Enter Password");
            if (password == null) {
                System.out.println("null error"); // or leave blank
            } else if (password.equals("bigsales")) {
                new UpdateStock();
                super.dispose();
            } else {
                int decision = JOptionPane.showConfirmDialog(null, "Wrong Password!\n   Try again?", "Wrong Password", 0);
                if (decision == 0) {
                    stock.doClick();
                }
            }
        } else if (e.getSource() == quit) {
            StockData.close();
            System.exit(0);
        }
    }
}
