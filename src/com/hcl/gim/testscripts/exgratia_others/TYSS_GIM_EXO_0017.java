package com.hcl.gim.testscripts.exgratia_others;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId: TYSS_GIM_0017 
 * Claim Type: Exgratia Others 
 * TestScript Name: TYSS_GIM_EXO_0017 
 * Description: Verify the user is able to clear the data of
 *               Exgratia others claim after valid data and docx Extension is uploaded.
 * 
 * Author: Aatish Slathia
 */

public class TYSS_GIM_EXO_0017 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaOthers where SlNo ='17'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the user is able to clear the data after valid data and docx Extension is uploaded.")
	public synchronized void TC_TYSS_GIM_EXO_0017(String slNo, String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String rate, String noofInstalments,
			String remarks) throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_exgratia_others_login"));

		String[] data = { employeeCode, countryCode, companyCode, amount, fromDate, toDate, rate, noofInstalments,
				remarks };
		String[] format = { "number", "number", "number", "number", "date", "date", "string", "number", "string" };
		Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Get login details for role Geo HR */
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		/*************** GeoHR Login to upload & clear the claim *************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Click on Hcl Banner Popup Close Button */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/*
		 * Validate Initiate Claim Page & Select Claim type-Exgratia - Language
		 * allowance
		 */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_exgratia_others_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* click on Claim type Radio Button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtyperadio_excel_upload"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* uploads the downloaded template to Upload Template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Upload or Drag and Drop your files here */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

		/* click on Clear Button */
		pages.initiateClaimPage.clkExcelClearButton();
	}
}
