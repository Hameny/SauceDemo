package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

  private final By TITLE = By.className("title");
  private final String ADD_TO_CART_PATTERN = "//div[text()='%s']\" +\n"
      + "        \"/ancestor::div[@class='inventory_item']\"\n"
      + "        + \"//div[@data-test='inventory-item-price']";

  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  public String getTitle() {
    return driver.findElement(TITLE).getText();
  }

  public void addToCard(String product) {
    driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
  }
}
