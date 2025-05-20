package pl.akademiaqa.page_objects.sections.shippingdetails;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.page_objects.common.BaseSection;

public class PaymentSection extends BaseSection {
    Locator payByCashOnDelivery;
    Locator payByBankWire;
    Locator payByCheck;
    Locator paymentTermsAndConditionsCheckbox;

    public PaymentSection(Page page) {
        super(page);
        payByCashOnDelivery = page.locator("//input[contains(@data-module-name,'cashondelivery')]");
        payByBankWire = page.locator("//input[contains(@data-module-name,'wirepayment')]");
        payByCheck = page.locator("//input[contains(@data-module-name,'checkpayment')]");
        paymentTermsAndConditionsCheckbox = page.locator("#checkout-payment-step").getByLabel("I agree to the terms");
    }

    public PaymentSection selectPaymentType(PaymentType paymentType) {
        switch (paymentType) {
            case BANK_WIRE -> payByBankWire.check();
            case CASH_ON_DELIVERY -> payByCashOnDelivery.check();
            case CHECK -> payByCheck.check();
            default -> throw new IllegalArgumentException("Invalid payment type: " + paymentType);
        }
        return this;
    }

    public PaymentSection selectPaymentTermsAndConditions() {
        paymentTermsAndConditionsCheckbox.check();
        return this;
    }

    public enum PaymentType {
        BANK_WIRE,
        CASH_ON_DELIVERY,
        CHECK;
    }
}