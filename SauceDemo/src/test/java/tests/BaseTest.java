package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import pages.LoginPage;
import pages.ProductsPage;

public class BaseTest {

  WebDriver driver;
  LoginPage loginPage;
  ProductsPage productsPage;

  @BeforeMethod
  public void setup() {
    ChromeOptions options = new ChromeOptions();
    HashMap<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("credentials_enable_service", false);
    chromePrefs.put("profile.password_manager_enabled", false);
    options.setExperimentalOption("prefs", chromePrefs);
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--disable-infobars");
    options.addArguments("--start-maximized");//максимальный экран
    options.addArguments("--incognito");//инкогнито
    options.addArguments("--disable-notification");//убрать уведомленияя
    options.addArguments("--headless");//без открытия интерфейса
    driver = new ChromeDriver();

    loginPage = new LoginPage(driver);
    productsPage = new ProductsPage(driver);
  }

  @AfterMethod(alwaysRun = true)
  public void teardown() {
    driver.quit();
  }
}