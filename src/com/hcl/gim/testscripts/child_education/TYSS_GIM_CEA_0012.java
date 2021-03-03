package com.hcl.gim.testscripts.child_education;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.commonutils.ExcelUtil;

/*TestCaseId: TYSS_GIM_CEA_0012
 *Claim Type: Child education allowance
 *TestScript Name: TYSS_GIM_CEA_0012
 *Description: Verify that approvers can referback the claims by using BULK
 * APPROVE/REFERBACK button Exception - Suresh
 *Author : Shreya
 */
public class TYSS_GIM_CEA_0012 extends BaseTest {

	@Test(description="Description:Verify that approvers can referback the claims by using BULK APPROVE/REFERBACK button ")
	public synchronized void TC_TYSS_GIM_CEA_0012() throws FilloException {
		/* Employee Code */

		/* Getting all the data */
		ArrayList<String[]> arraylist = new ArrayList<String[]>();
		String employeedata[][] = ExcelUtil.getRowDataFromExcelUsingFillo("./data/GIMTestData_OtherFlows.xlsx",
				"SELECT * FROM  ChildEducationAllowance WHERE SlNo='12'");

		for (int i = 0; i < employeedata.length; i++) {
			arraylist.add(employeedata[i]); // All the row of data i e in array

		}

		/* Login With Valid Credentials as Employee */
		Map<String, String> logcred = WebActionUtil.getLoginDetailsForCEA(prop_app_constants.getProperty("cea_empcode_tyss_12"));
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		pages.loginpage.loginToApplication(logcred.get("EmpCode"), logcred.get("Password"));

		/* Close the Home page */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* My Claims pages is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		ArrayList<String> lstclaimids = new ArrayList<String>();
		for (String[] data : arraylist) {

			/* Click on Raise New Request Button and click on child education allowance */
			pages.homePage.raiseNewRequest();

			/* Raise child allowance claim for Tuition option */
			pages.childEducationAllowancePage.applyoneTimeRegistrationExceptionalCase(data[2], data[3], data[4], data[5], data[6],
					data[7], data[8], data[9], WebActionUtil.getSampleFilePath(data[10]));

			/*
			 * Age should be between 3 to 18 year. If you submit than it will go to L2 Head
			 * Pop up is displayed
			 */
			pages.childEducationAllowancePage.clkExceptionYesButton();

			/* Click on Initiate Claim Button. */
			pages.childEducationAllowancePage.clickInitiateClaim();

			/* Click on 'Confirm and Initiate' Button. */
			pages.childEducationAllowancePage.clickConfirmAndInitiate();

			/* Retrieve the claim id */
			WebActionUtil.waitForAngularPageToLoad();
			pages.homePage.goToHome();

			/* Click on X Button */
			pages.homePage.closeHomeHCLBannerPopUp();

			/* Retrieve the claim id */
			lstclaimids.add(pages.homePage.retrieveClaimId());

		}
		/* Logout As Employee */
		pages.homePage.logout();

		/********** Refer back by RM **********/

		/* Login With Valid Credentials as RM. */
		pages.loginpage.loginToApplication(logcred.get("RM"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under RM Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		String claimidSeperatedwithComma = pages.pendingActionsPage.getclaimidwithcomma(lstclaimids);
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("rejectRemark"));

		/* Click on Refer back button */
		pages.pendingActionsPage.clickOnBulkReferBackButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusRejectTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_rejected_rm"));
		}

		/* Log Out as RM */
		pages.homePage.logout();

		/********** Re initiate the claim by Employee *****/

		/* Login as the Employee */
		pages.loginpage.loginToApplication(logcred.get("EmpCode"), logcred.get("Password"));

		/* Click on Close (X) button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Edit all the claims */
		for (String claimid : lstclaimids) {
			WebActionUtil.waitForAngularPageToLoad();
			/* Click on edit icon of the rejected claims */
			pages.homePage.clickEditClaim(claimid);

			/* Modify any required fields. */
			/* Click on update claim button */
			pages.childEducationAllowancePage
					.updateClaimRequestExceptionalCase(prop_app_constants.getProperty("newAmount"));

			/*
			 * Age should be between 3 to 18 year. If you submit than it will go to L2 Head
			 * Pop up is displayed
			 */
			WebActionUtil.waitForAngularPageToLoad();
			pages.childEducationAllowancePage.clkExceptionYesButtonAfterEdit();

			/*Close HCL banner pop up*/
			pages.homePage.closeHomeHCLBannerPopUp();

		}

		/* Validate My claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Click on home link under drop down & confirm the claim has been re-initiate
		 */

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.homePage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_rm"));

		}

		/* Logout As Employee */
		pages.homePage.logout();

		/********** Approve the claim by RM *****/

		/* Login as RM */
		pages.loginpage.loginToApplication(logcred.get("RM"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under RM Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		}

		/* Log Out as RM */
		pages.homePage.logout();

		/********** Reject By the L2 *****/

		/* Login as L2 Head */
		pages.loginpage.loginToApplication(logcred.get("L2"), logcred.get("Password"));

		/*Close HCL Banner POP UP*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate L2 home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under L2 Head Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click on BULK APPROVAL/REFERBACK button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("rejectRemark"));

		/* Click On REFERBACK button On the Pop up Form */
		pages.pendingActionsPage.clickOnBulkReferBackButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusRejectTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_rejected_l2_head"));
		}

		/* Log Out as L2 */
		pages.homePage.logout();

		/********** Re initiate by the Employee *****/
		/* Login as the Employee */
		pages.loginpage.loginToApplication(logcred.get("EmpCode"), logcred.get("Password"));

		/* Click on Close (X) button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Edit all the claims */
		for (String claimid : lstclaimids) {
			WebActionUtil.waitForAngularPageToLoad();
			/* Click on edit icon of the rejected claims */
			pages.homePage.clickEditClaim(claimid);

			/* Modify any required fields. */
			/* Click on update claim button */
			pages.childEducationAllowancePage
					.updateClaimRequestExceptionalCase(prop_app_constants.getProperty("newAmount"));

			/*
			 * Age should be between 3 to 18 year. If you submit than it will go to L2 Head
			 * Pop up is displayed
			 */
			WebActionUtil.waitForAngularPageToLoad();
			pages.childEducationAllowancePage.clkExceptionYesButtonAfterEdit();
			
             /*Close HCL Banner Pop Up*/
			pages.homePage.closeHomeHCLBannerPopUp();

		}

		/* Validate My claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));


		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.homePage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_rm"));

		}

		/* Logout As Employee */
		pages.homePage.logout();

		/********** Approve the claim by RM *****/

		/* Login as RM */
		pages.loginpage.loginToApplication(logcred.get("RM"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under RM Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		}

		/* Log Out as RM */
		pages.homePage.logout();

		/********** Approve By the L2 *****/

		/* Login as L2 */
		pages.loginpage.loginToApplication(logcred.get("L2"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL2Head"));

		/* Validate L2 home page is displayed */

		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under L2 Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

	
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));
		}

		/* Log Out as L2 */
		pages.homePage.logout();

		/******* Reject by Geo HR *****/

		/* Login as Geo HR */
		pages.loginpage.loginToApplication(logcred.get("GeoHR"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));
		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under GEO HR Head Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the Pending Actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click on BULK APPROVAL/REFERBACK button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("rejectRemark"));
		/* Click On REFERBACK button On the Pop up Form */
		pages.pendingActionsPage.clickOnBulkReferBackButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusRejectTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_rejected_geo_hr"));
		}

		/* Log Out as GEO HR */
		pages.homePage.logout();

		/********** Re initiate by the Employee *****/
		/* Login as the Employee */
		pages.loginpage.loginToApplication(logcred.get("EmpCode"), logcred.get("Password"));

		/* Click on Close (X) button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Edit all the claims */
		for (String claimid : lstclaimids) {
			WebActionUtil.waitForAngularPageToLoad();
			/* Click on edit icon of the rejected claims */
			pages.homePage.clickEditClaim(claimid);

			/* Modify any required fields. */
			/* Click on update claim button */
			pages.childEducationAllowancePage
					.updateClaimRequestExceptionalCase(prop_app_constants.getProperty("newAmount"));

			/*
			 * Age should be between 3 to 18 year. If you submit than it will go to L2 Head
			 * Pop up is displayed
			 */
			WebActionUtil.waitForAngularPageToLoad();
			pages.childEducationAllowancePage.clkExceptionYesButtonAfterEdit();
/*Close HCL Banner POP up*/
			pages.homePage.closeHomeHCLBannerPopUp();

		}

		/* Validate My claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Click on home link under drop down & confirm the claim has been re-initiate
		 */

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.homePage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_rm"));

		}

		/* Logout As Employee */
		pages.homePage.logout();

		/********** Approve the claim by RM *****/

		/* Login as RM */
		pages.loginpage.loginToApplication(logcred.get("RM"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under RM Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		}

		/* Log Out as RM */
		pages.homePage.logout();

		/********** Approve By the L2 *****/

		/* Login as L2 */
		pages.loginpage.loginToApplication(logcred.get("L2"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL2Head"));

		/* Validate L2 home page is displayed */

		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under L2 Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));
		}

		/* Log Out as L2 */
		pages.homePage.logout();

		/********** Approve By the GEO HR *****/

		/* Login as GEO HR */
		pages.loginpage.loginToApplication(logcred.get("GeoHR"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Validate RM home page is displayed */

		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under GeoHR Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_es_level1"));
		}

		/* Log Out as GEO HR */
		pages.homePage.logout();

		/********* Reject by ES Level 1 *******/
		/* Login as ES Level 1 Head */
		pages.loginpage.loginToApplication(logcred.get("ES1"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1OnSite"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under ESLevel 1 Head Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click on BULK APPROVAL/REFERBACK button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("rejectRemark"));
		/* Click On REFERBACK button On the Pop up Form */
		pages.pendingActionsPage.clickOnBulkReferBackButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusRejectTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_rejected_es_level1"));
		}

		/* Log Out as ES Level 1 */
		pages.homePage.logout();

		/********** Re initiate by the Employee *****/
		/* Login as the Employee */
		pages.loginpage.loginToApplication(logcred.get("EmpCode"), logcred.get("Password"));

		/* Click on Close (X) button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Edit all the claims */
		for (String claimid : lstclaimids) {
			WebActionUtil.waitForAngularPageToLoad();
			/* Click on edit icon of the rejected claims */
			pages.homePage.clickEditClaim(claimid);

			/* Modify any required fields. */
			/* Click on update claim button */
			pages.childEducationAllowancePage
					.updateClaimRequestExceptionalCase(prop_app_constants.getProperty("newAmount"));

			/*
			 * Age should be between 3 to 18 year. If you submit than it will go to L2 Head
			 * Pop up is displayed
			 */
			WebActionUtil.waitForAngularPageToLoad();
			pages.childEducationAllowancePage.clkExceptionYesButtonAfterEdit();
/*Close Home HCL Banner PopUp*/
			pages.homePage.closeHomeHCLBannerPopUp();

		}

		/* Validate My claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.homePage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_rm"));

		}

		/* Logout As Employee */
		pages.homePage.logout();

		/********** Approve the claim by RM *****/

		/* Login as RM */
		pages.loginpage.loginToApplication(logcred.get("RM"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under RM Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		}

		/* Log Out as RM */
		pages.homePage.logout();

		/********** Approve By the L2 *****/

		/* Login as L2 */
		pages.loginpage.loginToApplication(logcred.get("L2"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL2Head"));

		/* Validate L2 home page is displayed */

		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under L2 Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));
		}

		/* Log Out as L2 */
		pages.homePage.logout();

		/********** Approve By the GEO HR *****/

		/* Login as GEO HR */
		pages.loginpage.loginToApplication(logcred.get("GeoHR"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Validate RM home page is displayed */

		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under GeoHR Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_es_level1"));
		}

		/* Log Out as GEO HR */
		pages.homePage.logout();

		/****** Approve Claim by ESlevel 1 ******/
		
		/* Login as ES Level 1 */
		pages.loginpage.loginToApplication(logcred.get("ES1"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under L2 Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();

		/* Click on yes button on confirmation pop up */
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_es_level2"));
		}

		/* Log Out as ES Level 1 */
		pages.homePage.logout();

		/****** Reject Claim by ESlevel 2 ******/

		/* Login as ES Level 2 */
		pages.loginpage.loginToApplication(logcred.get("ES2"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2OnSite"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under ESLevel 1 Head Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the Pending Actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click on BULK APPROVAL/REFERBACK button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("rejectRemark"));
		/* Click On REFERBACK button On the Pop up Form */
		pages.pendingActionsPage.clickOnBulkReferBackButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusRejectTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_rejected_es_level2"));
		}

		/* Log Out as ES Level 2 */
		pages.homePage.logout();

		/********** Re initiate by the Employee *****/
		/* Login as the Employee */
		pages.loginpage.loginToApplication(logcred.get("EmpCode"), logcred.get("Password"));

		/* Click on Close (X) button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Edit all the claims */
		for (String claimid : lstclaimids) {
			WebActionUtil.waitForAngularPageToLoad();
			/* Click on edit icon of the rejected claims */
			pages.homePage.clickEditClaim(claimid);

			/* Modify any required fields. */
			/* Click on update claim button */
			pages.childEducationAllowancePage
					.updateClaimRequestExceptionalCase(prop_app_constants.getProperty("newAmount"));

			/*
			 * Age should be between 3 to 18 year. If you submit than it will go to L2 Head
			 * Pop up is displayed
			 */
			WebActionUtil.waitForAngularPageToLoad();
			pages.childEducationAllowancePage.clkExceptionYesButtonAfterEdit();
 /*Close HCL Banner Pop Up*/
			pages.homePage.closeHomeHCLBannerPopUp();

		}

		/* Validate My claims Page */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

	

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.homePage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_rm"));

		}

		/* Logout As Employee */
		pages.homePage.logout();

		/********** Approve the claim by RM *****/

		/* Login as RM */
		pages.loginpage.loginToApplication(logcred.get("RM"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under RM Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		}

		/* Log Out as RM */
		pages.homePage.logout();

		/********** Approve By the L2 *****/

		/* Login as L2 */
		pages.loginpage.loginToApplication(logcred.get("L2"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL2Head"));

		/* Validate L2 home page is displayed */

		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under L2 Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));
		}

		/* Log Out as L2 */
		pages.homePage.logout();

		/********** Approve By the GEO HR *****/

		/* Login as GEO HR */
		pages.loginpage.loginToApplication(logcred.get("GeoHR"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Validate GEO HR home page is displayed */

		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under GeoHR Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_es_level1"));
		}

		/* Log Out as GEO HR */
		pages.homePage.logout();

		/****** Approve Claim by ESlevel 1 ******/
		/* Login as ES Level HR */
		pages.loginpage.loginToApplication(logcred.get("ES1"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));

		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under L2 Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();

		/* Click on yes button on confirmation pop up */
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pending_es_level2"));
		}

		/* Log Out as ES Level 1 */
		pages.homePage.logout();

		/****** Approve Claim by ESlevel 2 ******/
		/* Login as ES Level 1 */
		pages.loginpage.loginToApplication(logcred.get("ES2"), logcred.get("Password"));

		/* Click on Close (X) Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));
		
		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click On Pending For Approval Under ES2 Link */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(
				prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),
				prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();

		/*
		 * Enter the claim numbers in "Enter Claim Number" text field by separating with
		 * commas
		 */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);

		/* Enter the remarks and approve */
		pages.pendingActionsPage.setRemarksBulkApprovalReferBackPopUp(prop_app_constants.getProperty("approveRemark"));
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();

		/* Click On Drop down Icon */
		pages.homePage.goToPreviousRecords();

		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		for (String claimid : lstclaimids) {
			/* Verify the status of the claim */
			pages.previousRecordsPage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		}

		/* Log Out as ES Level 2 */
		pages.homePage.logout();

		/********
		 * Login as Employee to validate whether claim is pushed to irem
		 **********/

		/* Login with Employee with valid credentials */
		pages.loginpage.loginToApplication(logcred.get("EmpCode"), logcred.get("Password"));

		/* Click on X Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* My Claims pages is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate the status Pushed to Irem */
		for (String claimid : lstclaimids) {
			pages.homePage.validateStatusApproveTxt(claimid,
					prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		}
	}

}
