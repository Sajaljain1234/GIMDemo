package com.hcl.gim.testscripts.exgratia_language_allowance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId:TYSS_GIM_0010
 *Claim Type:Exgatia - Language allowance
 *TestScript Name: TYSS_GIM_ELA_0010
 *Description:Verify the status of GEO HR Exgratia-Language allowance claim 
 *            after getting approved by all the approvers
 *Author : Harsha K
 * 
 * */
public class TYSS_GIM_ELA_0010 extends BaseTest {
	
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaLanguageAllowance where SlNo ='10'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify the status of GEO HR Exgratia-Language allowance claim after getting approved by all the approvers")
	public synchronized void TC_TYSS_GIM_ELA_0010(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String rate, String noOfInstallments, String remarks)
			throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_la_login"));

		String[] format = { "number", "number", "number", "number", "date", "date", "string", "number", "string" };
		String[] data = { employeeCode, countryCode, companyCode, amount, fromDate, toDate, rate, noOfInstallments,
				remarks };
		Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Get login details for role Geo HR , ES1 & ES2 */
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		String[] es2LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES2"));

		/***************
		 * GeoHR Login to upload & approve the claim on behalf of employee
		 *************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Click on Hcl Banner Popup Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Geo HR Initiate of Behalf of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Validate Initiate Claim Page & Select Claim type-Exgratia - Language
		 * allowance
		 */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_exgratia_la_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* click on Claimtype Radio Button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Download Form Template & validate the document */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_exgratia_la_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* uploads the downloaded template to Upload Template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Upload or Drag and Drop your files here */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

		/* click on Upload Button */
		pages.initiateClaimPage.clkExcelUploadButton();

		/* click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		// Click on Menu Bar and Click on Initiate Claims
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));

		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		pages.initiateClaimsPage.validateInitiateClaimsPageData();
		

		/* click on Submit Tool tip */
		pages.initiateClaimsPage.clkApproveClaim(claimId);

		/* Enter the Remark in Remark text area field */
		pages.initiateClaimsPage.setApproveRemark(remarks);

		/* click on Submit Button */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* click on Yes Button */
		pages.initiateClaimsPage.clkApproveYes(claimId);
		

		/* Logout as Geo HR */
		pages.homePage.logout();

		/************ ES1 Login to approve from ES-1 ***********/

		pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		// click on Pending Approval Button
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* validate Pending action page & role */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		// Click on Approve Tool tip
		pages.pendingActionsPage.referApproveClaim(claimId);
		/* Enter Approval Remarks */
		pages.pendingActionsPage.setApproveRemark(remarks);
		/* click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Logout as ES1 */
		pages.homePage.logout();

		/************ ES2 Login to approve from ES-2 ***********/

		/* Login as ES2 */
		pages.loginpage.loginToApplication(es2LoginDetails[0], es2LoginDetails[1]);

		/* Close the HCL Banner PopUp */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2"));
		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* click on Pending Approval Button */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* validate Pending action page & role */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES2OnSite"));

		/* Click on Approve Tool tip */
		pages.pendingActionsPage.referApproveClaim(claimId);

		/* Enter Approval Remarks */
		pages.pendingActionsPage.setApproveRemark(remarks);

		/* click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Logout as ES2 */
		pages.homePage.logout();

		/***
		 * GeoHR Login to verify the status 'Pushed to iRem' after approval from ES-2
		 ***/

		/*** GeoHR Login ***/
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		// Close the HCL Banner Pop Up
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		/* Validate Initiate Claim Page */
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

		pages.homePage.goToPreviousRecords();
		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Verify the status */
		pages.previousRecordsPage.validateStatusApproveTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));

		

	}
}
