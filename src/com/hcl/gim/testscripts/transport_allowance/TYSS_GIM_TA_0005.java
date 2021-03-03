package com.hcl.gim.testscripts.transport_allowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/*TestCaseId : TYSS_GIM_0005
 *Claim Type : Transport Allowance
 *TestScript Name : TYSS_GIM_TA_0005
 *Description : Verify that User is able to clear the Data for Transport Allowance claim after valid Data and .pdf extension is uploaded
 *Author : Vikas
 * 
 * */
public class TYSS_GIM_TA_0005 extends BaseTest {
	
	@Test(description = "Description: Verify that user is able to clear the data after valid data and .pdf extension is uploaded")
	public synchronized void TC_TYSS_GIM_TA_0005() {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_transport_allowance_login"));
		String[] geoHRLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Enter GEO HR user name and Password */
		pages.loginpage.loginToApplication(geoHRLoginCredentials[0], geoHRLoginCredentials[1]);

		/* Click on HCL Banner Popup */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
		
		/* Click on Initiate Behalf Of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
		
		/* Select Claim Type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_transport_allowance_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));
		
		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_transport_allowance_dropdown"));;
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Paste Your Content Here */
		String[] empCodes = prop_app_constants.getProperty("ta_empcode").split(",");
		pages.initiateClaimPage.clkPasteYourContentHereMultipleText(prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"), 
				prop_app_constants.getProperty("claimtype_transport_allowance_sheetname"),empCodes, downloadedDocumentName);
		
		/* Click on Upload Or Drag And Drop Files */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_pdf")));
		
		/* Click on Clear Button */
		pages.initiateClaimPage.clkClearButton();
	}

}
