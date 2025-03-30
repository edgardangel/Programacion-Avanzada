package Controlador;

import Vista.MainFrame;

public class App {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        MainController mainController = new MainController(mainFrame);
        mainFrame.setVisible(true);
    }
}
