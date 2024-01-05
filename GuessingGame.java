import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int secretNumber;
    private int numberOfGuesses;
    private JLabel messageLabel;
    private JTextField guessField;
    private JButton guessButton;

    public GuessingGame() {
        secretNumber = new Random().nextInt(100) + 1;
        numberOfGuesses = 0;

        setTitle("Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        messageLabel = new JLabel("Try to guess the secret number between 1 and 100.");
        guessField = new JTextField();
        guessButton = new JButton("Submit Guess");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(messageLabel);
        add(guessField);
        add(guessButton);

        setVisible(true);
    }

    private void checkGuess() {
        try {
            int guessedNumber = Integer.parseInt(guessField.getText());
            numberOfGuesses++;

            if (guessedNumber == secretNumber) {
                messageLabel.setText("Congratulations! You guessed the number " + secretNumber + " in " + numberOfGuesses + " guesses.");
                guessField.setEnabled(false);
                guessButton.setEnabled(false);
            } else if (guessedNumber < secretNumber) {
                messageLabel.setText("Too low. Try again.");
            } else {
                messageLabel.setText("Too high. Try again.");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessingGame();
            }
        });
    }
}
