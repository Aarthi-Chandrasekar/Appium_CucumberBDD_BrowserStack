package stepdefinition;

import com.mycompany.Environment;
import com.mycompany.iosPages.LoginPage;
import com.mycompany.iosPages.ProductsPage;
import com.mycompany.utility.VideoUtility;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class IOS_Mobile_Stepdefinition {

    IOSDriver driver;

    ProductsPage homePage;
    LoginPage loginPage;

    String sessionString;

    @Given("the app is opened in iPhone")
    public void appisOpened() {
        this.driver = Environment.initializeIOSDriver();
        sessionString = this.driver.getSessionId().toString();
        Environment.addSession("IOSNativeApp",sessionString);

    }

    @When("User logs in app with username {string} and password {string} in iPhone")
    public void user_logs_in_with_username_and_password(String username, String password) {
        try {
            loginPage = new LoginPage(this.driver);
            loginPage.setUser(username);
            loginPage.setPASSWORD(password);
            homePage =loginPage.clickLogin();

        }
        catch (InterruptedException interruptedException){
            interruptedException.printStackTrace();
        }
    }

   @Then("User is able to login successfully in iPhone")
    public void user_login_successful() throws InterruptedException {
        Thread.sleep(2000);
        assert (homePage.getTitle().equalsIgnoreCase("PRODUCTS"));
        driver.quit();

    }



}
