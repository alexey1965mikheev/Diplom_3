package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    // Локатор заголовка "Вход"
    public final By entrance = By.xpath(".//main/div/h2[text()='Вход']");
    // Локатор поля ввода "Email"
    private final By emailField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    // Локатор поля ввода "Пароль"
    private final By passwordField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль']");
    // Локатор логотипа "Stellar Burgers"
    public final By enterButton = By.xpath(".//div/a[@href='/']");
    // Локатор кнопки "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    // Локатор кнопки "Конструктор"
    private final By constructorButton = By.xpath(".//a/p[text()='Конструктор']");
    // Локатор ссылки "Зарегистрироваться"
    private final By registerLink = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    // Локатор ссылки "Восстановить пароль"
    private final By forgotPasswordLink = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email в поле ввода Email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль в поле Password")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Кликнуть по кнопке Войти")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Кликнуть по ссылке Зарегистрироваться")
    public void clickOnRegisterLink() {
        driver.findElement(registerLink).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Кликнуть по ссылке Восстановить пароль")
    public void clickOnForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Кликнуть по кнопке Конструктор")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Кликнуть по логотипу Stellar Burgers")
    public void clickOnLogo() {
        driver.findElement(enterButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Авторизоваться")
    public void authorization(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
    }

    @Step("Ожидание загрузки заголовка ВХОД")
    public void waitForLoadEntrance() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }

    @Step("Ожидание загрузки страницы")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }
}
