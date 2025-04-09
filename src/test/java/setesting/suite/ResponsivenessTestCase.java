package setesting.suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestListener;

/**
 * Test Stack Exchange's responsive design functionalities.
 */
@Listeners(TestListener.class)
public final class ResponsivenessTestCase {
    private ResponsivenessTestCase() {}

    /**
     * Validate the homepage renders in the time listed on the performance page.
     */
    @Test(priority = 31)
    public static void homepageRenderTimeTest() {
        // TODO
    }

    /**
     * Validate the feed is visible in smaller browser window.
     */
    @Test(priority = 32)
    public static void smallFeedLayoutTest() {
        // TODO
    }

    /**
     * Validate login button and window is usable in a smaller browser window.
     */
    @Test(priority = 33)
    public static void smallLoginModalTest() {
        // TODO
    }

    /**
     * Validate sites dropdown is usable in a smaller browser window.
     */
    @Test(priority = 34)
    public static void smallDropdownTest() {
        // TODO
    }

    /**
     * Validate text editor is usable in a smaller browser window.
     */
    @Test(priority = 35)
    public static void smallTextEditorTest() {
        // TODO med
    }
}
