package com.hcl.gim.testscripts.exgratia_language_allowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/*TestCaseId:TYSS_GIM_0021
 *Claim Type:Exgatia - Language allowance
 *TestScript Name: TYSS_GIM_ELA_0021
 *Description:Verify that the User is able to Reset Data for Exgratia-Language allowance claim after data and .docx extension uploaded.  
 *            
 *Author : Harsha K
 * 
 * */

public class TYSS_GIM_ELA_0021 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaLanguageAllowance where SlNo ='21'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that the User is able to Reset Data for Exgratia-Language allowance claim after data and .docx extension uploaded.")
	public synchronized void TC_TYSS_GIM_ELA_0021(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String rate, String noOfInstallments, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_la_login"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Get GeoHR Login details */
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Click on Hcl Banner Popup Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Geo HR Initiate of Behalf of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select Exgratia-Language Bonus */
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
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/* Click on Reset Button */
		pages.throughTheFormPage.clkThroughFormResetButton();

		/* Validate Exgratia Language Allowance is Reset */
		pages.throughTheFormPage.validateReseteExgratiaLanguageAllowance();
	}
}