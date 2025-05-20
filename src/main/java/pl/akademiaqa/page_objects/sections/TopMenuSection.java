package pl.akademiaqa.page_objects.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.page_objects.common.BaseSection;
import pl.akademiaqa.page_objects.pages.ArtPage;
import pl.akademiaqa.page_objects.pages.SearchResultsPage;

public class TopMenuSection extends BaseSection {
    private Locator languageSelector;
    private Locator englishLanguageOption;
    private Locator polishLanguageOption;
    private Locator selectedLanguageOption;
    private Locator searchInput;
    private Locator artTabLink;

    public TopMenuSection(Page page) {
        super(page);
        this.languageSelector = page.locator(".language-selector");
        this.englishLanguageOption = page.locator("a[data-iso-code=en]");
        this.polishLanguageOption = page.locator("a[data-iso-code=pl]");
        this.selectedLanguageOption = page.locator("span[class=expand-more]");
        this.searchInput = page.locator("input[name=s]");
        this.artTabLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Art").setExact(true));
    }

    public SearchResultsPage searchForProduct(String productName) {
        searchInput.fill(productName);
        searchInput.press("Enter");
        return new SearchResultsPage(page);
    }

    public ArtPage clickArtLink() {
        artTabLink.click();
        return new ArtPage(page);
    }

    public TopMenuSection setPageLanguageTo(PageLanguage language) {
        if (!selectedLanguageOption.innerText().equalsIgnoreCase(language.toString())) {
            languageSelector.click();
            switch (language) {
                case ENGLISH -> englishLanguageOption.click();
                case POLSKI -> polishLanguageOption.click();
                default -> throw new IllegalArgumentException("Invalid language: " + language);
            }
        }
        return this;
    }

    public enum PageLanguage {
        ENGLISH,
        POLSKI
    }
}
