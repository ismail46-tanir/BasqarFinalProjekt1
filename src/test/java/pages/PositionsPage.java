package pages;

import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import utilities.ParentClass;

import static pageModels.Buttons.Save;

public class PositionsPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.Positions);
    }

    public void add(String newCountryName, String newCountryCode){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormNameInput, newCountryName);
        sendKeysTo(DialogFormShortNameInput, newCountryCode);
        clickToDialogButton(Save);
    }

    public void update(String oldCountryName, String newCountryName, String newCountryCode){
        editTheTableData(oldCountryName);
        if (newCountryName.length()>0){
            clear(DialogFormNameInput);
            sendKeysTo(DialogFormNameInput, newCountryName);
        }
        if (newCountryCode.length()>0) {
            clear(DialogFormShortNameInput);
            sendKeysTo(DialogFormShortNameInput, newCountryCode);
        }
        clickToDialogButton(Save);
    }

    public void delete(String position){
        deleteTheTableData(position);
    }

}

