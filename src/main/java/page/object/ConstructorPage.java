package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class ConstructorPage {

    private final WebDriver driver;
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By bunsButton = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesButton = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By toppingsButton = By.xpath(".//span[text()='Начинки']/parent::div");
    private final String classAttributeName = "class";
    private final String currentTabClassName = "tab_tab_type_current__2BEPc";


    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка отображения ингредиентов из раздела Булки")
    public boolean isBunsSelected() {
        String classAttribute = driver.findElement(bunsButton).getAttribute(classAttributeName);
        return classAttribute.contains(currentTabClassName);
    }

    @Step("Проверка отображения ингредиентов из раздела Соусы")
    public boolean isSaucesSelected() {
        String classAttribute = driver.findElement(saucesButton).getAttribute(classAttributeName);
        return classAttribute.contains(currentTabClassName);
    }

    @Step("Проверка отображения ингредиентов из раздела Начинки")
    public boolean isToppingsSelected() {
        String classAttribute = driver.findElement(toppingsButton).getAttribute(classAttributeName);
        return classAttribute.contains(currentTabClassName);
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

