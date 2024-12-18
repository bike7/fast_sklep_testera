package pl.akademiaqa;

import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.page_objects.pages.HomePage;
import pl.akademiaqa.page_objects.sections.TopNavigationSection;

class FilterBrPriceTests extends TestFixtures {

    @Test
    void shouldReturnProductsWithPriceGraterThan40() {
        // given
        new HomePage(page)
                .navigate()
                .setPageLanguage(TopNavigationSection.PageLanguage.ENGLISH)
                .getTopMenuAndSearchSection()
                .clickArtLink();
        page.pause();
        // when
        // then
        System.out.println(page.url());
    }
}
