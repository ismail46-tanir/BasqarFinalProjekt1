package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import pages.SalaryConstantsPage;

import java.util.Map;

public class SalaryConstantsSteps {

    SalaryConstantsPage sc = new SalaryConstantsPage();

    @Given("^user on salary constants page$")
    public void userOnCountriesPage() {
        if (!sc.userOnSameThePage(NavBarObjects.SalaryConstants)) {
            sc.getPage();
        }
    }

    @And("^user navigate to salary constants page$")
    public void userNavigateToSalaryConstantsPage() {
        sc.getPage();
    }

    @When("^user create a salary constant as follows$")
    public void userCreateASalaryConstantAsFollows(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        String name = map.get("name");
        String validFormDate = map.get("validFormDate");
        String key = map.get("key");
        int value = Integer.parseInt(map.get("value"));
        sc.add(name, validFormDate, key, value);
    }

    @Then("^salary constant should be created$")
    public void salaryConstantShouldBeCreated() {
        sc.notificationResult(NotificationResults.created);

    }

    @When("^user update the salary constant name as \"([^\"]*)\" as follows$")
    public void userUpdateTheSalaryConstantNameAsAsFollows(String oldName, DataTable dataTable)  {
        sc.getPage();
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        String name = map.get("name");
        String validFormDate = map.get("validFormDate");
        String key = map.get("key");
        int value = Integer.parseInt(map.get("value"));
        sc.update(oldName, name, validFormDate, key, value);
    }

    @Then("^salary constant should be updated$")
    public void salaryConstantShouldBeUpdated() {
        sc.notificationResult(NotificationResults.updated);
    }

    @When("^user delete the constant type named as \"([^\"]*)\"$")
    public void userDeleteTheConstantTypeNamedAs(String name) {
        sc.getPage();
        sc.delete(name);
    }

    @Then("^salary constant should be deleted$")
    public void salaryConstantShouldBeDeleted() {
        sc.notificationResult(NotificationResults.deleted);
    }
}
