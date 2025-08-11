package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

  private final By FIRST_NAME_INPUT = By.xpath("//input[@data-test = 'firstName']");
  private final By LAST_NAME_INPUT = By.xpath("//input[@data-test = 'lastName']");
  private final By ZIP_CODE_INPUT = By.xpath("//input[@data-test = 'postalCode']");
  private final By CONTINUE_BUTTON = By.xpath("//input[@data-test = 'continue']");
  private final By TITLE_OVERVIEW = By.xpath("//span[text() = 'Checkout: Overview']");
  private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");
  private final String PATTERN_PRODUCT_NAME = "//div[text() = '%s']";
  private final String PATTERN_PRODUCT_PRICE =
      "//div[text() = '%s']/ancestor::div[@class = 'cart_item']" +
          "//div[@class = 'inventory_item_price']";
  private final By TOTAL_PRODUCT_PRICE = By.xpath("//div[@class = 'summary_total_label']");
  private final By COMPLETE_MESSAGE = By.xpath("//h2[@class = 'complete-header']");

  public CheckoutPage(WebDriver driver) {
    super(driver);
  }

  public String getCompleteMessage() {
    return driver.findElement(COMPLETE_MESSAGE).getText();
  }

  public void clickButtonFinish() {
    driver.findElement(By.xpath("//button[@id = 'finish']")).click();
  }

  public String getProductName(String product) {
    return driver.findElement(By.xpath(String.format(PATTERN_PRODUCT_NAME, product))).getText();
  }

  public String getProductPrice(String product) {
    return driver.findElement(By.xpath(String.format(PATTERN_PRODUCT_PRICE, product))).getText();
  }

  public String getTotalPrice() {
    String totalText = driver.findElement(TOTAL_PRODUCT_PRICE).getText();
    return totalText.replace("Total: $", "");
  }

  public String getTitleOverview() {
    return driver.findElement(TITLE_OVERVIEW).getText();
  }

  public String getErrorMessage() {
    return driver.findElement(ERROR_MESSAGE).getText();
  }

  public void openPageCheckOutInfo() {
    driver.get("https://www.saucedemo.com/checkout-step-one.html");
  }

  public void openPageCheckOutOverview() {
    driver.get("https://www.saucedemo.com/checkout-step-two.html");
  }

  public void clickToButtonContinue() {
    driver.findElement(CONTINUE_BUTTON).click();
  }

  public void inputFullInfo(String firstName, String lastName, String zipCode) {
    driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
    driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
    driver.findElement(ZIP_CODE_INPUT).sendKeys(zipCode);
  }
}
