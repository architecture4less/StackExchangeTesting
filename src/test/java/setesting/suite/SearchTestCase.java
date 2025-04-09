package setesting.suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestListener;

/**
 * Test Stack Exchange's question search functionality.
 */
@Listeners(TestListener.class)
public final class SearchTestCase {
    private SearchTestCase() {}

    /**
     * Validate the ability to search for and view a question.
     */
    @Test(priority = 16)
    public static void searchQuestionTest() {
        // TODO
    }

    /**
     * Validate the ability to search for and view a community.
     */
    @Test(priority = 17)
    public static void searchCommunityTest() {
        // TODO
    }

    /**
     * Validate the ability to search for and view a user.
     */
    @Test(priority = 18)
    public static void searchUserTest() {
        // TODO
    }

    /**
     * Validate the functionality of the page selector.
     */
    @Test(priority = 19)
    public static void usePageSelectorTest() {
        // TODO
    }

    /**
     * Validate the functionality of the lines per page selector.
     */
    @Test(priority = 20)
    public static void usePaginatorTest() {
        // TODO
    }
}
