package setesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static setesting.TestUtils.sleep;

/**
 * Common steps that need to be performed for multiple tests.
 */
public final class TestSteps {
    private TestSteps() {}

    /**
     * Log into stackexchange using the stored credentials with the given driver.
     */
    public static void doLogin(WebDriver driver) {
        driver.get("https://stackexchange.com/");
        sleep(1.0);

        driver.findElement(By.linkText("Log in")).click();
        sleep(1.0);

        Properties credentials = TestUtils.getProperties("credentials.properties");
        driver.findElement(By.id("email")).sendKeys(credentials.getProperty("email"));
        sleep(1.0);

        driver.findElement(By.id("password")).sendKeys(credentials.getProperty("password"));
        sleep(1.0);

        driver.findElement(By.id("submit-button")).click();
        sleep(4.0); // await redirect
    }
}
