package setesting;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static setesting.TestUtils.sleep;

/**
 * Common steps that need to be performed for multiple tests.
 */
public final class TestSteps {
    private TestSteps() {}

    public static void limitTestSpeed() {
        sleep(2.0);
    }

    public static void limitTestCaseSpeed() {
        sleep(2.0);
    }

    /**
     * Account for the site's use of CAPTCHAs.
     */
    public static void doCaptcha(WebDriver driver) {
        sleep(2.0);
        while (true) {
            try {
                driver.findElement(By.id("nocaptcha-form"));
                sleep(2.0);
            }
            catch (NoSuchElementException ex) {
                break;
            }
        }
        while (true) {
            try {
                driver.findElement(By.xpath("//div[text()=\"stackexchange.com needs to review the security of your connection before proceeding.\"]"));
                sleep(2.0);
            }
            catch (NoSuchElementException ex) {
                break;
            }
        }
        sleep(2.0);
    }

    /**
     * Log into stackexchange using the stored credentials with the given driver.
     */
    public static void doLogin(WebDriver driver) {
        driver.get("https://stackexchange.com/");
        TestSteps.doCaptcha(driver);

        driver.findElement(By.linkText("Log in")).click();
        TestSteps.doCaptcha(driver);

        Properties credentials = TestUtils.getProperties("credentials.properties");
        driver.findElement(By.id("email")).sendKeys(credentials.getProperty("email"));
        sleep(1.0);

        driver.findElement(By.id("password")).sendKeys(credentials.getProperty("password"));
        sleep(1.0);

        driver.findElement(By.id("submit-button")).click();
        sleep(4.0); // await redirect

        TestSteps.doCaptcha(driver);
    }
}
