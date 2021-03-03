package com.hcl.gim.testscripts.ikhaya;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
/**
 *TestCaseId: TYSS_GIM_003
 *Claim Type: Ikahya safety bonus
 *TestScript Name: TYSS_GIM_ISB_003
 *Description: Verify that User is able to initiate the 'Ikahya safety bonus(client-seriti/elango) allowance' Claim by writing the Data in the Copy & Paste field. 
 *            
 *Author: Manish Kumar C D
 */

public class TYSS_GIM_ISB_003 extends BaseTest{
	@Test(description = "Description:Verify that User is able to initiate the 'Ikahya safety bonus(client-seriti/elango) allowance' Claim by writing the Data in the Copy & Paste field.")
	public synchronized void TC_TYSS_GIM_ISB_003() {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_login"));

		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/************ GEO HR Login ******************/
		/* Login as GEO HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee*/
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*Select Ikahya Safety Bonus */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

		/*Type data into Paste Your Content Here Text*/
		pages.initiateClaimPage.setPasteYourContentHereText(prop_app_constants.getProperty("data"));

		/*Upload or Drag and Drop Your Files Here Link*/
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* click on Upload Button*/
		pages.initiateClaimPage.clkUploadButton();

		/*validates Please Paste Records Popup is displayed or not*/
		pages.initiateClaimPage.validatePleasePasteRecordsPopup();
	}
}