package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser"))) {
            startYandex();
        } else {
            startChrome();
        }
    }

    public void startChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void startYandex() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
