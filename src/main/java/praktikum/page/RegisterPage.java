package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;

    // Локатор поля ввода "Имя"
    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    // Локатор поля ввода "Email"
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    // Локатор поля ввода "Пароль"
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
    // Локатор кнопки "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // Локатор надписи "Некорректный пароль"
    public final By errorPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    // Локатор текста заголовка "Регистрация"
    private final By registerText = By.xpath(".//div/h2[text()='Регистрация']");

    private final By loginButton = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("Ввести имя")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step ("Ввести email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step ("Ввести пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step ("Нажать на кнопку 'Зарегистрироваться'")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Зарегистрировать пользователя")
    public void registration(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickOnRegisterButton();
    }

    @Step("Ожидание загрузки текста 'Регистрация'")
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(registerText));
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }
}
