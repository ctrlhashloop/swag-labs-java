package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By ITEM_NAMES = By.cssSelector("[data-test='inventory-item-name']");
    private static final By CHECKOUT_BUTTON = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private static By removeButton(String productName) {
        return By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']"
                + "/ancestor::div[@data-test='inventory-item']//button");
    }

    public boolean isLoaded() {
        return isDisplayed(TITLE) && getText(TITLE).equals("Your Cart");
    }

    public List<String> getItemNames() {
        return findAll(ITEM_NAMES).stream().map(WebElement::getText).toList();
    }

    public CartPage removeItem(String productName) {
        click(removeButton(productName));
        return this;
    }

    public CheckoutInfoPage checkout() {
        click(CHECKOUT_BUTTON);
        return new CheckoutInfoPage(driver);
    }
}
