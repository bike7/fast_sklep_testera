package pl.akademiaqa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.page_objects.pages.ArtPage;
import pl.akademiaqa.page_objects.pages.HomePage;
import pl.akademiaqa.page_objects.sections.TopMenuSection;

import java.util.List;

class FilterByPriceTests extends TestFixtures {

    private ArtPage artPage;
    private final Double priceThreshold = 40.0;

    @BeforeEach
    void setUp() {
        artPage = new HomePage(page)
                .navigate()
                .getTopMenuSection()
                .setPageLanguageTo(TopMenuSection.PageLanguage.ENGLISH)
                .clickArtLink();
    }

    @DisplayName("Filter by price by inserting param into url")
    @Test
    void shouldReturnProductsWithPriceGreaterThan40FilterByUrl() {
        List<Double> prices = artPage
                .getFilterBySection()
                .filterByPriceUsingUrl(priceThreshold)
                .getArtPage()
                .getProductListSection()
                .getProductPrices();

        assertPricesAboveThreshold(prices);
    }

    @DisplayName("Filter by price by moving slider using mouse")
    @Test
    void shouldReturnProductsWithPriceGreaterThan40UsingMouse() {
        List<Double> prices = artPage
                .getFilterBySection()
                .filterByPriceUsingMouse(priceThreshold)
                .getArtPage()
                .getProductListSection()
                .getProductPrices();

        assertPricesAboveThreshold(prices);
    }

    @DisplayName("Filter by price by moving slider using keyboard")
    @Test
    void shouldReturnProductsWithPriceGreaterThan40UsingKeyboard() {
        List<Double> prices = artPage
                .getFilterBySection()
                .filterByPriceUsingKeyboard(priceThreshold)
                .getArtPage()
                .getProductListSection()
                .getProductPrices();

        assertPricesAboveThreshold(prices);
    }

    private void assertPricesAboveThreshold(List<Double> prices) {
        Assertions.assertThat(prices.stream().allMatch(price -> price > priceThreshold)).isTrue();
    }
}