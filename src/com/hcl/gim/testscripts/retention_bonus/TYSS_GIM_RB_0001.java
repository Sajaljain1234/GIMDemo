package com.hcl.gim.testscripts.retention_bonus;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/**
 *TestCaseId: TYSS_GIM__0001
 *Claim Type: RetentionBonus
 *TestScript Name: TYSS_GIM_RB_0001
 *Description: Verify the user is able to download 'RetentionBonus' allowance form template
 *Author: Abhilash B
 */
public class TYSS_GIM_RB_0001 extends BaseTest {
	@Test(description="Description:Verify the user is able to download 'RetentionBonus' copyPaste form template")
	public void TC_TYSS_GIM_RB_0001()
	{
		   
			Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_retention_bonus_login"));			
            InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
			String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
       
			/****** Geo HR Login *******/
			/* Login as GEO HR */
			pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

			/* Close the HCL Banner Pop Up */
			pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
			
			/* Click on Initiate on behalf of the employee */
			pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
						
			/* Select the Retention Bonus claim type */
			pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

			/* Download the Template */
			String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"));	
			
			/*validates the download*/
			WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
	}
}