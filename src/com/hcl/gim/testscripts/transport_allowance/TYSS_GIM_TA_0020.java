package com.hcl.gim.testscripts.transport_allowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId : TYSS_GIM_0020
 *Claim Type : Transport Allowance
 *TestScript Name : TYSS_GIM_TA_0020
 *Description : Verify that User is able to Reset the Data for Transport Allowance claim after valid Data and .jpg Extension is uploaded
 *Author : Vikas
 * 
 * */
public class TYSS_GIM_TA_0020 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from TransportAllowance where SlNo ='20'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that user is able to reset the data after valid Data and .jpg extension file is uploaded")
	
	public synchronized void TC_TYSS_GIM_TA_0020(String slNo, String employeeCode, String countryCode, String companyCode,  String amount, 
			String fromDate, String toDate, String inputType, String remarks) {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_transport_allowance_login"));
		String[] geoHRLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Enter GEO HR user name and Password */
		pages.loginpage.loginToApplication(geoHRLoginCredentials[0], geoHRLoginCredentials[1]);

		/* Click on HCL Banner Popup */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate Behalf Of Employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_transport_allowance_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Through The Form Radio Button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));
		
		/* Click on Through The Form Reset Button */
		pages.throughTheFormPage.filltheTransportAllowance(fromDate, toDate, employeeCode, countryCode, companyCode, amount, remarks, inputType, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));
		
		/* Click on Through The Form Reset Button */
		pages.throughTheFormPage.clkThroughFormResetButton();
		
		/* Validate Data Reset */
		pages.throughTheFormPage.validateResetTransportAllowance();
	}

}
