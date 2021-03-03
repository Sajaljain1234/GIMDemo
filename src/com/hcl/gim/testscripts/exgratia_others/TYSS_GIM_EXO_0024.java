package com.hcl.gim.testscripts.exgratia_others;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 *TestCaseId: TYSS_GIM_0024
 *Claim Type: Exgratia Others
 *TestScript Name: TYSS_GIM_EXO_0024
 *Description: Verify the user is able to reset the data for Exgratia other 
 *             claim after valid data and docx extension is uploaded.
 *            
 *Author: Aatish Slathia
 */

public class TYSS_GIM_EXO_0024 extends BaseTest{

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaOthers where SlNo ='24'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the user is able to reset the data after valid data and docx extension is uploaded.")
	public synchronized void TC_TYSS_GIM_EXO_0024(String slNo, String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String rate, String noofInstalments,
			String remarks) throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_others_login"));
		
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		/* Get login details for role Geo HR*/
		    String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		

		/************ GEO HR Login ******************/
		/*Login as Geo HR*/
		pages.loginpage.loginToApplication(geoHrLoginDetails[0],geoHrLoginDetails[1]);
		
		/*Click on Hcl Banner Popup Close Button*/
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
			
		/* Click on Initiate on behalf of the employee*/
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
		
		/* Select Exgratia others */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));
		
		/*click on Claimtype Radio Button*/
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));
		
		/* Fills the Exgratia others and Uploads the File */
		pages.throughTheFormPage.fillTheExgratiaOther(fromDate, toDate, employeeCode, countryCode, companyCode, amount,
				 remarks,rate, noofInstalments,
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/*click on Reset Button*/
		pages.throughTheFormPage.clkThroughFormResetButton();
		
		/*Data should be Reset*/
		pages.throughTheFormPage.validateResetExgratiaOther();
	}
}

