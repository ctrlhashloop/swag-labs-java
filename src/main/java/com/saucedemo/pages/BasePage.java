package com.saucedemo.pages;

import com.saucedemo.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage{
    //Every page object extends this, so waiting logic lives in one place:
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    // Constructor to initialize driver, wait, and PageFactory elements
    protected BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(ConfigReader.getInteger("explicit.wait.seconds")));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
