package com.hcl.gim.testscripts.car_parking_deduction;


import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 * TestCaseId:TYSS_GIM_0009 Claim Type:Car parking Deduction
 * TestSCriptName:TYSS_GIM_CPD_0009 
 * Description:Verify the User is able to Clear the data  for 'Car Parking Deduction' allowance' 
 * @author Vivek Dogra
 *
 */
public class TYSS_GIM_CPD_0009 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from CarParkingDeduction where SlNo='9'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:Verify the User is able to Clear the data  for 'Car Parking Deduction' allowance'")
	public synchronized void TC_TYSS_GIM_CPD_0009(String slNo,String EmployeeCode, String CountryCode,
			String CompanyCode, String Amount, String fromDate, String toDate, String periodicity,
			String NoofInstalments, String remarks) throws InterruptedException {

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		String[] data = { EmployeeCode, CountryCode, CompanyCode, Amount, fromDate, toDate, periodicity,
				NoofInstalments, remarks };
		String[] format = { "number", "number", "number", "number", "date", "date", "string", "number", "string" };
		Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);
		
		Map<String, String[]> logindata = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"));
		String[] getGeoHRLoginCredentials = logindata.get(prop_app_constants.getProperty("roleGEOHR"));
		
		/************ GEO HR Login ******************/
		/* Login as Geo HR */
		pages.loginpage.loginToApplication(getGeoHRLoginCredentials[0], getGeoHRLoginCredentials[1]);

			/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Validate Initiate Claim page is displayed and Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));
		
		/* Click on Excel Upload Tab */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);


		/* uploads the downloaded template to Upload Template */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Upload or Drag and Drop your files here */
		pages.initiateClaimPage
				.clkExcelUploadorDragandDropyourfileshereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* click on Clear Button */
		pages.initiateClaimPage.clkExcelClearButton();
	}
}
