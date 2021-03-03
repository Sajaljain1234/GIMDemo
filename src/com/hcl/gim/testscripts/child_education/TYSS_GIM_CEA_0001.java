package com.hcl.gim.testscripts.child_education;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFile;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId:TYSS_GIM_CEA_0001
 *Claim Type:Child education allowance
 *TestScript Name: TYSS_GIM_CEA_0001
 *Description:This test case is to verify that onsite employee can raise the child education claim for the first time
 * for one time registration
 *Author : Abhilash
 * 
 */

public class TYSS_GIM_CEA_0001 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ChildEducationAllowance where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: verify that onsite employee can raise the child education claim for the first time for one time registration")
	public synchronized void TC_TYSS_GIM_CEA_0001(String slNo, String empcode, String childName, String academicYear,
			String fromDate, String toDate, String grade, String amount, String schoolName, String remarks,
			String fileFormat) throws InterruptedException {
       
		Map<String, String> logindata = WebActionUtil.getLoginDetailsForCEA(empcode);
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		
		/****** Employee Login *******/
		/* Login as Employee */
		pages.loginpage.loginToApplication(logindata.get("EmpCode"), logindata.get("Password"));
         
		/* Click on HCL Banner Pop up Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* validate my claim page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * click on Raise New Request Button and click on Child Education Allowance
		 * Button
		 */
		pages.homePage.raiseNewRequest();

		/* Applies for one time registration */
		pages.childEducationAllowancePage.applyoneTimeRegistration(childName, academicYear, fromDate, toDate, grade,
				amount, schoolName, remarks, WebActionUtil.getSampleFilePath(fileFormat));

		/* Clicks on Initiate Claim button */
		pages.childEducationAllowancePage.clickInitiateClaim();

		/* Clicks on Confirm And Initiate button */
		pages.childEducationAllowancePage.clickConfirmAndInitiate();

		/* navigates to Home page */
		pages.homePage.goToHome();

		/* Click on HCL Banner Pop up Close Button */
		pages.homePage.closeHomeHCLBannerPopUp();

		/* Retrieves claim ID */
		String claimId = pages.homePage.retrieveClaimId();

		/* Clicks on Logout button */
		pages.homePage.logout();

		/****** RM Login *******/
		/* Login as RM */
		pages.loginpage.loginToApplication(logindata.get("RM"), logindata.get("Password"));

		/* Click on HCL Banner Pop up Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextRM"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleRM"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/* Click on Submit in Actions */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Click on Previous Record Link */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Verify the Status of the Claim. */
		pages.previousRecordsPage.validateStatusApproveTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_pending_l4_head"));

		/* Click on Logout */
		pages.homePage.logout();

		/****** L4 Head Login *******/
		/* Login as L4 */
		pages.loginpage.loginToApplication(logindata.get("L4"), logindata.get("Password"));

		/* Click on HCL Banner Pop up Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL4Head"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextL4"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleL4Head"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/* Click on approve button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Click on Previous Record Link */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Verify the Status of the Claim. */
		pages.previousRecordsPage.validateStatusApproveTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));

		/* Click on Logout */
		pages.homePage.logout();

		/****** GEO HR Login *******/
		/* Login as GEO HR */
		pages.loginpage.loginToApplication(logindata.get("GeoHR"), logindata.get("Password"));

		/* Click on HCL Banner Pop up Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextGeoHR"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on Pending for Approval under RM Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/* Click on approve button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();

		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Click on Previous Record Link */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Verify the Status of the Claim. */
		pages.previousRecordsPage.validateStatusApproveTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_pending_es_level1"));

		/* Click on Logout */
		pages.homePage.logout();

		/****** Es1 Login *******/
		/* Login as ES1 */
		pages.loginpage.loginToApplication(logindata.get("ES1"), logindata.get("Password"));

		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/* Click on approve button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Click on Previous Record Link */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Verify the Status of the Claim. */
		pages.previousRecordsPage.validateStatusApproveTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_pending_es_level2"));

		/* Click on Logout */
		pages.homePage.logout();

		/****** Es2 Login *******/
		/* Login as ES Level2 */
		pages.loginpage.loginToApplication(logindata.get("ES2"), logindata.get("Password"));

		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2"));

		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES2OnSite"));

		/* Click on initiated claim check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);

		/* Click on approve button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);

		/* Click on Previous Record Link */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Verify the Status of the Claim. */
		pages.previousRecordsPage.validateStatusApproveTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));

		/* Click on Logout */
		pages.homePage.logout();

		/****** Employee Login *******/
		/* Login as Employee */
		pages.loginpage.loginToApplication(logindata.get("EmpCode"), logindata.get("Password"));

		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* validate my claim page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* verify status of initiated claim */
		pages.homePage.validateStatusApproveTxt(claimId,
				prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));

	}

}
