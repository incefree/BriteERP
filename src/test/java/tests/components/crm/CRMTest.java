package tests.components.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BriteErpUtils;
import utilities.BrowserFactory;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

public class CRMTest {

    WebDriver driver = BrowserFactory.getDriver("chrome");
    String usernameLoginLocator = "//input[@id='login']";
    String passwordLoginLocator = "//input[@id='password']";
    String opportunityNameLocator = "(//td[@class='o_data_cell o_required_modifier'])[1]";

    @BeforeMethod
    public void setUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://34.220.250.213/web/login");
        // step 1 Enter username
        driver.findElement(By.xpath(usernameLoginLocator)).sendKeys("eventscrmmanager64@info.com");
        // Step 2 Enter password
        driver.findElement(By.xpath(passwordLoginLocator)).sendKeys("eventscrmmanager");
        // Step 3 Click on Log in button
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
    }

    @Test
    public void CRMModuleTest() {


        WebElement crm = driver.findElement(By.xpath("//span[@class='oe_menu_text'][contains(text(),'CRM')] "));
        crm.click();
        SeleniumUtils.waitPlease(4);


        driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm o-kanban-button-new'] ")).click();
        SeleniumUtils.waitPlease(4);

        // enter opportunity title
        driver.findElement(By.xpath("//*[@class='o_field_char o_field_widget o_input o_required_modifier']")).sendKeys("customer");

        // click on customer
        driver.findElement(By.xpath("//input[@class='o_input ui-autocomplete-input']")).click();

        // enter the name for the customer
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//a[contains(text(),'---???NewName')]")).click();
        SeleniumUtils.waitPlease(3);

        // put the number for expected revenue
        driver.findElement(By.xpath("//*[@class='o_field_float o_field_number o_field_widget o_input']")).click();
        driver.findElement(By.xpath("//*[@class='o_field_float o_field_number o_field_widget o_input']")).clear();
        driver.findElement(By.xpath("//*[@class='o_field_float o_field_number o_field_widget o_input']")).sendKeys("5");
        SeleniumUtils.waitPlease(4);

        // click on create
        driver.findElement(By.xpath("//button[@name='close_dialog']")).click();
        SeleniumUtils.waitPlease(4);

        // go to and click on the list button
        WebElement list=   driver.findElement(By.xpath("//button[@data-original-title='List']"));
//        SeleniumUtils.clickOn(driver, list, 15);
        SeleniumUtils.waitPlease(5);

        // select the first option
        driver.findElement(By.xpath("//tr[1]//td[1]//div[1]//input[1]")).click(); // fails here

        // go to action
        driver.findElement(By.xpath("//button[contains(text(),'Action')]")).click();
        SeleniumUtils.waitPlease(4);

        // delete
        WebElement del = driver.findElement(By.xpath("//a[contains(text(),'Delete')]"));
//        SeleniumUtils.clickOn(driver, del, 15);
        SeleniumUtils.waitPlease(4);

        // ok and verify
        WebElement confirm = driver.findElement(By.xpath("//span[contains(text(),'Ok')]"));
//        SeleniumUtils.clickOn(driver, confirm, 15);
        SeleniumUtils.waitPlease(4);


        driver.navigate().refresh();
        System.out.print(driver.getTitle());
        String expectedOpportunityName = "Odoo";
        Assert.assertEquals(driver.getTitle(), expectedOpportunityName);
        SeleniumUtils.waitPlease(2);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}



