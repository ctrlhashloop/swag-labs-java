package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private static final By SUBTOTAL = By.cssSelector("[data-test='subtotal-label']");
    private static final By TAX = By.cssSelector("[data-test='tax-label']");
    private static final By TOTAL = By.cssSelector("[data-test='total-label']");
    private static final By FINISH_BUTTON = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    private static double parseAmount(String label) {
        return Double.parseDouble(label.substring(label.indexOf('$') + 1));
    }

    public double getSubtotal() {
        return parseAmount(getText(SUBTOTAL));
    }

    public double getTax() {
        return parseAmount(getText(TAX));
    }

    public double getTotal() {
        return parseAmount(getText(TOTAL));
    }

    public CheckoutCompletePage finish() {
        click(FINISH_BUTTON);
        return new CheckoutCompletePage(driver);
    }
}
