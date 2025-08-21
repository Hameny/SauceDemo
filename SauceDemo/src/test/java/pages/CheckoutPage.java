package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

  private final By FIRST_NAME_INPUT = By.id("first-name");
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
    return driver.findElement(COMPLETE_MESSAGE).getText();
  }

  @Step("Click button 'FINISH'")
  public void clickButtonFinish() {
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
    return driver.findElement(TITLE_OVERVIEW).getText();
  }

  @Step("Get error message")
  public String getErrorMessage() {
    return driver.findElement(ERROR_MESSAGE).getText();
  }

  @Step("Open page 'Check out info'")
  public void openPageCheckOutInfo() {
    driver.get("https://www.saucedemo.com/checkout-step-one.html");
  }

  @Step("Open page 'ChecK out overview'")
  public void openPageCheckOutOverview() {
    driver.get("https://www.saucedemo.com/checkout-step-two.html");
  }

  @Step("Click Button 'Continue'")
  public void clickToButtonContinue() {
    driver.findElement(CONTINUE_BUTTON).click();
  }

  @Step("Input full info")
  public void inputFullInfo(String firstName, String lastName, String zipCode) {
    driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
    driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
    driver.findElement(ZIP_CODE_INPUT).sendKeys(zipCode);
  }
}
