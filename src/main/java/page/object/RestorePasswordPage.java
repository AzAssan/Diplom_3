package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestorePasswordPage {
    private final WebDriver driver;
    private final By signInLink = By.className("Auth_link__1fOlj");
    private final By pageTitle = By.xpath(".//h2[text()='Восстановление пароля']");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка открытия страницы восстановления пароля")
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


