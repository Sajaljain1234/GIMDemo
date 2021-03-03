package com.hcl.gim.testscripts.transport_allowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/**
 * TestCase ID : TYSS_GIM_0022
 * Claim Name : Transport Allowance
 * TesScript Name : TYSS_GIM_TA_0022
 * Description : Verify the status of GEO HR for Transport after getting referred back by GEO HR RM
 * @author vikas.kc
 *
 */
public class TYSS_GIM_TA_0022 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from TransportAllowance where SlNo ='22'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the status of GEO HR after getting referred back by GEO HR RM")
	
	public synchronized void TC_TYSS_GIM_TA_0022(String slNo, String employeeCode, String countryCode, String companyCode,  String amount, 
			String fromDate, String toDate, String inputType, String remarks) {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_transport_allowance_login"));
		String[] geoHRLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] geoHRRMLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));

		
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/*************** GEO HR Login **************/
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

		/* Click on Through The Form Radio Button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));
		
		/* Click on Through The Form Reset Button */
		pages.throughTheFormPage.filltheTransportAllowance(fromDate, toDate, employeeCode, countryCode, companyCode, amount, remarks, inputType,
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));
		
		/* Click on Through The Form Submit Button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();
		
		/* Message saying 'Are you sure you want to proceed' Should be displayed. */
		pages.throughTheFormPage.validatePopup();
		
		/* Click on Through The Form Yes Button */
		pages.throughTheFormPage.clkPopUpYesButton();
		
		/* Claim requests has been successfully raised. Pop up message should be displayed */
		pages.throughTheFormPage.validateClaimSuccessMessage();
		
		/* Click on Initiate Claims */
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));
		
		/* Verify list of claims */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();
	
		/* Get Claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		
		/* Click on Approve Claim ID */
		pages.initiateClaimsPage.clkClaimID(claimID);
		
		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("approveRemark"));
		
		/* Click on Submit Button */
		pages.initiateClaimsPage.clkAppClaimsPopUpApproveSubmit();
		
		/* Click on Yes Button */
		pages.initiateClaimsPage.clkApproveYesThroughClaimID(claimID);
		
		/* Click on Logout Button */
		pages.homePage.logout();
	
//		String claimID = "145";
		/*************** GEO HR RM Login **************/
		/* Enter GEO HR RM user name and Password */
		pages.loginpage.loginToApplication(geoHRRMLoginCredentials[0], geoHRRMLoginCredentials[1]);
		
		/* Click on HCL Banner Popup */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));
		
		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Click on Pending For Approval */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Validate List of Claims displayed in Pending Actions Page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));
		
		/* Click on Approve Claim ID */
		pages.pendingActionsPage.clickClaimNumber(claimID);
		
		/* Enter Remarks */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click on Cancel Button */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();
		
		/* Verify list of claims */
		pages.pendingActionsPage.validateClaimIDReferBack(claimID);

		/* Click on Logout Button */
		pages.homePage.logout();
		
		
		
		/*************** GEO HR Login **************/
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
		pages.previousRecordsPage.validateStatusRejectTxt(claimID, prop_app_constants.getProperty("expectedStatus_rejected_geo_hr_rm"));

	}
}
