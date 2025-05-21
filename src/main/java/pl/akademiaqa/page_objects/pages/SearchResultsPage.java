package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.sections.ProductListSection;

@Getter
public class SearchResultsPage extends BasePage {
    private final ProductListSection productListSection;

    public SearchResultsPage(Page page) {
        super(page);
        this.productListSection = new ProductListSection(page);
    }

}
