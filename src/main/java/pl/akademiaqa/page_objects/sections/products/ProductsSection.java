package pl.akademiaqa.page_objects.sections.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.page_objects.common.BaseSection;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsSection extends BaseSection {
    List<Locator> products;
    List<Locator> productsPrices;
    private Page page;

    public ProductsSection(Page page) {
        super(page);
        this.products = page.locator(".js-product").all();
        this.productsPrices = page.locator(".js-product .price").all();
    }

    public List<Double> getProductPrices() {
        return productsPrices
                .stream()
                .map(Locator::innerText)
                .map(s -> s.replaceAll("zł", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
    
    public int getProductCount() {
        return products.size();
    }
}
