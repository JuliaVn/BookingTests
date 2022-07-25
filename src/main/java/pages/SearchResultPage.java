package pages;

import model.PropertyCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class SearchResultPage extends BasePage{

    @FindBy(xpath = "//div[@id='search_results_table']")
    private WebElement searchResultsTable;

    @FindBy(xpath = "//div[@data-testid='property-card']")
    private List<WebElement> propertyCardList;

    @FindBy(xpath = "//div[@data-testid='filters-sidebar']")
    private WebElement filterSidebar;

    @FindBy(xpath = "//div[@data-testid='filters-group-toggle-style']/following-sibling::div//div[@data-testid='filters-group-label-content']")
    private List<WebElement> priceRangeOptionsList;

    @FindBy(xpath = "//div[@data-testid='overlay-card']")
    private WebElement overlayCard;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public List<PropertyCard> getPropertyCardsData(){
        waitElementToBeVisible(searchResultsTable);
        waitClickabilityOfElements(propertyCardList);
        String mainWindowHandle = driver.getWindowHandle();
        var propertyList = new LinkedList<PropertyCard>();
        for (var element : propertyCardList) {
            waitElementToBeClickable(element);
            element.findElement(By.xpath(".//div[@data-testid='availability-cta']")).click();
            var windowHandles = driver.getWindowHandles();
            for (var window : windowHandles) {
                driver.switchTo().window(window);
            }
            var propertyPage = new PropertyPage(driver);
            propertyList.add(propertyPage.getProperty());
            driver.close();
            driver.switchTo().window(mainWindowHandle);
        }
        return propertyList;
    }

    public void selectOptionFromPriceRange(int number){
        waitElementToBeVisible(filterSidebar);
        priceRangeOptionsList.get(number-1).click();
    }

    public String[] getMinMaxPriceRange(int number){
        String priceRange = priceRangeOptionsList.get(number-1).getText();
        String range = priceRange.replaceAll("[US$\\s]", "");
        return range.split("-");
    }

    public List<Integer> getPriceList(){
        waitElementToBeInvisible(overlayCard);
        var priceList = new LinkedList<Integer>();
        for (var property : propertyCardList) {
            var price = property.findElement(By.xpath("(.//div[@data-testid='price-and-discounted-price']/span)[last()]"));
            priceList.add(Integer.parseInt(price.getText().replaceAll("[US$,]","")));
        }
        return priceList;
    }
}
