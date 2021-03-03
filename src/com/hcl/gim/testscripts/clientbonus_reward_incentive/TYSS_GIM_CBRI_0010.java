package com.hcl.gim.testscripts.clientbonus_reward_incentive;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/***
 * TestCaseID:TYSS_GIM_0010 Claim Type:Client Bonus/Reward/Incentive Test Script
 * Name:TYSS_GIM_CBRI_0010
 * 
 * @author shreya.u Description: Verify the status of LOB HR for Client
 *         Bonus/Reward/Incentive after getting approved by all approvers
 * 
 */
public class TYSS_GIM_CBRI_0010 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ClientBonusRewardIncentive where SlNo ='10'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:Verify the status of the LOB HR after getting approved by all approvers")
	public synchronized void TC_TYSS_GIM_CBRI_0010(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String typeOfPayment, String fromDate, String toDate, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		String[] data = { employeeCode, countryCode, companyCode, amount, typeOfPayment, fromDate, toDate, remarks };
		String[] format = { "number", "number", "number", "number", "string", "date", "date", "string" };

		Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

	
		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select the Client Bonus Reward Incentive claim type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Excel Upload Tab */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Click on Download Template link */
		String downloadedDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Click on Upload template and upload template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Click on Upload file */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkExcelUploadButton();

		/* Click on yes Button */
		WebActionUtil.waitForAngularPageToLoad();
		pages.initiateClaimPage.clkYesButton();

		/* Click on Menu Bar and Click on Initiate Claims */
		WebActionUtil.waitForAngularPageToLoad();
		pages.homePage.goToInitiateClaims();

		
		/* Validate List of claims need approval is displayed */
		WebActionUtil.waitForAngularPageToLoad();
		pages.initiateClaimsPage.validateInitiateClaimsPageData();
		
		/* Get the claim id */
		String claimid = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Click on Submit button Under the Actions Tab for the Uploaded Claim. */
		pages.initiateClaimsPage.clkApproveClaim(claimid);

		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on SUBMIT button. */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* Click on 'YES' Button. */
		WebActionUtil.waitForAngularPageToLoad();
		pages.initiateClaimsPage.clkApproveYes(claimid);

		/* Log out As LOB HR */
		pages.homePage.logout();

		/* Login as ES1 */
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);

		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1OnSite"));

		/* Validate the Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on pending Approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* List of all the claims should be displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		/* Click on refer back button under actions Tab */
		pages.pendingActionsPage.referApproveClaim(claimid);
		
		/* Enter Remark */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));
		
		/* Click on Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		WebActionUtil.waitForAngularPageToLoad();
		pages.pendingActionsPage.clickOnYesButton();
		/*
		 * /* 'Claim Requests have been successfully approved' Message should be
		 * Displayed and the Claim should be removed from the Pending Actions Page and
		 * Moved to the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimid);

		/* Log out As ES1 */
		pages.homePage.logout();

		/* Login as ES2 */
		String[] es2LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES2"));
		pages.loginpage.loginToApplication(es2LoginDetails[0], es2LoginDetails[1]);

		/* Click on Close button in HCL Banner Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2OnSite"));

		/* Validate the Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on Pending for approval link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES2OnSite"));

		/* Click on refer back button under actions Tab */
		pages.pendingActionsPage.referApproveClaim(claimid);
		
		/* Enter Remark */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));
		
		/* Click on Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();
		
		/* Click on Yes button */
		WebActionUtil.waitForAngularPageToLoad();
		pages.pendingActionsPage.clickOnYesButton();
		/*
		 * /* 'Claim Requests have been successfully approved' Message should be
		 * Displayed and the Claim should be removed from the Pending Actions Page and
		 * Moved to the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimid);

		/* Log out As ES2 */
		pages.homePage.logout();

		/* Login with LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* validate initiate pages */
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

		/* Click on Menu and Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Verify the Status of the Claim. */
		pages.previousRecordsPage.validateStatusApproveTxt(claimid,
				prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));

	}
}
