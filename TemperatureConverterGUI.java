import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Temperature Converter");
        add(titleLabel);

        inputField = new JTextField(10);
        add(inputField);

        JButton celsiusToFahrenheitButton = new JButton("Celsius to Fahrenheit");
        celsiusToFahrenheitButton.addActionListener(new ConvertButtonListener(true));
        add(celsiusToFahrenheitButton);

        JButton fahrenheitToCelsiusButton = new JButton("Fahrenheit to Celsius");
        fahrenheitToCelsiusButton.addActionListener(new ConvertButtonListener(false));
        add(fahrenheitToCelsiusButton);

        resultLabel = new JLabel();
        add(resultLabel);

        pack();
        setLocationRelativeTo(null);
    }

    private class ConvertButtonListener implements ActionListener {
        private boolean celsiusToFar;

        public ConvertButtonListener(boolean celsiusToFar) {
            this.celsiusToFar = celsiusToFar;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double inputValue = Double.parseDouble(inputField.getText());
                double result;

                if (celsiusToFar) {
                    result = (inputValue * 9/5) + 32;
                    resultLabel.setText("Result: " + result + " °F");
                } else {
                    result = (inputValue - 32) * 5/9;
                    resultLabel.setText("Result: " + result + " °C");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverterGUI converter = new TemperatureConverterGUI();
            converter.setVisible(true);
        });
    }
}
