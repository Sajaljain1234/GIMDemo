package com.hcl.gim.testscripts.exgratia_language_allowance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId:TYSS_GIM_0008
 *Claim Type:Exgatia - Language allowance
 *TestScript Name: TYSS_GIM_ELA_0008
 *Description:Verify that user is able to clear the Data for
 *            Exgratia-Language allowance claim after valid data 
 *            and .docx file is uploaded
 *            
 *Author : Harsha K
 * 
 * */
public class TYSS_GIM_ELA_0008 extends BaseTest {

		@Test(description = "Description: Verify that user is able to clear the Data for Exgratia-Language allowance claim after valid data and .docx file is uploaded")
		public synchronized void TC_TYSS_GIM_ELA_0008()
				throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_la_login"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		String[] empCodes = prop_app_constants.getProperty("ela_empcode").split(",") ;

		/* Get login details for role Geo HR */
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		/****** Login as GEO HR to download the form template *****/

		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Click on Hcl Banner Popup Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Geo HR Initiate of Behalf of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Validate Initiate Claim Page & Select Claim type-Exgratia - Language
		 * allowance
		 */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_exgratia_la_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Download Form Template & validate the document */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_exgratia_la_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/****
		 * Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		 * your content here'
		 ***/
		pages.initiateClaimPage.clkPasteYourContentHereMultipleText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_exgratia_la_sheetname"), empCodes, downloadedDocumentName);

		/* Upload your .docx file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/* Click on Clear Button. */
		pages.initiateClaimPage.clkClearButton();
	}
}
