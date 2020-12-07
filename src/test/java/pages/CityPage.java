package pages;

import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import utilities.ParentClass;

import static pageModels.Buttons.Save;

public class CityPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.Cities);
    }

    public void add(String country, String newCity){
        clickTo(PageButtonAdd);
        selectOption(DialogFormCountrySelect, country);
        sendKeysTo(DialogFormNameInput, newCity);
        clickToDialogButton(Save);
    }

    public void add(int countryIndex, String newCity){
        clickTo(PageButtonAdd);
        selectOption(DialogFormCountrySelect, countryIndex);
        sendKeysTo(DialogFormNameInput, newCity);
        clickToDialogButton(Save);
    }

    public void update(String oldName, String newName){
        editTheTableData(oldName);
        if (newName.length()>0) {
            clear(DialogFormNameInput);
            sendKeysTo(DialogFormNameInput, newName);
        }
        clickToDialogButton(Save);
    }

    public void delete(String city){
        deleteTheTableData(city);
    }

}
