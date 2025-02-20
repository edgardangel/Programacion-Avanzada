package tarea012;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class LoanAssistant extends JFrame {
    private JTextField balanceTextField, interestTextField, monthsTextField, paymentTextField;
    private JTextArea analysisTextArea;
    private JButton computeButton, newLoanButton, monthsButton, paymentButton, exitButton;
    private boolean computePayment;
    private Color lightYellow = new Color(255, 255, 128);
    
    public LoanAssistant() {
        setTitle("Loan Assistant");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints;
        
        Font myFont = new Font("Arial", Font.PLAIN, 16);
        
        JLabel balanceLabel = new JLabel("Loan Balance");
        balanceLabel.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        add(balanceLabel, gridConstraints);
        
        balanceTextField = new JTextField(10);
        balanceTextField.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        add(balanceTextField, gridConstraints);
        
        JLabel interestLabel = new JLabel("Interest Rate");
        interestLabel.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        add(interestLabel, gridConstraints);
        
        interestTextField = new JTextField(10);
        interestTextField.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        add(interestTextField, gridConstraints);
        
        JLabel monthsLabel = new JLabel("Number of Payments");
        monthsLabel.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.anchor = GridBagConstraints.WEST;
        add(monthsLabel, gridConstraints);
        
        monthsTextField = new JTextField(10);
        monthsTextField.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        add(monthsTextField, gridConstraints);
        
        JLabel paymentLabel = new JLabel("Monthly Payment");
        paymentLabel.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.anchor = GridBagConstraints.WEST;
        add(paymentLabel, gridConstraints);
        
        paymentTextField = new JTextField(10);
        paymentTextField.setFont(myFont);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 3;
        add(paymentTextField, gridConstraints);
        
        computeButton = new JButton("Compute Monthly Payment");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        gridConstraints.gridwidth = 2;
        add(computeButton, gridConstraints);
        
        newLoanButton = new JButton("New Loan Analysis");
        newLoanButton.setEnabled(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        gridConstraints.gridwidth = 2;
        add(newLoanButton, gridConstraints);
        
        analysisTextArea = new JTextArea(10, 30);
        analysisTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        analysisTextArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        analysisTextArea.setEditable(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 1;
        gridConstraints.gridheight = 4;
        add(analysisTextArea, gridConstraints);
        
        exitButton = new JButton("Exit");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 5;
        add(exitButton, gridConstraints);
        
        computeButton.addActionListener(e -> computeLoan());
        newLoanButton.addActionListener(e -> newLoanAnalysis());
        exitButton.addActionListener(e -> System.exit(0));
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void computeLoan() {
        double balance = Double.parseDouble(balanceTextField.getText());
        double interest = Double.parseDouble(interestTextField.getText());
        int months = Integer.parseInt(monthsTextField.getText());
        double monthlyInterest = interest / 1200;
        
        double multiplier = Math.pow(1 + monthlyInterest, months);
        double payment = balance * monthlyInterest * multiplier / (multiplier - 1);
        
        paymentTextField.setText(new DecimalFormat("0.00").format(payment));
        analysisTextArea.setText("Loan Analysis:\n");
        analysisTextArea.append("Loan Balance: $" + new DecimalFormat("0.00").format(balance) + "\n");
        analysisTextArea.append("Interest Rate: " + interest + "%\n");
        analysisTextArea.append("Monthly Payment: $" + new DecimalFormat("0.00").format(payment) + "\n");
    }
    
    private void newLoanAnalysis() {
        paymentTextField.setText("");
        analysisTextArea.setText("");
        computeButton.setEnabled(true);
        newLoanButton.setEnabled(false);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoanAssistant().setVisible(true));
    }
}