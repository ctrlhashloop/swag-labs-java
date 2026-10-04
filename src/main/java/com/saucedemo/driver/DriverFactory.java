package com.saucedemo.driver;

import com.saucedemo.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory
{
    // Owns the Browser
    // Tests run in parallel, so each thread needs its own browser. That is what ThreadLocal gives you:
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private DriverFactory() {};

    /*
    * The initDriver() method is responsible for setting up, configuring,
    * and launching the browser instance, and then storing it safely for the current thread.*/

    public static void initDriver(){
        ChromeOptions options = new ChromeOptions();

        if(ConfigReader.getBoolean("headless")){
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        // Stop Chrome's "save password" popups from blocking the login tests
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(ConfigReader.getInteger("page.load.timeout.seconds")));
        driver.manage().window().maximize();
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() { return DRIVER.get();}
    public static void quitDriver(){
        if(DRIVER.get() != null){
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}
