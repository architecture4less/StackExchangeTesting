package setesting.suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestListener;

/**
 * Test Stack Exchange's site accessibility functionalities.
 */
@Listeners(TestListener.class)
public final class AccessibilityTestCase {
    private AccessibilityTestCase() {}

    /**
     * Validate that site content is screen reader friendly.
     */
    @Test(priority = 36)
    public static void ariaLabelsTest() {
        // TODO
    }

    /**
     * Validate that images have an alt text.
     */
    @Test(priority = 37)
    public static void imageAltTextTest() {
        // TODO
    }

    /**
     * Validate support for keyboard navigation on forms.
     */
    @Test(priority = 38)
    public static void formKeyNavTest() {
        // TODO
    }

    /**
     * Validate inclusion of meaningful text in button and link elements.
     */
    @Test(priority = 39)
    public static void semanticHtmlTest() {
        // TODO
    }

    /**
     * Compare a screenshot of the homepage between multiple browsers.
     */
    @Test(priority = 40)
    public static void browserConsistencyTest() {
        // TODO
    }
}
