package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

  WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  private final By USERNAME_INPUT = By.id("user-name");
  private final By PASSWORD_INPUT = By.id("password");
  private final By LOGIN_BUTTON = By.id("login-button");
  private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

  public void open(){
    driver.get("https://www.saucedemo.com/");
  }

  public void login(String user,String password){
    driver.findElement(USERNAME_INPUT).sendKeys(user);
    driver.findElement(PASSWORD_INPUT).sendKeys(password);
    driver.findElement(LOGIN_BUTTON).click();
  }

  public String getErrorMessage(){
    return driver.findElement(ERROR_MESSAGE).getText();
  }
}
