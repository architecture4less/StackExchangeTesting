package setesting.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setesting.TestBrowser;
import setesting.TestListener;
import setesting.TestSteps;

import static setesting.TestUtils.sleep;

/**
 * Test Stack Exchange's responsive design functionalities.
 */
@Listeners(TestListener.class)
public final class ResponsivenessTestCase {
    private ResponsivenessTestCase() {}

    /**
     * Validate the homepage renders in the time listed on the performance page.
     */
    @Test(priority = 31, description = "Homepage renders within the advertised duration")
    public static void homepageRenderTimeTest() {
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            long startTime = System.nanoTime();

            // this method is inadequate for performance testing
            driver.get("https://stackexchange.com/");
            long endTime = System.nanoTime();
            double actualMs = (endTime - startTime) / 1e6;
            sleep(2.0);

            driver.get("https://stackexchange.com/performance");
            sleep(2.0);

            new Actions(driver).scrollToElement(driver.findElement(By.id("homeStat"))).scrollByAmount(0, 200).perform();
            sleep(2.0);

            double expectedMs = Double.parseDouble(driver.findElement(By.cssSelector("#homeStat .main")).getText());
            Assert.assertTrue(actualMs <= expectedMs, "expected homepage to load in at most "
                + expectedMs + "ms (actual: " + actualMs + "ms)");
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the feed is visible in smaller browser window.
     */
    @Test(priority = 32, description = "Feed is visible in a smaller browser window")
    public static void smallFeedLayoutTest() {
        SoftAssert asserter = new SoftAssert();
        Dimension browserSize = new Dimension(960, 540);
        for (TestBrowser browser : TestBrowser.values()) {
            WebDriver driver = browser.open();
            try {
                driver.manage().window().setSize(browserSize);
                driver.get("https://stackexchange.com/");
                sleep(2.0);

                WebElement mainArea = driver.findElement(By.id("mainArea"));

                // make sure the feed width is not wider than the browser
                int elemWidth = mainArea.getSize().getWidth();
                asserter.assertTrue(elemWidth <= browserSize.getWidth(), "expected feed width ("
                    + elemWidth + ") to be at most browser width (" + browserSize.getWidth() + ")");
            }
            finally {
                driver.quit();
            }
        }
        asserter.assertAll();
    }

    /**
     * Validate login button and window is usable in a smaller browser window.
     */
    @Test(priority = 33, description = "Login modal is usable in a smaller browser window")
    public static void smallLoginModalTest() {
        SoftAssert asserter = new SoftAssert();
        Dimension browserSize = new Dimension(960, 810);
        for (TestBrowser browser : TestBrowser.values()) {
            WebDriver driver = browser.open();
            try {
                driver.manage().window().setSize(browserSize);
                driver.get("https://stackexchange.com/");
                sleep(2.0);

                driver.findElement(By.linkText("Log in")).click();
                sleep(2.0);
                {
                    WebElement loginForm = driver.findElement(By.id("formContainer"));
                    loginForm.click();
                    sleep(2.0);

                    int elemWidth = loginForm.getSize().getWidth();
                    asserter.assertTrue(elemWidth <= browserSize.getWidth(), "expected login form width ("
                        + elemWidth + ") to be at most browser width (" + browserSize.getWidth() + ")");
                }
            }
            finally {
                driver.quit();
            }
        }
        asserter.assertAll();
    }

    /**
     * Validate sites dropdown is usable in a smaller browser window.
     */
    @Test(priority = 34, description = "Sites dropdown is usable in a smaller browser window")
    public static void smallDropdownTest() {
        SoftAssert asserter = new SoftAssert();
        Dimension browserSize = new Dimension(480, 810);
        for (TestBrowser browser : TestBrowser.values()) {
            WebDriver driver = browser.open();
            try {
                driver.manage().window().setSize(browserSize);
                driver.get("https://stackexchange.com/");
                sleep(2.0);

                driver.findElement(By.cssSelector("a.js-site-switcher-button")).click();
                sleep(2.0);

                Dimension elemSize = driver.findElement(By.cssSelector("div.siteSwitcher-dialog")).getSize();
                asserter.assertTrue(elemSize.getWidth() <= browserSize.getWidth(), "expected site switcher modal width ("
                    + elemSize.getWidth() + ") to be at most browser width (" + browserSize.getWidth() + ")");
                asserter.assertTrue(elemSize.getHeight() <= browserSize.getHeight(), "expected site switcher modal height ("
                    + elemSize.getHeight() + ") to be at most browser height (" + browserSize.getHeight() + ")");
            }
            finally {
                driver.quit();
            }
        }
        asserter.assertAll();
    }

    /**
     * Validate text editor is usable in a smaller browser window.
     */
    @Test(priority = 35, description = "Use Text editor in a smaller browser window")
    public static void smallTextEditorTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().setSize(new Dimension(960, 540));
            TestSteps.doLogin(driver);

            driver.get("https://stackoverflow.com/");
            sleep(2.0);

            driver.findElement(By.partialLinkText("Ask Question")).click();
            sleep(2.0);

            driver.manage().window().setSize(new Dimension(480, 270));
            driver.findElement(By.id("title")).sendKeys("Test");
        }
        finally {
            driver.quit();
        }
        asserter.assertAll();
    }
}
