package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Driver {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    public static ThreadLocal<String> browsers = new ThreadLocal<>();

    /**
     * this method returns the threadlocal webDriver
     * @return WebDriver
     */
    public static WebDriver getDriver(){

        if (browsers.get() == null){
            browsers.set("chrome");
        }

        if (drivers.get() == null){
            switch (browsers.get()){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver());
                    break;
                case "ie":
                case "internet explorer":
                    WebDriverManager.iedriver().setup();
                    drivers.set(new InternetExplorerDriver());
                    break;
                case "edge":
                case "msedge":
                    WebDriverManager.edgedriver().setup();
                    drivers.set(new EdgeDriver());
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    drivers.set(new OperaDriver());
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
            }

        }
        return drivers.get();
    }

    /**
     * quit the threadLocal WebDriver
     */
    public static void quitDriver(){
        if (drivers.get() != null){
            drivers.get().quit();
            drivers.set(null);
        }
    }
}
