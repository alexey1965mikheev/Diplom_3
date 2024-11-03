package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;

    // Локатор кнопки "Конструктор"
    private final By constructorButton = By.xpath(".//a[@href='/']/p[text()='Конструктор']");
    // Локатор кнопки "Выход"
    private final By exitButton = By.xpath(".//li/button[text()='Выход']");
    // Локатор надписи для перехода в Личный кабинет
    public final By textOnProfilePage = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть по кнопке Конструктор")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Кликнуть по кнопке Выйти")
    public void clickOnExitButton() {
        driver.findElement(exitButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Ожидание загрузки страницы личного кабинета")
    public void waitForLoadProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(textOnProfilePage));
    }

    @Step("Ожидание полной загрузки страницы")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }
}

