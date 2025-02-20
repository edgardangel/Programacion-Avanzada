package Tarea04;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PeopleApp {
    static class Person {
        int id;
        String name;
        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public String toString() {
            return name;
        }
    }
    static class PersonRepository {
        private List<Person> people = new ArrayList<>();
        private int nextId = 1;
        public String statusMessage = "";
        public void addNewPerson(String name) {
            try {
                if (name == null || name.trim().isEmpty())
                    throw new Exception("Valid name required");
                Person p = new Person(nextId++, name);
                people.add(p);
                statusMessage = (nextId - 1) + " record(s) added [Name: " + name + "]";
            } catch (Exception ex) {
                statusMessage = "Failed to add " + name + ". Error: " + ex.getMessage();
            }
        }
        public List<Person> getAllPeople() {
            return new ArrayList<>(people);
        }
    }
    private JFrame frame;
    private JTextField newPersonField;
    private JLabel statusMessageLabel;
    private DefaultListModel<Person> peopleListModel;
    private JList<Person> peopleList;
    private PersonRepository personRepo = new PersonRepository();
    public PeopleApp() {
        frame = new JFrame("People App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());
        newPersonField = new JTextField(20);
        JButton newButton = new JButton("New");
        newButton.addActionListener(e -> {
            String name = newPersonField.getText();
            personRepo.addNewPerson(name);
            statusMessageLabel.setText("Status: " + personRepo.statusMessage);
        });
        topPanel.add(new JLabel("New Person:"));
        topPanel.add(newPersonField);
        topPanel.add(newButton);
        JPanel centerPanel = new JPanel(new BorderLayout());
        peopleListModel = new DefaultListModel<>();
        peopleList = new JList<>(peopleListModel);
        centerPanel.add(new JScrollPane(peopleList), BorderLayout.CENTER);
        JButton getButton = new JButton("Get All");
        getButton.addActionListener(e -> {
            peopleListModel.clear();
            for (Person p : personRepo.getAllPeople())
                peopleListModel.addElement(p);
            statusMessageLabel.setText("Status: " + personRepo.statusMessage);
        });
        centerPanel.add(getButton, BorderLayout.SOUTH);
        statusMessageLabel = new JLabel("Status: ");
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(statusMessageLabel, BorderLayout.SOUTH);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PeopleApp());
    }
}