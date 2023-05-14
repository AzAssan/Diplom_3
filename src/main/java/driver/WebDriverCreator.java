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
//        System.setProperty("webdriver.chrome.driver", System.getenv("YANDEX_DRIVER_PATH"));

        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver110.exe");
        ChromeOptions options = new ChromeOptions();
//        String yandexDriverPath = System.getenv("YANDEX_BROWSER_PATH");
        options.setBinary("C:\\Users\\AAssan\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

        return new ChromeDriver(options);
    }

    private static WebDriver createChromeDriver() {
        //        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER_PATH"));
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver112.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-options=*");
        return new ChromeDriver(options);
    }
}
