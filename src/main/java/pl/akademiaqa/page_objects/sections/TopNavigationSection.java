package pl.akademiaqa.page_objects.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.page_objects.common.BasePage;

public class TopNavigationSection extends BasePage {
    private Locator languageSelector;
    private Locator englishLanguageOption;
    private Locator polishLanguageOption;
    private Locator selectedOption;

    public TopNavigationSection(Page page) {
        super(page);
        this.languageSelector = page.locator(".language-selector");
        this.englishLanguageOption = page.locator("a[data-iso-code=en]");
        this.polishLanguageOption = page.locator("a[data-iso-code=pl]");
        this.selectedOption = page.locator("span[class=expand-more]");
    }

    public TopNavigationSection setPageLanguageTo(PageLanguage language) {
        if (!selectedOption.innerText().equalsIgnoreCase(language.toString())) {
            languageSelector.click();
            switch (language) {
                case ENGLISH:
                    englishLanguageOption.click();
                    break;
                case POLSKI:
                    polishLanguageOption.click();
                    break;
            }
        }
        return this;
    }

    public enum PageLanguage {
        ENGLISH,
        POLSKI
    }
}
