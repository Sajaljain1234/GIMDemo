package com.hcl.gim.baseutil;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.hcl.gim.commonutils.ExcelUtil;
import com.hcl.gim.commonutils.FileOperation;
import com.hcl.gim.listeners.TestListener;
import com.hcl.gim.reports.Extent;
import com.hcl.gim.reports.ExtentHCLManager;
import com.hcl.gim.reports.ExtentHCLTest;
import com.hcl.gim.util.WebActionUtil;


import io.github.bonigarcia.wdm.WebDriverManager;
/***********************************************************************
 * Description : Implements Application Precondition and Postcondition.
 * 
 * @author : Shreya U ,Vivek Dogra, Aatish Slathia
 * @BeforeSuite: Creates all the folder structure for Extent Reports
 * @BeforeClass : Launches the browser according to the browser name specified.
 * @AfterClass : Closes the browser after completion of execution
 * @AfterSuite: Kills the driver (example chromedriver.exe) according to browser
 *              specified.
 */

public class BaseTest {
	
	public  WebDriver driver;
	public static Properties prop;
	
	public static Properties prop_app_constants;
	public static final int ITO = 10;
	public static final int ETO = 30;
	public static String sDirPath = System.getProperty("user.dir");
	public static final String EXCELPATH = sDirPath + "./data/GimTestData.xlsx";
	public static final String CLAIMTYPEEXCELPATH = sDirPath + "./data/ClaimTypeTemplateData.xlsx";
	public static Logger logger = LogManager.getLogger();
	public static  WebActionUtil WebActionUtil;
	public String testCaseName;
	public DesiredCapabilities cap;
	public static final String LOCAL_HUB_URL = "http://localhost:4444/wd/hub";
	public static final String CONFIGPATH = sDirPath + "./config/config.properties";
	public static final String GIM_APP_VALIDATIONS_CONSTANTS=sDirPath + "./data/GIM_Application_Validation_Constants.properties";
	
	public static ChromeOptions chromeOpt;
	public static final String SAMPLEFILESEXCELPATH = sDirPath + "./data/SampleFiles";
	public static final String GIMTESTDATAOTHERFLOWEXCELPATH = sDirPath + "./data/GIMTestData_OtherFlows.xlsx";
	//public String username = ExcelUtil.getCellData(EXCELPATH, "SystemUserName", 1, 0);
	
	static
	{
		try {
			prop = new Properties();
			prop_app_constants = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATH);
			prop.load(fis);
			
			FileInputStream fis1 = new FileInputStream(GIM_APP_VALIDATIONS_CONSTANTS);
			prop_app_constants.load(fis1);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Description : Creates folder structure for Extent reports.
	 * 
	 * @author:Shreya U
	 */
	@BeforeSuite(alwaysRun = true)
	public synchronized void createFiles() {
		try {
			logger.info("Folder creation for Extent");
			FileOperation fileOperation = new FileOperation();
			fileOperation.CreateFiles();
		} catch (Exception e) {
			logger.error("Exception while report inititation");
		}

	}

	/**
	 * Description: Launches the browser as specified in the parameter
	 * 
	 * @author:Shreya U,Vivek Dogra
	 * @param :browserName
	 */
	@Parameters({ "browserName" })
	@BeforeClass
	public synchronized void launchApp(String browserName) {
		
		logger = LogManager.getLogger(getClass().getName());
		ExtentTest parentExtentTest = ExtentHCLTest.createTest(getClass().getSimpleName());
		 ExtentHCLManager.setParentReport(parentExtentTest);
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		String userPath=System.getProperty("user.home")+"\\AppData\\Local\\Google\\Chrome\\UserData";
		 chromeOpt = new ChromeOptions(); 
     	 chromeOpt.addArguments("user-data-dir=" +userPath);


		 driver = new ChromeDriver(chromeOpt);
		 
			/*
			 * try {
			 * 
			 * //driver =CreateDriver.getInstance().setWebDriver(browserName,
			 * cap,LOCAL_HUB_URL ); driver
			 * =CreateDriver.getInstance().setdriver(browserName); } catch (Throwable e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); }
			 */
		
   
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		WebActionUtil = new WebActionUtil(driver, ETO);
		WebActionUtil.deleteXlFilesFromDownloads(System.getProperty("user.home")+"/Downloads");
				
		driver.manage().window().maximize();
//		String appurl= prop.getProperty("App_URL");
//		driver.get(appurl);
		
	}


	/**
	 * Description: Closes the browser
	 * 
	 * @author:Shreya U
	 */
	@AfterClass
	public synchronized void closeBrowser() {

		try {
			if (driver != null) {

				driver.quit();

			} else {
				logger.error("Unable to close the driver");
			}
		} catch (Exception e) {

		}

	}

	/**
	 * Description: Kills the driver in Task Manager as specified in the parameter.
	 * 
	 * @author:Shreya U
	 * @param :browserName
	 */
	@AfterSuite
	@Parameters({ "browserName" })
	public synchronized void killTask(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} else if (browserName.equalsIgnoreCase("firefox")) {

				Runtime.getRuntime().exec("taskkill /IM firefox.exe /F");
			} else if (browserName.equalsIgnoreCase("edge")) {

				Runtime.getRuntime().exec("taskkill /F /IM MicrosoftEdgeCP.exe");
			} else {
				logger.error("Browser name not specified properly to kill the task");
			}

		} catch (IOException e) {

		}
	}
	/**
	 * Description: Creates nodes for the test methods in Extent report.
	 * = 
	 * @author:Shreya U
	 * @param: methodName
	 */
	@BeforeMethod
	@Parameters({ "browserName" })
	public synchronized void setExtentReport(String browserName,Method methodName) {

		

	this.testCaseName = methodName.getName();
	//ExtentTest testReport = ExtentHCLManager.getParentReport().createNode(testCaseName, "Description");

	String desTestName = methodName.getAnnotation(Test.class).testName();
    String description = methodName.getAnnotation(Test.class).description();
 	ExtentTest testReport = ExtentHCLManager.getParentReport().createNode(testCaseName,description );
	ExtentHCLManager.setTestReport(testReport);
    	

		if(driver.getWindowHandles().size()>0) {
			WebActionUtil.validationinfo(browserName + " Browser is launched","blue");
			WebActionUtil.info(browserName + " Browser is launched");
		}
		else {
			WebActionUtil.fail("Failed to launch the Browser");
			WebActionUtil.error("Failed to launch the Browser");
			}
		String appurl= prop.getProperty("App_URL");
		driver.get(appurl);
	}
	
	
	
}
