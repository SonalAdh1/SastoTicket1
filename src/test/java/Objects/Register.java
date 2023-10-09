package Objects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utility;


public class Register {
    WebDriver driver;
    static Logger log;
    public Utility utility;

    public Register(WebDriver driver) {
        this.driver = driver;
        this.utility = new Utility(driver);
    }

    By fname = By.id("inputFirstName");
    By lname = By.id("inputLastName");
    By email = By.xpath("//*[@id='inputEmail']");
    By code = By.xpath("//*[@class='selected-dial-code']");
    By codeNp = By.xpath("//*[@data-dial-code='977']");
    By contactNum = By.id("inputPhone");
    By company = By.id("inputCompanyName");
    By address1 = By.id("inputAddress1");
    By address2 = By.id("inputAddress2");
    By city = By.id("inputCity");
    By state = By.id("stateinput");
    By postCode = By.id("inputPostcode");
    By country = By.id("inputCountry");
    By contactnum2 = By.id("customfield2");
    By generatePass = By.xpath("//*[@data-targetfields='inputNewPassword1,inputNewPassword2']");
    By password = By.id("btnGeneratePasswordInsert");
    By switchIframe = By.xpath("//div[@id='google-recaptcha-domainchecker1']//div[@class='g-recaptcha']//div//iframe");
    By captchaCheck = By.xpath("//div[@class='recaptcha-checkbox-border']");
    By captchaClick = By.xpath("//*[@class='btn btn-large btn-primary btn-recaptcha']");
    By captchaErr = By.xpath("//div[@class='alert alert-danger']");


    public void fillRegFormPersonalInfo() {
        driver.findElement(fname).sendKeys("Sonal");
        driver.findElement(email).sendKeys("Sonal1@gmail.com");
        driver.findElement(lname).sendKeys("Adhikari");
        driver.findElement(code).click();

        WebElement codeNepal = driver.findElement(codeNp);
        codeNepal.click();
        driver.findElement(contactNum).sendKeys("9841222222");
    }

    public void fillRegFormBilling() {
        driver.findElement(company).sendKeys("Cotiviti");
        driver.findElement(address1).sendKeys("Hattisaar");
        driver.findElement(address2).sendKeys("Kathmandu");
        driver.findElement(city).sendKeys("Kathmandu");
        driver.findElement(state).sendKeys("Bagmati");
        driver.findElement(postCode).sendKeys("45125");
        driver.findElement(country).sendKeys("Nepal");

    }

    public void fillRegFormPassword() {
        driver.findElement(contactnum2).sendKeys("9841222222");
        driver.findElement(generatePass).click();
        WebElement generatepassInsert = driver.findElement(password);
        utility.waitforvisibility(generatepassInsert, "from password insert");
        generatepassInsert.click();
    }

    public void captcha() {
        try {
            // Switch to the iframe
            driver.switchTo().frame(driver.findElement(switchIframe));
            WebElement checkbox = driver.findElement(captchaCheck);
            checkbox.click();
            driver.switchTo().defaultContent();
            driver.findElement(captchaClick).click();
            String errmsg = driver.findElement(captchaErr).getText();
            utility.logerror(errmsg);
        } catch (Exception e) {
            utility.loginfo("Verification of captcha failed!");

        }
    }

}
