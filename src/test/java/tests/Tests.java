package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tests extends BaseTest{

    private static final String LANGUAGE = "UK";
    private static final String SEARCH_KEYWORD = "New York";
    private static final String MONTH_CHECK_IN = "December 2022";
    private static final String MONTH_CHECK_OUT = "December 2022";
    private static final int DAY_CHECK_IN = 1;
    private static final int DAY_CHECK_OUT = 30;
    private static final String CURRENCY = "USD";
    private static final int NUMBER_OF_OPTION = 2;


    @Test
    public void checkThatSearchResultContainsSearchWordAndRangeDate(){
        getHeader().switchLanguage(LANGUAGE);
        getHomePage().enterDestination(SEARCH_KEYWORD);
        getHomePage().chooseDates(MONTH_CHECK_IN, MONTH_CHECK_OUT, DAY_CHECK_IN, DAY_CHECK_OUT);
        getHomePage().clickSearchButton();
        var data = getSearchResultPage().getPropertyCardsData();
        for (var property : data) {
            assertEquals(property.getCheckInMonth(), MONTH_CHECK_IN, "Check-in months don't match.");
            assertEquals(property.getCheckInDay(), DAY_CHECK_IN, "Check-in days don't match.");
            assertEquals(property.getCheckOutMonth(), MONTH_CHECK_OUT, "Check-out months don't match.");
            assertEquals(property.getCheckOutDay(), DAY_CHECK_OUT, "Check-out days don't match.");
        }
    }

    @Test
    public void checkSelectedPriceRange(){
        getHeader().switchLanguage(LANGUAGE);
        getHeader().switchCurrency(CURRENCY);
        getHomePage().enterDestination(SEARCH_KEYWORD);
        getHomePage().chooseDates(MONTH_CHECK_IN, MONTH_CHECK_OUT, DAY_CHECK_IN, DAY_CHECK_OUT);
        getHomePage().clickSearchButton();
        getSearchResultPage().selectOptionFromPriceRange(NUMBER_OF_OPTION);
        var range = getSearchResultPage().getMinMaxPriceRange(NUMBER_OF_OPTION);
        var days = getSearchResultPage().getAmountNights(MONTH_CHECK_IN, MONTH_CHECK_OUT, DAY_CHECK_IN, DAY_CHECK_OUT);
        var minPrice = Integer.parseInt(range[0]) * days;
        var maxPrice = Integer.parseInt(range[1]) * days;
        var priceList = getSearchResultPage().getPriceList();
        for (var price : priceList) {
            assertTrue(minPrice <= price && price <= maxPrice,
                    String.format("$%d is out of the range of $%d and $%d.", price, minPrice, maxPrice));
        }
    }
}
