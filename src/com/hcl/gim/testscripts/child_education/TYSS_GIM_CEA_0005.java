package com.hcl.gim.testscripts.child_education;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFile;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
import com.hcl.gim.util.WebActionUtil;

/*
 * TestCaseId: TYSS_GIM_CEA_0005
 * Claim Type: Child Education Allowance
 * TestScript Name: TYSS_GIM_CEA_0005
 * Description: Verify that On Site Employee Can Raise the Child Education Claim For the First Time for Tuition Fees
 * 
 * Author: Sajal jain
 */
public class TYSS_GIM_CEA_0005 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ChildEducationAllowance where SlNo ='5'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that On Site Employee Can Raise the Child Education Claim For the First Time for Tuition Fees")
	public synchronized void TC_TYSS_GIM_CEA_0005(String slNo, String empCode, String childName, String academicYear,
			String fromDate, String toDate, String grade, String amount, String schoolName, String remarks,
			String fileFormat) {

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		Map<String, String> loginDetails = WebActionUtil.getLoginDetailsForCEA(empCode);

		/****** Employee LOGIN ******/

		/* Login to Application as Employee */
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click Raise New Request button than click on Child education allowance */
		pages.homePage.raiseNewRequest();

		/* Fill the Child allowance form */
		pages.childEducationAllowancePage.applytuitionFees(childName, academicYear, fromDate, toDate, grade, amount,
				schoolName, remarks, WebActionUtil.getSampleFilePath(fileFormat));

		/* Click initiate claim button */
		pages.childEducationAllowancePage.clickInitiateClaim();

		/* Click confirm and initiate button */
		pages.childEducationAllowancePage.clickConfirmAndInitiate();

		/* Navigate to menu and click on Home Link */
		pages.homePage.goToHome();

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();

		/* retrieve claim ID */
		String claimID = pages.homePage.retrieveClaimId();

		/* logout from application */
		pages.homePage.logout();

		/****** RM LOGIN ******/

		/* Login to Application as RM */
		pages.loginpage.loginToApplication(loginDetails.get("RM"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Validate Home page is displayed */
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

		/* CLick on Approve button */
		pages.pendingActionsPage.referApproveClaim(claimID);

		/* Click Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click Yes button */
		pages.pendingActionsPage.clickOnYesButton();

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_pending_l4_head"));

		/* Logout from application */
		pages.homePage.logout();

		/****** L4 LOGIN ******/

		/* Login to Application as L4 */
		pages.loginpage.loginToApplication(loginDetails.get("L4"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL4Head"));

		/* Validate Home page is displayed */
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

		/* CLick on Approve button */
		pages.pendingActionsPage.referApproveClaim(claimID);

		/* Click Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click Yes button */
		pages.pendingActionsPage.clickOnYesButton();

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));

		/* Logout from application */
		pages.homePage.logout();

		/****** GEO HR LOGIN ******/

		/* Login to Application as GEO HR */
		pages.loginpage.loginToApplication(loginDetails.get("GeoHR"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Validate Home page is displayed */
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

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);

		/* CLick on Approve button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click Yes button */
		pages.pendingActionsPage.clickOnYesButton();

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_pending_es_level1"));

		/* Logout from application */
		pages.homePage.logout();

		/****** ES1 LOGIN ******/

		/* Login to Application as ES-1 */
		pages.loginpage.loginToApplication(loginDetails.get("ES1"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1OnSite"));

		/* Validate Home page is displayed */
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

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);

		/* CLick on Approve button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click Yes button */
		pages.pendingActionsPage.clickOnYesButton();

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_pending_es_level2"));

		/* Logout from application */
		pages.homePage.logout();

		/****** ES2 LOGIN ******/

		/* Login to Application as ES2 */
		pages.loginpage.loginToApplication(loginDetails.get("ES2"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2OnSite"));

		/* Validate Home page is displayed */
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

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);

		/* CLick on Approve button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click Submit button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click Yes button */
		pages.pendingActionsPage.clickOnYesButton();

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));

		/* Logout from application */
		pages.homePage.logout();

		/****** Employee LOGIN ******/

		/* Login to Application as Employee */
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));

	}
}