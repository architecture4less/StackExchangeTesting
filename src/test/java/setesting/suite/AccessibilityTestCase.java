package setesting.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setesting.TestBrowser;
import setesting.TestListener;

import static setesting.TestUtils.RUN_DIR;
import static setesting.TestUtils.saveScreenshot;
import static setesting.TestUtils.sleep;

/**
 * Test Stack Exchange's site accessibility functionalities.
 */
@Listeners(TestListener.class)
public final class AccessibilityTestCase {
    private AccessibilityTestCase() {}

    /**
     * Validate that site content is screen reader friendly.
     */
    @Test(priority = 36, description = "Site content is screen reader friendly.")
    public static void ariaLabelsTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            sleep(2.0);
            for (WebElement element : driver.findElements(By.cssSelector("button, input, .s-navigation, nav"))) {
                String title = element.getTagName() + " at " + element.getLocation();
                String ariaLabel = element.getDomAttribute("aria-label");
                LoggerFactory.getLogger("ariaLabelsTest").info("{} : {}", title, ariaLabel);
                asserter.assertNotNull(ariaLabel, title + " should have attribute 'aria-label'");
            }
            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate that images have an alt text.
     */
    @Test(priority = 37, description = "Images have an alt text.")
    public static void imageAltTextTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            sleep(2.0);
            for (WebElement element : driver.findElements(By.cssSelector("img"))) {
                String title = element.getTagName() + " at " + element.getLocation();
                String altText = element.getDomAttribute("alt");
                LoggerFactory.getLogger("imageAltTextTest").info("{} : {}", title, altText);
                asserter.assertNotNull(altText, title + " should have attribute 'alt'");
            }
            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate support for keyboard navigation on forms.
     */
    @Test(priority = 38, description = "Support for keyboard navigation on forms.")
    public static void formKeyNavTest() {
        Assert.fail("key navigation not supported");
    }

    /**
     * Validate inclusion of meaningful text in button and link elements.
     */
    @Test(priority = 39, description = "Inclusion of meaningful text in button and link elements.")
    public static void semanticHtmlTest() {
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            Assert.fail("html tags missing aria labels");
            // driver.manage().window().maximize();
            // driver.get("https://stackexchange.com/");
            // sleep(2.0);
            // for (WebElement element : driver.findElements(By.cssSelector("img"))) {
            //     String title = element.getTagName() + " at " + element.getLocation();
            //     String altText = element.getDomAttribute("alt");
            //     LoggerFactory.getLogger("imageAltTextTest").info("{} : {}", title, altText);
            // }
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Compare a screenshot of the homepage between multiple browsers.
     */
    @Test(priority = 40, description = "Screenshot the homepage in multiple browsers.")
    public static void browserConsistencyTest() {
        for (TestBrowser browser : TestBrowser.values()) {
            WebDriver driver = browser.open();
            try {
                driver.get("https://stackexchange.com/");
                driver.manage().window().maximize();
                sleep(1.0);
                LoggerFactory.getLogger("browserConsistencyTest").info(browser.name());
                saveScreenshot(driver, RUN_DIR.resolve("run-test/browserConsistencyTest/" + browser.name().toLowerCase()));
            }
            finally {
                driver.quit();
            }
        }
    }
}
