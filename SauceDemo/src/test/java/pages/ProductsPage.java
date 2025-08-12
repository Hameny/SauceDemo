package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

  private final By TITLE = By.className("title");
  private final String ADD_TO_CART_PATTERN ="//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";

  private final By CART_LINK = By.cssSelector(".shopping_cart_link");

  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  public String getTitle() {
    return driver.findElement(TITLE).getText();
  }
  public List<WebElement> getWebElementsListOfCart() {
    return driver.findElements(CART_LINK);
  }

  public void addToCard(String product) {
    By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
    driver.findElement(addToCart).click();
  }

  public void clickShoppingCart() {
    driver.findElement(CART_LINK).click();
  }
}
