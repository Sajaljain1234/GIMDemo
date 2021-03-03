package com.hcl.gim.testscripts.exgratia_others;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/*
 * TestCaseId: TYSS_GIM_0009 
 * Claim Type: Exgratia Others 
 * TestScript Name:TYSS_GIM_EXO_0009 
 * Description: Verify the user is able to clear the data of Exgratia others  
 *              after valid data and .jpg extension is uploaded.
 * 
 * Author: Aatish Slathia
 */

public class TYSS_GIM_EXO_0009 extends BaseTest {

	
	@Test(description = "Description: Verify the user is able to clear the data after valid data and .jpg extension is uploaded.")
	public synchronized void TC_TYSS_GIM_EXO_0009()
			 {
		
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_exgratia_others_login"));
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		String[] empCodes = prop_app_constants.getProperty("exo_empcode").split(",");
		
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/****** Login as GEO HR to initiate claim by copy & paste data *****/

		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Validate Initiate Claim Page & Select Claim type-Exgratia - Language
		 * allowance
		 */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Download form template link and validate file download */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
		

		/*
		 * Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		 * your content here
		 */
		pages.initiateClaimPage.clkPasteYourContentHereMultipleText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_exgratia_others_sheetname"), empCodes, downloadedDocumentName);
		
		
		/* Upload jpg file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

		/* click on Clear Button */
		pages.initiateClaimPage.clkClearButton();
	}
}
