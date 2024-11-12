package praktikum;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.html5.WebStorage;
import praktikum.page.LoginPage;
import praktikum.page.RegisterPage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class RegistrationTest extends BaseTest {

    private final String NAME = randomAlphanumeric(4, 8);
    private final String EMAIL = randomAlphanumeric(6, 10) + "@yandex.ru";
    private final String PASSWORD = randomAlphanumeric(10, 20);
    private final String INCORRECT_PASSWORD = randomAlphanumeric(1, 5);

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации")
    public void successfulRegistrationTest() {
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadRegisterPage();
        registerPage.registration(NAME, EMAIL, PASSWORD);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
        token = ((WebStorage) driver).getLocalStorage().getItem("accessToken");
    }

    @Test
    @DisplayName("Неуспешная регистрация пользователя")
    @Description("Проверка неуспешной регистрации пользователя при вводе пароля меньше 6 символов и появление сообщения 'Некорректный пароль'.")
    public void failedPasswordRegistrationTest() {
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadRegisterPage();
        registerPage.registration(NAME, EMAIL, INCORRECT_PASSWORD);

        Assert.assertTrue("Текст об ошибке отсутствует", driver.findElement(registerPage.errorPasswordText).isDisplayed());
    }
}