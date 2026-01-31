import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorKeypad {

    private final JPanel keypadPanel;
    private final String[] buttonLabels = {
        "7", "8", "9", "/",
        "6", "5", "4", "*",
        "3", "2", "1", "-",
        "0", "C", "=", "+"
    };

    public CalculatorKeypad(ActionListener listener) {
        keypadPanel = new JPanel(new GridLayout(4, 4, 5, 4));
        keypadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (String label : buttonLabels) {   // FIXED loop
            JButton button = new JButton(label);  // FIXED variable
            button.setFont(new Font("Arial", Font.BOLD, 17));
            button.addActionListener(listener);
            keypadPanel.add(button);
        }
    }

    public JPanel getKeypadPanel() {
        return keypadPanel;
    }
}