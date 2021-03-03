package com.hcl.gim.testscripts.retention_bonus;


import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM__0010
 *Claim Type: RetentionBonus
 *TestScript Name: TYSS_GIM_RB_0010
 *Description: verify the status of GEO HR after getting approved by all approvers
 *Author: Abhilash B
 */
public class TYSS_GIM_RB_0010 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from RetentionBonus where SlNo ='10'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:verify the status of Retention bonus claim after getting approved by all approvers(Excel upload)")
	public synchronized void TC_TYSS_GIM_RB_0010(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String bonusperiod,String remarks) throws InterruptedException  

	 {

		 Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_retention_bonus_login"));
		 String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		 String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		 String[] es2LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES2"));
		 String[] data= { employeeCode, countryCode, companyCode, amount,fromDate,toDate,bonusperiod,remarks};
         String [] format= {"number"	,"number","number","number","date","date","string","string"};
         Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);
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

		/* Click on Excel Upload Tab */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));
		
		/* Download the Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"));
		
		/*validates the download*/
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
		
		/* Click on Upload template and upload template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat,downloadedDocumentName);

		/* Click on Upload file */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkExcelUploadButton();
		
		/* Click on yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* Click on Menu Bar and Click on Initiate Claims */
		pages.homePage.goToInitiateClaims();
		
		/*validates list of claims*/
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Get the claim id */
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

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
		/* Login as ES1 */
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
		
		
		/* Click on Approve icon under action */
		pages.pendingActionsPage.referApproveClaim(claimId);
		
		/* Set remarks for approval action */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Check on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Check on yes Button */
		pages.pendingActionsPage.clickOnYesButton();
				
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);
		
		/* Log out As ES1 */
		pages.homePage.logout();
		
		
		/****** ES2 Login *******/

		/* Login as ES2 */
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
		

		/* Click on Approve icon under action */
		pages.pendingActionsPage.referApproveClaim(claimId);
		
		/* Set remarks for approval action */
		pages.pendingActionsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));

		/* Check on Submit Button */
		pages.pendingActionsPage.clickOnSubmitButton();

		/* Check on yes Button */
		pages.pendingActionsPage.clickOnYesButton();
		
		/*
		 * 'Claim Requests have been successfully approved' Message should be Displayed
		 * and the Claim should be removed from the Pending Actions Page and Moved to
		 * the Next Level Approval.
		 */
		pages.pendingActionsPage.validateClaimID(claimId);
		
		/* Log out As ES2 */
		pages.homePage.logout();
		
		
		  /****** Geo HR Login *******/
		/* Login with GEO HR */
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
		

		/* Verify the Status of the Claim. */
		pages.previousRecordsPage.validateStatusApproveTxt(claimId,  prop_app_constants.getProperty("expectedStatus_pushed_to_irem"));
		
		
	}
}
