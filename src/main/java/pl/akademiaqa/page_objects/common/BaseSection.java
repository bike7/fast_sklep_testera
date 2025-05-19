package pl.akademiaqa.page_objects.common;

import com.microsoft.playwright.Page;

public class BaseSection {
    protected Page page;

    public BaseSection(Page page) {
        this.page = page;
    }
}
