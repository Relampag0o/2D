import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Save preferences");
        f.getContentPane().add(new ExampleCorpFORM().getPanel1());
        f.setSize(460, 450);
        f.setVisible(true);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuNavegacion = new JMenu("Home");


        JMenuItem menuItemInicio = new JMenuItem("Home");
        JMenuItem menuItemServicio = new JMenuItem("Services");
        JMenuItem menuItemAcercaDe = new JMenuItem("About us");
        JMenuItem menuItemContacto = new JMenuItem("Contact");

        menuNavegacion.add(menuItemInicio);
        menuNavegacion.add(menuItemServicio);
        menuNavegacion.add(menuItemAcercaDe);
        menuNavegacion.add(menuItemContacto);


        menuBar.add(menuNavegacion);

        f.setJMenuBar(menuBar);

        f.setVisible(true);
    }
}