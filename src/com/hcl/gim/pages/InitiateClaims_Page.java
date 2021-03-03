package com.hcl.gim.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.util.WebActionUtil;

/**
 * Description: This class implements the methods for InitiateClaims
 * 
 * @author Suganthini, Vikas
 * 
 */
public class InitiateClaims_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public InitiateClaims_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Refer Remark text field */
	@FindBy(id = "referRemark")
	private WebElement txtReferRemark;

	/* Cancel Submit Button */
	@FindBy(id = "refered")
	private WebElement btnCacelSubmit;

	/* Close Button */
	@FindBy(xpath = "//h4[text()='Cancel ']/preceding-sibling::button[@class='close icon-close']")
	private WebElement btnReferClose;

	/* Approve Text Area */
	@FindBy(id = "approveRemark")
	private WebElement txtApproveRemark;

	/* Upload Link */
	@FindBy(xpath = "//div[@class='pull-left text']")
	private WebElement lnkUpload;

	/* Approve Submit Button */
	@FindBy(id = "AppClaims")
	private WebElement btnApproveSubmit;

	/* Yes Button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYes;

	/* No Button */
	@FindBy(xpath = "//button[text()='NO']")
	private WebElement btnNo;

	/* Approve Close Button */
	@FindBy(xpath = "//h4[text()='Submit ']/preceding-sibling::button[@class='close icon-close']")
	private WebElement btnApproveClose;

	/* Bulk Submit Button */
	@FindBy(xpath = "//button[contains(text(),'BULK SUBMIT')]")
	private WebElement btnBulkSubmit;

	/* Bulk ClaimID Text Area */
	@FindBy(xpath = "//textarea[@id='ClaimIds' and @name='ClaimIds']")
	private WebElement txtBulkClaimId;

	/* Bulk Attachment */
	@FindBy(xpath = "//label[text()='Attach Documents ']/parent::div/descendant::div[@class='pull-left text']")
	private WebElement lnkBulkAttachment;

	/* Bulk Remarks Text Area */
	@FindBy(xpath = "//textarea[@id='txtareaBulkRemarks' and @name='ApproveRemarks']")
	private WebElement txtBulkRemarks;

	/* Bulk Submit Approval Button */
	@FindBy(xpath = "//button[@id='AppClaimsBulkAprvl' and contains(text(),'SUBMIT')]")
	private WebElement btnBulkApprvlSubmit;

	/* Bulk Cancel Button */
	@FindBy(xpath = "//button[text()='Cancel' and @class='btn secondry-button ReferID']")
	private WebElement btnBulkCancel;

	/* Bulk Close Button */
	@FindBy(xpath = "//h4[contains(text(),'Bulk Submit')]/preceding-sibling::button[@class='close icon-close']")
	private WebElement btnBulkClose;

	/* Search Image */
	@FindBy(xpath = "//a[@class='searhFilter']/i")
	private WebElement btnSearchFilter;

	/* Search Text Field */
	@FindBy(id = "Search Image")
	private WebElement txtSearchbox;

	/* Filter Image */
	@FindBy(xpath = "//a/i[@class='iRem-filter']")
	private WebElement btniremFilter;

	/* All Claim Check Box */
	@FindBy(xpath = "//input[@id='abc']/following-sibling::span[@class='checkbox-custom']")
	private WebElement cbSelectAllClaims;

	/* Back Button */
	@FindBy(xpath = "//button[text()='BACK']")
	private WebElement btnBack;

	/* Submit Button */
	@FindBy(xpath = "//button[@class='btn primary-button actBtn']")
	private WebElement btnSubmit;

	/* Cancel Button */
	@FindBy(xpath = "//button[contains(text(),'CANCEL') and @class='btn secondry-button actBtn']")
	private WebElement btnCancel;

	/* Select Page Number */
	@FindBy(name = "myTable_length")
	private WebElement ddPageSize;

	/* NextArrow Button */
	@FindBy(id = "myTable_next")
	private WebElement btnNextArrow;

	/* PreviousArrow Button */
	@FindBy(id = "myTable_previous")
	private WebElement btnPreviousArrow;

	/* Employee Name Text Field */
	@FindBy(xpath = "//input[@class='form-control employeeIdName']")
	private WebElement txtEmpolyeeName;

	/* CliamID Text Field */
	@FindBy(xpath = "//input[@class='form-control claimId numberAndCommaOnly']")
	private WebElement txtClaimId;

	/* Country Text Field */
	@FindBy(xpath = "//label[text()='Country']/following-sibling::div/a[@class='selecty-selected']")
	private WebElement txtCountry;

	/* Input Type Text Field */
	@FindBy(xpath = "//label[text()='Input Type']/following-sibling::div/a[@class='selecty-selected']")
	private WebElement txtInputType;

	/* Period Button */
	@FindBy(id = "filter-date")
	private WebElement ddPeriod;

	/* Apply Button */
	@FindBy(xpath = "//button[text()='APPLY']")
	private WebElement btnApply;

	/* Reset Button */
	@FindBy(id = "btnReject")
	private WebElement btnReset;

	/* Close Button */
	@FindBy(xpath = "//div[contains(text(),'Filter')]/span")
	private WebElement btnFilterClose;

	/* Claim Detail Close Button */
	@FindBy(xpath = "//div[@id='claimDetail']/descendant::button[@class='close icon-close']")
	private WebElement btnCloseClaimDetail;

	/* Claim Detail Text */
	@FindBy(xpath = "//div[@id='claimDetailBody']/descendant::h4/b")
	private WebElement txtClaimDetail;

	/* Submit Pop up Text Message */
	@FindBy(xpath = "//div[@id='approveModal']/descendant::h4")
	private WebElement txtApproveSubmitMessage;

	/* Approve Warning Pop up Text Message */
	@FindBy(xpath = "//p[@class='lead text-muted ']")
	private WebElement txtApproveWarningMessage;

	/* Claim History Text */
	@FindBy(xpath = "//div[@id='claimHistoryBody']/descendant::h4[@class='modal-title']")
	private WebElement txtHistory;

	/* History Close Button */
	@FindBy(xpath = "//div[@id='historyModal']/descendant::button[@class='close icon-close']")
	private WebElement btnCloseHistory;

	/* Cancel Pop up Text Message */
	@FindBy(xpath = "//div[@id='referModal']/descendant::h4[@class='modal-title']")
	private WebElement txtCancel;

	/* BulkApprovalBody Text Message */
	@FindBy(xpath = "//div[@id='bulkApprovalBody']/descendant::h4[@class='modal-title']")
	private WebElement txtBulkApprovalBody;

	/* Approve Submit Text Message */
	@FindBy(xpath = "//div[@id='approveModal']/descendant::h4")
	private WebElement txtApproveSubmit;

	/* Claim Approve Toast Message Through Claim ID */
	@FindBy(xpath = "(//div[text()='Claim requests has been successfully approved.'])[2]")
	private WebElement ClaimSubmitedToastMessageThroughClaimID;

	/* Approve or Refer Remarks Text Field */
	@FindBy(id = "AppClaimsPopUp")
	private WebElement btnAppClaimsPopUpSubmit;

	/* AppClaimsPop Cancel Button */
	@FindBy(xpath = "//button[text()='CANCEL']")
	private WebElement btnAppClaimsPopUpCancel;

	/* Spinner */
	@FindBy(xpath = "//div[contains(@class,'spinner')]")
	private WebElement spinner;

	/* ReferBack Button */
	private WebElement clkCancelBack(String claimID) {
		String xpath = "//a[text()='" + claimID
				+ "']/parent::td/following-sibling::td/descendant::i[@class='iRem-referBack']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Claim Check Box */
	private WebElement chkClaim(String claimID) {
		String xpath = "//a[text()='" + claimID + "']/parent::td/preceding-sibling::td/descendant::span";
		return driver.findElement(By.xpath(xpath));
	}

	/* Approve */
	private WebElement clkApprove(String claimID) {
		String xpath = "//a[text()='" + claimID
				+ "']/parent::td/following-sibling::td/descendant::i[@class='iRem-approve']";
		return driver.findElement(By.xpath(xpath));
	}

	/* View History */
	private WebElement clkViewHistory(String claimID) {
		String xpath = "//a[text()='" + claimID + "']/parent::td/following-sibling::td[@class=' action']/descendant::i";
		return driver.findElement(By.xpath(xpath));
	}

	/* View Document */
	private WebElement clkViewDocument(String claimID) {
		String xpath = "//a[text()='" + claimID
				+ "']/../following-sibling::td[@class=' text-center']/descendant::i[@class='iRem-viewDocuments']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Claim Approve Toast Message */
	@FindBy(xpath = "(//div[text()='Claim requests has been successfully submitted.'])[2]")
	private WebElement ClaimSubmitedToastMessage;

	/* Claim Cancel Toast Message */
	@FindBy(xpath = "(//div[text()='Claim requests has been successfully cancelled.'])[2]")
	private WebElement ClaimCancelToastMessage;

	/* Table */
	@FindBy(id = "myTable_wrapper")
	private WebElement myTable;

	@FindBy(xpath = "//h4[text()='HR Partner Manual']")
	private WebElement txtHrPartnerManualPopup;

	/* Approve or Refer Remarks Text Field */
	@FindBy(id = "txtareaRemarks")
	private WebElement txtApproveRemarks;

	/* Pending actions data table */
	@FindBy(xpath = "//table[@id='myTable']//tr")
	private List<WebElement> lstPendingActionClaims;
	
	/**
	 * Description: Method to Click on Refer Back Button
	 * 
	 * @author Suganthini, vikas
	 * @param employeeName
	 */
	public synchronized void clkCancelClaim(String claimID) {
		try {
			WebActionUtil.waitForElement(clkCancelBack(claimID), "Refer Back Button");
			WebActionUtil.validateisElementDisplayed(clkCancelBack(claimID), "Refer Back Button",
					"Refer Back Button Is displayed", "Refer Back Button Is not displayed", "blue");
			WebActionUtil.clickOnElement(clkCancelBack(claimID), "Refer Back button",
					"Unable to click on Refer Back button");
			WebActionUtil.waitForElement(txtCancel, "Cancel Popup");
			WebActionUtil.validatetext("Cancel", txtCancel, "Cancel Popup", "Cancel Popup is displayed",
					"Cancel Popup is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click on Refer Back Button");
			Assert.fail("Unable to Click on Refer Back Button");
		}
	}

	/**
	 * Description: Method to Click on ViewDocument Button
	 * 
	 * @author Suganthini, vikas
	 * @param claimID
	 */
	public synchronized void clkViewDocumentClaim(String claimID) {
		try {
			WebActionUtil.waitForElement(clkViewDocument(claimID), "View Document Button");
			WebActionUtil.validateisElementDisplayed(clkViewDocument(claimID), "View Document button",
					"View Document button is displayed", "View Document button is not displayed", "blue");
			WebActionUtil.clickOnElement(clkViewDocument(claimID), "View Document button",
					"Unable to click on View Document button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on View Document button");
			Assert.fail("Unable to click on View Document button");
		}
	}

	/**
	 * Description: Method to Click on Approve Button
	 * 
	 * @author Suganthini, vikas
	 * @param  claimID
	 */
	public synchronized void clkApproveClaim(String claimID) {
		try {
			WebActionUtil.waitForElement(clkApprove(claimID), "Approve button");
			WebActionUtil.clickOnElement(clkApprove(claimID), "Approve button", "Unable to click on Approve button");
			WebActionUtil.waitForElement(txtApproveSubmitMessage, "Submit Popup");
			WebActionUtil.validatetext("Submit", txtApproveSubmitMessage, "Approve Submit Text message",
					"Approve Submit Popup is displayed", "Approve Submit Popup is not displayed", "blue");
			} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Approve button");
			Assert.fail("Unable to click on Approve button");
		}
	}

	/**
	 * Description: Method to Click on View History button
	 * 
	 * @author Suganthini, vikas
	 * @param claimID
	 */
	public synchronized void clkClaimViewHistory(String claimID) {
		try {
			WebActionUtil.waitForElement(clkViewHistory(claimID), "View History button");
			WebActionUtil.validateisElementDisplayed(clkApprove(claimID), "View History button",
					"View History button is displayed", "View History button is not displayed", "blue");
			WebActionUtil.clickOnElement(clkViewHistory(claimID), "View History button",
					"Unable to click on View History button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on View History button");
			Assert.fail("Unable to click on View History button");
		}
	}

	/**
	 * Description: Method to Click on Claim Check Box
	 * 
	 * @author Suganthini, vikas
	 * @param claimID
	 */
	public synchronized void clkchkClaim(String claimID) {
		try {
			WebActionUtil.waitForElement(chkClaim(claimID), "Check Box");
			WebActionUtil.clickCheckBox(chkClaim(claimID), "Check Box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Claim Check Box");
			Assert.fail("Unable to click on Claim Check Box");
		}
	}

	/**
	 * Description: This method implements to Enter Remark
	 * 
	 * @author: Suganthini,Vikas
	 * @param referRemark
	 */
	public synchronized void setCancelClaimRemark(String referRemark) {
		try {
			WebActionUtil.waitForElement(txtReferRemark, "Refered Remark Text Field");
			WebActionUtil.typeText(txtReferRemark, referRemark, "Refered Remark Text Field");
			WebActionUtil.extentinfo(referRemark+" entered in Refered Remark Text Field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter Refered Remark");
			Assert.fail("Unable to enter Refered Remark");
		}
	}

	/**
	 * Description: This Method implements clicks on Cancel Submit button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkCancelSubmit() {

		try {
			WebActionUtil.waitForElement(btnCacelSubmit, "Refered submit button");
			WebActionUtil.clickOnElement(btnCacelSubmit, "Refered submit button", "Unable to click on Refered Submit");

			WebActionUtil.waitForElement(ClaimCancelToastMessage, "Claim Cancel Toast Message");
			WebActionUtil.validateisElementDisplayed(ClaimCancelToastMessage, "Claim Cancel Success Message",
					"Claim requests has been successfully cancelled is displayed",
					"Claim requests has been successfully cancelled toast message is not dispalyed", "Blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Refered Submit button");
			Assert.fail("Unable to click on Refered Submit button");
		}
	}

	/**
	 * Description:This Method implements clicks on Refered Close button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkReferedClose() {

		try {
			WebActionUtil.waitForElement(btnReferClose, "Refered close button");
			WebActionUtil.clickOnElement(btnReferClose, "Refered close button", "Unable to click on Refered Close");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Refered Close button");
			Assert.fail("Unable to click on Refered Close button");
		}
	}

	/**
	 * Description: This method implements Enter approve Remark
	 * 
	 * @author: Suganthini,Vikas
	 * @param approveRemark
	 */

	public synchronized void setApproveRemark(String approveRemark) {
		try {
			WebActionUtil.waitForElement(txtApproveRemark, "Approve Remark Text Field");
			WebActionUtil.typeText(txtApproveRemark, approveRemark, "Approve Remark Text Field");
			WebActionUtil.extentinfo(approveRemark+" entered in Approve Remark Text Field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Enter Approve Remark");
			Assert.fail("Unable to Enter Approve Remark");
		}
	}

	/**
	 * Description: This method implements click on Upload Link
	 * 
	 * @author:Suganthini,Vikas
	 */
	public synchronized void clkUploadLink() {
		try {
			WebActionUtil.waitForElement(lnkUpload, "Upload Link");
			WebActionUtil.clickOnElement(lnkUpload, "Upload Link", "Unable to click on Upload Link");

		} catch (Exception e) {

			WebActionUtil.fail("Unable to click on Upload Link");
			Assert.fail("Unable to click on Upload Link");
		}
	}

	/**
	 * Description:This Method implements clicks on Approval Submit button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkApproveSubmit() {

		try {
			WebActionUtil.waitForElement(btnApproveSubmit, "Approve submit button");
			WebActionUtil.clickOnElement(btnApproveSubmit, "Approve submit button",
					"Unable to click on Approve Submit");
			WebActionUtil.waitForElement(txtApproveWarningMessage, "Approval Warning Popup");
			WebActionUtil.validatetext("Are you sure you want to proceed?", txtApproveWarningMessage,
					"Are you sure you want to proceed? Popup", "Are you sure you want to proceed? Popup is displayed",
					"Are you sure you want to proceed? Popup is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Approval Submit button");
			Assert.fail("Unable to click on Approval Submit button");
		}
	}

	/**
	 * Description:This Method implements clicks on Approve Yes button
	 * 
	 * @author Suganthini,Vikas
	 * @param claimId
	 */
	public synchronized void clkApproveYes(String claimId) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(btnYes, "Yes button");
			WebActionUtil.clickOnElement(btnYes, "Yes button", "Unable to click on Yes");
			WebActionUtil.waitForElement(ClaimSubmitedToastMessage, "Claim requests has been successfully submitted message");
			WebActionUtil.validateisElementDisplayed(ClaimSubmitedToastMessage, "Claim requests has been successfully submitted message",
					"Claim requests has been successfully submitted message is displayed", "Claim requests has been successfully submitted message is not displayed",
					"green");
		if (spinner.isDisplayed()) {
				new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
			}

			verifyClaimID(claimId);

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description:This Method implements clicks on Approve Yes button
	 * 
	 * @author Suganthini,Vikas
	 * @param claimID
	 */
	public synchronized void clkApproveYesThroughClaimID(String claimID) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(btnYes, "Yes button");
			WebActionUtil.clickOnElement(btnYes, "Yes button", "Unable to click on Yes");
			WebActionUtil.waitForElement(ClaimSubmitedToastMessageThroughClaimID,
					"Claim Submitted Success Message Through ClaimID");
			WebActionUtil.validateisElementDisplayed(ClaimSubmitedToastMessageThroughClaimID,
					"Claim requests has been successfully approved message",
					"Claim requests has been successfully approved message through ClaimID is displayed",
					"Claim requests has been successfully approved message through ClaimID is not dispalyed", "green");
			if (spinner.isDisplayed()) {
				new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
			}

			verifyClaimID(claimID);

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description:This Method implements clicks on Approve No button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkApproveNo() {

		try {
			WebActionUtil.waitForElement(btnNo, "No button");
			WebActionUtil.clickOnElement(btnNo, "No button", "Unable to click on No button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No button");
			Assert.fail("Unable to click on No button");
		}
	}

	/**
	 * Description:This Method implements clicks on Approval Close button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkApproveClose() {

		try {
			WebActionUtil.waitForElement(btnApproveClose, "Approval close button");
			WebActionUtil.clickOnElement(btnApproveClose, "Approval close button", "Unable to click on Approval Close button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Approval Close button");
			Assert.fail("Unable to click on Approval Close button");
		}
	}

	/**
	 * Description:This Method implements Enter Bulk ClaimId
	 * 
	 * @author Suganthini,Vikas
	 * @param BulkClaimId
	 */
	public synchronized void setBulkClaimId(String BulkClaimId) {

		try {
			WebActionUtil.waitForElement(txtBulkClaimId, "Bulk ClaimId Text area");
			WebActionUtil.typeText(txtBulkClaimId, BulkClaimId, "Bulk ClaimId Text area");
			WebActionUtil.extentinfo(BulkClaimId+" entered in Bulk ClaimId Text area");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Enter Bulk Claim ID");
			Assert.fail("Unable to Enter Bulk Claim ID");
		}
	}

	/**
	 * Description: This method implements click on BulkAttachement Link
	 * 
	 * @author:Suganthini,Vikas
	 */
	public synchronized void clkBulkAttachmentLink() {
		try {
			WebActionUtil.waitForElement(lnkBulkAttachment, "Bulk Attachement Link");
			WebActionUtil.validateisElementDisplayed(lnkBulkAttachment, "Bulk Attachement Link",
					"Bulk Attachement Link is displayed", "Bulk Attachement Link is not displayed", "blue");
			WebActionUtil.clickOnElement(lnkBulkAttachment, "Bulk Attachement Link",
					"Unable to click on Bulk Attachement Link");
			WebActionUtil.validatetext("Bulk Submit / Cancel Records ", txtBulkApprovalBody, "Bulk Approval Body Popup",
					"BulkApprovalBody Popup is displayed", "BulkApprovalBody Popup is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Attachement Link");
			Assert.fail("Unable to click on Bulk Attachement Link");
		}
	}

	/**
	 * Description: This method implements Enter Bulk Remark
	 * 
	 * @author: Suganthini,Vikas
	 * @param bulkRemark
	 */

	public synchronized void setBulkRemark(String bulkRemark) {
		try {
			WebActionUtil.waitForElement(txtBulkRemarks, "Bulk Remark Text Field");
			WebActionUtil.typeText(txtBulkRemarks, bulkRemark, "Bulk Remark Text Field");
			WebActionUtil.extentinfo(bulkRemark+" entered in Bulk Remark Text Field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Enter Bulk Remark");
			Assert.fail("Unable to Enter Bulk Remark");
		}
	}

	/**
	 * Description:This Method implements clicks on Bulk Approval Submit button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkBulkApproveSubmit() {

		try {
			WebActionUtil.waitForElement(btnBulkApprvlSubmit, "Bulk Approval submit button");
			WebActionUtil.clickOnElement(btnBulkApprvlSubmit, "Bulk Approval submit button",
					"Unable to click on Bulk Approval Submit");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Approval Submit Button");
			Assert.fail("Unable to click on Bulk Approval Submit Button");
		}
	}

	/**
	 * Description:This Method implements clicks on Bulk Cancel button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkBulkCancel() {

		try {
			WebActionUtil.waitForElement(btnBulkCancel, "Bulk cancel button");
			WebActionUtil.clickOnElement(btnBulkCancel, "Bulk cancel button", "Unable to click on Bulk Cancel");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Cancel Button");
			Assert.fail("Unable to click on Bulk Cancel Button");
		}
	}

	/**
	 * Description:This Method implements clicks on Bulk Close button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkBulkClose() {

		try {
			WebActionUtil.waitForElement(btnBulkClose, "Bulk close button");
			WebActionUtil.clickOnElement(btnBulkClose, "Bulk close button", "Unable to click on Bulk Close");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Close Button");
			Assert.fail("Unable to click on Bulk Close Button");
		}
	}

	/**
	 * Description:This Method implements clicks on Search Filter
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkSearchFilter() {

		try {
			WebActionUtil.waitForElement(btnSearchFilter, "Search Filter Button");
			WebActionUtil.clickOnElement(btnSearchFilter, "Search Filter Button", "Unable to click on Search Filter");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Search Filter Button");
			Assert.fail("Unable to click on Search Filter Button");
		}
	}

	/**
	 * Description: This method implements Enter Search Text
	 * 
	 * @author: Suganthini,Vikas
	 * @param searchText
	 */

	public synchronized void setSearchText(String searchText) {
		try {
			WebActionUtil.waitForElement(txtSearchbox, "Search box Text Field");
			WebActionUtil.typeText(txtSearchbox, searchText, "Search box Text Field");
			WebActionUtil.extentinfo(searchText+" entered in Search box Text Field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Enter Search Text");
			Assert.fail("Unable to Enter Search Text");
		}
	}

	/**
	 * Description:This Method implements clicks on Irem Filter
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkIremFilter() {

		try {
			WebActionUtil.waitForElement(btniremFilter, "Irem Filter button");
			WebActionUtil.clickOnElement(btniremFilter, "Irem Filter button", "Unable to click on Irem Filter");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Irem Filter button");
			Assert.fail("Unable to click on Irem Filter button");
		}
	}

	/**
	 * Description:This Method implements Click on Select All claims
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void chkSelectAllClaims() {
		try {
			WebActionUtil.waitForElement(cbSelectAllClaims, "Select all Claims checkbox");
			WebActionUtil.clickCheckBox(cbSelectAllClaims, "Select all Claims checkbox");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Select all Claims checkbox");
			Assert.fail("Unable to click on Select all Claims checkbox");
		}
	}

	/**
	 * Description:This Method implements clicks on Back
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkBack() {

		try {
			WebActionUtil.waitForElement(btnBack, "Back button");
			WebActionUtil.clickOnElement(btnBack, "Back button", "Unable to click on Back button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Back Button");
			Assert.fail("Unable to click on Back Button");
		}
	}

	/**
	 * Description:This Method implements clicks on Submit
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkSubmit() {

		try {
			WebActionUtil.waitForElement(btnSubmit, "Submit button");
			WebActionUtil.clickOnElement(btnSubmit, "Submit button", "Unable to click on Submit button");

			WebActionUtil.waitForElement(txtApproveSubmitMessage, "Submit Popup");
			WebActionUtil.validatetext("Submit", txtApproveSubmitMessage, "Approve Submit Text message",
					"Approve Submit Popup is displayed", "Approve Submit Popup is not displayed", "blue");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Submit Button");
			Assert.fail("Unable to click on Submit Button");
		}
	}

	/**
	 * Description:This Method implements clicks on Cancel
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkCancel() {

		try {
			WebActionUtil.waitForElement(btnCancel, "Cancelbutton");
			WebActionUtil.clickOnElement(btnCancel, "Cancel button", "Unable to click on Cancel button");
			WebActionUtil.waitForElement(txtCancel, "Cancel Popup");
			WebActionUtil.validatetext("Cancel", txtCancel, "Cancel Popup", "Cancel Popup is displayed",
					"Cancel Popup is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Cancel button");
			Assert.fail("Unable to click on Cancel button");
		}
	}

	/**
	 * Description:This Method implements clicks on Next Arrow
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkNextArror() {

		try {
			WebActionUtil.waitForElement(btnNextArrow, "Next Arrow button");
			WebActionUtil.clickOnElement(btnNextArrow, "Next Arrow button", "Unable to click on Next Arrow button");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Next Arrow button");
			Assert.fail("Unable to click on Next Arrow button");
		}
	}

	/**
	 * Description:This Method implements clicks on PreviousArrow button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkPreviousArrow() {

		try {
			WebActionUtil.waitForElement(btnPreviousArrow, "Previous Arrow button");
			WebActionUtil.clickOnElement(btnPreviousArrow, "Previous Arrow button", "Unable to click on Previous Arrow button");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Previous Arrow button");
			Assert.fail("Unable to click on Previous Arrow button");
		}
	}

	/**
	 * Description: Method to Select Page Size
	 * 
	 * @author Suganthini, vikas
	 * @param employeeName
	 */
	public synchronized void selectPageSize(String pagaNumber) {
		try {
			WebActionUtil.waitForElement(ddPageSize, "Select Page Size");
			WebActionUtil.selectByText(ddPageSize, pagaNumber);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select Page Size");
			Assert.fail("Unable to Select Page Size");
		}
	}

	/**
	 * Description: Method to Enter Employee Name or SAP ID
	 * 
	 * @author Suganthini, vikas
	 * @param employeeName
	 */
	public synchronized void setEmployeeName(String employeeName) {
		try {
			WebActionUtil.waitForElement(txtClaimId, "Enter Employee Name or SAP ID ");
			WebActionUtil.typeText(txtEmpolyeeName, employeeName, "Employee Name or SAP ID ");
			WebActionUtil.extentinfo(employeeName+" entered in Employee Name or SAP ID text box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Enter Employee Name or SAP ID");
			Assert.fail("Unable to Enter Employee Name or SAP ID");
		}
	}

	/**
	 * Description: Method to Enter CliamID
	 * 
	 * @author Suganthini, vikas
	 * @param claimID
	 */
	public synchronized void setClaimId(String claimID) {
		try {
			WebActionUtil.waitForElement(txtClaimId, "ClaimID text box");
			WebActionUtil.typeText(txtClaimId, claimID, "ClaimID text box");
			WebActionUtil.extentinfo(claimID+" entered in ClaimID text box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to ClaimID text box");
			Assert.fail("Unable to ClaimID text box");
		}
	}

	/**
	 * Description: Method to Enter ClaimID
	 * 
	 * @author Suganthini, vikas
	 * @param pageNumber
	 */
	public synchronized void setPage(String pageNumber) {
		try {
			WebActionUtil.waitForElement(ddPageSize, "Enter Page Number");
			WebActionUtil.selectByText(ddPageSize, pageNumber);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Enter Page Number ");
			Assert.fail("Unable to Enter Page Number ");
		}
	}

	/**
	 * Description: Method to Enter CountryName
	 * 
	 * @author Suganthini, vikas
	 * @param employeeName
	 */
	public synchronized void setCountryName(String countryName) {
		try {
			WebActionUtil.waitForElement(txtCountry, "Enter CountryName");
			WebActionUtil.typeText(txtCountry, countryName, "Enter CountryName");
			WebActionUtil.extentinfo(countryName+" entered in Country Name text box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Country Name text box");
			Assert.fail("Unable to Country Name text box");
		}
	}

	/**
	 * Description: Method to Enter InputType
	 * 
	 * @author Suganthini, vikas
	 * @param inputType
	 */
	public synchronized void setInputType(String inputType) {
		try {
			WebActionUtil.waitForElement(txtInputType, "Enter InputType");
			WebActionUtil.typeText(txtInputType, inputType, "Enter InputType");
			WebActionUtil.extentinfo(inputType+" entered in Input Type text box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Enter Input Type ");
			Assert.fail("Unable to Enter Input Type ");
		}
	}

	/**
	 * Description: Method to Click on Period
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void clkPeriod() {
		try {
			WebActionUtil.waitForElement(ddPeriod, "Period button");
			WebActionUtil.clickOnElement(ddPeriod, "Period button", "Unable to click on Period button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Period button");
			Assert.fail("Unable to click on Period button");
		}
	}

	/**
	 * Description: Method to Click on Apply Button
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void clkApply() {
		try {
			WebActionUtil.waitForElement(btnApply, "Apply button");
			WebActionUtil.clickOnElement(btnApply, "Apply button", "Unable to click on Apply button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Apply button");
			Assert.fail("Unable to click on Apply button");
		}
	}

	/**
	 * Description: Method to Click on Reset Button
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void clkReset() {
		try {
			WebActionUtil.waitForElement(btnReset, "Reset button");
			WebActionUtil.clickOnElement(btnReset, "Reset button", "Unable to click on Reset button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Reset button");
			Assert.fail("Unable to click on Reset button");
		}
	}

	/**
	 * Description: Method to Click on Filter Close Button
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void clkFilterClose() {
		try {
			WebActionUtil.waitForElement(btnFilterClose, "Filter Close button");
			WebActionUtil.clickOnElement(btnFilterClose, "Filter Close button", "Unable to click on Filter Close button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Filter Close button");
			Assert.fail("Unable to click on Filter Close button");
		}
	}

	/**
	 * Description: Method to Return a Claim ID
	 * 
	 * @author Suganthini, vikas
	 * @param sapID
	 * @param fromDate
	 * @param toDate
	 */
	public synchronized String getClaimID(String sapID, String fromDate, String toDate) {
		String path = "";
		try {
			String period = changeDateFormat(fromDate, toDate);
			path = "//td[(text()='" + sapID + "')]/ancestor::tr/td[7][text()='" + period + "']/ancestor::tr/td[2]";

			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Return claimID");
			Assert.fail("Unable to Return claimID");
		}
		return driver.findElement(By.xpath(path)).getText();
	}

	/**
	 * Description: Method to Return a Claim ID
	 * 
	 * @author Suganthini, vikas
	 */
	public static String changeDateFormat(String fromDate, String toDate) throws ParseException {
		SimpleDateFormat format1 = null;
		SimpleDateFormat format2 = null;
		Date modifiedFromDate = null;
		Date modifiedToDate = null;
		try {
			format1 = new SimpleDateFormat("MM/dd/yyyy");

			format2 = new SimpleDateFormat("dd-MMM-yy");

			modifiedFromDate = format1.parse(fromDate);

			modifiedToDate = format1.parse(toDate);

		} catch (Exception e) {
		}
		return format2.format(modifiedFromDate) + " To " + format2.format(modifiedToDate);
	}

	/**
	 * Description: Method to Click on Claim ID
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void clkClaimID(String claimID) {
		try {
			WebElement expectedClaimID = driver.findElement(By.xpath("//a[text()='" + claimID + "']"));
			WebActionUtil.clickOnElement(expectedClaimID, "ClaimID : "+expectedClaimID.getText()+" link", "Unable to click on ClaimID : "+expectedClaimID.getText()+" link");
			verifyClaimDetail(claimID);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click on claimID");
			Assert.fail("Unable to Click on claimID");
		}
	}

	/**
	 * Description: Method to Claim ID is Approved or Not
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void verifyClaimID(String claimId) {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			boolean claimIDRemovedStatus = isClaimRemovedStatus(claimId);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (claimIDRemovedStatus) {
				WebActionUtil
						.info("Claim is removed from the Initiate Claims Page and moved to next level for approval");
				WebActionUtil
						.pass("Claim is removed from the Initiate Claims Page and moved to next level for approval");
			} else {
				WebActionUtil.error(
						"Claim is not removed from the Initiate Claims Page");
				WebActionUtil.fail(
						"Claim is not removed from the Initiate Claims Page");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil
					.fail("Claim is not removed from the Initiate Claims Page");
			Assert.fail("Claim is not removed from the Initiate Claims Page");
		}

	}

	/**
	 * Description : Method to fetch Claim Removed or Not Status
	 * 
	 * @return
	 */
	public synchronized boolean isClaimRemovedStatus(String claimID) {
		try {
			driver.findElement(By.xpath("//a[text()='" + claimID + "']"));
			return false;

		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * Description: Method to Verify Claim Detail
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void verifyClaimDetail(String claimID) {
		try {
			WebActionUtil.waitForElement(txtClaimDetail, "Verify Claim Detail");

			WebActionUtil.validatetext("Claim Details (" + claimID + ")", txtClaimDetail, "Verify Claim Detail",
					"Claim details popup is displayed", "Claim details popup is not displayed", "blue");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Claim details popup is not displayed");
			Assert.fail("Claim details popup is not displayed");
		}
	}

	/**
	 * Description: Method to Click on Close Claim Details
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void clkCloseClaimDetails() {
		try {
			WebActionUtil.waitForElement(btnCloseClaimDetail, "Close Claim Details button");
			WebActionUtil.clickOnElement(btnFilterClose, "Close Claim Details button",
					"Unable to click on Close Claim Details button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Close Claim Details button");
			Assert.fail("Unable to click on Close Claim Details button");
		}
	}

	/**
	 * Description: Method to Verify Approve Text message
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void verifyApproveMessage() {
		try {
			WebActionUtil.waitForElement(txtApproveSubmitMessage, "Approve Text message");
			WebActionUtil.validatetext("Are you sure you want to proceed?", txtApproveSubmitMessage,
					"Approve Text message", "Are you sure you want to proceed? is displayed",
					"Are you sure you want to proceed? is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fetch Text");
			Assert.fail("Unable to fetch Text");
		}
	}

	/**
	 * Description: Method to Click on Close History
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void clkCloseHistory() {
		try {
			WebActionUtil.waitForElement(btnCloseHistory, "Close History button");
			WebActionUtil.clickOnElement(btnFilterClose, "Close History button",
					"Unable to click on Close History button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Close History button");
			Assert.fail("Unable to click on Close History button");
		}
	}

	/**
	 * Description: Method to Verify Cancel Text message
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void verifyCancelMessage() {
		try {
			WebActionUtil.waitForElement(txtCancel, "Refer Back Popup");
			WebActionUtil.validatetext("Cancel ", txtCancel, "Refer Back Popup", "Refer Back Popup is displayed",
					"Refer Back Popup is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fetch Text");
			Assert.fail("Unable to fetch Text");
		}
	}

//	/**
//	 * Description: Method to Verify BulkApprovalBody Text message
//	 * 
//	 * @author Suganthini, vikas
//	 */
//	public synchronized void verifyBulkApprovalBodyMessage() {
//		try {
//			WebActionUtil.waitForElement(txtBulkApprovalBody, "BulkApprovalBody Text message");
//			WebActionUtil.validatetext("Bulk Submit / Cancel Records ", txtBulkApprovalBody,
//					"BulkApprovalBody Text message", "", "", "blue");
//		} catch (Exception e) {
//			WebActionUtil.error(e.getMessage());
//			WebActionUtil.fail("Unable to fetch Text");
//			Assert.fail("Unable to fetch Text");
//		}
//	}

//	/**
//	 * Description: Method to Verify Approve Submit Text message
//	 * 
//	 * @author Suganthini, vikas
//	 */
//	public synchronized void verifyApproveSubmitMessage() {
//		try {
//			WebActionUtil.waitForElement(txtApproveSubmit, "Approve Submit Text message");
//			WebActionUtil.validatetext("Submit ", txtApproveSubmitMessage, "Approve Submit Text message", "", "",
//					"blue");
//		} catch (Exception e) {
//			WebActionUtil.error(e.getMessage());
//			WebActionUtil.fail("Unable to fetch Text");
//			Assert.fail("Unable to fetch Text");
//		}
//	}

	/**
	 * Description: This method implements Enter approve or ReferBack Remark
	 * 
	 * @author: Suganthini,Vikas
	 * @param approveRemark
	 */

	public synchronized void setApproveOrReferBackRemark(String remark) {
		try {

			WebActionUtil.waitForElement(txtApproveRemarks, "Approve or ReferBack Remark Text Field");
			WebActionUtil.typeText(txtApproveRemarks, remark, "Approve or ReferBack Remark Text Field");
			WebActionUtil.extentinfo(remark+" entered in Approve or ReferBack Remark Text Field");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter "+remark+" in Approve or ReferBack Remark Text Field");
			Assert.fail("Unable to enter "+remark+" in Approve or ReferBack Remark Text Field");
		}
	}

	/**
	 * Description:This Method implements clicks on Approval Submit button
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkAppClaimsPopUpApproveSubmit() {

		try {
			WebActionUtil.waitForElement(btnAppClaimsPopUpSubmit, "Approval submit button");
			WebActionUtil.clickOnElement(btnAppClaimsPopUpSubmit, "Approval submit button",
					"Unable to click on Approval Submit");
			try {
				new WebDriverWait(driver, 6).until(ExpectedConditions.invisibilityOf(btnAppClaimsPopUpSubmit));
			} catch (Exception e) {

			}
			WebActionUtil.waitForElement(txtApproveWarningMessage, "Approval Warning Popup");
			WebActionUtil.validatetext("Are you sure you want to proceed?", txtApproveWarningMessage,
					"Are you sure you want to proceed? Popup", "Are you sure you want to proceed? Popup is displayed",
					"Are you sure you want to proceed? Popup is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Approval Submit button");
			Assert.fail("Unable to click on Approval Submit button");
		}
	}

	/**
	 * Description:This Method implements clicks on Cancel
	 * 
	 * @author Suganthini,Vikas
	 */
	public synchronized void clkAppClaimsPopUpCancel() {

		try {
			WebActionUtil.waitForElement(btnAppClaimsPopUpCancel, "Cancel button");
			WebActionUtil.clickOnElement(btnAppClaimsPopUpCancel, "Cancel button", "Unable to click on Cancel button");

			WebActionUtil.waitForElement(ClaimCancelToastMessage, "Claim Cancel Toast Message");
			WebActionUtil.validateisElementDisplayed(ClaimCancelToastMessage, "Claim requests has been successfully cancelled message",
					"Claim requests has been successfully cancelled message is displayed",
					"Claim requests has been successfully cancelled toast Message is not dispalyed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Cancel button");
			Assert.fail("Unable to click on Cancel button");
		}
	}

	/**
	 * Description: Validate if Pending Actions Headers/Data is present in the table
	 * 
	 * @author Harsha K B
	 */

	public synchronized void validateInitiateClaimsPageData() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(lstPendingActionClaims));

		WebElement weTableHeaderRow = lstPendingActionClaims.get(0);

		List<WebElement> lstTH = weTableHeaderRow.findElements(By.xpath("//th"));

		boolean isHeadersPresent = true;

		for (int i = 1; i < lstTH.size(); i++) {

			if (lstTH.get(i).getText().isEmpty()) {
				isHeadersPresent = false;
				break;
			}
		}

		List<WebElement> wePedningActionClaimsDataRows = driver.findElements(By.xpath("//table[@id='myTable']//tr"));
		boolean isDataPresent = true;

		if (wePedningActionClaimsDataRows.size() > 0) {
			for (int k = 1; k < wePedningActionClaimsDataRows.size(); k++) {

				List<WebElement> weTabledataRow = driver
						.findElements(By.xpath("//table[@id='myTable']//tr[" + k + "]/td"));

				for (int j = 1; j <= 7; j++) {

					// System.out.println(weTabledataRow.get(j).getText());
					if (weTabledataRow.get(j).getText().isEmpty()) {
						isDataPresent = false;
						break;
					}
				}
				if (!isDataPresent) {

					break;
				}
			}
		}
		if (isHeadersPresent && isDataPresent)
			WebActionUtil.validationinfo("List of claims that needs approval is displayed", "blue");
		else
			WebActionUtil.validationinfo("List of claims that needs approval is not displayed", "red");

	}

}