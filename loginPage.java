package saueclab.saucelab.pagefactory;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {
    WebDriver driver;

    By username_txtfield = By.id("user-name");
    By password_txtfield = By.id("password");
    By login_btn = By.id("login-button");
    public loginPage(WebDriver driver){
        this.driver = driver;
    }
    public void typeUsername(String username){
        driver.findElement(username_txtfield).sendKeys(username);
    }
    public void typePassword(String password){
        driver.findElement(password_txtfield).sendKeys(password);
    }
    public void clickLoginBtn(){
        driver.findElement(login_btn).click();
    }
    public void verifyUsername(String username){
        Assert.assertEquals(driver.findElement(username_txtfield).getAttribute("value"), username);
    }
    public void verifyPassword(String password){
        Assert.assertEquals(driver.findElement(password_txtfield).getAttribute("value"), password);
    }
    public void verifyLogin(String redirectedURL){
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html", "Login failed.");
    }
}
