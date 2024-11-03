package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.MainPage;

public class ConstructorTest {

    private WebDriver driver;
    private final DriverFactory factory = new DriverFactory();

    public ConstructorTest() {
    }

    @Before
    public void initDriver() {
        factory.initDriver();
        driver = factory.getDriver();
        new MainPage(driver).open();
    }


    @Test
    @DisplayName("Переход в раздел 'Булки'")
    @Description("Проверка перехода в раздел 'Булки' и появление картинки с булкой")
    public void transitionToBunsSectionTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSaucesButton();
        mainPage.clickOnBunsButton();
        mainPage.checkToppingBun();
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    @Description("Проверка перехода в раздел 'Соусы' и появление картинки с соусом")
    public void transitionToSaucesSectionTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSaucesButton();
        mainPage.checkToppingSauce();
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    @Description("Проверка перехода в раздел 'Начинки' и появление картинки с начинкой")
    public void transitionToFillingsSectionTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnFillingButton();
        mainPage.checkToppingFillings();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}