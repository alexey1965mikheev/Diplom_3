package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import praktikum.page.LoginPage;
import praktikum.page.PasswordRecoveryPage;
import praktikum.page.RegisterPage;
import praktikum.users.User;
import praktikum.users.UserClient;

public class LoginTest extends BaseTest {

    private String email;
    private String password;
    private String name;

    @Before
    public void registerUser() {
        email = "email" + RandomStringUtils.randomAlphanumeric(5) + "@yandex.ru";
        password = "password" + RandomStringUtils.randomAlphanumeric(8);
        name = "name" + RandomStringUtils.randomAlphanumeric(6);

        Response response = UserClient.createUser(new User(email, password, name));
        token = response.path("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт'")
    @Description("Проверка кнопки 'Войти в аккаунт' на главной странице")
    public void enterByLoginButtonTest() {
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(email, password);
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный Кабинет'")
    @Description("Проверка кнопки 'Личный Кабинет' на главной странице")
    public void enterByPersonalAccountButtonTest() {
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(email, password);
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через форму регистрации")
    public void enterByRegistrationFormTest() {
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginButton();
        loginPage.waitForLoadEntrance();
        loginPage.authorization(email, password);
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через форму восстановления пароля")
    public void enterByPasswordRecoveryFormatTest() {
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnForgotPasswordLink();
        PasswordRecoveryPage recoverPasswordPage = new PasswordRecoveryPage(driver);
        recoverPasswordPage.waitForLoadedPasswordRecovery();
        recoverPasswordPage.clickOnLoginLink();
        loginPage.authorization(email, password);
        mainPage.waitForLoadMainPage();
    }
}
