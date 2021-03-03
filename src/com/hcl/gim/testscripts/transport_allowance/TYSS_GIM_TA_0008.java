package com.hcl.gim.testscripts.transport_allowance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/**
 * TestCase ID : TYSS_GIM_0008
 * Claim Name : Transport Allowance
 * TesScript Name : TYSS_GIM_TA_0008
 * Description : Verify the status of GEO HR for Transport after getting referred back by ES1 Onsite
 * @author vikas.kc
 *
 */
public class TYSS_GIM_TA_0008 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from TransportAllowance where SlNo ='8'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the status of GEO HR after getting referred back by ES1 Onsite")

	public synchronized void TC_TYSS_GIM_TA_0008(String slNo, String employeeCode, String countryCode, String companyCode,  String amount, 
			String fromDate, String toDate, String inputType, String remarks) {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_transport_allowance_login"));

		String[] geoHRLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] geoHRRMLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));
		String[] eS1LoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleES1OnSite"));

		Set<String> roles = loginDetails.keySet();
		List<String []> expectedHistory = new ArrayList<>();

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		
		/***************** GEO HR Login ***********************/
		/* Enter GEO HR user name and Password */
		pages.loginpage.loginToApplication(geoHRLoginCredentials[0], geoHRLoginCredentials[1]);

		/* Click on HCL Banner Popup */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate Behalf Of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_transport_allowance_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_transport_allowance_dropdown"));;
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
		
		String strQuery = "SELECT EmployeeCode, CountryCode, CompanyCode, Amount, FromDate, ToDate, InputType, Remarks from TransportAllowance WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";
		
		/* Click on Paste Your Content Here */
		pages.initiateClaimPage.clkPasteYourContentHereText(prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"), prop_app_constants.getProperty("claimtype_transport_allowance_sheetname"),
				strQuery, downloadedDocumentName);
		
		/* Click on Upload Or Drag And Drop Files */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* Click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* Click on Initiate Claims */
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));
		
		/* Verify list of claims */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Get Claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Click on Check Box To Upload Claim */
		pages.initiateClaimsPage.clkchkClaim(claimID);

		/* Click on Submit Button */
		pages.initiateClaimsPage.clkSubmit();

		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveRemark(remarks);

		/* Click on Approve Claim Submit Button */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* Click on Yes Button */
		pages.initiateClaimsPage.clkApproveYes(claimID);

		/*Expected history after LOB HR approval*/
		expectedHistory.add(new String [] {prop_app_constants.getProperty("historyStatus_claimnumber"),claimID});
		expectedHistory.add(new String [] {prop_app_constants.getProperty("historyStatus_uploaded"), prop_app_constants.getProperty("roleGEOHR"), geoHRLoginCredentials[0]});
		expectedHistory.add(new String [] {prop_app_constants.getProperty("historyStatus_submitted"), prop_app_constants.getProperty("roleGEOHR"), geoHRLoginCredentials[0]});

		/* Click on Logout Button */
		pages.homePage.logout();

		/***************** GEO HR RM Login ***********************/
		/* Enter GEO HR RM user name and Password */
		pages.loginpage.loginToApplication(geoHRRMLoginCredentials[0], geoHRRMLoginCredentials[1]);

		/* Click on HCL Banner Popup */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));
		
		/* Validate Home Page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Click on Pending For Approval on GEO HR RM Card */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Validate List of Claims displayed in Pending Actions Page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));
		
		/* Click on Pending For Approval */
		pages.previousRecordsPage.validateHistoryPopupDetail(claimID, roles, expectedHistory);

		/* Click on View Document Icon of the Claim */
		pages.pendingActionsPage.clickOnViewDocumentIcon(claimID, WebActionUtil.getSampleFileName(prop_app_constants.getProperty("fileFormat_docx")), prop_app_constants.getProperty("fileFormat_docx"));

		/* Validate Downloaded File */

		/*Click on initiated claim Check box*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);

		/* Click on Submit in Actions */
		pages.pendingActionsPage.referApproveClaim(claimID);	

		/* Set remarks for approval action */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();	

		/* Click on Yes button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/* Verify Claim removed from page or not */
		pages.pendingActionsPage.validateClaimID(claimID);

		expectedHistory.add(new String [] {prop_app_constants.getProperty("historyStatus_approved"), prop_app_constants.getProperty("roleGEOHRRM"), geoHRRMLoginCredentials[0]});

		/* Click on Logout Button */
		pages.homePage.logout();

		/***************** ES1 Login ***********************/
		/* Enter ES1 Onsite user name and Password */
		pages.loginpage.loginToApplication(eS1LoginCredentials[0], eS1LoginCredentials[1]);

		/* Click on HCL Banner Popup */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1OnSite"));
		
		/* Validate Home Page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Click on Pending For Approval on ES1 Onsite Card */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Validate List of Claims displayed in Pending Actions Page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));
		
		/* Click on Pending For Approval */
		pages.previousRecordsPage.validateHistoryPopupDetail(claimID, roles, expectedHistory);

		/* Click on View Document Icon of the Claim */
		pages.pendingActionsPage.clickOnViewDocumentIcon(claimID, WebActionUtil.getSampleFileName(prop_app_constants.getProperty("fileFormat_docx")), prop_app_constants.getProperty("fileFormat_docx"));
		
		/* Click on Initiated claim Check box*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);

		/* Click on Cancel Button */
		pages.pendingActionsPage.clickOnReferbackButton();

		/* Set remarks for approval action */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));

		/* Click on Refer Back Submit button */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();	
		
		/* Verify Claim removed from page or not */
		pages.pendingActionsPage.validateClaimIDReferBack(claimID);

		/* Click on Logout Button */
		pages.homePage.logout();


		/***************** GEO HR Login ***********************/
		/* Enter GEO HR user name and Password */
		pages.loginpage.loginToApplication(geoHRLoginCredentials[0], geoHRLoginCredentials[1]);

		/* Click on HCL Banner Popup */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate Behalf Of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page */
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"), prop_app_constants.getProperty("expectedInitiateClaimUrl"), prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));
		
		/*Click on previous record */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records page */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"), prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/*Verify the status of the claim*/
		pages.previousRecordsPage.validateStatusRejectTxt(claimID, prop_app_constants.getProperty("expectedStatus_rejected_es_level1"));

	}

}
