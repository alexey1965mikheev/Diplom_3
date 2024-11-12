package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class PasswordRecoveryPage {

    private final WebDriver driver;

    // Локатор поля ввода "Email"
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Локатор кнопки "Восстановить"
    private final By recoverButton = By.xpath(".//form/button[text()='Восстановить']");
    // Локатор заголовка "Восстановление пароля"
    public final By recoverPassword = By.xpath(".//main/div/h2[text()='Восстановление пароля']");
    // Локатор кнопки "Войти"
    private final By loginLink = By.xpath(".//div/p/a[@href = '/login' and text() = 'Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести Email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Кликнуть по кнопке Восстановить")
    public void clickOnRecoverButton() {
        driver.findElement(recoverButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Кликнуть по кнопке Войти")
    public void clickOnLoginLink() {
        driver.findElement(loginLink).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Восстановление пароля")
    public void PasswordRecovery(String email) {
        setEmail(email);
        clickOnRecoverButton();
    }

    @Step("Ожидание полной загрузки страницы")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForLoadedPasswordRecovery() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPassword));
    }
}
