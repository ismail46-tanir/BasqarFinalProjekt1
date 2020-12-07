package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import pages.BudgetAccountsPage;

import java.util.Map;

public class BudgetAccountsSteps {

    BudgetAccountsPage bp = new BudgetAccountsPage();

    @Given("^user on budget accounts page$")
    public void userOnCountriesPage() {
        if (!bp.userOnSameThePage(NavBarObjects.BudgetAccounts)) {
            bp.getPage();
        }
    }

    @And("^user navigate to budget accounts page$")
    public void userNavigateToBudgetAccountsPage() {
        bp.getPage();
    }

    @When("^user create a budget account as follows$")
    public void userCreateABudgetAccountAsFollows(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        bp.add(map);
    }

    @Then("^budget account should be created$")
    public void budgetAccountShouldBeCreated() {
        bp.justifyNotificationAs(NotificationResults.created);
    }

    @When("^user delete the budget account named as \"([^\"]*)\" under category (\\d+)$")
    public void userDeleteTheBudgetAccountNamedAsUnderCategory(String name, int category) {
        bp.getPage();
        bp.delete(name, category);
    }
    @Then("^budget account should be deleted$")
    public void budgetAccountShouldBeDeleted() {
        bp.justifyNotificationAs(NotificationResults.deleted);
    }
}
