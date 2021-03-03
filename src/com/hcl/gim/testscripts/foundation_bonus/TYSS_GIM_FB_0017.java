package com.hcl.gim.testscripts.foundation_bonus;
import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId:TYSS_GIM_0017 
 * Claim Type:Foundation Bonus Geo allowance TestSCript
 * Name:TYSS_GIM_FB_0017 
 * Description:Verify the status of GEo HR for 'Foundation Bonus Geo' allowance claim after getting referred back by GEO HR RM.
 * 
 * @author Suganthini
 *
 */
public class TYSS_GIM_FB_0017 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from FoundationBonusGeo where SlNo ='17'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify the status of GEO HR for 'Foundation Bonus' allowance claim after getting referred back by GEO HR RM OnSite using through the form option" )
	public void TC_TYSS_GIM_FB_0017(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String paymentType, String remarks)
			throws InterruptedException {
		
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_login"));
		String[] geohrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] geohrrmLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHRRM"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

	
		
		/******* GEO HR Login*********/

		/* Enter user name and Password and login as GEO HR */
		pages.loginpage.loginToApplication(geohrLoginDetails[0], geohrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Through the form */
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* fill the form */
		pages.throughTheFormPage.fillTheFoundationBonusGeo(fromDate, toDate, employeeCode, countryCode, companyCode,
				amount, remarks, paymentType,
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));

		/* Click on submit button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();

		/* Validate Are you sure you want to proceed Pop up */
		pages.throughTheFormPage.validatePopup();

		/* Click on Yes button in pop up */
		pages.throughTheFormPage.clkPopUpYesButton();

		/* Validate claim success message */
		// WebActionUtil.waitForAngularPageload();
		pages.throughTheFormPage.validateClaimSuccessMessage();

		/* click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Retrieve the claim ID */
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Validate List of all the claims in InitiateClaimPage */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* click on claim ID of initiated claim */
		pages.initiateClaimsPage.clkClaimID(claimId);

		/* Enter remark in claim id pop up */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Approve button in claim id pop up */
		pages.initiateClaimsPage.clkAppClaimsPopUpApproveSubmit();

		/* Click on Yes button */
		pages.initiateClaimsPage.clkApproveYesThroughClaimID(claimId);
		/* Logout from the application */
		pages.homePage.logout();

		/******* GEO HR RM Login*********/

		/* Enter user name and Password and login as GEO HR RM */
		pages.loginpage.loginToApplication(geohrrmLoginDetails[0], geohrrmLoginDetails[1]);

		/* Click on Close button in HCL Banner Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHRRM"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on Pending for approval link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate List of all claims in pending page */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHRRM"));

		/* click on claim ID of initiated claim */
		pages.pendingActionsPage.clkClaimID(claimId);

		/* Enter remark in claim id pop up */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click cancel button in claim id pop up and validate success message */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();

		/*
		 * validate claim removed from pending action page 
		 */
		pages.pendingActionsPage.validateClaimIDReferBack(claimId);

		/* logout from application */
		pages.homePage.logout();

		/******* GEO HR Login*********/

		/* Enter user name and Password and login as GEO HR */
		pages.loginpage.loginToApplication(geohrLoginDetails[0], geohrLoginDetails[1]);

		/* Click on Close button in HCL Banner Pop up */
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

		/* Validate status of the claim */
		pages.previousRecordsPage.validateStatusRejectTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_rejected_geo_hr_rm"));

	}
}
