package com.hcl.gim.testscripts.exgratia_language_allowance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId:TYSS_GIM_0011
 *Claim Type:Exgatia - Language allowance
 *TestScript Name: TYSS_GIM_ELA_0011
 *Description:Verify the user is able to Cancel Exgratia-Language allowance claim 
 *            for which the data has been uploaded by the GeO HR
 *Author : Harsha K
 * 
 * */
public class TYSS_GIM_ELA_0011 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaLanguageAllowance where SlNo ='11'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify the user is able to Cancel Exgratia-Language allowance claim for which the data has been uploaded by the GeO HR")
	public synchronized void TC_TYSS_GIM_ELA_0011(String slNo,String employeeCode, String countryCode, String companyCode,
			String amount, String fromDate, String toDate, String rate, String noOfInstallments, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_la_login"));

		String[] data = { employeeCode, countryCode, companyCode, amount, fromDate, toDate, rate, noOfInstallments,
				remarks };
		String[] format = { "number", "number", "number", "number", "date", "date", "string", "number", "string" };
		Map<String, String> mapDataAndFormat = WebActionUtil.getData_FormatMap(data, format);

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/**** Get login details for role Geo HR *******/
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		/***** Login as GEO HR to upload & Cancel Claim on behalf of employee ******/

		// Login as GEO HR
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		// Close the HCL Banner Pop Up
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
				prop_app_constants.getProperty("claimtype_exgratia_la_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* click on Claim type Radio Button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/* Download Form Template & validate the document */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_exgratia_la_dropdown"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* uploads the downloaded template to Upload Template */
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

		/*Retrieve the claim id*/
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		/* Validate Initiate claim page data */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();
		
		/* Click on Cancel button */
		pages.initiateClaimsPage.clkCancelClaim(claimId);

		/* Enter Remark */
		pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));

		/*
		 * Click cancel submit and validate Claim requests has been successfully
		 * cancelled toast message
		 */
		pages.initiateClaimsPage.clkCancelSubmit();
	}
}
