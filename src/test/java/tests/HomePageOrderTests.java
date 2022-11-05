package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.OrderDataModel;
import model.RentDataModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObjects.HomePageObject;
import pageObjects.OrderPageObject;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class HomePageOrderTests {
    private final OrderDataModel orderDataModel;
    private final RentDataModel rentDataModel;
    private WebDriver webDriver = null;

    public HomePageOrderTests(OrderDataModel orderDataModel, RentDataModel rentDataModel) {

        // Модель для проверки
        this.orderDataModel = orderDataModel;
        this.rentDataModel = rentDataModel;
    }

    @Before
    public void init() {
        // В браузере хроме будет ошибка и тест не пройдет
        // webDriver = BrowserManager.Chrome();
        webDriver = BrowserManager.Edge();

        // Устанавливаем куки, чтобы не было кнопки про куки
        webDriver.get(Constants.URL);
        webDriver.manage().addCookie(Constants.DefaultCookies);
    }


    @Test
    public void test_IfClickedOrderButtonInHeader_ThenOpenOrderPage() {
        webDriver.get(Constants.URL);
        HomePageObject homePageObject = new HomePageObject(webDriver);
        homePageObject.clickOrderButtonInHeader();

        assertEquals("Ожидалось, что перейдем на страницу создания заказа", Constants.URL_ORDER, webDriver.getCurrentUrl());
    }

    @Test
    public void test_IfClickedOrderButtonInPage_ThenOpenOrderPage() {
        webDriver.get(Constants.URL);
        HomePageObject homePageObject = new HomePageObject(webDriver);
        homePageObject.clickOrderButtonInPage();
        assertEquals("Ожидалось, что перейдем на страницу создания заказа", Constants.URL_ORDER, webDriver.getCurrentUrl());
    }


    @Test
    public void test_CreateOrder() {
        webDriver.get(Constants.URL_ORDER);

        OrderPageObject orderPage = new OrderPageObject(webDriver);
        orderPage.waitForLoadHeader();
        orderPage.sendOrderData(orderDataModel);

        String expectedHeader = "Про аренду";
        String header = orderPage.getHeaderText();
        assertEquals("Ожидалось, что заголовок будет '" + expectedHeader + "', но заголовок '" + header + "'", expectedHeader, header);

        orderPage.sendRentData(rentDataModel);

        orderPage.waitForLoadModal();
        orderPage.clickYesButton();

        orderPage.waitForLoadModal();
        String modalHeader = orderPage.getModalHeader();
        String expectedModalHeader = "Заказ оформлен";
        assertTrue("Ожидалось, что заголовок модального окна будет содержать фразу '" + expectedModalHeader + "', но заголовок '" + modalHeader + "'", modalHeader.contains(expectedModalHeader));
    }


    @After
    public void teardown() {
        webDriver.quit();
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(Constants.OrderData1, Constants.OrderData2);
    }

}
