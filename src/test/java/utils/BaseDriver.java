package utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BaseDriver {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void setUp() {
        switch (ConfigReader.getProperty("browser").toLowerCase()) {
            case "firefox":
                FirefoxOptions options=new FirefoxOptions();
                options.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(options);
                break;
            case "edge":
                EdgeOptions options1=new EdgeOptions();
                options1.setAcceptInsecureCerts(true);
                driver = new EdgeDriver(options1);
                break;
            case "chrome":
                ChromeOptions options2 = new ChromeOptions();
                options2.setAcceptInsecureCerts(true);
                options2.setPageLoadStrategy(PageLoadStrategy.EAGER);

                driver = new ChromeDriver(options2);
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getIntProperty("pageloadtimeout")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getIntProperty("explicit.wait")));
    }
    public static WebDriver getDriver() {
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            ReusableMethods.threadWait(3);
            driver.quit();
            driver = null;
        }
    }
}
