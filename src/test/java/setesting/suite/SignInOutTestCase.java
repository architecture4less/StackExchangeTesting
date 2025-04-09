package setesting.suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestListener;

/**
 * Test Stack Exchange's user login/logout functionality.
 */
@Listeners(TestListener.class)
public final class SignInOutTestCase {
    private SignInOutTestCase() {}

    /**
     * Validate the functionality of the sign up page.
     */
    @Test(priority = 6)
    public static void signupFormTest() {
        // TODO
    }

    /**
     * Validate an error message for invalid login credentials.
     */
    @Test(priority = 7)
    public static void badLoginTest() {
        // TODO
    }

    /**
     * Validate account access with valid login credentials.
     */
    @Test(priority = 8)
    public static void goodLoginTest() {
        // TODO LARGE
    }

    /**
     * Validate homepage redirection upon logout.
     */
    @Test(priority = 9)
    public static void logoutTest() {
        // TODO med
    }

    /**
     * Validate restriction to the account management page upon logout.
     */
    @Test(priority = 10)
    public static void logoutNoAccessTest() {
        // TODO med
    }
}
