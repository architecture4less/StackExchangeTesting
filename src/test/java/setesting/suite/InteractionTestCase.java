package setesting.suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestListener;

/**
 * Test Stack Exchange's question interaction functionalities.
 */
@Listeners(TestListener.class)
public final class InteractionTestCase {
    private InteractionTestCase() {}

    /**
     * Validate the ability to upvote and down-vote.
     */
    @Test(priority = 21)
    public static void ratePostTest() {
        // TODO med
    }

    /**
     * Validate the ability to copy the question's share link and then view the author's profile.
     */
    @Test(priority = 22)
    public static void useSocialLinksTest() {
        // TODO
    }

    /**
     * Validate the ability to revise or tag a question.
     */
    @Test(priority = 23)
    public static void revisePostTest() {
        // TODO LARGE
    }

    /**
     * Validate the ability to save a question and manage saved posts.
     */
    @Test(priority = 24)
    public static void bookmarkPostTest() {
        // TODO med
    }

    /**
     * Validate the ability to load more related questions and choose one.
     */
    @Test(priority = 25)
    public static void navRelatedPostsTest() {
        // TODO
    }
}
