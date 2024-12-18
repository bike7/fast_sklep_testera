package pl.akademiaqa.page_objects.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BasePage;

import java.util.List;

@Getter
public class SearchResultsSection extends BasePage {
    private List<Locator> products;

    public SearchResultsSection(Page page) {
        super(page);
        products = page.locator(".js-product").all();
    }
}
