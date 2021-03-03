package com.hcl.gim.testscripts.clientbonus_reward_incentive;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/***
 * TestCaseID:TYSS_GIM_0008 Claim Type:Client Bonus/Reward/Incentive Test Script
 * Name:TYSS_GIM_CBRI_0008
 * 
 * @author shreya.u Description: Verify that the user is able to clear the data
 *         for claim after valid and .docx extension is uploaded.
 * 
 */
public class TYSS_GIM_CBRI_0008 extends BaseTest {
	@Test(description="Description:Verify that the user is able to clear the data for the claim")
	public synchronized void TC_TYSS_GIM_CBRI_0008() {
		
		String[] empCodes = prop_app_constants.getProperty("cbri_empcode").split(",");
		

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		/* Enter user name and Password and login as LOB HR */
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

		/* Click on Download form template link */

		String documentname = pages.initiateClaimPage.clkDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"));
		/*
		 * Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		 * your content here
		 */
		pages.initiateClaimPage.clkPasteYourContentHereMultipleText(GIMTESTDATAOTHERFLOWEXCELPATH,
				prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_sheetname"), empCodes,
				documentname);

		/* Upload your .docx file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/* Click on Clear button */
		pages.initiateClaimPage.clkClearButton();
	}

}
