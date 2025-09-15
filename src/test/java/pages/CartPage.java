package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
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

  @Step("Open cart page")
  public CartPage open() {
    log.info("Open cart page");
    driver.get(BASE_URL + "cart.html");
    return this;
  }

  @Step("Get cart title")
  public String getCartTitle() {
    log.info("Get cart title");
    return driver.findElement(CART_TITLE_LABEL).getText();
  }

  @Step("Get quantity cart label")
  public String getQuantityCartLabel() {
    log.info("Get quantity cart label");
    return driver.findElement(QUANTITY_CART_LABEL).getText();
  }

  @Step("Get description cart label")
  public String getDescriptionCartLabel() {
    log.info("Get description cart label");
    return driver.findElement(DESCRIPTION_CART_LABEL).getText();
  }

  @Step("Get product name")
  public String getProductName(String product) {
    log.info("Get name product");
    return driver.findElement(By.xpath(String.format(PRODUCT_NAME, product))).getText();
  }

  @Step("Get product price")
  public String getProductPrice(String product) {
    log.info("Get price product");
    String xpath = String.format(PRODUCT_PRICE, product);
    String priceText = driver.findElement(By.xpath(xpath)).getText().replace("$", "");
    return priceText;
  }

  public CartPage isPageOpened() {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE_LABEL));
    } catch (TimeoutException e) {
      log.error(e.getMessage());
      Assert.fail("Page isn't open");
    }
    return this;
  }

  @Step("Click continue shopping")
  public void clickContinueShopping() {
    log.info("Click continue button ");
    driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
  }

  @Step("Click checkout")
  public void clickCheckout() {
    log.info("Click checkout button");
    driver.findElement(CHECKOUT_BUTTON).click();
  }

  @Step("Click remove")
  public void clickRemove(String product) {
    By removingProduct = By.xpath(String.format(REMOVE_BUTTON_PATTERN, product));
    driver.findElement(removingProduct).click();
  }
}
