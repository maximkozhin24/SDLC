package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AstrologyModel {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private LocalDate birthDate;
    private String zodiacSign;
    private String description;

    private static final Map<String, String> SIGN_DESCRIPTIONS = new HashMap<>();
    static {
        SIGN_DESCRIPTIONS.put("Овен", "Энергичный и решительный лидер. Стихия: Огонь. Планета: Марс.");
        SIGN_DESCRIPTIONS.put("Телец", "Надежный и терпеливый ценитель прекрасного. Стихия: Земля. Планета: Венера.");
        SIGN_DESCRIPTIONS.put("Близнецы", "Любознательный и адаптивный коммуникатор. Стихия: Воздух. Планета: Меркурий.");
        SIGN_DESCRIPTIONS.put("Рак", "Чуткий и интуитивный хранитель домашнего очага. Стихия: Вода. Планета: Луна.");
        SIGN_DESCRIPTIONS.put("Лев", "Творческий и страстный генератор идей. Стихия: Огонь. Планета: Солнце.");
        SIGN_DESCRIPTIONS.put("Дева", "Аналитичный и внимательный к деталям перфекционист. Стихия: Земля. Планета: Меркурий.");
        SIGN_DESCRIPTIONS.put("Весы", "Дипломатичный и справедливый эстет. Стихия: Воздух. Планета: Венера.");
        SIGN_DESCRIPTIONS.put("Скорпион", "Страстный и проницательный стратег. Стихия: Вода. Планета: Плутон.");
        SIGN_DESCRIPTIONS.put("Стрелец", "Свободолюбивый и оптимистичный путешественник. Стихия: Огонь. Планета: Юпитер.");
        SIGN_DESCRIPTIONS.put("Козерог", "Дисциплинированный и целеустремленный реалист. Стихия: Земля. Планета: Сатурн.");
        SIGN_DESCRIPTIONS.put("Водолей", "Оригинальный и прогрессивный мыслитель. Стихия: Воздух. Планета: Уран.");
        SIGN_DESCRIPTIONS.put("Рыбы", "Мечтательный и эмпатичный романтик. Стихия: Вода. Планета: Нептун.");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void setBirthDate(int day, int month, int year) {
        LocalDate oldDate = this.birthDate;
        this.birthDate = LocalDate.of(year, month, day);

        calculateZodiac();

        pcs.firePropertyChange("birthDate", oldDate, this.birthDate);
    }

    private void calculateZodiac() {
        int day = birthDate.getDayOfMonth();
        int month = birthDate.getMonthValue();

        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) zodiacSign = "Овен";
        else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) zodiacSign = "Телец";
        else if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) zodiacSign = "Близнецы";
        else if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) zodiacSign = "Рак";
        else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) zodiacSign = "Лев";
        else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) zodiacSign = "Дева";
        else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) zodiacSign = "Весы";
        else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) zodiacSign = "Скорпион";
        else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) zodiacSign = "Стрелец";
        else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) zodiacSign = "Козерог";
        else if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) zodiacSign = "Водолей";
        else zodiacSign = "Рыбы";

        this.description = SIGN_DESCRIPTIONS.getOrDefault(zodiacSign, "Загадочная личность.");
    }

    public LocalDate getBirthDate() { return birthDate; }
    public String getZodiacSign() { return zodiacSign; }
    public String getDescription() { return description; }
}