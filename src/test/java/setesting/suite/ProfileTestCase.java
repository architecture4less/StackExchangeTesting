package setesting.suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestListener;

/**
 * Test Stack Exchange's profile management functionalities.
 */
@Listeners(TestListener.class)
public final class ProfileTestCase {
    private ProfileTestCase() {}

    /**
     * Validate the ability to view the activity of another user.
     */
    @Test(priority = 11)
    public static void viewProfileTest() {
        // TODO
    }

    /**
     * Validate the ability to open the account's latest notification.
     */
    @Test(priority = 12)
    public static void viewNotifsTest() {
        // TODO
    }

    /**
     * Validate the ability to update the account's display name and bio.
     */
    @Test(priority = 13)
    public static void updateProfileTest() {
        // TODO med
    }

    /**
     * Validate the ability to update the account's profile picture.
     */
    @Test(priority = 14)
    public static void updateAvatarTest() {
        // TODO med
    }

    /**
     * Validate the ability to update the account's site preferences.
     */
    @Test(priority = 15)
    public static void updatePrefsTest() {
        // TODO med
    }
}
