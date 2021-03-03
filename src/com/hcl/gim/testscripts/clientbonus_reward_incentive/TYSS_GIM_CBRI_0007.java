package com.hcl.gim.testscripts.clientbonus_reward_incentive;



import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
/***
 * TestCaseID:TYSS_GIM_0007
 * Claim Type:Client Bonus/Reward/Incentive
 * Test Script Name:TYSS_GIM_CBRI_0007
 * @author shreya.u
 * Description: Verify that the user is able to go back to the select claim page
 *              after selecting the Client Bonus/Reward/Incentive
 * 
 */

public class TYSS_GIM_CBRI_0007 extends BaseTest{
	

	@Test(description="Description:Verify that the user is able to go back to the select claim page after selecting the Client Bonus/Reward/Incentive")
	public synchronized void TC_TYSS_GIM_CBRI_0007() {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));
		
		
		 /* Login as LOB HR */
	    InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));
		
		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),  prop_app_constants.getProperty("expectedHomePageTitle"), prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select the Client Bonus Reward Incentive claim type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"),  prop_app_constants.getProperty("expectedClaimPageText") , prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				 prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleLOBHR"));
		
		/* Click on Back button */
		pages.initiateClaimPage.clkBackButton(prop_app_constants.getProperty("expectedClaimPageText"), prop_app_constants.getProperty("expectedInitiateClaimUrl"), prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));
		

}
}
