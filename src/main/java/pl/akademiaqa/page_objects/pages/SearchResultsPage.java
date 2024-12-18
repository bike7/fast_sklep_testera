package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.page_objects.sections.SearchResultsSection;
import pl.akademiaqa.page_objects.utils.PageUtils;

@Getter
public class SearchResultsPage {
    private SearchResultsSection searchResultsSection;

    public SearchResultsPage(Page page) {
        PageUtils.waitForPageLoad(page);
        this.searchResultsSection = new SearchResultsSection(page);
    }
}
