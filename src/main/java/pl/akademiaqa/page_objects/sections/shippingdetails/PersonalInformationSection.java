package pl.akademiaqa.page_objects.sections.shippingdetails;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.page_objects.common.BaseSection;

public class PersonalInformationSection extends BaseSection {
    Locator mrCheckbox;
    Locator mrsCheckbox;
    Locator firstname;
    Locator lastname;
    Locator email;
    Locator termsAndConditionsCheckbox;
    Locator dataPrivacyCheckbox;

    public PersonalInformationSection(Page page) {
        super(page);
        mrCheckbox = page.getByLabel("Mr.");
        mrsCheckbox = page.getByLabel("Mrs.");
        firstname = page.getByLabel("First name");
        lastname = page.getByLabel("Last name");
        email = page.getByLabel("Email");
        termsAndConditionsCheckbox = page.getByLabel("I agree to the terms");
        dataPrivacyCheckbox = page.getByLabel("Customer data privacy");
    }

    public PersonalInformationSection enterFirstName(String firstName) {
        firstname.fill(firstName);
        return this;
    }

    public PersonalInformationSection enterLastName(String lastName) {
        lastname.fill(lastName);
        return this;
    }

    public PersonalInformationSection enterEmail(String emailAddress) {
        email.fill(emailAddress);
        return this;
    }

    public PersonalInformationSection selectTermsAndConditions() {
        termsAndConditionsCheckbox.check();
        return this;
    }

    public PersonalInformationSection selectDataPrivacy() {
        dataPrivacyCheckbox.check();
        return this;
    }

    public PersonalInformationSection selectSocialTitle(SocialTitle title) {
        switch (title) {
            case MR -> mrCheckbox.check();
            case MRS -> mrsCheckbox.check();
            default -> throw new IllegalArgumentException("Invalid social title: " + title);
        }
        return this;
    }

    public enum SocialTitle {
        MR,
        MRS;
    }
}