package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import pages.SubjectCategoriesPage;
import pages.SubjectsPage;

import java.util.Map;

public class SubjectCategoriesSteps {

    SubjectCategoriesPage subCatPage = new SubjectCategoriesPage();

    @Given("^user on subject categories page$")
    public void userOnCountriesPage() {
        if (!subCatPage.userOnSameThePage(NavBarObjects.SubjectCategories)) {
            subCatPage.getPage();
        }
    }

    @And("^user navigate to subject categories page$")
    public void userNavigateToSubjectCategoriesPage() {
        subCatPage.getPage();
    }

    @When("^user create a subject category name as \"([^\"]*)\" and code as \"([^\"]*)\"$")
    public void userCreateASubjetCategoryAsAndCodeAs(String subjectCategory, String code) {
        subCatPage.add(subjectCategory, code);
    }

    @Then("^subject category should be created$")
    public void subjetCategoryShouldBeCreated() {
        subCatPage.justifyNotificationAs(NotificationResults.created);
    }

    @When("^user delete the subject category name as \"([^\"]*)\"$")
    public void userDeleteTheSubjectCategoryNameAs(String name) {
        subCatPage.delete(name);
    }

    @Then("^subject category should be deleted$")
    public void subjectCategoryShouldBeDeleted() {
        subCatPage.justifyNotificationAs(NotificationResults.deleted);
    }

    @When("^user update the subject category named \"([^\"]*)\" to name as \"([^\"]*)\" and code as \"([^\"]*)\"$")
    public void userUpdateTheSubjectCategoryNamedToNameAsAndCodeAs(String oldName, String newName, String newCode) {
        subCatPage.update(oldName, newName, newCode);
    }

    @Then("^subject category should be updated$")
    public void subjectCategoryShouldBeUpdated() {
        subCatPage.justifyNotificationAs(NotificationResults.updated);
    }

    @Then("^subject category should not be deleted$")
    public void subjectCategoryShouldNotBeDeleted() {
        subCatPage.notificationResult(NotificationResults.Error);
    }
}
