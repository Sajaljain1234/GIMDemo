package com.hcl.gim.testscripts.patent_award;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;

/*
 * TestCaseId: TYSS_GIM_0014
 * Claim Type: Patent Award Allowance
 * TestScript Name: TYSS_GIM_PA_0014
 * Description: Verify that user is able to go back to the select claim page after selecting the 'Patent Award'
 * allowance claim
 * 
 * Author: Sajal jain
 */
public class TYSS_GIM_PA_0014 extends BaseTest {
	@Test(description = "Description: Verify that user is able to go back to the select claim page after selecting the 'Patent Award' allowance claim in excel upload option")
	public void TC_TYSS_GIM_PA_0014() {

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_patent_award_login"));
		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		/****** LOB HR LOGIN ******/

		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Validate Home page and Click on Initiate on Behalf of Employee Tab */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page is displayed and Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_patent_award_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));

		/* Click on excel button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Click on Back Button */
		pages.initiateClaimPage.clkExcelBackButton(prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));

	}
}
