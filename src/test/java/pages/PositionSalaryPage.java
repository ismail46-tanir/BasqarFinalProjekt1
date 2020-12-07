package pages;

import org.openqa.selenium.By;
import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import pageModels.PageObjects;
import utilities.ParentClass;

import static pageModels.Buttons.Save;

public class PositionSalaryPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.PositionSalary);
    }

    public void add(String newPositionSalary){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormNameInputInPosSal, newPositionSalary);
        clickToDialogButton(Save);
    }

    public void update(String oldPositionSalary, String newPositionSalary){
        editTheTableData(oldPositionSalary);
        if (newPositionSalary.length()>0) {
            clear(DialogFormNameInputInPosSal);
            sendKeysTo(DialogFormNameInputInPosSal, newPositionSalary);
        }
        clickToDialogButton(Save);
    }

    public void delete(String positionSalary){
        deleteTheTableData(positionSalary);
    }

    public void addPositionSalaryTo(String experience, String from, String salary, String currency){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormName2Input, experience);
        clickTo(DialogFormFromDate);
        setCalenderDate(from);
        sendKeysTo(DialogFormSalary, salary+"");
        selectOption(PageFormCurrencySelect, currency);
        clickTo(DialogButtonAddWithText);
        clickTo(DialogContainerButtonSave);
    }

    public void updatePositionSalaryTo(String experience, String from, String salary, String currency){
        editTheTableData(experience);
        clickTo(TableButtonEditUnique);
        clear(DialogFormName2Input);
        clickTo(DialogFormFromDate);
        setCalenderDate(from);
        clear(DialogFormSalary);
        sendKeysTo(DialogFormSalary, salary+"");
        selectOption(PageFormCurrencySelect, currency);
        clickTo(DialogContainerButtonSave);
    }

    public void clickToAddPositionSalary(String name){
        clickToEditDeleteButtonInTable(PageFormButtonAddPositionSalary, name);
    }

}
