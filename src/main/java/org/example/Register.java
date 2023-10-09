package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Register {
    utility utility = new utility();
//    final WebDriver driver = utility.Webdriver("chrome");
    WebDriver driver;

    @Test(priority = 2)
    public void verifyLogo() {
        boolean flag = driver.findElement(By.xpath("//a[@class='navbar-brand mr-3']")).isDisplayed();
        Assert.assertTrue(flag);
    }

//    @Test(priority = 3)
    void registerForm() {
        driver.findElement(By.xpath("//*[@class='small font-weight-bold']")).click();
    }

    @Test(priority = 4)
    void fillRegFormPersonalInfo() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@class='card-body p-4']"))));
        driver.findElement(By.id("inputFirstName")).sendKeys("Sonal");
        driver.findElement(By.xpath("//*[@id='inputEmail']")).sendKeys("Sonal1@gmail.com");
        driver.findElement(By.id("inputLastName")).sendKeys("Adhikari");
        driver.findElement(By.xpath("//*[@class='selected-dial-code']")).click();
//        utility.waitUntilClickable("//*[@data-dial-code='977']");

//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-dial-code='977']")));
//        WebElement dialcode = driver.findElement(By.xpath("//*[@data-dial-code='977']"));
        WebElement dialcode = driver.findElement(By.xpath("//div[class='flag-container'//ul[class='country-list']//li[data-dial-code='977']]"));
        utility.waitforclick(dialcode);

        driver.findElement(By.xpath("//*[@data-dial-code='977']")).click();
        driver.findElement(By.id("inputPhone")).sendKeys("9841222222");
    }
//    div-class="flag-container",ul-class="country-list",li-data-dial-code="977"

    @Test(priority = 5)
    void fillRegFormBilling() {
        driver.findElement(By.id("inputCompanyName")).sendKeys("Cotiviti");
        driver.findElement(By.id("inputAddress1")).sendKeys("Hattisaar");
        driver.findElement(By.id("inputAddress2")).sendKeys("Kathmandu");
        driver.findElement(By.id("inputCity")).sendKeys("Kathmandu");
        driver.findElement(By.id("stateinput")).sendKeys("Bagmati");
        driver.findElement(By.id("inputPostcode")).sendKeys("45125");
        driver.findElement(By.id("inputCountry")).sendKeys("Nepal");

    }

    @Test(priority = 6)
    void fillRegFormPassword() {
        driver.findElement(By.id("customfield2")).sendKeys("9841222222");
        driver.findElement(By.xpath("//*[@class='btn btn-default btn-sm btn-sm-block generate-password']")).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGeneratePasswordInsert")));
        WebElement generatepass = driver.findElement(By.id("btnGeneratePasswordInsert"));
        utility.waitforclick(generatepass);

        driver.findElement(By.id("btnGeneratePasswordInsert")).click();
    }
    @Test(priority = 7)
    void captcha() {
        try {
            // Switch to the iframe
            driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='google-recaptcha-domainchecker1']//div[@class='g-recaptcha']//div//iframe")));
            WebElement checkbox = driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']"));
            checkbox.click();
            driver.switchTo().defaultContent();

            driver.findElement(By.xpath("//*[@class='btn btn-large btn-primary btn-recaptcha']")).click();
            String errmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
            utility.logerror(errmsg);

        } catch (Exception e) {
            utility.loginfo("Verification of captcha failed!");

        }
    }
}

