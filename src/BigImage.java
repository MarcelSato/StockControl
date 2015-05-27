
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BigImage extends JFrame implements ActionListener {

    String imageLocation;
    JButton check = new JButton();

    public BigImage() {
    }

    public void actionPerformed(ActionEvent e) {
        super.dispose();
    }

    void bigImage(String a) {
        imageLocation = a;
    }

    void showImage() {
        Image myImage = getToolkit().createImage(imageLocation);
        ImageIcon myIcon = new ImageIcon(myImage);
        setLayout(new BorderLayout());
        setSize(700, 750);
        setLocationRelativeTo(null);
        setTitle("Click on the image to close window");
        this.setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel();
        top.add(check);
        check.setBorder(null);
        check.addActionListener(this);
        check.setBackground(Color.DARK_GRAY);
        check.setIcon(myIcon);
        add("North", top);
        JPanel middle = new JPanel();
        add("Center", middle);
        setResizable(false);
        setVisible(true);
    }
}
