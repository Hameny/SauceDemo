package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get(BASE_URL + "inventory.html");
  }


}
