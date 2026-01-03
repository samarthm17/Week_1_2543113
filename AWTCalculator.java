import java.awt.*;
import java.awt.event.*;

public class AWTCalculatorAligned extends Frame implements ActionListener {

    TextField t1, t2, t3;
    Button add, sub, mul, div, clr;

    AWTCalculatorAligned() {

        setTitle("AWT Calculator");
        setSize(450, 280);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // TextFields
        t1 = new TextField(20);
        t2 = new TextField(20);
        t3 = new TextField(20);
        t3.setEditable(false);

        // Buttons
        add = new Button("+");
        sub = new Button("-");
        mul = new Button("*");
        div = new Button("/");
        clr = new Button("Clear");

        // ----- Row 1 -----
        gbc.gridx = 0; gbc.gridy = 0;
        add(new Label("First Number :"), gbc);

        gbc.gridx = 1;
        add(t1, gbc);

        // ----- Row 2 -----
        gbc.gridx = 0; gbc.gridy = 1;
        add(new Label("Second Number:"), gbc);

        gbc.gridx = 1;
        add(t2, gbc);

        // ----- Row 3 (Buttons) -----
        Panel p = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        p.add(add);
        p.add(sub);
        p.add(mul);
        p.add(div);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(p, gbc);
        gbc.gridwidth = 1;

        // ----- Row 4 -----
        gbc.gridx = 0; gbc.gridy = 3;
        add(new Label("Result       :"), gbc);

        gbc.gridx = 1;
        add(t3, gbc);

        // ----- Row 5 -----
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(clr, gbc);

        // Action Listeners
        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        clr.addActionListener(this);

        // Close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == clr) {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            return;
        }

        try {
            double a = Double.parseDouble(t1.getText());
            double b = Double.parseDouble(t2.getText());
            double result = 0;

            if (e.getSource() == add)
                result = a + b;
            else if (e.getSource() == sub)
                result = a - b;
            else if (e.getSource() == mul)
                result = a * b;
            else if (e.getSource() == div) {
                if (b == 0)
                    throw new ArithmeticException("Divide by zero");
                result = a / b;
            }

            t3.setText(String.valueOf(result));
        }
        catch (ArithmeticException ex) {
            t3.setText("Error: Divide by Zero");
        }
        catch (NumberFormatException ex) {
            t3.setText("Invalid Input");
        }
    }

    public static void main(String[] args) {
        new AWTCalculatorAligned();
    }
}
