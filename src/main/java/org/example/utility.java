package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.System.exit;

public class utility {
    public static WebDriver driver;
    public static WebDriver Webdriver(String browserName){
        switch (browserName.toLowerCase()){
            case "chrome": driver = new ChromeDriver();
                break;
            case "edge": driver = new EdgeDriver();
                break;
            case "firefox": driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser name"+browserName);
                exit(1);

        }
        return driver;
    }
    static Logger log;

    public void waitforclick(WebElement path){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.valueOf(path))));

    }
    public void waitforvisibility(WebElement path){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(path));
    }

    public void loginfo(String infos) {
        log = LogManager.getLogger(Main.class);
        log.info(infos);
    }
    public void logerror(String error) {
        log = LogManager.getLogger(Main.class);
        log.info(error);
    }


}
