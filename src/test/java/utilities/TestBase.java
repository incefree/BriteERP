package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import tests.components.components.pages.login.LoginPage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestBase {
    //should be public/protected !!!!
    public WebDriver driver;
    public Actions action;
    public SoftAssert softAssert;
    public WebDriverWait wait;
    //we need this object for building reports, but it doesn't build itself
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @BeforeTest // it runs only one time before the test. The annotated method will be run before any test method belonging to the classes inside the <test> tag is run.
    public void testSetup() {
        //we are creating actual reporter
        // ExtentReports generate a HTML report that contain the appropriate test results
        report = new ExtentReports(); // creates the report
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss"));

        // this is path to the report itself

//      String pathToReport = System.getProperty("user.dir")+"/test-output/report.html";
        String pathToReport = System.getProperty("user.dir") + "/test-output/reports/report_" + date + ".html";
        ;
        htmlReporter = new ExtentHtmlReporter(pathToReport);

        report.attachReporter(htmlReporter);

        //we can add system information to report
        report.setSystemInfo("II", System.getProperty("ii.name"));

        htmlReporter.config().setDocumentTitle("BriteERP Test Automation");

    }


    @BeforeClass // The annotated method will be run before the first test method in the current class is invoked
    public void setup() {
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitwait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("url"));
    }

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert(); // soft assert means the testing still continues even if a condition fails
        action = new Actions(driver);
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(2));


    }


    //ITestresult describes the result of a test.
    //we can determine if test failed, passed or ignored
    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            //if test failed get a screenshot and save the location to the image
            String pathToTheScreenshot = SeleniumUtils.getScreenshot2(result.getName());

            // capture the name of a test method that failed
            extentLogger.fail(result.getName());
            try {
                //to add screenshot into report
                extentLogger.addScreenCaptureFromPath(pathToTheScreenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //to add thrown exception into report
            extentLogger.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            //if test skipped, this information will appear on the report
            extentLogger.skip("Test case skipped " + result.getName());
        }

    }

    @AfterClass
    public void teardown() {
        Driver.closeDriver();
    }

    @AfterTest
    public void tearDownTest() {
        report.flush();
    } // Flush method is used to erase any previous data on the report and create a new report


}