package pages;

import pageModels.NavBarObjects;
import utilities.ParentClass;

import static pageModels.Buttons.Save;

public class BankAccountsPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.BankAccounts);
    }

    public void add(String name, String iban, String currency, String integrationCode){
        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormNameInput, name);
        sendKeysTo(DialogFormIban, iban);
        if (isInteger(currency))
            selectOption(PageFormCurrencySelect, getIntVal(currency));
        else
            selectOption(PageFormCurrencySelect, currency);

        sendKeysTo(DialogFormIntegrationCode, integrationCode);
        clickToDialogButton(Save);
    }

    public void update(String oldName, String name, String iban, String currency, String integrationCode){
        editTheTableData(oldName);
        if (name.length()>0) {
            clear(DialogFormNameInput);
            sendKeysTo(DialogFormNameInput, name);
        }
        if (iban.length()==22) {
            clear(DialogFormIban);
            sendKeysTo(DialogFormIban, iban);
        }
        if (isInteger(currency))
            selectOption(PageFormCurrencySelect, getIntVal(currency));
        else
            selectOption(PageFormCurrencySelect, currency);

        if (integrationCode.length()>0) {
            clear(DialogFormIntegrationCode);
            sendKeysTo(DialogFormIntegrationCode, integrationCode);
        }
        clickToDialogButton(Save);
    }

    public void delete(String city){
        deleteTheTableData(city);
    }

}
