package tests.components.components.pages.expenses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigurationReader;
import utilities.Driver;

public class ExpensesPage {

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Long.valueOf(ConfigurationReader.getProperty("explicitwait")));

    @FindBy(xpath = "(//*[@class='oe_menu_text'][contains(text(),'Expenses')])[1]")
    public WebElement expenseDoc;

    @FindBy(xpath = "//*[@class='active'][contains(text(),'My Expenses to Submit')]")
    public WebElement header;

    @FindBy(xpath = "//*[@class='btn btn-primary btn-sm o_list_button_add'][contains(text(),'Create')]")
    public WebElement clickCreate;

    @FindBy(xpath = "(//input[@class='o_input ui-autocomplete-input'])[1]")
    public WebElement findProduct;

    @FindBy(xpath = "//a[contains(text(),'[///] iphone 7')] ")
    public WebElement chooseProduct;

    @FindBy(xpath = "//*[@name='quantity']")
    public WebElement number;

    @FindBy(xpath = "//input[@class='o_input ui-autocomplete-input'][@id='o_field_input_27']")
    public WebElement employeeButton;

    @FindBy(xpath = "//a[contains(text(),'Ashley Presley')]")
    public WebElement chooseEmployee;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm o_form_button_save']")
    public WebElement save;

    @FindBy(xpath = "//button[contains(text(),'Action')]")
    public WebElement action;

    @FindBy(xpath = "//a[contains(text(),'Delete')]")
    public WebElement chooseDelete;

    @FindBy(xpath = "//span[contains(text(),'Ok')]")
    public WebElement finishDelete;



    public ExpensesPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


}
