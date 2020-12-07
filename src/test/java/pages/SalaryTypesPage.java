package pages;

import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import utilities.ParentClass;

import static pageModels.Buttons.Save;

public class SalaryTypesPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.SalaryTypes);
    }

    public void add(String newSalaryType, String...userType){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormNameInput, newSalaryType);
        selectOptionMulti(DialogFormUserTypeMultiSelect, userType);
        clickToDialogButton(Save);
    }

    public void add(String newSalaryType, int...userType){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormNameInput, newSalaryType);
        selectOptionMulti(DialogFormUserTypeMultiSelect, userType);
        clickToDialogButton(Save);
    }

    public void update(String oldName, String newName, String...userType){
        editTheTableData(oldName);
        if (newName.length()>0) {
            clear(DialogFormNameInput);
            sendKeysTo(DialogFormNameInput, newName);
        }
        if (userType.length>0) {
            deleteMultiSelectOptions(MultiSelectOptionDelete);
            selectOptionMulti(DialogFormUserTypeMultiSelect, userType);
        }
        clickToDialogButton(Save);
    }

    public void update(String oldName, String newName, int...userType){
        editTheTableData(oldName);
        if (newName.length()>0) {
            clear(DialogFormNameInput);
            sendKeysTo(DialogFormNameInput, newName);
        }
        if (userType.length>0) {
            deleteMultiSelectOptions(MultiSelectOptionDelete);
            selectOptionMulti(DialogFormUserTypeMultiSelect, userType);
        }
        clickToDialogButton(Save);
    }

    public void delete(String salaryType){
        deleteTheTableData(salaryType);
    }

}
