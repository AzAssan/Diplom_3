import driver.WebDriverCreator;
import enums.Browser;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.object.ConstructorPage;

import static org.junit.Assert.assertTrue;
@RunWith(Parameterized.class)
public class ConstructorPageTest {
    private final WebDriver driver;

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{
                {Browser.CHROME},
                {Browser.YANDEX},
        };
    }

    public ConstructorPageTest(Browser browser) {
        driver = WebDriverCreator.createWebDriver(browser);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void enterMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testGoToBunsSection() {
        enterMainPage();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickConstructorButton();
        assertTrue(constructorPage.isPageTitle());

        // сначала нажимаем 'Соусы', чтобы после перейти в 'Булки'
        constructorPage.clickSaucesButton();
        constructorPage.clickBunsButton();
        assertTrue(constructorPage.isBuns());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testGoToSaucesSection() {
        enterMainPage();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickConstructorButton();
        assertTrue(constructorPage.isPageTitle());

        constructorPage.clickSaucesButton();
        assertTrue(constructorPage.isSauces());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testGoToToppingsSection() {
        enterMainPage();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickConstructorButton();
        assertTrue(constructorPage.isPageTitle());

        constructorPage.clickToppingsButton();
        assertTrue(constructorPage.isToppings());
    }
}
