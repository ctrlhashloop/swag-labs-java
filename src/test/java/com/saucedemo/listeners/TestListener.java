package com.saucedemo.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.saucedemo.driver.DriverFactory;
import com.saucedemo.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();
    private final ExtentReports extent = ExtentManager.getInstance();

    @Override public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.assignCategory(result.getTestClass().getRealClass().getSimpleName());
        TEST.set(test);
    }

    @Override public void onTestSuccess(ITestResult result) { TEST.get().pass("Test passed"); }

    @Override public void onTestFailure(ITestResult result) {
        TEST.get().fail(result.getThrowable());
        if (DriverFactory.getDriver() != null) {
            String shot = ScreenshotUtil.asBase64(DriverFactory.getDriver());
            TEST.get().fail("Screenshot at failure",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(shot).build());
        }
    }

    @Override public void onTestSkipped(ITestResult result) { TEST.get().skip("Test skipped"); }

    @Override public void onFinish(ITestContext context) { extent.flush(); }   // writes the HTML file
}