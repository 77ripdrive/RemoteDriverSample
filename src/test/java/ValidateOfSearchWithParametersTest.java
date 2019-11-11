import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.NavigationPage;
import pages.QuotePage;
import pages.YahooFinancePage;


public class ValidateOfSearchWithParametersTest extends DriverManager {

    protected static NavigationPage navigationPage;
    protected static YahooFinancePage yahooFinancePage;
    protected static QuotePage quotePage;

    @ParameterizedTest

    @DisplayName("Setting the environment")
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1)
    void setUp(String browser, String browserVersion,
               String platformName, RunType runType,
               String organisation, String fullName, String expectedDate) {

        this.createDriver(browser, browserVersion, platformName, runType);
        navigationPage = new NavigationPage(getDriver());
        yahooFinancePage = navigationPage.goToYahooFinancePage();
        quotePage = navigationPage.goToQuotePage();

        yahooFinancePage.searchForOrganisation(getDriver(), organisation, fullName);
        Assertions.assertEquals(expectedDate, quotePage.receiveDateOfStartTrading());
    }

    @AfterEach
    void tearDown() {
        closeDriver();
    }


}
