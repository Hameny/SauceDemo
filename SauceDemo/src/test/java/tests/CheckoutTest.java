package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest extends BaseTest{

  public String firstName = "Pavel";
  public String lastName = "Hameny";
  public String zipcode = "220000";
  SoftAssert softAssert = new SoftAssert();

  @Test
  public void checkEnterFullInformation() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    checkoutPage.openPageCheckOutInfo();
    checkoutPage.inputFullInfo(firstName, lastName, zipcode);
    checkoutPage.clickToButtonContinue();
    assertEquals(checkoutPage.getTitleOverview(), "Checkout: Overview", "Incorrect text");
  }

  @Test
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
