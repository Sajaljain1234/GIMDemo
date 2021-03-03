package com.hcl.gim.testscripts.retention_bonus;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM__0019
 *Claim Type: RetentionBonus
 *TestScript Name: TYSS_GIM_RB_0019
 *Description: Verify that user is able to cancel the Retention Bonus claim after data has been uploaded by Geo HR
 *            
 *Author: Abhilash B
 */
public class TYSS_GIM_RB_0019 extends BaseTest{
	
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from RetentionBonus where SlNo ='19'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:Verify that user is able to cancel the Retention Bonus claim after data has been uploaded(through the form)")
	public synchronized void TC_TYSS_GIM_RB_0019(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String bonusperiod,String remarks) throws InterruptedException  

	 { 
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_retention_bonus_login"));
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));		
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		
		 /****** Geo HR Login *******/
		/*login as Geo HR*/
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
		
		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
					
		/* Select the Retention Bonus claim type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Excel Upload Tab */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));
	
		
		/*Enter below Details for Client Bonus Reward Incentive */
		pages.throughTheFormPage.fillTheRetentionBonus(fromDate, toDate, employeeCode, countryCode, companyCode, amount, remarks,bonusperiod, WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));
		
		/*Click on SUBMIT Button.*/
		pages.throughTheFormPage.clkThroughFormSubmitButton();
		
		/*Message saying 'Are you sure you want to proceed' Should be displayed.*/
		pages.throughTheFormPage.validatePopup();
		
		/*Click on 'YES'.*/
		pages.throughTheFormPage.clkPopUpYesButton();
		
		/*Claim requests has been successfully raised. Pop up message should be displayed*/
		pages.throughTheFormPage.validateClaimSuccessMessage();	
		
		/* Click on Menu Bar and Click on Initiate Claims */
		pages.homePage.goToInitiateClaims();
		
		/*validates list of claims*/
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Get the claim id */
		String claimid = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		
		/* Click on Submit button Under the Actions Tab for the Uploaded Claim. */
		pages.initiateClaimsPage.clkClaimID(claimid);

		/*Enter cancel Remark */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));
		
		/*clicks on cancel button */
		pages.initiateClaimsPage.clkAppClaimsPopUpCancel();
					
		
	}

}
