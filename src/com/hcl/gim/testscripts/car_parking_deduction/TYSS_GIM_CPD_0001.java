package com.hcl.gim.testscripts.car_parking_deduction;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/**
 * TestCaseId:TYSS_GIM_0001 Claim Type:Car parking Deduction TestSCript
 * Name:TYSS_GIM_CPD_0001 Description:Verify that User is able to Download Car
 * parking Deduction allowance form template.
 * 
 * @author Vivek Dogra
 *
 */
public class TYSS_GIM_CPD_0001 extends BaseTest {
	
	@Test(description = "Description: Verify if user is able to download Car parking the template")
	public synchronized void TC_TYSS_GIM_CPD_0001() {

		Map<String, String[]> logindata = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_car_parking_deduction_login"));

		String[] getGeoHRLoginCredentials = logindata.get(prop_app_constants.getProperty("roleGEOHR"));
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/************ GEO HR Login ******************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(getGeoHRLoginCredentials[0], getGeoHRLoginCredentials[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page is displayed and Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_car_parking_deduction_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_car_parking_deduction_sheetname"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
	}
}
