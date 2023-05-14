import clients.UserClient;
import driver.WebDriverCreator;
import enums.Browser;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.object.*;
import user.information.UserCreds;
import user.information.UserRequest;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTests {
    private final WebDriver driver;
    private final UserRequest user = UserRequest.generate();
    private final UserClient apiClient = new UserClient();
    private UserCreds creds;

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{
                {Browser.CHROME},
                {Browser.YANDEX},
        };
    }

    public LoginTests(Browser browser) {
       driver = WebDriverCreator.createWebDriver(browser);
    }

    @Before
    public void setUp() {
        creds = apiClient.userCreate(user);
    }

    @After
    public void tearDown() {
        driver.quit();
        apiClient.userDelete(creds);
    }

    @Test
    @DisplayName("Успешный логин пользователя через кнопку 'Войти в аккаунт' на главной странице")
    public void testLoginFromMainPage() {
        enterMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickSignInButton();

        checkLogin(driver);
    }

    @Test
    @DisplayName("Успешный логин пользователя через ссылку 'Личный кабинет'")
    public void testLoginFromAccountPage() {
        enterMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        checkLogin(driver);
    }

    @Test
    @DisplayName("Успешный логин пользователя через кнопку 'Регистрации'")
    public void testLoginFromRegistrationPage() {
        enterRegistrationPage();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        assertTrue(registrationPage.isPageOpened());
        registrationPage.clickSignInLink();

        checkLogin(driver);
    }

    @Test
    @DisplayName("Успешный логин пользователя через страницу 'Восстановленя пароля'")
    public void testLoginFromRestorePasswordForm() {
        enterForgotPasswordPage();

        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        assertTrue(restorePasswordPage.isPageOpened());
        restorePasswordPage.clickSignInLink();

        checkLogin(driver);
    }

    public void checkLogin(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());

        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.isUserLoggedIn());
    }

    public void enterMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    public void enterRegistrationPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    public void enterForgotPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }


}

