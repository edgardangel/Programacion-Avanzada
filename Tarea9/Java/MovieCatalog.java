package tarea09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MovieCatalog {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieCatalog::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("MovieCatalog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        String[] movies = {"Back to the Future", "The Godfather", "Inception", "The Dark Knight", "Pulp Fiction"};
        JList<String> movieList = new JList<>(movies);

        movieList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = movieList.locationToIndex(evt.getPoint());
                    String selectedMovie = movieList.getModel().getElementAt(index);
                    showMovieDetails(selectedMovie);
                }
            }
        });

        frame.add(new JScrollPane(movieList));
        frame.setVisible(true);
    }

    private static void showMovieDetails(String movieTitle) {
        JFrame detailsFrame = new JFrame("Movie Details");
        detailsFrame.setSize(350, 200);
        detailsFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("Title: " + movieTitle);

        switch (movieTitle) {
            case "Back to the Future":
                panel.add(titleLabel);
                panel.add(new JLabel("Year: 1985"));
                panel.add(new JLabel("Studio: Universal Pictures"));
                panel.add(new JLabel("Director: Robert Zemeckis"));
                break;
            case "The Godfather":
                panel.add(titleLabel);
                panel.add(new JLabel("Year: 1972"));
                panel.add(new JLabel("Studio: Paramount Pictures"));
                panel.add(new JLabel("Director: Francis Ford Coppola"));
                break;
            case "Inception":
                panel.add(titleLabel);
                panel.add(new JLabel("Year: 2010"));
                panel.add(new JLabel("Studio: Warner Bros."));
                panel.add(new JLabel("Director: Christopher Nolan"));
                break;
            case "The Dark Knight":
                panel.add(titleLabel);
                panel.add(new JLabel("Year: 2008"));
                panel.add(new JLabel("Studio: Warner Bros."));
                panel.add(new JLabel("Director: Christopher Nolan"));
                break;
            case "Pulp Fiction":
                panel.add(titleLabel);
                panel.add(new JLabel("Year: 1994"));
                panel.add(new JLabel("Studio: Miramax"));
                panel.add(new JLabel("Director: Quentin Tarantino"));
                break;
        }

        detailsFrame.add(panel, BorderLayout.CENTER);
        detailsFrame.setVisible(true);
    }
}
