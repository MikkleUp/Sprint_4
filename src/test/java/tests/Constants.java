package tests;

import model.OrderDataModel;
import model.RentDataModel;
import org.openqa.selenium.Cookie;

import java.time.LocalDate;

public class Constants {

    // Ссылка на главную страницу
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    // Ссылка на страницу заказов
    public static final  String URL_ORDER = "https://qa-scooter.praktikum-services.ru/order";

    // Куки, которые скрывают кнопку о принятии куков
    public static final Cookie DefaultCookies = new Cookie("Cartoshka", "true");

    // Таймаут для ожидания
    public static final int Timeout = 10;

    // Заказ №1
    public static final Object[] OrderData1 = {

            new OrderDataModel("Иван", "Иванов", "Москва, ул. Пушкина", "Красносельская", "89999999999"),
            new RentDataModel(LocalDate.now().plusDays(2), 3, false, true, "Хочу серый!")
    };

    // Заказ №2
    public static final Object[] OrderData2 = {

            new OrderDataModel("Петр", "Петров", "Москва, ул. НЕПушкина", "Южная", "89997771122"),
            new RentDataModel(LocalDate.now().plusDays(4), 6, true, false, "А я хочу чёрный!")
    };

}
