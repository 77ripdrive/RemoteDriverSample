package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Action;

public class YahooFinancePage {

    private static final String BASE_URL = "";
    private static final String PRE_LOCATOR_FOR_QUOTE = "//div[contains(text(),'%s')]";

    @FindBy(xpath = "//*[@id='yfin-usr-qry']")
    private WebElement searchForNews;

    @FindBy(xpath = "//a[@title='Industries']")
    private WebElement industriesLink;

    @FindBy(xpath = "//*[@id='data-util-col']//a[@title='Recently Viewed']")
    private WebElement recentlyViewed;

    @FindBy(xpath = "//*[@id='Col2-0-SymbolLookup-Proxy']//input[@placeholder='Quote Lookup']")
    private WebElement quoteLookup;

    public YahooFinancePage() {
    }

    public  String getBaseUrl() {
        return BASE_URL;
    }

    public void searchForOrganisation(WebDriver driver, String organisationForSearch, String chooseFromOrganisationForSearch)
    {
        searchForNews.sendKeys(organisationForSearch);
        Action.clickWithChoice(driver,PRE_LOCATOR_FOR_QUOTE, chooseFromOrganisationForSearch);

    }

}
