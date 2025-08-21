package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SauceDemoFindLocators extends BaseTest {

  @Test(testName = "Проверка локаторов",
      description = "Проверка локаторов",
      groups = {"smoke"})
  @Description("Проверка локаторов")
  @Owner("PavelHameny")
  public void searchLocatorsTest() {
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name"));
    driver.findElement(By.id("login-button"));
    driver.findElement(By.id("password"));
    driver.findElement(By.name("password"));
    driver.findElement(By.name("user-name"));
    driver.findElement(By.className("login_logo"));
    driver.findElement(By.className("login_wrapper-inner"));
    driver.findElement(By.tagName("input"));
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.name("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
    driver.findElement(By.linkText("LinkedIn"));
    driver.findElement(By.partialLinkText("Link"));

    //XPATH
    driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
    driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));//по тексту
    driver.findElement(By.xpath("//div[contains(text(), 'Backpack')]"));
    driver.findElement(By.xpath("//button[contains(@data-test, 'backpack')]"));
    driver.findElement(By.xpath("//span[text()='Products'][1]//ancestor::div[1]"));
    driver.findElement(By.xpath("//div[@id='shopping_cart_container']//descendant::a"));
    driver.findElement(By.xpath("//div[@class='inventory_item'][1]/following::div[2]"));
    driver.findElement(By.xpath("//span[@class='select_container']/parent::div"));
    driver.findElement(By.xpath("//span[@class='select_container']/preceding::div[1]"));
    driver.findElement(
        By.xpath("//div[@class='inventory_item' and @data-test='inventory-item'][1]"));

    //CSS
    driver.findElement(By.cssSelector(".shopping_cart_link"));
    driver.findElement(By.cssSelector("#contents_wrapper"));
    driver.findElements(By.cssSelector(".btn_primary.btn_small"));
    driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-backpack']"));
    driver.findElement(By.cssSelector("span[class='title']"));
    driver.findElement(By.cssSelector("footer.footer"));
    driver.findElement(By.cssSelector("[lang|='en']"));
    driver.findElement(By.cssSelector("a[href^='https']:nth-child(2)"));
    driver.findElement(By.cssSelector("[data-test$=twitter]"));
    driver.findElement(By.cssSelector("[data-test*=twitter]"));
    driver.findElements(By.cssSelector("[class~='btn_primary']"));
  }
}