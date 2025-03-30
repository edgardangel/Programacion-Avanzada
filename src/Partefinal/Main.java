package Partefinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private JMenu mConfiguracion;
    private JMenuItem miCategorias, miInsumos, miObras;

    public Main() {
        initComponents();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        desktopPane = new JDesktopPane();
        mainPanel.add(desktopPane, "desktop");

        menuBar = new JMenuBar();
        mConfiguracion = new JMenu("Configuración");
        miCategorias = new JMenuItem("Categorías");
        miInsumos = new JMenuItem("Insumos");
        miObras = new JMenuItem("Obras");

        miCategorias.addActionListener(this);
        miInsumos.addActionListener(this);
        miObras.addActionListener(this);

        mConfiguracion.add(miCategorias);
        mConfiguracion.add(miInsumos);
        mConfiguracion.add(miObras);
        menuBar.add(mConfiguracion);
        setJMenuBar(menuBar);

        add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == miCategorias) {
            abrirFrame(new CategoriaFrame(menuBar));
        } else if (e.getSource() == miInsumos) {
            abrirFrame(new InsumoFrame(menuBar));
        } else if (e.getSource() == miObras) {
            abrirFrame(new ObraFrame(menuBar));
        }
    }

    private void abrirFrame(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
        frame.setLocation(
            (desktopPane.getWidth() - frame.getWidth()) / 2,
            (desktopPane.getHeight() - frame.getHeight()) / 2
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}