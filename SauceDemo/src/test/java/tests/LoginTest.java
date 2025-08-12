package tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test
  public void checkPositiveLogin() {
    loginPage.open();
    loginPage.login("standard_user", "secret_sauce");
    assertEquals(productsPage.getTitle(),
        "Products",
        "Логин не выполнен");
  }

  @Test
  public void checkNegativetestWithoutPassword() {
    loginPage.open();
    loginPage.login("standard_user", "");
    assertEquals(loginPage.getErrorMessage(),
        "Epic sadface: Password is required",
        "Сообщение об ошибке не соответствует");
  }

  @Test
  public void checkNegativetestWithoutLogin() {
    loginPage.open();
    loginPage.login("", "secret_sauce");
    assertEquals(loginPage.getErrorMessage(),
        "Epic sadface: Username is required",
        "Сообщение об ошибке не соответствует");
  }

  @Test
  public void checkNegativetestWithOtherLoginAndPassword() {
    loginPage.open();
    loginPage.login("Test", "Test");
    assertEquals(loginPage.getErrorMessage(),
        "Epic sadface: Username and password do not match any user in this service",
        "Сообщение об ошибке не соответствует");
  }

  @Test
  public void paramTest(){
    loginPage.open();
    loginPage.login(user, password);
    assertEquals(loginPage.getErrorMessage(),
        "Epic sadface: Username and password do not match any user in this service",
        "Сообщение об ошибке не соответствует");
  }
}
