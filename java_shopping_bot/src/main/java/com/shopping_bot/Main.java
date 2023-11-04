package com.shopping_bot;

import com.microsoft.playwright.*;

public class Main {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://amazon.ae");
        page.getByPlaceholder("Search Amazon.ae").type("phone");
        // page.locator("id=nav-search-submit-button").click();

        

    }
}
