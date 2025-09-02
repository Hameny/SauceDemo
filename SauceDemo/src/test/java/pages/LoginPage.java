package pages;


import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class LoginPage extends BasePage{

  private final By USERNAME_INPUT = By.id("user-name");
  private final By PASSWORD_INPUT = By.id("password");
  private final By LOGIN_BUTTON = By.id("login-button");
  private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @Step("Открытие страницы логина")
  public LoginPage open() {
    log.info("Login open page");
    driver.get(BASE_URL);
    return this;
  }

  public LoginPage isPageOpened() {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
    } catch (TimeoutException e) {
      log.error(e.getMessage());
      Assert.fail("Page isn't open");
    }
    return this;
  }

  @Step("Enput login '{user}' and password '{password}'")
  public ProductsPage login(String user,String password){
    log.info("Authorization: {}, {}", user, password);
    driver.findElement(USERNAME_INPUT).sendKeys(user);
    driver.findElement(PASSWORD_INPUT).sendKeys(password);
    driver.findElement(LOGIN_BUTTON).click();
    return new ProductsPage(driver);
  }
@Step("Get error message")
  public String getErrorMessage(){
    return driver.findElement(ERROR_MESSAGE).getText();
  }
}
