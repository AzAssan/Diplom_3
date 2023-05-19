import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class ConstructorPageTest {
    private final WebDriver driver = WebDriverCreator.createWebDriver();

    @After
    public void tearDown() {
        driver.quit();
    }

    private void enterMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testGoToBunsSection() {
        enterMainPage();

        ConstructorPage constructorPage = new ConstructorPage(driver);

        // сначала нажимаем 'Соусы', чтобы после перейти в 'Булки'
        constructorPage.clickSaucesButton();
        constructorPage.clickBunsButton();
        assertTrue(constructorPage.isBunsSelected());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testGoToSaucesSection() {
        enterMainPage();

        ConstructorPage constructorPage = new ConstructorPage(driver);

        constructorPage.clickSaucesButton();
        assertTrue(constructorPage.isSaucesSelected());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testGoToToppingsSection() {
        enterMainPage();

        ConstructorPage constructorPage = new ConstructorPage(driver);

        constructorPage.clickToppingsButton();
        assertTrue(constructorPage.isToppingsSelected());
    }
}
