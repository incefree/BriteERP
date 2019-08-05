package tests.components.login_navigation;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.components.components.pages.login.LoginPage;
import utilities.*;

public class LoginTest extends TestBase {
    LoginPage login = new LoginPage();

    @Test
    public void validCredential() {

        // manager number must start from 1
        for (int i = 1; i <= login.userNames.size(); i++) { // we said <= so it is not out of boundary
            // extentLogger comes from TestBase class so we can create reports
            //this is required, otherwise you will get null pointer exception
            extentLogger = report.createTest("Login as an Event Scrum Manager #" + i);
            BriteErpUtils.login(login.userNames.get(i - 1), login.password); // i-1 indicates index number , (first step: 1-1 = 0)
            SeleniumUtils.waitPlease(3);
            String expectedTitle = "#Inbox - Odoo";
            String actualTitle = Driver.getDriver().getTitle();
            extentLogger.info("Actual title is :" + actualTitle); // info shows Details part on reports
            extentLogger.info("Hi, How are you today?"); // to indicate a message on the report
            // to validate title
            Assert.assertTrue(expectedTitle.contains(actualTitle)); // contains is better for dynamic
            BriteErpUtils.logout();
            extentLogger.pass("It has been verified that Main Page title is \"" + expectedTitle + "\".");
        }
    }
}