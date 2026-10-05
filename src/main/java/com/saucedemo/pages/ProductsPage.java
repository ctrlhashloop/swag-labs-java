package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By ITEM_NAMES = By.cssSelector("[data-test='inventory-item-name']");
    private static final By ITEM_PRICES = By.cssSelector("[data-test='inventory-item-price']");
    private static final By SORT_DROPDOWN = By.cssSelector("[data-test='product-sort-container']");
    private static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");
    private static final By CART_LINK = By.cssSelector("[data-test='shopping-cart-link']");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private static By itemButton(String productName) {
        return By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']"
                + "/ancestor::div[@data-test='inventory-item']//button");
    }

    public boolean isLoaded() {
        return isDisplayed(TITLE) && getText(TITLE).equals("Products");
    }

    public int getProductCount() {
        return findAll(ITEM_NAMES).size();
    }

    public List<String> getProductNames() {
        return findAll(ITEM_NAMES).stream().map(WebElement::getText).toList();
    }

    public List<Double> getProductPrices() {
        return findAll(ITEM_PRICES).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .toList();
    }

    public ProductsPage sortBy(String visibleText) {
        Select select = new Select(wait.until(d -> d.findElement(SORT_DROPDOWN)));
        select.selectByVisibleText(visibleText);
        return this;
    }

    public ProductsPage addToCart(String productName) {
        click(itemButton(productName));
        return this;
    }

    public ProductsPage removeFromCart(String productName) {
        click(itemButton(productName));
        return this;
    }

    public int getCartBadgeCount() {
        List<WebElement> badge = findAll(CART_BADGE);
        return badge.isEmpty() ? 0 : Integer.parseInt(badge.get(0).getText());
    }

    public CartPage openCart() {
        click(CART_LINK);
        return new CartPage(driver);
    }

    public LoginPage logout() {
        click(MENU_BUTTON);
        click(LOGOUT_LINK);
        return new LoginPage(driver);
    }
}
