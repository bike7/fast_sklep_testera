package pl.akademiaqa.page_objects.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.page_objects.common.BaseSection;
import pl.akademiaqa.page_objects.pages.ProductDetailsPage;

import java.util.List;

public class ProductListSection extends BaseSection {
    private final List<Locator> products;
    private final List<Locator> productLinks;
    private final List<Locator> productsPrices;


    public ProductListSection(Page page) {
        super(page);
        products = page.locator(".js-product").all();
        productLinks = page.locator(".js-product .product-description a").all();
        productsPrices = page.locator(".js-product .price").all();
    }


    public ProductDetailsPage openProductDetails(String productName) {
        productLinks
                .stream()
                .filter(p -> p.innerText().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Can't find product with name: " + productName))
                .click();
        return new ProductDetailsPage(page);
    }

    public List<Double> getProductPrices() {
        return productsPrices
                .stream()
                .map(Locator::innerText)
                .map(s -> s.replace("zł", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public int getNumberOfProducts() {
        return products.size();
    }
}
