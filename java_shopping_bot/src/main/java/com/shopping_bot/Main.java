package com.shopping_bot;

import com.microsoft.playwright.*;

public class Main {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://amazon.ae");
        page.getByPlaceholder("Search Amazon.ae").type("iphone 15 pro max ");
        page.locator("id=nav-search-submit-button").click();
        // page.locator("(//div[@role='button'])[1]").click();
        // page.locator("(//img[@class='s-image'])[1]").click();
        // System.out.println("Product 1: " + page.locator("id=productTitle").textContent());
        System.out.println("Product 1: " + page.locator("(//span[contains(@class,'a-size-base-plus a-color-base')])[1]").textContent());
        // System.out.println(page.locator("(//span[@class='a-price-whole'])[1]").textContent());
        System.out.println("Price: " + page.locator("(//span[@class='a-offscreen'])[1]").textContent());

        

    }
}
