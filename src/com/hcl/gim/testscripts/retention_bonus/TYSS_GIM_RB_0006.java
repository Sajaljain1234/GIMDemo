package com.hcl.gim.testscripts.retention_bonus;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/**
 *TestCaseId: TYSS_GIM__0006
 *Claim Type: RetentionBonus
 *TestScript Name: TYSS_GIM_RB_0006
 *Description: verify the user is able to cancel the retension bonus claim for which data is
 * uploaded by Geo HR 
 *Author: Abhilash B
 */

public class TYSS_GIM_RB_0006 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from RetentionBonus where SlNo ='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:verify the user is able to cancel the retension bonus claim for which data is uploaded(copypaste)")
	public void TC_TYSS_GIM_RB_0006(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String bonusperiod,String remarks) throws InterruptedException  

	{

		    Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_retention_bonus_login"));
	        String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));		
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
			pages.initiateClaimPage.clkPasteYourContentHereText(GIMTESTDATAOTHERFLOWEXCELPATH, prop_app_constants.getProperty("claimtype_retention_bonus_sheetname"),strQuery ,downloadedDocumentName);

			/* Upload your  file */
			pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_msg")));

			/* Click on UPLOAD Button. */
			pages.initiateClaimPage.clkUploadButton();

			/* Click on 'YES'. */
			pages.initiateClaimPage.clkYesButton();
			
			/* Click on Menu Bar and Click on Initiate Claims */
			pages.homePage.goToInitiateClaims();
			
			/*validates list of claims*/
			pages.initiateClaimsPage.validateInitiateClaimsPageData();
			
			/* Get the claim id */
			String claimid = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
			
			/* click on checkbox for cancel claim id */
			pages.initiateClaimsPage.clkchkClaim(claimid);
			
			/* Click on Cancel button */
			pages.initiateClaimsPage.clkCancel();
			
			/* Enter Remark and click on submit button */
			pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));
			
			/* Click cancel submit */
			pages.initiateClaimsPage.clkCancelSubmit();
				
		
	}
}