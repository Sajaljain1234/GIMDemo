package com.hcl.gim.testscripts.transport_allowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/*TestCaseId : TYSS_GIM_0019
 *Claim Type : Transport Allowance
 *TestScript Name : TYSS_GIM_TA_0019
 *Description : Verify that User is able to go back to the select claim Page after Selecting the Transport Allowance claim 
 * *Author : Vikas
 * 
 * */
public class TYSS_GIM_TA_0019 extends BaseTest {

	@Test(description = "Description: Verify that user is able to go back to the select claim page after selecting the claim")
	public synchronized void TC_TYSS_GIM_TA_0019() {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_transport_allowance_login"));
		String[] geoHRLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Enter GEO HR user name and Password */
		pages.loginpage.loginToApplication(geoHRLoginCredentials[0], geoHRLoginCredentials[1]);

		/* Click on Close Button in HCL Banner Popup */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Validate Home page and Click on Initiate Behalf Of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*  Validate Initiate Claim page is displayed and Select Claim Type From Drop Down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_transport_allowance_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));
		
		/* Click on Through The Form Back Button */
		pages.throughTheFormPage.clkThroughFormBackButton();
		
		/* Validate Initiate Claim page */
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

	}
}
