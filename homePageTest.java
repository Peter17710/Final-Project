package saueclab.saucelab.loginTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import saueclab.saucelab.pagefactory.homePage;
import saueclab.saucelab.pagefactory.loginPage;
import org.testng.Assert;
import java.time.Duration;

public class homePageTest {

    private WebDriver driver;
    private loginPage login;
    private homePage homePage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C://Users//Peter//Desktop//demo//saucelab//src//test//java//saueclab//saucelab//resources//chromedriver-win64//chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void positiveAddToCartScenario() {
        login = new loginPage(driver);
        login.typeUsername("standard_user");
        login.typePassword("secret_sauce");
        login.clickLoginBtn();

        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_link")));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/inventory.html"),
                "Login failed or incorrect URL. Actual: " + currentUrl);

        homePage = new homePage(driver);

        homePage.clickAddToCartBtn();
        homePage.clickCartLnk();

        String cartUrl = driver.getCurrentUrl();
        Assert.assertTrue(cartUrl.contains("/cart.html"),
                "Navigation to cart failed. Actual: " + cartUrl);

        String cartItemText = homePage.getCartItemText();
        Assert.assertTrue(cartItemText.contains("Sauce Labs Backpack"),
                "The item was not added to the cart. Actual cart item: " + cartItemText);
    }

    @Test
    public void removeItemFromCart() {
        homePage.clickHomeLink();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));

        homePage.clickRemoveBtn();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));

        homePage.clickCartLnk();


        Assert.assertTrue(homePage.isCartEmpty(), "Item was not removed from the cart.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
