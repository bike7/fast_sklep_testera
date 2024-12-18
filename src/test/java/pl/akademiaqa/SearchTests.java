package pl.akademiaqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.page_objects.pages.HomePage;
import pl.akademiaqa.page_objects.sections.TopNavigationSection;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SearchTests extends TestFixtures {

    @DisplayName("Search for products by product name")
    @ParameterizedTest(name = "Should return {1} product(s) for search phrase: {0}")
    @MethodSource("provideSearchData")
    void should_return_products_by_product_name(String productName, int expectedSearchResultsSize) {
        int actualSearchResultsSize = new HomePage(page)
                .navigate()
                .setPageLanguage(TopNavigationSection.PageLanguage.POLSKI)
                .getTopMenuAndSearchSection()
                .searchForProduct(productName)
                .getSearchResultsSection()
                .getProducts()
                .size();

        assertThat(actualSearchResultsSize).isEqualTo(expectedSearchResultsSize);
    }

    private static Stream<Arguments> provideSearchData() {
        return Stream.of(
                Arguments.of("mug", 5),
                Arguments.of("frame", 4),
                Arguments.of("t-shirt", 1),
                Arguments.of("notebook", 3),
                Arguments.of("graphics", 3)
        );
    }
}
