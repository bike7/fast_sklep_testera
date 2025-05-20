package pl.akademiaqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.page_objects.pages.HomePage;
import pl.akademiaqa.page_objects.sections.FilterBySection;
import pl.akademiaqa.page_objects.sections.TopMenuSection;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FilterByTests extends TestFixtures {

    @DisplayName("Filter products by different criteria")
    @ParameterizedTest(name = "Should return {1} product(s) when filtering by {0}")
    @MethodSource("provideFilterData")
    void shouldFilterProducts(FilterBySection.FilterCheckboxes filterType, int expectedProductCount) {
        int actualProductCount = new HomePage(page)
                .navigate()
                .getTopMenuSection()
                .setPageLanguageTo(TopMenuSection.PageLanguage.ENGLISH)
                .clickArtLink()
                .getFilterBySection()
                .selectFilterCheckbox(filterType)
                .getArtPage()
                .getProductListSection()
                .getNumberOfProducts();

        assertThat(actualProductCount).isEqualTo(expectedProductCount);
    }

    private static Stream<Arguments> provideFilterData() {
        return Stream.of(
                Arguments.of(FilterBySection.FilterCheckboxes.MATT_PAPER, 3),
                Arguments.of(FilterBySection.FilterCheckboxes.AVAILABLE, 7)
        );
    }

}