package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.page_objects.common.BasePage;

public class ShoppingCartPage extends BasePage {
    Locator proceedToCheckoutButton;

    public ShoppingCartPage(Page page) {
        super(page);
        proceedToCheckoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed to checkout"));
    }

    public ShippingDetailsPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new ShippingDetailsPage(page);
    }
}
