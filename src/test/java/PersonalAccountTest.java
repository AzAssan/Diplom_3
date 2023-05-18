import clients.UserClient;
import driver.WebDriverCreator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import page.object.ConstructorPage;
import page.object.PersonalAccountPage;
import page.object.MainPage;
import page.object.LoginPage;
import user.information.UserCreds;
import user.information.UserRequest;


import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {
    private final WebDriver driver = WebDriverCreator.createWebDriver();
    private final UserRequest user = UserRequest.generate();
    private final UserClient apiClient = new UserClient();
    private UserCreds creds;


    public void enterMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site");
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
    @DisplayName("Проверка перехода на страницу личного кабинета")
    public void testGoToPersonalAccountPage() {
        enterMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.isUserLoggedIn());

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        assertTrue(personalAccountPage.isInfoText());
    }

    @Test
    @DisplayName("Проверка перехода на страницу конструктора")
    public void testGoToConstructorPage() {
        enterMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.isUserLoggedIn());

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        assertTrue(personalAccountPage.isInfoText());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickConstructorButton();
        assertTrue(mainPage.isPageTitle());
    }


    @Test
    @DisplayName("Проверка выхода из личного кабинета")
    public void testLogoutFromPersonalAccount() {
        enterMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.isUserLoggedIn());

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        assertTrue(personalAccountPage.isInfoText());
        personalAccountPage.clickLogoutButton();

        assertTrue(loginPage.isPageOpened());
    }

    @Test
    @DisplayName("Успешный переход на главную страницу сайта при нажатии на лого из личного кабинета")
    public void testSuccessfulLogoToMainPage() {
        enterMainPage();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.isUserLoggedIn());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLogo();
        assertTrue(mainPage.isPageTitle());
    }

}

