package praktikum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import praktikum.page.MainPage;

public class StellarBurgersTests {

    protected WebDriver driver;
    private final DriverFactory factory = new DriverFactory();

    @Before
    public void initDriver() {
        factory.initDriver();
        driver = factory.getDriver();
        new MainPage(driver).open();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
