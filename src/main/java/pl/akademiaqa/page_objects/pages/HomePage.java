package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.sections.TopMenuSection;
import pl.akademiaqa.page_objects.utils.Properties;

@Getter
public class HomePage extends BasePage {
    private TopMenuSection topMenuSection;

    public HomePage(Page page) {
        super(page);
        this.topMenuSection = new TopMenuSection(page);
    }

    public HomePage navigate() {
        page.navigate(Properties.getBaseUrl(), new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        return this;
    }

}
