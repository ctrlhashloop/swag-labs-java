package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutInfoPage fillDetails(String firstName, String lastName, String postalCode) {
        if (firstName != null && !firstName.isEmpty()) {
            type(FIRST_NAME, firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            type(LAST_NAME, lastName);
        }
        if (postalCode != null && !postalCode.isEmpty()) {
            type(POSTAL_CODE, postalCode);
        }
        return this;
    }

    public CheckoutOverviewPage continueToOverview() {
        click(CONTINUE_BUTTON);
        return new CheckoutOverviewPage(driver);
    }

    /** Clicks Continue when validation is expected to fail, so the page stays put. */
    public CheckoutInfoPage continueExpectingError() {
        click(CONTINUE_BUTTON);
        return this;
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }
}
