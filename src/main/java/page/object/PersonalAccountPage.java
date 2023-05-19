package page.object;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final By infoText = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Выход из личного кабинета")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Проверка наличия информационного блока на странице личного кабинета")
    public boolean isInfoText() {
        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(infoText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}


