package com.hcl.gim.testscripts.clientbonus_reward_incentive;



import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/***
 * TestCaseID:TYSS_GIM_0017
 * Claim Type:Client Bonus/Reward/Incentive
 * Test Script Name:TYSS_GIM_CBRI_0017
 * @author shreya.u
 * Description: Verify that the user is able to cancel the Client Bonus/Reward/Incentive for which the data has been uploaded
 * 
 */
public class TYSS_GIM_CBRI_0017 extends BaseTest{
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from ClientBonusRewardIncentive where SlNo ='17'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description: Verify that the user is able to cancel the claim for which the data has been uploaded")
	public synchronized void TC_TYSS_GIM_CBRI_0017(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String typeOfPayment, String fromDate, String toDate, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		
		/* Login as LOB HR */
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);


		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));
		
		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),  prop_app_constants.getProperty("expectedHomePageTitle"), prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select the Client Bonus Reward Incentive claim type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"),  prop_app_constants.getProperty("expectedClaimPageText") , prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				 prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleLOBHR"));
		
		
		/* Click on Through the form Tab */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));
		
		/* Enter below Details for Client Bonus Reward Incentive */
		pages.throughTheFormPage.fillTheClientBonusRewardIncentive(fromDate, toDate, employeeCode, countryCode, companyCode, amount, remarks, typeOfPayment, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* Click on SUBMIT Button. */
		pages.throughTheFormPage.clkThroughFormSubmitButton();

		/* Message saying 'Are you sure you want to proceed' Should be displayed. */
		pages.throughTheFormPage.validatePopup();

		/* Click on 'YES'. */
		WebActionUtil.waitForAngularPageToLoad();
		pages.throughTheFormPage.clkPopUpYesButton();

		/*
		 * Claim requests has been successfully raised. Pop up message should be
		 * displayed
		 */
		pages.throughTheFormPage.validateClaimSuccessMessage();

		/* Click on Menu Bar and Click on Initiate Claims */
		WebActionUtil.waitForAngularPageToLoad();
		pages.homePage.goToInitiateClaims();
		
		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Get the claim id */
		String claimid = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Click on initiated claim ID number */
		pages.initiateClaimsPage.clkClaimID(claimid);
		/* Enter Remark */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click on Refer Back button */
		pages.initiateClaimsPage.clkAppClaimsPopUpCancel();
		
		
	}
}
