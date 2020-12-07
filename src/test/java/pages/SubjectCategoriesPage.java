package pages;

import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import utilities.ParentClass;

import static pageModels.Buttons.Save;

public class SubjectCategoriesPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.SubjectCategories);
    }

    public void add(String newSubjectCategory, String newSubjectCategoryCode){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormNameInput, newSubjectCategory);
        sendKeysTo(DialogFormCodeInput, newSubjectCategoryCode);
        clickToDialogButton(Save);
    }

    public void update(String oldSubjectCategory, String newSubjectCategory, String newSubjectCategoryCode){
        editTheTableData(oldSubjectCategory);
        if (newSubjectCategory.length()>0){
            clear(DialogFormNameInput);
            sendKeysTo(DialogFormNameInput, newSubjectCategory);
        }
        if (newSubjectCategoryCode.length()>0) {
            clear(DialogFormCodeInput);
            sendKeysTo(DialogFormCodeInput, newSubjectCategoryCode);
        }
        clickToDialogButton(Save);
    }

    public void delete(String subjectCategory){
        deleteTheTableData(subjectCategory);
    }

}
