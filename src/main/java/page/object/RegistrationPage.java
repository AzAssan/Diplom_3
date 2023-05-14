package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver driver;
    private final By pageTitle = By.xpath(".//h2[text()='Регистрация']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By nameField = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By emailField = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordField = By.name("Пароль");
    private final By errorIncorrectPassword = By.xpath(".//p[@class='input__error text_type_main-default']");
    private final By signInLink = By.className("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Регистрация пользователя с email: {email} и паролем: {password}")
    public void register(String name, String email, String password) {
        WebElement nameField = driver.findElement(this.nameField);
        nameField.sendKeys(name);

        WebElement emailField = driver.findElement(this.emailField);
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(this.passwordField);
        passwordField.sendKeys(password);

        WebElement registerButton = driver.findElement(this.registerButton);
        registerButton.click();
    }

    @Step("Получение сообщения об ошибке")
    public boolean isErrorIncorrectPasswordExists() {
        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(errorIncorrectPassword));

            WebElement errorElement = driver.findElement(errorIncorrectPassword);
            return errorElement.getText().equals("Некорректный пароль");
        } catch (TimeoutException e) {
            return false;
        }

    }
    @Step("Проверка, что страница регистрации открыта")
    public boolean isPageOpened() {
        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    @Step("Нажать на ссылку Войти")
    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }

}


