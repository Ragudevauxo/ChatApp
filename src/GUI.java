import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JPanel panel;
    private JButton startchat;

    public GUI() {
        startchat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         JOptionPane.showMessageDialog(null,"Done");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat APP");
        frame.setContentPane(new GUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400,400);

    }

}
