package tarea08;
import javax.swing.*;
import java.awt.*;

public class ManageProductsUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Manage Products");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(null);
        
        // Panel izquierdo
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(255, 140, 0));
        leftPanel.setBounds(0, 0, 150, 400);
        leftPanel.setLayout(null);
        
        JLabel labelSeller = new JLabel("SELLER");
        labelSeller.setForeground(Color.WHITE);
        labelSeller.setFont(new Font("Arial", Font.BOLD, 16));
        labelSeller.setBounds(30, 50, 120, 30);
        leftPanel.add(labelSeller);
        
        JLabel labelCategories = new JLabel("CATEGORIES");
        labelCategories.setForeground(Color.WHITE);
        labelCategories.setFont(new Font("Arial", Font.PLAIN, 12));
        labelCategories.setBounds(30, 80, 120, 30);
        leftPanel.add(labelCategories);
        
        // Panel derecho - Gestión de productos
        JLabel labelManage = new JLabel("MANAGE PRODUCTS");
        labelManage.setFont(new Font("Arial", Font.BOLD, 18));
        labelManage.setBounds(250, 10, 200, 30);
        frame.add(labelManage);
        
        JLabel labelProdID = new JLabel("PRODID");
        labelProdID.setBounds(160, 50, 100, 20);
        frame.add(labelProdID);
        
        JTextField textProdID = new JTextField();
        textProdID.setBounds(230, 50, 100, 20);
        frame.add(textProdID);
        
        JLabel labelName = new JLabel("NAME");
        labelName.setBounds(160, 80, 100, 20);
        frame.add(labelName);
        
        JTextField textName = new JTextField();
        textName.setBounds(230, 80, 100, 20);
        frame.add(textName);
        
        JLabel labelCategory = new JLabel("CATEGORY");
        labelCategory.setBounds(160, 110, 100, 20);
        frame.add(labelCategory);
        
        String[] categories = {"Beverage", "Meat", "Dairy"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(230, 110, 100, 20);
        frame.add(categoryBox);
        
        JLabel labelQuantity = new JLabel("QUANTITY");
        labelQuantity.setBounds(350, 50, 100, 20);
        frame.add(labelQuantity);
        
        JTextField textQuantity = new JTextField();
        textQuantity.setBounds(420, 50, 100, 20);
        frame.add(textQuantity);
        
        JLabel labelPrice = new JLabel("PRICE");
        labelPrice.setBounds(350, 80, 100, 20);
        frame.add(labelPrice);
        
        JTextField textPrice = new JTextField();
        textPrice.setBounds(420, 80, 100, 20);
        frame.add(textPrice);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(180, 150, 80, 30);
        frame.add(addButton);
        
        JButton editButton = new JButton("Edit");
        editButton.setBounds(270, 150, 80, 30);
        frame.add(editButton);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(360, 150, 80, 30);
        frame.add(deleteButton);
        
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(450, 150, 80, 30);
        frame.add(clearButton);
        
        // Tabla de productos
        JLabel labelProdList = new JLabel("PRODUCTS LIST");
        labelProdList.setFont(new Font("Arial", Font.BOLD, 14));
        labelProdList.setBounds(250, 200, 200, 30);
        frame.add(labelProdList);
        
        String[] columnNames = {"PRODID", "PRODNAME", "PRODQTY", "PRODPRICE", "PRODCAT"};
        Object[][] data = {
            {1, "Pepsi Cane", 773, 90.0, "Meat"},
            {2, "Coca", 780, 15.0, "Beverage"},
            {3, "Sausage", 592, 35.0, "Meat"},
            {4, "Eggs", 800, 4.0, "Dairy"}
        };
        JTable productTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(160, 230, 400, 120);
        frame.add(scrollPane);
        
        frame.add(leftPanel);
        frame.setVisible(true);
    }
}

