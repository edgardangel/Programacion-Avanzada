package a2233336126_cursoUdemy_unidad01;

import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;

public class TestSwingCommonFeatures extends JFrame {
	
	public TestSwingCommonFeatures() {
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        JButton jbtLeft = new JButton("Left");
        JButton jbtCenter = new JButton("Center");
        JButton jbtRight = new JButton("Right");
        jbtLeft.setBackground(Color.WHITE);
        jbtCenter.setForeground(Color.GREEN);
        jbtRight.setToolTipText("This is the Right button");
        p1.add(jbtLeft);
        p1.add(jbtCenter);
        p1.add(jbtRight);
        p1.setBorder(new TitledBorder("Three Buttons"));

        Font largeFont = new Font("TimesRoman", Font.BOLD, 20);
        Border lineBorder = new LineBorder(Color.BLACK, 2);

        JPanel p2 = new JPanel(new GridLayout(1, 2, 5, 5));
        JLabel jlblRed = new JLabel("Red");
        JLabel jlblorange = new JLabel("Orange");
        jlblRed.setForeground(Color.RED);
        jlblorange.setForeground(Color.ORANGE);
        jlblRed.setFont(largeFont);
        jlblorange.setFont(largeFont);
        jlblRed.setBorder(lineBorder);
        jlblorange.setBorder(lineBorder);
        p2.add(jlblRed);
        p2.add(jlblorange);
        p2.setBorder(new TitledBorder("Two Labels"));

        setLayout(new GridLayout(2, 1, 5, 5));
        add(p1);
        add(p2);
        
        setTitle("Test Swing Common Features");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
	
	public static void main(String[] args) {
		JFrame frame = new TestSwingCommonFeatures ();
		frame.setVisible(true);
	}
	
}
