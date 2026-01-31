import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {

    private final CalculatorDisplay display;
    private final CalculatorLogic logic;
    private final CalculatorKeypad keypad;

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new CalculatorDisplay();
        logic = new CalculatorLogic();
        keypad = new CalculatorKeypad(new ButtonClickListener());

        add(display.getDisplayPanel(), BorderLayout.NORTH);
        add(keypad.getKeypadPanel(), BorderLayout.CENTER);

        setSize(350, 450);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // Button listener
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            processCommand(command);
        }
    }

    private void processCommand(String command) {
        switch (command) {

            case "C":
                logic.clear();
                display.clear();
                break;

            case "=":
                try {
                    double result = logic.calculateResult();
                    display.setDisplay(String.valueOf(result));
                } catch (ArithmeticException e) {
                    display.setDisplay("Error");
                    logic.clear();
                }
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                logic.setOperator(command);
                logic.setNewNumber(true);
                break;

            default: // numbers
                if (logic.isNewNumber()) {
                    display.setDisplay(command);
                    logic.setNewNumber(false);
                } else {
                    display.appendToDisplay(command);
                }
                logic.setCurrentNumber(Double.parseDouble(display.getText()));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}