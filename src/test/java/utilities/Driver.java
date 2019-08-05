package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static WebDriver driver;

    private Driver(){

    }

    public static WebDriver getDriver(){
        if(driver==null){
            String browser = ConfigurationReader.getProperty("browser");
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Illegal browser name!");
            }  //switch

//            if (browser.equals("chrome")){
//                    WebDriverManager.chromedriver().setup();
//                    driver = new ChromeDriver();
//            }
//            else if (browser.equals("firefox")){
//                    WebDriverManager.firefoxdriver().setup();
//                    driver = new FirefoxDriver();
//            }
//            else{
//                throw new RuntimeException("Illegal browser name!");
//            }




        }//if
        return driver;
    }//getDriver()

    //will kill driver
    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver = null;
        } //if
    } //closeDriver()
} // Driver Class

