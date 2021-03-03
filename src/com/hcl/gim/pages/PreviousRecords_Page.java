package com.hcl.gim.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gim.util.WebActionUtil;

/**
 * Description: This class implements the methods for Previous Records Page
 * 
 * @author Sajal jain
 */
public class PreviousRecords_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 30;

	public PreviousRecords_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Search Filter Button */
	@FindBy(xpath = "//a[@class='searhFilter']/i")
	private WebElement btnSearchFilter;

	/* Search Text Box */
	@FindBy(id = "searchbox")
	private WebElement txtSearchBox;

	/* Irem Filter Button */
	@FindBy(xpath = "//a/i[@class='iRem-filter']")
	private WebElement btnIremFilter;

	/* Employee Name Text Box */
	@FindBy(xpath = "//input[@class='form-control employeeIdName']")
	private WebElement txtEmployeeName;

	/* Claim Id Text Box */
	@FindBy(xpath = "//input[contains(@class,'form-control claimId numberAndCommaOnly')]")
	private WebElement txtClaimIDInIremFilter;

	/* Country Drop down */
	@FindBy(xpath = "(//a[@class='selecty-selected'])[1]")
	private WebElement ddCountry;

	/* Option in Country Drop down */
	private WebElement getCountry(String countryName) {
		String xpath = "//ul[@class='selecty-options active']//li[text()='" + countryName + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Input Type Drop down */
	@FindBy(xpath = "(//a[@class='selecty-selected'])[2]")
	private WebElement ddInputType;

	/* Option Input Type Drop down */
	private WebElement getInputType(String inputType) {
		String xpath = "//ul[@class='selecty-options active']//li[text()='" + inputType + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Period Drop down */
	@FindBy(xpath = "//label[text()='Period']/following-sibling::input")
	private WebElement ddPeriod;

	/* Status Drop down */
	@FindBy(xpath = "(//a[@class='selecty-selected'])[3]")
	private WebElement ddStatus;

	/* Option Status Drop down */
	private WebElement getStatus(String status) {
		String xpath = "//ul[@class='selecty-options active']//li[text()='" + status + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Apply Button */
	@FindBy(xpath = "//button[text()='APPLY']")
	private WebElement btnAPPLY;

	/* Reset Button */
	@FindBy(xpath = "//button[text()='RESET']")
	private WebElement btnRESET;

	/* Close Button in Filter Pop up */
	@FindBy(xpath = "//span[@class=' icon-close filter-close']")
	private WebElement btnCloseFilterPopup;

	/* Click History */
	private WebElement getHistory(String claimID) {
		String xpath = "//a[text()='" + claimID + "']/parent::td/following-sibling::td[@class=' action']/descendant::i";
		return driver.findElement(By.xpath(xpath));
	}

	/* Fetch History Pop up Text */
	@FindBys({ @FindBy(xpath = "//div[contains(@class,'history')]/ul/li") })
	private List<WebElement> txtHistoryPopup;

	/* History Pop up Close Button */
	@FindBy(xpath = "//h4[contains(text(),'History')]/preceding-sibling::button")
	private WebElement btnCloseHistoryPopup;

	/* Click View Documents */
	private WebElement getViewDocuments(String claimID) {
		String xpath = "//a[text()='" + claimID
				+ "']/parent::td/following-sibling::td[@class=' text-center']/descendant::i";
		return driver.findElement(By.xpath(xpath));
	}

	/* View Documents Pop up Text */
	@FindBys({ @FindBy(xpath = "//a[contains(@id,'Anch')]") })
	private List<WebElement> txtViewDocumentsPopup;

	/* Fetch Status Approved */
	private WebElement getStatusApproved(String claimID) {
		String xpath = "//a[text()='" + claimID + "']/parent::td/following-sibling::td/span[@class='pendingText']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Fetch Status Rejected */
	private WebElement getStatusRejected(String claimID) {
		String xpath = "//a[text()='" + claimID + "']/parent::td/following-sibling::td/span[@class='referbackText']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Fetch Claim ID */
	private WebElement getClaimID(String claimID) {
		String xpath = "//a[text()='" + claimID + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Claim ID Pop up Text */
	@FindBys({ @FindBy(xpath = "//div[@id='claimDetailBody']//td/p") })
	private List<WebElement> txtClaimIDPopup;

	/* Claim ID Pop up Close Button */
	@FindBy(xpath = "//b[contains(text(),'Claim Details')]/parent::h4/preceding-sibling::button")
	private WebElement btnCloseClaimIDPopup;

	/* Page Size Drop down */
	@FindBy(name = "myTable_length")
	private WebElement ddPageSize;

	/* Next Arrow Button */
	@FindBy(id = "myTable_next")
	private WebElement btnNextArrow;

	/* Previous Arrow Button */
	@FindBy(id = "myTable_previous")
	private WebElement btnPreviousArrow;

	/* Filter Pop up Text */
	@FindBy(xpath = "//div[text()='Filter  ']")
	private WebElement txtFilter;

	/* Previous Records Text */
	@FindBy(xpath = "//ol[@class='breadcrumb']/li[@class='active']")
	private WebElement txtPreviousRecords;

	/* Pop up Claim Number text */
	@FindBy(xpath = "//label[contains(text(),'Claim Number')]")
	private WebElement popupClaimNumber;

	/* Pop up History Detail */
	@FindBy(xpath = "//div[@class='historyDeatil']//li")
	private List<WebElement> popupHistoryDetails;

	/* History Text */
	@FindBy(xpath = "//h4[text()='History ']")
	private WebElement txtHistory;

	/* Document Link */
	private WebElement lnkDocument(String documentName) {
		String xpath = "//a[text()='" + documentName + "']";
		return driver.findElement(By.xpath(xpath));
	}
	
	/* Processing div */
	@FindBy(xpath = "//div[text()='Processing...']")
	private WebElement divProcessing;

	/**
	 * Description Method to Select Period Drop down
	 * 
	 * @author Sajal jain
	 * @param date
	 */
	public synchronized void selectPeriodDD(String date) {
		try {
			WebActionUtil.waitForElement(btnIremFilter, "Irem Filter button");
			WebActionUtil.clickOnElement(btnIremFilter, "Irem Filter button", "Unable to click on Irem Filter button");
			WebActionUtil.waitForElement(ddPeriod, "Period drop down");
			WebActionUtil.clickOnElement(ddPeriod, "Period drop down", "Unable to click Period drop down");
			WebActionUtil.clearText(ddPeriod, "Period drop down");
			WebActionUtil.typeText(ddPeriod, date, "Period drop down");
			ddPeriod.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Period drop down");
			Assert.fail("Unable to perform action on Period drop down");
		}
	}

	/**
	 * Description: Click on View Document Icon and validate document is displayed
	 * and click on document link and validate document download
	 * 
	 * @author Sajal jain
	 * @param claimID
	 * @param documentName
	 * @param fileExtension
	 */
	public synchronized void validateViewDocumentsPopup(String claimID, String documentName, String fileExtension) {
		try {
			WebActionUtil.waitForElement(getViewDocuments(claimID), "View Documents icon");
			WebActionUtil.clickOnElement(getViewDocuments(claimID), "View Documents icon",
					"Unable to click on View Documents icon");
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElements(txtViewDocumentsPopup));
			WebActionUtil.validateisElementDisplayed(lnkDocument(documentName), "Document link",
					documentName + " link is displayed", documentName + " link is not displayed", "blue");
			WebActionUtil.clickOnElement(lnkDocument(documentName), "Document link",
					"Unable to click on " + documentName);
			String userDir = System.getProperty("user.home");
			String downloadedDocumentName = WebActionUtil.getDownloadedDocumentName(userDir + "/Downloads",
					fileExtension);
			Assert.assertTrue(downloadedDocumentName.contains(fileExtension));
			WebActionUtil.info(downloadedDocumentName + " file successfully downloaded");
			WebActionUtil.validationinfo(downloadedDocumentName + " file successfully downloaded", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on View Documents");
			Assert.fail("Unable to perform action on View Documents");
		}
	}

	/**
	 * Description Method to Validate Text in Claim Id Popup Text
	 * 
	 * @author Sajal jain
	 * @param claimID
	 * @param expectedMsg
	 */
	public synchronized void validateClaimIDPopupTxt(String claimID, String expectedMsg) {
		try {
			WebActionUtil.waitForElement(getClaimID(claimID), "Claim ID text");
			WebActionUtil.clickOnElement(getClaimID(claimID), "Claim ID text", "Unable to click Claim ID text");
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElements(txtClaimIDPopup));
			int count = 0;
			for (WebElement txtClaimID : txtClaimIDPopup) {
				if (txtClaimID.getText().contains(expectedMsg)) {
					WebActionUtil.validatePartialText(expectedMsg, txtClaimID, "Claim Id Pop up text",
							expectedMsg + " present in Claim Id pop up",
							expectedMsg + " not present in Claim Id pop up", "blue");
					WebActionUtil.clickOnElement(btnCloseClaimIDPopup, "Close button in Claim ID pop up",
							"Unable to click Close button in Claim ID pop up");
					count++;
					break;
				}
			}
			if (count == 0) {
				WebActionUtil.fail(expectedMsg + " is not present in Claim ID pop up");
				Assert.fail(expectedMsg + " is not present in Claim ID pop up");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Claim ID pop up text");
			Assert.fail("Unable to validate Claim ID PopUp Text");
		}

	}

	/**
	 * Description Method to Validate Status Reject Text is displayed
	 * 
	 * @author Sajal jain
	 * @param claimID
	 * @param expectedStatus
	 */
	public synchronized void validateStatusRejectTxt(String claimID, String expectedStatus) {
		try {
			WebActionUtil.waitForAngularPageload();
			if(divProcessing.isDisplayed())
			{
				new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOf(divProcessing));
			}
			WebActionUtil.waitForElement(getStatusRejected(claimID), "Reject Status text");
			WebActionUtil.validatetext(expectedStatus, getStatusRejected(claimID), "Reject Status text",
					expectedStatus + " status is displayed", expectedStatus + " status is not displayed", "green");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail(expectedStatus + " is not displayed");
			Assert.fail(expectedStatus + " is not displayed");
		}
	}

	/**
	 * Description Method to Validate Status Approve Text is displayed
	 * 
	 * @author Sajal jain
	 * @param claimID
	 * @param expectedStatus
	 */
	public synchronized void validateStatusApproveTxt(String claimID, String expectedStatus) {
		try {
			WebActionUtil.waitForAngularPageload();
			if(divProcessing.isDisplayed())
			{
				new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOf(divProcessing));
			}
			WebActionUtil.waitForElement(getStatusApproved(claimID), "Approve Status text");
			WebActionUtil.validatetext(expectedStatus, getStatusApproved(claimID), "Approve Status text",
					expectedStatus + " status is displayed", expectedStatus + " status is not displayed", "green");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail(expectedStatus + " is not displayed");
			Assert.fail(expectedStatus + " is not displayed");
		}
	}

	/**
	 * Description Method to click Previous Arrow Button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkPreviousArrowBtn() {
		try {
			WebActionUtil.waitForElement(btnPreviousArrow, "Previous Arrow button");
			WebActionUtil.scrollToElement(btnPreviousArrow, "Previous Arrow button");
			WebActionUtil.clickOnElement(btnPreviousArrow, "Previous Arrow button",
					"Unable to click Previous Arrow button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Previous Arrow button");
			Assert.fail("Unable to click Previous Arrow button");
		}
	}

	/**
	 * Description Method to click Next Arrow Button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkNextArrowBtn() {
		try {
			WebActionUtil.waitForElement(btnNextArrow, "Next Arrow button");
			WebActionUtil.scrollToElement(btnNextArrow, "Next Arrow button");
			WebActionUtil.clickOnElement(btnNextArrow, "Next Arrow button", "Unable to click Next Arrow button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Next Arrow button");
			Assert.fail("Unable to click Next Arrow button");
		}
	}

	/**
	 * Description Method to Select Page Size Drop down
	 * 
	 * @author Sajal jain
	 * @param pageSize
	 */
	public void selectPageSizeDD(String pageSize) {
		try {
			WebActionUtil.waitForElement(ddPageSize, "Page Size drop down");
			WebActionUtil.scrollToElement(ddPageSize, "Page Size drop down");
			WebActionUtil.selectByText(ddPageSize, pageSize);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to select option in Page Size drop down");
			Assert.fail("Unable to select option in Page Size drop down");
		}
	}

	/**
	 * Description Method to Click on Close Button in Filter
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkCloseBtn() {
		try {
			WebActionUtil.waitForElement(btnCloseFilterPopup, "Close button in filter");
			WebActionUtil.clickOnElement(btnCloseFilterPopup, "Close button in filter",
					"Unable to click Close button in filter");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Close button in filter");
			Assert.fail("Unable to click Close button in filter");
		}
	}

	/**
	 * Description Method to Click on Reset Button in Filter
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkResetBtn() {
		try {
			WebActionUtil.waitForElement(btnRESET, "Reset button");
			WebActionUtil.clickOnElement(btnRESET, "Reset button", "Unable to click Reset button");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Reset button");
			Assert.fail("Unable to click Reset button");
		}
	}

	/**
	 * Description Method to click on Apply button in filter
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkApplyBtn() {
		try {
			WebActionUtil.waitForElement(btnAPPLY, "Apply button");
			WebActionUtil.clickOnElement(btnAPPLY, "Apply button", "Unable to click Apply button");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Apply button");
			Assert.fail("Unable to click Apply button");
		}
	}

	/**
	 * Description Method to click on Irem filter and select Status drop down
	 * 
	 * @author Sajal jain
	 * @param status
	 */
	public synchronized void selectStatusDD(String status) {
		try {
			WebActionUtil.waitForElement(btnIremFilter, "Irem Filter button");
			WebActionUtil.clickOnElement(btnIremFilter, "Irem Filter button", "Unable to click on Irem filter button");
			WebActionUtil.waitForElement(ddStatus, "Status drop down");
			WebActionUtil.clickOnElement(ddStatus, "Status drop down", "Unable to click Status drop down");
			WebActionUtil.waitForElement(getStatus(status), status+" option in Status drop down");
			WebActionUtil.clickOnElement(getStatus(status), status+" option in Status drop down",
					"Unable to click "+status+" option in Status drop down");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Status drop down");
			Assert.fail("Unable to perform action on Status drop down");
		}
	}

	/**
	 * Description Method to click on Irem Filter and select Input Type Drop down
	 * 
	 * @author Sajal jain
	 * @param inputType
	 */
	public synchronized void selectInputTypeDD(String inputType) {
		try {
			WebActionUtil.waitForElement(btnIremFilter, "Irem Filter button");
			WebActionUtil.clickOnElement(btnIremFilter, "Irem Filter button", "Unable to click on Irem Filter button");
			WebActionUtil.waitForElement(ddInputType, "Input Type drop down");
			WebActionUtil.clickOnElement(ddInputType, "Input Type drop down", "Unable to click Input Type drop down");
			WebActionUtil.waitForElement(getInputType(inputType), inputType+" option in Input Type drop down");
			WebActionUtil.clickOnElement(getInputType(inputType), inputType+" option in Input Type drop down",
					"Unable to click option in Input Type drop down");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Input Type drop down");
			Assert.fail("Unable to perform action on Input Type drop down");
		}
	}

	/**
	 * Description Method to click on Irem Filter and select Country Drop down
	 * 
	 * @author Sajal jain
	 * @param countryName
	 */
	public synchronized void selectCountryDD(String countryName) {
		try {
			WebActionUtil.waitForElement(btnIremFilter, "Irem Filter button");
			WebActionUtil.clickOnElement(btnIremFilter, "Irem Filter button", "Unable to click on Irem Filter button");
			WebActionUtil.waitForElement(ddCountry, "Country drop down");
			WebActionUtil.clickOnElement(ddCountry, "Country drop down", "Unable to click Country drop down");
			WebActionUtil.waitForElement(getCountry(countryName), countryName+" option in Country drop down");
			WebActionUtil.clickOnElement(getCountry(countryName), countryName+" option in Country drop down",
					"Unable to click option in Country drop down");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Country drop down");
			Assert.fail("Unable to perform action on Country drop down");
		}
	}

	/**
	 * Description Method to click on Irem Filter and Enter Claim ID Text Box
	 * 
	 * @author Sajal jain
	 * @param claimID
	 */
	public synchronized void setClaimID(String claimID) {
		try {
			WebActionUtil.waitForElement(btnIremFilter, "Irem Filter button");
			WebActionUtil.clickOnElement(btnIremFilter, "Irem Filter button", "Unable to click on Irem Filter button");
			WebActionUtil.waitForElement(txtClaimIDInIremFilter, "Claim ID text box");
			WebActionUtil.typeText(txtClaimIDInIremFilter, claimID, "Claim ID text box");
			WebActionUtil.extentinfo(claimID+" entered in Claim ID text box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Claim ID text box");
			Assert.fail("Unable to perform action on ClaimID text box");
		}
	}

	/**
	 * Description Method to click on Irem Filter and Enter Employee Name/ sap id
	 * Text Box
	 * 
	 * @author Sajal jain
	 * @param employeeNameOrSapid
	 */
	public synchronized void setEmployeeName(String employeeNameOrSapid) {
		try {
			WebActionUtil.waitForElement(btnIremFilter, "Irem Filter button");
			WebActionUtil.clickOnElement(btnIremFilter, "Irem Filter button", "Unable to click on Irem Filter button");
			WebActionUtil.waitForElement(txtEmployeeName, "Employee Name text box");
			WebActionUtil.typeText(txtEmployeeName, employeeNameOrSapid, "Employee Name text box");
			WebActionUtil.extentinfo(employeeNameOrSapid+" entered in Employee Name text box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Employee Name text box");
			Assert.fail("Unable to perform action on Employee Name text box");
		}
	}

	/**
	 * Description Method to Click on Search Filter Button and type text in Search
	 * Text Box
	 * 
	 * @author Sajal jain
	 * @param search
	 */
	public synchronized void clkSearchFilterBtn(String search) {
		try {
			WebActionUtil.waitForElement(btnSearchFilter, "Search Filter button");
			WebActionUtil.clickOnElement(btnSearchFilter, "Search Filter button",
					"Unable to click on Search Filter button");
			WebActionUtil.typeText(txtSearchBox, search, "Search text box");
			WebActionUtil.extentinfo(search+" entered in Search text box");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Search Filter");
			Assert.fail("Unable to perform action on Search Filter");
		}
	}

	/**
	 * Description Method to validate Previous Records Page is displayed
	 * 
	 * @author Sajal jain
	 * @param expectedPreviousRecordsPageText
	 * @param expectedPreviousRecordsPageUrl
	 * @param expectedPreviousRecordsPageTitle
	 */
	public synchronized void validatePreviousRecordsPage(String expectedPreviousRecordsPageText,
			String expectedPreviousRecordsPageUrl, String expectedPreviousRecordsPageTitle) {
		try {
			WebActionUtil.waitForAngularPageload();
			String actualClaimPageText=null;
			try {
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			actualClaimPageText = driver
					.findElement(By.xpath("//div[@class='pull-left topHeader']/descendant::li[2]")).getText();
			}catch(Exception e) {
				WebActionUtil.error(e.getMessage());
				WebActionUtil.fail("Previous record page is not loaded within 120 seconds");
				Assert.fail("Previous record page is not loaded within 120 seconds");
			}
			String actualUrl = driver.getCurrentUrl();
			String actualTitle = driver.getTitle();

			if (actualClaimPageText.equals(expectedPreviousRecordsPageText)
					&& actualUrl.equals(expectedPreviousRecordsPageUrl)
					&& actualTitle.equals(expectedPreviousRecordsPageTitle)) {
				WebActionUtil.validationinfo(
						"Previous Records Page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle,
						"blue");
				WebActionUtil.info("Previous Records Page is displayed, url= " + driver.getCurrentUrl() + ", title= "
						+ actualTitle);
			} else {
				if (!expectedPreviousRecordsPageTitle.equals(actualTitle)) {
					WebActionUtil.validationinfo("Title not matching, Expected= " + expectedPreviousRecordsPageTitle
							+ ", Actual= " + actualTitle, "red");
					WebActionUtil.error("Title not matching, Expected= " + expectedPreviousRecordsPageTitle
							+ ", Actual= " + actualTitle);
				}
				if (!expectedPreviousRecordsPageUrl.equals(actualUrl)) {
					WebActionUtil.validationinfo(
							"Url not matching, Expected= " + expectedPreviousRecordsPageUrl + ", Actual= " + actualUrl,
							"red");
					WebActionUtil.error(
							"Url not matching, Expected= " + expectedPreviousRecordsPageUrl + ", Actual= " + actualUrl);
				}
				if (!expectedPreviousRecordsPageText.equals(actualClaimPageText)) {
					WebActionUtil.validationinfo("Title not matching, Expected= " + expectedPreviousRecordsPageText
							+ ", Actual= " + actualClaimPageText, "red");
					WebActionUtil.error("Title not matching, Expected= " + expectedPreviousRecordsPageText + ", Actual= "
							+ actualClaimPageText);
				}
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Previous Records Page");
			Assert.fail("Unable to validate Previous Records Page");
		}
	}

	/**
	 * Description Method to validate History Pop up is displayed
	 * 
	 * @author Sajal jain
	 */
	private synchronized void validateHistoryPopup() {
		try {
			WebActionUtil.waitForAngularPageload();
			WebActionUtil.waitForElement(txtHistory, "History Pop up text");
			WebActionUtil.validatePartialText("History", txtHistory, "History Pop up text",
					"History Pop up is displayed", "History Pop up is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("History Pop up is not displayed");
			Assert.fail("History Pop up is not displayed");
		}
	}

	/**
	 * Description:This Method implements to return History Data in History Pop up
	 * 
	 * @author Abhilash
	 * @param claimID
	 * @param roles
	 * @return lstHistoryData
	 */
	private synchronized List<String[]> fetchHistoryData(String claimID, Set<String> roles) {
		List<String[]> lstHistoryData = new ArrayList<>();
		try {
			WebActionUtil.waitForElement(getHistory(claimID), "History icon");
			WebActionUtil.clickOnElement(getHistory(claimID), "History icon", "Unable to click on History icon");

			validateHistoryPopup();

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(popupClaimNumber));
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate History Pop up text");
			Assert.fail("Unable to validate History Pop up text");
		}
		String claimNumber = popupClaimNumber.getText();

		String[] claimNumberSplit = claimNumber.split(":");
		String claimkey = claimNumberSplit[0].trim().replaceAll("[(]", "");
		String claimValue = claimNumberSplit[1].trim().replaceAll("[)]", "");

		lstHistoryData.add(new String[] { claimkey, claimValue });

		Set<String> setUniqueWordsInRoles = uniqueWords(roles);

		List<WebElement> lstHistoryDetails = popupHistoryDetails;

		for (int i = lstHistoryDetails.size() - 1; i >= 0; i--) {

			String strDetail = lstHistoryDetails.get(i).getText();
			strDetail = strDetail.replaceAll("\\s+", " ");
			String[] strHistoryDetailsSplit = strDetail.split(" ");
			String status = strHistoryDetailsSplit[3];
			String role = strHistoryDetailsSplit[5];

			if (setUniqueWordsInRoles.contains(strHistoryDetailsSplit[6])) {
				role = role + " " + strHistoryDetailsSplit[6];
			}

			if (setUniqueWordsInRoles.contains(strHistoryDetailsSplit[7])) {
				role = role + " " + strHistoryDetailsSplit[7];
			}

			String empcode = strDetail.substring(strDetail.indexOf("(") + 1, strDetail.indexOf(")"));
			lstHistoryData.add(new String[] { status, role, empcode });

		}
		return lstHistoryData;
	}

	/**
	 * Description Method to fetch unique words from given Set
	 * 
	 * @author Harsha K.B
	 * @param keys
	 * @return unqwords
	 */
	private static Set<String> uniqueWords(Set<String> keys) {
		Set<String> unqwords = new LinkedHashSet<String>();

		for (String key : keys) {
			String[] words = key.split(" ");

			for (String word : words) {
				unqwords.add(word);
			}
		}

		return unqwords;
	}

	/**
	 * Description Method to Validate Details in History Pop up
	 * 
	 * @author Sajal jain
	 * @param claimID
	 * @param roles
	 * @param expectedHistory
	 */
	public synchronized void validateHistoryPopupDetail(String claimID, Set<String> roles,
			List<String[]> expectedHistory) {
		try {
			List<String[]> actualList = fetchHistoryData(claimID, roles);
			boolean flag = false;
			if (expectedHistory.size() == actualList.size()) {
				for (int i = 0; i < expectedHistory.size(); i++) {
					if (!Arrays.equals(expectedHistory.get(i), actualList.get(i))) {
						flag = true;
						if (flag) {
							WebActionUtil
									.error(Arrays.toString(expectedHistory.get(i)) + " not present in History Pop up");
							WebActionUtil.fail(
									"Expected history is not matching with the actual history in the application");
							Assert.fail("Expected history is not matching with the actual history in the application");
						}
					}
				}
				WebActionUtil.info("Expected history is matching with the actual history in the application");
				WebActionUtil.pass("Expected history is matching with the actual history in the application");
				clkCloseBtnHistoryPopup();
			} else {
				WebActionUtil.error("Expected count and actual count mismatched in History Pop up");
				WebActionUtil.fail("Unable to validate History Pop up text");
				Assert.fail("Unable to validate History Pop up text");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate History Pop up text");
			Assert.fail("Unable to validate History Pop up text");
		}
	}

	/**
	 * Description Method to Click on Close button in History Pop up
	 * 
	 * @author Sajal jain
	 */
	private synchronized void clkCloseBtnHistoryPopup() {
		try {
			WebActionUtil.waitForElement(btnCloseHistoryPopup, "Close button in History Pop up");
			WebActionUtil.clickOnElement(btnCloseHistoryPopup, "Close button in History Pop up",
					"Unable to click Close button in History Pop up");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Close button in History Pop up");
			Assert.fail("Unable to click Close button in History Pop up");
		}
	}

}