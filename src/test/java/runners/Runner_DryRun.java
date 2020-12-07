package runners;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;
import utilities.Driver;


@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"stepdefs"},
        dryRun = true
)

public class Runner_DryRun extends AbstractTestNGCucumberTests {


    @AfterTest
    public void tearDown(){
        Driver.quitDriver();
    }


}
