package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage extends BasePage{

    @FindBy(xpath = "//input[contains(@class,'sb-destination__input')]")
    private WebElement searchInput;

    @FindBy(xpath = "//div[contains(@class,'sb-date-field')][@data-mode='checkin']")
    private WebElement dateCheckInButton;

    @FindBy(xpath = "//div[contains(@class,'xp-calendar')]//div[@class='bui-calendar']")
    private WebElement calendarPopup;

    @FindBy(xpath = "//div[contains(@class,'bui-calendar__control--next')]")
    private WebElement calendarControlNext;

    @FindBy(xpath = "(//div[@class='bui-calendar__month'])[1]")
    private WebElement calendarMonth;

    @FindBy(xpath = "(//table[@class='bui-calendar__dates'])[1]//td[contains(@class,'bui-calendar__date')]/span/span")
    private List<WebElement> calendarDateList;

    @FindBy(xpath = "//button[contains(@class,'sb-searchbox__button')]")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterDestination(String keyword){
        waitElementToBeClickable(searchInput).sendKeys(keyword);
    }

    public void selectDate(String month, int date){
        String currentMonth = calendarMonth.getText();
        while(!currentMonth.contains(month)){
            waitElementToBeClickable(calendarControlNext).click();
            currentMonth = calendarMonth.getText();
        }
        for (var webElement : calendarDateList) {
            if(Integer.parseInt(webElement.getText()) == date) {
                webElement.click();
                return;
            }
        }
    }

    public void chooseDates(String monthCheckIn, String monthCheckOut, int dayCheckIn, int dayCheckOut){
        dateCheckInButton.click();
        waitElementToBeVisible(calendarPopup);
        selectDate(monthCheckIn, dayCheckIn);
        selectDate(monthCheckOut, dayCheckOut);
    }

    public void clickSearchButton(){
        searchButton.click();
    }
}
