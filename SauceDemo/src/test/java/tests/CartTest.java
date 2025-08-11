package tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

  String productOne = "Sauce Labs Onesie";
  String productTwo = "Sauce Labs Fleece Jacket";
  SoftAssert softAssert = new SoftAssert();

  @Test
  public void checkAddProductToCart() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    productsPage.addToCard(productOne);
    cartPage.open();
    String productInCart = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
    assertEquals(productInCart, "Sauce Labs Onesie", "Наименование продукта не совпало");
  }

  @Test
  public void checkAddTwoProductToCart() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    productsPage.addToCard(productOne);
    productsPage.addToCard(productTwo);
    productsPage.clickShoppingCart();

    String firstProductName = cartPage.getProductName(productOne);
    String secondProductName = cartPage.getProductName(productTwo);
    String firstProductPrice = cartPage.getProductPrice(productOne);
    String secondProductPrice = cartPage.getProductPrice(productTwo);

    softAssert.assertEquals(firstProductName, productOne, "Наименование первого товара не совпадает");
    softAssert.assertEquals(firstProductPrice, "7.99", "Цена первого товара не совпадает");
    softAssert.assertEquals(secondProductName, productTwo, "Наименование второго товара не совпадает");
    softAssert.assertEquals(secondProductPrice, "49.99", "Цена второго товара не совпадает");
    softAssert.assertAll();
  }

  @Test
  void checkProceedToCheckoutFromCart() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    productsPage.addToCard(productOne);
    productsPage.clickShoppingCart();
    cartPage.clickCheckout();

    String openedCheckoutPage = driver.findElement(By.cssSelector(".title")).getText();

    assertEquals(openedCheckoutPage, "Checkout: Your Information", "Страница 'Checkout' не открылась");
  }

}
