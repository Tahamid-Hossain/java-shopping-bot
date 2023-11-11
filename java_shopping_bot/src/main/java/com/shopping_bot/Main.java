package com.shopping_bot;

import java.util.Properties;
import java.util.Scanner;
import com.microsoft.playwright.*;
import javax.activation.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter product name: ");
        String productName = userInput.nextLine();

        System.out.println("Number of results to be shown: ");
        int nproducts = userInput.nextInt();


        String product1;
        String price1;

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://amazon.ae");
        page.getByPlaceholder("Search Amazon.ae").fill(productName);
        page.locator("id=nav-search-submit-button").click();
        // page.locator("(//div[@role='button'])[1]").click();
        // page.locator("(//img[@class='s-image'])[1]").click();
        // System.out.println("Product 1: " + page.locator("id=productTitle").textContent());
        /* for (int i = 1; i <=nproducts; i++){
            page.locator("img[data-image-index='%s']".formatted(i)).click();
            // System.out.println("Product"+ i + "link: " + page.url());
            
            System.out.println("Product "+ i + ": " + page.locator("id=productTitle").textContent());
            
            System.out.println("Price: " + page.locator("(//span[@class='a-offscreen'])[1]").textContent());
            page.goBack();
        }*/
        page.locator("img[data-image-index='%s']".formatted(nproducts)).click();
        product1 = page.locator("id=productTitle").textContent();
        price1 = page.locator("(//span[@class='a-offscreen'])[1]").textContent();
        playwright.close();

        String to = "mitahamid13@gmail.com";
        String from = "amazonbotcud@gmail.com";
        String host = "localhost";
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);

        
        Session session = Session.getDefaultInstance(properties, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("amazonbotcud@gmail.com", "gwrs bolh lvdf riiv");
    }});

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Your product results");

            message.setText(product1 + price1);

            Transport.send(message);

            System.out.println("Message sent successfully");
    }catch(MessagingException mex){
        mex.printStackTrace();}

    }
}
