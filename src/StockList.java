
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class StockList extends JFrame implements ActionListener {

    private JTextArea information = new JTextArea(40, 18);
    private JButton exit = new JButton("Exit");
    private BasketData basketData;
//    private BufferedReader inFile;

    public StockList(final BasketData basketData) {
        this.basketData = basketData;
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/others/b.png"));
        Border newBorder = BorderFactory.createRaisedBevelBorder();

        setLayout(new BorderLayout());
        setSize(234, 720);
        setLocationRelativeTo(null);
        setTitle("Products List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        JScrollPane scroll = new JScrollPane(information);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel middle = new JPanel();
        JPanel bottom = new JPanel();
        information.setText("All Products\n\n");
        information.setEditable(false);
//        middle.add(information);
        middle.add(scroll);
        bottom.add(exit);
        exit.addActionListener(this);
        exit.setBorder(newBorder);
        exit.setBackground(Color.DARK_GRAY);
        exit.setForeground(Color.white);
        exit.setMnemonic(KeyEvent.VK_E);
        add("Center", middle);
        add("South", bottom);
        middle.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);

        for (int i = 1; i < 100; i++) {
            String key = "" + i;
            String name = StockData.getName(key);
            if (name == null){
                
            } else {
            information.append(key + " - " + name + "\n");
            }
        }
        scroll.setViewportView(information);
        information.setCaretPosition(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            super.dispose();
        }
    }
}
