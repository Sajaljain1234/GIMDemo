package com.hcl.gim.testscripts.patent_award;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId: TYSS_GIM_0017
 * Claim Type: Patent Award Allowance
 * TestScript Name: TYSS_GIM_PA_0017
 * Description: Verify that user is able to cancel the 'Patent Award' allowance claim for which the data
 * has been uploaded by the LOB HR
 * 
 * Author: Sajal jain
 */
public class TYSS_GIM_PA_0017 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from PatentAward where SlNo='17'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that user is able to cancel the 'Patent Award' allowance claim for which the data has been uploaded by the LOB HR using through the form option")
	public void TC_TYSS_GIM_PA_0017(String slNo,String employeeCode, String countryCode, String companyCode, String amount,
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
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* fill the form */
		pages.throughTheFormPage.fillThePatentAward(fromDate, toDate, employeeCode, countryCode, companyCode, amount,
				remarks, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")), patentId);

		/* Click on submit button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();

		/* Validate Are you sure you want to proceed Pop up */
		pages.throughTheFormPage.validatePopup();

		/* Click on Yes button in pop up */
		pages.throughTheFormPage.clkPopUpYesButton();

		/* Validate claim success message */
		pages.throughTheFormPage.validateClaimSuccessMessage();

		/* click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Retrieve the claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/*
		 * click on claim ID of initiated claim and Validate Claim Details Pop up is
		 * displayed
		 */
		pages.initiateClaimsPage.clkClaimID(claimID);

		/* Enter remark in claim id pop up */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click cancel button in claim id pop up and validate success message */
		pages.initiateClaimsPage.clkAppClaimsPopUpCancel();

	}
}