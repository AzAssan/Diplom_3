import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPageEnterTest {
    private final WebDriver driver = WebDriverCreator.createWebDriver();
    private final String loginURL = "https://stellarburgers.nomoreparties.site/login";

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    public void testLoginFromMainPage() {
        enterMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickSignInButton();

        assertEquals(loginURL, driver.getCurrentUrl());

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
    }

    @Test
    @DisplayName("Вход через ссылку в личном кабинете")
    public void testLoginFromAccountPage() {
        enterMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        assertEquals(loginURL, driver.getCurrentUrl());

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
    }

    @Test
    @DisplayName("Вход через кнопку в странице регистрации")
    public void testLoginFromRegistrationPage() {
        enterRegistrationPage();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        assertTrue(registrationPage.isPageOpened());
        registrationPage.clickSignInLink();

        assertEquals(loginURL, driver.getCurrentUrl());

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
    }

    @Test
    @DisplayName("Вход через ссылку в странице восстановления пароля")
    public void testLoginFromRestorePasswordForm() {
        enterForgotPasswordPage();

        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        assertTrue(restorePasswordPage.isPageOpened());
        restorePasswordPage.clickSignInLink();

        assertEquals(loginURL, driver.getCurrentUrl());

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
    }

    private void enterMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    private void enterRegistrationPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    private void enterForgotPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }
}

