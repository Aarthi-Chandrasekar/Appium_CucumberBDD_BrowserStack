package stepdefinition;

import com.mycompany.androidPages.FormPage;
import com.mycompany.androidPages.ProductCatalogue;
import com.mycompany.Environment;
import io.appium.java_client.android.AndroidDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Android_APP_Mobile_Stepdefinition {

    AndroidDriver driver;

    FormPage frmPage;

    ProductCatalogue productCatalogue;

    String sessionString;

    @Given("the app is opened in Android phone")
    public void the_app_is_opened() throws Throwable {
        this.driver = Environment.initializeAndroidDriver();
        sessionString = this.driver.getSessionId().toString();
        Environment.addSession("AndroidNativeApp",sessionString);

    }

    @When("User logs in with username {string} and country {string} and select gender {string} in Android phone")
    public void theLoginFormEntered(String username, String country, String gender) throws Throwable {
        frmPage = new FormPage(this.driver);
        frmPage.setActivity();
        frmPage.setGender(gender);
        frmPage.setCountrySelection(country);
        frmPage.setNameField(username);
        productCatalogue = frmPage.submitForm();


    }

    @Then("User is able to login successfully and lands on Products page in Android phone")
    public void user_login_successful() throws InterruptedException {
        Thread.sleep(2000);
        assert productCatalogue.getProductTitleText().contains("Products") : "Title does not contain 'Products'";
        driver.quit();
    }




}
