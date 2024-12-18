package pl.akademiaqa.page_objects.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.page_objects.common.BasePage;
import pl.akademiaqa.page_objects.pages.ArtPage;
import pl.akademiaqa.page_objects.pages.SearchResultsPage;

public class TopMenuAndSearchSection extends BasePage {
    private Locator searchInput;

    private Locator artLink;

    public TopMenuAndSearchSection(Page page) {
        super(page);
        this.searchInput = page.locator("input[name=s]");
        this.artLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Art").setExact(true));
    }

    public SearchResultsPage searchForProduct(String productName) {
        searchInput.fill(productName);
        searchInput.press("Enter");
        return new SearchResultsPage(page);
    }

    public ArtPage clickArtLink() {
        artLink.click();
        return new ArtPage(page);
    }
}
