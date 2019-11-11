import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    protected RunType runType;

    private static final  ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public enum RunType {LOCAL, GRID}

    private static void createLocalDriver(String browser, String browserVersion, String platformName) {
        MutableCapabilities capabilities ;
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications", "--disable-popup-blocking", "–disable-infobars");
                capabilities = chromeOptions;
                capabilities.setCapability("browserVersion", browserVersion);
                capabilities.setCapability("platformName", platformName);
                driver.set(new ChromeDriver(capabilities));
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications", "--disable-popup-blocking", "–disable-infobars");
                capabilities = firefoxOptions;
                capabilities.setCapability("browserVersion", browserVersion);
                capabilities.setCapability("platformName", platformName);
                driver.set(new FirefoxDriver(firefoxOptions));
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications", "--disable-popup-blocking", "–disable-infobars");
                capabilities = edgeOptions;
                capabilities.setCapability("browserVersion", browserVersion);
                capabilities.setCapability("platformName", platformName);
                driver.set(new EdgeDriver(capabilities));
            }
        }
    }

    private static void createGridDriver(String browser, String browserVersion, String platformName) {
        DesiredCapabilities gridCapabilities = new DesiredCapabilities();
        gridCapabilities.setBrowserName(browser);
        gridCapabilities.setVersion(browserVersion);
        gridCapabilities.setPlatform(Platform.extractFromSysProperty(platformName));
        String GRID_REMOTE_URL = "http://localhost:4455/wd/hub";
        try {
            driver.set(new RemoteWebDriver(new URL(GRID_REMOTE_URL), gridCapabilities));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    protected static   void createDriver(String browser, String browserVersion, String platformName, RunType runType) {

        switch (runType) {
            case LOCAL:
               createLocalDriver(browser,browserVersion,platformName);
                break;
            case GRID:
                createGridDriver(browser,browserVersion,platformName);
                break;
        }
    }

    protected static WebDriver getDriver() {
        return driver.get();
    }

    protected static void closeDriver() {
        driver.get().quit();
        driver.remove();
    }

}
