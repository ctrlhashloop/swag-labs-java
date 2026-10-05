package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    private static final String BACKPACK = "Sauce Labs Backpack";
    private static final String ONESIE = "Sauce Labs Onesie";

    @Test(description = "Items added on Products page appear in the cart")
    public void addedItemsAppearInCart() {
        CartPage cart = loginPage.loginAs(standardUser(), password())
                .addToCart(BACKPACK)
                .addToCart(ONESIE)
                .openCart();
        Assert.assertTrue(cart.isLoaded(), "Cart page should be displayed");
        Assert.assertEquals(cart.getItemNames(), List.of(BACKPACK, ONESIE));
    }

    @Test(description = "Removing an item empties it from the cart")
    public void removeItemFromCart() {
        CartPage cart = loginPage.loginAs(standardUser(), password())
                .addToCart(BACKPACK)
                .openCart()
                .removeItem(BACKPACK);
        Assert.assertTrue(cart.getItemNames().isEmpty(), "Cart should be empty after removal");
    }
}
