package pl.akademiaqa.page_objects.sections.search;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BaseSection;

import java.util.List;

@Getter
public class SearchResultsSection extends BaseSection {
    private List<Locator> products;

    public SearchResultsSection(Page page) {
        super(page);
        products = page.locator(".js-product").all();
    }
}
