package driver;

import enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createWebDriver(Browser browser) {

        switch (browser) {
            case YANDEX:
                return createYandexDriver();
            case CHROME:
                return createChromeDriver();
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", System.getenv("YANDEX_DRIVER_PATH"));
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));

        return new ChromeDriver(options);
    }

    private static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER_PATH"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-options=*");
        return new ChromeDriver(options);
    }
}
