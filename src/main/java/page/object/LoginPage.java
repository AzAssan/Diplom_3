package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");
    private final By pageTitle = By.xpath(".//h2[text()='Вход']");
    private final By emailField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By signInButton = By.xpath(".//button[text()='Войти']");
    private final By infoText = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Вход с email {0} и паролем {1}")
    public void login(String email, String password) {
        WebElement nameField = driver.findElement(emailField);
        nameField.sendKeys(email);

        WebElement passwordField = driver.findElement(this.passwordField);
        passwordField.sendKeys(password);

        clickSignInButton();
    }

    public boolean isPageOpened() {
        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    public boolean isUserLoggedIn() {
        clickPersonalAccountButton();

        try {
            new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(infoText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}



