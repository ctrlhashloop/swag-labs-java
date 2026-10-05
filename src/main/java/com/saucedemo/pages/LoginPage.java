package com.saucedemo.pages;

import com.saucedemo.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage
{
    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) { super(driver); }

    public LoginPage open(){
        driver.get(ConfigReader.get("base.url"));
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(USERNAME, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD, password);
        return this;
    }

    /** Happy-path login; returns the next page in the flow. */
    public ProductsPage loginAs(String user, String pass) {   // returns NEXT page
        type(USERNAME, user);
        type(PASSWORD, pass);
        click(LOGIN_BUTTON);
        return new ProductsPage(driver);
    }

    /** Negative login; stays on the login page so the error can be asserted. */
    public LoginPage loginExpectingFailure(String username, String password) {
        if (username != null && !username.isEmpty()) {
            enterUsername(username);
        }
        if (password != null && !password.isEmpty()) {
            enterPassword(password);
        }
        click(LOGIN_BUTTON);
        return this;
    }

    public boolean isErrorDisplayed() { return isDisplayed(ERROR);}
    public boolean isLoginButtonDisplayed() { return isDisplayed(LOGIN_BUTTON);}
    public String getErrorMessage() { return getText(ERROR); }
}
