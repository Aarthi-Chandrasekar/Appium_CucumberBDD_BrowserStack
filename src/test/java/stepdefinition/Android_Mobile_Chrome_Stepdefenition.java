package stepdefinition;

import com.mycompany.Environment;
import com.mycompany.Pages.LoginPage;
import com.mycompany.Pages.ProductsPage;
import com.mycompany.utility.VideoUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Android_Mobile_Chrome_Stepdefenition {

    LoginPage loginPage;
    ProductsPage productsPage;

    AndroidDriver driver;

    String sessionString;

    @Given("User is on the SauceDemo login page in Chrome Browser")
    public void IOSUser_is_on_the_SauceDemo_login_page() {
        this.driver = Environment.insitalizeChromeWebDriver();
        sessionString = this.driver.getSessionId().toString();
        Environment.addSession("AndroidChrome",sessionString);
        loginPage= new LoginPage(this.driver);
        loginPage.landingPage();
    }

    @When("User logs in with username {string} and password {string} in Chrome Browser")
    public void IOSUser_logs_in_with_IOSUsername_and_password(String IOSUsername, String password) {
        loginPage.setUser(IOSUsername);
        loginPage.setPASSWORD(password);
        loginPage.clickLogin();
    }

    @Then("User is able to login successfully in Chrome Browser")
    public void user_logs_in_successfully() {
        productsPage = new ProductsPage(this.driver);
        assert ("Swag Labs".equalsIgnoreCase(productsPage.getTitle()));
    }
    @When("User adds an item to the cart in Chrome Browser")
    public void IOSUser_adds_an_item_to_the_cart() {
        productsPage.clickButton(".btn_inventory");
    }

    @Then("The item is added to the cart successfully in Chrome Browser")
    public void the_item_is_added_to_the_cart_successfully() {
        assert ("Swag Labs".equalsIgnoreCase(productsPage.getTitle()));
        loginPage.quit();
    }


}
