package setesting.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setesting.TestBrowser;
import setesting.TestListener;
import setesting.TestSteps;

import static setesting.TestUtils.sleep;

/**
 * Test Stack Exchange's question interaction functionalities.
 */
@Listeners(TestListener.class)
public final class InteractionTestCase {
    private InteractionTestCase() {}

    private static int getVoteCountOfPage(WebDriver driver) {
        try {
            return Integer.parseInt(driver.findElement(By.cssSelector(".js-vote-count")).getText().strip());
        }
        catch (NumberFormatException | NoSuchElementException ex) {
            return -1;
        }
    }

    /**
     * Validate the ability to upvote and down-vote.
     */
    @Test(priority = 21, description = "Upvote and down-vote posts")
    public static void ratePostTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://meta.stackexchange.com/questions/57682/how-should-sockpuppets-be-handled-on-stack-exchange/57685");
            sleep(2.0);

            int initialVoteCount = getVoteCountOfPage(driver);

            driver.findElement(By.cssSelector(".js-vote-up-btn")).click();
            sleep(3.0);
            asserter.assertEquals(getVoteCountOfPage(driver), initialVoteCount + 1, "expected up-voted count to be one greater");

            driver.findElement(By.cssSelector(".js-vote-down-btn")).click();
            sleep(3.0);
            asserter.assertEquals(getVoteCountOfPage(driver), initialVoteCount - 1, "expected down-voted count to be one less");

            driver.findElement(By.cssSelector(".js-vote-down-btn")).click();
            sleep(1.0);

            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to copy the question's share link and then view the revision history.
     */
    @Test(priority = 22, description = "Copy a question's share link and then view the revision history")
    public static void useSocialLinksTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackoverflow.com/questions/12293158/page-scroll-up-or-down-in-selenium-webdriver-selenium-2-using-java");
            sleep(2.0);

            driver.findElement(By.linkText("Share")).click();
            sleep(2.0);

            String shareLink = driver.findElement(By.id("share-sheet-input-se-share-sheet-0")).getText();
            LoggerFactory.getLogger("useSocialLinksTest").info("Shared link: \"{}\"", shareLink);

            driver.findElement(By.cssSelector("div.post-signature a")).click();
            sleep(4.0);
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to revise or tag a question.
     */
    @Test(priority = 23, description = "Revise or tag a question")
    public static void revisePostTest() {
        Assert.fail("cannot safely automate this test");
    }

    /**
     * Validate the ability to save a question and manage saved posts.
     */
    @Test(priority = 24, description = "Save a question and manage saved posts")
    public static void bookmarkPostTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://meta.stackexchange.com/questions/57682/how-should-sockpuppets-be-handled-on-stack-exchange/57685");
            sleep(2.0);

            driver.findElement(By.cssSelector(".js-saves-btn")).click();
            sleep(2.0);

            driver.findElement(By.id("user-profile-button")).click();
            sleep(2.0);

            driver.findElement(By.partialLinkText("Saves")).click();
            sleep(2.0);

            driver.findElement(By.cssSelector("div.s-post-summary button.s-btn")).click();
            sleep(2.0);

            driver.findElement(By.cssSelector("button.js-unsave")).click();
            sleep(2.0);
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to load more related questions and choose one.
     */
    @Test(priority = 25, description = "Load more related questions and choose one")
    public static void navRelatedPostsTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackoverflow.com/questions/45511841/xpath-get-text-after-first-html-tag");
            sleep(2.0);

            new Actions(driver).scrollToElement(driver.findElement(By.id("h-linked"))).scrollByAmount(0, 200).perform();
            sleep(1.0);

            driver.findElements(By.cssSelector("div.sidebar-linked a.question-hyperlink")).get(0).click();
            sleep(2.0);

            new Actions(driver).scrollToElement(driver.findElement(By.id("h-related"))).scrollByAmount(0, 200).perform();
            sleep(1.0);

            driver.findElements(By.cssSelector("div.sidebar-linked a.question-hyperlink")).get(1).click();
            sleep(2.0);
        }
        finally {
            driver.quit();
        }
    }
}
