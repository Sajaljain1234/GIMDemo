package com.hcl.gim.testscripts.exgratia_others;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/*
 * TestCaseId: TYSS_GIM_0007 
 * Claim Type: Exgratia Others 
 * TestScript Name:TYSS_GIM_EXO_0007 
 * Description: Verify user is able to initiate the Exgratia
 *              -other claim by writing the data in copy and paste field.
 * 
 * Author: Aatish Slathia
 *
 **/

public class TYSS_GIM_EXO_0007 extends BaseTest {

	@Test(description = "Description: Verify user is able to initiate the claim by writing the data in copy and paste field.")
	public synchronized void TC_TYSS_GIM_EXO_0007()
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_exgratia_others_login"));
		
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		/****** Login as GEO HR to initiate claim by copy & paste data *****/

		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Click on Hcl Banner Popup Close Button */
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

		/*
		 * Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		 * your content here
		 */
		pages.initiateClaimPage.setPasteYourContentHereText(prop_app_constants.getProperty("data"));

		/* Upload docx file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/* click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* Validating Please Paste Records Pop-up is displayed or not */
		pages.initiateClaimPage.validatePleasePasteRecordsPopup();
	}
}