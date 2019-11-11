package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage {

    private WebDriver driver;

    public NavigationPage(WebDriver driver) {
        this.driver = driver;
    }

    public YahooFinancePage goToYahooFinancePage() {
        YahooFinancePage yahooFinancePage = new YahooFinancePage();
        PageFactory.initElements(driver, yahooFinancePage);
        driver.get(yahooFinancePage.getBaseUrl());
        return yahooFinancePage;
    }

    public QuotePage goToQuotePage() {
        QuotePage quotePage = new QuotePage();
        PageFactory.initElements(driver, quotePage);
        return quotePage;
    }

}
