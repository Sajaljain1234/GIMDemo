package com.hcl.gim.testscripts.clientbonus_reward_incentive;



import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/***
 * TestCaseID:TYSS_GIM_0006
 * Claim Type:Client Bonus/Reward/Incentive
 * Test Script Name:TYSS_GIM_CBRI_0006
 * @author shreya.u
 * Description: Verify that user is able to initiate Client Bonus/Reward/Incentive by writing
 *             the data in copy paste field.
 * 
 */
public class TYSS_GIM_CBRI_0006 extends BaseTest {
	
	
	@Test(description="Description:Verify that user is able to initiate Client Bonus/Reward/Incentive by writing the data in copy paste field.")
	public synchronized void TC_TYSS_GIM_CBRI_0006() {
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
		
		/*Enter the data in the text field saying 'Paste your content here'*/
		pages.initiateClaimPage.setPasteYourContentHereText(prop_app_constants.getProperty("data"));
		
		/* Upload your .msg file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_msg")));

		/* Click on UPLOAD Button. */
		pages.initiateClaimPage.clkUploadButton();
		

		/*please paste records pop up is displayed*/
		pages.initiateClaimPage.validatePleasePasteRecordsPopup();
		
	}

}
