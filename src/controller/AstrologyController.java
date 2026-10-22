package controller;

import model.AstrologyModel;
import view.InputDialog;
import view.MainView;

import javax.swing.*;
import java.time.DateTimeException;

public class AstrologyController {
    private AstrologyModel model;
    private MainView view;

    public AstrologyController(AstrologyModel model, MainView view) {
        this.model = model;
        this.view = view;


        model.addPropertyChangeListener(view);
    }

    public AstrologyModel getModel() {
        return model;
    }

    public void showInputDialog() {
        // Передаем последнюю сохраненную дату для восстановления
        InputDialog dialog = new InputDialog(view, model.getBirthDate());
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            processData(dialog.getDay(), dialog.getMonth(), dialog.getYear());
        }
    }

    private void processData(int day, int month, int year) {
        try {
            model.setBirthDate(day, month, year);
        } catch (DateTimeException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view,
                "Введены некорректные данные! Проверьте правильность даты.\n" +
                    "Возможно, такого дня не существует в выбранном месяце.",
                "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        }
    }
}