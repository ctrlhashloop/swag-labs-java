package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest
{
    @Test(description = "Standard User Can Log In")
    public void ValidLoginShowsProducts(){
        ProductsPage products = loginPage.loginAs(standardUser(),password());
        Assert.assertTrue(products.isLoaded(),"Products page should be displayed");
    }

    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLogins() {
        return new Object[][]{
                {"invalid_user",  "secret_sauce", "Username and password do not match"},
                {"",              "secret_sauce", "Username is required"},
                {"standard_user", "",             "Password is required"}
        };
    }

    @Test(dataProvider = "invalidLogins")
    public void invalidLoginShowsError(String user, String pass, String expected) {
        loginPage.loginExpectingFailure(user, pass);
        Assert.assertTrue(loginPage.getErrorMessage().contains(expected));
    }
}
