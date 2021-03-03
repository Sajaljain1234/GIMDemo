package com.hcl.gim.testscripts.patent_award;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId: TYSS_GIM_0018
 * Claim Type: Patent Award Allowance
 * TestScript Name: TYSS_GIM_PA_0018
 * Description: Verify the status of LOB HR for 'Patent Award' allowance claim after getting referred back by ES1 OnSite
 * 
 * Author: Sajal jain
 */
public class TYSS_GIM_PA_0018 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from PatentAward where SlNo='18'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify the status of LOB HR for 'Patent Award' allowance claim after getting referred back by ES1 OnSite using through the form option" )
	public void TC_TYSS_GIM_PA_0018(String slNo,String employeeCode, String countryCode, String companyCode, String amount,
			String fromDate, String toDate, String patentId, String remarks) throws InterruptedException {

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_patent_award_login"));
		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));
		String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));

		/****** LOB HR LOGIN ******/

		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Validate Home page and Click on Initiate on Behalf of Employee Tab */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page is displayed and Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_patent_award_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Through the form button */
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* fill the form */
		pages.throughTheFormPage.fillThePatentAward(fromDate, toDate, employeeCode, countryCode, companyCode, amount,
				remarks, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")), patentId);

		/* Click on submit button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();

		/* Validate Are you sure you want to proceed Pop up */
		pages.throughTheFormPage.validatePopup();

		/* Click on Yes button in pop up */
		pages.throughTheFormPage.clkPopUpYesButton();

		/* Validate claim success message */
		pages.throughTheFormPage.validateClaimSuccessMessage();

		/* Navigate to menu and click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Retrieve the claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/*
		 * Click on claim ID of initiated claim and Validate Claim Details Pop up is
		 * displayed
		 */
		pages.initiateClaimsPage.clkClaimID(claimID);

		/* Enter remark in claim id pop up */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("approveRemark"));

		/* Click on Approve button in claim id pop up */
		pages.initiateClaimsPage.clkAppClaimsPopUpApproveSubmit();

		/* Click on Yes button */
		pages.initiateClaimsPage.clkApproveYesThroughClaimID(claimID);

		/* logout from application */
		pages.homePage.logout();

		/****** ES1 LOGIN ******/

		/* Enter user name and Password and login as ES1 */
		pages.loginpage.loginToApplication(es1LoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1OnSite"));

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
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		/* click on claim ID of initiated claim */
		pages.pendingActionsPage.clickClaimNumber(claimID);

		/* Enter remark in claim id pop up */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click refer back button in claim id pop up and validate success message */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();

		/* Validate claim removed from pending action page */
		pages.pendingActionsPage.validateClaimIDReferBack(claimID);

		/* logout from application */
		pages.homePage.logout();

		/****** LOB HR LOGIN ******/

		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Validate Home page and Click on Initiate on Behalf of Employee Tab */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page */
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of the claim */
		pages.previousRecordsPage.validateStatusRejectTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_rejected_es_level1"));
	}
}