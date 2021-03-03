package com.hcl.gim.testscripts.car_parking_deduction;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/**
 * TestCaseId:TYSS_GIM_0012 Claim Type:Car parking Deduction
 * TestSCriptName:TYSS_GIM_CPD_0013 Description:Verify the status of GEO HR for
 * 'Car Parking Deduction' allowance' claim after getting Rejected by GEO HR RM

 * 
 * @author Vivek Dogra
 *
 */
public class TYSS_GIM_CPD_0013 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from CarParkingDeduction where SlNo='13'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:Verify the status of GEO HR for 'Car Parking Deduction' allowance' claim after getting Rejected by GEO HR RM")
	public synchronized void TC_TYSS_GIM_CPD_0013(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String periodicity,
			String noofInstalments, String remarks) {

		Map<String, String[]> logindata = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"));
		String[] getGeoHRLoginCredentials = logindata.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] getGeoHRRMLoginCredentials = logindata.get(prop_app_constants.getProperty("roleGEOHRRM"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/************ GEO HR Login ******************/
		/* Login as Geo HR */
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

		/* Click on 'Through the Form' */
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* Enter below Details for Client Bonus Reward Incentive */
		pages.throughTheFormPage.filltheCarParkingDeduction(fromDate, toDate, employeeCode, countryCode, companyCode,
				amount, remarks, periodicity, noofInstalments,
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_pdf")));

		/* Click on Through The Form Submit Button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();

		/* Message saying 'Are you sure you want to proceed' Should be displayed. */
		pages.throughTheFormPage.validatePopup();

		/* Click on Through The Form Yes Button */
		pages.throughTheFormPage.clkPopUpYesButton();

		/*
		 * Claim requests has been successfully raised. Pop up message should be
		 * displayed
		 */
		pages.throughTheFormPage.validateClaimSuccessMessage();

		/* Click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		/* Click on Approve Claim ID */
		pages.initiateClaimsPage.clkClaimID(claimId);

		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Submit Button */
		pages.initiateClaimsPage.clkAppClaimsPopUpApproveSubmit();

		/* Click on Yes Button */
		pages.initiateClaimsPage.clkApproveYesThroughClaimID(claimId);

		/* Logout as GEO HR */
		pages.homePage.logout();

		/************ GEO HR RM Login ******************/
		/* Login as GEO HR RM */
		pages.loginpage.loginToApplication(getGeoHRRMLoginCredentials[0], getGeoHRRMLoginCredentials[1]);

		/* Close the HCL Banner PopUp */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));

		/* click on Pending Approval Button */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate list of claims need approval is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Click on Approve Claim ID */
		pages.pendingActionsPage.clkClaimID(claimId);

		/* Enter the Remarks into Remarks TextAreafield */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click on refer back button */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();

		/*
		 * Validate claim removed from pending action page and moved to next level for
		 * approval
		 */
		pages.pendingActionsPage.validateClaimIDReferBack(claimId);

		/* Logout as GEO HR RM */
		pages.homePage.logout();

		/************ GEO HR Login ******************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(getGeoHRLoginCredentials[0], getGeoHRLoginCredentials[1]);

		/* Click on Hcl Banner Popup Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
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
		pages.previousRecordsPage.validateStatusRejectTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_rejected_geo_hr_rm"));

	}
}
