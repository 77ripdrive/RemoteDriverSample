package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Action {

    public static String dateFormat(String oldDateString) {
        LocalDate date = LocalDate.parse(oldDateString, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        return date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy", new Locale("us")));
    }

    public static By createXpath(String format, Object... args) {
        return By.xpath(String.format(format, args));
    }

    public static void chooseElementFromList(List<WebElement> webElements, String option) {
        for (WebElement webElement : webElements) {
            if (webElement.getText().equalsIgnoreCase(option)) {
                webElement.click();
                break;
            }
        }
    }

    public static void clickWithChoice(WebDriver driver, String preLocator, String chooseFromOrganisationForSearch) {
        driver.findElement(createXpath(preLocator, chooseFromOrganisationForSearch)).click();
    }

}
