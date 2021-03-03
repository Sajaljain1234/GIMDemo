package com.hcl.gim.testscripts.hcl_jewels;
import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/*
 * TestCaseId:TYSS_GIM_0006 
 * Claim Type:HCL Jewels TestSCript
 * Name:TYSS_GIM_HJ_0006 
 * Description:Verify that USer is able to initiate the 'HCL Jewels' allowance claim by writing the Data in the Copy & Paste field.
 * 
 * @author Suganthini
 *
 */
public class TYSS_GIM_HJ_0006 extends BaseTest {

	@Test(description = "Description: Verify that user is able to initiate the 'HCL Jewles' allowance claim using copy & paste option")
	public synchronized void TC_TYSS_GIM_HJ_0006() {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_hcl_jewels_login"));
		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/******* LOB HR Login*********/
		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_hcl_jewels_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Download form template link */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_hcl_jewels_dropdown"));

		/* Validate Download template */
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Enter the data in the text field */
		pages.initiateClaimPage.setPasteYourContentHereText(prop_app_constants.getProperty("data"));

		/* Upload the file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_pdf")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* please paste record validation */
		pages.initiateClaimPage.validatePleasePasteRecordsPopup();
	}
}
