package tests.components.crm;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.components.components.pages.crm.OpportunityPage;
import tests.components.components.pages.crm.PipelineRevenuePage;
import utilities.BriteErpUtils;
import utilities.SeleniumUtils;
import utilities.TestBase;

import java.io.IOException;
import java.text.DecimalFormat;

public class PipelineRevenueTest extends TestBase {
    // to be able to access locators in PipelineRevenuePage class
    PipelineRevenuePage crmPipelineRev = new PipelineRevenuePage();

/*
Acceptance Criteria:
1.  Verify that second opportunity’ Expected Revenue value on the Pivot board should be the same
    as the Expected revenue column value on the list board.
*/

    @Test(priority = 1)
    public void PreConditionCreateOpportunity() {
        // we put this number (2) to create depending on how many opportunity we need as a pre-condition
        int NumOfNewOpportunities = 2;
        // to create a report
        // extentLogger comes from TestBase class so we can create reports
        extentLogger=report.createTest("Creating "+NumOfNewOpportunities+" new opportunities for precondition ");
        BriteErpUtils.login();
        SeleniumUtils.waitPlease(2);
        // to go to CRM module
        BriteErpUtils.navigateToModule("CRM");
//        SeleniumUtils.waitPlease(2);
        OpportunityPage.createOpportunity(NumOfNewOpportunities);
        SeleniumUtils.waitPlease(1);
        BriteErpUtils.logout();
        extentLogger.pass(NumOfNewOpportunities+" opportunit(y/ies) has/have been created.");

    }

    /*
    Acceptance Criteria:
    2.  Verify that on the pivot table, the total expected revenue should be the sum of all opportunities’
        expected revenue.
    */
    @Test(priority=2)
    public void verifySumOfRevenuesEqual() throws IOException {
        extentLogger=report.createTest("Verify that the Sum of Revenues is equal to the Total Revenue");
        BriteErpUtils.login();
        SeleniumUtils.waitPlease(2);
        // to go to CRM module
        BriteErpUtils.navigateToModule("CRM");
        crmPipelineRev.pivotElement.click();
        SeleniumUtils.waitPlease(1);
        crmPipelineRev.totalRowElement.click();
        crmPipelineRev.opportunityElement.click();
        SeleniumUtils.waitPlease(1);
        // we want the number as double. For example 12.33 and 12.3299 so it is better to show only 2 decimals after the number for confirmation
        DecimalFormat df = new DecimalFormat(".00");

        extentLogger.info("Verification of Sum of Revenues"); // to put notes on the report , so we can give details  during the test report
        double SumOfRevenues = (BriteErpUtils.getSumOfColumn(crmPipelineRev.column2Element))/3; // by dividing 3 , since the table shows 3 totals ( with the one that I must calculate)
        extentLogger.info("Verified that the Sum of Revenues  is $ "+df.format(SumOfRevenues)); // to show it on the test report

        extentLogger.info("Verification of Total Revenue"); // to show verification on the test report
        double TotalRevenue = BriteErpUtils.convertToDouble(crmPipelineRev.pivotTotalElement.getText()); // result is expected as double
        extentLogger.info("Verified that the Total Revenue is $ "+df.format(TotalRevenue));

        Assert.assertEquals(df.format(SumOfRevenues),df.format(TotalRevenue)); // to validate the result
        BriteErpUtils.logout();
        extentLogger.pass("Verified that the Sum of the Revenues is equal to Total Revenue"); // to declare the final result on the report
    }

    /*
Acceptance Criteria:
2.  Verify that the pivot table total expected revenue should be same as the list table total expected revenue.

*/

    @Test(priority=3)
    public void verifyRevenues() {
        extentLogger=report.createTest("Verify Pivot-TotalRevenue equals List-TotalRevenue");
        BriteErpUtils.login();
        SeleniumUtils.waitPlease(2);
        // go to CRM
        BriteErpUtils.navigateToModule("CRM");

        crmPipelineRev.pivotElement.click();
        SeleniumUtils.waitPlease(1);
        extentLogger.info("Verification of Pivot-TotalRevenue");
        String PivotExpectedRevenue = crmPipelineRev.PivotExpectedRevenueElement.getText();
        extentLogger.info("Verified that the Pivot-TotalRevenue is $ "+PivotExpectedRevenue);

        extentLogger.info("Verification of List-TotalRevenue");
        crmPipelineRev.listElement.click();
        String ListExpectedRevenue = crmPipelineRev.ListExpectedRevenueElement.getText();
        extentLogger.info("Verified that the List-TotalRevenue is $ "+ListExpectedRevenue);

//        Assert.assertTrue(PivotExpectedRevenue.equals(ListExpectedRevenue));  // that can be used as well instead
        Assert.assertEquals(PivotExpectedRevenue,ListExpectedRevenue);
        BriteErpUtils.logout();
        extentLogger.pass("Verified both numbers are equal");
    }


}