package com.hcl.gim.testscripts.clientbonus_reward_incentive;



import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/***
 * TestCaseID:TYSS_GIM_0015
 * Claim Type:Client Bonus/Reward/Incentive
 * Test Script Name:TYSS_GIM_CBRI_0015
 * @author shreya.u
 * Description: Verify that user is able to clear the data for 'Client Bonus/Reward/Incentive' allowance claim after valid 
 *             data and .pdf extension is uploaded
 * 
 */
public class TYSS_GIM_CBRI_0015 extends BaseTest{
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ClientBonusRewardIncentive where SlNo ='15'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:Verify that the user is able to clear the data for the claim")
	public synchronized void TC_TYSS_GIM_CBRI_0015(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String typeOfPayment, String fromDate, String toDate, String remarks) {
		
		
	    String[] data = { employeeCode, countryCode, companyCode, amount,typeOfPayment, fromDate, toDate, remarks };
		String [] format= {"number"	,"number","number","number","string","date","date","string"};
		 Map<String, String> mapDataAndFormat= WebActionUtil.getData_FormatMap(data, format);
	
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));


		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);
		
		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));
		
		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),  prop_app_constants.getProperty("expectedHomePageTitle"), prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select the Client Bonus Reward Incentive claim type */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"),  prop_app_constants.getProperty("expectedClaimPageText") , prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				 prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleLOBHR"));
		
		
		/* Click on Excel Upload Tab */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Click on Download Template link */
		String downloadedDocumentName =pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_client_bonus_reward_incentive_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
			
		/* Click on Upload template and upload template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Click on Upload file */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_pdf")));

		/* Click on Clear button */
		pages.initiateClaimPage.clkExcelClearButton();
	

}
}
