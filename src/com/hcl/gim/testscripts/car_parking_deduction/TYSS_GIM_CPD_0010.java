package com.hcl.gim.testscripts.car_parking_deduction;


import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 * TestCaseId:TYSS_GIM_0010 Claim Type:Car parking Deduction
 * TestSCriptName:TYSS_GIM_CPD_0010 
 * Description:Verify the status of GEO HR for 'Carparking Deduction' allowance claim after getting referred back by GEO HR 
 * @author Vivek Dogra
 *
 */
public class TYSS_GIM_CPD_0010 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from CarParkingDeduction where SlNo='10'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:Verify the status of GEO HR for 'Carparking Deduction' allowance claim after getting referred back by GEO HR ")
	public void TC_TYSS_GIM_CPD_0010(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String periodicity,
			String noofInstalments, String remarks) throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"));

		String[] geohrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] getGeoHRRMLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		String[] data = { employeeCode, countryCode, companyCode, amount, fromDate, toDate, periodicity,
				noofInstalments, remarks };
		String[] format = { "number", "number", "number", "number", "date", "date", "string", "number", "string" };
		Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);
		
		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(geohrLoginDetails[0], geohrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
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
		
		/* Click on Excel Upload Tab */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Download Form Template */
		pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		String downloadedDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);


		/* uploads the downloaded template to Upload Template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Upload or Drag and Drop your files here */
		pages.initiateClaimPage
				.clkExcelUploadorDragandDropyourfileshereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_msg")));

		/* click on Upload Button */
		pages.initiateClaimPage.clkExcelUploadButton();

		/* click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* click on Initiate Claims */
		pages.homePage.goToInitiateClaims();
		
		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* click on Submit Tool tip */
		pages.initiateClaimsPage.clkApproveClaim(claimId);

		/* Enter the Remark in Remark textareafield */
		pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* click on Submit Button */
		pages.initiateClaimsPage.clkApproveSubmit();

		/* click on Yes Button */
		pages.initiateClaimsPage.clkApproveYes(claimId);

		/* Logout as Geo HR */
		pages.homePage.logout();

		/************ GEO HR RM Login ******************/
		/* Login as GEO HR RM */
		pages.loginpage.loginToApplication(getGeoHRRMLoginCredentials[0], getGeoHRRMLoginCredentials[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));
		
		/* click on Pending Aproval Button */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate list of claims need approval is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Click on Approve Tool tip */
		pages.pendingActionsPage.referBackClaim(claimId);

		/* Enter Approval Remarks */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));

		/*
		 * Click on Submit button and Validate 'Claim request has been successfully
		 * referred back' success message
		 */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();
		
		/*
		 * Validate claim removed from pending action page and moved to next level for
		 * approval
		 */
		pages.pendingActionsPage.validateClaimIDReferBack(claimId);

		/* Logout as GEO HR RM */
		pages.homePage.logout();

		/************ GEO HR Login ******************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geohrLoginDetails[0], geohrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Geo HR Initiate of Behalf of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* click on Previous Records */
		pages.homePage.goToPreviousRecords();
		
		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Verify the status */
		pages.previousRecordsPage.validateStatusRejectTxt(claimId, prop_app_constants.getProperty("expectedStatus_rejected_geo_hr_rm"));

	}

}
