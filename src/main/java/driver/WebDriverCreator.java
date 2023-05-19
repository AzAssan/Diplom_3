package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createWebDriver() {
        System.setProperty("webdriver.chrome.driver", System.getenv("WEB_DRIVER_PATH"));

        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getenv("WEB_BROWSER_PATH"));
        options.addArguments("--remote-allow-options=*");

        return new ChromeDriver(options);
    }

}
