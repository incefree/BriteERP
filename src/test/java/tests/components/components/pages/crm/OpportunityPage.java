package tests.components.components.pages.crm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.time.Duration;
import java.util.Random;

public class OpportunityPage {
    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Long.valueOf(ConfigurationReader.getProperty("explicitwait")));

    @FindBy(css = "button[class='btn btn-icon fa fa-lg fa-table o_cp_switch_pivot']")
    public WebElement pivotElement;

    @FindBy(css = "button[class='btn btn-icon fa fa-lg fa-list-ul o_cp_switch_list']")
    public WebElement listElement;

    @FindBy(xpath = "(//span[@class='fa fa-ellipsis-v'])[1]")
    public WebElement optToggleElement;

    @FindBy(xpath = "(//div[@class='oe_kanban_content'])[1]")
    public WebElement FirstOptElement;

    @FindBy(xpath = "(//a[contains(text(),'Delete')])[2]")
    public WebElement DeleteButton1Locator;

    @FindBy(css = "[class='btn btn-sm btn-primary']")
    public WebElement okButton1Locator;

    @FindBy(xpath = "//li[contains(text(),'Pipeline')]")
    public WebElement pipelineLocator;

    @FindBy(css = "span[class='o_pager_limit']")  //     span[class='o_pager_limit']
    public WebElement optNumberBeforeDelete;

    @FindBy(xpath = "//tr[1]//td[1]")
    public WebElement vendorBottonLocator;

    @FindBy(css = "tr:nth-of-type(1)>td:nth-of-type(1)>div:nth-of-type(1)>input:nth-of-type(1)")
    public WebElement checkBoxLocator;

    @FindBy(xpath = "//button[contains(text(),'Action')]")
    public WebElement ActionButtonLocator;

    @FindBy(linkText = "Delete")
    public WebElement DeleteButton2Locator;

    @FindBy(xpath = "//span[contains(text(),'Ok')]")
    public WebElement okButton2Locator;

    @FindBy(css = "span[class='o_pager_limit']")
    public WebElement optNumberAfterDelete;

    @FindBy(css = "button[class^='btn btn-primary btn-sm o-kanban']")
    public WebElement CreateElement;

    @FindBy(css = "input[class^='o_field_char o_field_widget o_input o_required']")
    public WebElement OpportunityTitle;

    @FindBy(xpath = "(//div[@class='o_input_dropdown'])[1]")
    public WebElement CustomerElement;

    @FindBy(xpath = "(//li[@class='ui-menu-item'])[1]")
    public WebElement CustomerChoiceElement;

    @FindBy(css = "[class='o_field_float o_field_number o_field_widget o_input']")
    public WebElement ExpectedRevenueElement;

    @FindBy(xpath = "//table[@class='o_group o_inner_group o_group_col_6']//div[@name='priority']//a[@title='Very High']")
    public WebElement TwoStarElement;

    @FindBy(xpath = "Priority")
    public WebElement priorityElement;

    @FindBy(css = "button[name='close_dialog']")
    public WebElement CreateFinalElement;


    public OpportunityPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    public static void createOpportunity(int NamOfNewOpportunities) {

        for (int i = 0; i < NamOfNewOpportunities; i++) {

            String firstName = OpportunityPage.randomFirstName();
            String lastName = OpportunityPage.randomLastName();
            String Title = firstName + " " + lastName;
            String Revenue = OpportunityPage.randomRevenue();
            OpportunityPage opportunity = new OpportunityPage();
            SeleniumUtils.waitPlease(1);
            opportunity.CreateElement.click();
            SeleniumUtils.waitPlease(1);
            opportunity.OpportunityTitle.sendKeys(Title);
//            SeleniumUtils.waitPlease(1);
            opportunity.CustomerElement.click();
            opportunity.CustomerChoiceElement.click();
            opportunity.ExpectedRevenueElement.click();
            opportunity.ExpectedRevenueElement.clear();
            opportunity.ExpectedRevenueElement.sendKeys(Revenue);
            opportunity.TwoStarElement.click();
            //           SeleniumUtils.waitPlease(1);
            opportunity.CreateFinalElement.click();

        }
    }

    public static String randomFirstName() {
        Random rand = new Random();
        String choices2 = "abcdefghijklmnopqrstuvwxyz";
        String choices1 = choices2.toUpperCase(); // first letter has to be uppercase
        int i = 0;
        String name = choices1.charAt(rand.nextInt(choices1.length())) + "";
        while (i < SeleniumUtils.OneDigitRanNum()) {

            name += choices2.charAt(rand.nextInt(choices2.length()));
            i += 1;
        }
        return name;
    }

    public static String randomLastName() {
        Random rand = new Random();
        String choices2 = "abcdefghijklmnopqrstuvwxyz";
        String choices1 = choices2.toUpperCase(); // first letter has to be uppercase
        int i = 0;
        String name = choices1.charAt(rand.nextInt(choices1.length())) + "";
        while (i < SeleniumUtils.OneDigitRanNum()) {

            name += choices2.charAt(rand.nextInt(choices2.length()));
            i += 1;
        }
        return name;
    }

    // numbers before point  x x x . __  whole number  parts   // for example 786
    public static String randomRevenue() {
        Random rand = new Random();
        String choices1 = "123456789";  // first number can not be 0
        String choices2 = "1234567890";

        String number1 = choices1.charAt(rand.nextInt(choices1.length())) + "";
        for (int i = 0; i < SeleniumUtils.OneDigitRanNum(); i++) {
            number1 += choices2.charAt(rand.nextInt(choices2.length()));
        }

// decimal parts      // for example 786.96   --> creates the part that has .96
        String number2 = "";
        for (int i = 0; i < 2; i++) {
            number2 += choices2.charAt(rand.nextInt(choices2.length()));
        }
        String number = number1 + "." + number2;
        return number;
    }


}