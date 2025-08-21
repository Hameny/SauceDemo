package tests;

import static org.testng.Assert.assertEquals;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

  public String firstName = "Pavel";
  public String lastName = "Hameny";
  public String zipcode = "220000";

  @Test(priority = 1,
      invocationCount = 2,
      description = "Проверка ввода всей информации в поле Имя,Фамилия,Почтовый индекс",
      testName = "Позитивный тест.Проверка ввода всей информации",
      groups = {"smoke"})
  @Description("Проверка ввода всей информации в поле Имя,Фамилия,Почтовый индекс")
  @Owner("PavelHameny")
  public void checkEnterFullInformation() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    checkoutPage.openPageCheckOutInfo();
    checkoutPage.inputFullInfo(firstName, lastName, zipcode);
    checkoutPage.clickToButtonContinue();
    assertEquals(checkoutPage.getTitleOverview(), "Checkout: Overview", "Incorrect text");
  }

  @Test(priority = 1,
      invocationCount = 2,
      description = "Проверка выполнения заказа",
      testName = "Позитивный тест.Проверка выполнения заказа",
      groups = {"smoke"})
  @Description("Проверка выполнения заказа")
  @Owner("PavelHameny")
  public void CheckCompleteCheckout() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    productsPage.addToCard("Sauce Labs Onesie");
    checkoutPage.openPageCheckOutInfo();
    checkoutPage.inputFullInfo(firstName, lastName, zipcode);
    checkoutPage.openPageCheckOutOverview();
    checkoutPage.clickButtonFinish();
    assertEquals(checkoutPage.getCompleteMessage(),
        "Thank you for your order!",
        "Не правильное сообщение");
  }
}
