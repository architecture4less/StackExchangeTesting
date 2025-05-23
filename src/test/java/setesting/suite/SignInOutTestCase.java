package setesting.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setesting.TestBrowser;
import setesting.TestListener;
import setesting.TestSteps;

import static setesting.TestUtils.sleep;

/**
 * Test Stack Exchange's user login/logout functionality.
 */
@Listeners(TestListener.class)
public final class SignInOutTestCase {
    private SignInOutTestCase() {}

    @AfterMethod
    private static void waitAfterEach() {
        TestSteps.limitTestSpeed();
    }

    @AfterClass
    private static void waitAfterAll() {
        TestSteps.limitTestCaseSpeed();
    }

    /**
     * Validate the functionality of the 'sign up' page.
     */
    @Test(priority = 6, description = "Functional 'sign up' page")
    public static void signupFormTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            TestSteps.doCaptcha(driver);

            driver.findElement(By.linkText("Meta")).click();
            TestSteps.doCaptcha(driver);

            driver.findElement(By.cssSelector("a.js-signup-button")).click();
            TestSteps.doCaptcha(driver);

            driver.findElement(By.id("signup-modal-email")).sendKeys("email");
            driver.findElement(By.id("signup-modal-password")).sendKeys("1234");
            driver.findElement(By.id("signup-modal-submit-button")).click();
            sleep(2.0);

            asserter.assertFalse(driver.findElements(By.xpath("//*[text()='email is not a valid email address.']")).isEmpty(), "expected bad email message");
            asserter.assertFalse(driver.findElements(By.xpath("//*[text()='Password must be at least 8 characters long.']")).isEmpty(), "expected bad password message");

            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate an error message for invalid login credentials.
     */
    @Test(priority = 7, description = "Error message shown with invalid login credentials")
    public static void badLoginTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://stackexchange.com/");
            TestSteps.doCaptcha(driver);

            driver.findElement(By.linkText("Log in")).click();
            TestSteps.doCaptcha(driver);

            driver.findElement(By.id("email")).sendKeys("invalid@example.com");
            sleep(1.0);

            driver.findElement(By.id("password")).sendKeys("12345678");
            sleep(1.0);

            driver.findElement(By.id("submit-button")).click();
            sleep(1.0); // await login attempt
            TestSteps.doCaptcha(driver);

            asserter.assertFalse(driver.findElements(By.xpath("//*[text()='\n            An error occurred with log in.']")).isEmpty(), "expected login error message");

            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate account access with valid login credentials.
     */
    @Test(priority = 8, description = "Account is accessed with valid login credentials")
    public static void goodLoginTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            // driver.manage().window().maximize();
            // driver.get("https://stackexchange.com/");
            // TestSteps.doCaptcha(driver);

            TestSteps.doLogin(driver);

            asserter.assertFalse(driver.findElements(By.id("user-profile-button")).isEmpty(), "expected a user profile button");

            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate homepage redirection upon logout.
     */
    @Test(priority = 9, description = "Homepage redirects upon logout")
    public static void logoutTest() {
        SoftAssert asserter = new SoftAssert();
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            TestSteps.doLogin(driver);

            driver.get("https://stackexchange.com/");
            TestSteps.doCaptcha(driver);

            // this fails because the site does not currently provide a log out method
            driver.findElement(By.linkText("Log out")).click();
            sleep(6.0); // await redirect

            asserter.assertTrue(driver.findElements(By.id("user-profile-button")).isEmpty(), "expected no user profile button");

            asserter.assertAll();
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Validate restriction to the account management page upon logout.
     */
    @Test(priority = 10, description = "Account management page restricted upon logout")
    public static void logoutNoAccessTest() {
        WebDriver driver = TestBrowser.CHROME.open();
        try {
            driver.manage().window().maximize();
            driver.get("https://meta.stackexchange.com/users/mylogins/1730939");
            TestSteps.doCaptcha(driver);

            Assert.assertFalse(driver.findElements(By.xpath("//*[text()='Page not found']")).isEmpty(), "expected a 404 message");
        }
        finally {
            driver.quit();
        }
    }
}
