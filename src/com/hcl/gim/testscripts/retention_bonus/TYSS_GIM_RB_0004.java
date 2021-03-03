package com.hcl.gim.testscripts.retention_bonus;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
/*
 *TestCaseId: TYSS_GIM__0004
 *Claim Type: RetentionBonus
 *TestScript Name: TYSS_GIM_RB_0004
 *Description: verify that user is able to go back to the select claim page after
 * selecting Retention Bonus claim
 *            
 *Author: Abhilash B
 */

public class TYSS_GIM_RB_0004 extends BaseTest {
	@Test(description="Description:verify that user is able to go back to the select claim page after selecting Retention Bonus claim")
	public void TC_TYSS_GIM_RB_0004()
	{

		    Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_retention_bonus_login"));
	        String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));	
            InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
            
            /****** Geo HR Login *******/
			/* Login as GEO HR */
			pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

			/* Close the HCL Banner Pop Up */
			pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
			
			/* Click on Initiate on behalf of the employee */
			pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
						
			/* Select the Retention Bonus claim type */
			pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

			/*click on Back Button*/
			pages.initiateClaimPage.clkBackButton(prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));
			
			
		
	}
}