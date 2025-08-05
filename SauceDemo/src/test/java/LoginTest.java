import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
  @Test
  public void checkPositiveLogin(){
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.name("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
    WebElement productsTitle = driver.findElement(By.xpath("//*[contains(text(), 'Products')]"));
    String titleText = productsTitle.getText();
    Assert.assertTrue(titleText.contains("Products"), "Title does not contain 'Products'");  }

  @Test
  public void checkNegativetestWithoutPassword(){
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.name("password")).sendKeys("");
    driver.findElement(By.id("login-button")).click();
    String errorContainer = driver.findElement(By.className("error-message-container")).getText();

    Assert.assertEquals(errorContainer,"Epic sadface: Password is required");
  }

  @Test
  public void checkNegativetestWithoutLogin(){
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name")).sendKeys("");
    driver.findElement(By.name("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
    String errorContainer = driver.findElement(By.className("error-message-container")).getText();

    Assert.assertEquals(errorContainer,"Epic sadface: Username is required");
  }

  @Test
  public void checkNegativetestWithOtherLoginAndPassword(){
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name")).sendKeys("test");
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.id("login-button")).click();
    String errorContainer = driver.findElement(By.className("error-message-container")).getText();

    Assert.assertEquals(errorContainer,"Epic sadface: Username and password do not match any user in this service");
  }

}
