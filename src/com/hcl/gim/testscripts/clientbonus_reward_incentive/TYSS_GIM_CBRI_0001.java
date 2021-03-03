package com.hcl.gim.testscripts.clientbonus_reward_incentive;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/***
 * TestCaseID:TYSS_GIM_0001 Claim Type:Client Bonus/Reward/Incentive Test Script
 * Name:TYSS_GIM_CBRI_0001
 * 
 * @author shreya.u Description: Verify that the user is able to download Client
 *         Bonus/Reward/Incentive form Template
 * 
 */
public class TYSS_GIM_CBRI_0001 extends BaseTest {
	@Test(description="Description:Verify that user is able to download Template")
	public synchronized void TC_TYSS_GIM_CBRI_0001()
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));
		/* Login as LOB HR */
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select the Client Bonus Reward Incentive claim type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

	}
}
