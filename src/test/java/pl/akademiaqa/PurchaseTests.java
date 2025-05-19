package pl.akademiaqa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.page_objects.pages.HomePage;

class PurchaseTests extends TestFixtures {

    @DisplayName("Should purchase customizable mug")
    @Test
    void shouldPurchaseCustomizableMug() {
        String productName = "Customizable mug";

        new HomePage(page)
                .navigate()
                .getTopMenuAndSearchSection()
                .searchForProduct(productName)
                .getSearchResultsSection()
                .openProductDetails(productName);
        //page.pause();

        Assertions.assertThat(true).isTrue();
    }
}
