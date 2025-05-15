package pl.akademiaqa.page_objects.common;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
        this.page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}
