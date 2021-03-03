package com.hcl.gim.testscripts.exgratia_others;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/* TestCaseId: TYSS_GIM_0005 
 * Claim Type: Exgratia Others 
 * TestScript Name: TYSS_GIM_EXO_0005 
 * Description: Verify the status of GEO HR forExgratia other claim
 *              after getting referred back by ES1 OnSite.
 * 
 * Author: Aatish Slathia
 */

public class TYSS_GIM_EXO_0005 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaOthers where SlNo ='5'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description ="Description: Verify the status of GEO HR after getting referred back by ES1 OnSite. ")
	public synchronized void TC_TYSS_GIM_EXO_0005(String slNo,String employeeCode,
			String countryCode,String companyCode,String amount,String fromDate,
			String toDate,String rate,String noofInstalments,String remarks)
			throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_exgratia_others_login"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
	
		// To store data of Approval status, Role & EmployeeCode
	    // will be used for Validation after all transactions
		List<String[]> lstHistoryExpectedData = new ArrayList<>();
		Set<String> roles = loginDetails.keySet();
		
		/**** Get login details for role Geo HR,ES1 *******/
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] geoHrRmLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));

	
		/****** Login as GEO HR to upload & approve Claim on behalf of employee ******/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
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

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
		
		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,Rate,NoofInstalments,Remarks from ExgratiaOthers WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";

		/*
		 * Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		 * your content here'
		 ****/
		pages.initiateClaimPage.clkPasteYourContentHereText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_exgratia_others_sheetname"), strQuery,
				downloadedDocumentName);

		/* Upload jpeg file*/
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* Select Initiate Claims from Dropdown */
		pages.homePage.goToInitiateClaims();

		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		lstHistoryExpectedData
				.add(new String[] { prop_app_constants.getProperty("historyStatus_claimnumber"), claimId });
		lstHistoryExpectedData.add(new String[] { prop_app_constants.getProperty("historyStatus_uploaded"),
				prop_app_constants.getProperty("roleGEOHR"), geoHrLoginDetails[0] });
		lstHistoryExpectedData.add(new String[] { prop_app_constants.getProperty("historyStatus_submitted"),
				prop_app_constants.getProperty("roleGEOHR"), geoHrLoginDetails[0] });

		/* Validate list of Claims for Approval in Initiate Claims Page */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on initiated claim check box */
		pages.initiateClaimsPage.clkchkClaim(claimId);

		/* Click on Approve button */
		pages.initiateClaimsPage.clkSubmit();

		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit button */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* Click on Yes button */
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

		/* click on Pending Approval Button */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* validate Pending action page & role */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* validate list of claims for approval in Pending Actions Page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Click on History Icon */
		pages.pendingActionsPage.validateHistoryPopupDetail(claimId, roles, lstHistoryExpectedData);

		/* Click on View Documents */
		pages.pendingActionsPage.clickOnViewDocumentIcon(claimId,
				WebActionUtil.getSampleFileName(prop_app_constants.getProperty("fileFormat_jpeg")),
				prop_app_constants.getProperty("fileFormat_jpeg"));
		
		/* Click on Refer back button */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/* Click on Submit */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Set remarks for approval action */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on Yes button */
		pages.pendingActionsPage.clickOnYesButton();

		lstHistoryExpectedData.add(new String[] { prop_app_constants.getProperty("historyStatus_approved"),
				prop_app_constants.getProperty("roleGEOHRRM"), geoHrRmLoginDetails[0] });

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/* click on logout */
		pages.homePage.logout();

		/************ ES1 Login to reject from ES-1 ***********/
		
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

		/* validate list of claims for approval in Pending Actions Page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		/* Click on History Icon */
		pages.pendingActionsPage.validateHistoryPopupDetail(claimId, roles, lstHistoryExpectedData);

		/* Click on View Documents and validate download file */
		pages.pendingActionsPage.clickOnViewDocumentIcon(claimId,
				WebActionUtil.getSampleFileName(prop_app_constants.getProperty("fileFormat_jpeg")),
				prop_app_constants.getProperty("fileFormat_jpeg"));

		/* Click on Refer back button */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/* Click on Cancel Button */
		pages.pendingActionsPage.clickOnReferbackButton();

		/* Enter Remarks*/
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));

		/* click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page.
		 * 
		 */
		pages.pendingActionsPage.validateClaimIDReferBack(claimId);

		/* logout as ES1 */
		pages.homePage.logout();

		/*** GeoHR Login to verify the status after refer back(Rejected) from ES-1 ***/

		/*** GeoHR Login ***/
		/* Login as GEO HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim Page */
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

		/* Navigate to menu and click on previous records link */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));


		/* Verify the status */
		pages.previousRecordsPage.validateStatusRejectTxt(claimId, prop_app_constants.getProperty("expectedStatus_rejected_es_level1"));

	}
}
