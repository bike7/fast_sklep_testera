package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.sections.FilterBySection;
import pl.akademiaqa.page_objects.sections.ProductListSection;

@Getter
public class ArtPage extends BasePage {
    private ProductListSection productListSection;
    private FilterBySection filterBySection;

    public ArtPage(Page page) {
        super(page);
        productListSection = new ProductListSection(page);
        filterBySection = new FilterBySection(page);
    }
}
