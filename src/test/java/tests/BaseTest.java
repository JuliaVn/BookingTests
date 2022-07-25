package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;
import pages.Header;
import pages.HomePage;
import pages.SearchResultPage;
import fileActions.Input;

import java.util.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {

    private WebDriver driver = null;
    private Header header;
    private HomePage homePage;
    private SearchResultPage searchResultPage;
    private static final String BOOKING_URL = "https://www.booking.com";
    private static final String FILE_NAME = "result.txt";

    @BeforeClass
    public void name(ITestContext context) {
        List<String> methodsList = new ArrayList<String>();
        ITestNGMethod[] a = context.getAllTestMethods();
        for (ITestNGMethod b : a) {
            if (b.getRealClass() == this.getClass()) {
                methodsList.add(b.getConstructorOrMethod().getName());
            }
        }
        Input.inputDataIntoFile(methodsList, FILE_NAME);
    }

    @BeforeClass
    public void init(){
        chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(BOOKING_URL);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @AfterClass
    public void terminate(){
        driver.quit();
        driver = null;
    }

    public WebDriver getDriver(){
        if (driver == null) return new ChromeDriver();
        return driver;
    }

    public Header getHeader() {
        return new Header(getDriver());
    }

    public HomePage getHomePage() {
        return new HomePage(getDriver());
    }

    public SearchResultPage getSearchResultPage(){
        return  new SearchResultPage(getDriver());
    }
}
