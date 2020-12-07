package pages;

import pageModels.NavBarObjects;
import pageModels.NotificationResults;
import utilities.ParentClass;

import java.util.Map;

public class BudgetAccountsPage extends ParentClass {

    public void getPage(){
        clickOnMenuTo(NavBarObjects.Dashboard);
        clickOnMenuTo(NavBarObjects.BudgetAccounts);
    }

    public void add(Map<String, String> map){
        String name = map.get("name");
        String code = map.get("code");
        String category = map.get("category");
        String type = map.get("type");
        String parentAccountCode = map.get("parentAccountCode");
        String closingAccountCode = map.get("closingAccountCode");
        String balanceType = map.get("balanceType");
        String integrationCodes = map.get("integrationCodes");
        String currency = map.get("currency");

        clickTo(PageButtonAdd);
        sendKeysTo(DialogFormCodeInput, code);
        sendKeysTo(DialogFormNameInput, name);
        selectOption(PageFormCategorySelect, Integer.parseInt(category));
        selectOption(PageFormTypeSelect, type);
        switch (type.toLowerCase()){
            case "detail":
                sendKeysTo(PageFormParentClosingAccountCode, closingAccountCode);
                selectOption(3);
            case "auxiliary":
                sendKeysTo(PageFormParentAccountCode, parentAccountCode);
                selectOption(0);
        }

        selectOption(PageFormBalanceTypeSelect, balanceType);

        /*
        clickTo(PageFormIntegrationCodeToAdd);
        clickTo(DialogueFormIntegrationCodeToAdd);
        clickTo(DialogueFormIntegrationCodeInputLast);
        sendKeysTo(DialogueFormIntegrationCodeInputLast, integrationCodes);
        clickTo(DialogButtonApplyWithText);
         */

        selectOption(PageFormCurrencySelect, currency);

        clickTo(PageButtonSaveWithText);
    }

    public void delete(String name, int category){
        getPage();
        selectOption(PageFormCategorySelectInBudgetAccount, category);
        searchInTextField(PageFormNameInputInBudgetAccount, name);
        deleteTheTableData(name);
    }

}
