package pl.akademiaqa.common;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.*;
import pl.akademiaqa.page_objects.utils.BrowserFactory;
import pl.akademiaqa.page_objects.utils.Properties;

import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFixtures {

    private BrowserFactory browserFactory;
    private Browser browser;
    private BrowserContext context;
    protected Page page;

    @BeforeAll
    void setUpBrowser() {
        browserFactory = new BrowserFactory();
        browser = browserFactory.getBrowser();
    }

    @BeforeEach
    void setUpContext() {
        context = browser.newContext();
        if (Properties.isTracingEnabled()) {
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }
        page = context.newPage();
        page.setViewportSize(1920, 1080);
    }

    @AfterEach
    void closeContext() {
        if (Properties.isTracingEnabled()) {
            context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("traces/trace.zip")));
        }
        context.close();
    }

    @AfterAll
    void teardown() {
        browserFactory.getPlaywright().close();
    }

}
