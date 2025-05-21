package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.page_objects.common.BasePage;

public class OrderConfirmationPage extends BasePage {
    Locator orderedProduct;
    Locator customizationLink;
    Locator customizationDetails;
    Locator closeCustomizationDetailsButton;
    Locator paymentMethod;
    Locator shippingMethod;

    public OrderConfirmationPage(Page page) {
        super(page);
        orderedProduct = page.locator("//div[@class='order-confirmation-table']//div[contains(@class, 'details')]");
        customizationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Product customization"));
        customizationDetails = page.locator("//div[contains(@class,'product-customization')]");
        closeCustomizationDetailsButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
        paymentMethod = page.getByText("Payment method: ");
        shippingMethod = page.getByText("Shipping method: ");
    }

    public String getProductCustomizationText() {
        customizationLink.click();
        String details = customizationDetails.innerText().replace("Type your text here", "");
        closeCustomizationDetailsButton.click();
        return details;
    }

    public String getOrderedItemName() {
        return orderedProduct.innerText().replace("Product customization", "");
    }

    public String getPaymentMethod() {
        return paymentMethod.innerText().replace("Payment method: ", "");
    }

    public String getShippingMethod() {
        return shippingMethod.innerText().replace("Shipping method: ", "");
    }
}
