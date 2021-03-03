package com.hcl.gim.testscripts.exgratia_language_allowance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId:TYSS_GIM_0017
 *Claim Type:Exgatia - Language allowance
 *TestScript Name: TYSS_GIM_ELA_0017
 *Description: Verify that the User is able to Cancel the 'GEO HR Exgratia-Language allowance' Claim 
 *             for which the Data has been uploaded by GEO HR.
 *Author : Harsha K
 * 
 * */
public class TYSS_GIM_ELA_0017 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaLanguageAllowance where SlNo ='17'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that the User is able to Cancel the 'GEO HR Exgratia-Language allowance' Claim for which the Data has been uploaded by GEO HR.")
	public synchronized void TC_TYSS_GIM_ELA_0017(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String rate, String noOfInstallments, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_la_login"));

		/* Get Login details for Geo HR */
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/*************** GeoHR Login to upload & cancel the claim *************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Click on Hcl Banner Popup Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Geo HR Initiate of Behalf of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Validate Initiate Claim Page & Select Claim type-Exgratia - Language
		 * allowance
		 */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_exgratia_la_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* click on Claimtype Radio Button */
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* Fill the form with datas */
		pages.throughTheFormPage.fillTheExgratiaLanguageAllowance(fromDate, toDate, employeeCode, countryCode,
				companyCode, amount, remarks, rate, noOfInstallments,
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_msg")));

		/* Click on Through The Form Submit Button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();

		/* Validate Popup is displayed */
		pages.throughTheFormPage.validatePopup();

		/* Click on Through The Form Yes Button */
		pages.throughTheFormPage.clkPopUpYesButton();

		/*
		 * Claim requests has been successfully raised. Pop up message should be
		 * displayed
		 */
		pages.throughTheFormPage.validateClaimSuccessMessage();

		// Click on Menu Bar and Click on Initiate Claims
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));

		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Validate list of Claims for Approval in Initiate Claims Page */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on Approve Claim ID */
		pages.initiateClaimsPage.clkClaimID(claimId);
		/* Enter the remarks Approve Or ReferBack Remarkstextarefield */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(remarks);

		/* Click on Cancel Button */
		pages.initiateClaimsPage.clkAppClaimsPopUpCancel();

	}
}
