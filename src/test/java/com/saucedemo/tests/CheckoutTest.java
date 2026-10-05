package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutInfoPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private static final String BACKPACK = "Sauce Labs Backpack";

    private CheckoutInfoPage goToCheckout() {
        return loginPage.loginAs(standardUser(), password())
                .addToCart(BACKPACK)
                .openCart()
                .checkout();
    }

    @Test(description = "Complete end-to-end purchase")
    public void endToEndPurchase() {
        CheckoutCompletePage complete = goToCheckout()
                .fillDetails("Jane", "Doe", "00100")
                .continueToOverview()
                .finish();
        Assert.assertEquals(complete.getConfirmationHeader(), "Thank you for your order!");
    }

    @Test(description = "Order total equals subtotal plus tax")
    public void totalEqualsSubtotalPlusTax() {
        CheckoutOverviewPage overview = goToCheckout()
                .fillDetails("Jane", "Doe", "00100")
                .continueToOverview();
        double expected = Math.round((overview.getSubtotal() + overview.getTax()) * 100.0) / 100.0;
        Assert.assertEquals(overview.getTotal(), expected, 0.001, "Total should be subtotal + tax");
    }

    @Test(description = "Missing first name blocks checkout")
    public void missingFirstNameShowsError() {
        CheckoutInfoPage info = goToCheckout()
                .fillDetails("", "Doe", "00100")
                .continueExpectingError();
        Assert.assertTrue(info.getErrorMessage().contains("First Name is required"));
    }

    @Test(description = "Missing postal code blocks checkout")
    public void missingPostalCodeShowsError() {
        CheckoutInfoPage info = goToCheckout()
                .fillDetails("Jane", "Doe", "")
                .continueExpectingError();
        Assert.assertTrue(info.getErrorMessage().contains("Postal Code is required"));
    }
}
