package com.hcl.gim.testscripts.exgratia_language_allowance;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/*TestCaseId:TYSS_GIM_0018
 *Claim Type:Exgatia - Language allowance
 *TestScript Name: TYSS_GIM_ELA_0018
 *Description:Verify the status of GEO HR Exgratia-Language allowance claim after getting referred back by ES1 Onsite. 
 *            
 *Author : Harsha K
 * 
 * */

public class TYSS_GIM_ELA_0018 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaLanguageAllowance where SlNo ='18'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify the status of GEO HR Exgratia-Language allowance claim after getting referred back by ES1 Onsite.")
	public synchronized void TC_TYSS_GIM_ELA_0018(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String rate, String noOfInstallments, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_la_login"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Get login details for role Geo HR & ES1 */
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));

		/*************** GeoHR Login to upload & approve the claim *************/
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
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* Fill the form with datas */
		pages.throughTheFormPage.fillTheExgratiaLanguageAllowance(fromDate, toDate, employeeCode, countryCode,
				companyCode, amount, remarks, rate, noOfInstallments,
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));

		/* Click on Through The Form Submit Button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();

		/* Validate Popup is displayed */
		pages.throughTheFormPage.validatePopup();

		/* Click on Through The Form Yes Button */
		pages.throughTheFormPage.clkPopUpYesButton();

		/*
		 * Claim requests has been successfully raised. Pop up message should be
		 * displayed
		 */
		pages.throughTheFormPage.validateClaimSuccessMessage();

		// Click on Menu Bar and Click on Initiate Claims
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));

		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Validate list of Claims for Approval in Initiate Claims Page */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on Approve Claim ID */
		pages.initiateClaimsPage.clkClaimID(claimId);
		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(remarks);

		/* Click on Submit Button */
		pages.initiateClaimsPage.clkAppClaimsPopUpApproveSubmit();

		/* Click on Yes Button */
		pages.initiateClaimsPage.clkApproveYesThroughClaimID(claimId);

		/* Logout as Geo HR */
		pages.homePage.logout();

		/************ ES1 Login to refer back(Rejected) from ES-1 ***********/

		// Login as ES1

		pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);
		// Close the HCL Banner Pop Up
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

		/* Click on Approve Claim ID */
		pages.pendingActionsPage.clkClaimID(claimId);

		/* Enter the Remarks into Remarks TextAreafield */
		pages.pendingActionsPage.setApproveOrReferBackRemark(remarks);

		/* Click on refer back button */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimIDReferBack(claimId);

		/* Logout as ES1 */
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

		/* Go to Previous records page */
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
