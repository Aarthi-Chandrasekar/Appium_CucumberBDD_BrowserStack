package com.mycompany.iosPages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ProductsPage {

    private final WebDriverWait wait;

    IOSDriver driver ;

    @iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='PRODUCTS']")
    private WebElement title;

    @iOSXCUITFindBy(xpath="//XCUIElementTypeOther[contains(@name, 'ADD TO CART')]")
    private List<WebElement> cartitems;


    public ProductsPage(IOSDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
    }

    public String getTitle() throws InterruptedException {
        title.click();
        Thread.sleep(2000); // Wait for 2 seconds
        return title.getText();
    }

    public void addItemToCartByIndex(int index)
    {
        cartitems.get(index).click();

    }

}
