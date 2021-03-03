package com.hcl.gim.testscripts.patent_award;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId: TYSS_GIM_0011
 * Claim Type: Patent Award Allowance
 * TestScript Name: TYSS_GIM_PA_0011
 * Description: Verify that user is able to cancel the 'Patent Award' allowance claim for which the 
 * data has been uploaded by the LOB HR
 * 
 * Author: Sajal jain
 */
public class TYSS_GIM_PA_0011 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from PatentAward where SlNo='11'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that user is able to cancel the 'Patent Award' allowance claim for which the data has been uploaded by the LOB HR using excel upload option")
	public void TC_TYSS_GIM_PA_0011(String slNo,String employeeCode, String countryCode, String companyCode, String amount,
			String fromDate, String toDate, String patentId, String remarks) throws InterruptedException {

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		String[] data = { employeeCode, countryCode, companyCode, amount, fromDate, toDate, patentId, remarks };
		String[] format = { "number", "number", "number", "number", "date", "date", "number", "string" };
		Map<String,String> mapDataAndFormat=WebActionUtil.getData_FormatMap(data,format);
		
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

		/* Click on Excel button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Click on Download form template link and retrieve the download file */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_patent_award_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Upload the Excel */
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

		/* Retrieve the claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on Cancel button */
		pages.initiateClaimsPage.clkCancelClaim(claimID);

		/* Enter Remark */
		pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));

		/*
		 * Click cancel submit and validate Claim requests has been successfully
		 * cancelled toast message
		 */
		pages.initiateClaimsPage.clkCancelSubmit();

	}
}