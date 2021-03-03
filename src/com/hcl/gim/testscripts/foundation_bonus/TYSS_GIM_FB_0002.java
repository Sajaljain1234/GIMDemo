package com.hcl.gim.testscripts.foundation_bonus;
import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId:TYSS_GIM_0002
 *  Claim Type:Foundation Bonus Geo allowance TestSCript
 * Name:TYSS_GIM_FB_0002 
 * Description:Verify the status of GEO HR for 'Foundation Bonus Geo' allowance Claim after getting approved by all the approvers.
 * 
 * @author Suganthini
 *
 */
public class TYSS_GIM_FB_0002 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from FoundationBonusGeo where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify the status of GEO HR for 'Foundation Bonus' allowance claim after getting approved by all the approvers using copy & paste option")
	public synchronized void TC_TYSS_GIM_FB_0002(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String payment, String remarks)
			throws InterruptedException {
		
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_login"));
		String[] geohrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] geohrrmLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		String[] es2LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES2"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/******* GEO HR Login*********/

		/* Enter user name and Password and login as GEO HR */
		pages.loginpage.loginToApplication(geohrLoginDetails[0], geohrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Download form template link */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_foundation_bonus_geo_dropdown"));

		/* Validate Download template */
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,PaymentType,Remarks from FoundationBonusGeo WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";
		
		/* Copy Paste the content from excel sheet */
		pages.initiateClaimPage.clkPasteYourContentHereText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_sheetname"), strQuery,
				downloadedDocumentName);

		/* Upload the file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* Click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* Navigate to menu and click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Retrieve the claim ID */
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Validate List of all the claims in InitiateClaimPage */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on initiated claim check box */
		pages.initiateClaimsPage.clkchkClaim(claimId);

		/* Click on Submit button */
		pages.initiateClaimsPage.clkSubmit();

		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit button in Approve pop up */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* Click on Yes button */
		pages.initiateClaimsPage.clkApproveYes(claimId);

		/* Logout from the application */
		pages.homePage.logout();

		/******* GEO HR RM *********/

		/* Enter user name and Password and login as GEO HR RM */
		pages.loginpage.loginToApplication(geohrrmLoginDetails[0], geohrrmLoginDetails[1]);

		/* Click on Close button in HCL Banner Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Validate Home page */
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

		/* Validate List of all claims in pending page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

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
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Logout from the application */
		pages.homePage.logout();

		/******* ES1 Login*********/

		/* Enter user name and Password and login as ES1 */
		pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);

		/* Click on Close button in HCL Banner Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));

		/* Validate Home page */
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

		/* Validate List of all claims in pending page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

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
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Logout from the application */
		pages.homePage.logout();

		/******* ES2 Login*********/

		/* Enter user name and Password and login as ES2 */
		pages.loginpage.loginToApplication(es2LoginDetails[0], es2LoginDetails[1]);

		/* Click on Close button in HCL Banner Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2"));

		/* Validate Home page */
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

		/* Validate List of all claims in pending page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES2OnSite"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

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
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Logout from the application */
		pages.homePage.logout();

		/******* GEO HR Login*********/

		/* Enter user name and Password and login as GEO HR */
		pages.loginpage.loginToApplication(geohrLoginDetails[0], geohrLoginDetails[1]);

		/* Click on Close button in HCL Banner Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page */
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
		pages.previousRecordsPage.validateStatusApproveTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));

	}
}
