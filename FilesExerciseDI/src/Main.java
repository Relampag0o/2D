import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        JFrame frame = new JFrame("Jmenu example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");

        frame.setVisible(true);

        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(openItem);
        fileMenu.add(exitItem);


        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int val = jFileChooser.showOpenDialog(frame);
                   if (val==1){
                       
                   }

            }
        });


    }
}