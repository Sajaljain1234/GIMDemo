package com.hcl.gim.testscripts.rsu;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
/***
 * TestCaseID:TYSS_GIM_0006
 * Claim Type:Client RSU Payment(Restricted Stock Units)
 * Test Script Name:TYSS_GIM_RSU_0006
 * @author Vivek Dogra
 * Description: Verify that user is able to initiate RSU Payment(Restricted Stock Units) by writing
 *             the data in copy paste field.
 * 
 */
public class TYSS_GIM_RSU_0006 extends BaseTest {
	@Test(description="Description: Verify that user is able to initiate RSU Payment(Restricted Stock Units) by writing the data in copy paste field")
	public void TC_TYSS_GIM_RSU_0006() {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		

		/****** LOB HR LOGIN ******/

		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Validate Home page and Click on Initiate on Behalf of Employee Tab */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page is displayed and Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Download form template link and validate file download */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_login"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Enter the data in the Place saying 'Paste your content here' */
		pages.initiateClaimPage.setPasteYourContentHereText(prop_app_constants.getProperty("data"));

		/* Upload the JPG file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/* Click on Upload button */
		pages.initiateClaimPage.clkUploadButton();

		/* Please Paste Records pop up is displayed */
		pages.initiateClaimPage.validatePleasePasteRecordsPopup();
	}
}