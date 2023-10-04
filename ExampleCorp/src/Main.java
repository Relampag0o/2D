import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Save preferences");
        f.getContentPane().add(new ExampleCorpFORM().getPanel1());
        f.setSize(300,300);
        f.setVisible(true);
    }
}