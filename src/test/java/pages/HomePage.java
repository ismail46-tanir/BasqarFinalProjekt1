package pages;

import org.openqa.selenium.NoSuchElementException;
import pageModels.BrowserSize;
import pageModels.NavBarObjects;
import utilities.ParentClass;

public class HomePage extends ParentClass {

    public void getHomePage(){
        setBrowserSizeTo(BrowserSize.max);
        String url = "https://test.basqar.techno.study/";

        if (!driver.getCurrentUrl().contains(url))
            driver.get(url);
    }

    public void prepareLogin(){
        if (!isLoggedIn()) {
            if (!isExists(LoginButton)) {
                clickTo(UserButton);
            }
        }
        if (isEnabled(CookieDialogBox)) {
            clickTo(CookieDialogButton);
        }
    }

    /**
     * this method login to the site
     */
    public void login(){
        if (!isLoggedIn()) {
            String username = "daulet2030@gmail.com";
            String password = "TechnoStudy123@";

            clickTo(LoginButton);
            sendKeysTo(LoginPageUserName, username);
            sendKeysTo(LoginPagePassword, password);
            clickTo(LoginPageSubmitButton);
        }
        setImplicitlyWaitTimeoutTo(10);
    }

    /**
     * this method login to the site
     * @param username username
     * @param password  password
     */
    public void login(String username, String password){
        if (!isLoggedIn()) {
            clickTo(LoginButton);
            sendKeysTo(LoginPageUserName, username);
            sendKeysTo(LoginPagePassword, password);
            clickTo(LoginPageSubmitButton);
        }
        setImplicitlyWaitTimeoutTo(10);
    }

    public void clearLoginFields(){
        clear(LoginPageUserName);
        clear(LoginPagePassword);
    }

    public void logout(){
        openMenu();
        if (isExists(UserMenuVisible)){
            clickTo(UserMenuVisible);
        }else{
            clickOnMenuTo(NavBarObjects.Dashboard);
            openMenu();
            clickTo(UserMenuHidden);
        }
        clickTo(Logout);
    }

    public boolean isLoggedIn(){
        setImplicitlyWaitTimeoutTo(2);
        try {
            driver.findElement(UserMessageBell);
            setImplicitlyWaitTimeoutTo(10);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
