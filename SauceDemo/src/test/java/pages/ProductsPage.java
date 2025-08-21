package pages;

import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

  private final By TITLE = By.className("title");
  private final String ADD_TO_CART_PATTERN = "//div[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";

  private final By CART_LINK = By.cssSelector(".shopping_cart_link");

  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  @Step("Поиск сообщения")
  public String getTitle() {
    return driver.findElement(TITLE).getText();
  }

  @Step("Поиск товара")
  public List<WebElement> getWebElementsListOfCart() {
    return driver.findElements(CART_LINK);
  }

  @Step("Добавление товара '{product}' в корзину")
  public void addToCard(String product) {
    By addToCart = By.xpath(String.format(ADD_TO_CART_PATTERN, product));
    driver.findElement(addToCart).click();
  }

  @Step("Нажатие на кнопку корзина")
  public void clickShoppingCart() {
    driver.findElement(CART_LINK).click();
  }
}
