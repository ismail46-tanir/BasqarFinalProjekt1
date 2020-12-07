package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageModels.Buttons;
import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import pages.CountryPage;
import utilities.MyUtils;


public class CountrySteps extends MyUtils {
    CountryPage countryPage = new CountryPage();

    @Given("^user on countries page$")
    public void userOnCountriesPage() {
        if (!countryPage.userOnSameThePage(NavBarObjects.Countries)) {
            countryPage.getPage();
        }
    }

    @And("^user navigate to countries page$")
    public void userNavigateToCountriesPage() {
        countryPage.getPage();
    }

    @When("^user create a country name as \"([^\"]*)\" and code as \"([^\"]*)\"$")
    public void userCreateACountryNameAsAndCodeAs(String country, String code) {
        countryPage.add(country, code);
    }

    @Then("^country should be created$")
    public void countryShouldBeCreated() {
        countryPage.justifyNotificationAs(NotificationResults.created);
    }

    @When("^user edit the country named \"([^\"]*)\"$")
    public void userEditThe(String country) {
        countryPage.editTheTableData(country);
    }

    @And("^click to \"([^\"]*)\" button on dialog$")
    public void clickToButtonOnDialog(String buttonString)  {
        Buttons button = countryPage.getButtonFromText(buttonString);
        countryPage.clickToDialogButton(button);
    }

    @When("^user delete the country named as \"([^\"]*)\"$")
    public void userDeleteTheCountryNameAs(String country) {
        countryPage.delete(country);
    }

    @Then("^country should be deleted$")
    public void countryShouldBeDeleted() {
        countryPage.justifyNotificationAs(NotificationResults.deleted);
    }

    @When("^user update the country named \"([^\"]*)\" to name as \"([^\"]*)\" and code as \"([^\"]*)\"$")
    public void userUpdateCountryNewAsNameAndNewCode(String oldCountry, String newCountry, String newCode)  {
        countryPage.update(oldCountry, newCountry, newCode);
    }

    @Then("^country should be updated$")
    public void countryShouldBeUpdated() {
        countryPage.justifyNotificationAs(NotificationResults.updated);

    }

}
