package praktikum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import praktikum.page.MainPage;
import praktikum.users.UserClient;

public class BaseTest {

    protected WebDriver driver;
    protected DriverFactory factory;
    protected UserClient userClient;

    protected MainPage mainPage;

    protected static String token = null;

    @Before
    public void initDriver() {
        factory = new DriverFactory();
        factory.initDriver();
        driver = factory.getDriver();

        userClient = new UserClient();

        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.waitForLoadMainPage();
    }

    @After
    public void tearDown() {
        driver.quit();
        if (token != null) {
            userClient.deleteUser(token);
        }
    }
}