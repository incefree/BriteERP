package tests.components.components.pages.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.time.Duration;

public class PipelineRevenuePage {

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Long.valueOf(ConfigurationReader.getProperty("explicitwait")));

    @FindBy(css= "button[class='btn btn-icon fa fa-lg fa-table o_cp_switch_pivot']")
    public WebElement pivotElement;

    @FindBy(xpath= "//tr[1]//td[2]")
    public WebElement PivotExpectedRevenueElement;

    @FindBy(css= "button[class='btn btn-icon fa fa-lg fa-list-ul o_cp_switch_list']")
    public WebElement listElement;

    @FindBy(css= "td[title='Expected Revenues']")
    public WebElement ListExpectedRevenueElement;

    @FindBy(css= "td[class='o_pivot_header_cell_closed']")
    public WebElement totalRowElement;

    @FindBy(xpath= "//a[contains(text(),'Opportunity')]")
    public WebElement opportunityElement;

    @FindBy(css= "td:nth-of-type(2)")
    public WebElement revenueListElement;

    @FindBy(css= "tr:nth-of-type(1)>td:nth-of-type(2)")
    public WebElement pivotTotalElement;

    public By column2Element = By.cssSelector("tbody>tr>td:nth-of-type(2)");


    public PipelineRevenuePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


}