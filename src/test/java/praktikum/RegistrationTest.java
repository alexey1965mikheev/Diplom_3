package praktikum;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.page.LoginPage;
import praktikum.page.MainPage;
import praktikum.page.RegisterPage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class RegistrationTest {

    private WebDriver driver;
    private final DriverFactory factory = new DriverFactory();

    String NAME = randomAlphanumeric(4, 8);
    String EMAIL = randomAlphanumeric(6, 10) + "@yandex.ru";
    String PASSWORD = randomAlphanumeric(10, 20);
    String PASSWORD_FAILED = randomAlphanumeric(0, 5);

    @Before
    public void initDriver() {
        factory.initDriver();
        driver = factory.getDriver();
        new MainPage(driver).open();
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadRegisterPage();
        registerPage.registration(NAME, EMAIL, PASSWORD);
        loginPage.waitForLoadEntrance();
    }

    @Test
    @DisplayName("Неуспешная регистрация пользователя")
    @Description("Проверка неуспешной регистрации пользователя при вводе пароля меньше 6 символов и появление сообщения 'Некорректный пароль'.")
    public void failedPasswordRegistrationTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadRegisterPage();
        registerPage.registration(NAME, EMAIL, PASSWORD_FAILED);
        //Проверка появление текста "Некорректный пароль"
        Assert.assertTrue("Текст об ошибке отсутствует", driver.findElement(registerPage.errorPasswordText).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}