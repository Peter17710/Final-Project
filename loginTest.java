package saueclab.saucelab.loginTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import saueclab.saucelab.pagefactory.loginPage;

public class loginTest {
    WebDriver driver;
    loginPage login;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C://Users//Peter//Desktop//demo//saucelab//src//test//java//saueclab//saucelab//resources//chromedriver-win64//chromedriver.exe") ;
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/");
        login = new loginPage(driver);
    }
    @Test
    public void positiveLoginScenario(){
        login.typeUsername("standard_user");
        login.verifyUsername("standard_user");
        login.typePassword("secret_sauce");
        login.verifyPassword("secret_sauce");
        login.clickLoginBtn();
        login.verifyLogin("https://www.saucedemo.com/v1/inventory.html");
    }
    @AfterClass
    public void terminate(){
        driver.quit();
    }

}
