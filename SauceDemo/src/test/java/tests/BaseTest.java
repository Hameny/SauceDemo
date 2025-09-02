package tests;

import static utils.AllureUtils.takeScreenshot;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

@Listeners(TestListener.class)
public class BaseTest {

  WebDriver driver;
  LoginPage loginPage;
  ProductsPage productsPage;
  CheckoutPage checkoutPage;
  CartPage cartPage;
  String user = System.getProperty("user");
  String password = System.getProperty("password");

  @Parameters({"browser"})
  @BeforeMethod(alwaysRun = true, description = "Настройка драйвера")
  public void setup(@Optional("chrome") String browser) {
    if (browser.equalsIgnoreCase("chrome")) {
      ChromeOptions options = new ChromeOptions();
      HashMap<String, Object> chromePrefs = new HashMap<>();
      chromePrefs.put("credentials_enable_service", false);
      chromePrefs.put("profile.password_manager_enabled", false);
      options.setExperimentalOption("prefs", chromePrefs);
      options.addArguments("--incognito");
      options.addArguments("--disable-notifications");
      options.addArguments("--disable-popup-blocking");
      options.addArguments("--disable-infobars");
      options.addArguments("--headless");
      driver = new ChromeDriver(options);
    } else if (browser.equalsIgnoreCase("Safari")) {
      SafariOptions options = new SafariOptions();
//       options.setAutomaticInspection(true);
//       options.setAutomaticProfiling(true);
      driver = new SafariDriver(options);
    }

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    loginPage = new LoginPage(driver);
    productsPage = new ProductsPage(driver);
    checkoutPage = new CheckoutPage(driver);
    cartPage = new CartPage(driver);
  }

  @AfterMethod(alwaysRun = true)
  public void teardown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      takeScreenshot(driver);
  }
    if(driver != null) {
      driver.quit();
    }
  }
}