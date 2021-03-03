package com.hcl.gim.testscripts.exgratia_others;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId: TYSS_GIM_0014 
 * Claim Type: Exgratia Others 
 * TestScript Name: TYSS_GIM_EXO_0014 
 * Description: Verify the status of the Exgratia others after
 *              getting referred back by ES1 OnSite.
 * 
 * Author: Aatish Slathia
 */

public class TYSS_GIM_EXO_0014 extends BaseTest {
	
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaOthers where SlNo ='14'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the status after getting referred back by ES1 OnSite.")
	public synchronized void TC_TYSS_GIM_EXO_0014(String slNo, String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String rate, String noOfInstallments,
			String remarks)
			throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_exgratia_others_login"));

		
		String[] data = {employeeCode, countryCode, companyCode, amount, fromDate, toDate, rate, noOfInstallments,
				remarks};
		String[] format = { "number", "number", "number", "number", "date", "date", "string", "number", "string" };
		Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		/**** Get login details for role Geo HR, Geo HRM, ES1 *******/
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] geoHrRmLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		
		/************ GEO HR Login ******************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Click on Hcl Banner Popup Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Validate Initiate Claim Page & Select Claim type-Exgratia - Language
		 * allowance
		 */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* click on Claimtype Radio Button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Download Form Template & validate the document */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtyperadio_excel_upload"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Uploads the downloaded template to Upload Template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Upload or Drag and Drop your files here */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* click on Upload Button */
		pages.initiateClaimPage.clkExcelUploadButton();

		/* click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Get the claim id & store it */
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Validate list of Claims for Approval in Initiate Claims Page */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on Submit button Under the Actions Tab for the Uploaded Claim. */
		pages.initiateClaimsPage.clkApproveClaim(claimId);

		/* Enter the Remark in Remark text area field */
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit Button */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* Click on Yes Button */
		pages.initiateClaimsPage.clkApproveYes(claimId);

		/* Logout as Geo HR */
		pages.homePage.logout();

		/************ GEO HR RM Login ******************/
		/* Login as GEO HR RM */
		pages.loginpage.loginToApplication(geoHrRmLoginDetails[0], geoHrRmLoginDetails[1]);

		/* Close the HCL Banner PopUp */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on Pending Approval Button */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending action page & role */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
        pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Click on Approve Tool tip */
		pages.pendingActionsPage.referApproveClaim(claimId);

		/* Enter Remarks */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

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

		/* Logout as Geo HR RM */
		pages.homePage.logout();

		/************ ES1 Login ******************/
		/* Login as ES1 */
		pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));

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
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		/* Refer back Claim */
		pages.pendingActionsPage.referBackClaim(claimId);

		/* Set Remarks */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));

		/* click on Submit button */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page.
		 */
		pages.pendingActionsPage.validateClaimIDReferBack(claimId);

		/* Logout as ES1 */
		pages.homePage.logout();

		/************ GEO HR Login ******************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Click on Hcl Banner Popup Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim Page */
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

		/* Click on Previous Record */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate the status - Rejected by ES Level -1 */
		pages.previousRecordsPage.validateStatusRejectTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_rejected_es_level1"));

	}
}