package com.hcl.gim.testscripts.exgratia_others;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/*
 *TestCaseId: TYSS_GIM_0023
 *Claim Type: Exgratia Others
 *TestScript Name: TYSS_GIM_EXO_0023
 *Description: Verify the user is able to go back to the Select Claim page after 
 *             Selecting the Exgratia other claim.
 *            
 *Author: Aatish Slathia
 */

public class TYSS_GIM_EXO_0023 extends BaseTest{

	@Test(description = "Description: Verify the user is able to go back to the Select Claim page after Selecting the Claim")
	public synchronized void TC_TYSS_GIM_EXO_0023() throws InterruptedException {
		
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_others_login"));


		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Get login details for role Geo HR*/
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
			
		
		/************ GEO HR Login ******************/
		/*Login as Geo HR*/
		pages.loginpage.loginToApplication(geoHrLoginDetails[0],geoHrLoginDetails[1]);
		
		/*Click on Hcl Banner Popup Close Button*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
			
		/* Click on Initiate on behalf of the employee*/
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
		
		/* Select Exgratia others */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));
	
		/*click on Claimtype Radio Button*/
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));
		
		/*Click on Back Button*/
		pages.throughTheFormPage.clkThroughFormBackButton();
		
		/*Validate Initiate Claim Page*/
		pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));
	}
}