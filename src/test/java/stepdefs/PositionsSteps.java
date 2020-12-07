package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import pages.PositionsPage;

public class PositionsSteps {

    PositionsPage pos = new PositionsPage();

    @Given("^user on positions page$")
    public void userOnCountriesPage() {
        if (!pos.userOnSameThePage(NavBarObjects.Positions)) {
            pos.getPage();
        }
    }

    @And("^user navigate to positions page$")
    public void userNavigateToPositionsPage() {
        pos.getPage();
    }

    @When("^user create a position name as \"([^\"]*)\" and short name as \"([^\"]*)\"$")
    public void userCreateAPositionNameAsAndShortNameAs(String name, String shortName) {
        pos.add(name, shortName);
    }

    @Then("^position should be created$")
    public void positionShouldBeCreated() {
        pos.justifyNotificationAs(NotificationResults.created);
    }

    @When("^user update the position named \"([^\"]*)\" to name as \"([^\"]*)\" and code as \"([^\"]*)\"$")
    public void userUpdateThePositionNamedToNameAsAndCodeAs(String oldName, String newName, String newCode)  {
        pos.update(oldName, newName, newCode);
    }

    @Then("^position should be updated$")
    public void positionShouldBeUpdated() {
        pos.justifyNotificationAs(NotificationResults.updated);
    }

    @When("^user edit the position named \"([^\"]*)\"$")
    public void userEditThePositionNamed(String name)  {
        pos.editTheTableData(name);
    }

    @When("^user delete the position named as \"([^\"]*)\"$")
    public void userDeleteThePositionNamedAs(String name)  {
        pos.delete(name);
    }

    @Then("^position should be deleted$")
    public void positionShouldBeDeleted() {
        pos.notificationResult(NotificationResults.deleted);
    }
}
