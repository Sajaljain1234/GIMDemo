package com.hcl.gim.testscripts.ikhaya;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM_022
 *Claim Type: Ikahya safety bonus
 *TestScript Name: TYSS_GIM_ISB_022
 *Description: Verify that the status of GEO HR for 'Ikahya safety bonus(Client-seriti/elango) allowance' Claim after getting referred back by GEO HR RM.
 *            
 *Author: Manish Kumar C D
 */

public class TYSS_GIM_ISB_022 extends BaseTest{
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql="Select * from Ikahyasafetybonus where SlNo='22'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that the status of GEO HR for 'Ikahya safety bonus(Client-seriti/elango) allowance' Claim after getting referred back by GEO HR RM.")
	public synchronized void TC_TYSS_GIM_ISB_022(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String rate,String noofInstalments,String remarks) throws InterruptedException {


		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_login"));

		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] geoHrRmLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));

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

		/*click on Claimtype Radio Button*/
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/*Fills the Ikahya Safetybonus and Uploads the File*/
		pages.throughTheFormPage.fillTheIkahyaSafetybonus(fromDate, toDate, employeeCode, countryCode, companyCode, amount, remarks, noofInstalments, rate, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/* Click on Through The Form Submit Button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();

		/* Validate Popup is displayed*/
		pages.throughTheFormPage.validatePopup();

		/* Click on Through The Form Yes Button */
		pages.throughTheFormPage.clkPopUpYesButton();

		/* Claim requests has been successfully raised. Pop up message should be displayed */
		pages.throughTheFormPage.validateClaimSuccessMessage();

		/*click on Initiate Claims*/
		pages.homePage.goToInitiateClaims();

		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/*Validate list of Claims for Approval in Initiate Claims Page*/
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on Approve Claim ID */
		pages.initiateClaimsPage.clkClaimID(claimId);

		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit Button */
		pages.initiateClaimsPage.clkAppClaimsPopUpApproveSubmit();

		/* Click on Yes Button */
		pages.initiateClaimsPage.clkApproveYesThroughClaimID(claimId);

		/* Logout as GEO HR  */
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


		/* Click on Approve Claim ID */
		pages.pendingActionsPage.clkClaimID(claimId);

		/*Enter the Remarks into Remarks TextAreafield */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("approveRemark"));

		/*Click on refer back button*/
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page. 
		 * 
		 */
		pages.pendingActionsPage.validateClaimIDReferBack(claimId);


		/*Logout as GEO HR RM*/
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
		pages.previousRecordsPage.validateStatusRejectTxt(claimId, prop_app_constants.getProperty("expectedStatus_rejected_geo_hr_rm"));



	}
}
