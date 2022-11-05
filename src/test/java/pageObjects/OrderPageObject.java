package pageObjects;

import model.OrderDataModel;
import model.RentDataModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.Constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderPageObject {

    // Заголовок
    private final By header = By.className("Order_Header__BZXOb");

    // Поле "Имя"
    private final By nameInputField = By.xpath("//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    private final By secondNameInputField = By.xpath("//input[@placeholder='* Фамилия']");

    // Поле "Адрес"
    private final By addressInputField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    private final By metroSelectorField = By.className("select-search__input");

    // Выпадающий список "Станиция метро", первая кнопка
    private final By metroSelectorButton = By.xpath("//div[@class='select-search__select']/ul/li/button");

    // Поле "Телефон"
    private final By numberInputField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Кнопка "Заказать"
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Поле ввода даты
    private final By rentDateSelector = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    // Поле ввода количества суток аренды
    private final By rentDropdown = By.className("Dropdown-root");

    // Элемент выпадающего списка количества суток аренды
    private final By rentDropdownItem = By.className("Dropdown-option");

    // чекбокс "черный жемчуг"
    private final By blackCheckbox = By.id("black");

    // чекбокс "серая безысходность"
    private final By greyCheckbox = By.id("grey");

    // Поле ввода комментария
    private final By commentInputField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Модальное окно
    private final By modal = By.className("Order_Modal__YZ-d3");

    // Кнопка "Да" на модальном окне
    private final By yesButtonModal = By.xpath("//button[text()='Да']");

    // Заголовок модального окна
    private final By modalHeader = By.className("Order_ModalHeader__3FDaJ");

    private final WebDriver driver;

    public OrderPageObject(WebDriver driver) {
        this.driver = driver;
    }

    // Получить заголовок страницы
    // на 1-м этапе: "Для кого самокат"
    // на 2-м этапе: "Про аренду"
    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    // Ожидание загрузки заголовка
    public void waitForLoadHeader() {
        new WebDriverWait(driver, Constants.Timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    // Ввод имени на шаге 1
    public void insertName(String name) {
        driver.findElement(nameInputField).sendKeys(name);
    }

    // Ввод фамилии на шаге 1
    public void insertSecondName(String secondName) {
        driver.findElement(secondNameInputField).sendKeys(secondName);
    }

    // Ввод адреса на на шаге 1
    public void insertAddress(String address) {
        driver.findElement(addressInputField).sendKeys(address);
    }

    // Выбор метро из списка
    public void selectMetro(String metro) {
        driver.findElement(metroSelectorField).click();
        driver.findElement(metroSelectorField).sendKeys(metro);
        driver.findElement(metroSelectorButton).click();
    }

    // Ввод номера телефона
    public void insertNumber(String number) {
        driver.findElement(numberInputField).sendKeys(number);
    }

    // нажатие на кнопку Далее
    public void clickNextButton() {

        new WebDriverWait(driver, Constants.Timeout)
                .until(ExpectedConditions.elementToBeClickable(nextButton));

        driver.findElement(nextButton).click();
    }

    // первый этап создания заказа
    public void sendOrderData(OrderDataModel model) {
        insertName(model.name);
        insertSecondName(model.surname);
        insertAddress(model.address);
        selectMetro(model.metro);
        insertNumber(model.phone);
        clickNextButton();
    }

    // Установить дату в поле даты
    public void setDate(String date) {
        driver.findElement(rentDateSelector).click();
        driver.findElement(rentDateSelector).sendKeys(date);
        driver.findElement(rentDateSelector).sendKeys(Keys.ENTER);

        var value = driver.findElement(rentDateSelector).getAttribute("value");
        assertEquals("даты отличаются " + value + " " + date, value, date);
    }

    // Выбор количества дней аренды
    public void setRentSelect(int dayCountIndex) {
        driver.findElement(rentDropdown).click();
        driver.findElements(rentDropdownItem).get(dayCountIndex).click();
    }

    // Установить флаг цвета "черный жемчуг"
    public void setBlackCheckbox() {
        driver.findElement(blackCheckbox).click();
    }

    // Установить флаг цвета "Серая безысходность"
    public void setGreyCheckbox() {
        driver.findElement(greyCheckbox).click();
    }

    // Вписать комментарий
    public void insertComment(String comment) {
        driver.findElement(commentInputField).sendKeys(comment);
    }

    // Нажать на кнопку "Заказать" на странице
    public void clickMakeOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Отправить данные о заказе про аренду
    public void sendRentData(RentDataModel model) {
        setDate(model.orderDate);
        setRentSelect(model.rentDays);
        if (model.blackColor) setBlackCheckbox();
        if (model.greyColor) setGreyCheckbox();

        insertComment(model.comment);
        clickMakeOrderButton();
    }

    // Ожидание загрузки модального окна
    public void waitForLoadModal() {
        new WebDriverWait(driver, Constants.Timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(modal));

    }

    // Нажать на кнопку "да" в модальном окне "Хотите сделать заказ?"
    public void clickYesButton() {
        driver.findElement(yesButtonModal).click();
    }

    // Заголовок модального окна
    public String getModalHeader() {
        return driver.findElement(modalHeader).getText();
    }
}
