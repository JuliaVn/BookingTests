package pages;

import model.PropertyCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PropertyPage extends BasePage{

    @FindBy(xpath = "//div[@id='basiclayout']")
    private WebElement propertyCardLayout;

    @FindBy(xpath = "//span[contains(@class,'hp_address_subtitle')]")
    private WebElement propertyAddress;

    @FindBy(xpath = "//div[contains(@class,'xp__dates__checkin')]//div[contains(@class,'month-year')]//span")
    private WebElement monthCheckIn;

    @FindBy(xpath = "//div[contains(@class,'xp__dates__checkin')]//div[contains(@class,'day')]//span")
    private WebElement dayCheckIn;

    @FindBy(xpath = "//div[contains(@class,'xp__dates__checkout')]//div[contains(@class,'month-year')]//span")
    private WebElement monthCheckOut;

    @FindBy(xpath = "//div[contains(@class,'xp__dates__checkout')]//div[contains(@class,'day')]//span")
    private WebElement dayCheckOut;

    public PropertyPage(WebDriver driver) {
        super(driver);
    }

    public PropertyCard getProperty(){
        waitElementToBeVisible(propertyCardLayout);
        waitElementToBeVisible(propertyAddress);
        var property = new PropertyCard();
        property.setAddress(propertyAddress.getText());
        property.setCheckInMonth(monthCheckIn.getAttribute("textContent"));
        property.setCheckInDay(Integer.parseInt(dayCheckIn.getAttribute("textContent").replaceAll("[^0-9]", "")));
        property.setCheckOutMonth(monthCheckOut.getAttribute("textContent"));
        property.setCheckOutDay(Integer.parseInt(dayCheckOut.getAttribute("textContent").replaceAll("[^0-9]", "")));
        return property;
    }
}
