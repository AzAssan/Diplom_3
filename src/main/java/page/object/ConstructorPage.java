package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorPage {

    private final WebDriver driver;
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By pageTitle = By.xpath(".//h1[text()='Соберите бургер']");
    private final By bunsButton = By.xpath(".//span[text()='Булки']");
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");
    private final By toppingsButton = By.xpath(".//span[text()='Начинки']");
    private final By bun_R2_D3 = By.xpath(".//img[@alt='Флюоресцентная булка R2-D3']");
    private final By bun_N_200i = By.xpath(".//img[@alt='Краторная булка N-200i']");
    private final By sauce_Spicy_X = By.xpath(".//img[@alt='Соус Spicy-X']");
    private final By traditionalGalacticSauce = By.xpath(".//img[@alt='Соус традиционный галактический']");
    private final By meat_Protostomia = By.xpath(".//img[@alt='Мясо бессмертных моллюсков Protostomia']");
    private final By fruitsOfFallenianTree = By.xpath(".//img[@alt='Плоды Фалленианского дерева']");


    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка отображения ингредиентов из раздела Булки")
    public boolean isBuns() {
        return driver.findElement(bun_R2_D3).isDisplayed() &&
                driver.findElement(bun_N_200i).isDisplayed();
    }

    @Step("Проверка отображения ингредиентов из раздела Соусы")
    public boolean isSauces() {
        return driver.findElement(sauce_Spicy_X).isDisplayed() &&
                driver.findElement(traditionalGalacticSauce).isDisplayed();
    }

    @Step("Проверка отображения ингредиентов из раздела Начинки")
    public boolean isToppings() {
        return driver.findElement(meat_Protostomia).isDisplayed() &&
                driver.findElement(fruitsOfFallenianTree).isDisplayed();
    }

    @Step("Проверка отображения заголовка страницы")
    public boolean isPageTitle() {
        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Нажатие кнопки 'Булки'")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }
    @Step("Нажатие кнопки 'Соусы'")
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }
    @Step("Нажатие кнопки 'Начинки'")
    public void clickToppingsButton() {
        driver.findElement(toppingsButton).click();
    }

    @Step("Нажатие кнопки 'Лого'")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Нажатие кнопки 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
}

