package com.hcl.gim.testscripts.hcl_jewels;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId:TYSS_GIM_0021
 * Claim Type:HCL Jewels TestSCript
 * Name:TYSS_GIM_HJ_0021 
 * Description:Verify that User is able to Reset the Data for 'HCL jewels allowance' 
   Claim after valid Data and .pdf Extension is uploaded.
 * 
 * @author suganthi.r
 *
 */
public class TYSS_GIM_HJ_0021 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from HCLJewels where SlNo='21'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that user is able to reset the data for 'HCL Jewles' allowance claim after valid data and .pdf extension is uploaded in through the form option")
	public void TC_TYSS_GIM_HJ_0021(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String payment, String remarks) throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_hcl_jewels_login"));
		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		/** LOB HR Login***/
		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_hcl_jewels_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));

		/* Click Through the form */
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* Enter the employee details */
		pages.throughTheFormPage.fillTheHCLJewels(fromDate, toDate, employeeCode, countryCode, companyCode, amount,
				remarks, payment, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_pdf")));

		/* Click on reset button */
		pages.throughTheFormPage.clkThroughFormResetButton();

		/* Validate reset HCL Jewels */
		pages.throughTheFormPage.validateResetHCLJewel();
	}
}
