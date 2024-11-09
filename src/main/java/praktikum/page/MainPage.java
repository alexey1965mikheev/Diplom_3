package praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MainPage {

    private final WebDriver driver;

    // Локатор кнопки "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // Локатор кнопки "Личный кабинет"
    private final By accountButton = By.xpath(".//a[@href='/account']");
    // Локатор вкладки "Булки"
    private final By bunsButton = By.xpath(".//span[text()='Булки']");
    // Локатор вкладки "Соусы"
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");
    // Локатор вкладки "Начинки"
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']");
    private final By activityTopping = By.xpath("//div[starts-with(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]//span");


    // Локатор текста для проверки видимости на главной странице
    public By textBurgerMainPage = By.xpath(".//section/h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    public void clickOnAccountButton() {
        driver.findElement(accountButton).click();
        waitForInvisibilityLoadingAnimation();
    }


    public void clickOnBunsButton() {
        driver.findElement(bunsButton).click();

    }

    public void clickOnSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    public void clickOnFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    public void checkToppingBun() {
        String countActivity = driver.findElement(activityTopping).getText();
        assertEquals("Булки", countActivity);
    }

    public void checkToppingSauce() {
        String countActivity = driver.findElement(activityTopping).getText();
        assertEquals(countActivity, "Соусы");
    }

    public void checkToppingFillings() {
        String countActivity = driver.findElement(activityTopping).getText();
        assertEquals(countActivity, "Начинки");
    }

    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(textBurgerMainPage));
    }

    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
        waitDocReady();
    }

    public void waitDocReady() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }
}

