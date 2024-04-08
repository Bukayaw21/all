
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CalculatorFrame frame = new CalculatorFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class CalculatorFrame extends JFrame {
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private JButton btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals;
    private JButton btnClear, btnDot, btnSin, btnCos, btnTan, btnHex, btnOct, btnBin,btnBack;
    private JTextField display;

    private double currentValue = 0;
    private String currentOperator = "";

    public CalculatorFrame() {
        setTitle("Calculator");
        setSize(300, 300);
        setResizable(false);
       getContentPane().setBackground(Color.darkGray); 

        initComponents();
        addComponents();

        btn0.addActionListener(new ButtonClickListener());
        btn1.addActionListener(new ButtonClickListener());
        btn2.addActionListener(new ButtonClickListener());
        btn3.addActionListener(new ButtonClickListener());
        btn4.addActionListener(new ButtonClickListener());
        btn5.addActionListener(new ButtonClickListener());
        btn6.addActionListener(new ButtonClickListener());
        btn7.addActionListener(new ButtonClickListener());
        btn8.addActionListener(new ButtonClickListener());
        btn9.addActionListener(new ButtonClickListener());
        btnAdd.addActionListener(new ButtonClickListener());
        btnSubtract.addActionListener(new ButtonClickListener());
        btnMultiply.addActionListener(new ButtonClickListener());
        btnDivide.addActionListener(new ButtonClickListener());
        btnEquals.addActionListener(new ButtonClickListener());
        btnClear.addActionListener(new ButtonClickListener());
        btnDot.addActionListener(new ButtonClickListener());
        btnSin.addActionListener(new ButtonClickListener());
        btnCos.addActionListener(new ButtonClickListener());
        btnTan.addActionListener(new ButtonClickListener());
        btnHex.addActionListener(new ButtonClickListener());
        btnOct.addActionListener(new ButtonClickListener());
        btnBin.addActionListener(new ButtonClickListener());
        btnBack.addActionListener(new ButtonClickListener());
    }

    private void initComponents() {
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        btn0 = new JButton("0");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");

        btnAdd = new JButton("+");
        btnSubtract = new JButton("-");
        btnMultiply = new JButton("*");
        btnDivide = new JButton("/");
        btnEquals = new JButton("=");

        btnClear = new JButton("C");
        btnDot = new JButton(".");
        btnSin = new JButton("sin");
        btnCos = new JButton("cos");
        btnTan = new JButton("tan");
        btnHex = new JButton("Hex");
        btnOct = new JButton("Oct");
        btnBin = new JButton("Bin");
        btnBack = new JButton("<-");
    }

    private void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(display, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(btn7, gbc);
        gbc.gridx = 1;
        add(btn8, gbc);
        gbc.gridx = 2;
        add(btn9, gbc);
        gbc.gridx = 3;
        add(btnAdd, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(btn4, gbc);
        gbc.gridx = 1;
        add(btn5, gbc);
        gbc.gridx = 2;
        add(btn6, gbc);
        gbc.gridx = 3;
        add(btnSubtract, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(btn1, gbc);
        gbc.gridx = 1;
        add(btn2, gbc);
        gbc.gridx = 2;
        add(btn3, gbc);
        gbc.gridx = 3;
        add(btnMultiply, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(btnDot, gbc);
        gbc.gridx = 1;
        add(btn0, gbc);
        gbc.gridx = 2;
        add(btnEquals, gbc);
        gbc.gridx = 3;
        add(btnDivide, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        add(btnClear, gbc);
        gbc.gridx = 1;
        add(btnSin, gbc);
        gbc.gridx = 2;
        add(btnCos, gbc);
        gbc.gridx = 3;
        add(btnTan, gbc);
        
        gbc.gridy = 6;
        gbc.gridx = 0;
        add(btnHex, gbc);
        gbc.gridx = 1;
        add(btnOct, gbc);
        gbc.gridx = 2;
        add(btnBin, gbc);
        gbc.gridx = 3;
        add(btnBack, gbc);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton source = (JButton) event.getSource();
            String buttonText = source.getText();

            switch (buttonText) {
                case "+":
                case "-":
                case "*":
                case "/":
                    currentOperator = buttonText;
                    currentValue = Double.parseDouble(display.getText());
                    display.setText("");
                    break;
                case "=":
                    double result = 0;
                    double operand = Double.parseDouble(display.getText());

                    switch (currentOperator) {
                        case "+":
                            result = currentValue + operand;
                            break;
                        case "-":
                            result = currentValue - operand;
                            break;
                        case "*":
                            result = currentValue * operand;
                            break;
                        case "/":
                            result = currentValue / operand;
                            break;
                    }

                    display.setText(Double.toString(result));
                    currentValue = 0;
                    currentOperator = "";
                    break;
                case "C":
                    display.setText("");
                    break;
                case "sin":
                    double angle = Double.parseDouble(display.getText());
                    result = Math.sin(Math.toRadians(angle));
                    display.setText(Double.toString(result));
                    break;
                case "cos":
                    angle = Double.parseDouble(display.getText());
                    result = Math.cos(Math.toRadians(angle));
                    display.setText(Double.toString(result));
                    break;
                case "tan":
                    angle = Double.parseDouble(display.getText());
                    result = Math.tan(Math.toRadians(angle));
                    display.setText(Double.toString(result));
                    break;
                case "Hex":
                    int decimalValue = Integer.parseInt(display.getText());
                    display.setText(Integer.toHexString(decimalValue));
                    break;
                case "Oct":
                    decimalValue = Integer.parseInt(display.getText());
                    display.setText(Integer.toOctalString(decimalValue));
                    break;
                case "Bin":
                    decimalValue = Integer.parseInt(display.getText());
                    display.setText(Integer.toBinaryString(decimalValue));
                    break;
                default:
                    display.setText(display.getText() + buttonText);
                    break;
            }
        }
    }
}
