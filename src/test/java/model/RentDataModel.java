package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Модель данных для заполнения на втором этапе создания заказа
public class RentDataModel {

    // Дата в формате dd.MM.yyyy
    public String orderDate;

    // Количество суток (индекс)
    public int rentDays;

    // Галочка "черный цвет"
    public boolean blackColor;

    // Галочка "серый цвет"
    public boolean greyColor;

    // Комментарий
    public String comment;

    public RentDataModel(LocalDate date, int rentDays, boolean blackColor, boolean greyColor, String comment) {
        orderDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.rentDays = rentDays;
        this.blackColor = blackColor;
        this.greyColor = greyColor;
        this.comment = comment;
    }
}
