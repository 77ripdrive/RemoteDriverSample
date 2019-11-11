package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Action;

public class QuotePage {

    private static final String PRE_LOCATOR_FOR_TABLE = "//*[@id='Col1-1-Financials-Proxy']//tr[%s]/td[2]/span";
    private static final String PRE_LOCATOR_FOR_DATE_SEARCH = "//span[text()='%s']";
    private static final String PRE_LOCATOR_FOR_MAX_VALUE = "//span[text()='%s']/following::td[2]/span";
    private static final String PRE_LOCATOR_FOR_MIN_VALUE = "//span[text()='%s']/following::td[3]/span";
    private static final String PRE_LOCATOR_FOR_START_VALUE = "//span[text()='%s']/following::td[1]/span";

    @FindBy(xpath = "//*[@id='quote-nav']//span[text()='Historical Data']")
    private WebElement choiceHistoricalData;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//input[@data-test='date-picker-full-range']")
    private WebElement choiceTimePeriod;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Max']")
    private WebElement choiceMaxTimePeriod;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//input[@name='startDate']")
    private WebElement dateOfStartTrading;

    @FindBy(xpath = "//*[@id='Col1-0-Profile-Proxy']//span[text()='Industry']")
    private WebElement fieldIndustry;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//input[@name='startDate']")
    private WebElement dataOfSearch;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Done']")
    private WebElement pushDoneButton;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Daily']")
    private WebElement frequentlyDaily;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Weekly']")
    private WebElement frequentlyWeekly;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Apply']")
    private WebElement buttonApply;

    public QuotePage() {
    }

    public String receiveDateOfStartTrading()
    {
        choiceHistoricalData.click();
        choiceTimePeriod.click();
        choiceMaxTimePeriod.click();
        return dateOfStartTrading.getAttribute("value");
    }

    public String receiveValueOfStartTrading(WebDriver driver,String dateOldTime, String dateForSearch)
    {
        setDateOfTimePeriod(dateOldTime);
        frequentlyDaily.click();
        frequentlyWeekly.click();
        buttonApply.click();
        return driver.findElement(Action.createXpath(PRE_LOCATOR_FOR_START_VALUE, Action.dateFormat(dateForSearch)))
                .getText();
    }

    public void setDateOfTimePeriod(String dataOfTrade)
    {
        choiceHistoricalData.click();
        choiceTimePeriod.click();
        dataOfSearch.sendKeys(dataOfTrade);
        pushDoneButton.click();
    }
}
