package pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  WebDriver driver;
  WebDriverWait wait;

  public final String BASE_URL = "https://www.saucedemo.com/";

  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
  }

  public void clickJs(){
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("argument[0].click;",element);
  }
}
