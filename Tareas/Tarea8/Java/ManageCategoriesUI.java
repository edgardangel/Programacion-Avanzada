package tarea08;
import javax.swing.*;
import java.awt.*;

public class ManageCategoriesUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Manage Categories");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(null);
        
        // Panel izquierdo
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(255, 140, 0));
        leftPanel.setBounds(0, 0, 150, 400);
        leftPanel.setLayout(null);
        
        JLabel labelProducts = new JLabel("Products");
        labelProducts.setForeground(Color.WHITE);
        labelProducts.setFont(new Font("Arial", Font.BOLD, 16));
        labelProducts.setBounds(30, 50, 120, 30);
        leftPanel.add(labelProducts);
        
        JLabel labelSeller = new JLabel("Seller");
        labelSeller.setForeground(Color.WHITE);
        labelSeller.setFont(new Font("Arial", Font.PLAIN, 12));
        labelSeller.setBounds(30, 80, 120, 30);
        leftPanel.add(labelSeller);
        
        // Panel derecho - Gestión de categorías
        JLabel labelManage = new JLabel("MANAGE CATEGORIES");
        labelManage.setFont(new Font("Arial", Font.BOLD, 18));
        labelManage.setBounds(250, 10, 200, 30);
        frame.add(labelManage);
        
        JLabel labelCatID = new JLabel("CATID");
        labelCatID.setBounds(160, 50, 100, 20);
        frame.add(labelCatID);
        
        JTextField textCatID = new JTextField();
        textCatID.setBounds(230, 50, 100, 20);
        frame.add(textCatID);
        
        JLabel labelName = new JLabel("NAME");
        labelName.setBounds(350, 50, 100, 20);
        frame.add(labelName);
        
        JTextField textName = new JTextField();
        textName.setBounds(420, 50, 100, 20);
        frame.add(textName);
        
        JLabel labelDescription = new JLabel("DESCRIPTION");
        labelDescription.setBounds(160, 80, 100, 20);
        frame.add(labelDescription);
        
        JTextField textDescription = new JTextField();
        textDescription.setBounds(230, 80, 290, 20);
        frame.add(textDescription);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(180, 120, 80, 30);
        frame.add(addButton);
        
        JButton editButton = new JButton("Edit");
        editButton.setBounds(270, 120, 80, 30);
        frame.add(editButton);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(360, 120, 80, 30);
        frame.add(deleteButton);
        
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(450, 120, 80, 30);
        frame.add(clearButton);
        
        // Tabla de categorías
        JLabel labelCatList = new JLabel("CATEGORIES LIST");
        labelCatList.setFont(new Font("Arial", Font.BOLD, 14));
        labelCatList.setBounds(250, 170, 200, 30);
        frame.add(labelCatList);
        
        String[] columnNames = {"CATID", "CATNAME", "CATDESC"};
        Object[][] data = {
            {1, "BEVERAGE", "All kind of drinks"},
            {2, "Dairy", "Nice Dairy to enjoy"},
            {3, "Meat", "Excellent meat"}
        };
        JTable categoryTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(categoryTable);
        scrollPane.setBounds(160, 200, 400, 120);
        frame.add(scrollPane);
        
        frame.add(leftPanel);
        frame.setVisible(true);
    }
}
