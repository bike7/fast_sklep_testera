package pl.akademiaqa.page_objects.sections.shippingdetails;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.page_objects.common.BaseSection;

public class AddressSection extends BaseSection {
    Locator address;
    Locator postalCode;
    Locator city;

    public AddressSection(Page page) {
        super(page);
        address = page.getByLabel("Address", new Page.GetByLabelOptions().setExact(true));
        postalCode = page.getByLabel("Zip/Postal Code");
        city = page.getByLabel("City");
    }

    public AddressSection enterAddress(String addressLine) {
        address.fill(addressLine);
        return this;
    }

    public AddressSection enterPostalCode(String code) {
        postalCode.fill(code);
        return this;
    }

    public AddressSection enterCity(String cityName) {
        city.fill(cityName);
        return this;
    }
}