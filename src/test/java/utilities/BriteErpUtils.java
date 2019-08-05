package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import tests.components.components.pages.login.LoginPage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BriteErpUtils {
    //====================================================================================================
//   Navigate To Modules
//----------------------------------------------------------------------------------------------------

    public static void navigateToModule(String tab) {
        String tabLocator = "//span[@class='oe_menu_text'][contains(text(),'" + tab + "')]";
        SeleniumUtils.clickWithWait(Driver.getDriver(), By.xpath(tabLocator), 5);
//      SeleniumUtils.waitPlease(2);
    }

    //====================================================================================================
//   Login - Logout
//----------------------------------------------------------------------------------------------------
    public static void login() {
        LoginPage login = new LoginPage();
        login.userNameElement.sendKeys(ConfigurationReader.getProperty("username"));
        login.passwordElement.sendKeys(ConfigurationReader.getProperty("password"));
        login.loginButtonElement.click();
    }

    public static void login(String username, String password) {
        LoginPage login = new LoginPage();
        login.userNameElement.sendKeys(username);
        login.passwordElement.sendKeys(password);
        login.loginButtonElement.click();
    }

    public static void logout() {
        LoginPage logout = new LoginPage();
        logout.ManagerLocator.click();
        logout.logOutLocator.click();
        //       SeleniumUtils.waitPlease(1);
    }

//====================================================================================================
//   Convert
//----------------------------------------------------------------------------------------------------

    public static double convertToDouble(String s) {
        s = s.replace(",", "").replace(".", "");
        double d = Integer.parseInt(s);
        return d / 100;
    }


//====================================================================================================
//   Table - Row
//----------------------------------------------------------------------------------------------------

    /**
     * Write a method that will return count of rows
     */
    public static int getCountOfRows() {
        return Driver.getDriver().findElements(By.cssSelector("tbody tr")).size();
    }

    /**
     * Write a method that will return count of rows
     * In this case, object of by allows us to specify table locator of any type
     */
    public static int getCountOfRows(By by) {
        return Driver.getDriver().findElements(by).size();
    }


//====================================================================================================
//   Table - Column
//----------------------------------------------------------------------------------------------------

    /**
     * Write a method that returns number of columns
     */
    public static int getCountOfColumns() {
        return Driver.getDriver().findElements(By.cssSelector("table td")).size();
    }

//----------------------------------------------------------------------------------------------------


    /**
     * Write a method that returns number of columns
     */
    public static int getCountOfColumns(By by) {
        return Driver.getDriver().findElements(by).size();
    }


//----------------------------------------------------------------------------------------------------

    public static List<String> getColumnData(int columnNumber) {
        String locatorForColumn = "tbody>tr>td:nth-of-type(" + columnNumber + ")";
        List<WebElement> columnCollectionOfWebElements = Driver.getDriver().findElements(By.cssSelector(locatorForColumn));
        List<String> columnCollectionOfText = new ArrayList<>();
        for (WebElement element : columnCollectionOfWebElements) {
            columnCollectionOfText.add(element.getText());
        }
        return columnCollectionOfText;
    }

//----------------------------------------------------------------------------------------------------

    public static List<String> getColumnData(By by) {
        List<WebElement> columnCollectionOfWebElements = Driver.getDriver().findElements(by);
        List<String> columnCollectionOfText = new ArrayList<>();
        for (WebElement element : columnCollectionOfWebElements) {
            columnCollectionOfText.add(element.getText());
        }
        return columnCollectionOfText;
    }

//----------------------------------------------------------------------------------------------------

    public static double getSumOfColumn(int columnNumber) {
        String locatorForColumn = "tbody>tr>td:nth-of-type(" + columnNumber + ")";
        List<WebElement> columnCollectionOfWebElements = Driver.getDriver().findElements(By.xpath(locatorForColumn));

        double sum = 0;
        for (WebElement element : columnCollectionOfWebElements) {
            String s = element.getText().replace(",", "").replace(".", "");
            sum += Integer.parseInt(s);
        }
        return sum / 100;
    }

//----------------------------------------------------------------------------------------------------

    public static double getSumOfColumn(By by) {
        List<WebElement> columnCollectionOfWebElements = Driver.getDriver().findElements(by);

        double sum = 0;
        for (WebElement element : columnCollectionOfWebElements) {
            String s = element.getText().replace(",", "").replace(".", "");
            sum += Integer.parseInt(s);
        }
        return sum / 100;
    }


    //====================================================================================================
//   Screen Shoots
//----------------------------------------------------------------------------------------------------
    public static void captureScreenShot(String ssName) {
        try {
            String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot scrShoot = (TakesScreenshot) Driver.getDriver();
            File source = scrShoot.getScreenshotAs(OutputType.FILE);
            String dest = System.getProperty("user.dir") + "/test-output/Screenshots/" + ssName + date + ".png";

            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
        } catch (Exception e) {
            System.out.println("while taking the screenshot happened the exception" + e.getMessage());
        }
    }
//
//    public static String getScreenshot1(String ssName){
//        String time= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        TakesScreenshot takesScreenshot=(TakesScreenshot) Driver.getDriver();
//        File source= takesScreenshot.getScreenshotAs(OutputType.FILE);
//        String target=System.getProperty("user.dir")+"/test-output/Screenshots"+ssName+time+".png";
//        File finalDestination=new File(target);
//
//        try{
//            FileUtils.copyFile(source,finalDestination);
//
//        }catch (IOException io){
//
//
//
//        }
//        return target;
//    }
//    public static String getScreenshot2(String name)  {
//        // name the screenshot with the current date time to avoid duplicate name
//        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_hh:mm:ss a"));
//        // TakesScreenshot ---> interface from selenium which takes screenshots
//        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        // full path to the screenshot location
//        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
//
//        File finalDestination = new File(target);
//
//        // save the screenshot to the path given
//        try {
//            FileUtils.copyFile(source, finalDestination);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return target;
//    }


//====================================================================================================
//   loaderMaskLocator  / need adaptation and modification for BriteERP
//----------------------------------------------------------------------------------------------------
//????????????????????????????????????????????????????????????????????????????????????????????
//    /**
//     * Waits until loader screen present. If loader screen will not pop up at all,
//     * NoSuchElementException will be handled  bu try/catch block
//     * Thus, we can continue in any case.
//     */
//    public static void waitUntilLoaderScreenDisappear() {
//        try {
//            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Long.valueOf(ConfigurationReader.getProperty("explicitwait"))));
//
//            wait.until(ExpectedConditions.invisibilityOf(Driver.getDriver().findElement(By.cssSelector(loaderMaskLocator))));
//        }catch (Exception e){
//            System.out.println(e+" :: Loader mask doesn't present.");
//        }
//    }
//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
// Need adaptation and modification for BriteERP
//    public static List<String> getColumnData(String columnName){
//        int columnNumber = 0;
//        for (int i=0; i < getCountOfColumns(); i++){
//            if(columnName.equals(getHeadersText().get(i))){
//                columnNumber = i + 1;
//            }
//        }
//        String locatorForColumn = "//table[1]//tbody//tr//td["+columnNumber+"]";
//        //this is a collection of web elements that contains values from specific column
//        List<WebElement> columnCollectionOfWebElements = Driver.getDriver().findElements(By.xpath(locatorForColumn));
//        List<String> columnCollectionOfText = new ArrayList<>();
//        for (WebElement element: columnCollectionOfWebElements){
//            columnCollectionOfText.add(element.getText());
//        }
//        return columnCollectionOfText;
//    }


}