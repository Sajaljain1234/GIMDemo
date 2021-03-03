package com.hcl.gim.testscripts.car_parking_deduction;

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
 * TestCaseId:TYSS_GIM_0006 Claim Type:Car parking Deduction
 * TestSCriptName:TYSS_GIM_CPD_0006 Description:Verify the status of GEO HR for
 * 'Carparking Deduction' allowance claim after getting referred back by GEO HR
 * RM.
 * 
 * @author Vivek Dogra
 *
 */
public class TYSS_GIM_CPD_0006 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from CarParkingDeduction where SlNo='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:Verify the status of GEO HR for 'Carparking Deduction' allowance claim after getting referred back by GEO HR RM")
	public synchronized void TC_TYSS_GIM_CPD_0006(String slNo, String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String periodicity,
			String noofInstalments, String remarks) {

		Map<String, String[]> logindata = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"));

		String[] getGeoHRLoginCredentials = logindata.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] getGeoHRRMLoginCredentials = logindata.get(prop_app_constants.getProperty("roleGEOHRRM"));

		Set<String> roles = logindata.keySet();
		List<String[]> expectedHistory = new ArrayList<>();
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Enter user name and Password and login as GEO HR */
		pages.loginpage.loginToApplication(getGeoHRLoginCredentials[0], getGeoHRLoginCredentials[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page is displayed and Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_sheetname"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* SQL query */
		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,Periodicity,Noofinstalments,Remarks from CarParkingDeduction WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";

		/* Copy Paste the content from excel sheet */
		pages.initiateClaimPage.clkPasteYourContentHereText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"), strQuery,
				downloadedDocumentName);

		/* Upload the file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));
		/* Click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();
		/* Click on Yes Button */
		pages.initiateClaimPage.clkYesButton();
		/* Navigate to menu and click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Retrieve the claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		/* Click on initiated claim check box */
		pages.initiateClaimsPage.clkchkClaim(claimID);
		/* Click on Submit button */
		pages.initiateClaimsPage.clkSubmit();
		/* Set Remarks */
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));
		/* Click on Submit button in Approve pop up */
		pages.initiateClaimsPage.clkApproveSubmit();
		/* Click on Yes button */
		pages.initiateClaimsPage.clkApproveYes(claimID);
		/* Expected history after GEO HR approval */

		expectedHistory.add(new String[] { prop_app_constants.getProperty("historyStatus_claimnumber"), claimID });
		expectedHistory.add(new String[] { prop_app_constants.getProperty("historyStatus_uploaded"),
				prop_app_constants.getProperty("roleGEOHR"), getGeoHRLoginCredentials[0] });
		expectedHistory.add(new String[] { prop_app_constants.getProperty("historyStatus_submitted"),
				prop_app_constants.getProperty("roleGEOHR"), getGeoHRLoginCredentials[0] });
		/* Logout from the application */
		pages.homePage.logout();

		/* Enter user name and Password and login as GEO HR RM */
		pages.loginpage.loginToApplication(getGeoHRRMLoginCredentials[0], getGeoHRRMLoginCredentials[1]);
		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Validate Home page */
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

		/* Validate List of claims need approval is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Validate History Popup Details */
		pages.pendingActionsPage.validateHistoryPopupDetail(claimID, roles, expectedHistory);
		/* Click on View document icon of the claim */
		pages.pendingActionsPage.clickOnViewDocumentIcon(claimID,
				WebActionUtil.getSampleFileName(prop_app_constants.getProperty("fileFormat_docx")),
				prop_app_constants.getProperty("fileFormat_docx"));
		/* Click on initiated claim Check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);
		/* Click on refer back button under actions Tab */
		pages.pendingActionsPage.clickOnReferbackButton();
		/* Enter Remark */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));
		/*
		 * Click on Submit button and Validate 'Claim request has been successfully
		 * referred back' success message
		 */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();

		pages.pendingActionsPage.validateClaimIDReferBack(claimID);
		/* logout from application */
		pages.homePage.logout();

		/* Enter user name and Password and login as GEO HR */
		pages.loginpage.loginToApplication(getGeoHRLoginCredentials[0], getGeoHRLoginCredentials[1]);
		/* Close the HCL Banner Pop Up */
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
		/* Verify the status */
		pages.previousRecordsPage.validateStatusRejectTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_rejected_geo_hr_rm"));
	}
}