package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest
{
    @Test(description = "Standard User Can Log In")
    public void ValidLoginShowsProducts(){
        ProductsPage products = loginPage.loginAs(standardUser(),password());
        Assert.assertTrue(products.isLoaded(),"Products page should be displayed");
    }
}
