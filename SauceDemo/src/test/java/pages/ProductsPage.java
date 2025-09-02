package pages;

import io.qameta.allure.Step;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ProductsPage extends BasePage {

  private final By TITLE = By.className("title");
  private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";

  private final By CART_LINK = By.cssSelector(".shopping_cart_link");

  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  public ProductsPage isPageOpened() {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
    } catch (TimeoutException e) {
      log.error(e.getMessage());
      Assert.fail("Page isn't open");
    }
    return this;
  }

  @Step("Поиск сообщения")
  public String getTitle() {
    log.info("Search message and get text");
    return driver.findElement(TITLE).getText();
  }

  @Step("Поиск товара")
  public List<WebElement> getWebElementsListOfCart() {
    return driver.findElements(CART_LINK);
  }

  @Step("Добавление товара '{product}' в корзину")
  public void addToCard(String product) {
    log.info("Click add product");
    By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
    driver.findElement(addToCart).click();
  }

  @Step("Нажатие на кнопку корзина")
  public void clickShoppingCart() {
    log.info("Click cart button");
    driver.findElement(CART_LINK).click();
  }
}
