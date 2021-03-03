package com.hcl.gim.testscripts.clientbonus_reward_incentive;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/***
 * TestCaseID:TYSS_GIM_0002 Claim Type:Client Bonus/Reward/Incentive Test Script
 * Name:TYSS_GIM_CBRI_0002
 * 
 * @author shreya.u Description: Verify the status of LOB HR for Client
 *         Bonus/Reward/Incentive after getting approved by all approvers.
 * 
 */
public class TYSS_GIM_CBRI_0002 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ClientBonusRewardIncentive where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:Verify the status of the LOB HR after getting approved by all approvers")
	public synchronized void TC_TYSS_GIM_CBRI_0002(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String typeOfPayment, String fromDate, String toDate, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		/* Login as LOB HR */
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

		
		/* Download Form Template */
		String documentname = pages.initiateClaimPage.clkDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"));

		/*
		 * Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		 * your content here
		 */
		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,TypeofPayment,FromDate,ToDate,Remarks from ClientBonusRewardIncentive WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";


		pages.initiateClaimPage.clkPasteYourContentHereText(GIMTESTDATAOTHERFLOWEXCELPATH,
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_sheetname"), strQuery,
				documentname);

		/* Upload your .jpg file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

		/* Click on UPLOAD Button. */
		pages.initiateClaimPage.clkUploadButton();

		/* Click on 'YES'. */
		WebActionUtil.waitForAngularPageToLoad();
		pages.initiateClaimPage.clkYesButton();

		/* Click on Menu Bar and Click on Initiate Claims */
		WebActionUtil.waitForAngularPageToLoad();
		pages.homePage.goToInitiateClaims();
		
		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Get the claim id */
		String claimid = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		
		/* Click on initiated claim check box */
		pages.initiateClaimsPage.clkchkClaim(claimid);

		/* Click on Approve button */
		pages.initiateClaimsPage.clkSubmit();

		/* Enter Approve Remarks */
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit button */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* Click on Yes button */
		pages.initiateClaimsPage.clkApproveYes(claimid);

		/* Log out As LOB HR */
		pages.homePage.logout();

		/* Enter User ID and Password of ES1 OnSite and Click on Log In. */
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);

		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));

		/* Validate the Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* List of all the claims should be displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));
		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimid);

		/* Click on Submit in Actions */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Set remarks for approval action */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on Yes button */
		pages.pendingActionsPage.clickOnYesButton();

		/*
		 * validate claim removed from pending action page and moved to next level for
		 * approval
		 */
		pages.pendingActionsPage.validateClaimID(claimid);

		/* Log out as ES1*/
		pages.homePage.logout();

		/* Enter User ID and Password of ES1 OnSite and Click on Log In. */
		String[] es2LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES2"));
		pages.loginpage.loginToApplication(es2LoginDetails[0], es2LoginDetails[1]);

		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2OnSite"));

		/* Validate the Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* List of all the claims should be displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES2OnSite"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimid);

		/* Click on Submit in Actions */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Set remarks for approval action */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on Yes button */
		pages.pendingActionsPage.clickOnYesButton();
		/*
		 * validate claim removed from pending action page and moved to next level for
		 * approval
		 */
		pages.pendingActionsPage.validateClaimID(claimid);

		/* Log out */
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
