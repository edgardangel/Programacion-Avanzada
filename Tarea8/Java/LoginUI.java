package tarea08;

import javax.swing.*;
import java.awt.*;

public class LoginUI {
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(null);
        
        // Panel izquierdo
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(255, 140, 0));
        leftPanel.setBounds(0, 0, 180, 300);
        leftPanel.setLayout(null);
        
        JLabel labelTitle = new JLabel("FamilyPoint");
        labelTitle.setForeground(Color.WHITE);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitle.setBounds(30, 50, 120, 30);
        leftPanel.add(labelTitle);
        
        JLabel labelSub1 = new JLabel("Every Product");
        labelSub1.setForeground(Color.WHITE);
        labelSub1.setFont(new Font("Arial", Font.PLAIN, 12));
        labelSub1.setBounds(30, 80, 120, 30);
        leftPanel.add(labelSub1);
        
        JLabel labelSub2 = new JLabel("Nice Service");
        labelSub2.setForeground(Color.WHITE);
        labelSub2.setFont(new Font("Arial", Font.PLAIN, 12));
        labelSub2.setBounds(30, 100, 120, 30);
        leftPanel.add(labelSub2);
        
        // Panel derecho
        JLabel labelLogin = new JLabel("LOGIN");
        labelLogin.setFont(new Font("Arial", Font.BOLD, 18));
        labelLogin.setBounds(300, 10, 100, 30);
        frame.add(labelLogin);
        
        JLabel labelRole = new JLabel("Select Role");
        labelRole.setBounds(200, 60, 100, 20);
        frame.add(labelRole);
        
        String[] roles = {"Admin", "User"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setBounds(300, 60, 120, 20);
        frame.add(roleBox);
        
        JLabel labelUID = new JLabel("UID");
        labelUID.setBounds(200, 100, 100, 20);
        frame.add(labelUID);
        
        JTextField textUID = new JTextField();
        textUID.setBounds(300, 100, 120, 20);
        frame.add(textUID);
        
        JLabel labelPassword = new JLabel("PASSWORD");
        labelPassword.setBounds(200, 140, 100, 20);
        frame.add(labelPassword);
        
        JPasswordField textPassword = new JPasswordField();
        textPassword.setBounds(300, 140, 120, 20);
        frame.add(textPassword);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(230, 200, 100, 30);
        frame.add(loginButton);
        
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(340, 200, 100, 30);
        frame.add(clearButton);
        
        frame.add(leftPanel);
        frame.setVisible(true);
    }
}
