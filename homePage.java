package saueclab.saucelab.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class homePage {

    private WebDriver driver;

    public homePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddToCartBtn() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    public void clickRemoveBtn() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    public void clickCartLnk() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public void clickHomeLink() {
        driver.findElement(By.className("app_logo")).click();
    }

    // Updated method to avoid NoSuchElementException
    public String getCartItemText() {
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='cart_item']//div[@class='inventory_item_name']"));
        if (items.isEmpty()) {
            return "";
        } else {
            return items.get(0).getText();
        }
    }

    // New helper method for cart check
    public boolean isCartEmpty() {
        return driver.findElements(By.className("cart_item")).isEmpty();
    }
}
