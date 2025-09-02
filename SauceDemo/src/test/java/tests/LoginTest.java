package tests;

import static org.testng.Assert.assertEquals;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test(priority = 1,
      invocationCount = 2,
      description = "Проверка входа в систему с валидными данными",
      testName = "Позитивный тест.Вход в систему с валидными данными",
      groups = {"smoke"})
  @Description("Проверка входа в систему с валидными данными")//Allure
  @Owner("PavelHameny")//Создатель теста
  @Link("123")//ссылка на доку
  @Epic("Логин пейдж")
  @Feature("Log in")
  @Story("Проверка входа в систему с валидными данными")
  @Severity(SeverityLevel.CRITICAL)
  @Lead("PavelH")
  @TmsLink("")//ссылка на тест-кейс
  @Issue("")//ссылка на баг репорт
  public void checkPositiveLogin() {
    loginPage.open()
        .isPageOpened()
        .login("standard_user", "secret_sauce");
    assertEquals(productsPage.getTitle(),
        "Products",
        "Логин не выполнен");
  }


  @Test(priority = 1,
      invocationCount = 2,
      description = "Проверка входа в систему без пароля",
      testName = "Негативный тест.Вход в систему без пароля",
      groups = {"smoke"})
  @Description("Проверка входа в систему без пароля")//Allure
  @Owner("PavelHameny")//Создатель теста
  public void checkNegativetestWithoutPassword() {
    loginPage.open()
        .isPageOpened()
        .login("standard_user", "");
    assertEquals(loginPage.getErrorMessage(),
        "Epic sadface: Password is required!!",
        "Сообщение об ошибке не соответствует");
  }

  @Test(priority = 1,
      invocationCount = 2,
      description = "Проверка входа в систему без логина",
      testName = "Негативный тест.Вход в систему без логина",
      groups = {"smoke"})
  @Description("Проверка входа в систему без логина")
  @Owner("PavelHameny")
  public void checkNegativetestWithoutLogin() {
    loginPage.open()
        .isPageOpened()
        .login("", "secret_sauce");
    assertEquals(loginPage.getErrorMessage(),
        "Epic sadface: Username is required",
        "Сообщение об ошибке не соответствует");
  }

  @Test(priority = 1,
      invocationCount = 2,
      description = "Проверка входа в систему без логина и  пароля",
      testName = "Негативный тест.Вход в систему без логина и пароля",
      groups = {"smoke"})
  @Description("Проверка входа в систему без логина и  пароля")
  @Owner("PavelHameny")
  public void checkNegativetestWithOtherLoginAndPassword() {
    loginPage.open()
        .isPageOpened()
        .login("", "");
    assertEquals(loginPage.getErrorMessage(),
        "Epic sadface: Username is required",
        "Сообщение об ошибке не соответствует");
  }

  @DataProvider(name = "LoginData")
  public Object[][] loginData() {
    return new Object[][]{
        {"standard_user", "", "Epic sadface: Password is required"},
        {"", "secret_sauce", "Epic sadface: Username is required"},
        {"test", "test",
            "Epic sadface: Username and password do not match any user in this service"}
    };
  }

  @Test(dataProvider = "LoginData",
      groups = {"smoke"},
      description = "Проверка получения сообщений при различных способах входа",
      testName = "Негативный тест логина")
  @Description("Проверка получения сообщений при различных способах входа")
  @Owner("PavelHameny")
  public void checkLoginWithNegativeValue1(String user, String password, String expectedMessage) {
    loginPage.open()
        .isPageOpened()
        .login(user, password);
    assertEquals(loginPage.getErrorMessage(),
        expectedMessage,
        "Сообщение не соответствует");
  }
}
