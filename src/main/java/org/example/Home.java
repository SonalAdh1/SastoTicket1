package org.example;


import org.openqa.selenium.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class Home {

    utility utility = new utility();
    final WebDriver driver = utility.Webdriver("chrome");

    @BeforeClass
    public void setup() {
        driver.get("https://phptravels.org/");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    void viewInstallation() {
        driver.findElement(By.id("Primary_Navbar-Store")).click();
        driver.findElement(By.id("Primary_Navbar-Store-Installation")).click();
        utility.loginfo("inside the installation page");
    }

    @Test(priority = 2)
    void order() {
        driver.findElement(By.id("product1-order-button")).click();
        driver.navigate().back();
        driver.findElement(By.id("product3-order-button")).click();
        driver.navigate().back();
        driver.findElement(By.id("product3-order-button")).click();
        utility.loginfo("Products added to the cart");
    }

    @Test(priority = 3)
    public void removeItems() {
        WebElement form = driver.findElement(By.xpath("//form[@action='/cart.php?a=view']"));
        List<WebElement> removeItem = form.findElements(By.xpath("//div[@class='col-sm-1 hidden-xs d-none d-sm-block']//button[@class='btn btn-link btn-xs btn-remove-from-cart']//i[@class='fas fa-times']"));
        if (!removeItem.isEmpty()) {
            removeItem.get(0).click();
        } else {
            System.out.println("No items to remove.");
        }

        WebElement popup = driver.findElement(By.xpath("//div[@class='modal-dialog']"));
        utility.waitforvisibility(popup);

        boolean disp = popup.isDisplayed();
        if (disp == true) {
            try {
                driver.findElement(By.xpath("//div[@class='modal-content']//div[@class='modal-footer justify-content-center']//button[@class='btn btn-primary']")).click();
                utility.loginfo("1 Item removed from cart");
            } catch (Exception e) {
                utility.logerror("Item is not removed");
            }

        } else {
            System.out.println("Popup is not displayed");
        }
    }

    @Test(priority = 5)
    public void orderSummary() {
        WebElement form = driver.findElement(By.xpath("//form[@action='/cart.php?a=view']"));
        List<WebElement> formItems = form.findElements(By.xpath("//div[@class='item']//div[@class='row']//div[@class='col-sm-7']//span[@class='item-title']"));
        List<WebElement> formPrice = form.findElements(By.xpath("//div[@class='item']//div[@class='row']//div[@class='col-sm-4 item-price']"));

        if (formItems.size() == formPrice.size()) {
            for (int i = 0; i < formItems.size(); i++) {
                String itemText = formItems.get(i).getText();
                String itemPrice = formPrice.get(i).getText();
                System.out.println("\nOrdered Item: " + itemText + ", Price: " + itemPrice);
            }
        } else {
            System.err.println("Number of items and prices do not match.");
        }

    }

    @Test(priority = 4)
    public void promoCode() {
        driver.findElement(By.id("inputPromotionCode")).sendKeys("promo");
        WebElement validateButton = driver.findElement(By.xpath("//form[@action='cart.php?a=view']//button[@value='Validate Code']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", validateButton);
        validateButton.click();
        String promoCodeErr = driver.findElement(By.xpath("//div[@class='secondary-cart-body']//div[@class='alert alert-warning text-center']")).getText();
        utility.loginfo(promoCodeErr);
    }


    @Test(priority = 6)
    public void checkout() {
        driver.findElement(By.id("checkout")).click();
    }
}
