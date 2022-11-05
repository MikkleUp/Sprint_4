package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pageObjects.HomePageObject;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;



@RunWith(Parameterized.class)
public class HomePageAccordionTests {
    private final WebDriver webDriver;
    private final int testingItem;


    public HomePageAccordionTests(int testingItem) {

        // Тестируемый элемент (индекс) выпадающего списка
        this.testingItem = testingItem;

        // Драйвер для браузера Chrome
        webDriver = BrowserManager.Chrome();

        // Устанавливаем куки, чтобы не появлялась кнопка "Принять куки"
        webDriver.get(Constants.URL);
        webDriver.manage().addCookie(Constants.DefaultCookies);
    }

    // Тесты выпадающего списка
    // Нажимаем на элемент выпадающего списка
    // Проверяем, что элемент раскрыт
    @Test
    public void test_accordion_button() {
        webDriver.get(Constants.URL);
        HomePageObject homePageObject = new HomePageObject(webDriver);
        homePageObject.clickAccordingItem(testingItem);
        boolean isItemExpanded = homePageObject.isAccordionItemIsExpanded(testingItem);
        assertTrue("Ожидалось, что элемент будет раскрыт №" + testingItem, isItemExpanded);
    }


    @After
    public void teardown() {
        webDriver.quit();
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        // Индексы проверяемых элементов
        return Arrays.asList(new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}
        });
    }

}
