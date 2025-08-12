package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

  private final By CART_TITLE_LABEL = By.cssSelector(".title");
  private final By QUANTITY_CART_LABEL = By.cssSelector(".cart_quantity_label");
  private final By DESCRIPTION_CART_LABEL = By.cssSelector(".cart_desc_label");
  private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
  private final By CHECKOUT_BUTTON = By.id("checkout");
  private final String PRODUCT_PRICE = "//*[text() = '%s']/ancestor::div[@class='cart_item']//*[@class='inventory_item_price']";
  private final String REMOVE_BUTTON_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'cart_item']//button";
  private final String PRODUCT_NAME = "//div[text() = '%s']";

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get(BASE_URL + "cart.html");
  }

  public String getCartTitle() {
    return driver.findElement(CART_TITLE_LABEL).getText();
  }

  public String getQuantityCartLabel() {
    return driver.findElement(QUANTITY_CART_LABEL).getText();
  }

  public String getDescriptionCartLabel() {
    return driver.findElement(DESCRIPTION_CART_LABEL).getText();
  }

  public String getProductName(String product) {
    return driver.findElement(By.xpath(String.format(PRODUCT_NAME, product))).getText();
  }

  public String getProductPrice(String product) {
    String xpath = String.format(PRODUCT_PRICE, product);
    String priceText = driver.findElement(By.xpath(xpath)).getText().replace("$", "");
    return priceText;
  }

  public void clickContinueShopping() {
    driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
  }

  public void clickCheckout() {
    driver.findElement(CHECKOUT_BUTTON).click();
  }

  public void clickRemove(String product) {
    By removingProduct = By.xpath(String.format(REMOVE_BUTTON_PATTERN, product));
    driver.findElement(removingProduct).click();
  }
}
