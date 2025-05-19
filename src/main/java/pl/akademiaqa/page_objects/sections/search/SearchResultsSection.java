package pl.akademiaqa.page_objects.sections.search;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.page_objects.common.BaseSection;

import java.util.List;


public class SearchResultsSection extends BaseSection {
    private final List<Locator> products;
    private final List<Locator> productLinks;

    public SearchResultsSection(Page page) {
        super(page);
        products = page.locator(".js-product").all();
        productLinks = page.locator(".js-product .product-description a").all();
    }

    public int getNumberOfProducts() {
        return products.size();
    }

    public void openProductDetails(String productName) {
        productLinks
                .stream()
                .filter(p -> p.innerText().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Can't find product with name: " + productName))
                .click();
    }

}
