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
import setesting.TestUtils;

import java.nio.file.Path;

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
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackexchange.com/");
            TestUtils.sleep(1.0);

            driver.findElement(By.linkText("Meta")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.id("user-profile-button")).click();
            TestUtils.sleep(1.0);

            {
                WebElement element = driver.findElement(By.id("user-panel-accounts"));
                new Actions(driver).scrollToElement(element).scrollByAmount(0, 200).perform();
                TestUtils.sleep(1.0);
            }
            driver.findElement(By.partialLinkText("View all 9 accounts")).click();
            TestUtils.sleep(1.0);
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
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackexchange.com/");
            TestUtils.sleep(1.0);

            driver.findElement(By.cssSelector("a.js-inbox-button")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.cssSelector("li.inbox-item")).click();
            TestUtils.sleep(2.0);

            driver.findElement(By.cssSelector("a.js-inbox-button")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.cssSelector("button.js-toggle-unread")).click();
            TestUtils.sleep(2.0);
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
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.findElement(By.linkText("Meta")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.id("user-profile-button")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.partialLinkText("Edit profile")).click();
            TestUtils.sleep(1.0);

            {
                WebElement nameInput = driver.findElement(By.id("displayName"));
                nameInput.clear();
                TestUtils.sleep(1.0);

                // the display name may only be changed once per month
                // String nameText = "Jared O'Toole";
                // nameInput.sendKeys(nameText);
                // TestUtils.sleep(1.0);
            }

            driver.findElement(By.id("change-picture")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.id("avatar-upload")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.partialLinkText("link from the web")).click();
            TestUtils.sleep(1.0);

            String avatarUrl = "https://cdn.pixilart.com/photos/large/c1aa290d0f8f382.jpg";
            driver.findElement(By.cssSelector("input.js-modal-input-url")).sendKeys(avatarUrl);
            TestUtils.sleep(1.0);

            driver.findElement(By.cssSelector("button.js-modal-cta-submit")).click();
            TestUtils.sleep(2.0);
            {
                WebElement bioInput = driver.findElement(By.id("wmd-input"));
                new Actions(driver).scrollToElement(bioInput).scrollByAmount(0, 200).perform();
                TestUtils.sleep(1.0);

                bioInput.clear();
                TestUtils.sleep(1.0);

                String bioText = "Student at FGCU!\n";
                bioInput.sendKeys(bioText);
                TestUtils.sleep(2.0);
            }
            {
                WebElement saveButton = driver.findElement(By.cssSelector("button.js-save-button"));
                new Actions(driver).scrollToElement(saveButton).scrollByAmount(0, 200).perform();
                TestUtils.sleep(1.0);

                saveButton.click();
                TestUtils.sleep(1.0);
            }

            TestUtils.saveScreenshot(driver, TestUtils.RUN_DIR.resolve("run-test/updateProfileTest"));
            TestUtils.sleep(4.0);

            driver.findElement(By.partialLinkText("Edit profile")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.id("change-picture")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.partialLinkText("Identicon")).click();
            TestUtils.sleep(1.0);
            {
                WebElement nameInput = driver.findElement(By.id("displayName"));
                nameInput.clear();
                TestUtils.sleep(1.0);

                // the display name may only be changed once per month
                // String nameText = "user1730939";
                // nameInput.sendKeys(nameText);
                // TestUtils.sleep(1.0);
            }
            {
                WebElement bioInput = driver.findElement(By.id("wmd-input"));
                new Actions(driver).scrollToElement(bioInput).scrollByAmount(0, 200).perform();
                TestUtils.sleep(1.0);

                bioInput.clear();
                TestUtils.sleep(1.0);
            }
            {
                WebElement saveButton = driver.findElement(By.cssSelector("button.js-save-button"));
                new Actions(driver).scrollToElement(saveButton).scrollByAmount(0, 200).perform();
                TestUtils.sleep(1.0);

                saveButton.click();
                TestUtils.sleep(1.0);
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
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackoverflow.com/");
            TestUtils.sleep(2.0);

            driver.findElement(By.id("user-profile-button")).click();
            TestUtils.sleep(2.0);

            driver.findElement(By.linkText("Settings")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.partialLinkText("Hide communities")).click();
            TestUtils.sleep(1.0);

            driver.findElements(By.cssSelector("button[name=\"hide-site\"]")).get(2).click();
            TestUtils.sleep(1.0);
            {
                WebElement hidden = driver.findElement(By.id("hidden-communities"));
                new Actions(driver).scrollToElement(hidden).scrollByAmount(0, 200).perform();
                TestUtils.sleep(1.0);
            }
            driver.findElements(By.cssSelector("button[name=\"unhide-site\"]")).forEach(WebElement::click);
            TestUtils.sleep(1.0);
            {
                WebElement visible = driver.findElement(By.id("visible-communities"));
                new Actions(driver).scrollToElement(visible).scrollByAmount(0, 200).perform();
                TestUtils.sleep(1.0);
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
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackoverflow.com/");
            TestUtils.sleep(2.0);

            driver.findElement(By.id("user-profile-button")).click();
            TestUtils.sleep(2.0);

            driver.findElement(By.linkText("Settings")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.partialLinkText("Preferences")).click();
            TestUtils.sleep(1.0);

            driver.findElement(By.id("enableForcedLight")).click();
            TestUtils.sleep(2.0);

            TestUtils.saveScreenshot(driver, screenshotFolder);

            driver.findElement(By.id("enableForcedDark")).click();
            TestUtils.sleep(2.0);

            TestUtils.saveScreenshot(driver, screenshotFolder);

            driver.findElement(By.id("enableForcedLight")).click();
            TestUtils.sleep(1.0);
        }
        finally {
            driver.quit();
        }
    }
}
