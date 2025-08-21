package tests;

import static org.testng.Assert.assertEquals;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

  String productOne = "Sauce Labs Onesie";
  String productTwo = "Sauce Labs Fleece Jacket";
  SoftAssert softAssert = new SoftAssert();

  @Test(priority = 2,
      invocationCount = 2,
      description = "Проверка добавления товара в корзину",
      testName = "Позитивный тест.Проверка добавления товара в корзину",
      groups = {"smoke"})
  @Description("Проверка добавления товара в корзину")
  @Owner("PavelHameny")
  public void checkAddProductToCart() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    productsPage.addToCard(productOne);
    cartPage.open();
    String productInCart = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
    assertEquals(productInCart, "Sauce Labs Onesie", "Наименование продукта не совпало");
  }

  @Test(priority = 2,
      invocationCount = 2,
      description = "Проверка добавления  2-х товаров в корзину",
      testName = "Позитивный тест.Проверка добавления  2-х товаров в корзину",
      groups = {"smoke"})
  @Description("Проверка добавления  2-х товаров в корзину")
  @Owner("PavelHameny")
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

    softAssert.assertEquals(firstProductName, productOne,
        "Наименование первого товара не совпадает");
    softAssert.assertEquals(firstProductPrice, "7.99", "Цена первого товара не совпадает");
    softAssert.assertEquals(secondProductName, productTwo,
        "Наименование второго товара не совпадает");
    softAssert.assertEquals(secondProductPrice, "49.99", "Цена второго товара не совпадает");
    softAssert.assertAll();
  }

  @Test(priority = 2,
      invocationCount = 2,
      description = "Проверка Checkout при попадании в корзину",
      testName = "Позитивный тест.Проверка Checkout при попадании в корзину",
      groups = {"smoke"})
  @Description("Проверка Checkout при попадании в корзину")
  @Owner("PavelHameny")
  void checkProceedToCheckoutFromCart() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    productsPage.addToCard(productOne);
    productsPage.clickShoppingCart();
    cartPage.clickCheckout();

    String openedCheckoutPage = driver.findElement(By.cssSelector(".title")).getText();

    assertEquals(openedCheckoutPage, "Checkout: Your Information",
        "Страница 'Checkout' не открылась");
  }

}
