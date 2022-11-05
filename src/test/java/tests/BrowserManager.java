package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

public class BrowserManager {

    // аргументы для дравейров
    private static final List<String> args = List.of("--no-sandbox", "--headless", "--disable-dev-shm-usage");

    // Получить драйвер Google Chrome
    public static WebDriver Chrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(args);

        return new ChromeDriver(options);
    }

    // Получить драйвер Microsoft Edge
    public static WebDriver Edge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments(args);

        return new EdgeDriver(options);
    }

    public static WebDriver Mozilla() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(args);

        return new FirefoxDriver(options);
    }
}
