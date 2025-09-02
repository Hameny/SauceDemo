package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPageFactory extends BasePage {

  @FindBy(id = "username")
  WebElement userNameField;
  @FindBy(id = "password")
  WebElement userPasswordField;
  @FindBy(id = "Login")
  WebElement buttonLogin;

  private final By LOGIN_FIELD = By.id("username");
  private final By PASSWORD_FIELD = By.id("password");

  public LoginPageFactory(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get(BASE_URL);
  }

  public void isPageOpened() {
    wait.until(ExpectedConditions.visibilityOfElement(buttonLogin));
  }

  public void login(String user, String password) {
    driver.findElement(LOGIN_FIELD).sendKeys(user);
    driver.findElement(PASSWORD_FIELD).sendKeys(password);
    driver.findElement(By.id("Login")).click();
  }
}
