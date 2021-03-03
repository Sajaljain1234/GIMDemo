package com.hcl.gim.testscripts.rsu;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/***
 * TestCaseID:TYSS_GIM_0001 Claim Type:Client RSU Payment(Restricted Stock
 * Units) Test Script Name:TYSS_GIM_RSU_0001
 * 
 * @author Vivek dogra Description: Verify that the user is able to download RSU
 *         Payment(Restricted Stock Units) form Template
 * 
 */
public class TYSS_GIM_RSU_0001 extends BaseTest {
	
	@Test(description="Description: Verify that the user is able to download RSU Payment(Restricted Stock Units) form Template")
	public synchronized void TC_TYSS_GIM_RSU_0001() throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_login"));

		String[] lobhrLoginDetails = loginDetails.get("LOB HR");

		/* Login as GEO HR */
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));
		
		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page is displayed and Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));
	
		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_login"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

	}
}
