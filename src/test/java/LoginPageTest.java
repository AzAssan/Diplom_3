import clients.UserClient;
import com.github.javafaker.Faker;
import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.LoginPage;
import user.information.UserCreds;
import user.information.UserRequest;

import static org.junit.Assert.assertTrue;

public class LoginPageTest {

    private final WebDriver driver = WebDriverCreator.createWebDriver();
    private final UserClient apiClient = new UserClient();
    private final Faker faker = new Faker();
    private UserRequest user;
    private UserCreds creds;


    @Before
    public void setUp() {
        user = new UserRequest(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().firstName()
        );
        creds = apiClient.userCreate(user);
    }

    @After
    public void tearDown() {
        driver.quit();
        apiClient.userDelete(creds);
    }

    @Test
    @DisplayName("Успешный логин пользователя")
    public void checkLogin() {
        String loginURL = "https://stellarburgers.nomoreparties.site/login";
        driver.get(loginURL);

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());

        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.isUserLoggedIn());
    }
}
