package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Page;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.utils.PageUtils;

public class ArtPage extends BasePage {
    public ArtPage(Page page) {
        super(page);
        PageUtils.waitForPageLoad(page);
    }
}
