package model;

// Модель данных для заполнения на первом этапе создания заказа
public class OrderDataModel {

    // Имя
    public String name;

    // Фамилия
    public String surname;

    // Адрес
    public String address;

    // Станиция метро
    public String metro;

    // Номер телефона
    public String phone;

    public OrderDataModel(String name, String surname, String address, String metro, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
    }

}
