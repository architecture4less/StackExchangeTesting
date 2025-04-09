package setesting;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Utility functions for the Stack Exchange testing suite.
 */
final class TestUtils {
    private TestUtils() {}

    public static final Path RUN_DIR = Path.of("").toAbsolutePath();

    public static Dimension getCurrentPageSize(WebDriver driver) {
        if (driver instanceof JavascriptExecutor js) {
            Long pageWidth = (Long) js.executeScript("return document.documentElement.scrollWidth;");
            Long pageHeight = (Long) js.executeScript("return document.documentElement.scrollHeight;");
            return new Dimension(
                Optional.ofNullable(pageWidth).map(Long::intValue).orElse(0),
                Optional.ofNullable(pageHeight).map(Long::intValue).orElse(0));
        }
        throw new IllegalArgumentException("driver cannot execute javascript");
    }

    public static File saveScreenshot(WebDriver driver, Path saveFolder) {
        if (driver instanceof TakesScreenshot screenshotTaker) {
            File tempFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
            File saveFile = saveFolder.resolve(tempFile.getName()).toFile();
            try {
                FileHandler.copy(tempFile, saveFile);
                return saveFile;
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        throw new IllegalArgumentException("driver cannot take screenshot");
    }

    public static void switchToAnotherWindow(WebDriver driver) {
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    public static void sleep(double seconds) {
        try {
            Thread.sleep((int) (Math.max(0, seconds) * 1000));
        }
        catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
