package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.sections.shippingdetails.AddressSection;
import pl.akademiaqa.page_objects.sections.shippingdetails.PaymentSection;
import pl.akademiaqa.page_objects.sections.shippingdetails.PersonalInformationSection;
import pl.akademiaqa.page_objects.sections.shippingdetails.ShippingMethodSection;

public class ShippingDetailsPage extends BasePage {

    PersonalInformationSection personalInformationSection;
    AddressSection addressSection;
    ShippingMethodSection shippingMethodSection;
    PaymentSection paymentSection;
    Locator continueButton;
    Locator placeOrderButton;

    public ShippingDetailsPage(Page page) {
        super(page);
        personalInformationSection = new PersonalInformationSection(page);
        addressSection = new AddressSection(page);
        shippingMethodSection = new ShippingMethodSection(page);
        paymentSection = new PaymentSection(page);
        continueButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"));
        placeOrderButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Place order"));
    }

    public ShippingDetailsPage fillInPersonalInformationSection(PersonalInformationSection.SocialTitle socialTitle, String firstname, String lastname, String email) {
        personalInformationSection.selectSocialTitle(socialTitle)
                .enterFirstName(firstname)
                .enterLastName(lastname)
                .enterEmail(email)
                .selectTermsAndConditions()
                .selectDataPrivacy();
        clickContinueButton();
        return this;
    }

    public ShippingDetailsPage fillInAddressSection(String addressLine, String postalCode, String cityName) {
        addressSection.enterAddress(addressLine)
                .enterPostalCode(postalCode)
                .enterCity(cityName);
        clickContinueButton();
        return this;
    }

    public ShippingDetailsPage fillInShippingMethodSection(ShippingMethodSection.DeliveryMethod deliveryMethod) {
        shippingMethodSection.selectDeliveryMethod(deliveryMethod);
        clickContinueButton();
        return this;
    }

    public ShippingDetailsPage fillInPaymentSection(PaymentSection.PaymentType paymentType) {
        paymentSection.selectPaymentType(paymentType)
                .selectPaymentTermsAndConditions();
        return this;
    }

    public OrderConfirmationPage placeOrder() {
        placeOrderButton.click();
        return new OrderConfirmationPage(page);
    }

    private void clickContinueButton() {
        continueButton.click();
    }
}