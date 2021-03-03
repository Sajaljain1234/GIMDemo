package com.hcl.gim.testscripts.retention_bonus;


import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM__0012
 *Claim Type: RetentionBonus
 *TestScript Name: TYSS_GIM_RB_0012
 *Description: verify that user is able to clear the data after valid data and jpeg file is uploaded
 *Author: Abhilash B
 */
public class TYSS_GIM_RB_0012 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from RetentionBonus where SlNo ='12'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:verify that user is able to clear the data after valid data is uploaded(Excel upload)")
	public synchronized void TC_TYSS_GIM_RB_0012(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String bonusperiod,String remarks) throws InterruptedException  

	 {
		 Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_retention_bonus_login"));
		 String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		 String[] data= { employeeCode, countryCode, companyCode, amount,fromDate,toDate,bonusperiod,remarks};
         String [] format= {"number"	,"number","number","number","date","date","string","string"};
         Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);
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
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));
		
		/* Download the Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"));
		
		/*validates the download*/
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
		

		/* Click on Upload template and upload template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat,downloadedDocumentName);

		/* Click on Upload file */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));
        
		/* Click on clear button */
		pages.initiateClaimPage.clkExcelClearButton();
		
		
	}
}
