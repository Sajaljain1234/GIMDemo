package com.hcl.gim.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gim.util.WebActionUtil;

/**
 * Description: This class implements the methods for Pending Actions
 * 
 * @author Vivek Dogra
 * 
 */
public class PendingActions_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public PendingActions_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Pending Approval tab */
	@FindBy(xpath = "//p[text()='Pending for Approval']")
	private WebElement tabPendingApproval;

	/* Search icon */
	@FindBy(xpath = "//i[@class='iRem-search']")
	private WebElement icnSearch;

	/* Filter icon */
	@FindBy(xpath = "//i[@class='iRem-filter']")
	private WebElement icnFilter;

	/* Export to excel icon */
	@FindBy(xpath = "//i[@class='iRem-exportToExcel']")
	private WebElement icnExporttoexcel;

	/* BULK APPROVAL/REFER BACK button */
	@FindBy(xpath = "//button[text()='  BULK APPROVAL/REFER BACK']")
	private WebElement btnBulkApprovalReferback;

	/* Check All check box */
	@FindBy(xpath = "//input[@id='abc']")
	private WebElement cbCheckAll;

	/* Actions Refer back Button */
	private WebElement getReferBack(String claimid) {
		String xpath = "//a[text()='" + claimid + "']/../../descendant::i[@class='iRem-referBack']";
		return driver.findElement(By.xpath(xpath));
	}

	/* View document icon */
	private WebElement getViewDocumnet(String claimid) {
		String xpath = "//a[text()='" + claimid + "']/../../descendant::i[@class='iRem-viewDocuments']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Approval or Cancel Remarks Text area Field */
	@FindBy(id = "txtareaRemarks")
	private WebElement txtareaApprovalorCancelRemarks;

	/* refer back remarks */
	@FindBy(id = "referRemark")
	private WebElement txtareaReferBackRemarks;

	/* Submit ReferBack button */
	@FindBy(xpath = "(//button[text()='SUBMIT'])[1]")
	private WebElement btnSubmitReferBack;

	/* Back button */
	@FindBy(xpath = "//button[text()='BACK']")
	private WebElement btnBack;

	/* Approve button */
	@FindBy(xpath = "//button[text()='APPROVE ']")
	private WebElement btnApprove;

	/* Refer back button */
	@FindBy(xpath = "//button[text()='REFER BACK']")
	private WebElement btnReferback;

	/* Refer SUBMIT button */
	@FindBy(xpath = "(//button[text()='SUBMIT'])[2]")
	private WebElement btnSubmit;

	/* Approval Pop up Yes button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYes;

	/* Approval Pop up No button */
	@FindBy(xpath = "//button[text()='NO']")
	private WebElement btnNo;

	/* Bulk Approval submit button */
	@FindBy(id = "AppClaimsBulkAprvl")
	private WebElement btnBulkApprovalSubmit;

	/* Bulk Approval remarks TextArea */
	@FindBy(id = "ClaimIds")
	private WebElement txtBulkApprovalRemarks;

	/* Bulk Approval cancel button */
	@FindBy(xpath = "//button[@class='btn secondry-button ReferID' and text()='Cancel']")
	private WebElement btnCancelBulkApproval;

	/* Bulk Approval cancel remarks text area */
	@FindBy(id = "txtareaBulkRemarks")
	private WebElement txtCancelBulkApprovalRemarks;

	/* Bulk Approval Pop UP Yes button */
	@FindBy(xpath = "//button[@class='confirm btn btn-lg btn primary-button' and text()='YES']")
	private WebElement btnBulkApprovalPopUpYes;

	/* Bulk Approval Pop UP No button */
	@FindBy(xpath = "//button[@class='cancel btn btn-lg btn secondry-button' and text()='NO']")
	private WebElement btnBulkApprovalPopUpNo;

	/* Pending Action text */
	@FindBy(xpath = "//li[text()='Pending Actions']")
	private WebElement txtPendingAction;

	/* Pop up Approval form */
	@FindBy(xpath = "//form[@id='dropzone99']/div[@class='modal-content']")
	private WebElement popupApprove;

	/* Pop up Refer form */
	@FindBy(xpath = "//h4[text()='Refer Back ']")
	private WebElement popupReferback;

	/* Pop up History Detail */
	@FindBy(xpath = "//div[@class='historyDeatil']//li")
	private List<WebElement> popupHistoryDetails;

	/* Document Link */
	private WebElement getDocumentLnk(String documentlink) {
		String xpath = "//a[text()='" + documentlink + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Single check box */
	private WebElement getCheckbox(String claimId) {
		String xpath = "//a[@id='claimId' and text()='" + claimId
				+ "']/parent::td/parent::tr//label/input[@type='checkbox']/parent::label";
		return driver.findElement(By.xpath(xpath));
	}

	/* Claim requests has been successfully approved toast message text */
	@FindBy(xpath = "//div[text()='Claim requests has been successfully approved.']")
	private WebElement ApproveToastmsg;

	/* Claim requests has been successfully Referred Back toast message text */
	@FindBy(xpath = "//div[text()='Claim requests has been successfully Referred Back.']")
	private WebElement cancelToastmsg;

	/* Approve Text Area */
	@FindBy(id = "approveRemark")
	private WebElement txtApproveRemark;

	/* History Text */
	@FindBy(xpath = "//h4[text()='History ']")
	private WebElement txtHistory;

	/* History Pop up Close Button */
	@FindBy(xpath = "//h4[contains(text(),'History')]/preceding-sibling::button")
	private WebElement btnCloseHistoryPopup;

	/* Pop up Claim Number text */
	@FindBy(xpath = "//label[contains(text(),'Claim Number')]")
	private WebElement popupClaimNumber;

	/* Approve Text Area */
	@FindBy(id = "txtareaRemarks")
	private WebElement txtAreaRemark;

	/* Approve Text Area */
	@FindBy(id = "AppClaimsPopUp")
	private WebElement btnApproveClaimpopup;

	/* Pop up Claim Number text */
	@FindBy(xpath = "//span[@class='glb-btn approvalButton']/button[text()='REFER BACK']")
	private WebElement btnReferBack;

	/* Pending actions data table */
	@FindBy(xpath = "//table[@id='myTable']//tr")
	private List<WebElement> lstPendingActionClaims;

	/* Click History */
	private WebElement getHistory(String claimID) {
		String xpath = "//a[text()='" + claimID + "']/parent::td/following-sibling::td[@class=' action']/descendant::i";
		return driver.findElement(By.xpath(xpath));
	}

	/* Claim Number Text */
	private WebElement getClaimNumber(String claimId) {
		String xpath = "//a[@id='claimId' and text()='" + claimId + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Action Refer Approve Button */
	private WebElement selectApprove(String claimid) {
		String xpath = "//a[text()='" + claimid + "']/../../descendant::i[@class='iRem-approve']/parent::button";
		return driver.findElement(By.xpath(xpath));
	}

	/* Action Refer Approve Button */
	private WebElement selecthistory(String claimid) {
		String xpath = "//a[text()='" + claimid + "']/../../descendant::i[@class='iRem-history']/parent::button";
		return driver.findElement(By.xpath(xpath));
	}

	/* Approve Warning Pop up Text Message */
	@FindBy(xpath = "//p[@class='lead text-muted ']")
	private WebElement txtApproveWarningMessage;

	/* Claim Detail pop up text */
	@FindBy(xpath = "//div[@id='claimDetailBody']/descendant::h4/b")
	private WebElement txtClaimDetail;

	/* Close button in Claim Details pop up */
	@FindBy(xpath = "//div[@id='claimDetail']/descendant::button[@class='close icon-close']")
	private WebElement btnCloseClaimDetails;

	/* Pop up */
	@FindBy(xpath = "//div[contains(@class,'sweet-alert')]")
	private WebElement popup;

	/* Bulk Approval cancel remarks text area */
	@FindBy(id = "txtareaBulkRemarks")
	private WebElement txtBulkRemarks;

	/* Bulk refer back button */
	@FindBy(xpath = "//button[@class='btn secondry-button ReferID' and text()='REFER BACK' ]")
	private WebElement btnBulkReferBack;

	/* Spinner */
	@FindBy(xpath = "//div[contains(@class,'spinner')]")
	private WebElement spinner;

	/**
	 * Description: Method click on Claim ID text
	 * 
	 * @author Vivek Dogra
	 * @param claimID
	 */
	public synchronized void clickClaimNumber(String claimID) {
		try {
			WebActionUtil.waitForElement(getClaimNumber(claimID), "Claim ID text");
			WebActionUtil.clickOnElement(getClaimNumber(claimID), "Claim ID text", "Unable to click on Claim ID text");
			validateClaimDetailPopup(claimID);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Claim ID text");
			Assert.fail("Unable to click on Claim ID text");
		}
	}

	/**
	 * Description: Method to Validate Claim Detail pop up is displayed
	 * 
	 * @author Suganthini, vikas
	 * @param claimID
	 */
	private synchronized void validateClaimDetailPopup(String claimID) {
		try {
			WebActionUtil.waitForElement(txtClaimDetail, "Claim Detail pop up text");
			WebActionUtil.validatetext("Claim Details (" + claimID + ")", txtClaimDetail, "Claim Detail pop up text",
					"Claim Details pop up is displayed", "Claim Details pop up is not displayed", "blue");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Claim Details pop up");
			Assert.fail("Unable to validate Claim Details pop up");
		}
	}

	/**
	 * Description: Method to Click on Close Claim Details
	 * 
	 * @author Suganthini, vikas
	 */
	public synchronized void clkCloseClaimDetails() {
		try {
			WebActionUtil.waitForElement(btnCloseClaimDetails, "Close button in Claim Details pop up");
			WebActionUtil.clickOnElement(btnCloseClaimDetails, "Close button in Claim Details pop up",
					"Unable to click on Close button in Claim Details pop up");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Close button in Claim Details pop up");
			Assert.fail("Unable to click on Close button in Claim Details pop up");
		}
	}

	/**
	 * Description: Click on View Document Icon
	 * 
	 * @author Vivek Dogra
	 * @param claimId
	 * @param documentlnk
	 * @param fileExtension
	 */
	public synchronized void clickOnViewDocumentIcon(String claimId, String documentlnk, String fileExtension) {
		try {
			WebActionUtil.waitForElement(getViewDocumnet(claimId), "View documents icon");
			WebActionUtil.clickOnElement(getViewDocumnet(claimId), "View documents icon",
					"Unable to click on View documents icon");
			WebActionUtil.validateisElementDisplayed(getDocumentLnk(documentlnk), "Document link",
					"Document link is displayed", "Document link is not displayed", "blue");
			WebActionUtil.clickOnElement(getDocumentLnk(documentlnk), "Document link",
					"Unable to click on Document link");
			String userDir = System.getProperty("user.home");
			String downloadedDocumentName = WebActionUtil.getDownloadedDocumentName(userDir + "/Downloads",
					fileExtension);
			Assert.assertTrue(downloadedDocumentName.contains(fileExtension));
			WebActionUtil.info(downloadedDocumentName + " file succesfully downloaded");
			WebActionUtil.validationinfo(downloadedDocumentName + " file succesfully downloaded", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on View documents icon");
			Assert.fail("Unable to perform action on View documents icon");
		}
	}

	/**
	 * Description: Validate Pending action page is displayed
	 * 
	 * @author Vivek Dogra
	 * @param expectedPendingActionsPageText
	 * @param expectedPendingActionsPageUrl
	 * @param expectedPendingActionsPageTitle
	 */
	public synchronized void validatePendingActionpage(String expectedPendingActionsPageText,
			String expectedPendingActionsPageUrl, String expectedPendingActionsPageTitle) {
		try {
			String actualClaimPageText = driver
					.findElement(By.xpath("//div[@class='pull-left topHeader']/descendant::li[2]")).getText();
			String actualUrl = driver.getCurrentUrl();
			String actualTitle = driver.getTitle();
			if (actualClaimPageText.equals(expectedPendingActionsPageText)
					&& actualUrl.equals(expectedPendingActionsPageUrl)
					&& actualTitle.equals(expectedPendingActionsPageTitle)) {
				WebActionUtil.validationinfo(
						"Pending Action Page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle,
						"blue");
				WebActionUtil.info(
						"Pending Action Page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle);
			} else {
				if (!expectedPendingActionsPageTitle.equals(actualTitle)) {
					WebActionUtil.validationinfo("Title not matching, Expected= " + expectedPendingActionsPageTitle
							+ ", Actual = " + actualTitle, "red");
					WebActionUtil.error("Title not matching, Expected= " + expectedPendingActionsPageTitle
							+ ", Actual= " + actualTitle);
				}
				if (!expectedPendingActionsPageUrl.equals(actualUrl)) {
					WebActionUtil.validationinfo(
							"Url not matching, Expected= " + expectedPendingActionsPageUrl + ", Actual= " + actualUrl,
							"red");
					WebActionUtil.error(
							"Url not matching, Expected= " + expectedPendingActionsPageUrl + ", Actual= " + actualUrl);
				}
				if (!expectedPendingActionsPageText.equals(actualClaimPageText)) {
					WebActionUtil.validationinfo("Title not matching, Expected= " + expectedPendingActionsPageText
							+ ", Actual= " + actualClaimPageText, "red");
					WebActionUtil.error("Title not matching, Expected= " + expectedPendingActionsPageText + ", Actual= "
							+ actualClaimPageText);
				}
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Pending action page");
			Assert.fail("Unable to validate Pending action page");
		}
	}

	/**
	 * Description: Click on Pending Approval Tab
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clkPendingforApprovalTab() {
		try {
			WebActionUtil.waitForElement(tabPendingApproval, "Pending for Approval tab");
			WebActionUtil.clickOnElement(tabPendingApproval, "Pending for Approval tab",
					"Unable to click on Pending for Approval tab");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Pending for Approval tab");
			Assert.fail("Unable to click on Pending for Approval tab");
		}
	}

	/**
	 * Description: Click on Search Icon
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clkSearchIcon() {
		try {
			WebActionUtil.waitForElement(icnSearch, "Search icon");
			WebActionUtil.clickOnElement(icnSearch, "Search icon", "Unable to click on Search icon");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Search icon");
			Assert.fail("Unable to click on Search icon");
		}
	}

	/**
	 * Description: Click on Filter Icon
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clkFilterIcon() {
		try {
			WebActionUtil.waitForElement(icnFilter, "Filter icon");
			WebActionUtil.clickOnElement(icnFilter, "Filter icon", "Unable to click on Filter icon");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Filter icon");
			Assert.fail("Unable to click on Filter icon");
		}
	}

	/**
	 * Description: Click on Export to Excel Icon
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnExporttoExcelIcon() {
		try {
			WebActionUtil.waitForElement(icnExporttoexcel, "Export to Excel icon");
			WebActionUtil.clickOnElement(icnExporttoexcel, "Export to Excel icon",
					"Unable to click on Export to Excel icon");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Export to Excel icon");
			Assert.fail("Unable to click on Export to Excel icon");
		}
	}

	/**
	 * Description: Click on Bulk Approval Refer back Button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnBulkApprovalReferbackButton() {
		try {
			WebActionUtil.waitForElement(btnBulkApprovalReferback, "Bulk Approval Refer back button");
			WebActionUtil.clickOnElement(btnBulkApprovalReferback, "Bulk Approval Refer back button",
					"Unable to click on Bulk Approval Refer back button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Approval Refer back button");
			Assert.fail("Unable to click on Bulk Approval Refer back button");
		}
	}

	/**
	 * Description: Click on select all check box
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnCheckAllBox() {
		try {
			WebActionUtil.waitForElement(cbCheckAll, "Select all check box");
			WebActionUtil.clickOnElement(cbCheckAll, "Select all check box", "Unable to click on Select all check box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Select all check box");
			Assert.fail("Unable to click on Select all check box");
		}
	}

	/**
	 * Description: Click on Refer back icon under Action
	 * 
	 * @author Vivek Dogra
	 * @param claimid
	 */
	public synchronized void referBackClaim(String claimid) {
		try {
			WebActionUtil.waitForElement(getReferBack(claimid), "Action Refer back icon");
			WebActionUtil.clickOnElement(getReferBack(claimid), "Action Refer back icon",
					"Unable to click on Refer back icon");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on the action refer back according to claim id : " + claimid);
			Assert.fail("Unable to click on the action refer back according to claim id");
		}
	}

	/**
	 * Description: Click on Approve icon under Action
	 * 
	 * @author Vivek Dogra
	 * @param claimid
	 */
	public synchronized void referApproveClaim(String claimid) {
		try {
			WebActionUtil.waitForElement(selectApprove(claimid), "Action Approve icon");
			WebActionUtil.clickOnElement(selectApprove(claimid), "Action Approve icon",
					"Unable to click on action Approve icon");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on action Approve icon according to claim id : " + claimid);
			Assert.fail("Unable to click on action Approve icon according to claim id");
		}
	}

	/**
	 * Description: Click on Back button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnCBackButton() {
		try {
			WebActionUtil.waitForElement(btnBack, "Back button");
			WebActionUtil.clickOnElement(btnBack, "Back button", "Unable to click on Back button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Back button");
			Assert.fail("Unable to click on Back Button ");
		}
	}

	/**
	 * Description: Click on Refer Back button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnReferbackButton() {
		try {
			WebActionUtil.waitForElement(btnReferback, "Refer back button");
			WebActionUtil.clickOnElement(btnReferback, "Refer back button", "Unable to click on Refer back button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Refer back Button");
			Assert.fail("Unable to click on Refer back Button");
		}
	}

	/**
	 * Description: Click on Submit button on Refer Back pop up.
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnSubmitReferBackButton() {
		try {
			WebActionUtil.validateisElementDisplayed(popupReferback, "Refer back pop up",
					"Refer back pop up is displayed", "Refer back popup is not displayed", "blue");

			WebActionUtil.clickOnElement(btnSubmitReferBack, "Submit button on Refer Back pop up",

					"Unable to click on Submit button on Refer Back pop up");

			WebActionUtil.validateisElementDisplayed(cancelToastmsg,
					"Claim requests has been successfully Referred Back toast message",
					"Claim requests has been successfully Referred Back Toast message is displayed",
					"Claim requests has been successfully Referred Back Toast message is not displayed", "Blue");
			try {
				while (true) {
					if (spinner.isDisplayed()) {
						new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
						break;
					}
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {

			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Submit button on Refer Back pop up");
			Assert.fail("Unable to click on Submit button on Refer Back pop up");
		}
	}

	/**
	 * Description: Click on Yes button on Warning pop up Form.
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnYesButton() {
		try {
			WebActionUtil.validatetext("Are you sure you want to proceed?", txtApproveWarningMessage,
					"Are you sure you want to proceed text in pop up",
					"Are you sure you want to proceed message is displayed",
					"Are you sure you want to proceed message is not displayed", "blue");

			WebActionUtil.poll(2000l);
			WebActionUtil.waitForElement(btnYes, "Yes button");
			WebActionUtil.clickOnElement(btnYes, "Yes button", "Unable to click on Yes button");

			WebActionUtil.validateisElementDisplayed(ApproveToastmsg,
					"Claim requests has been successfully approved toast message",
					"Claim requests has been successfully approved toast message is displayed",
					"Claim requests has been successfully approved toast message is not displayed", "blue");
			if (spinner.isDisplayed()) {
				new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(spinner));
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes button");
			Assert.fail("Unable to click on Yes button");
		}
	}

	/**
	 * Description: Method to set text in Refer Back Remark text box
	 * 
	 * @author Vivek Dogra
	 * @param remarks
	 */
	public synchronized void setReferBackRemarksTextAreafield(String remarks) {
		try {
			WebActionUtil.waitForElement(txtareaReferBackRemarks, "Refer Back Remarks text box");
			WebActionUtil.typeText(txtareaReferBackRemarks, remarks, "Refer Back Remarks text box");
			WebActionUtil.extentinfo(remarks + " entered in Refer Back Remarks text box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to type into Refer Back Remarks text box");
			Assert.fail("Unable to type into Refer Back Remarks text box");
		}
	}

	/**
	 * Description: Method to click on Bulk Approval Button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnBulkApprovelButton() {
		try {
			WebActionUtil.waitForElement(btnBulkApprovalSubmit, "Bulk Approve button");
			WebActionUtil.clickOnElement(btnBulkApprovalSubmit, "Bulk Approve button",
					"Unable to click on Bulk Approve Submit button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Approve Submit button");
			Assert.fail("Unable to click on Bulk Approve Submit button");
		}
	}

	/**
	 * Description: Method to click on Bulk Approve pop up Yes Button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnBulkApprovelpopupYesButton() {
		try {
			WebActionUtil.waitForElement(btnBulkApprovalPopUpYes, "Yes button in Bulk Approve pop up");
			WebActionUtil.clickOnElement(btnBulkApprovalPopUpYes, "Yes button in Bulk Approve pop up",
					"Unable to click on Yes button in Bulk Approve pop up");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes button in Bulk Approve pop up");
			Assert.fail("Unable to click on Yes button in Bulk Approve pop up");
		}
	}

	/**
	 * Description: Method to set Cancel Remarks in Bulk Approve pop up
	 * 
	 * @author Vivek Dogra
	 * @param cancellationRemarks
	 */
	public synchronized void setBulkCancelationRemarks(String cancellationRemarks) {
		try {
			WebActionUtil.waitForElement(txtCancelBulkApprovalRemarks, "Bulk Cancellation Remarks Text Area");
			WebActionUtil.typeText(txtCancelBulkApprovalRemarks, cancellationRemarks,
					"Bulk Cancellation Remarks Text Area");
			WebActionUtil.extentinfo(cancellationRemarks + " entered in Bulk Cancellation Remarks Text Area");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to set cancellation remarks in Bulk approve pop up");
			Assert.fail("Unable to set cancellation remarks in Bulk approve pop up");
		}
	}

	/**
	 * Description: Method to click on Particular check box
	 * 
	 * 
	 * @author Vivek Dogra
	 * @param claimid
	 */
	public synchronized void clkPaticularCheckbox(String claimid) {
		try {
			WebActionUtil.waitForElement(getCheckbox(claimid), "Particular claim check box");
			WebActionUtil.clickCheckBox(getCheckbox(claimid), "Particular claim check box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on particular claim check box");
			Assert.fail("Unable to click on particular claim check box");
		}
	}

	/**
	 * Description: Method to Retrieve ClaimId Details
	 * 
	 * @author Vivek Dogra
	 * @param employeName
	 * @param sapId
	 * @param amount
	 * @param toDate
	 * @param fromDate
	 */
	public synchronized void retrieveClaimIdDetails(String employeName, String sapId, String amount, String toDate,
			String fromDate) {
		try {
			ArrayList<String> listOne = new ArrayList<>();
			List<WebElement> lstClaimDetails = WebActionUtil.driver
					.findElements(By.xpath("//div[@class='modal-body']//tbody/tr/td/p"));
			for (WebElement claimdetail : lstClaimDetails) {
				String claimdetails = claimdetail.getText();
				listOne.add(claimdetails);
				WebActionUtil.info(claimdetails);
			}
			ArrayList<String> listTwo = new ArrayList<>();
			listTwo.add(employeName);
			listTwo.add(sapId);
			listTwo.add(amount);
			listTwo.add(toDate);
			listTwo.add(fromDate);
			listOne.retainAll(listTwo);
			for (String claimdetail : listOne) {
				WebActionUtil.validationinfo("Claim details are :" + claimdetail, "blue");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to retrieve Claim Details");
			Assert.fail("Unable to retrieve Claim Details");
		}
	}

	/**
	 * Description: Method to set Approve or Cancel Remarks text area
	 * 
	 *
	 * @author Vivek Dogra
	 * @param remarks
	 */
	public synchronized void setApprovalorCancelRemarksTextAreafield(String remarks) {
		try {
			WebActionUtil.waitForElement(txtareaApprovalorCancelRemarks, "Approve or Cancel Remarks text area");
			WebActionUtil.typeText(txtareaApprovalorCancelRemarks, remarks, "Approve or Cancel Remarks text area");
			WebActionUtil.extentinfo(remarks + " entered in Approve or Cancel Remarks text area");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to set Approve or Cancel Remarks text area");
			Assert.fail("Unable to set Approve or Cancel Remarks text area");
		}

	}

	/**
	 * Description: This method implements Enter approve Remark
	 * 
	 * @author: Vivek Dogra
	 * @param approveRemark
	 */
	public synchronized void setApproveRemark(String approveRemark) {
		try {
			WebActionUtil.waitForElement(txtApproveRemark, "Approve Remark text field");
			WebActionUtil.typeText(txtApproveRemark, approveRemark, "Approve Remark text field");
			WebActionUtil.extentinfo(approveRemark + " entered in Approve Remark text field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter Approve remark");
			Assert.fail("Unable to enter Approve remark");
		}
	}

	/**
	 * Description: Click on Submit button in Approve pop up form.
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnSubmitButton() {
		try {
			WebActionUtil.validateisElementDisplayed(popupApprove, "Approve pop up", "Approve pop up is displayed",
					"Approve pop up is not displayed", "blue");
			WebActionUtil.clickOnElement(btnSubmit, "Submit button", "Unable to click on Submit button");
			try {
				new WebDriverWait(driver, 6).until(ExpectedConditions.invisibilityOf(btnSubmit));
			} catch (Exception e) {

			}
		} catch (Exception e) {

			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Submit button");
			Assert.fail("Unable to click on Submit button");
		}
	}

	/**
	 * Description Method to Validate Details in History Popup
	 * 
	 * @author Sajal jain
	 * @param claimID
	 * @param roles
	 * @param expectedList
	 */
	public synchronized void validateHistoryPopupDetail(String claimID, Set<String> roles,
			List<String[]> expectedList) {
		try {
			List<String[]> actualList = fetchHistoryData(claimID, roles);
			boolean flag = false;
			if (expectedList.size() == actualList.size()) {
				for (int i = 0; i < expectedList.size(); i++) {
					if (!Arrays.equals(expectedList.get(i), actualList.get(i))) {
						flag = true;
						if (flag) {
							WebActionUtil
									.error(Arrays.toString(expectedList.get(i)) + " not present in History pop up");
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
				WebActionUtil.error("Expected count and actual count mismatched in history popup");
				WebActionUtil.fail("Unable to validate History pop up text");
				Assert.fail("Unable to validate History pop up text");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate History pop up text");
			Assert.fail("Unable to validate History pop up text");
		}
	}

	/**
	 * Description: This Method implements to return History Data in History pop up
	 * 
	 * @author Abhilash
	 * @param claimID
	 * @param roles
	 * @return lstHistoryData
	 */
	private synchronized List<String[]> fetchHistoryData(String claimID, Set<String> roles) {
		List<String[]> lstHistoryData = new ArrayList<>();
		try {
			WebActionUtil.waitForElement(getHistory(claimID), "History link");
			WebActionUtil.clickOnElement(getHistory(claimID), "History link", "Unable to click on History link");

			validateHistoryPopup();

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(popupClaimNumber));
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate History pop up text");
			Assert.fail("Unable to validate History pop up text");
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
	 * Description Method to Click on Close Button in History pop up
	 * 
	 * @author Sajal jain
	 */
	private synchronized void clkCloseBtnHistoryPopup() {
		try {
			WebActionUtil.waitForElement(btnCloseHistoryPopup, "Close button in History pop up");
			WebActionUtil.clickOnElement(btnCloseHistoryPopup, "Close button in History pop up",
					"Unable to click Close button in History pop up");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Close button in History pop up");
			Assert.fail("Unable to click Close button in History pop up");
		}
	}

	/**
	 * Description Method to validate History pop up is displayed
	 * 
	 * @author Sajal jain
	 */
	private synchronized void validateHistoryPopup() {
		try {
			WebActionUtil.waitForAngularPageload();
			WebActionUtil.waitForElement(txtHistory, "History text");
			WebActionUtil.validatePartialText("History", txtHistory, "History Text", "History pop up is displayed",
					"History pop up is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate History pop up");
			Assert.fail("Unable to validate History pop up");
		}
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
	 * Description: Method to Click on Claim ID
	 * 
	 * @author Suganthini, vikas
	 * @param claimID
	 */
	public synchronized void clkClaimID(String claimID) {
		try {
			WebElement expectedClaimID = driver.findElement(By.xpath("//a[text()='" + claimID + "']"));
			WebActionUtil.clickOnElement(expectedClaimID, "Claim ID : " + claimID + " link",
					"Unable to click on Claim ID : " + claimID + " link");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Claim ID : " + claimID + " link");
			Assert.fail("Unable to click on Claim ID : " + claimID + " link");
		}
	}

	/**
	 * Description: This method implements Enter approve or ReferBack Remark
	 * 
	 * @author: Vivek Dogra
	 * @param remark
	 */
	public synchronized void setApproveOrReferBackRemark(String remark) {
		try {
			WebActionUtil.waitForElement(txtAreaRemark, "Approve or Refer Back Remark text field");
			WebActionUtil.typeText(txtAreaRemark, remark, "Approve or Refer Back Remark text field");
			WebActionUtil.extentinfo(remark + " entered in Approve or Refer Back Remark text field");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to set Approve or Refer Back Remark");
			Assert.fail("Unable to set Approve or Refer Back Remark");
		}
	}

	/**
	 * Description: This method Click on Approve Button
	 * 
	 * @author: Vivek Dogra
	 */
	public synchronized void clickApproveButtonunderClaimpopup() {
		try {

			WebActionUtil.waitForElement(btnApproveClaimpopup, "Approve button in Claim ID pop up");
			WebActionUtil.clickOnElement(btnApproveClaimpopup, "Approve button in Claim ID pop up",
					"Unable to click Approve button in Claim ID pop up");
			WebActionUtil.waitForAngularPageload();
			try {
				new WebDriverWait(driver, 6).until(ExpectedConditions.invisibilityOf(btnApproveClaimpopup));
			} catch (Exception e) {
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Approve button");
			Assert.fail("Unable to click on Approve button");
		}
	}

	/**
	 * Description: This method Click on Refer Back button
	 * 
	 * @author: Vivek Dogra
	 */
	public synchronized void clickReferBackButtonunderClaimpopup() {
		try {
			WebActionUtil.waitForElement(btnReferBack, "Refer Back button in Claim ID pop up");
			WebActionUtil.clickOnElement(btnReferBack, "Refer Back button in Claim ID pop up",
					"Unable to click on Refer Back button in Claim ID pop up");
			WebActionUtil.waitForAngularPageload();
			WebActionUtil.validateisElementDisplayed(cancelToastmsg,
					"Claim requests has been successfully Referred Back toast message",
					"Claim requests has been successfully Referred Back toast message is displayed",
					"Claim requests has been successfully Referred Back toast message is not displayed", "blue");
			try {
				while (true) {
					if (spinner.isDisplayed()) {
						new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
						break;
					}
				}
			} catch (Exception e) {

			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Refer Back button in Claim ID pop up");
			Assert.fail("Unable to click on Refer Back button in Claim ID pop up");
		}
	}

	/**
	 * Description: Validate if Pending Actions Headers/Data is present in the table
	 * 
	 * @author Harsha K B
	 * @param expectedRole
	 */
	public synchronized void validatePendingActionsData(String expectedRole) {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(lstPendingActionClaims));
		String actualRole = driver.findElement(By.xpath("//span[@class='glb-pnl-showRole role']")).getText().trim();
		expectedRole = expectedRole.trim();

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
	if (isHeadersPresent && isDataPresent && expectedRole.equals(actualRole)) {
			WebActionUtil.validationinfo("List of all claims that need approval is displayed under " + actualRole,
					"blue");
			WebActionUtil.info("List of all claims that need approval is displayed under " + actualRole);
		} else {

			if (isHeadersPresent == false || isDataPresent == false) {
				WebActionUtil.fail("List of all claims that need approval is not displayed");
				WebActionUtil.error("List of all claims that need approval is not displayed");
			}
			if (actualRole.equals(expectedRole) == false) {
				WebActionUtil.fail("Actual role is not as Expected role");
				WebActionUtil.error("Actual role is not as Expected role");
			}

		}

	}

	/**
	 * Description: Method to Claim ID is Approved or Not
	 * 
	 * @author Suganthini, vikas
	 * @param claimId
	 */
	public synchronized void validateClaimID(String claimId) {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			boolean claimIDRemovedStatus = isClaimRemovedStatus(claimId);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (claimIDRemovedStatus) {
				WebActionUtil
						.info("Claim is removed from the Pending Actions page and moved to next level for approval");
				WebActionUtil
						.pass("Claim is removed from the Pending Actions page and moved to next level for approval");
			} else {
				WebActionUtil.error("Claim is not removed from the Pending Actions page");
				WebActionUtil.fail("Claim is not removed from the Pending Actions page");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil
					.fail("Claim is not removed from the Pending Actions Page");
			Assert.fail("Claim is not removed from the Pending Actions Page");
		}

	}

	/**
	 * Description: Method to validate Claim ID Refer Back is removed from pending
	 * action page
	 * 
	 * @author Suganthini, vikas
	 * @param claimId
	 */
	public synchronized void validateClaimIDReferBack(String claimId) {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			boolean claimIDRemovedStatus = isClaimRemovedStatus(claimId);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (claimIDRemovedStatus) {
				WebActionUtil.info("Claim is removed from Pending action page ");
				WebActionUtil.pass("Claim is removed from Pending action page ");
			} else {
				WebActionUtil.error("Claim is not removed from Pending action page ");
				WebActionUtil.fail("Claim is not removed from Pending action page ");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Claim is not removed from Pending action page ");
			Assert.fail("Claim is not removed from Pending action page ");
		}

	}

	/**
	 * Description: Method to fetch Claim Removed or Not Status
	 * 
	 * @author Vivek Dogra
	 * @param claimID
	 */
	public synchronized boolean isClaimRemovedStatus(String claimID) {
		WebElement weClaimIdLink = null;
		try {
			try {
				weClaimIdLink = driver.findElement(By.xpath("//a[text()='" + claimID + "']"));
				new WebDriverWait(driver, 7).until(ExpectedConditions.invisibilityOf(weClaimIdLink));
				driver.findElement(By.xpath("//a[text()='" + claimID + "']"));
			} catch (NoSuchElementException e) {
				throw e;
			} catch (TimeoutException r) {

			}
			return false;

		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * Description: Click on Approve Button.
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnApproveButton() {
		try {
			WebActionUtil.validateisElementEnabled(btnApprove, "Approve button", "Approve button is highlighted",
					"Approve button is not highlighted", "blue");
			WebActionUtil.clickOnElement(btnApprove, "Approve button", "Unable to click on Approve button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Approve button");
			Assert.fail("Unable to click on Approve button");
		}
	}

	/**
	 * Description: Get the claim id separated with the comma
	 * 
	 * 
	 * @author Shreya
	 * @param lstclaimids
	 * @return strClaimIdsConcat
	 */
	public synchronized String getclaimidwithcomma(ArrayList lstclaimids) {
		String strClaimIdsConcat = "";
		for (int i = 0; i < lstclaimids.size() - 1; i++) {

			strClaimIdsConcat = strClaimIdsConcat + lstclaimids.get(i) + ",";

		}
		strClaimIdsConcat = strClaimIdsConcat + lstclaimids.get(lstclaimids.size() - 1);
		return strClaimIdsConcat;

	}

	/**
	 * Description: Method to click on Bulk Approval Button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnBulkApprovalButton() {
		try {
			WebActionUtil.waitForElement(btnBulkApprovalSubmit, "Bulk Approve button");
			WebActionUtil.clickOnElement(btnBulkApprovalSubmit, "Bulk Approve button",
					"Unable to click on Bulk Approve Submit button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Approve Submit button");
			Assert.fail("Unable to click on Bulk Approve Submit button");
		}
	}

	/**
	 * Description: Method to click on Bulk Cancel Button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnBulkCancelButton() {
		try {
			WebActionUtil.waitForElement(btnCancelBulkApproval, "Bulk Cancel button");
			WebActionUtil.clickOnElement(btnCancelBulkApproval, "Bulk Cancel button",
					"Unable to click on Bulk Cancel Submit button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Cancel Submit button");
			Assert.fail("Unable to click on Bulk Cancel Submit button");
		}
	}

	/**
	 * Description: Method to click on Bulk Cancel Button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnBulkReferBackButton() {
		try {
			WebActionUtil.waitForElement(btnBulkReferBack, "Bulk Refer back button");
			WebActionUtil.clickOnElement(btnBulkReferBack, "Bulk Refer back button",
					"Unable to click on Bulk Refer back button");
			WebActionUtil.validateisElementDisplayed(cancelToastmsg, "Claim requests has been successfully Referred Back pop up message",
					"Claim requests has been successfully Referred Back pop up is displayed",
					"Claim requests has been successfully Referred Back pop up is not displayed", "green");
			try {
				while (true) {
					if (spinner.isDisplayed()) {
						new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
						break;
					}
				}
			} catch (Exception e) {

			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Bulk Refer back button");
			Assert.fail("Unable to click on Bulk Refer back button");
		}
	}

	/**
	 * Description: Method to click on Bulk Approve pop up Yes Button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnBulkApprovalpopupYesButton() {
		try {
			WebActionUtil.validateisElementDisplayed(popup, "Confirmation pop up", "Confirmation pop up is displayed",
					"Confirmation pop up is not displayed ", "blue");
			WebActionUtil.waitForAngularPageToLoad();
			WebActionUtil.waitForElement(btnBulkApprovalPopUpYes, "Bulk Approve pop up Yes button");
			WebActionUtil.clickOnElement(btnBulkApprovalPopUpYes, "Bulk Approve pop up Yes button",
					"Unable to click on Yes button in Bulk Approve pop up");
			WebActionUtil.validateisElementDisplayed(ApproveToastmsg, "Claim requests has been successfully approved toast message",
					"Claim requests has been successfully approved toast message is displayed",
					"Claim requests has been successfully approved toast message is not displayed", "green");
			//if (ApproveToastmsg.isDisplayed()) 
		
				try {
					new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
				} catch (Exception e) {

				}
		
			
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes button in Bulk Approve pop up");
			Assert.fail("Unable to click on Yes button in Bulk Approve pop up");
		}
	}

	/**
	 * Description: Method to click on Bulk Approval pop Up No Button
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized void clickOnBulkApprovelpopupNoButton() {
		try {
			WebActionUtil.waitForElement(btnBulkApprovalPopUpNo, "Bulk Approve pop up No button");
			WebActionUtil.clickOnElement(btnBulkApprovalPopUpNo, "Bulk Approve pop up No button",
					"Unable to click on No button in Bulk Approve pop up");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No button in Bulk Approve pop up");
			Assert.fail("Unable to click on No button in Bulk Approve pop up");
		}
	}

	/**
	 * Description: Method to set Remarks in Bulk Approve pop up
	 * 
	 * @author Vivek Dogra
	 * @param claimidswithcomma
	 */
	public synchronized void setBulkApprovalRemarks(String claimidswithcomma) {
		try {
			WebActionUtil.waitForElement(txtBulkApprovalRemarks, "Bulk Approve Remarks text area");
			WebActionUtil.typeText(txtBulkApprovalRemarks, claimidswithcomma, "Bulk Approve Remarks text area");
			WebActionUtil.validateEnteredValue1(claimidswithcomma, WebActionUtil.getTextUsingJS("ClaimIds"),
					"Bulk Approve Remarks text area", claimidswithcomma+" claim numbers reflected in text field",
					claimidswithcomma+ " claim numbers not reflected in text field", "blue");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to set Remarks in Bulk approve pop up");
			Assert.fail("Unable to set Remarks in Bulk approve pop up");
		}
	}

	/**
	 * Description: Method to set Remarks in Bulk Approve pop up
	 * 
	 * @author Vivek Dogra
	 * @param remarks
	 */
	public synchronized void setRemarksBulkApprovalReferBackPopUp(String remarks) {
		try {
			WebActionUtil.waitForElement(txtBulkRemarks, "Bulk Remarks Text Area");
			WebActionUtil.typeText(txtBulkRemarks, remarks, "Bulk  Remarks Text Area");
			WebActionUtil.validateEnteredValue1(remarks, WebActionUtil.getTextUsingJS("txtareaBulkRemarks"),
					"Bulk Remarks Text Area", remarks+" entered in Bulk Remarks Text Area",
					remarks+" not entered in Bulk Remarks Text Area", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to set remarks in Bulk approve pop up");
			Assert.fail("Unable to set  remarks in Bulk approve pop up");
		}
	}
}