import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch extends JFrame {
    private long startTime;
    private boolean isRunning;
    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    public Stopwatch() {
        setTitle("Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        timeLabel = new JLabel("00:00:00.000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(timeLabel);
        add(buttonPanel);

        setVisible(true);
    }

    private void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            resetButton.setEnabled(false);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        long currentTime = System.currentTimeMillis() - startTime;
                        updateTimeLabel(currentTime);
                    }
                }
            });
            thread.start();
        }
    }

    private void stop() {
        if (isRunning) {
            isRunning = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            resetButton.setEnabled(true);
        }
    }

    private void reset() {
        stop();
        updateTimeLabel(0);
    }

    private void updateTimeLabel(long currentTime) {
        long hours = currentTime / 3600000;
        long minutes = (currentTime % 3600000) / 60000;
        long seconds = (currentTime % 60000) / 1000;
        long milliseconds = currentTime % 1000;

        String timeText = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
        timeLabel.setText(timeText);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               Stopwatch sw =  new Stopwatch();
			   sw.setLocationRelativeTo(null);
            }
        });
    }
}
