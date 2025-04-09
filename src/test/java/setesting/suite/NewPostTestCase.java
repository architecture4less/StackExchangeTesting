package setesting.suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setesting.TestListener;

/**
 * Test Stack Exchange's question posting functionalities.
 */
@Listeners(TestListener.class)
public final class NewPostTestCase {
    private NewPostTestCase() {}

    /**
     * Validate text inputting in rich text mode.
     */
    @Test(priority = 26)
    public static void inputRichTextTest() {
        // TODO med
    }

    /**
     * Validate text inputting in markdown mode.
     */
    @Test(priority = 27)
    public static void inputMarkdownTest() {
        // TODO med
    }

    /**
     * Validate ability to ask a question.
     */
    @Test(priority = 28)
    public static void postQuestionTest() {
        // TODO LARGE
    }

    /**
     * Validate ability to post an answer.
     */
    @Test(priority = 29)
    public static void postAnswerTest() {
        // TODO LARGE
    }

    /**
     * Validate ability to comment on a question.
     */
    @Test(priority = 30)
    public static void postCommentTest() {
        // TODO LARGE
    }
}
