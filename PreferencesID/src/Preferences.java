import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Preferences {


    private JPanel panel1;
    private JTextField textField1;
    private JCheckBox checkBox1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JSpinner spinner1;
    private JButton savePreferencesButton;


    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JCheckBox getCheckBox1() {
        return checkBox1;
    }

    public void setCheckBox1(JCheckBox checkBox1) {
        this.checkBox1 = checkBox1;
    }

    public JRadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(JRadioButton radioButton1) {
        this.radioButton1 = radioButton1;
    }

    public JRadioButton getRadioButton2() {
        return radioButton2;
    }

    public void setRadioButton2(JRadioButton radioButton2) {
        this.radioButton2 = radioButton2;
    }

    public JSpinner getSpinner1() {
        return spinner1;
    }

    public void setSpinner1(JSpinner spinner1) {
        this.spinner1 = spinner1;
    }

    public Preferences() {

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        savePreferencesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                int counter = 0;
                if (!textField1.equals(""))
                    s = "Name: " + textField1.getText() + '\n';
                if (checkBox1.isEnabled()) {
                    s += "Notifications selected: " + "Yes" + '\n';
                } else
                    s += "Notifications selected: " + "No" + '\n';


                if (radioButton1.isEnabled()) {
                    s += "Selected option: Option 1" + '\n';

                } else if (radioButton2.isEnabled())
                    s += "Selected option: Option 2" + '\n';

                s += "Number of elements: " + spinner1.getValue();

                JOptionPane.showMessageDialog(null, s);

            }
        });
    }
}
