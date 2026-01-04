import java.awt.*;
import java.awt.event.*;

public class CalculatorAWT extends Frame implements ActionListener {

    TextField display;
    Button[] num = new Button[10];
    Button addBtn, subBtn, mulBtn, divBtn, eqBtn;
    Button clrBtn, dotBtn, backBtn;

    double n1 = 0, n2 = 0;
    String op = "";

    CalculatorAWT() {

        setLayout(null);
        setSize(380, 520);
        setTitle("Calculator");
        setBackground(new Color(245, 245, 245));

        display = new TextField();
        display.setBounds(30, 50, 300, 50);
        display.setFont(new Font("Segoe UI", Font.BOLD, 26));
        display.setEditable(false);
        add(display);

        Font f = new Font("Segoe UI", Font.BOLD, 16);

        // Numbers (center grid)
        int x = 30, y = 120;
        for (int i = 1; i <= 9; i++) {
            num[i] = new Button(String.valueOf(i));
            num[i].setBounds(x, y, 70, 50);
            num[i].setFont(f);
            add(num[i]);
            num[i].addActionListener(this);

            x += 80;
            if (i % 3 == 0) {
                x = 30;
                y += 60;
            }
        }

        // 0 and dot
        num[0] = new Button("0");
        num[0].setBounds(30, y, 150, 50);
        num[0].setFont(f);
        add(num[0]);
        num[0].addActionListener(this);

        dotBtn = new Button(".");
        dotBtn.setBounds(190, y, 70, 50);
        dotBtn.setFont(f);
        add(dotBtn);
        dotBtn.addActionListener(this);

        // Operators (single right column)
        int ox = 270, oy = 120;
        addBtn = new Button("+");
        subBtn = new Button("-");
        mulBtn = new Button("*");
        divBtn = new Button("/");
        eqBtn  = new Button("=");

        Button[] ops = { addBtn, subBtn, mulBtn, divBtn, eqBtn };

        for (Button b : ops) {
            b.setBounds(ox, oy, 70, 50);
            b.setFont(f);
            b.setBackground(new Color(180, 180, 180));
            add(b);
            b.addActionListener(this);
            oy += 60;
        }

        // Clear & Backspace (top right)
        clrBtn = new Button("C");
        clrBtn.setBounds(270, 60, 70, 40);
        clrBtn.setFont(f);
        clrBtn.setBackground(Color.RED);
        clrBtn.setForeground(Color.WHITE);
        add(clrBtn);
        clrBtn.addActionListener(this);

        backBtn = new Button("⌫");
        backBtn.setBounds(190, 60, 70, 40);
        backBtn.setFont(f);
        add(backBtn);
        backBtn.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();

        if (s.matches("[0-9]")) {
            display.setText(display.getText() + s);
        }

        else if (s.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }

        else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            if (!display.getText().equals("")) {
                n1 = Double.parseDouble(display.getText());
                op = s;
                display.setText("");
            }
        }

        else if (s.equals("=")) {
            if (display.getText().equals("")) return;
            n2 = Double.parseDouble(display.getText());

            switch (op) {
                case "+": display.setText("" + (n1 + n2)); break;
                case "-": display.setText("" + (n1 - n2)); break;
                case "*": display.setText("" + (n1 * n2)); break;
                case "/":
                    display.setText(n2 == 0 ? "Error" : "" + (n1 / n2));
            }
            op = "";
        }

        else if (s.equals("C")) {
            display.setText("");
            n1 = n2 = 0;
            op = "";
        }

        else if (s.equals("⌫")) {
            String t = display.getText();
            if (t.length() > 0)
                display.setText(t.substring(0, t.length() - 1));
        }
    }

    public static void main(String[] args) {
        new CalculatorAWT();
    }
}
