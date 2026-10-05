# Swag Labs Automation Framework (Java)

UI test automation framework for [Swag Labs](https://www.saucedemo.com) built with
**Java 17, Selenium WebDriver 4, TestNG, Maven, ExtentReports** and the **Page Object Model**.

## Project structure

```
src/main/java/com/saucedemo
  config/   ConfigReader        reads config.properties, supports -D overrides
  driver/   DriverFactory       ThreadLocal WebDriver (Chrome, Firefox, Edge, headless)
  pages/    BasePage + page objects (Login, Products, Cart, CheckoutInfo/Overview/Complete)
  utils/    ScreenshotUtil
src/main/resources/config.properties
src/test/java/com/saucedemo
  base/      BaseTest           setup / teardown
  listeners/ TestListener, ExtentManager   reporting + screenshot on failure
  tests/     LoginTest, ProductsTest, CartTest, CheckoutTest
testng.xml                      suite (parallel by method, 2 threads)
```

## Design notes

- Page objects hold locators and actions only; assertions live in tests.
- Explicit waits only (no implicit waits, no `Thread.sleep`).
- One driver per thread, so tests are safe to run in parallel.
- Failed tests get a Base64 screenshot embedded in the Extent report.
- Config is overridable from the command line.

## Run

```bash
mvn clean test                                   # Chrome, headed
mvn clean test -Dheadless=true                   # headless
mvn clean test -Dbrowser=firefox -Dheadless=true
```

Report: `target/extent-reports/ExtentReport.html`

## CI

GitHub Actions workflow in `.github/workflows/ci.yml` runs the suite headless on every push and PR
and uploads the Extent report as an artifact.
