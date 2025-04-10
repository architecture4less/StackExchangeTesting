package setesting.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestBrowser;
import setesting.TestListener;
import setesting.TestSteps;

import static setesting.TestUtils.RUN_DIR;
import static setesting.TestUtils.saveScreenshot;
import static setesting.TestUtils.sleep;

/**
 * Test Stack Exchange's question posting functionalities.
 */
@Listeners(TestListener.class)
public final class NewPostTestCase {
    private NewPostTestCase() {}

    /**
     * Validate text inputting in rich text mode.
     */
    @Test(priority = 26, description = "Input text in rich text mode")
    public static void inputRichTextTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackoverflow.com");
            driver.findElement(By.partialLinkText("Ask Question")).click();
            sleep(2.0);

            driver.findElement(By.id("title")).sendKeys("Test");
            sleep(1.0);

            driver.findElement(By.xpath("//button[text()='Next']")).click();
            sleep(1.0);

            driver.findElement(By.xpath("//label[@title='Rich text mode']")).click();
            sleep(1.0);
            {
                WebElement field = driver.findElement(By.id("post-problem-details"));
                new Actions(driver).moveToElement(field).moveByOffset(50, 50).click()
                    .sendKeys("Rich plain text").sendKeys(Keys.ENTER).perform();
                sleep(1.0);

                driver.findElement(By.cssSelector("button.js-bold-btn")).click();
                sleep(1.0);

                new Actions(driver).moveToElement(field).moveByOffset(50, 50).click()
                    .sendKeys("Rich BOLD text").sendKeys(Keys.ENTER).perform();
                sleep(2.0);
            }
            saveScreenshot(driver, RUN_DIR.resolve("run-test/inputRichTextTest"));

            // try to use the discard button in case a draft was auto-saved
            try {
                WebElement discard = driver.findElement(By.xpath("//button[text()='Discard draft']"));
                new Actions(driver).scrollToElement(discard).scrollByAmount(0, 200).perform();
                discard.click();
                sleep(2.0);

                driver.findElement(By.id("discard-confirmation-btn")).click();
                sleep(2.0);
            }
            catch (ElementNotInteractableException ex) {
                // pass
            }
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate text inputting in markdown mode.
     */
    @Test(priority = 27, description = "Input text in markdown mode")
    public static void inputMarkdownTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackoverflow.com");
            driver.findElement(By.partialLinkText("Ask Question")).click();
            sleep(2.0);

            driver.findElement(By.id("title")).sendKeys("Test");
            sleep(1.0);

            driver.findElement(By.xpath("//button[text()='Next']")).click();
            sleep(1.0);

            driver.findElement(By.xpath("//label[@title='Markdown with preview mode']")).click();
            sleep(1.0);
            {
                WebElement field = driver.findElement(By.id("post-problem-details"));
                new Actions(driver).moveToElement(field).moveByOffset(50, 50).click()
                    .sendKeys("Markdown plain text  ").sendKeys(Keys.ENTER).perform();
                sleep(1.0);

                new Actions(driver).moveToElement(field).moveByOffset(50, 50).click()
                    .sendKeys("**Markdown BOLD text**  ").sendKeys(Keys.ENTER).perform();
                sleep(2.0);
            }
            saveScreenshot(driver, RUN_DIR.resolve("run-test/inputMarkdownTest"));

            // try to use the discard button in case a draft was auto-saved
            try {
                WebElement discard = driver.findElement(By.xpath("//button[text()='Discard draft']"));
                new Actions(driver).scrollToElement(discard).scrollByAmount(0, 200).perform();
                discard.click();
                sleep(2.0);

                driver.findElement(By.id("discard-confirmation-btn")).click();
                sleep(2.0);
            }
            catch (ElementNotInteractableException ex) {
                // pass
            }
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate ability to ask a question.
     */
    @Test(priority = 28, description = "Ask a question")
    public static void postQuestionTest() {
        Assert.fail("cannot safely automate this test");
    }

    /**
     * Validate ability to post an answer.
     */
    @Test(priority = 29, description = "Post an answer")
    public static void postAnswerTest() {
        Assert.fail("cannot safely automate this test");
    }

    /**
     * Validate ability to comment on a question.
     */
    @Test(priority = 30, description = "Comment on a question")
    public static void postCommentTest() {
        Assert.fail("cannot safely automate this test");
    }
}
