package setesting.suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestListener;

/**
 * Test Stack Exchange's anonymous user functionality.
 */
@Listeners(TestListener.class)
public final class AnonUserTestCase {
    private AnonUserTestCase() {}

    /**
     * Validate the presence of important elements on the homepage.
     */
    @Test(priority = 1)
    public static void homepageTest() {
        // TODO
    }

    /**
     * Validate the tour page, viewing all sites, and navigating to area 51.
     */
    @Test(priority = 2)
    public static void siteTourTest() {
        // TODO
    }

    /**
     * Validate the contact us form displays a popover that can be closed.
     */
    @Test(priority = 3)
    public static void contactFormTest() {
        // TODO
    }

    /**
     * Validate searching for a moderator's profile in the about us page.
     */
    @Test(priority = 4)
    public static void moderatorSearchTest() {
        // TODO
    }

    /**
     * Validate selecting a community from the banner dropdown.
     */
    @Test(priority = 5)
    public static void communityBrowseTest() {
        // TODO
    }
}
