package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import praktikum.page.LoginPage;
import praktikum.page.ProfilePage;

public class PersonalAccountTest extends BaseTest {

    private final static String EMAIL = "alex.mikheev_10@gmail.com";
    private final static String PASSWORD = "1111111111";

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода по клику на 'Личный кабинет'.")
    public void transitionToPersonalAccountTest() {
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        Assert.assertTrue("Страница авторизации не отобразилась", driver.findElement(loginPage.entrance).isDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета")
    @Description("Проверка перехода на вкладку 'Конструктор' со страницы авторизации пользователя")
    public void transitionToConstructorFromPersonalAccountTest() {
        mainPage.waitForInvisibilityLoadingAnimation();
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnConstructorButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Переход  в конструктор из личного кабинете не прошел", driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор через логотип 'Stellar Burgers'")
    @Description("Проверка перехода в конструктор при клике по логотипу 'Stellar Burgers'.")
    public void transitionToStellarBurgersFromProfilePageTest() {
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnLogo();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Конструктор при клике на логотип не загрузился", driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода кликом по кнопке 'Выйти' в личном кабинете")
    public void exitFromProfileTest() {
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
        mainPage.clickOnAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.clickOnExitButton();
        mainPage.waitForInvisibilityLoadingAnimation();
        Assert.assertTrue("Не удалось выйти из аккаунта", driver.findElement(loginPage.entrance).isDisplayed());
    }
}
