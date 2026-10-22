import controller.AstrologyController;
import model.AstrologyModel;
import view.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Установка системного вида интерфейса
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            AstrologyModel model = new AstrologyModel();
            MainView view = new MainView();  // Создаем без контроллера

            AstrologyController controller = new AstrologyController(model, view);

            view.setController(controller);  // Устанавливаем контроллер

            view.setVisible(true);
        });
    }
}