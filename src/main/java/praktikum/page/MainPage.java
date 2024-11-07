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

    //Локатор кнопки "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //Локатор кнопки "Личный кабинет"
    private final By accountButton = By.xpath(".//a[@href='/account']");
    //Локатор логотипа "Stellar Burgers"
    private final By logo = By.xpath(".//div/a[@href='/']");
    //Локатор кнопки "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //Локатор вкладки "Булки"
    private final By bunsButton = By.xpath(".//span[text()='Булки']");
    //Локатор вкладки "Соусы"
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");
    //Локатор вкладки "Начинки"
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']");
    private final By activityTopping = By.xpath("//div[starts-with(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]//span");

    //картинка с "Булкой" для проверки видимости раздела
    public By bunsImg = By.xpath(".//img[@alt='Краторная булка N-200i']");
    //текст заголовка "Булки" для проверки видимости раздела
    public By bunsText = By.xpath(".//h2[text()='Булки']");
    //картинка с "Соусом" для проверки видимости раздела
    public By saucesImg = By.xpath(".//p[text()='Соус с шипами Антарианского плоскоходца']");
    //картинка с "Начинкой" для проверки видимости раздела
    public By fillingsImg = By.xpath(".//img[@alt='Плоды Фалленианского дерева']");
    //текст для проверки видимости на главной странице
    public By textBurgerMainPage = By.xpath(".//section/h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(EnvConfig.BASE_URL);
        return this;
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    public void clickOnAccountButton() {
        driver.findElement(accountButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    public void clickOnLogo() {
        driver.findElement(logo).click();
        waitForInvisibilityLoadingAnimation();
    }

    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    public void clickOnBunsButton() {
        driver.findElement(bunsButton).click();

    }

    public void clickOnSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    public void clickOnFillingButton()  {
        driver.findElement(fillingsButton).click();
    }

    public void checkToppingBun() {
        String countActivity = driver.findElement(activityTopping).getText();
        assertEquals("Булки", countActivity);
    }

    public void checkToppingSauce()  {
        String countActivity = driver.findElement(activityTopping).getText();
        assertEquals(countActivity, "Соусы");
    }

    public void checkToppingFillings()  {
        String countActivity = driver.findElement(activityTopping).getText();
        assertEquals(countActivity, "Начинки");
    }

    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textBurgerMainPage));
    }

    public void waitForLoadBunsHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsImg));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsText));
    }

    public void waitForLoadSaucesHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesImg));
        waitDocReady();
    }

    public void waitForLoadFillingsHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsImg));
        waitDocReady();
    }

    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
        waitDocReady();
    }

    public void waitDocReady() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }
}

