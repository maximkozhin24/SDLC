package view;

import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;

public class InputDialog extends JDialog {
    // cmbMonth теперь отдельно, так как у него тип String
    private JComboBox<Integer> cmbDay, cmbYear;
    private JComboBox<String> cmbMonth;
    private JButton btnOk, btnCancel;
    private boolean confirmed = false;

    public InputDialog(JFrame parent, LocalDate lastDate) {
        super(parent, "Ввод даты рождения", true);
        setSize(320, 180);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 10, 10));
        setResizable(false);

        // День
        add(new JLabel("День:"));
        cmbDay = new JComboBox<>();
        for (int i = 1; i <= 31; i++) cmbDay.addItem(i);
        add(cmbDay);

        // Месяц
        add(new JLabel("Месяц:"));
        cmbMonth = new JComboBox<>();
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
            "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        for (int i = 1; i <= 12; i++) cmbMonth.addItem(i + " - " + months[i - 1]);
        add(cmbMonth);

        // Год
        add(new JLabel("Год:"));
        cmbYear = new JComboBox<>();
        for (int i = 1920; i <= 2026; i++) cmbYear.addItem(i);
        add(cmbYear);

        // Восстановление последних введенных данных
        if (lastDate != null) {
            cmbDay.setSelectedItem(lastDate.getDayOfMonth());
            cmbMonth.setSelectedIndex(lastDate.getMonthValue() - 1);
            cmbYear.setSelectedItem(lastDate.getYear());
        }

        // Кнопки
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnOk = new JButton("ОК");
        btnCancel = new JButton("Отмена");
        btnPanel.add(btnOk);
        btnPanel.add(btnCancel);
        add(btnPanel);

        btnOk.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        btnCancel.addActionListener(e -> dispose());
    }

    public boolean isConfirmed() { return confirmed; }
    public int getDay() { return (int) cmbDay.getSelectedItem(); }
    public int getMonth() { return cmbMonth.getSelectedIndex() + 1; }
    public int getYear() { return (int) cmbYear.getSelectedItem(); }
}