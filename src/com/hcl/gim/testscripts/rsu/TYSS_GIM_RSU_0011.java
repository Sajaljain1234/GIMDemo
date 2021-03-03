package com.hcl.gim.testscripts.rsu;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/***
 * TestCaseID:TYSS_GIM_0011 Claim Type:Client RSU Payment(Restricted Stock
 * Units) Test Script Name:TYSS_GIM_RSU_0011
 * 
 * @author Vivek dogra
 *  Description: Verify that the user is able to cancel the RSU Payment(Restricted Stock Units) for which the data has been uploaded
 * 
 */
public class TYSS_GIM_RSU_0011 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from RSUPaymentRestrictedStock where SlNo ='11'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description: Verify that the user is able to cancel the RSU Payment(Restricted Stock Units) for which the data has been uploaded")
	public synchronized void TC_TYSS_GIM_RSU_0011(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_login"));

		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));
		String[] data = { employeeCode, countryCode, companyCode, amount, fromDate, toDate, remarks };
		String[] format = { "number", "number", "number", "number", "date", "date", "string" };
		Map<String, String>mapDataAndFormat =  WebActionUtil.getData_FormatMap(data, format);
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

		/* Click on excel button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/*
		 * Click on Download form template link and validate retrieve the download file
		 */
		String downloadedDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_rsu_payment_restricted_stock_login"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Upload the excel */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Upload or Drag and Drop your files here */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* click on Upload Button */
		pages.initiateClaimPage.clkExcelUploadButton();

		/* click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Retrieve the claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Click cancel tool tip under actions tab for the uploaded claim. */
		pages.initiateClaimsPage.clkCancelClaim(claimID);
		
		/* Enter Remark and click on submit button */
		pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));
		
		/*Click on submit button*/
		pages.initiateClaimsPage.clkCancelSubmit();
		
	

}}
