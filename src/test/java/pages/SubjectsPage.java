package pages;

import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import utilities.ParentClass;

import static pageModels.Buttons.Save;

public class SubjectsPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.Subjects);
    }

    public void add(String newSubject, String newSubjectCode, int catOption, int styleOption){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormNameInput, newSubject);
        sendKeysTo(DialogFormCodeInput, newSubjectCode);
        selectOption(DialogFormSubjectCategorySelect, catOption);
        selectOption(DialogFormStyleSelect, styleOption);
        clickToDialogButton(Save);
    }

    public void add(String newSubject, String newSubjectCode, String catOption, int styleOption){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormNameInput, newSubject);
        sendKeysTo(DialogFormCodeInput, newSubjectCode);
        selectOption(DialogFormSubjectCategorySelect, catOption);
        selectOption(DialogFormStyleSelect, styleOption);
        clickToDialogButton(Save);
    }

    public void update(String oldSubject, String newSubject, String newSubjectCode,
                          int catOption, int styleOption){
        editTheTableData(oldSubject);
        if (newSubject.length()>0){
            clear(DialogFormNameInput);
            sendKeysTo(DialogFormNameInput, newSubject);
        }
        if (newSubjectCode.length()>0) {
            clear(DialogFormCodeInput);
            sendKeysTo(DialogFormCodeInput, newSubjectCode);
        }
        if (catOption>0) selectOption(DialogFormSubjectCategorySelect, catOption);
        if (styleOption>0) selectOption(DialogFormStyleSelect, styleOption);
        clickToDialogButton(Save);
    }

    public void delete(String country){
        deleteTheTableData(country);
    }

}
