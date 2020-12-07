
package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageModels.*;
import pageModels.HomePageObjects;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static pageModels.Buttons.Close;
import static pageModels.Buttons.Yes;


public class ParentClass implements HomePageObjects, PageObjects, DialogObjects {

    protected WebDriver driver;
    protected EventFiringWebDriver driver1;
    protected WebDriverWait wait;

    /**
     * class Constructor
     */
    public ParentClass(){
        driver = Driver.getDriver();
        //TestNGListener listen = new TestNGListener();
        //driver = new EventFiringWebDriver(driver1);
        //driver.register(listen);

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        setImplicitlyWaitTimeoutTo(10);
    }

    public void setImplicitlyWaitTimeoutTo(int timeSeconds){
        driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);
    }

    public void setBrowserSizeTo(BrowserSize size){
        switch (size){
            case big:
                driver.manage().window().setPosition(new Point(0, 0));
                driver.manage().window().setSize(new Dimension(1350, 730));
                break;
            case half:
                driver.manage().window().setPosition(new Point(0, 0));
                driver.manage().window().setSize(new Dimension(700, 730));
                break;
            default:
                driver.manage().window().maximize();
                break;
        }
    }

    public void openMenu(){
        if (isHidden(NavMenu)) {
            clickTo(NavMenuToggleButton);
            if (!isHidden(ToggleSideBarFolded))
                clickTo(ToggleSideBarFolded);
            else if (!isHidden(NavbarToggleButton))
                clickTo(NavbarToggleButton);

            waitToBeEnableFor(NavMenu);
        }
    }

    /**
     * if user on the same page
     * @param page NavBarObjects
     * @return boolean
     */
    public boolean userOnSameThePage(NavBarObjects page){
        return driver.findElements(By.xpath("//toolbar//*[contains(text(),'" + page.getHeader() + "')]")).size()>0;
    }


    /**
     * this method clicks to the links on left menu
     * @param page  takes varargs from PageModelNavBar enum
     */
    public void clickOnMenuTo(NavBarObjects page){
        openMenu();

        String[] linkTexts = page.getLinks();

        String strXpath = "//fuse-nav-vertical-group/div/fuse-nav-vertical-collapsable";
        for (int i = 0; i < linkTexts.length; i++) {
            if (linkTexts[i].equalsIgnoreCase("dashboard"))
                strXpath = "(//span[contains(text(),'" + linkTexts[i] + "')])[1]";
            else
                strXpath += "/a/span[contains(text(),'" + linkTexts[i] + "')]";
            By by = By.xpath(strXpath);
            clickTo(by);
            strXpath += "//ancestor::fuse-nav-vertical-collapsable/";
        }
        waitToBeExistFor(By.xpath("//toolbar//*[contains(text(),'" + page.getHeader() + "')]"));
    }

    /**
     * this method clicks to the links on left menu
     * @param page  takes varargs from PageModelNavBar enum
     */
    public void clickOnMenuTo1(NavBarObjects page){

        openMenu();

        String[] linkTexts = page.getLinks();

        String strXpath = "";
        for (int i=0; i<linkTexts.length; i++) {
            if (i==0)
                strXpath += "(//span[contains(text(),'" + linkTexts[i] + "')])[1]";
            else
                strXpath += "//span[contains(text(),'" + linkTexts[i] + "')][1]";
            By by = By.xpath(strXpath);
            clickTo(by);
            strXpath += "//ancestor::fuse-nav-vertical-collapsable";
        }
        waitToBeExistFor(By.xpath("//toolbar//*[contains(text(),'" + page.getHeader() + "')]"));
    }

    public WebElement getElement(By by){
        return driver.findElement(by);
    }

    public List<WebElement> getElements(By by){
        return driver.findElements(by);
    }



    public void clickTo(By by){
        waitToBeEnableFor(by);
        scrollTo(by);
        driver.findElement(by).click();
    }

    public void clear(By by){
        waitToBeEnableFor(by);
        scrollTo(by);
        driver.findElement(by).clear();
    }

    public void sendKeysTo(By by, String textToSend){
        waitToBeEnableFor(by);
        driver.findElement(by).sendKeys(textToSend);
    }

    public boolean isLoggedIn(By by){
        try {
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.MICROSECONDS);
            driver.findElement(by);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isExists(By by){
        return driver.findElements(by).size()>0;
    }


    public boolean isVisible(By by){
        if (!isExists(by)) return false;
        return driver.findElement(by).isDisplayed();
    }

    public boolean isInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (Exception ignored){
            return false;
        }

    }

    public int getIntVal(String strNum){
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return Integer.MIN_VALUE;
        }
    }

    public boolean isEnabled(By by){
        if (!isVisible(by)) return false;
        return driver.findElement(by).isEnabled();
    }

    public boolean isHidden(By cssSelector){
        String script = "return arguments[0].getAttribute(\"class\");";
        //waitToBeVisibleFor(cssSelector);
        String ret = ((JavascriptExecutor) driver)
                .executeScript(script, driver.findElement(cssSelector)).toString();
        return !(ret.contains("unfolded") || ret.contains("open"));
    }

    public void waitToBeExistFor(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitToBeVisibleFor(By by){
        waitToBeExistFor(by);
        scrollTo(by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitToBeEnableFor(By by){
        waitToBeVisibleFor(by);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }



    public boolean notificationResult(NotificationResults result){
        By popupContainerWithText = By.xpath("//div[@id='toast-container']//*[contains(text(), '" + result.toString() + "')]");
        boolean conc;
        waitToBeExistFor(popupContainerWithText);
        conc = getText(popupContainerWithText).toLowerCase().contains(result.toString().toLowerCase());
        return conc;
    }

    public void justifyNotificationAs(NotificationResults result){
        Assert.assertTrue(notificationResult(result));
    }

    public void scrollTo(WebElement element){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollTo(By by){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

    public void scrollTo(By by, Boolean bool){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(" + bool + ");", driver.findElement(by));
    }

    public String getText(By by){
        waitToBeVisibleFor(by);
        return driver.findElement(by).getText();
    }


    /**
     * click to button in justification dialog
     * @param button, button from Buttons
     */
    public void clickToDialogButton(Buttons button){
        switch (button){
            case Save:
                clickTo(DialogContainerButtonSave);
                break;
            case Yes:
                clickTo(DialogContainerButtonYes);
                break;
            case No:
                clickTo(DialogContainerButtonNo);
                break;
            case Close:
                clickTo(DialogContainerButtonClose);
                break;
            case Apply:
                clickTo(DialogButtonApplyWithText);
        }
    }

    /**
     * search a text in given input field
     * @param by the locator of input field
     * @param textToSearch text to searc
     */
    public void searchInTextField(By by, String textToSearch){
        clickTo(by);
        clear(by);
        sendKeysTo(by, textToSearch);
        clickTo(PageButtonSearchWithText);
    }


    /**
     * find the table row by given text
     * @param textToBeSearched text to be found
     * @return selenide element
     */
    public WebElement getTableRowWithText(String textToBeSearched){
        //By byTr = By.xpath("//*[contains(text(),'" + textToBeSearched + "')]//parent::tr");
        By byTr = By.xpath("//*[contains(text(),'" + textToBeSearched + "')]//ancestor::tr");
        waitToBeExistFor(byTr);
        return driver.findElement(byTr);
    }

    public void clickToEditDeleteButtonInTable(By by, String textToBeSearched){
        WebElement row = getTableRowWithText(textToBeSearched);
        WebElement button;
        if (!isEnabled(TableButtonDialogEditDeleteButton)) {
            row.findElement(TableButtonDialogEditDeleteButton).click();
            button = driver.findElement(TableButtonDialogEditDeleteDialog).findElement(by);
        }else {
            button = row.findElement(by);
        }
        button.click();
    }

    /**
     * delete the table row data
     * @param textToBeSearched the required text in the row
     */
    public void deleteTheTableData(String textToBeSearched){
        clickToEditDeleteButtonInTable(TableButtonDelete, textToBeSearched);
        clickToDialogButton(Yes);
    }


    /**
     * edit the table row data
     * @param textToBeSearched the required text in the row
     */
    public void editTheTableData(String textToBeSearched){
        clickToEditDeleteButtonInTable(TableButtonEdit, textToBeSearched);
        waitToBeEnableFor(DialogContainer);
    }

    public void clickPositionSalaryInTable(String textToBeSearched){
        clickToEditDeleteButtonInTable(PageFormButtonAddPositionSalary, textToBeSearched);
        waitToBeEnableFor(DialogContainer);
    }

    /*
    public void editTableData(String textToBeSearched){
        clickToEditDeleteButtonInTable(TableButtonEdit, textToBeSearched);
    }
    */

    public Buttons getButtonFromText(String buttonText){
        if (buttonText.toLowerCase().contains("save"))
            return Buttons.Save;
        else if (buttonText.toLowerCase().contains("yes"))
            return Buttons.Yes;
        else if (buttonText.toLowerCase().contains("no"))
            return Buttons.No;
        else
            return Close;
    }

    /**
     * delete the table row data
     * @param textToBeSearched the required text in the row
     */
    public boolean isDataExistOnList(String textToBeSearched){
        return getTableRowWithText(textToBeSearched).isEnabled();
    }


    /**
     *
     *  caleander methods
     *
     */
    //////////////////////////////////////////////////////////////////////////
    public static String formatDay(String dayStr){
        if (dayStr.length()==1)
            return "0" + dayStr;
        else
            return dayStr;
    }

    public void sendKeysToCalander(LocalDate date, By dateField){
        String dataToUSFormat = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        sendKeysTo(dateField, dataToUSFormat);
        if (isExists(Calender)){
            clickTo(OverlayContainer);
        }
    }

    public LocalDate stringToDate(String isoDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(isoDate, formatter);
    }

    public String[] getYearMonthDayAsArray(String dateStr){
        String[] yearMonthDay = dateStr.split("-");
        LocalDate date = stringToDate(dateStr);
        yearMonthDay[1] = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase();
        return yearMonthDay;
    }

    public int compareMonths(String month, String monthCalender){
        LinkedList<String> months =
                new LinkedList<>(Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"));
        int indexOfMonth = months.indexOf(month);
        int indexOfMonthCalender = months.indexOf(monthCalender);
        if (indexOfMonth > indexOfMonthCalender) return 1;
        else if (indexOfMonth < indexOfMonthCalender) return -1;
        else return 0;
    }

    public int compareMonthYear(String monthYear, String monthYearCalender){

        String month = monthYear.replaceAll("[^A-Z]+","");
        int year = Integer.parseInt(monthYear.replaceAll("[^0-9]+",""));
        String monthCalender = monthYearCalender.replaceAll("[^A-Z]+","");
        int yearCalender = Integer.parseInt(monthYearCalender.replaceAll("[^0-9]+",""));

        if (year < yearCalender) return 1;
        if (year > yearCalender) return 4;
        if (compareMonths(month, monthCalender)==-1) return 2;
        if (compareMonths(month, monthCalender)==1) return 3;
        return 0;
    }


    public void setCalenderDate(String dateStr){
        String[] dateArray = getYearMonthDayAsArray(dateStr);
        String monthYear = dateArray[1] + " " + dateArray[0];
        String monthYearCalender;
        do{
            monthYearCalender = getText(CalanderMonthYearText).trim();
            switch (compareMonthYear(monthYear, monthYearCalender)){
                case 1: clickTo(CalanderPreviousYear); break;
                case 2: clickTo(CalanderPreviousMonth); break;
                case 3: clickTo(CalanderNextMonth); break;
                case 4: clickTo(CalanderNextYear); break;
            }
        }while (compareMonthYear(monthYear, monthYearCalender)!=0);

        String day = Integer.parseInt(dateArray[2]) + "";
        driver.findElement(CalanderMonthTable).findElement(By.xpath("//*[text()=' " + day + " ']")).click();
    }


    //////////////////////////////////////////////////////////////////////////
    /**
     * select option from dropdown by text
     * @param optionText to select
     */
    public void selectOption(By by, String optionText){
        clickTo(by);
        WebElement option = null;
        List<WebElement> list = getElements(ListOfOptions);
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(optionText)){
                option = element;
                break;
            }
        }
        scrollTo(option);
        if (option != null) option.click();
    }


    /**
     * select option from dropdown by index
     * @param optionNumber to select
     */
    public void selectOption(By by, int optionNumber){
        clickTo(by);
        List<WebElement> list = getElements(ListOfOptions);
        if (optionNumber >= list.size())
            optionNumber = list.size()-1;
        else if (optionNumber < 0)
            optionNumber = 0;

        WebElement option = getElements(ListOfOptions).get(optionNumber);
        scrollTo(option);
        option.click();
    }

    public void selectOption(String optionText){
        WebElement option = null;
        List<WebElement> list = getElements(ListOfOptions);
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(optionText)){
                option = element;
                break;
            }
        }
        scrollTo(option);
        if (option != null) option.click();
    }

    public void selectOption(int optionNumber){
        List<WebElement> list = getElements(ListOfOptions);
        if (optionNumber >= list.size())
            optionNumber = list.size()-1;
        else if (optionNumber < 0)
            optionNumber = 0;

        WebElement option = getElements(ListOfOptions).get(optionNumber);
        scrollTo(option);
        option.click();
    }

    public void deleteMultiSelectOptions(By by){
        List<WebElement> list = driver.findElements(by);
        for (WebElement element : list) {
            element.click();
        }
    }

    public void selectOptionMulti(By by, String...optionTexts){
        WebElement option = null;
        List<WebElement> list;
        for (int i=0; i<optionTexts.length; i++) {
            clickTo(by);
            list = getElements(ListOfOptions);
            for (WebElement element : list) {
                if (element.getText().equalsIgnoreCase(optionTexts[i])){
                    option = element;
                    break;
                }
            }
            if (option != null){
                scrollTo(option);
                option.click();
            }
        }
    }


    public void selectOptionMulti(By by, int...optionNumbers){
        List<WebElement> list;
        for (int i=0; i<optionNumbers.length; i++) {
            clickTo(by);
            list = getElements(ListOfOptions);
            if (optionNumbers[i] >= list.size())
                optionNumbers[i] = list.size()-1;
            else if (optionNumbers[i] < 0)
                optionNumbers[i] = 0;
            WebElement option = getElements(ListOfOptions).get(optionNumbers[i]);
            scrollTo(option);
            option.click();
        }
    }


    /**
     * takeScreenshot
     * @param fileName		fileName
     */
    public void takeScreenshot(String fileName){
        String directoryPath = "target/ExtentReport/screenshots/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"));
        String filePath = directoryPath + fileName + "_" + Thread.currentThread().getName() + "_" + dt + ".png";
        try {
            FileUtils.copyFile(file, new File(filePath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


