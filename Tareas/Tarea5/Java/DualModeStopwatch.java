package tarea05;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DualModeStopwatch {

    private JFrame frame;
    private JLabel runningTimeLabel;
    private JLabel totalTimeLabel;
    private JTextField runningTimeField;
    private JTextField totalTimeField;
    private JButton startButton;
    private JButton resetButton;
    private JButton exitButton;
    private Timer runningTimer;
    private long startTime;
    private long runningStartTime;
    private long totalElapsedTime;
    private long runningElapsedTime;
    private boolean isRunning;

    public DualModeStopwatch() {
        frame = new JFrame("Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel timePanel = new JPanel();
        timePanel.setLayout(new GridLayout(2, 2, 5, 5));
        frame.add(timePanel, BorderLayout.CENTER);

        runningTimeLabel = new JLabel("Running Time:", SwingConstants.CENTER);
        totalTimeLabel = new JLabel("Total Time:", SwingConstants.CENTER);

        runningTimeField = new JTextField("00:00:00");
        runningTimeField.setEditable(false);
        runningTimeField.setHorizontalAlignment(JTextField.CENTER);
        runningTimeField.setFont(new Font("Monospaced", Font.BOLD, 20));

        totalTimeField = new JTextField("00:00:00");
        totalTimeField.setEditable(false);
        totalTimeField.setHorizontalAlignment(JTextField.CENTER);
        totalTimeField.setFont(new Font("Monospaced", Font.BOLD, 20));

        timePanel.add(runningTimeLabel);
        timePanel.add(runningTimeField);
        timePanel.add(totalTimeLabel);
        timePanel.add(totalTimeField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startButton = new JButton("Restart");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        resetButton.setEnabled(false);

        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        runningTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                runningElapsedTime = currentTime - runningStartTime;
                updateRunningTimeField();

                totalElapsedTime = currentTime - startTime;
                updateTotalTimeField();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    isRunning = true;
                    if (startTime == 0) {
                        startTime = System.currentTimeMillis();
                    }
                    runningStartTime = System.currentTimeMillis();
                    runningTimer.start();
                    startButton.setText("Stop");
                    resetButton.setEnabled(false);
                    exitButton.setEnabled(false);
                } else {
                    isRunning = false;
                    runningTimer.stop();
                    startButton.setText("Restart");
                    resetButton.setEnabled(true);
                    exitButton.setEnabled(true);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
                runningTimer.stop();
                startTime = 0;
                runningStartTime = 0;
                totalElapsedTime = 0;
                runningElapsedTime = 0;
                updateRunningTimeField();
                updateTotalTimeField();
                resetButton.setEnabled(false);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private void updateRunningTimeField() {
        runningTimeField.setText(formatTime(runningElapsedTime));
    }

    private void updateTotalTimeField() {
        totalTimeField.setText(formatTime(totalElapsedTime));
    }

    private String formatTime(long timeInMillis) {
        long seconds = (timeInMillis / 1000) % 60;
        long minutes = (timeInMillis / (1000 * 60)) % 60;
        long hours = timeInMillis / (1000 * 60 * 60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DualModeStopwatch());
    }
}

