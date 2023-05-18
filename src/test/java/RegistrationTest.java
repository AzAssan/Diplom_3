import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.LoginPage;
import page.object.RegistrationPage;
import user.information.UserRequest;

import static org.junit.Assert.assertTrue;
public class RegistrationTest {
    private final WebDriver driver = WebDriverCreator.createWebDriver();

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        enterRegistrationPage();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        assertTrue(registrationPage.isPageOpened());
        UserRequest user = UserRequest.generate();
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());

        // Проверка, что после регистрации происходит переход на другую страницу (например, на страницу личного кабинета)
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    public void testErrorForIncorrectPassword() {
        enterRegistrationPage();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        assertTrue(registrationPage.isPageOpened());

        UserRequest user = UserRequest.generate();
        String password = "12345"; // Некорректный пароль (менее 6 символов)
        registrationPage.register(user.getName(), user.getEmail(), password);
        // Проверка, что на странице регистрации появляется сообщение об ошибке для некорректного пароля
        assertTrue(registrationPage.isErrorIncorrectPasswordExists());
    }

    private void enterRegistrationPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

}
