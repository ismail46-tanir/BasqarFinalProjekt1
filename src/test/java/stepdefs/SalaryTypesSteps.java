package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import pages.SalaryTypesPage;

public class SalaryTypesSteps {

    SalaryTypesPage st = new SalaryTypesPage();

    @Given("^user on salary types page$")
    public void userOnCountriesPage() {
        if (!st.userOnSameThePage(NavBarObjects.SalaryTypes)) {
            st.getPage();
        }
    }

    @And("^user navigate to salary types page$")
    public void userNavigateToSalaryTapesPage() {
        st.getPage();
    }

    @When("^user create a salary type name as \"([^\"]*)\" and user type as \"([^\"]*)\"$")
    public void userCreateASalaryTypeNameAsAndUserTypeAs(String name, String userType)  {
        if (st.isInteger(userType))
            st.add(name, Integer.parseInt(userType));
        else
            st.add(name, userType);
    }

    @Then("^salary type should be created$")
    public void salaryTypeShouldBeCreated() {
        st.notificationResult(NotificationResults.created);
    }

    @When("^user update the salary type named \"([^\"]*)\" to name as \"([^\"]*)\" and user type as \"([^\"]*)\"$")
    public void userUpdateTheSalaryTypeNamedToNameAsAndUserTypeAs(String oldName,
                                                                  String newName,
                                                                  String newUserType)  {
        if (st.isInteger(newUserType))
            st.update(oldName, newName, Integer.parseInt(newUserType));
        else
            st.update(oldName, newName, newUserType);
    }

    @Then("^salary type should be updated$")
    public void salaryTypeShouldBeUpdated() {
        st.notificationResult(NotificationResults.updated);
    }

    @When("^user edit the salary type named \"([^\"]*)\"$")
    public void userEditTheSalaryTypeNamed(String name) {
        st.editTheTableData(name);
    }

    @When("^user delete the salary type named as \"([^\"]*)\"$")
    public void userDeleteTheSalaryTypeNamedAs(String name) {
        st.delete(name);
    }

    @Then("^salary type should be deleted$")
    public void salaryTypeShouldBeDeleted() {
        st.notificationResult(NotificationResults.deleted);
    }
}
