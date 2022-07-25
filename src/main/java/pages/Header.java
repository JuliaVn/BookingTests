package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage{

    @FindBy(xpath = "//button[@data-modal-id='language-selection']")
    private WebElement chooseLanguageButton;

    @FindBy(xpath = "//div[contains(@class,'bui-modal')][@id='language-selection']")
    private WebElement languagePopup;

    @FindBy(xpath = "//button[@data-modal-header-async-type='currencyDesktop']")
    private WebElement chooseCurrencyButton;

    @FindBy(xpath = "//h2[contains(text(),'currency')]/ancestor::div[contains(@class,'bui-modal--active')]")
    private WebElement currencyPopup;

    public Header(WebDriver driver) {
        super(driver);
    }

    private WebElement languageButtonInPopup(String language){
        return driver.findElement(By.xpath(String.format("//div[contains(text(),'%s')]", language)));
    }

    private WebElement currencyButtonInPopup(String currency){
        return driver.findElement(By.xpath(String.format("//div[contains(text(),'%s')]", currency)));
    }

    public void switchLanguage(String language){
        waitElementToBeClickable(chooseLanguageButton).click();
        waitElementToBeVisible(languagePopup);
        languageButtonInPopup(language).click();
    }

    public void switchCurrency(String currency){
        waitElementToBeClickable(chooseCurrencyButton).click();
        waitElementToBeVisible(currencyPopup);
        currencyButtonInPopup(currency).click();
    }
}
