package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.sections.search.TopMenuAndSearchSection;
import pl.akademiaqa.page_objects.sections.search.TopNavigationSection;
import pl.akademiaqa.page_objects.utils.Properties;

@Getter
public class HomePage extends BasePage {
    private TopMenuAndSearchSection topMenuAndSearchSection;
    private TopNavigationSection topNavigationSection;

    public HomePage(Page page) {
        super(page);
        this.topMenuAndSearchSection = new TopMenuAndSearchSection(page);
        this.topNavigationSection = new TopNavigationSection(page);
    }

    public HomePage navigate() {
        page.navigate(Properties.getBaseUrl(), new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        return this;
    }

    public HomePage setPageLanguage(TopNavigationSection.PageLanguage language) {
        topNavigationSection.setPageLanguageTo(language);
        return this;
    }
}
