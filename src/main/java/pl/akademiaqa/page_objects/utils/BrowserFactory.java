package pl.akademiaqa.page_objects.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    private Playwright playwright;
    private Browser browser;

    public Playwright getPlaywright() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        return playwright;
    }

    public Browser getBrowser() {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(Properties.isBrowserHeadless())
                .setSlowMo(Properties.getBrowserSlowMo());

        switch (Properties.getBrowserType()) {
            case "chromium" -> browser = getPlaywright().chromium().launch(launchOptions);
            case "firefox" -> browser = getPlaywright().firefox().launch(launchOptions);
            case "webkit" -> browser = getPlaywright().webkit().launch(launchOptions);
            case "chrome" -> browser = getPlaywright().chromium().launch(launchOptions.setChannel("chrome"));
            case "msedge" -> browser = getPlaywright().chromium().launch(launchOptions.setChannel("msedge"));
            default -> throw new IllegalArgumentException("Unsupported browser type");
        }
        return browser;
    }
}
