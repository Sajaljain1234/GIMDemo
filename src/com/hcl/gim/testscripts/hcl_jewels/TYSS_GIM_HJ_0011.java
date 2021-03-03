package com.hcl.gim.testscripts.hcl_jewels;
import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId:TYSS_GIM_0011 
 * Claim Type:HCL Jewels TestSCript
 * Name:TYSS_GIM_HJ_0011 
 * Description:Verify that User is able to Cancel the 'HCL Jewels allowance' Claim for which the data has been uploaded by the LOB HR.
 * 
 * @author Suganthini
 *
 */
public class TYSS_GIM_HJ_0011 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from HCLJewels where SlNo='11'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that user is able to cancel the 'HCL Jewles' allowance claim for which the data has been uploaded by the LOB HR using excel upload option")
	public synchronized void TC_TYSS_GIM_HJ_0011(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String payment, String remarks) throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_hcl_jewels_login"));

		String[] data = { employeeCode, countryCode, companyCode, amount, fromDate, toDate, payment, remarks };
		String[] format = { "number", "number", "number", "number", "date", "date", "string", "string" };
		Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);
		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/******* LOB HR Login *********/
		
		/* Enter user name and Password and login as LOB HR */
		pages.loginpage.loginToApplication(lobhrLoginDetails[0], lobhrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleLOBHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkLOBHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_hcl_jewels_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleLOBHR"));

		/* Click Excel upload */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Click on Download form template link and retrieve the download file */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_hcl_jewels_login"));

		/* Validate Download template */
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* Upload the excel */
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat, downloadedDocumentName);

		/* Upload or Drag and Drop your files here */
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_msg")));

		/* click on Upload Button */
		pages.initiateClaimPage.clkExcelUploadButton();

		/* click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* click on Initiate Claims */
		pages.homePage.goToInitiateClaims();

		/* Retrieve the claim ID */
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/* Validate List of all the claims in InitiateClaimPage */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Click on Cancel button */
		pages.initiateClaimsPage.clkCancelClaim(claimId);

		/* Enter Remark and click on submit button */
		pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click cancel submit */
		pages.initiateClaimsPage.clkCancelSubmit();

	}
}
