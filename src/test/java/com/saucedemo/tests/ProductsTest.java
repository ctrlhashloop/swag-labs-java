package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsTest extends BaseTest {

    private static final String BACKPACK = "Sauce Labs Backpack";
    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    @Test(description = "Inventory shows all six products")
    public void inventoryShowsSixProducts() {
        ProductsPage products = loginPage.loginAs(standardUser(), password());
        Assert.assertEquals(products.getProductCount(), 6, "Expected 6 products");
    }

    @Test(description = "Price low to high sorts prices ascending")
    public void sortByPriceLowToHigh() {
        ProductsPage products = loginPage.loginAs(standardUser(), password())
                .sortBy("Price (low to high)");
        List<Double> actual = products.getProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual, expected, "Prices should be sorted ascending");
    }

    @Test(description = "Name Z to A sorts names descending")
    public void sortByNameZToA() {
        ProductsPage products = loginPage.loginAs(standardUser(), password())
                .sortBy("Name (Z to A)");
        List<String> actual = products.getProductNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());
        Assert.assertEquals(actual, expected, "Names should be sorted Z to A");
    }

    @Test(description = "Cart badge tracks items added and removed")
    public void cartBadgeUpdates() {
        ProductsPage products = loginPage.loginAs(standardUser(), password());
        products.addToCart(BACKPACK).addToCart(BIKE_LIGHT);
        Assert.assertEquals(products.getCartBadgeCount(), 2, "Badge should show 2 after adding two items");
        products.removeFromCart(BACKPACK);
        Assert.assertEquals(products.getCartBadgeCount(), 1, "Badge should show 1 after removing one item");
    }
}
