package tests.components.expenses;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.components.components.pages.expenses.ExpensesPage;
import utilities.BriteErpUtils;
import utilities.SeleniumUtils;
import utilities.TestBase;

// There are 2 test case here
public class TestCaseID_MA8 extends TestBase {
// TestCase ID MA-8
// TEST CASE: Testing from manager perspective: The manager should be able to verify  if the manager should be able to create new expenses document
// step 1 Enter username
// Step 2 Enter password
// Step 3 Go to Expenses (My Expenses to Submit)
// Step 4 Click on create button
// Step 5 Choose a product
// Step 6 put number on quantity price
// Step 7 Choose the employee
// step 8 click on save button

    // to be able to access locators in ExpensesPage class
    ExpensesPage expensesPage = new ExpensesPage();
    String num = "5";
    @Test(description = "Create an expense document")
    public void createExpensesDoc(){
        extentLogger=report.createTest("Creating an expense document");
        BriteErpUtils.login();
        SeleniumUtils.waitPlease(2);
        // Step 1 Go to Expenses (My Expenses to Submit)
        expensesPage.expenseDoc.click();
        SeleniumUtils.waitPlease(2);
        // my expenses to submit
        String expectedHeaderText = "My Expenses to Submit";
        Assert.assertEquals(expensesPage.header.getText(), expectedHeaderText);
        SeleniumUtils.waitPlease(2);
        // Step 2 Click on create button
        expensesPage.clickCreate.click();
        SeleniumUtils.waitPlease(2);
        // Step 3 Choose a product
        expensesPage.findProduct.click();
        expensesPage.chooseProduct.click();
        SeleniumUtils.waitPlease(2);
        // Step 4 put number on quantity price
        expensesPage.number.clear();
        expensesPage.number.sendKeys(num);
        extentLogger.info(num + " entered quantity number."); // we can put this to shows more specific details in the testing process
        SeleniumUtils.waitPlease(2);
        // Step 5 Choose the employee
        expensesPage.employeeButton.click();
        SeleniumUtils.waitPlease(2);
        expensesPage.chooseEmployee.click();
        SeleniumUtils.waitPlease(2);
        // step 6 click on save button
        expensesPage.save.click();
        SeleniumUtils.waitPlease(2);
        // step 7 verification process after saving it
        System.out.print(driver.getTitle());
        String AfterCreateTitle = "[///] iphone 7 - Odoo";
        Assert.assertEquals(driver.getTitle(), AfterCreateTitle);
        SeleniumUtils.waitPlease(2);
        extentLogger.pass(" An expense document has been created successfully.");

    }
    //    TestCase ID MA-12
// The user should be able delete an expenses document successfully.
    // step 1 click on action button
    // step 2 choose delete and click on it
    // Step 3 click on ok to finish deleting it
    // step 4 verification process after deleting it

    @Test(description = "Delete an expense document")
    public void deleteExpensesDoc() {
        extentLogger=report.createTest("Deleting an expense document");
        BriteErpUtils.login();
        SeleniumUtils.waitPlease(2);
        // Step 1 Go to Expenses (My Expenses to Submit)
        expensesPage.expenseDoc.click();
        SeleniumUtils.waitPlease(2);
        // Step 2 Click on create button
        expensesPage.clickCreate.click();
        SeleniumUtils.waitPlease(2);
        // Step 3 Choose a product
        expensesPage.findProduct.click();
        expensesPage.chooseProduct.click();
        SeleniumUtils.waitPlease(2);
        // Step 4 put number on quantity price
        expensesPage.number.clear();
        expensesPage.number.sendKeys(num);
        SeleniumUtils.waitPlease(2);
        // Step 5 Choose the employee
        expensesPage.employeeButton.click();
        SeleniumUtils.waitPlease(2);
        expensesPage.chooseEmployee.click();
        SeleniumUtils.waitPlease(2);
        // step 6 click on save button
        expensesPage.save.click();
        SeleniumUtils.waitPlease(2);
        expensesPage.action.click();
        SeleniumUtils.waitPlease(2);
        // step 7 choose delete and click on it
        expensesPage.chooseDelete.click();
        SeleniumUtils.waitPlease(2);
        // Step 8 click on ok to finish deleting it
        expensesPage.finishDelete.click();
        SeleniumUtils.waitPlease(2);
        // step 9 verification process after deleting it
        System.out.print(driver.getTitle());
        String AfterCreateTitle = "My Expenses to Submit - Odoo";
        Assert.assertEquals(driver.getTitle(), AfterCreateTitle);
        SeleniumUtils.waitPlease(2);
        extentLogger.pass(" An expense document has been deleted successfully.");

    }
}






