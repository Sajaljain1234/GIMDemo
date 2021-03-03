package com.hcl.gim.testscripts.retention_bonus;


import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/**
 *TestCaseId: TYSS_GIM__0002
 *Claim Type: RetentionBonus
 *TestScript Name: TYSS_GIM_RB_0002
 *Description: Verify the status of GEO HR for 'RetentionBonus' allowance claim after getting approved by all the approvers
 *            
 *Author: Abhilash B
 */

public class TYSS_GIM_RB_0002 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from RetentionBonus where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:Verify the status of the Retention Bonus claim after getting approved by all the approvers(copypaste)")
	public void TC_TYSS_GIM_RB_0002(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String bonusperiod,String remarks) throws InterruptedException  

	{	   
	        
		    Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_retention_bonus_login"));
		    String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		    String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		    String[] es2LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES2"));   
			InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

			/****** Geo HR Login *******/
			/* Login as GEO HR */
			pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

			/* Close the HCL Banner Pop Up */
			pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
			
			/* Click on Initiate on behalf of the employee */
			pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
						
			/* Select the Retention Bonus claim type */
			pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

			/* Download the Template */
			String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"));	
			
			/*validates the download*/
			WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
			
			String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,BonusPeriod,Remarks from RetentionBonus WHERE EmployeeCode='"
					+ employeeCode + "' and SlNo ='" + slNo + "'";
			

			/* Copy Paste the content from excel sheet */
			pages.initiateClaimPage.clkPasteYourContentHereText(GIMTESTDATAOTHERFLOWEXCELPATH, prop_app_constants.getProperty("claimtype_retention_bonus_sheetname"), strQuery,downloadedDocumentName);

			/* Upload your  file */
			pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

			/* Click on UPLOAD Button. */
			pages.initiateClaimPage.clkUploadButton();

			/* Click on 'YES'. */
			pages.initiateClaimPage.clkYesButton();			

			/* Click on Menu Bar and Click on Initiate Claims */
			pages.homePage.goToInitiateClaims();
			
			/*validates list of claims*/
			pages.initiateClaimsPage.validateInitiateClaimsPageData();
			
			/* Get the claim id */
			String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
			
			/*Click on initiated claim check box*/
			pages.initiateClaimsPage.clkchkClaim(claimId);
			
			/* Click on Submit button Under the Actions Tab for the Uploaded Claim. */
			pages.initiateClaimsPage.clkApproveClaim(claimId);

			/* Enter Remarks */
			pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));
			
			/* Click on SUBMIT button. */
			pages.initiateClaimsPage.clkApproveSubmit();
			
			/* Click on 'YES' Button. */
			pages.initiateClaimsPage.clkApproveYes(claimId);
			
			/* Log out As GEO HR */
			pages.homePage.logout();
			
			
			/****** ES1 Login *******/

			/* Enter User ID and Password of ES1 OnSite and Click on Log In. */
			pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);

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
			
			/* Click on Submit in Actions */
			pages.pendingActionsPage.referApproveClaim(claimId);
			
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
			
			/* Log out */
			pages.homePage.logout();
			
			
			/****** ES2 Login *******/

			/* Enter User ID and Password of ES1 OnSite and Click on Log In. */
			pages.loginpage.loginToApplication(es2LoginDetails[0], es2LoginDetails[1]);

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
			
			/* Click on Submit in Actions */
			pages.pendingActionsPage.referApproveClaim(claimId);
			
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
		
			/* Log out */
			pages.homePage.logout();
			
			
			/*** GeoHR Login ***/
			/* Login with Geo HR */
			pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);
			
			/* Close the HCL Banner Pop Up */
			pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
			
			/* Click on Initiate on behalf of the employee */
			pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
			
			/*validate initiate claim page*/
			pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));
			
			/* Click on Menu and Previous records */
			pages.homePage.goToPreviousRecords();
			
			/* Validate previous records Page displayed */
			pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
			
			/*Verify the Status of the Claim.*/
			pages.previousRecordsPage.validateStatusApproveTxt(claimId, prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		
			
	}
}