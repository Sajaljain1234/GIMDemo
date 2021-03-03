package com.hcl.gim.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.hcl.gim.util.WebActionUtil;

/**
 * Description: This class implements the methods to claim child education
 * allowance
 * 
 * @author Abhilash B
 * 
 */
public class ChildEducationAllowance_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public ChildEducationAllowance_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* One time registration */
	@FindBy(id = "btnOT")
	private WebElement btnOneTimeRegistration;

	/* Tuition fees */
	@FindBy(id = "btnTF")
	private WebElement btnTuitionFees;

	/* child name drop down */
	@FindBy(xpath = "//label[text()='Child Name ']/..//a[text()='Select']")
	private WebElement ddChildname;

	/* select academic year */
	public WebElement SelectAcademicYear(String var) {
		String xpath = "//button[text()='" + var + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* select child name */
	public WebElement SelectChildName(String var) {
		String xpath = "//li[text()='" + var + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* From date */
	@FindBy(id = "fromDate")
	private WebElement txtFromDate;

	/* To date */
	@FindBy(id = "toDate")
	private WebElement txtToDate;

	/* class/grade drop down */
	@FindBy(xpath = "//label[text()='Class/Grade ']/..//a[text()='Select']")
	private WebElement ddClass;

	/* select class/grade */
	public WebElement SelectGrade(String var) {
		String xpath = "//li[text()='" + var + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Claim amount */
	@FindBy(id = "Amount")
	private WebElement txtAmount;

	/* school name */
	@FindBy(id = "SchoolName")
	private WebElement txtSchoolName;

	/* Remarks */
	@FindBy(id = "ChildEducationRemarks")
	private WebElement txtRemarks;

	/* upload button */
	@FindBy(id = "dropzone1")
	private WebElement btnAttachDocument;

	/* add claim Button */
	@FindBy(id = "showClaimListBtn")
	private WebElement btnAddClaim;

	/* Download template link */
	@FindBy(xpath = "//a[text()='Download template  ']")
	private WebElement lnkDownloadTemplate;

	/* initiate claim button */
	@FindBy(id = "btnInitiateClaim")
	private WebElement btnInitiateClaim;

	/* confirm and initiate button */
	@FindBy(xpath = "//button[text()=' CONFIRM AND INITIATE']")
	private WebElement btnConfirmAndInitiate;

	/* confirm and initiate close button */
	@FindBy(xpath = "//h4[text()='Please Confirm']/..//button[@class='close icon-close']")
	private WebElement btnConfirmAndInitiateclose;

	/* added request actions button */
	@FindBy(xpath = "//a[@class='popoverDataSmall iRem-more']")
	private WebElement btnAddedRequestActions;

	/* added request edit button */
	@FindBy(xpath = "//i[@class='iRem-edit']/parent::button")
	private WebElement btnEditRequest;

	/* added request delete button */
	@FindBy(xpath = "//i[@class='iRem-delete']/parent::button")
	private WebElement btnDeleteRequest;

	/* confirm yes button for delete */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYes;

	/* Requests added */
	@FindBy(xpath = "//h3[contains(text(),'Requests added')]")
	private WebElement txtRequestAdded;

	/* child education Allowance */
	@FindBy(xpath = "//h3[text()='Claim request for Child Education Allowance ']")
	private WebElement txtChildEducationAllowance;

	/* Claim request has been successfully initiated message */
	@FindBy(xpath = "//div[text()='Claim request has been successfully initiated.']")
	private WebElement txtclaimInitiatedConfirmation;

	/* Update claim Button */
	@FindBy(id = "showClaimListBtn")
	private WebElement btnUpdateClaim;

	/* Record is deleted message */
	@FindBy(xpath = "(//div[text()='Record deleted successfully.'])[2]")
	private WebElement txtRecordDeletedConfirmation;

	/* Exception PopUp */
	@FindBy(xpath = "//p[contains(text(),'Age should be between 3 and 18 year. If you submit then it will go to L2 head.')]")
	private WebElement txtExceptionPopUp;

	/* Exception Yes Button */
	@FindBy(xpath = "//p[contains(text(),'Age should be')]/parent::div/descendant::button[@class='confirm btn btn-lg btn primary-button']")
	private WebElement btnExceptionYes;

	/* Data saved successfully text */
	@FindBy(xpath = "//div[text()='Data saved successfully']")
	private WebElement txtDataSavedSuccessfully;

	/* Spinner */
	@FindBy(xpath = "//div[@class='loader2 material-spinner']")
	private WebElement spinner;

	/* Exception Pop up */
	@FindBy(xpath = "//p[contains(text(),'One time registration is already payout. If you submit then it will go to L2 head.')]")
	private WebElement txtExceptionPopUpOneTime;

	/* Exception Yes Button */
	@FindBy(xpath = "//p[contains(text(),'One time registration is already payout. If you submit then it will go to L2 head.')]/parent::div/descendant::button[@class='confirm btn btn-lg btn primary-button']")
	private WebElement btnExceptionYesForOneTime;

	/**
	 * Description: This method implements add one time registration claim
	 * 
	 * @author:Abhilash B
	 * @param :ChildName
	 * @param :Academic Year
	 * @param :From Date
	 * @param :To Date
	 * @param :Class/Grade
	 * @param :Amount
	 * @param :School name
	 * @param :filelocation
	 * @param :Remarks
	 */
	public synchronized void applyoneTimeRegistration(String childName, String academicYear, String fromDate,
			String toDate, String grade, String amount, String schoolName, String remarks, String filelocation) {
		try {
			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"blue");
			WebActionUtil.validateAttribute1(btnOneTimeRegistration, "class", "btn active", "one time registration",
					"OneTimeRegistration is selected by default", "OneTimeRegistration is not selected by default",
					"blue");
			WebActionUtil.clickOnElement(ddChildname, "Child Name dropdown", "Unable to click on Child Name dropdown");
			WebActionUtil.clickOnElement(SelectChildName(childName), childName, "Unable to select ChildName");
			WebActionUtil.validateAttribute1(SelectChildName(childName), "class", "selected", "ChildName",
					childName + " is Selected as childname", childName + " is not Selected as childname", "blue");
			WebActionUtil.poll(1000);
			WebActionUtil.clickOnElement(SelectAcademicYear(academicYear), academicYear,
					"Unable to select Academic Year");
			WebActionUtil.validateAttribute1(SelectAcademicYear(academicYear), "class", "btn active", "Academic year",
					academicYear + " is selected as Academic Period ",
					academicYear + " is not selected as Academic Period ", "blue");
			WebActionUtil.selectGIM_CalendarDate("fromDate", fromDate, "From Date");
			WebActionUtil.validateEnteredValue1(fromDate, WebActionUtil.getTextUsingJS("fromDate"), "From Date",
					fromDate + " is selected as From Date", fromDate + " is not selected as From Date", "blue");
			WebActionUtil.selectGIM_CalendarDate("toDate", toDate, "To Date");
			WebActionUtil.validateEnteredValue1(toDate, WebActionUtil.getTextUsingJS("toDate"), "To Date",
					toDate + " is selected as To Date", toDate + " is selected as To Date", "blue");
			WebActionUtil.clickOnElement(ddClass, "class/grade drop down", "Unable to click on class/grade dropdown");
			WebActionUtil.clickOnElement(SelectGrade(grade), grade, "Unable to select class/grade");
			WebActionUtil.validateAttribute1(SelectGrade(grade), "class", "selected", "class/Grade",
					grade + " is selected as class/grade", grade + " is not selected as class/grade", "blue");
			WebActionUtil.typeText(txtAmount, amount, "Amount");
			WebActionUtil.validateEnteredValue1(amount, WebActionUtil.getTextUsingJS("Amount"), "Amount",
					amount + " is entered in Amount", amount + " is not entered in Amount", "blue");
			WebActionUtil.typeText(txtSchoolName, schoolName, "School name");
			WebActionUtil.validateEnteredValue1(schoolName, WebActionUtil.getTextUsingJS("SchoolName"), "School Name",
					schoolName + " is entered in School Name", schoolName + " is not entered in School Name", "blue");
			WebActionUtil.typeText(txtRemarks, remarks, "Remarks");
			WebActionUtil.validateEnteredValue1(remarks, WebActionUtil.getTextUsingJS("ChildEducationRemarks"),
					"Remarks", remarks + " is entered in remarks ", remarks + " is not entered in remarks ", "blue");
			WebActionUtil.clickOnElement(lnkDownloadTemplate, "Download Template",
					"Unable to click on Download Template");
			String downloadedDocumentName = WebActionUtil
					.getDownloadedDocumentName(System.getProperty("user.home") + "/Downloads", "pdf");
			WebActionUtil.validateDownload(downloadedDocumentName, "Downloaded document is in pdf Format",
					"Downloaded document is not in pdf Format", "blue");
			WebActionUtil.clickOnElement(btnAttachDocument, "Attach document button",
					"Unable to click on Attach Document button");
			WebActionUtil.upload(filelocation);
			WebActionUtil.validateAttribute1(btnAttachDocument, "class", "dz-clickable dropzone dz-started",
					"Attach document", "Document is uploaded", "Document is not uploaded", "blue");
			WebActionUtil.clickOnElement(btnAddClaim, "Add Claim button", "Unable to click on Add Claim");
			WebActionUtil.validateisElementDisplayed(txtRequestAdded, "Request Added",
					"Adding claim of One Time Registration is successfull",
					"Failed to add Claim of One Time Registration", "green");
			WebActionUtil.validateisElementDisplayed(btnInitiateClaim, "Initiate claim",
					"Initiate claim button is displayed", "Initiate claim button is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to add claim of One Time Registration type ");
			WebActionUtil.error("Unable to add claim of One Time Registration type ");
			Assert.fail("Unable to add claim of One Time Registration type ");
		}

	}

	/**
	 * Description: This method implements add tuition fees claim
	 * 
	 * @author:Abhilash B
	 * @param childName
	 * @param academicYear
	 * @param fromDate
	 * @param toDate
	 * @param grade
	 * @param amount
	 * @param schoolName
	 * @param remarks
	 * @param filelocation
	 */
	public synchronized void applytuitionFees(String childName, String academicYear, String fromDate, String toDate,
			String grade, String amount, String schoolName, String remarks, String filelocation) {
		try {
			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"blue");
			WebActionUtil.clickOnElement(btnTuitionFees, "Tution Fees", "Unable click on Tution fees");
			WebActionUtil.validateAttribute1(btnTuitionFees, "class", "btn active", "Tuition Fees",
					"Tuition Fees is selected", "Tuition Fees is not selected", "blue");
			WebActionUtil.clickOnElement(ddChildname, "Child name dropdown", "Unable to click on child name dropdown");
			WebActionUtil.clickOnElement(SelectChildName(childName), childName, "Unable to select ChildName");
			WebActionUtil.validateAttribute1(SelectChildName(childName), "class", "selected", "ChildName",
					childName + " is Selected as childname", childName + " is not Selected as childname", "blue");
			WebActionUtil.poll(1000);
			WebActionUtil.clickOnElement(SelectAcademicYear(academicYear), academicYear,
					"Unable to select Academic Year");
			WebActionUtil.validateAttribute1(SelectAcademicYear(academicYear), "class", "btn active", "Academic year",
					academicYear + " is selected as Academic Period ",
					academicYear + " is not selected as Academic Period ", "blue");
			WebActionUtil.selectGIM_CalendarDate("fromDate", fromDate, "From Date");
			WebActionUtil.validateEnteredValue1(fromDate, WebActionUtil.getTextUsingJS("fromDate"), "From Date",
					fromDate + " is selected as From Date", fromDate + " is not selected as From Date", "blue");
			WebActionUtil.selectGIM_CalendarDate("toDate", toDate, "To Date");
			WebActionUtil.validateEnteredValue1(toDate, WebActionUtil.getTextUsingJS("toDate"), "To Date",
					toDate + " is selected as To Date", toDate + " is selected as To Date", "blue");
			WebActionUtil.clickOnElement(ddClass, "class/grade drop down", "Unable to click on class/grade dropdown");
			WebActionUtil.clickOnElement(SelectGrade(grade), grade, "Unable to select class/grade");
			WebActionUtil.validateAttribute1(SelectGrade(grade), "class", "selected", "class/Grade",
					grade + " is selected as class/grade", grade + " is not selected as class/grade", "blue");
			WebActionUtil.typeText(txtAmount, amount, "Amount");
			WebActionUtil.validateEnteredValue1(amount, WebActionUtil.getTextUsingJS("Amount"), "Amount",
					amount + " is entered in Amount", amount + " is not entered in Amount", "blue");
			WebActionUtil.typeText(txtSchoolName, schoolName, "School name");
			WebActionUtil.validateEnteredValue1(schoolName, WebActionUtil.getTextUsingJS("SchoolName"), "School Name",
					schoolName + " is entered in School Name", schoolName + " is not entered in School Name", "blue");
			WebActionUtil.typeText(txtRemarks, remarks, "Remarks");
			WebActionUtil.validateEnteredValue1(remarks, WebActionUtil.getTextUsingJS("ChildEducationRemarks"),
					"Remarks", remarks + " is entered in remarks ", remarks + " is not entered in remarks ", "blue");
			WebActionUtil.clickOnElement(lnkDownloadTemplate, "Download Template",
					"Unable to click on Download Template");
			String downloadedDocumentName = WebActionUtil
					.getDownloadedDocumentName(System.getProperty("user.home") + "/Downloads", "pdf");
			WebActionUtil.validateDownload(downloadedDocumentName, "Downloaded document is in pdf Format",
					"Downloaded document is not in pdf Format", "blue");
			WebActionUtil.clickOnElement(btnAttachDocument, "Attach document Button",
					"Unable to click on Attach Document Button");
			WebActionUtil.upload(filelocation);
			WebActionUtil.validateAttribute1(btnAttachDocument, "class", "dz-clickable dropzone dz-started",
					"Attach document", "Document is uploaded", "Document is not uploaded", "blue");
			WebActionUtil.clickOnElement(btnAddClaim, "Add Claim button", "Unable to click on Add Claim");
			WebActionUtil.validateisElementDisplayed(txtRequestAdded, "Request Added",
					"Adding claim of Tuition Fees is successfull", "Failed to add Claim of Tuition Fees", "green");
			WebActionUtil.validateisElementDisplayed(btnInitiateClaim, "Initiate claim",
					"Initiate claim button is displayed", "Initiate claim button is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to add claim of Tuition Fees type ");
			WebActionUtil.error("Unable to add claim of Tuition Fees type ");
			Assert.fail("Unable to add claim of Tuition Fees type ");
		}

	}

	/**
	 * Description: This method implements initiate claim
	 * 
	 * @author:Abhilash B
	 *
	 **/
	public synchronized void clickInitiateClaim() {

		try {
			WebActionUtil.waitForElement(btnInitiateClaim, "Initiate claim");
			WebActionUtil.clickOnElement(btnInitiateClaim, "Initiate claim", "Unable to click on initiate claim");
			WebActionUtil.validateisElementDisplayed(btnConfirmAndInitiate, "Confirm and Initiate pop up",
					"Confirm and initiate pop up is displayed", "Confirm and initiate pop up is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on Initiate claim");
			Assert.fail("Unable to click on Initiate claim");
		}
	}

	/**
	 * Description: This method implements to Confirm and Initiate claim
	 * 
	 * @author:Abhilash B
	 **/
	public synchronized void clickConfirmAndInitiate() {
		try {
			WebActionUtil.waitForElement(btnConfirmAndInitiate, "Confirm and Initiate");
			WebActionUtil.clickOnElement(btnConfirmAndInitiate, "Confirm and Initiate",
					"Unable to click on Confirm and Initiate");
			WebActionUtil.validateisElementDisplayed(txtclaimInitiatedConfirmation,
					"Claim request has been successfully initiated pop up",
					"Claim request has been successfully initiated pop up is displayed",
					"Claim request has been successfully initiated pop up is not displayed", "green");
			
			try {
				while (true) {
					if (spinner.isDisplayed()) {
						new WebDriverWait(driver, 6).until(ExpectedConditions.invisibilityOf(spinner));
						break;
					}
				}
			} catch (Exception e) {

			}
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on confirm and initiate ");
			Assert.fail("Unable to click on confirm and initiate ");
		}

	}

	/**
	 * Description: This method implements to edit added claim request
	 * 
	 * @author:Abhilash B
	 **/
	public synchronized void clickEditRequest() {

		try {
			WebActionUtil.clickOnElement(btnAddedRequestActions, "Added Request Actions",
					"Unable to click on added request actions button");
			WebActionUtil.validateisElementDisplayed(btnEditRequest, "Edit Request", "Edit request button is displayed",
					"Edit request button is not displayed", "blue");
			WebActionUtil.validateisElementDisplayed(btnDeleteRequest, "Delete Request",
					"Delete request button is displayed", "Delete request button is not displayed", "blue");
			WebActionUtil.clickOnElement(btnEditRequest, "Edit Request", "Unable to click on edit request button");
			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"green");
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on Edit request button ");
			Assert.fail("Unable to click on Edit request button");
		}
	}

	/**
	 * Description: This method implements to delete added claim request
	 * 
	 * @author:Abhilash B
	 **/
	public synchronized void clickDeleteRequest() {

		try {
			WebActionUtil.clickOnElement(btnAddedRequestActions, "Added Request Actions",
					"Unable to click on added request actions button");
			WebActionUtil.validateisElementDisplayed(btnEditRequest, "Edit Request", "Edit request button is displayed",
					"Edit request button is not displayed", "blue");
			WebActionUtil.validateisElementDisplayed(btnDeleteRequest, "Delete Request",
					"Delete request button is displayed", "Delete request button is not displayed", "blue");
			WebActionUtil.clickOnElement(btnDeleteRequest, "Delete Request",
					"Unable to click on Delete request button");
			WebActionUtil.clickOnElement(btnYes, "yes button",
					"Failed to click on yes button of confirm delete request");
			WebActionUtil.validateisElementDisplayed(txtRecordDeletedConfirmation,
					"Record Deleted confirmation message", "Record Deleted successfully", "Failed to delete the Record",
					"green");
		} catch (Exception e) {
			WebActionUtil.error("Failed to click on delete request button");
			Assert.fail("Failed to click on delete  request button");
		}
	}

	/**
	 * Description: This method implements to Update claim request
	 * 
	 * @author Abhilash B
	 * @param amount
	 **/
	public synchronized void updateClaimRequest(String amount) {
		try {
			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"blue");
			WebActionUtil.clearText(txtAmount, "Amount");
			WebActionUtil.typeText(txtAmount, amount, "Amount");
			WebActionUtil.validateEnteredValue1(amount, WebActionUtil.getTextUsingJS("Amount"), "Amount text box",
					amount + " is entered in Amount text box ", amount + " is not entered in Amount text box", "blue");
			WebActionUtil.clickOnElement(btnUpdateClaim, "Update claim button",
					"Failed to click on Update claim button");
			WebActionUtil.validateisElementDisplayed(txtDataSavedSuccessfully, "Data saved successfully",
					"Data saved successfully pop up is displayed", "Data saved successfully pop up is not displayed",
					"green");
			if (spinner.isDisplayed()) {
				try {
					new WebDriverWait(driver, 6).until(ExpectedConditions.invisibilityOf(spinner));
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {
			WebActionUtil.error("Failed to Update claim ");
			Assert.fail("Failed to  Update claim ");
		}

	}

	/**
	 * Description: This method implements to Update claim request
	 * 
	 * @author:Abhilash B
	 * @param amount
	 **/
	public synchronized void updateClaimRequestExceptionCase(String amount) {
		try {
			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"blue");
			WebActionUtil.clearText(txtAmount, "Amount text box");
			WebActionUtil.typeText(txtAmount, amount, "Amount text box");
			WebActionUtil.validateEnteredValue1(amount, WebActionUtil.getTextUsingJS("Amount"), "Amount text box",
					amount + " is entered in Amount text box", amount + " is not entered in Amount text box", "blue");
			WebActionUtil.clickOnElement(btnUpdateClaim, "Update Claim button",
					"Failed to click on Update Claim button");

		} catch (Exception e) {
			WebActionUtil.error("Failed to Update claim");
			Assert.fail("Failed to Update claim");
		}

	}

	/**
	 * Description: This method implements to click on Exception Yes Button.
	 * 
	 * @author:Abhilash B
	 **/
	public synchronized void clkExceptionYesButton() {

		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(txtExceptionPopUp, "Exception PopUp");
			WebActionUtil.validateisElementDisplayed(txtExceptionPopUp, "Exception Pop Up",
					"Age should be between 3 and 18 year. If you submit then it will go to L2 head. Popup is displayed",
					"Age should be between 3 and 18 year. If you submit then it will go to L2 head. Popup is not displayed",
					"blue");
			WebActionUtil.waitForElement(btnExceptionYes, "Exception Yes button");
			WebActionUtil.clickOnElement(btnExceptionYes, "Exception Yes button",
					"Unable to click on Exception Yes button");
			WebActionUtil.waitForElement(btnInitiateClaim, "Initiate Claim button");
			WebActionUtil.validateisElementDisplayed(btnInitiateClaim, "Initiate Claim button",
					"Claim is added to the Claim Dashboard", "Claim is not added to the Claim Dashboard", "blue");
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on Exception Yes button");
			Assert.fail("Unable to click on Exception Yes button");
		}
	}

	/**
	 * Description: This method implements to Update claim request
	 * 
	 * @author Abhilash B
	 * @param amount
	 **/
	public synchronized void updateClaimRequestExceptionalCase(String amount) {

		try {

			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"blue");
			WebActionUtil.clearText(txtAmount, "Amount");
			WebActionUtil.typeText(txtAmount, amount, "Amount");
			WebActionUtil.validateEnteredValue1(amount, WebActionUtil.getTextUsingJS("Amount"), "Amount text box",
					amount + " is entered in Amount text box", amount + " is not entered in Amount text box", "blue");
			WebActionUtil.clickOnElement(btnUpdateClaim, "Update Claim button",
					"Failed to click on Update Claim button");

		} catch (Exception e) {
			WebActionUtil.error("Failed to Update Claim");
			Assert.fail("Failed to Update Claim");
		}

	}

	/**
	 * Description: This method implements to click on Exception Yes Button.
	 * 
	 * @author:Abhilash B
	 **/
	public synchronized void clkExceptionYesButtonAfterEdit() {

		try {
			WebActionUtil.poll(1000);
			WebActionUtil.waitForElement(txtExceptionPopUp, "Exception PopUp");
			WebActionUtil.validateisElementDisplayed(txtExceptionPopUp, "Exception PopUp",
					"Age should be between 3 and 18 year. If you submit then it will go to L2 head Popup is displayed",
					"Age should be between 3 and 18 year. If you submit then it will go to L2 head Popup is not displayed",
					"blue");
			WebActionUtil.waitForElement(btnExceptionYes, "Exception Yes button");
			WebActionUtil.clickOnElement(btnExceptionYes, "Exception Yes button",
					"Unable to click on Exception Yes button");
			WebActionUtil.validateisElementDisplayed(txtDataSavedSuccessfully, "Data saved successfully",
					"Data saved successfully pop up is displayed", "Data saved successfully pop up is not displayed",
					"green");
			if (spinner.isDisplayed()) {
				try {
					new WebDriverWait(driver, 6).until(ExpectedConditions.invisibilityOf(spinner));
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on Exception Yes button ");
			Assert.fail("Unable to click on Exception Yes button");
		}
	}

	/**
	 * Description: This method implements add one time registration claim
	 * Exceptional case
	 * 
	 * @author:Abhilash B
	 * @param :ChildName
	 * @param :Academic Year
	 * @param :From Date
	 * @param :To Date
	 * @param :Class/Grade
	 * @param :Amount
	 * @param :School name
	 * @param :filelocation
	 * @param :Remarks
	 */

	public synchronized void applyoneTimeRegistrationExceptionalCase(String childName, String academicYear,
			String fromDate, String toDate, String grade, String amount, String schoolName, String remarks,
			String filelocation) {

		try {
			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"blue");
			WebActionUtil.validateAttribute1(btnOneTimeRegistration, "class", "btn active", "one time registration",
					"OneTimeRegistration is selected by default", "OneTimeRegistration is not selected by default",
					"blue");
			WebActionUtil.clickOnElement(ddChildname, "Child name dropdown", "Unable to click on child name dropdown");
			WebActionUtil.clickOnElement(SelectChildName(childName), childName, "Unable to select ChildName");
			WebActionUtil.validateAttribute1(SelectChildName(childName), "class", "selected", "ChildName",
					childName + " is Selected as childname", childName + " is not Selected as childname", "blue");
			WebActionUtil.poll(1000);
			WebActionUtil.clickOnElement(SelectAcademicYear(academicYear), academicYear,
					"Unable to select Academic Year");
			WebActionUtil.validateAttribute1(SelectAcademicYear(academicYear), "class", "btn active", "Academic year",
					academicYear + " is selected as Academic Period ",
					academicYear + " is not selected as Academic Period ", "blue");
			WebActionUtil.selectGIM_CalendarDate("fromDate", fromDate, "From Date");
			WebActionUtil.validateEnteredValue1(fromDate, WebActionUtil.getTextUsingJS("fromDate"), "From Date",
					fromDate + " is selected as From Date", fromDate + " is not selected as From Date", "blue");
			WebActionUtil.selectGIM_CalendarDate("toDate", toDate, "To Date");
			WebActionUtil.validateEnteredValue1(toDate, WebActionUtil.getTextUsingJS("toDate"), "To Date",
					toDate + " is selected as To Date", toDate + " is selected as To Date", "blue");
			WebActionUtil.clickOnElement(ddClass, "class/grade drop down", "Unable to click on class/grade dropdown");
			WebActionUtil.clickOnElement(SelectGrade(grade), grade, "Unable to select class/grade");
			WebActionUtil.validateAttribute1(SelectGrade(grade), "class", "selected", "class/Grade",
					grade + " is selected as class/grade", grade + " is not selected as class/grade", "blue");
			WebActionUtil.typeText(txtAmount, amount, "Amount");
			WebActionUtil.validateEnteredValue1(amount, WebActionUtil.getTextUsingJS("Amount"), "Amount",
					amount + " is entered in Amount", amount + " is not entered in Amount", "blue");
			WebActionUtil.typeText(txtSchoolName, schoolName, "School name");
			WebActionUtil.validateEnteredValue1(schoolName, WebActionUtil.getTextUsingJS("SchoolName"), "School Name",
					schoolName + " is entered in School Name", schoolName + " is not entered in School Name", "blue");
			WebActionUtil.typeText(txtRemarks, remarks, "Remarks");
			WebActionUtil.validateEnteredValue1(remarks, WebActionUtil.getTextUsingJS("ChildEducationRemarks"),
					"Remarks", remarks + " is entered in remarks", remarks + " is not entered in remarks ", "blue");
			WebActionUtil.clickOnElement(lnkDownloadTemplate, "Download Template",
					"Unable to click on Download Template");
			String downloadedDocumentName = WebActionUtil
					.getDownloadedDocumentName(System.getProperty("user.home") + "/Downloads", "pdf");
			WebActionUtil.validateDownload(downloadedDocumentName, "Downloaded document is in pdf Format",
					"Downloaded document is not in pdf Format", "blue");
			WebActionUtil.clickOnElement(btnAttachDocument, "Attach document button",
					"Unable to click on Attach Document button");
			WebActionUtil.upload(filelocation);
			WebActionUtil.validateAttribute1(btnAttachDocument, "class", "dz-clickable dropzone dz-started",
					"Attach document", "Document is uploaded", "Document is not uploaded", "blue");
			WebActionUtil.clickOnElement(btnAddClaim, "Add Claim button", "Unable to click on Add Claim");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to add claim of One Time Registration type");
			WebActionUtil.error("Unable to add claim of One Time Registration type");
			Assert.fail("Unable to add claim of One Time Registration type ");
		}

	}

	/**
	 * Description: This method implements add tuition fees claim Exceptional case
	 * 
	 * @author:Abhilash B
	 * @param childName
	 * @param academicYear
	 * @param fromDate
	 * @param toDate
	 * @param grade
	 * @param amount
	 * @param schoolName
	 * @param remarks
	 * @param filelocation
	 */
	public synchronized void applytuitionFeesExceptionalCase(String childName, String academicYear, String fromDate,
			String toDate, String grade, String amount, String schoolName, String remarks, String filelocation) {
		try {
			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"blue");
			WebActionUtil.clickOnElement(btnTuitionFees, "Tution Fees", "Unable click on Tution fees");
			WebActionUtil.validateAttribute1(btnTuitionFees, "class", "btn active", "Tuition Fees",
					"Tuition Fees is selected", "Tuition Fees is not selected", "blue");
			WebActionUtil.clickOnElement(ddChildname, "Child name dropdown", "Unable to click on child name dropdown");
			WebActionUtil.clickOnElement(SelectChildName(childName), childName, "Unable to select ChildName");
			WebActionUtil.validateAttribute1(SelectChildName(childName), "class", "selected", "ChildName",
					childName + " is Selected as childname", childName + " is not Selected as childname", "blue");
			WebActionUtil.poll(1000);
			WebActionUtil.clickOnElement(SelectAcademicYear(academicYear), academicYear,
					"Unable to select Academic Year");
			
			WebActionUtil.validateAttribute1(SelectAcademicYear(academicYear), "class", "btn active", "Academic year",
					academicYear + " is selected as Academic Period ",
					academicYear + " is not selected as Academic Period ", "blue");
			WebActionUtil.selectGIM_CalendarDate("fromDate", fromDate, "From Date");
			WebActionUtil.validateEnteredValue1(fromDate, WebActionUtil.getTextUsingJS("fromDate"), "From Date",
					fromDate + " is selected as From Date", fromDate + " is not selected as From Date", "blue");
			WebActionUtil.selectGIM_CalendarDate("toDate", toDate, "To Date");
			WebActionUtil.validateEnteredValue1(toDate, WebActionUtil.getTextUsingJS("toDate"), "To Date",
					toDate + " is selected as To Date", toDate + " is selected as To Date", "blue");
			WebActionUtil.clickOnElement(ddClass, "class/grade drop down", "Unable to click on class/grade dropdown");
			WebActionUtil.clickOnElement(SelectGrade(grade), grade, "Unable to select class/grade");
			WebActionUtil.validateAttribute1(SelectGrade(grade), "class", "selected", "class/Grade",
					grade + " is selected as class/grade", grade + " is not selected as class/grade", "blue");
			WebActionUtil.typeText(txtAmount, amount, "Amount");
			WebActionUtil.validateEnteredValue1(amount, WebActionUtil.getTextUsingJS("Amount"), "Amount",
					amount + " is entered in Amount", amount + " is not entered in Amount", "blue");
			WebActionUtil.typeText(txtSchoolName, schoolName, "School name");
			WebActionUtil.validateEnteredValue1(schoolName, WebActionUtil.getTextUsingJS("SchoolName"), "School Name",
					schoolName + " is entered in School Name", schoolName + " is not entered in School Name", "blue");
			WebActionUtil.typeText(txtRemarks, remarks, "Remarks");
			WebActionUtil.validateEnteredValue1(remarks, WebActionUtil.getTextUsingJS("ChildEducationRemarks"),
					"Remarks", remarks + " is entered in remarks ", remarks + " is not entered in remarks ", "blue");
			WebActionUtil.clickOnElement(lnkDownloadTemplate, "Download Template",
					"Unable to click on Download Template");
			String downloadedDocumentName = WebActionUtil
					.getDownloadedDocumentName(System.getProperty("user.home") + "/Downloads", "pdf");
			WebActionUtil.validateDownload(downloadedDocumentName, "Downloaded document is in pdf Format",
					"Downloaded document is not in pdf Format", "blue");
			WebActionUtil.clickOnElement(btnAttachDocument, "Attach document button",
					"Unable to click on Attach Document button");
			WebActionUtil.upload(filelocation);
			WebActionUtil.validateAttribute1(btnAttachDocument, "class", "dz-clickable dropzone dz-started",
					"Attach document", "Document is uploaded", "Document is not uploaded", "blue");
			WebActionUtil.clickOnElement(btnAddClaim, "Add Claim button", "Unable to click on Add Claim");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to add claim of Tuition Fees type");
			WebActionUtil.error("Unable to add claim of Tuition Fees type");
			Assert.fail("Unable to add claim of Tuition Fees type");
		}

	}

	/**
	 * Description: This method implements to Update claim request
	 * 
	 * @author:Abhilash B 
	 * @param amount
	 **/
	public synchronized void updateClaimRequestException(String amount) {
		try {
			WebActionUtil.validateisElementDisplayed(txtChildEducationAllowance, "Child Education Allowance",
					"Child Education Allowance Page is displayed", "Child Education Allowance Page is not displayed",
					"blue");
			WebActionUtil.clearText(txtAmount, "Amount");
			WebActionUtil.typeText(txtAmount, amount, "Amount");
			WebActionUtil.validateEnteredValue1(amount, WebActionUtil.getTextUsingJS("Amount"), "Amount",
					amount + " is entered in Amount text box", amount + " is not entered in Amount text box", "blue");
			WebActionUtil.clickOnElement(btnUpdateClaim, "Update claim button",
					"Failed to click on Update claim button");
			if (spinner.isDisplayed()) {
				try {
					new WebDriverWait(driver, 6).until(ExpectedConditions.invisibilityOf(spinner));
				} catch (Exception e) {

				}
			}
		} catch (Exception e) {
			WebActionUtil.error("Failed to Update claim ");
			Assert.fail("Failed to  Update claim ");
		}

	}

	/**
	 * Description: This method implements to click on Exception Yes Button.
	 * 
	 * @author:Abhilash B
	 **/
	public synchronized void clkExceptionYesButtonForOneTimeUpdate() {

		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(txtExceptionPopUpOneTime, "Exception PopUp");
			WebActionUtil.validateisElementDisplayed(txtExceptionPopUpOneTime, "Exception PopUp",
					"One time registration is already payout. If you submit then it will go to L2 head. Popup is displayed",
					"One time registration is already payout. If you submit then it will go to L2 head. Popup is not displayed",
					"blue");
			WebActionUtil.waitForElement(btnExceptionYesForOneTime, "Exception Yes Button");
			WebActionUtil.clickOnElement(btnExceptionYesForOneTime, "Exception Yes Button",
					"Unable to click on Exception Yes Button");
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on Exception Yes Button ");
			Assert.fail("Unable to click on Exception Yes Button");
		}
	}

	/**
	 * Description: This method implements to click on Exception Yes Button.
	 * 
	 * @author:Abhilash B
	 **/
	public synchronized void clkExceptionYesButtonForOneTime() {

		try {
            WebActionUtil.poll(1000);
			WebActionUtil.waitForElement(txtExceptionPopUpOneTime, "Exception PopUp");
			WebActionUtil.validateisElementDisplayed(txtExceptionPopUpOneTime, "Exception PopUp",
					"One time registration is already payout. If you submit then it will go to L2 head. Popup is displayed",
					"One time registration is already payout. If you submit then it will go to L2 head. Popup is not displayed",
					"blue");
			WebActionUtil.waitForElement(btnExceptionYesForOneTime, "Exception Yes button");
			WebActionUtil.clickOnElement(btnExceptionYesForOneTime, "Exception Yes button",
					"Unable to click on Exception Yes button");
			WebActionUtil.waitForElement(btnInitiateClaim, "Initiate Claim button");
			WebActionUtil.validateisElementDisplayed(btnInitiateClaim, "Initiate Claim button",
					"Claim is added to the Claim Dashboard", "Claim is not added to the Claim Dashboard", "blue");
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on Exception Yes button ");
			Assert.fail("Unable to click on Exception Yes button");
		}
	}

}