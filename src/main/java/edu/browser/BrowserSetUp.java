package edu.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BrowserSetUp {
    protected static WebDriver driver;

    public static WebDriver initDriver() throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");
        BrowserType browserType = BrowserType.valueOf(browserProperty);

        switch (browserType) {
            case Chrome:
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                initImplicityWait();
                break;
            case Yandex:
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\админ\\.cache\\selenium\\chromedriver\\win64\\132.0.6834.159\\chromedriver.exe");
                ChromeOptions yandexProperties = new ChromeOptions();
                driver = new ChromeDriver(yandexProperties);
                driver.manage().window().maximize();
                initImplicityWait();
                break;
            default:
                throw new IllegalArgumentException("Unknown browser");
        }
        return driver;
    }
    public static void initImplicityWait(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

}

