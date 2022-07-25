package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

public class BasePage {

    protected static WebDriver driver;
    private static WebDriverWait wait;
    private static final long timeToWait = 30;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
    }

    public void waitElementToBeVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitClickabilityOfElements(List<WebElement> elements){
        for (var element : elements) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    public void waitElementToBeInvisible(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public long getAmountNights(String monthCheckIn, String monthCheckOut, int dayCheckIn, int dayCheckOut){
        String checkInDate = dayCheckIn + " " + monthCheckIn;
        String checkOutDate = dayCheckOut + " " + monthCheckOut;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.US);
        LocalDate firstDate = LocalDate.parse(checkInDate, formatter);
        LocalDate secondDate = LocalDate.parse(checkOutDate, formatter);
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }
}
