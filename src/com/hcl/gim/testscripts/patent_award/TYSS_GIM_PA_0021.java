package com.hcl.gim.testscripts.patent_award;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId: TYSS_GIM_0021
 * Claim Type: Patent Award Allowance
 * TestScript Name: TYSS_GIM_PA_0021
 * Description: Verify that user is able to reset the data for 'Patent Award' allowance claim after valid data 
 * and .docx extension is uploaded
 * 
 * Author: Sajal jain
 */
public class TYSS_GIM_PA_0021 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from PatentAward where SlNo='21'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that user is able to reset the data for 'Patent Award' allowance claim after valid data and .docx extension is uploaded in through the form option")
	public void TC_TYSS_GIM_PA_0021(String slNo,String employeeCode, String countryCode, String companyCode, String amount,
			String fromDate, String toDate, String patentId, String remarks) throws InterruptedException {

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
		
		/* Click on Through the form button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* fill the form */
		pages.throughTheFormPage.fillThePatentAward(fromDate, toDate, employeeCode, countryCode, companyCode, amount,
				remarks, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")), patentId);

		/* Click on Reset button */
		pages.throughTheFormPage.clkThroughFormResetButton();

		/* Validate data reset */
		pages.throughTheFormPage.validateResetThePatentAward();
	}

}
