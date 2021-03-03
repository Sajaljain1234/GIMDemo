package com.hcl.gim.testscripts.clientbonus_reward_incentive;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/***
 * TestCaseID:TYSS_GIM_0004 Claim Type:Client Bonus/Reward/Incentive Test Script
 * Name:TYSS_GIM_CBRI_0004
 * 
 * @author shreya.u Description: Verify the status of LOB HR for Client
 *         Bonus/Reward/Incentive after getting referred back by ES1 On site
 * 
 */
public class TYSS_GIM_CBRI_0004 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ClientBonusRewardIncentive where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description: Verify the status of LOB HR after the claim  getting referred back by ES1 On site")
	public synchronized void TC_TYSS_GIM_CBRI_0004(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String typeOfPayment, String fromDate, String toDate, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_login"));

		/* Login details */
		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));

		Set<String> roles = loginDetails.keySet();
		List<String[]> expectedHistory = new ArrayList<>();

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
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

		/* Click on Download form template link */
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
		
		
		/* Upload the file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* Click on Yes Button */
		WebActionUtil.waitForAngularPageToLoad();
		pages.initiateClaimPage.clkYesButton();

		/* Navigate to menu and click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Retrieve the claim ID */
		WebActionUtil.waitForAngularPageToLoad();
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* click on initiated claim check box for cancel */
		pages.initiateClaimsPage.clkchkClaim(claimID);

		/* Click on submit button */
		pages.initiateClaimsPage.clkSubmit();

		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on SUBMIT button */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* Click on 'YES' Button. */
		WebActionUtil.waitForAngularPageToLoad();
		pages.initiateClaimsPage.clkApproveYes(claimID);

		/* Expected history after LOB HR approval */
		expectedHistory.add(new String[] { prop_app_constants.getProperty("historyStatus_claimnumber"), claimID });
		expectedHistory.add(new String[] { prop_app_constants.getProperty("historyStatus_uploaded"),
				prop_app_constants.getProperty("roleLOBHR"), lobhrLoginDetails[0] });
		expectedHistory.add(new String[] { prop_app_constants.getProperty("historyStatus_approved"),
				prop_app_constants.getProperty("roleLOBHR"), lobhrLoginDetails[0] });

		/* logout from application */
		pages.homePage.logout();

		/* Enter user name and Password and login as ES1 */
		pages.loginpage.loginToApplication(es1LoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1OnSite"));

		/* Validate the Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click Pending for approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		/* Validate History Pop up Details */
		pages.pendingActionsPage.validateHistoryPopupDetail(claimID, roles, expectedHistory);

		/* Click on View document icon of the claim */
		pages.pendingActionsPage.clickOnViewDocumentIcon(claimID,
				WebActionUtil.getSampleFileName(prop_app_constants.getProperty("fileFormat_png")),
				prop_app_constants.getProperty("fileFormat_png"));

		/* Click on initiated claim Check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);

		/* Click on refer back button under actions Tab */
		pages.pendingActionsPage.clickOnReferbackButton();

		/* Enter Rejected Remark */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));

		/*
		 * Click on Submit button and Validate 'Claim request has been successfully
		 * referred back' success message
		 */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();
		
		/*Validate claim ID is referred back*/
		pages.pendingActionsPage.validateClaimIDReferBack(claimID);

		/* logout from application */
		pages.homePage.logout();

		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Banner Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* validate initiate pages */
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of the claim */
		pages.previousRecordsPage.validateStatusRejectTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_rejected_es_level1"));

	}
}
