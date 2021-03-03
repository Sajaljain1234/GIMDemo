package com.hcl.gim.testscripts.foundation_bonus;
import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/*
 * TestCaseId:TYSS_GIM_0003 
 * Claim Type:Foundation Bonus Geo allowance TestSCript
 * Name:TYSS_GIM_FB_0003 
 * Description:Verify that User is able to initiate the 'Foundation Bonus Geo allowance' Claim by writing the Data in the Copy & Paste field.
 * 
 * @author Suganthini
 *
 */
public class TYSS_GIM_FB_0003 extends BaseTest {
	@Test(description = "Description: Verify that user is able to initiate the 'Foundation Bonus' allowance claim By writing the data in copy paste field")
	public synchronized void TC_TYSS_GIM_FB_0003() {
		
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_login"));
		String[] geohrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/******* GEO HR *********/

		/* Enter user name and Password and login as GEO HR */
		pages.loginpage.loginToApplication(geohrLoginDetails[0], geohrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Download form template link */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_foundation_bonus_geo_dropdown"));

		/* Validate Download template */
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Enter the data in the text field */
		pages.initiateClaimPage.setPasteYourContentHereText(prop_app_constants.getProperty("data"));

		/* Upload the file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* please paste record validation */
		pages.initiateClaimPage.validatePleasePasteRecordsPopup();
	}
}
