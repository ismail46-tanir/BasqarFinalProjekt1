package pages;

import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import utilities.ParentClass;

import static pageModels.Buttons.Save;

public class SalaryConstantsPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.SalaryConstants);
    }

    public void add(String name, String validFormDate, String key, int value){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormName2Input, name);
        clickTo(DialogFormValidFormInput);
        setCalenderDate(validFormDate);
        sendKeysTo(DialogFormKeyInput, key);
        sendKeysTo(DialogFormValueIntInput, value+"");
        clickToDialogButton(Save);
    }

    public void update(String oldName, String newName, String newValidFormDate, String newKey, int newValue){
        searchInTextField(PageFormNameInput2, oldName);

        editTheTableData(oldName);
        if (newName.length()>0){
            clear(DialogFormName2Input);
            sendKeysTo(DialogFormName2Input, newName);
        }
        if (newValidFormDate.length()>0) {
            clear(DialogFormValidFormInput);
            setCalenderDate(newValidFormDate);
        }
        if (newKey.length()>0) {
            clear(DialogFormKeyInput);
            sendKeysTo(DialogFormKeyInput, newKey);
        }
        sendKeysTo(DialogFormValueIntInput, newValue+"");
        clickToDialogButton(Save);
    }

    public void delete(String name){
        searchInTextField(PageFormNameInput2, name);
        deleteTheTableData(name);
    }


}
