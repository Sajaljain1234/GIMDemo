package com.hcl.gim.testscripts.ikhaya;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
/**
 *TestCaseId: TYSS_GIM_010
 *Claim Type: Ikahya safety bonus
 *TestScript Name: TYSS_GIM_ISB_010
 *Description: Verify that User is able to Download Ikahya safety bonus(client-seriti/elango) allowance form template.
 *            
 *Author: Manish Kumar C D
 */

public class TYSS_GIM_ISB_010 extends BaseTest{
	@Test(description = "Description: Verify that User is able to Download Ikahya safety bonus(client-seriti/elango) allowance form template.")
	public synchronized void TC_TYSS_GIM_ISB_010() {

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

		/*click on Claimtype Radio Button*/
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/*Download Form Template*/
		String validDownloadDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_dropdown"));
		WebActionUtil.validateDownloadedDocument(validDownloadDocumentName);
}
}
