package pl.akademiaqa.page_objects.modal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.pages.ShoppingCartPage;

public class ProductSummaryModal extends BasePage {
    Locator proceedToCheckoutButton;

    public ProductSummaryModal(Page page) {
        super(page);
        proceedToCheckoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed to checkout"));
    }

    public ShoppingCartPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new ShoppingCartPage(page);
    }
}
