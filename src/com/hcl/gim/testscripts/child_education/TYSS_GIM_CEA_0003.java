package com.hcl.gim.testscripts.child_education;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFile;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId:TYSS_GIM_CEA_0003
 *Claim Type:Child education allowance
 *TestScript Name: TYSS_GIM_ELA_0003
 *Description:Verify that On Site Employee Can Raise the Child Education Claim for One time Registration for the second for same Academic year(Exception flow)
 *Author : Aatish Slathia
 */

public class TYSS_GIM_CEA_0003 extends BaseTest{
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ChildEducationAllowance where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,dependsOnMethods="com.hcl.gim.testscripts.child_education.TYSS_GIM_CEA_0001.TC_TYSS_GIM_CEA_0001",description = "Description: Verify that On Site Employee Can Raise the Child Education Claim for One time Registration for the second for same Academic year(Exception flow)")
	public synchronized void TC_TYSS_GIM_CEA_0003(String slNo, String empcode,String childName,String academicYear,String fromDate,
			String toDate,String grade,String amount,String schoolName,String remarks, String fileFormat) 
	{
		Map<String, String> loginDetails = WebActionUtil.getLoginDetailsForCEA(empcode);
	
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		/****** Employee LOGIN ******/
		
		/* Login to Application as employee */
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Click Raise New Request button than click on Child education allowance */
		pages.homePage.raiseNewRequest();

		/*Applies for one time registration*/
		pages.childEducationAllowancePage.applyoneTimeRegistrationExceptionalCase( childName, academicYear, fromDate, toDate, grade, amount, schoolName, remarks, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));
		
		/* Click on Yes button */
		pages.childEducationAllowancePage.clkExceptionYesButtonForOneTime();
		
		/*Clicks on Initiate Claim button*/
		pages.childEducationAllowancePage.clickInitiateClaim();
		
		/*Clicks on Confirm And Initiate button*/
		pages.childEducationAllowancePage.clickConfirmAndInitiate();
						
		/*navigates to Home page*/
		pages.homePage.goToHome();
		
		/*Click on Hcl Banner Popup Close Button*/
		pages.homePage.closeHomeHCLBannerPopUp();
		
		/*Retrieves claim ID*/
		String claimId=pages.homePage.retrieveClaimId();
		System.out.println(claimId);
	
		/*Clicks on Logout button*/
		pages.homePage.logout();
		
		
		
		/****** RM Login *******/
		/* Login to Application as RM */
		pages.loginpage.loginToApplication(loginDetails.get("RM"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));

		/* Click Pending for approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();

		/*Validate list of claims for approval in Pending Action Page*/
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/*Click on initiated claim check box*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);
		
		/* Click on Submit in Actions */
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Enter Remarks and Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();
		
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);
		
		/*Click on Previous Record Link*/
		pages.homePage.goToPreviousRecords();
		
		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/*Verify the Status of the Claim.*/
		pages.previousRecordsPage.validateStatusApproveTxt(claimId, prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		
		/*Click on Logout*/
		pages.homePage.logout();
		
		
		/****** L2 Head Login *******/
		/*Login as L2*/
		pages.loginpage.loginToApplication(loginDetails.get("L2"), loginDetails.get("Password"));
					
		/*Click on Hcl Banner Popup Close Button*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL2Head"));
	
		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextL2"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();
        
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"), prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleL2Head"));
					
		/*Click on initiated claim check box*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);
		
		/* Click on approve button*/
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Enter Remarks and Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();
		
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);
		
		/*Click on Previous Record Link*/
		pages.homePage.goToPreviousRecords();
		
		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/*Verify the Status of the Claim.*/
		pages.previousRecordsPage.validateStatusApproveTxt(claimId, prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));
					
		/*Click on Logout*/
		pages.homePage.logout();
		
		
		/****** Geo HR Login *******/
		/*Login as GEO HR*/
		pages.loginpage.loginToApplication(loginDetails.get("GeoHR"), loginDetails.get("Password"));
		
		/*Click on Hcl Banner Popup Close Button*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
		
		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextGeoHR"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*Click on Pending for Approval under RM Link*/
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"), prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleGEOHR"));
		
		/*Click on initiated claim check box*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);
		
		/* Click on approve button*/
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Enter Remarks and Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();
		
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);
		
		/*Click on Previous Record Link*/
		pages.homePage.goToPreviousRecords();
		
		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/*Verify the Status of the Claim.*/
		pages.previousRecordsPage.validateStatusApproveTxt(claimId, prop_app_constants.getProperty("expectedStatus_pending_es_level1"));
					
		/*Click on Logout*/
		pages.homePage.logout();
		
		
		/******Es1 Login *******/
		/*Login as ES1*/
		pages.loginpage.loginToApplication(loginDetails.get("ES1"), loginDetails.get("Password"));
		
		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));
		
		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();
        
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"), prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));
					
		/*Click on initiated claim check box*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);
		
		/* Click on approve button*/
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Enter Remarks and Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);
		
		/*Click on Previous Record Link*/
		pages.homePage.goToPreviousRecords();
		
		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
					
		/*Verify the Status of the Claim.*/
		pages.previousRecordsPage.validateStatusApproveTxt(claimId, prop_app_constants.getProperty("expectedStatus_pending_es_level2"));
		
		/*Click on Logout*/
		pages.homePage.logout();

		/******Es2 Login *******/
		/*Login as ES Level2 */
		pages.loginpage.loginToApplication(loginDetails.get("ES2"), loginDetails.get("Password"));
					
		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2"));
		
		/* Validate Home page */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
		pages.pendingActionsPage.clkPendingforApprovalTab();
        
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"), prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Validate Pending Action data is displayed */
		pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES2OnSite"));
					
		/*Click on initiated claim check box*/
		pages.pendingActionsPage.clkPaticularCheckbox(claimId);
		
		/* Click on approve button*/
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Enter Remarks and Click on SUBMIT Button. */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Click on YES. */
		pages.pendingActionsPage.clickOnYesButton();
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);
		
		/*Click on Previous Record Link*/
		pages.homePage.goToPreviousRecords();
		
		/* Validate previous records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
					
		/*Verify the Status of the Claim.*/
		pages.previousRecordsPage.validateStatusApproveTxt(claimId, prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		
		/*Click on Logout*/
		pages.homePage.logout();
		
		
		/******Employee Login *******/
		/*Login as Employee*/
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));
		
		/* Click on 'X' Button. */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));	
		
		/*validate my claim page*/
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
					
		/*verify status of initiated claim*/
	    pages.homePage.validateStatusApproveTxt(claimId,prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		
	}


}
