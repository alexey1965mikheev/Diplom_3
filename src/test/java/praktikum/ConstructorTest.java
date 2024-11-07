package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход в раздел 'Булки'")
    @Description("Проверка перехода в раздел 'Булки' и появление картинки с булкой")
    public void transitionToBunsSectionTest() {
        mainPage.clickOnSaucesButton();
        mainPage.clickOnBunsButton();
        mainPage.checkToppingBun();
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    @Description("Проверка перехода в раздел 'Соусы' и появление картинки с соусом")
    public void transitionToSaucesSectionTest() {
        mainPage.clickOnSaucesButton();
        mainPage.checkToppingSauce();
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    @Description("Проверка перехода в раздел 'Начинки' и появление картинки с начинкой")
    public void transitionToFillingsSectionTest() {
        mainPage.clickOnFillingButton();
        mainPage.checkToppingFillings();
    }
}