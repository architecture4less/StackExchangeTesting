package setesting.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestBrowser;
import setesting.TestListener;
import setesting.TestSteps;

import java.nio.file.Path;

import static setesting.TestUtils.RUN_DIR;
import static setesting.TestUtils.saveScreenshot;
import static setesting.TestUtils.sleep;

/**
 * Test Stack Exchange's profile management functionalities.
 */
@Listeners(TestListener.class)
public final class ProfileTestCase {
    private ProfileTestCase() {}

    /**
     * Validate the ability to view the activity of another user.
     */
    @Test(priority = 11, description = "View the activity of another user")
    public static void viewProfileTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackexchange.com/");
            sleep(1.0);

            driver.findElement(By.linkText("Meta")).click();
            sleep(1.0);

            driver.findElement(By.id("user-profile-button")).click();
            sleep(1.0);

            {
                WebElement element = driver.findElement(By.id("user-panel-accounts"));
                new Actions(driver).scrollToElement(element).scrollByAmount(0, 200).perform();
                sleep(1.0);
            }
            driver.findElement(By.partialLinkText("View all 9 accounts")).click();
            sleep(1.0);
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to open the account's latest notification.
     */
    @Test(priority = 12, description = "Open the account's latest notification")
    public static void viewNotifsTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackexchange.com/");
            sleep(1.0);

            driver.findElement(By.cssSelector("a.js-inbox-button")).click();
            sleep(1.0);

            driver.findElement(By.cssSelector("li.inbox-item")).click();
            sleep(2.0);

            driver.findElement(By.cssSelector("a.js-inbox-button")).click();
            sleep(1.0);

            driver.findElement(By.cssSelector("button.js-toggle-unread")).click();
            sleep(2.0);
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to update the account's profile picture and bio.
     */
    @Test(priority = 13, description = "Update the account's profile picture and bio")
    public static void updateProfileTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.findElement(By.linkText("Meta")).click();
            sleep(1.0);

            driver.findElement(By.id("user-profile-button")).click();
            sleep(1.0);

            driver.findElement(By.partialLinkText("Edit profile")).click();
            sleep(1.0);

            {
                WebElement nameInput = driver.findElement(By.id("displayName"));
                nameInput.clear();
                sleep(1.0);

                // the display name may only be changed once per month
                // String nameText = "Jared O'Toole";
                // nameInput.sendKeys(nameText);
                // TestUtils.sleep(1.0);
            }

            driver.findElement(By.id("change-picture")).click();
            sleep(1.0);

            driver.findElement(By.id("avatar-upload")).click();
            sleep(1.0);

            driver.findElement(By.partialLinkText("link from the web")).click();
            sleep(1.0);

            String avatarUrl = "https://cdn.pixilart.com/photos/large/c1aa290d0f8f382.jpg";
            driver.findElement(By.cssSelector("input.js-modal-input-url")).sendKeys(avatarUrl);
            sleep(1.0);

            driver.findElement(By.cssSelector("button.js-modal-cta-submit")).click();
            sleep(2.0);
            {
                WebElement bioInput = driver.findElement(By.id("wmd-input"));
                new Actions(driver).scrollToElement(bioInput).scrollByAmount(0, 200).perform();
                sleep(1.0);

                bioInput.clear();
                sleep(1.0);

                String bioText = "Student at FGCU!\n";
                bioInput.sendKeys(bioText);
                sleep(2.0);
            }
            {
                WebElement saveButton = driver.findElement(By.cssSelector("button.js-save-button"));
                new Actions(driver).scrollToElement(saveButton).scrollByAmount(0, 200).perform();
                sleep(1.0);

                saveButton.click();
                sleep(1.0);
            }

            saveScreenshot(driver, RUN_DIR.resolve("run-test/updateProfileTest"));
            sleep(4.0);

            driver.findElement(By.partialLinkText("Edit profile")).click();
            sleep(1.0);

            driver.findElement(By.id("change-picture")).click();
            sleep(1.0);

            driver.findElement(By.partialLinkText("Identicon")).click();
            sleep(1.0);
            {
                WebElement nameInput = driver.findElement(By.id("displayName"));
                nameInput.clear();
                sleep(1.0);

                // the display name may only be changed once per month
                // String nameText = "user1730939";
                // nameInput.sendKeys(nameText);
                // TestUtils.sleep(1.0);
            }
            {
                WebElement bioInput = driver.findElement(By.id("wmd-input"));
                new Actions(driver).scrollToElement(bioInput).scrollByAmount(0, 200).perform();
                sleep(1.0);

                bioInput.clear();
                sleep(1.0);
            }
            {
                WebElement saveButton = driver.findElement(By.cssSelector("button.js-save-button"));
                new Actions(driver).scrollToElement(saveButton).scrollByAmount(0, 200).perform();
                sleep(1.0);

                saveButton.click();
                sleep(1.0);
            }
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to update the account's hidden communities.
     */
    @Test(priority = 14, description = "Update the account's hidden communities")
    public static void updateHiddenSitesTest() {
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackoverflow.com/");
            sleep(2.0);

            driver.findElement(By.id("user-profile-button")).click();
            sleep(2.0);

            driver.findElement(By.linkText("Settings")).click();
            sleep(1.0);

            driver.findElement(By.partialLinkText("Hide communities")).click();
            sleep(1.0);

            driver.findElements(By.cssSelector("button[name=\"hide-site\"]")).get(2).click();
            sleep(1.0);
            {
                WebElement hidden = driver.findElement(By.id("hidden-communities"));
                new Actions(driver).scrollToElement(hidden).scrollByAmount(0, 200).perform();
                sleep(1.0);
            }
            driver.findElements(By.cssSelector("button[name=\"unhide-site\"]")).forEach(WebElement::click);
            sleep(1.0);
            {
                WebElement visible = driver.findElement(By.id("visible-communities"));
                new Actions(driver).scrollToElement(visible).scrollByAmount(0, 200).perform();
                sleep(1.0);
            }
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate the ability to update the account's site preferences.
     */
    @Test(priority = 15, description = "Update the account's site preferences")
    public static void updatePrefsTest() {
        Path screenshotFolder = Path.of("run-test/updatePrefsTest");
        WebDriver driver = TestBrowser.EDGE.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackoverflow.com/");
            sleep(2.0);

            driver.findElement(By.id("user-profile-button")).click();
            sleep(2.0);

            driver.findElement(By.linkText("Settings")).click();
            sleep(1.0);

            driver.findElement(By.partialLinkText("Preferences")).click();
            sleep(1.0);

            driver.findElement(By.id("enableForcedLight")).click();
            sleep(2.0);

            saveScreenshot(driver, screenshotFolder);

            driver.findElement(By.id("enableForcedDark")).click();
            sleep(2.0);

            saveScreenshot(driver, screenshotFolder);

            driver.findElement(By.id("enableForcedLight")).click();
            sleep(1.0);
        }
        finally {
            driver.quit();
        }
    }
}
