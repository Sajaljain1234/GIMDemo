package com.hcl.gim.testscripts.ikhaya;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;

import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM_002
 *Claim Type: Ikahya safety bonus
 *TestScript Name: TYSS_GIM_ISB_002
 *Description: Verify the status of GEO HR for 'Ikahya safety bonus(client-seriti/elango)' allowance Claim  after getting approved by all the approvers. 
 *            
 *Author: Manish Kumar C D
 */

public class TYSS_GIM_ISB_002 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql="Select * from Ikahyasafetybonus where SlNo='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify the status of GEO HR for 'Ikahya safety bonus(client-seriti/elango)' allowance Claim  after getting approved by all the approvers.")
	public synchronized void TC_TYSS_GIM_ISB_002(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String rate,String noofInstalments,String remarks) throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_login"));

		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] geoHrRmLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		String[] es2LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES2"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/************ GEO HR Login ******************/
		/* Login as GEO HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee*/
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*Select Ikahya Safety Bonus */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

		/*Download Form Template*/
		String validDownloadDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_dropdown"));
		WebActionUtil.validateDownloadedDocument(validDownloadDocumentName);

		/* SQL query */
		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,Rate,NoofInstalments,Remarks from Ikahyasafetybonus WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";

		/*Paste Your Content Here Text*/
		pages.initiateClaimPage.clkPasteYourContentHereText(prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"), prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_sheetname"),
				strQuery,validDownloadDocumentName);

		/*Upload or Drag and Drop Your Files Here Link*/
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

		/* click on Upload Button*/
		pages.initiateClaimPage.clkUploadButton();

		/*click on Yes Button*/
		pages.initiateClaimPage.clkYesButton();

		/*Select Initiate Claims from Dropdown*/
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));


		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/*Validate list of Claims for Approval in Initiate Claims Page*/
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/*Click on Particular claim checkbox*/
		pages.initiateClaimsPage.clkchkClaim(claimId);

		/*Click on Approve Claim tool tip*/
		pages.initiateClaimsPage.clkApproveClaim(claimId);

		/*Enter the Approve Remark*/
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/*Click on Submit Button*/
		pages.initiateClaimsPage.clkApproveSubmit();

		/*Click on Yes Button*/
		pages.initiateClaimsPage.clkApproveYes(claimId);

		/*Logout as Geo HR*/
		pages.homePage.logout();

		/************ GEO HR RM Login ******************/
		/*Login as GEO HR RM*/
		pages.loginpage.loginToApplication(geoHrRmLoginDetails[0],geoHrRmLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*click on Pending Approval Button*/
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/*Validate list of claims for approval in Pending Action Page*/
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/*validate list of claims for approval in Pending Actions Page*/
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));

		/*click on Particular Checkbox*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/*Click on Approve Tool tip*/
		pages.pendingActionsPage.referApproveClaim(claimId);

		/*Enter Approval Remarks*/
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/*click on Submit Button*/
		pages.pendingActionsPage.clickOnSubmitButton();

		/*click on Yes Button*/
		pages.pendingActionsPage.clickOnYesButton();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/*Logout as Geo HR RM*/
		pages.homePage.logout();

		/************ ES1 Login ******************/
		/*Login as ES1*/
		pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*click on Pending Approval Button*/
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/*Validate list of claims for approval in Pending Action Page*/
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/*validate list of claims for approval in Pending Actions Page*/
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		/*click on Particular Checkbox*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/*Click on Approve Tool tip*/
		pages.pendingActionsPage.referApproveClaim(claimId);

		/*Enter Approval Remarks*/
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/*click on Submit Button*/
		pages.pendingActionsPage.clickOnSubmitButton();

		/*click on Yes Button*/
		pages.pendingActionsPage.clickOnYesButton();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/*Logout as ES1*/
		pages.homePage.logout();

		/************ ES2 Login ******************/

		/* Login as ES2 */
		pages.loginpage.loginToApplication(es2LoginDetails[0], es2LoginDetails[1]);

		/* Close the HCL Banner PopUp */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2"));
		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));


		/*click on Pending Approval Button*/
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/*Validate list of claims for approval in Pending Action Page*/
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/*validate list of claims for approval in Pending Actions Page*/
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES2OnSite"));

		/*click on Particular Checkbox*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/*Click on Approve Tool tip*/
		pages.pendingActionsPage.referApproveClaim(claimId);

		/*Enter Approval Remarks*/
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/*click on Submit Button*/
		pages.pendingActionsPage.clickOnSubmitButton();

		/*click on Yes Button*/
		pages.pendingActionsPage.clickOnYesButton();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);


		/*Logout as ES2*/
		pages.homePage.logout();

		/************ GEO HR Login ******************/
		/* Login as GEO HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee*/
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*Validate Initiate Claim Page*/
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"), prop_app_constants.getProperty("expectedInitiateClaimUrl"), prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

		/*Click on Previous Record*/
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"), prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/*validate Status*/
		pages.previousRecordsPage.validateStatusApproveTxt(claimId, prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));




	}
}
