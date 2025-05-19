package pl.akademiaqa.page_objects.sections.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import pl.akademiaqa.page_objects.common.BaseSection;
import pl.akademiaqa.page_objects.pages.ArtPage;

public class FilterBySection extends BaseSection {
    private Locator leftPriceSlider;
    private Locator priceSliderValues;
    private Locator grayLoadingOverlay;
    private Locator mattPaperCheckbox;
    private Locator availableCheckbox;

    public FilterBySection(Page page) {
        super(page);
        leftPriceSlider = page.locator(".ui-slider-handle").first();
        priceSliderValues = page.locator("//ul[@data-slider-label='Price']//p");
        grayLoadingOverlay = page.locator(".overlay__inner");
        mattPaperCheckbox = page.locator("#search_filters").getByLabel("Matt paper");
        availableCheckbox = page.locator("#search_filters").getByLabel("Available");
    }

    public ArtPage getArtPage() {
        return new ArtPage(page);
    }

    public FilterBySection filterByPriceUsingUrl(Double priceThreshhold) {
        String newURL = page.url() + "&q=Price-zł-" + priceThreshhold + "-44";
        page.navigate(newURL, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        return this;
    }

    public FilterBySection filterByPriceUsingMouse(Double priceThreshhold) {
        double width = leftPriceSlider.boundingBox().width;
        double height = leftPriceSlider.boundingBox().height;

        while (getPriceSliderLeftValue() < priceThreshhold) {
            leftPriceSlider.scrollIntoViewIfNeeded();
            double x = leftPriceSlider.boundingBox().x;
            double y = leftPriceSlider.boundingBox().y;
            page.mouse().move(x + width / 2, y + height / 2);
            page.mouse().down();
            page.mouse().move(x + width, y);
            page.mouse().up();
            page.waitForCondition(() -> grayLoadingOverlay.isHidden());
        }
        return this;
    }

    public FilterBySection filterByPriceUsingKeyboard(Double priceThreshhold) {
        while (getPriceSliderLeftValue() < priceThreshhold) {
            leftPriceSlider.scrollIntoViewIfNeeded();
            leftPriceSlider.focus();
            page.keyboard().press("ArrowRight");
            page.waitForCondition(() -> grayLoadingOverlay.isHidden());
        }
        return this;
    }

    public FilterBySection selectFilterCheckbox(FilterCheckboxes selectedCheckbox) {
        switch (selectedCheckbox) {
            case MATT_PAPER:
                mattPaperCheckbox.check();
                break;
            case AVAILABLE:
                availableCheckbox.check();
                break;
            default:
                throw new IllegalArgumentException("Unknown filter checkbox: " + selectedCheckbox);
        }
        page.waitForCondition(() -> grayLoadingOverlay.isHidden());
        return this;
    }

    public enum FilterCheckboxes {
        MATT_PAPER,
        AVAILABLE;
    }

    private double getPriceSliderLeftValue() {
        Double d = Double.parseDouble(
                priceSliderValues
                        .innerText()
                        .split(" ")[0]
                        .replace("zł", ""));
        return d;
    }
}
