package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class ProductInBasketTest extends BaseTest {

  @Test(priority = 2,
      invocationCount = 2,
      description = "Проверка наименования товара и стоимости в корзине и при добавлении в корзину",
      testName = "Позитивный тест.Проверка наименования товара и стоимости в корзине и при добавлении в корзину",
      groups = {"smoke"})
  @Description("Проверка наименования товара и стоимости в корзине и при добавлении в корзину")
  @Owner("PavelHameny")
  public void checkCartTest() {
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.name("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
    String nameProduct = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"))
        .getText();
    String priceProduct = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']" +
            "/ancestor::div[@class='inventory_item']//div[@data-test='inventory-item-price']"))
        .getText();
    driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
    driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    String allPriceBasket = driver.findElement(By.cssSelector(".inventory_item_price")).getText();
    String nameProductInBasket = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"))
        .getText();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(priceProduct, allPriceBasket);
    softAssert.assertEquals(nameProduct, nameProductInBasket);
  }
}