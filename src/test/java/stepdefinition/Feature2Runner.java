package stepdefinition;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Execution(ExecutionMode.CONCURRENT)
@CucumberOptions(monochrome = true, strict = true, plugin = {"pretty", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"}, features = "src/test/java/features", glue = "stepdefinition",
//Enable Below tag  to run sample native app automation in android device
//tags = { "@Android-NativeApp"})
// Enable Below tag to run mobile automation of android device in Chrome browser.
       tags = {"@Android-Chrome"})
//Enable Below tag  to run sample native app automation in ios device
//tags = { "@iOS-NativeApp" })
// Enable Below tag to run mobile automation of IOS device in Safari browser.
 //tags = {"@iOS_Safari"})
public class Feature2Runner {

}