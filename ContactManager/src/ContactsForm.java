import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsForm {


    private JPanel panel1;
    private JTextField nameText;
    private JTextField emailText;
    private JTable table1;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel nameLabel;
    private JLabel emailLabel;

    private DefaultTableModel tablemodel;


    public ContactsForm() {



        tablemodel = new DefaultTableModel();
        tablemodel.addColumn("Name");
        tablemodel.addColumn("Email");

        table1.setModel(tablemodel);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!nameText.getText().isEmpty() && !emailText.getText().isEmpty()) {
                    String[] v = new String[2];
                    v[0] = nameText.getText();
                    v[1] = emailText.getText();


                    tablemodel.addRow(v);
                }

                nameText.setText("");
                emailText.setText("");

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.requestFocus();
                if (!nameText.getText().isEmpty() && !emailText.getText().isEmpty()) {

                    String[] v = new String[2];
                    v[0] = nameText.getText();
                    v[1] = emailText.getText();

                    tablemodel.setValueAt(v[0], table1.getSelectedRow(), 0);
                    tablemodel.setValueAt(v[1], table1.getSelectedRow(), 1);

                    nameText.setText("");
                    emailText.setText("");
                }


            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table1.getSelectedRow();

                if (index != -1)
                    tablemodel.removeRow(index);

                nameText.setText("");
                emailText.setText("");

            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }


}
