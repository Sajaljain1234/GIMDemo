package com.hcl.gim.testscripts.child_education;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFile;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId: TYSS_GIM_CEA_0006
 * Claim Type: Child Education Allowance
 * TestScript Name: TYSS_GIM_CEA_0006
 * Description: Verify that On Site Employee Can Re-initiate the Child Education Claim after referred back by all the
 * approvers for the First Time for Tuition Fees
 * 
 * Author: Sajal jain
 */
public class TYSS_GIM_CEA_0006 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ChildEducationAllowance where SlNo ='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that On Site Employee Can Re-initiate the Child Education Claim after referred back by all the approvers for the First Time for Tuition Fee")
	public synchronized void TC_TYSS_GIM_CEA_0006(String slNo, String empCode, String childName, String academicYear,
			String fromDate, String toDate, String grade, String amount, String schoolName, String remarks,
			String fileFormat) {

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		Map<String, String> loginDetails = WebActionUtil.getLoginDetailsForCEA(empCode);

		/****** Employee LOGIN ******/

		/* Login to Application as employee */
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

		/* Retrieve the claimID */
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

		/* CLick on Refer back button */
		pages.pendingActionsPage.referBackClaim(claimID);

		/* Set Remark */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));

		/* Click submit Button */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();

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

		/* Click on Edit Button */
		pages.homePage.clickEditClaim(claimID);

		/* Update the claim */
		pages.childEducationAllowancePage.updateClaimRequest(prop_app_constants.getProperty("newAmount"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_rm"));

		/* Logout from application */
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

		/* Navigate to menu and click on Previous records link */
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

		/* CLick on Refer back button */
		pages.pendingActionsPage.referBackClaim(claimID);

		/* Set Remark */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));

		/* Click submit Button */
		pages.pendingActionsPage.clickOnSubmitReferBackButton();

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

		/* Click on Edit Button */
		pages.homePage.clickEditClaim(claimID);

		/* Update the claim */
		pages.childEducationAllowancePage.updateClaimRequest(prop_app_constants.getProperty("newAmount"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_rm"));

		/* Logout from application */
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

		/* Click on claim ID of initiated claim */
		pages.pendingActionsPage.clickClaimNumber(claimID);

		/* Enter remark in claim id pop up */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click refer back button in claim id pop up and validate success message */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusRejectTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_rejected_l4_head"));

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

		/* Click on Edit Button */
		pages.homePage.clickEditClaim(claimID);

		/* Update the claim */
		pages.childEducationAllowancePage.updateClaimRequest(prop_app_constants.getProperty("newAmount"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_rm"));

		/* Logout from application */
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

		/* Click on claim ID of initiated claim */
		pages.pendingActionsPage.clickClaimNumber(claimID);

		/* Enter remark in claim id pop up */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click refer back button in claim id pop up and validate success message */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusRejectTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_rejected_geo_hr"));

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

		/* Click on Edit Button */
		pages.homePage.clickEditClaim(claimID);

		/* Update the claim */
		pages.childEducationAllowancePage.updateClaimRequest(prop_app_constants.getProperty("newAmount"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_rm"));

		/* Logout from application */
		pages.homePage.logout();

		/****** RM LOGIN ******/

		/* Login as RM */
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

		/* ClicK on Approve Tool tip */
		pages.pendingActionsPage.referApproveClaim(claimID);

		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* click on Yes Button */
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

		/* Click on Logout */
		pages.homePage.logout();

		/****** L4 LOGIN ******/

		/* Login as L4 head */
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

		/* ClicK on Approve Tool tip */
		pages.pendingActionsPage.referApproveClaim(claimID);

		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* click on Yes Button */
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

		/* Click on Logout */
		pages.homePage.logout();

		/****** GEO HR LOGIN ******/

		/* Login as GEO HR */
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

		/* Click on Logout */
		pages.homePage.logout();

		/****** ES1 LOGIN ******/

		/* Login as ES1 */
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

		/* Click on claim ID of initiated claim */
		pages.pendingActionsPage.clickClaimNumber(claimID);

		/* Enter remark in claim id pop up */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click refer back button in claim id pop up and validate success message */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();

		/* Navigate to menu and click on Home Link */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusRejectTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_rejected_es_level1"));

		/* Click on Logout */
		pages.homePage.logout();

		/****** Employee LOGIN ******/

		/* Login to Application as Employee */
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on Edit Button */
		pages.homePage.clickEditClaim(claimID);

		/* Update the claim */
		pages.childEducationAllowancePage.updateClaimRequest(prop_app_constants.getProperty("newAmount"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_rm"));

		/* Logout from application */
		pages.homePage.logout();

		/****** RM LOGIN ******/

		/* Login as RM */
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

		/* ClicK on Approve Tool tip */
		pages.pendingActionsPage.referApproveClaim(claimID);

		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* click on Yes Button */
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

		/* Click on Logout */
		pages.homePage.logout();

		/****** L4 LOGIN ******/

		/* Login as L4 head */
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

		/* ClicK on Approve Tool tip */
		pages.pendingActionsPage.referApproveClaim(claimID);

		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* click on Yes Button */
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

		/* Click on Logout */
		pages.homePage.logout();

		/****** GEO HR LOGIN ******/

		/* Login as GEO HR */
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

		/* click on approval check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);

		/* click on Approve Button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* click on Yes Button */
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

		/* Click on Logout */
		pages.homePage.logout();

		/****** ES1 LOGIN ******/

		/* Login as ES1 */
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

		/* click on claim ID of initiated claim */
		pages.pendingActionsPage.clickClaimNumber(claimID);

		/* Click on Approve button in claim id pop up */
		pages.pendingActionsPage.clickApproveButtonunderClaimpopup();

		/* Click on Yes button */
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

		/* Click on Logout */
		pages.homePage.logout();

		/****** ES2 LOGIN ******/

		/* Login as ES2 */
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

		/* Click on claim ID of initiated claim */
		pages.pendingActionsPage.clickClaimNumber(claimID);

		/* Enter remark in claim id pop up */
		pages.pendingActionsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click refer back button in claim id pop up and validate success message */
		pages.pendingActionsPage.clickReferBackButtonunderClaimpopup();

		/* Navigate to menu and click on Previous records */
		pages.homePage.goToPreviousRecords();

		/* Validate Previous Records Page is displayed */
		pages.previousRecordsPage.validatePreviousRecordsPage(
				prop_app_constants.getProperty("expectedPreviousRecordsPageText"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),
				prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));

		/* Validate status of claim */
		pages.previousRecordsPage.validateStatusRejectTxt(claimID,
				prop_app_constants.getProperty("expectedStatus_rejected_es_level2"));

		/* Click on Logout */
		pages.homePage.logout();

		/****** Employee LOGIN ******/

		/* Login to Application as Employee */
		pages.loginpage.loginToApplication(loginDetails.get("EmpCode"), loginDetails.get("Password"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleEmployee"));

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Click on Edit Button */
		pages.homePage.clickEditClaim(claimID);

		/* Update the claim */
		pages.childEducationAllowancePage.updateClaimRequest(prop_app_constants.getProperty("newAmount"));

		/* Click on Close button in HCL Partner manual pop up */
		pages.homePage.closeHomeHCLBannerPopUp();

		/* Validate My Claims Page is displayed */
		pages.homePage.validateMyClaimsPage(prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate status of claim */
		pages.homePage.validateStatusApproveTxt(claimID, prop_app_constants.getProperty("expectedStatus_pending_rm"));

		/* Logout from application */
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

		/* Click on approval check box */
		pages.pendingActionsPage.clkPaticularCheckbox(claimID);

		/* click on Approve Button */
		pages.pendingActionsPage.clickOnApproveButton();

		/* Click on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* click on Yes Button */
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

		/* click on claim ID of initiated claim */
		pages.pendingActionsPage.clickClaimNumber(claimID);

		/* Click on Approve button in claim id pop up */
		pages.pendingActionsPage.clickApproveButtonunderClaimpopup();

		/* Click on Yes button */
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

		/* click on claim ID of initiated claim */
		pages.pendingActionsPage.clickClaimNumber(claimID);

		/* Click on Approve button in claim id pop up */
		pages.pendingActionsPage.clickApproveButtonunderClaimpopup();

		/* Click on Yes button */
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