package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.sections.SearchResultsSection;

@Getter
public class SearchResultsPage extends BasePage {
    private SearchResultsSection searchResultsSection;

    public SearchResultsPage(Page page) {
        super(page);
        this.searchResultsSection = new SearchResultsSection(page);
    }
}
