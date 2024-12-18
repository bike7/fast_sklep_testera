package pl.akademiaqa.page_objects.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class PageUtils {

    public static void waitForPageLoad(Page page) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}
