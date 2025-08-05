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

  public void addToCard(String product) {
    driver.findElement(By.xpath("//div[text()='" + product + "']" +
        "/ancestor::div[@class='inventory_item']"
        + "//div[@data-test='inventory-item-price']")).click();
  }
}
