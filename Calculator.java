import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener {
    private static JFrame frame;
    private static JTextField display;
    private static JButton zero, one, two, three, four, five, six, seven, eight, nine;
    private static JButton add, sub, mult, div, eq, clr, dec, del, neg;
    private char operator;
    private double operand1, operand2, result;
    private boolean negReminder = false;



   public Calculator() {

    frame = new JFrame("Calculator");
    JPanel panel = new JPanel();
    frame.setSize(270,350);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    panel.setLayout(null);


    // DISPLAY BOX //
    display = new JTextField();
    display.setBounds(10, 30, 250, 50);
    display.setHorizontalAlignment(JTextField.RIGHT);
    display.setEditable(false);
    panel.add(display);

    
    // DIGIT BUTTONS //
    nine = new JButton("9");
    nine.setBounds(110,120,50,50);
    nine.addActionListener(this);
    panel.add(nine);

    eight = new JButton("8");
    eight.setBounds(60,120,50,50);
    eight.addActionListener(this);
    panel.add(eight);

    seven = new JButton("7");
    seven.setBounds(10,120,50,50);
    seven.addActionListener(this);
    panel.add(seven);

    six = new JButton("6");
    six.setBounds(110,170,50,50);
    six.addActionListener(this);
    panel.add(six);

    five = new JButton("5");
    five.setBounds(60,170,50,50);
    five.addActionListener(this);
    panel.add(five);

    four = new JButton("4");
    four.setBounds(10,170,50,50);
    four.addActionListener(this);
    panel.add(four);

    three = new JButton("3");
    three.setBounds(110,220,50,50);
    three.addActionListener(this);
    panel.add(three);

    two = new JButton("2");
    two.setBounds(60,220,50,50);
    two.addActionListener(this);
    panel.add(two);

    one = new JButton("1");
    one.setBounds(10,220,50,50);
    one.addActionListener(this);
    panel.add(one);

    zero = new JButton("0");
    zero.setBounds(60,270,50,50);
    zero.addActionListener(this);
    panel.add(zero);

    // OPERATION BUTTONS //
    add = new JButton("+");
    add.setBounds(160,270,50,50);
    add.addActionListener(this);
    panel.add(add);

    sub = new JButton("-");
    sub.setBounds(160,220,50,50);
    sub.addActionListener(this);
    panel.add(sub);

    mult = new JButton("x");
    mult.setBounds(160,170,50,50);
    mult.addActionListener(this);
    panel.add(mult);

    div = new JButton("/");
    div.setBounds(210,170,50,50);
    div.addActionListener(this);
    panel.add(div);

    // ACTION BUTTONS // 
    clr = new JButton("C");
    clr.setBounds(10,270,50,50);
    clr.addActionListener(this);
    panel.add(clr);

    eq = new JButton("=");
    eq.setBounds(210,220,50,100);
    eq.addActionListener(this);
    panel.add(eq);

    dec = new JButton(".");
    dec.setBounds(110,270,50,50);
    dec.addActionListener(this);
    panel.add(dec);

    del = new JButton("DEL");
    del.setBounds(210,120,50,50);
    del.addActionListener(this);
    panel.add(del);

    neg = new JButton("(-)");
    neg.setBounds(160,120,50,50);
    neg.addActionListener(this);
    panel.add(neg);

   }



    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();
        frame.setVisible(true);
    }



    // HANDLES BUTTON ACTION //
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();

        // IF DIGIT BUTTON IS PRESSED //
        for (int i=0; i<10; i++) {
            if (str.equals(String.valueOf(i))) {
                display.setText(display.getText().concat(str));
                if (negReminder == true) {      // IF (-) WAS PRESSED BEFORE A DIGIT //
                    int tempnum = Integer.valueOf(display.getText());
                    tempnum *= -1;
                    display.setText(String.valueOf(tempnum));
                    negReminder = false;
                }
            }
        }
        // IF OPERATION BUTTON IS PRESSED
        if (str.equals("+")) {
            if (operand1 == 0.0) {
                operator = '+';
                operand1 = Double.parseDouble(display.getText());
                display.setText("");
            }
            else { // if operand1 has a value //
                operand2 = Double.parseDouble(display.getText());
                operand1 = Calculate(operand1, operand2, operator);
                operand2 = 0;
                operator = '+';
                display.setText("");
            }
        }
        if (str.equals("-")) {
            if (operand1 == 0.0) {
                operator = '-';
                operand1 = Double.parseDouble(display.getText());
                display.setText("");
            }
            else { // if operand1 has a value //
                operand2 = Double.parseDouble(display.getText());
                operand1 = Calculate(operand1, operand2, operator);
                operand2 = 0;
                operator = '-';
                display.setText("");
            }
        }
        if (str.equals("x")) {
            if (operand1 == 0.0) {
                operator = 'x';
                operand1 = Double.parseDouble(display.getText());
                display.setText("");
            }
            else { // if operand1 has a value //
                operand2 = Double.parseDouble(display.getText());
                operand1 = Calculate(operand1, operand2, operator);
                operand2 = 0;
                operator = 'x';
                display.setText("");
            }
        }
        if (str.equals("/")) {
            if (operand1 == 0.0) {
                operator = '/';
                operand1 = Double.parseDouble(display.getText());
                display.setText("");
            }
            else { // if operand1 has a value //
                operand2 = Double.parseDouble(display.getText());
                operand1 = Calculate(operand1, operand2, operator);
                operand2 = 0;
                operator = '/';
                display.setText("");
            }
        }

        // IF ACTION BUTTON IS PRESSED //
        if (str.equals("=")) {  // EQ
            if (!display.getText().equals("")){
                operand2 = Double.parseDouble(display.getText());
                result = Calculate(operand1, operand2, operator);
                display.setText(String.valueOf(result));
                operand1=0.0;
                operand2=0.0;
            }
            else {      // IF NO IMPUT WAS GIVEN BEFORE EQ //
                operand2 = 0;
                result = Calculate(operand1, operand2, operator);
                display.setText(String.valueOf(result));
                operand1=0.0;
            }
            
        }
        if (str.equals("C")) {    // CLEAR
            operand1 = 0;
            operand2 = 0;
            display.setText("");
        }

        if (str.equals(".")) {     // DEC
            display.setText(display.getText().concat(str));
        }
        
        if (str.equals("DEL")) {    // DEL
            String DELstr = display.getText();
            display.setText("");
            for (int i=0; i<DELstr.length()-1;i++) {
                display.setText(display.getText()+DELstr.charAt(i));
            }
        }

        if (str.equals("(-)")) {    //NEG
            if (!display.getText().equals("")) {
                double tempnum = Double.parseDouble(display.getText());
                tempnum *= -1;
                display.setText(String.valueOf(tempnum));
            }
            else {
                negReminder = true;
            }
        }
    }



    private double Calculate(double op1, double op2, char operation) {
        if (operation == '+') {
            return (op1 + op2);
        }
        if (operation == '-') {
            return (op1 - op2);
        }
        if (operation == 'x') {
            return (op1 * op2);
        }
        if (operation == '/') {
            return (op1 / op2);
        }
        else {
            return 0;
        }
    }

}

