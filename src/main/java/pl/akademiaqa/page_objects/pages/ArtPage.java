package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.sections.products.FilterBySection;
import pl.akademiaqa.page_objects.sections.products.ProductsSection;

@Getter
public class ArtPage extends BasePage {
    private ProductsSection productsSection;
    private FilterBySection filterBySection;

    public ArtPage(Page page) {
        super(page);
        productsSection = new ProductsSection(page);
        filterBySection = new FilterBySection(page);
    }
}
