package setesting.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setesting.TestBrowser;
import setesting.TestListener;

import java.util.Optional;

import static setesting.TestUtils.sleep;

/**
 * Test Stack Exchange's anonymous user functionality.
 */
@Listeners(TestListener.class)
public final class AnonUserTestCase {
    private AnonUserTestCase() {}

    /**
     * Validate the presence of important elements on the homepage.
     */
    @Test(priority = 1, description = "Important elements are on the homepage")
    public static void homepageSmokeTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            sleep(2.0);

            asserter.assertFalse(driver.findElements(By.cssSelector("a.s-topbar--logo[href=\"https://stackexchange.com\"]")).isEmpty(), "home logo should be present");
            asserter.assertFalse(driver.findElements(By.linkText("Log in")).isEmpty(), "login button should be present");
            asserter.assertFalse(driver.findElements(By.id("search")).isEmpty(), "search bar should be present");

            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the tour page, viewing all sites, and navigating to area 51.
     */
    @Test(priority = 2, description = "Visit the tour page, view all sites, and navigate to area 51")
    public static void siteTourTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            sleep(2.0);

            driver.findElement(By.linkText("Tour")).click();
            sleep(2.0);
            {
                WebElement element = driver.findElement(By.id("ask-your-own"));
                new Actions(driver).scrollToElement(element).scrollByAmount(0, 200).perform();
                sleep(2.0);

                element.click();
                sleep(2.0);
            }
            {
                WebElement element = driver.findElement(By.partialLinkText("Area 51"));
                new Actions(driver).scrollToElement(element).scrollByAmount(0, 200).perform();
                sleep(2.0);

                element.click();
                sleep(2.0);
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "https://area51.stackexchange.com/";
            LoggerFactory.getLogger("siteTourTest").info("url : {}", currentUrl);
            asserter.assertEquals(currentUrl, expectedUrl, "expected url was \"" + expectedUrl + "\"");

            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the 'contact us' form displays a popover that can be closed.
     */
    @Test(priority = 3, description = "The 'contact us' form displays a popover that can be closed")
    public static void contactFormTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            sleep(2.0);

            driver.findElement(By.linkText("About Us")).click();
            sleep(2.0);

            driver.findElement(By.cssSelector(".tabs a[href*=\"security\"]")).click();
            sleep(2.0);

            driver.findElement(By.linkText("Contact Form")).click();
            sleep(2.0);

            driver.findElement(By.id("group-1")).click();
            sleep(2.0);

            driver.findElement(By.id("topic-6")).click();
            sleep(4.0);

            driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
            sleep(2.0);
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate searching for a moderator's profile in the 'about us' page.
     */
    @Test(priority = 4, description = "Search for a moderator's profile in the 'about us' page")
    public static void moderatorSearchTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            sleep(2.0);

            driver.findElement(By.linkText("About Us")).click();
            sleep(2.0);

            driver.findElement(By.linkText("moderators")).click();
            sleep(1.0);
            {
                WebElement mod = driver.findElement(By.cssSelector("div.mods-container > div:nth-child(13) .mods-summary-list a:nth-child(1)"));
                new Actions(driver).scrollToElement(mod).scrollByAmount(0, 200).perform();
                mod.click();
                sleep(2.0);
            }
            driver.findElement(By.linkText("Activity")).click();
            sleep(2.0);
        }
        finally {
            driver.quit();
        }

    }

    /**
     * Validate selecting a community from the banner dropdown.
     */
    @Test(priority = 5, description = "Select a community from the banner dropdown")
    public static void communityBrowseTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            sleep(2.0);

            driver.findElement(By.cssSelector("a.js-site-switcher-button")).click();
            sleep(2.0);

            driver.findElement(By.cssSelector("div.siteSwitcher-dialog input[type=\"text\"]")).sendKeys("math");
            sleep(2.0);

            driver.findElement(By.cssSelector("div.siteSwitcher-dialog li.p0:nth-child(3) a")).click();
            sleep(2.0);

            driver.findElement(By.cssSelector("div.s-post-summary:nth-child(1) a.s-link")).click();
            sleep(2.0);

            String currentUrl = Optional.ofNullable(driver.getCurrentUrl()).orElse("");
            String expectedUrlPrefix = "https://math.stackexchange.com/questions/";
            LoggerFactory.getLogger("communityBrowseTest").info("url : {}", currentUrl);
            asserter.assertTrue(currentUrl.startsWith(expectedUrlPrefix), "expected to be visiting a question on the math stackexchange");

            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }
}
