package com.hcl.gim.testscripts.exgratia_others;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId: TYSS_GIM_0003 
 * Claim Type: Exgratia Others 
 * TestScript Name: TYSS_GIM_EXO_0003 
 * Description: Verify user is able to cancel the Exgratia
 *              other claim for which data has been uploaded by GEO HR.
 * 
 * Author: Aatish Slathia
 */

public class TYSS_GIM_EXO_0003 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaOthers where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description : Verify user is able to cancel claim for which data has been uploaded by GEO HR.")
	public synchronized void TC_TYSS_GIM_EXO_0003(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String rate,String noofInstalments,String remarks) throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_exgratia_others_login"));
		
		/**** Get login details for role Geo HR *******/
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/************ GEO HR Login ******************/
		
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Validate Initiate Claim Page & Select Claim type-Exgratia - others
		 * allowance
		 */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Download the Template */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,Rate,NoofInstalments,Remarks from ExgratiaOthers WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";
		
		/*
		 * Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		 * your content here'
		 ****/
		pages.initiateClaimPage.clkPasteYourContentHereText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_exgratia_others_sheetname"), strQuery,
				downloadedDocumentName);

		/* Upload png file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));

		/* click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* Click on Menu Bar and Click on Initiate Claims */
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));

		/* Fetching the claim id & store it */
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Validate list of Claims for Approval in Initiate Claims Page */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on initiated claim check box */
		pages.initiateClaimsPage.clkchkClaim(claimId);

		/* click Cancel Button */
		pages.initiateClaimsPage.clkCancel();

		/* Enter remarks */
		pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click on Submit Button */
		pages.initiateClaimsPage.clkCancelSubmit();
	}
}