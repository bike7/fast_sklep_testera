package pl.akademiaqa.page_objects.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.modal.ProductSummaryModal;

@Getter
public class ProductDetailsPage extends BasePage {

    Locator yourCustomizationMessageInput;
    Locator saveCustomizationButton;
    Locator customizationConfirmationMessage;
    Locator addToCartButton;

    public ProductDetailsPage(Page page) {
        super(page);
        yourCustomizationMessageInput = page.getByPlaceholder("Your message here");
        saveCustomizationButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save Customization"));
        customizationConfirmationMessage = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your customization:"));
        addToCartButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart"));

    }

    public ProductDetailsPage saveCustomizationMessage(String message) {
        yourCustomizationMessageInput.fill(message);
        saveCustomizationButton.click();
        customizationConfirmationMessage.waitFor();
        return this;
    }

    public ProductSummaryModal addToCart() {
        addToCartButton.click();
        return new ProductSummaryModal(page);
    }
}
