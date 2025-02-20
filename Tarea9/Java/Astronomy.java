package tarea09;
import javax.swing.*;
import java.awt.*;

public class Astronomy extends JFrame {

    public Astronomy() {
        setTitle("Moon Phase");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Moon Phase");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 255, 255));
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        
        JLabel moonLabel = new JLabel("Moon");
        moonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        moonLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        moonLabel.setForeground(new Color(200, 200, 200)); 
        panel.add(moonLabel);

        JLabel daylightLabel = new JLabel("Daylight");
        daylightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        daylightLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        daylightLabel.setForeground(new Color(200, 200, 200));
        panel.add(daylightLabel);

        JLabel bodiesLabel = new JLabel("Bodies");
        bodiesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bodiesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        bodiesLabel.setForeground(new Color(200, 200, 200));
        panel.add(bodiesLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        
        JLabel dateLabel = new JLabel("sábado, 1 de febrero de 2025");
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dateLabel.setForeground(new Color(255, 255, 255));
        panel.add(dateLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        
        JLabel phaseLabel = new JLabel("Waxing Crescent");
        phaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        phaseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        phaseLabel.setForeground(new Color(255, 215, 0)); 
        panel.add(phaseLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        
        JLabel daysLabel = new JLabel("Sunday Monday Tuesday Wednesday");
        daysLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        daysLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        daysLabel.setForeground(new Color(200, 200, 200));
        panel.add(daysLabel);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Astronomy frame = new Astronomy();
            frame.setVisible(true);
        });
    }
}
