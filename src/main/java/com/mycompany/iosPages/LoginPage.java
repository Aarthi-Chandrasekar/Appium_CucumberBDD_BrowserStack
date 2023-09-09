package com.mycompany.iosPages;


import com.mycompany.Environment;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

    private final WebDriverWait wait;

    IOSDriver driver ;

    @iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name='Username']")
    private WebElement username;

    @iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name='Password']")
    private WebElement password;
//
    @iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name='LOGIN']")
    private WebElement LOGIN_BUTTON;


    public LoginPage(IOSDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }




    public void setUser(String user) throws InterruptedException {
        username.click();
        Thread.sleep(2000); // Wait for 2 seconds
        username.sendKeys(user);

    }

    public void setPASSWORD(String pass) throws InterruptedException {
        password.click();
        Thread.sleep(2000); // Wait for 2 seconds
        password.sendKeys(pass);
    }

    public ProductsPage clickLogin() throws InterruptedException {
        LOGIN_BUTTON.click();
        return new ProductsPage(driver);
    }



    public void quit(){
        driver.quit();
    }


}

