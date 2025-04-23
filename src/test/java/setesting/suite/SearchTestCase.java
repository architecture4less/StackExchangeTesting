package setesting.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestBrowser;
import setesting.TestListener;
import setesting.TestSteps;

import java.util.Optional;

import static setesting.TestUtils.sleep;

/**
 * Test Stack Exchange's question search functionality.
 */
@Listeners(TestListener.class)
public final class SearchTestCase {
    private SearchTestCase() {}

    @AfterMethod
    private static void waitAfterEach() {
        TestSteps.limitTestSpeed();
    }

    @AfterClass
    private static void waitAfterAll() {
        TestSteps.limitTestCaseSpeed();
    }

    /**
     * Validate the ability to search for and view a question.
     */
    @Test(priority = 16, description = "Search for and view a question")
    public static void searchQuestionTest() {
        WebDriver driver = TestBrowser.OPERA.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/search");
            TestSteps.doCaptcha(driver);
            {
                WebElement searchBar = driver.findElement(By.cssSelector("form#bigsearch input"));
                String searchText = "xpath";
                searchBar.sendKeys(searchText);
                sleep(1.0);

                searchBar.sendKeys(Keys.RETURN);
                TestSteps.doCaptcha(driver);
            }

            driver.findElements(By.cssSelector("div.search-results div.result-link a")).get(0).click();
            sleep(2.0);
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to search for and view a community.
     */
    @Test(priority = 17, description = "Search for and view a community")
    public static void searchCommunityTest() {
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/sites?view=list#oldest");
            {
                Assert.fail("no community search bar");
                // WebElement searchBar = driver.findElement(By.cssSelector("form input"));
                // String searchText = "math";
                // searchBar.sendKeys(searchText);
                // sleep(1.0);

                // searchBar.sendKeys(Keys.RETURN);
                // sleep(1.0);
            }
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to search for and view a user.
     */
    @Test(priority = 18, description = "Search for and view a user")
    public static void searchUserTest() {
        WebDriver driver = TestBrowser.FIREFOX.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/search");
            TestSteps.doCaptcha(driver);
            {
                WebElement searchBar = driver.findElement(By.cssSelector("form#bigsearch input"));
                String searchText = "user:100";
                searchBar.sendKeys(searchText);
                sleep(1.0);

                searchBar.sendKeys(Keys.RETURN);
                TestSteps.doCaptcha(driver);
            }

            driver.findElements(By.cssSelector("div.search-results div.result-link a")).get(0).click();
            sleep(2.0);
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the functionality of the page selector.
     */
    @Test(priority = 19, description = "Functional page selector")
    public static void usePageSelectorTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            TestSteps.doCaptcha(driver);
            {
                WebElement nextButton = driver.findElement(By.cssSelector("div.pager span.next"));
                new Actions(driver).scrollToElement(nextButton).scrollByAmount(0, 200).perform();
                sleep(1.0);

                nextButton.click();
                sleep(1.0);
            }
            {
                WebElement nextButton = driver.findElement(By.cssSelector("div.pager span.next"));
                new Actions(driver).scrollToElement(nextButton).scrollByAmount(0, 200).perform();
                sleep(1.0);
            }
            String currentUrl = Optional.ofNullable(driver.getCurrentUrl()).orElse("");
            Assert.assertTrue(currentUrl.contains("page=2"), "expected url to have 'page=2'");
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the functionality of the 'lines per page' selector.
     */
    @Test(priority = 20, description = "Functional 'lines per page' selector")
    public static void usePaginatorTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            TestSteps.doCaptcha(driver);
            {
                WebElement fifteenButton = driver.findElement(By.partialLinkText("15"));
                new Actions(driver).scrollToElement(fifteenButton).scrollByAmount(0, 200).perform();
                sleep(1.0);

                fifteenButton.click();
                sleep(1.0);
            }
            {
                WebElement fifteenButton = driver.findElement(By.partialLinkText("15"));
                new Actions(driver).scrollToElement(fifteenButton).scrollByAmount(0, 200).perform();
                sleep(1.0);
            }
            String currentUrl = Optional.ofNullable(driver.getCurrentUrl()).orElse("");
            Assert.assertTrue(currentUrl.contains("pagesize=15"), "expected url to have 'pagesize=15'");
        }
        finally {
            driver.quit();
        }
    }
}
