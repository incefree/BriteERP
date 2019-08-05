package tests.components.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

import java.util.concurrent.TimeUnit;

public class CRMTest2 {

    WebDriver driver = BrowserFactory.getDriver("chrome");
    String CRMModuleLocator = "(//span[@class='oe_menu_text'])[5]";
    String listButtonLocator = "(//button[@accesskey='l'])";
    String checkBoxLocator = "(//input[@type='checkbox'])[4]";
    String opportunityTextLocator = "(//th[@class='o_column_sortable'])[2]";
    String actionButtonLocator = "(//button[@aria-expanded='false'])[2]";
    String deleteButtonLocator = "(//a[@data-index='3'])";
    String okButtonLocator = "button[class='btn btn-sm btn-primary']";
    String opportunityNameLocator = "(//td[@class='o_data_cell o_required_modifier'])[1]";

    @BeforeMethod
    public void setUp(){
        // make it full screen
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://34.220.250.213/web/login");
    }

    @Test
    public void CRMModuleTest() {
        driver.findElement(By.id("login")).sendKeys("eventscrmmanager64@info.com");
        driver.findElement(By.id("password")).sendKeys("eventscrmmanager", Keys.ENTER);
        // Go to CRM module
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(CRMModuleLocator))));
        driver.findElement(By.xpath(CRMModuleLocator)).click();
        driver.findElement(By.xpath(listButtonLocator)).click();

        String expectedResult = "Opportunity";
        String actualResult = driver.findElement(By.xpath(opportunityTextLocator)).getText();
        Assert.assertEquals(actualResult, expectedResult);

        String expectedOpportunityName = "meeting";
        driver.findElement(By.xpath(checkBoxLocator)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(actionButtonLocator))));
        // go to action
        driver.findElement(By.xpath(actionButtonLocator)).click();
        // delete
        driver.findElement(By.xpath(deleteButtonLocator)).click();
        // confirm by clicking on OK
        driver.findElement(By.cssSelector(okButtonLocator)).click();
        // refresh the page
        driver.navigate().refresh();
        String actualOpportunityName = driver.findElement(By.xpath(opportunityNameLocator)).getText();
        Assert.assertFalse(actualOpportunityName.equalsIgnoreCase(expectedOpportunityName));
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
