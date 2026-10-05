package com.saucedemo.base;

import com.saucedemo.config.ConfigReader;
import com.saucedemo.driver.DriverFactory;
import com.saucedemo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver).open();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverFactory.quitDriver();
    }

    protected String standardUser() {return ConfigReader.get("standard.user");}
    protected String password() {return ConfigReader.get("password");}
}
