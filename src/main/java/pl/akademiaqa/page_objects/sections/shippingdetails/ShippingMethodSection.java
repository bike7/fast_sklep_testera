package pl.akademiaqa.page_objects.sections.shippingdetails;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import pl.akademiaqa.page_objects.common.BaseSection;

public class ShippingMethodSection extends BaseSection {
    Locator deliveryMyCarrierOption;
    Locator deliveryClickAndCollectOption;

    public ShippingMethodSection(Page page) {
        super(page);
        deliveryClickAndCollectOption = page.getByLabel("Click and collect Pick up");
        deliveryMyCarrierOption = page.getByLabel("My carrier Delivery next day");
    }

    public ShippingMethodSection selectDeliveryMethod(DeliveryMethod deliveryMethod) {
        switch (deliveryMethod) {
            case CLICK_AND_COLLECT -> deliveryClickAndCollectOption.check();
            case MY_CARRIER -> deliveryMyCarrierOption.check();
            default -> throw new IllegalArgumentException("Invalid delivery method: " + deliveryMethod);
        }
        page.waitForLoadState(LoadState.NETWORKIDLE);
        return this;
    }

    public enum DeliveryMethod {
        CLICK_AND_COLLECT,
        MY_CARRIER;
    }
}