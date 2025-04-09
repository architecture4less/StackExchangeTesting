package setesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Path;
import java.util.Map;

/**
 * The web drivers that should be available for this testing environment.
 */
public enum TestBrowser {

    CHROME("chrome", TestUtils.RUN_DIR.resolve("src/test/resources/webdrivers/chromedriver-win64/chromedriver.exe")) {
        @Override
        public WebDriver open() {
            ChromeOptions opts = new ChromeOptions();
            return new ChromeDriver(opts);
        }
    },
    EDGE("edge", TestUtils.RUN_DIR.resolve("src/test/resources/webdrivers/edgedriver-win64/msedgedriver.exe")) {
        @Override
        public WebDriver open() {
            EdgeOptions opts = new EdgeOptions();
            opts.setExperimentalOption("prefs", Map.of(
                "download.default_directory", TestUtils.RUN_DIR.toString(),
                "download.prompt_for_download", false
            ));
            return new EdgeDriver(opts);
        }
    },
    FIREFOX("gecko", TestUtils.RUN_DIR.resolve("src/test/resources/webdrivers/geckodriver-win64/geckodriver.exe")) {
        @Override
        public WebDriver open() {
            FirefoxOptions opts = new FirefoxOptions();
            return new FirefoxDriver(opts);
        }
    },
    OPERA("opera", TestUtils.RUN_DIR.resolve("src/test/resources/webdrivers/operadriver-win64/operadriver.exe")) {
        @Override
        public WebDriver open() {
            ChromeOptions opts = new ChromeOptions();
            return new ChromeDriver(opts);
        }
    };

    TestBrowser(String driverName, Path driverPath) {
        System.setProperty("webdriver." + driverName + ".driver", driverPath.toString());
    }

    public abstract WebDriver open();
}
