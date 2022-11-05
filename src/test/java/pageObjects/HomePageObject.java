package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.Constants;

import java.time.Duration;

public class HomePageObject {

    // Кнопка "Заказать" в шапке
    private final By orderPageButtonHeader = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]");


    // Кнопка "Заказать" на странице
    private final By orderPageButton = By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button");


    // Кнопки выпадающего списка
    private final By[] accordingElements = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7"),
    };


    private final WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    // Клик по кнопке "Заказать" в шапке страницы
    public void clickOrderButtonInHeader() {
        driver.findElement(orderPageButtonHeader).click();
    }

    // Клик по кнопке "Заказать" на странице
    public void clickOrderButtonInPage() {
        driver.findElement(orderPageButton).click();
    }

    // Клик по объекту выпадающего списка по индексу
    public void clickAccordingItem(int index) {
        if (index < 0 || index >= accordingElements.length)
            throw new IllegalArgumentException("index меньше нуля или больше, чем длина массива");

        var byElement = accordingElements[index];
        var element = driver.findElement(byElement);
        element.click();
    }

    // Проверка, что выпадающий элемент под индексом открыт
    public boolean isAccordionItemIsExpanded(int index) {
        if (index < 0 || index >= accordingElements.length)
            throw new IllegalArgumentException("index меньше нуля или больше, чем длина массива");

        var accordingElement = accordingElements[index];
        var htmlElement = driver.findElement(accordingElement);

        var controlsAttribute = htmlElement.getAttribute("aria-controls");

        // Ожидаем, пока раскроется выпадающий список
        new WebDriverWait(driver, Constants.Timeout)
                .until(ExpectedConditions.attributeToBe(htmlElement, "aria-expanded", "true"));

        var controlsElement = driver.findElement(By.id(controlsAttribute));
        return controlsElement.isDisplayed();
    }
}
