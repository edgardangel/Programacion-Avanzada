package tarea09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TipCalculator extends JFrame {
    private JTextField billAmountField, customTipField;
    private JLabel tipLabel, totalLabel;
    private JPanel standardPanel, customPanel;

    public TipCalculator() {
        setTitle("Tip Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        standardPanel = createStandardPanel();
        customPanel = createCustomPanel();

        add(standardPanel);
        setVisible(true);
    }

    private JPanel createStandardPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Standard Tip Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(102, 0, 204));
        panel.add(titleLabel);

        billAmountField = new JTextField();
        billAmountField.setHorizontalAlignment(JTextField.CENTER);
        billAmountField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(billAmountField);

        tipLabel = new JLabel("Tip: 0.00", SwingConstants.CENTER);
        totalLabel = new JLabel("Total: 0.00", SwingConstants.CENTER);
        tipLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(tipLabel);
        panel.add(totalLabel);

        JButton customCalcButton = new JButton("Use Custom Calculator");
        customCalcButton.setBackground(new Color(102, 0, 204));
        customCalcButton.setForeground(Color.WHITE);
        customCalcButton.setFont(new Font("Arial", Font.BOLD, 14));
        customCalcButton.addActionListener(e -> switchToCustomPanel());
        panel.add(customCalcButton);

        JButton calculateButton = new JButton("Calculate Tip (15%)");
        calculateButton.setBackground(new Color(51, 153, 255));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.addActionListener(e -> calculateTip(15));
        panel.add(calculateButton);

        return panel;
    }

    private JPanel createCustomPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Custom Tip Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(102, 0, 204));
        panel.add(titleLabel);

        billAmountField = new JTextField();
        billAmountField.setHorizontalAlignment(JTextField.CENTER);
        billAmountField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(billAmountField);

        customTipField = new JTextField("Enter Custom Tip %");
        customTipField.setHorizontalAlignment(JTextField.CENTER);
        customTipField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(customTipField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton tip15Button = new JButton("15% Tip");
        tip15Button.setBackground(new Color(51, 204, 51));
        tip15Button.setForeground(Color.WHITE);
        tip15Button.setFont(new Font("Arial", Font.BOLD, 14));
        tip15Button.addActionListener(e -> calculateTip(15));

        JButton tip20Button = new JButton("20% Tip");
        tip20Button.setBackground(new Color(255, 102, 0));
        tip20Button.setForeground(Color.WHITE);
        tip20Button.setFont(new Font("Arial", Font.BOLD, 14));
        tip20Button.addActionListener(e -> calculateTip(20));

        buttonPanel.add(tip15Button);
        buttonPanel.add(tip20Button);
        panel.add(buttonPanel);

        JButton customTipButton = new JButton("Apply Custom Tip");
        customTipButton.setBackground(new Color(0, 153, 153));
        customTipButton.setForeground(Color.WHITE);
        customTipButton.setFont(new Font("Arial", Font.BOLD, 14));
        customTipButton.addActionListener(e -> applyCustomTip());
        panel.add(customTipButton);

        JButton backButton = new JButton("Back to Standard Calculator");
        backButton.setBackground(new Color(102, 0, 204));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> switchToStandardPanel());
        panel.add(backButton);

        return panel;
    }

    private void calculateTip(double tipPercent) {
        try {
            double bill = Double.parseDouble(billAmountField.getText());
            double tip = bill * tipPercent / 100;
            double total = bill + tip;
            tipLabel.setText(String.format("Tip: %.2f", tip));
            totalLabel.setText(String.format("Total: %.2f", total));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid bill amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void applyCustomTip() {
        try {
            double tipPercent = Double.parseDouble(customTipField.getText());
            calculateTip(tipPercent);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid tip percentage.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void switchToCustomPanel() {
        remove(standardPanel);
        add(customPanel);
        revalidate();
        repaint();
    }

    private void switchToStandardPanel() {
        remove(customPanel);
        add(standardPanel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TipCalculator::new);
    }
}