package com.hcl.gim.testscripts.child_education;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId: TYSS_GIM_CEA_0009
 *Claim Type: Child education allowance
 *TestScript Name: TYSS_GIM_ELA_0009
 *Description: Verify that On Site Employee Can Re-initiate the Child Education Claim Refered Back by All the Approvers for Tution Fees - Exception case
 *Author : Vikas
 */
public class TYSS_GIM_CEA_0009 extends BaseTest {


	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ChildEducationAllowance SlNo ='9'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that On Site Employee can re-initiate the Child Education claim refered back by all the approvers for Tution Fees - Exception case")
	
	public synchronized void TC_TYSS_GIM_CEA_0009(String slNo,String empCode, String childName, String academicYear, String fromDate,
			String toDate, String grade, String amount, String schoolName, String remarks, String fileFormat)
	{
		Map<String, String> loginDetails = WebActionUtil.getLoginDetailsForCEA(empCode);
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		/****** Employee LOGIN ******/
		
		/*Login to Application as employee*/
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));
		
		/* Validate My Claims Page displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/*Click Raise New Request button than click on Child education allowance*/
		pages.homePage.raiseNewRequest();
		
		/* Fill the Child allowance form */
		pages.childEducationAllowancePage.applytuitionFeesExceptionalCase(childName, academicYear, fromDate, toDate, grade, amount, schoolName, remarks,
				WebActionUtil.getSampleFilePath(fileFormat));
		
		/* Click on Yes Button */
		pages.childEducationAllowancePage.clkExceptionYesButton();
		
		/*Click initiate claim button*/
		pages.childEducationAllowancePage.clickInitiateClaim();
		
		/*Click confirm and initiate button*/
		pages.childEducationAllowancePage.clickConfirmAndInitiate();

		/* Navigate to Home Page */
		pages.homePage.goToHome();
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHomeHCLBannerPopUp();

		/* Retrieve the claimID */
		String claimID = pages.homePage.retrieveClaimId();

		/*logout from application*/
		pages.homePage.logout();
		
		/****** RM LOGIN ******/
		
		/*Login to Application as RM */
		pages.loginpage.loginToApplication(loginDetails.get("RM"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));
		
		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/*Click Pending for approval tab*/
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click on Claim Check Box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);
		
		/* Click on Approve Button */
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"), prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/* Validate Status of Claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		
		/* Logout from Application */
		pages.homePage.logout();

		
		/****** L2 LOGIN ******/
		
		/* Login to Application as L2 Head */
		pages.loginpage.loginToApplication(loginDetails.get("L2"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL2Head"));
		
		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/*Click Pending for approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click on Claim Check Box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);
		
		/* Click on Refer Back Button */
		pages.pendingActionsPage.clickOnReferbackButton();
		
		/* Enter Remarks */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();
		
		/* Logout from Application */
		pages.homePage.logout();
		
		/****** Employee LOGIN ******/
		
		/*Login to Application as employee*/
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));
		
		/* Validate My Claims Page displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*Click on Edit Button*/
		pages.homePage.clickEditClaim(claimID);
		
		/* Update the claim */
		pages.childEducationAllowancePage.updateClaimRequestExceptionCase(prop_app_constants.getProperty("newAmount"));	
		
		/* Click on Yes Button */
		pages.childEducationAllowancePage.clkExceptionYesButtonAfterEdit();
		
		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();
	
		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_rm"));

		/* Logout from Application */
		pages.homePage.logout();
		
		
		/****** RM LOGIN ******/
		
		/*Login to Application as RM*/
		pages.loginpage.loginToApplication(loginDetails.get("RM"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp("RM");
		
		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/*Click Pending for approval tab*/
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

		/* Click on Claim Check Box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);
		
		/* Click on Approve Button */
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"), prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/* Validate Status of Claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		
		/*Logout from application*/
		pages.homePage.logout();
		
		
		/****** L2 LOGIN ******/
		
		/*Login to Application as L2 */
		pages.loginpage.loginToApplication(loginDetails.get("L2"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL2Head"));

		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click Pending for approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click on Claim Check Box */
		pages.pendingActionsPage.clickClaimNumber(claimID);
		
		/* Enter Remarks */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));
		
		/* Click on Refer Back Button */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
		
		/* Validate Status of Claim */
		pages.previousRecordsPage.validateStatusRejectTxt(claimID, prop_app_constants.getProperty("expectedStatus_rejected_l2_head"));
		
		/* Logout from Application */
		pages.homePage.logout();
		
		
		
		/****** Employee LOGIN ******/
		
		/*Login to Application as employee */
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));
		
		/* Validate My Claims Page displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
		
		/*Click on Edit Button*/
		pages.homePage.clickEditClaim(claimID);
		
		/*Update the claim*/
		pages.childEducationAllowancePage.updateClaimRequestExceptionCase(prop_app_constants.getProperty("newAmount"));	
		
		/* Click on Yes Button */
		pages.childEducationAllowancePage.clkExceptionYesButtonAfterEdit();
		
		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();
	
		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_rm"));

		/* Logout from Application */
		pages.homePage.logout();
		
		
		/****** RM LOGIN ******/
		
		/*Login to Application as RM */
		pages.loginpage.loginToApplication(loginDetails.get("RM"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleRM"));
		
		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click Pending for approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click on Claim Check Box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);
		
		/* Click on Approve Button */
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate Status of Claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_l2_head"));
		
		/*Logout from application*/
		pages.homePage.logout();
		
		
		/****** L2 LOGIN ******/
		
		/*Login to Application as RM*/
		pages.loginpage.loginToApplication(loginDetails.get("L2"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleL2Head"));
		
		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click Pending for approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click on Claim Check Box */
		pages.pendingActionsPage.clickClaimNumber(claimID);
		
		/* Click on Approve Button */
		pages.pendingActionsPage.clickApproveButtonunderClaimpopup();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate Status of Claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_geo_hr"));
		
		/* Logout from Application */
		pages.homePage.logout();
		
		
		/****** GEO HR LOGIN ******/
		
		/*Login to Application as GEO HR */
		pages.loginpage.loginToApplication(loginDetails.get("GeoHR"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
		
		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click Pending for approval tab */
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click on Approval Button */
		pages.pendingActionsPage.referApproveClaim(claimID);
			
		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate Status of Claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_es_level1"));
		
		/*Logout from application*/
		pages.homePage.logout();
		
		
		/****** ES1 LOGIN ******/
		
		/*Login to Application as ES1 */
		pages.loginpage.loginToApplication(loginDetails.get("ES1"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1OnSite"));
		
		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));
	
		/*Click Pending for approval tab*/
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click on Claim Check Box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);
		
		/* Click on Approve Button */
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate Status of Claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_es_level2"));
		
		/*Logout from application*/
		pages.homePage.logout();	
		
		
		/****** ES2 LOGIN ******/
		
		/*Login to Application as ES2 */
		pages.loginpage.loginToApplication(loginDetails.get("ES2"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2OnSite"));
		
		/* Validate Home page is displayed */
		pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),	prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*Click Pending for approval tab*/
		pages.pendingActionsPage.clkPendingforApprovalTab();
		
		/* Validate Pending Action Page is displayed */
		pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"),
				prop_app_constants.getProperty("expectedPendingActionsPageUrl"),prop_app_constants.getProperty("expectedPendingActionsPageTitle"));
		
		/* Click on Claim Check Box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);
		
		/* Click on Approve Button */
		pages.pendingActionsPage.clickOnApproveButton();
		
		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();
		
		/* Click on Yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/* Click Previous Records in Menu drop down */
		pages.homePage.goToPreviousRecords();
		
		/* Validate Previous Records Page displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate Status of Claim */
		pages.previousRecordsPage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		
		/*Logout from application*/
		pages.homePage.logout();	
		
		
		/****** Employee LOGIN ******/
		
		/*Login to Application as employee */
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));
		
		/* Click on Close button in HCL Partner manual pop up*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));
		
		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));

	}

}
