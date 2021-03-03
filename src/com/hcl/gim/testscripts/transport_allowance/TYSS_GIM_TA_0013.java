package com.hcl.gim.testscripts.transport_allowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId : TYSS_GIM_0013
 *Claim Type : Transport Allowance
 *TestScript Name : TYSS_GIM_TA_0013
 *Description : Verify that User is able to clear the Data for Transport Allowance claim after valid Data and .jpeg extension is uploaded
 *Author : Vikas
 * 
 * */
public class TYSS_GIM_TA_0013 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from TransportAllowance where SlNo ='13'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that user is able to clear the data after valid data and .jpeg extension is uploaded")

	public synchronized void TC_TYSS_GIM_TA_0013(String slNo, String employeeCode, String countryCode, String companyCode,  String amount, 
			String fromDate, String toDate, String inputType, String remarks) {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_transport_allowance_login"));
		String[] geoHRLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		String [] data= {employeeCode, countryCode, companyCode, amount, fromDate, toDate, inputType, remarks};

		String [] format= {"number", "number", "number", "number", "date", "date", "string", "string"};

		Map<String,String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data,format);

		
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

		/* Click on Excel Upload Option Radio Button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));
		
		/* Click on Excel Template Download Link */
		String downloadedDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_transport_allowance_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
		
		/* uploads the downloaded template to Upload Template*/
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat,downloadedDocumentName);

		/* Click on Upload Or Drag And Drop Files */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* Click on Excel Clear Button */
		pages.initiateClaimPage.clkExcelClearButton();
	}

}
