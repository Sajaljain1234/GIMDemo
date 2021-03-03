package com.hcl.gim.testscripts.rsu;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/***
 * TestCaseID:TYSS_GIM_0017 Claim Type:RSU Payment(Restricted Stock Units) Test
 * Script Name:TYSS_GIM_RSU_0017
 * 
 * @author Vivek dogra
 *  Description: Verify user is able to cancel LOB HR for RSUPayment(Restricted Stock Units)
 * 
 */
public class TYSS_GIM_RSU_0017 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from RSUPaymentRestrictedStock where SlNo='17'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description: Verify user is able to cancel LOB HR for RSUPayment(Restricted Stock Units)")
	public synchronized void TC_TYSS_GIM_RSU_0017(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String remarks) {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));
		
		/* Login as GEO HR */
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
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
				prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));


		/* Click on Through the form button */
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));


		/* fill the form */
		pages.throughTheFormPage.fillTheRSUPayment(fromDate, toDate, employeeCode, countryCode, companyCode, amount,
				remarks, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpg")));

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
