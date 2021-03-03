package com.hcl.gim.testscripts.exgratia_language_allowance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId:TYSS_GIM_0004
 *Claim Type:Exgatia - Language allowance
 *TestScript Name: TYSS_GIM_ELA_0004
 *Description:Verify the status of GEO HR for Exgratia-Language allowance claim
 *             after getting referred back by ES1 Onsite
 *
 * *Author : Harsha K
 * 
 * */
public class TYSS_GIM_ELA_0004 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaLanguageAllowance where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify the status of GEO HR for Exgratia-Language allowance claim after getting referred back by ES1 Onsite")
	public synchronized void TC_TYSS_GIM_ELA_0004(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String rate, String noOfInstallments, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_la_login"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		// To store data of Approval status, Role & EmployeeCode
		// will be used for Validation after all transactions
		List<String[]> lstHistoryExpectedData = new ArrayList<>();

		/**** Get login details for role Geo HR,ES1 & ES2 *******/
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));

		/****** Login as GEO HR to upload & approve Claim on behalf of employee ******/
		// Login as GEO HR
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		// Close the HCL Banner Pop Up
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
				prop_app_constants.getProperty("claimtype_exgratia_la_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_exgratia"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,Rate,NoofInstalments,Remarks from ExgratiaLanguageAllowance WHERE EmployeeCode='" + employeeCode + "' and SlNo ='"+slNo+"'";
		
		/****
		 * Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		 * your content here'
		 ****/
		pages.initiateClaimPage.clkPasteYourContentHereText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_exgratia_la_sheetname"), strQuery,
				downloadedDocumentName);
		// Upload your .png file
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));

		// Click on UPLOAD Button
		pages.initiateClaimPage.clkUploadButton();
		
		// Click on 'YES'
		pages.initiateClaimPage.clkYesButton();

		// Click on Menu Bar and Click on Initiate Claims
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		// Get the claim id
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		lstHistoryExpectedData
				.add(new String[] { prop_app_constants.getProperty("historyStatus_claimnumber"), claimId });
		lstHistoryExpectedData.add(new String[] { prop_app_constants.getProperty("historyStatus_uploaded"),
				prop_app_constants.getProperty("roleGEOHR"), geoHrLoginDetails[0] });

		/*Click on initiated claim check box*/
		pages.initiateClaimsPage.clkchkClaim(claimId);
		
		/* Click on Approve button */
		pages.initiateClaimsPage.clkSubmit();
		
		// Enter Remarks
		pages.initiateClaimsPage.setApproveRemark(remarks);

		// Click on SUBMIT button.
		pages.initiateClaimsPage.clkApproveSubmit();
		
		// Click on 'YES' Button.
		pages.initiateClaimsPage.clkApproveYes(claimId);

		lstHistoryExpectedData.add(new String[] { prop_app_constants.getProperty("historyStatus_approved"),
				prop_app_constants.getProperty("roleGEOHR"), geoHrLoginDetails[0] });

		// Log out As GEO HR
		pages.homePage.logout();

		/************ ES1 Login to reject from ES-1 ***********/

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

		// click on History Icon
		Set<String> roles = loginDetails.keySet();
		pages.pendingActionsPage.validateHistoryPopupDetail(claimId, roles, lstHistoryExpectedData);

		/*Click on View Documents and validate download file*/
		pages.pendingActionsPage.clickOnViewDocumentIcon(claimId,
				WebActionUtil.getSampleFileName(prop_app_constants.getProperty("fileFormat_png")),
				prop_app_constants.getProperty("fileFormat_png"));

		/*Click on initiated claim check box*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);
		
		/*Click on Refer back button*/
		pages.pendingActionsPage.clickOnReferbackButton();
		
		/*Set remarks */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(remarks);
		
		/*Click on Submit button*/
		pages.pendingActionsPage.clickOnSubmitReferBackButton();
		
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page.
		 * 
		 */
		pages.pendingActionsPage.validateClaimIDReferBack(claimId);
	
		// Log out As ES1
		pages.homePage.logout();

		/*** GeoHR Login to verify the status after refer back(Rejected) from ES-1 ***/

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

		/*Navigate to menu and click on previous records link*/
		pages.homePage.goToPreviousRecords();
		
		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
	
		/* Verify the status */
		pages.previousRecordsPage.validateStatusRejectTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_rejected_es_level1"));

	}
}
