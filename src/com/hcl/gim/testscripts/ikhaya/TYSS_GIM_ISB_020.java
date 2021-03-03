package com.hcl.gim.testscripts.ikhaya;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM_020
 *Claim Type: Ikahya safety bonus
 *TestScript Name: TYSS_GIM_ISB_020
 *Description: Verify that the User is able to reset the Data 'Ikahya safety bonus(Client-seriti/elango) allowance' Claim afer Valid data and .jpeg extension is uploaded.
 *            
 *Author: Manish Kumar C D
 */
public class TYSS_GIM_ISB_020 extends BaseTest{
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql="Select * from Ikahyasafetybonus where SlNo='20'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that the User is able to reset the Data 'Ikahya safety bonus(Client-seriti/elango) allowance' Claim afer Valid data and .jpeg extension is uploaded." )
	public synchronized void TC_TYSS_GIM_ISB_020(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String rate,String noofInstalments,String remarks) throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_login"));

		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/************ GEO HR Login ******************/
		/* Login as GEO HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee*/
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*Select Ikahya Safety Bonus */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

		/*click on Claimtype Radio Button*/
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/*Fills the Ikahya Safetybonus and Uploads the File*/
		pages.throughTheFormPage.fillTheIkahyaSafetybonus(fromDate, toDate, employeeCode, countryCode, companyCode, amount, remarks, noofInstalments, rate, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/*click on Reset Button*/
		pages.throughTheFormPage.clkThroughFormResetButton();

		/*Data should be Reset*/
		pages.throughTheFormPage.validateResetIkahyaSafetybonus();
	}
}
