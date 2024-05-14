package org.qa.utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.qa.QACore;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Properties;

public class WebBaseTest extends QACore {
    protected static Properties serverProperties = new Properties();
    private static final String URL = "homePageURL";

    public static ExtentReports extentReports;
    public static ExtentTest logger;

    protected static WebDriver driver;

    protected void loadOnSuiteStart() throws Exception {
        serverProperties = getConfigsValue("Config-Web.properties");
    }

    private void reportHandler() {
        System.out.println(LocalDateTime.now());
        extentReports = new ExtentReports("reports/index/report " + LocalDateTime.now() + ".html");
        extentReports.addSystemInfo("Automation", "Quality Professionals Assignment");
        extentReports.addSystemInfo("Author", "Saeed Sokar");
        extentReports.addSystemInfo("version", "1");
    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        loadOnSuiteStart();
        reportHandler();
    }

    @AfterSuite
    public void afterSuite() throws Exception {
        extentReports.flush();
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger = extentReports.startTest(method.getName());
        openBrowser();
    }

    public void openBrowser() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver");
            ChromeOptions opt = new ChromeOptions();
//            opt.addExtensions(new File("./src/main/resources/extensions/Adblock.crx"));
            driver = new ChromeDriver(opt);
//            driver.manage().window().maximize();
            driver.navigate().to(serverProperties.getProperty(URL));
        }
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult result) throws InterruptedException {

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test Failed due to: " + result.getThrowable());
            logger.log(LogStatus.FAIL, logger.getDescription());
        } else {
            logger.log(LogStatus.SKIP, "Test escape as there is no error/exception ");
        }
        captureScreenshot(driver, result.getName());
    }
}
