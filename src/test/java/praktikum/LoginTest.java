package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.LoginPage;
import praktikum.page.MainPage;
import praktikum.page.PasswordRecoveryPage;
import praktikum.page.RegisterPage;
import praktikum.users.UserClient;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class LoginTest extends StellarBurgersTests {

    private static final String EMAIL = "alex.mikheev_10@gmail.com";
    private static final String PASSWORD = "1111111111";

    private final UserClient userClient;

    public LoginTest() {
       userClient = new UserClient();
    }

    @Before
    public void initDriver() {
       super.initDriver();

    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт'")
    @Description("Проверка кнопки 'Войти в аккаунт' на главной странице")
    public void enterByLoginButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный Кабинет'")
    @Description("Проверка кнопки 'Личный Кабинет' на главной странице")
    public void enterByPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через форму регистрации")
    public void enterByRegistrationFormTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        String name = randomAlphanumeric(4, 8);
        String email = randomAlphanumeric(6, 10) + "@yandex.ru";
        String password = randomAlphanumeric(6, 10);
        registerPage.registration(name, email, password);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(email, password);
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через форму восстановления пароля")
    public void enterByPasswordRecoveryFormatTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnForgotPasswordLink();
        PasswordRecoveryPage recoverPasswordPage = new PasswordRecoveryPage(driver);
        recoverPasswordPage.waitForLoadedPasswordRecovery();
        recoverPasswordPage.clickOnLoginLink();
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
    }
}
