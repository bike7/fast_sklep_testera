package pl.akademiaqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.page_objects.pages.HomePage;
import pl.akademiaqa.page_objects.pages.OrderConfirmationPage;
import pl.akademiaqa.page_objects.sections.TopMenuSection;
import pl.akademiaqa.page_objects.sections.shippingdetails.PaymentSection;
import pl.akademiaqa.page_objects.sections.shippingdetails.PersonalInformationSection;
import pl.akademiaqa.page_objects.sections.shippingdetails.ShippingMethodSection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PurchaseTests extends TestFixtures {

    private TopMenuSection topMenu;

    @BeforeEach
    void setUp() {
        topMenu = new HomePage(page)
                .navigate()
                .getTopMenuSection()
                .setPageLanguageTo(TopMenuSection.PageLanguage.ENGLISH);
    }

    @DisplayName("Should purchase customizable mug")
    @Test
    void shouldPurchaseCustomizableMug() {
        String productName = "Customizable mug";
        Faker faker = new Faker();
        String customizationMessage = faker.backToTheFuture().quote();
        PersonalInformationSection.SocialTitle socialTitle = PersonalInformationSection.SocialTitle.MRS;
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = firstname + "@" + lastname + ".com";
        String address = faker.address().streetAddress();
        String zipCode = faker.numerify("##-###");
        String city = faker.address().city();
        ShippingMethodSection.DeliveryMethod deliveryMethod = ShippingMethodSection.DeliveryMethod.CLICK_AND_COLLECT;
        PaymentSection.PaymentType paymentType = PaymentSection.PaymentType.CASH_ON_DELIVERY;

        OrderConfirmationPage orderConfirmationPage = topMenu
                .searchForProduct(productName)
                .getProductListSection()
                .openProductDetails(productName)
                .saveCustomizationMessage(customizationMessage)
                .addToCart()
                .proceedToCheckoutOnModal()
                .proceedToCheckoutOnShoppingCart()
                .fillInPersonalInformationSection(socialTitle, firstname, lastname, email)
                .fillInAddressSection(address, zipCode, city)
                .fillInShippingMethodSection(deliveryMethod)
                .fillInPaymentSection(paymentType)
                .placeOrder();

        assertAll(
                () -> assertThat(orderConfirmationPage.getOrderedItemName()).contains(productName),
                () -> assertThat(orderConfirmationPage.getProductCustomizationText()).contains(customizationMessage),
                () -> assertThat(orderConfirmationPage.getPaymentMethod()).contains("Cash on delivery"),
                () -> assertThat(orderConfirmationPage.getShippingMethod()).contains("Click and collect"));
    }
}

