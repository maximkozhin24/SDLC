package view;

import controller.AstrologyController;
import model.AstrologyModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainView extends JFrame implements PropertyChangeListener {
    private JButton btnOpenInput;
    private JLabel lblSign;
    private JTextArea txtDescription;
    private AstrologyController controller;

    public MainView() {
        setTitle("Астролог");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с кнопкой
        JPanel topPanel = new JPanel();
        btnOpenInput = new JButton("Узнать свой знак Зодиака");
        btnOpenInput.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(btnOpenInput);
        add(topPanel, BorderLayout.NORTH);

        // Центральная панель с результатом
        JPanel centerPanel = new JPanel(new BorderLayout());
        lblSign = new JLabel("Знак зодиака: неизвестно", SwingConstants.CENTER);
        lblSign.setFont(new Font("Arial", Font.BOLD, 18));
        lblSign.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        centerPanel.add(lblSign, BorderLayout.NORTH);

        txtDescription = new JTextArea();
        txtDescription.setEditable(false);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescription.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        centerPanel.add(new JScrollPane(txtDescription), BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Обработчик кнопки - использует this.controller который будет установлен позже
        btnOpenInput.addActionListener(e -> {
            if (controller != null) {
                controller.showInputDialog();
            }
        });
    }

    public void setController(AstrologyController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("birthDate".equals(evt.getPropertyName())) {
            updateView();
        }
    }

    public void updateView() {
        AstrologyModel model = controller.getModel();
        if (model.getZodiacSign() != null) {
            lblSign.setText("Ваш знак зодиака: " + model.getZodiacSign());
            txtDescription.setText(model.getDescription());
        }
    }
}