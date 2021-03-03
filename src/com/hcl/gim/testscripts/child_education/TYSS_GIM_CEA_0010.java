package com.hcl.gim.testscripts.child_education;

import java.util.ArrayList;

import java.util.Map;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.commonutils.ExcelUtil;


/*TestCaseId: TYSS_GIM_CEA_0010
 *Claim Type: Child education allowance
 *TestScript Name: TYSS_GIM_CEA_0010
 *Description: Verify that approvers can approve the claims by using BULK APPROVE/REFERBACK button
 *Author : Vikas
 */
public class TYSS_GIM_CEA_0010 extends BaseTest {

	@Test(description = "Description: Verify that approvers can approve the claims by using BULK APPROVE/REFERBACK button")
	public synchronized void TC_TYSS_GIM_CEA_0010() throws FilloException 
	{
		/*Getting all the data*/
		ArrayList<String[]> arraylist = new ArrayList<String[]>();
		String employeedata[][] =ExcelUtil.getRowDataFromExcelUsingFillo("./data/GIMTestData_OtherFlows.xlsx",
				"SELECT * FROM  ChildEducationAllowance WHERE SlNo='10'");

		for (int i = 0; i < employeedata.length; i++) {
			arraylist.add(employeedata[i]); // All the row of data i e in array

		}
        
		Map<String, String> loginDetails = WebActionUtil.getLoginDetailsForCEA(prop_app_constants.getProperty("cea_empcode_tyss_10"));
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		/****** Employee LOGIN ******/
		
		/*Login to Application as employee*/
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));
		
		/* Validate My Claims Page displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		ArrayList<String> lstclaimids = new ArrayList<String>();
		for (String[] data : arraylist) {
			
			/*Click Raise New Request button than click on Child education allowance*/
			pages.homePage.raiseNewRequest();
			
			/* Raise child allowance claim for Tuition option */
			pages.childEducationAllowancePage.applyoneTimeRegistration(data[2], data[3], data[4], data[5],
					data[6], data[7], data[8], data[9], WebActionUtil.getSampleFilePath(data[10]));
			
			/* Click on Initiate Claim Button. */
			pages.childEducationAllowancePage.clickInitiateClaim();

			/* Click on 'Confirm and Initiate' Button. */
			pages.childEducationAllowancePage.clickConfirmAndInitiate();

			/* Navigate to Home Page */
			pages.homePage.goToHome();
			
			/* Click on Close button in HCL Partner manual pop up*/
			pages.homePage.closeHomeHCLBannerPopUp();
			
			/* Retrieve the claimID */
			lstclaimids.add(pages.homePage.retrieveClaimId());
		}
		
		/*logout from application*/
		pages.homePage.logout();

		/****** RM LOGIN ******/
		
		/*Login to Application as RM*/
		pages.loginpage.loginToApplication(loginDetails.get("RM"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));
		
		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/* Click Pending for Approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();
		
		/* Enter the claim numbers in "Enter Claim Number" text field by separating with commas */
		String claimidSeperatedwithComma = pages.pendingActionsPage.getclaimidwithcomma(lstclaimids);
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);
		
		/* Click Bulk Approval Button */
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"), prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/* Validate status of Claim */
		for (String claimid : lstclaimids) {
			pages.previousRecordsPage.validateStatusApproveTxt(claimid, prop_app_constants.getProperty("expectedStatus_pending_l4_head"));
		}
		
		/*Logout from application*/
		pages.homePage.logout();
		
		/****** L4 LOGIN ******/
		
		/*Login to Application as L4 */
		pages.loginpage.loginToApplication(loginDetails.get("L4"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL4Head"));
		
		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/* Click Pending for Approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();
		
		/* Enter the claim numbers in "Enter Claim Number" text field by separating with commas */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);
		
		/* Click Bulk Approval Button */
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/* Validate status of Claim */
		for (String claimid : lstclaimids) {
			pages.previousRecordsPage.validateStatusApproveTxt(claimid, prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));
		}
		
		/*Logout from application*/
		pages.homePage.logout();
		
		
		/****** GEO HR LOGIN ******/
		
		/*Login to Application as GEO HR */
		pages.loginpage.loginToApplication(loginDetails.get("GeoHR"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
		
		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/* Click Pending for Approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();
		
		/* Enter the claim numbers in "Enter Claim Number" text field by separating with commas */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);
		
		/* Click Bulk Approval Button */
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/* Validate status of Claim */
		for (String claimid : lstclaimids) {
			pages.previousRecordsPage.validateStatusApproveTxt(claimid, prop_app_constants.getProperty("expectedStatus_pending_es_level1"));
		}
		
		/*Logout from application*/
		pages.homePage.logout();
		
		
		/****** ES1 LOGIN ******/
		
		/*Login to Application as ES1 */
		pages.loginpage.loginToApplication(loginDetails.get("ES1"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1OnSite"));
		
		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/* Click Pending for Approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();
		
		/* Enter the claim numbers in "Enter Claim Number" text field by separating with commas */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);
		
		/* Click Bulk Approval Button */
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/* Validate status of Claim */
		for (String claimid : lstclaimids) {
			pages.previousRecordsPage.validateStatusApproveTxt(claimid, prop_app_constants.getProperty("expectedStatus_pending_es_level2"));
		}
		
		/*Logout from application*/
		pages.homePage.logout();
		
		/****** ES2 LOGIN ******/
		
		/*Login to Application as ES2 */
		pages.loginpage.loginToApplication(loginDetails.get("ES2"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2OnSite"));
		
		/* Validate RM home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),	prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/* Click Pending for Approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate the pending actions page */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click On Bulk Approval Refer back Button */
		pages.pendingActionsPage.clickOnBulkApprovalReferbackButton();
		
		/* Enter the claim numbers in "Enter Claim Number" text field by separating with commas */
		pages.pendingActionsPage.setBulkApprovalRemarks(claimidSeperatedwithComma);
		
		/* Click Bulk Approval Button */
		pages.pendingActionsPage.clickOnBulkApprovalButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnBulkApprovalpopupYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/* Validate status of Claim */
		for (String claimid : lstclaimids) {
			pages.previousRecordsPage.validateStatusApproveTxt(claimid, prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		}
		
		/*Logout from application*/
		pages.homePage.logout();
		
		
		/****** Employee LOGIN ******/
		
		/*Login to Application as employee*/
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));
		
		/*Validate My claims page*/
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/* Validate the status Pushed to iRem */
		for (String claimid : lstclaimids) {
		pages.homePage.validateStatusApproveTxt(claimid, prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		}
	}
}
