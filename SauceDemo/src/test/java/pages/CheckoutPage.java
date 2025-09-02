package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class CheckoutPage extends BasePage {

  private final By FIRST_NAME_INPUT = By.id("first-name");
  private final By TITLE = By.xpath("//span[text()='Checkout: Your Information']");
  private final By LAST_NAME_INPUT = By.id("last-name");
  private final By ZIP_CODE_INPUT = By.id("postal-code");
  private final By CONTINUE_BUTTON = By.xpath("//input[@data-test = 'continue']");
  private final By TITLE_OVERVIEW = By.xpath("//span[text() = 'Checkout: Overview']");
  private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");
  private final String PRODUCT_NAME = "//div[text() = '%s']";
  private final String PRODUCT_PRICE = "//*[text() = '%s']/ancestor::div[@class='cart_item']//*[@class='inventory_item_price']";
  private final By TOTAL_PRODUCT_PRICE = By.xpath("//div[@class = 'summary_total_label']");
  private final By COMPLETE_MESSAGE = By.xpath("//h2[@class = 'complete-header']");

  public CheckoutPage(WebDriver driver) {
    super(driver);
  }

  @Step("Get complete message")
  public String getCompleteMessage() {
    log.info("Get complete message");
    return driver.findElement(COMPLETE_MESSAGE).getText();
  }

  public CheckoutPage isPageOpened() {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
    } catch (TimeoutException e) {
      log.error(e.getMessage());
      Assert.fail("Page isn't open");
    }
    return this;
  }

  @Step("Click button 'FINISH'")
  public void clickButtonFinish() {
    log.info("Click button 'FINISH'");
    driver.findElement(By.xpath("//button[@id = 'finish']")).click();
  }

  @Step("get product name")
  public String getProductName(String product) {
    return driver.findElement(By.xpath(String.format(PRODUCT_NAME, product))).getText();
  }

  @Step("Get product price")
  public String getProductPrice(String product) {
    String xpath = String.format(PRODUCT_PRICE, product);
    String priceText = driver.findElement(By.xpath(xpath)).getText().replace("$", "");
    return priceText;
  }

  @Step("Get total price")
  public String getTotalPrice() {
    String totalText = driver.findElement(TOTAL_PRODUCT_PRICE).getText();
    return totalText.replace("Total: $", "");
  }

  @Step("Get title Overview")
  public String getTitleOverview() {
    log.info("Click button 'FINISH'");
    return driver.findElement(TITLE_OVERVIEW).getText();
  }

  @Step("Get error message")
  public String getErrorMessage() {
    return driver.findElement(ERROR_MESSAGE).getText();
  }

  @Step("Open page 'Check out info'")
  public void openPageCheckOutInfo() {
    log.info("Open page 'Check out info'");
    driver.get("https://www.saucedemo.com/checkout-step-one.html");
  }

  @Step("Open page 'ChecK out overview'")
  public void openPageCheckOutOverview() {
    log.info("Open page 'ChecK out overview'");
    driver.get("https://www.saucedemo.com/checkout-step-two.html");
  }

  @Step("Click Button 'Continue'")
  public void clickToButtonContinue() {
    log.info("Click Button 'Continue'");
    driver.findElement(CONTINUE_BUTTON).click();
  }

  @Step("Input full info")
  public void inputFullInfo(String firstName, String lastName, String zipCode) {
    driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
    driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
    driver.findElement(ZIP_CODE_INPUT).sendKeys(zipCode);
  }
}
