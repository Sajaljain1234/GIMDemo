package com.hcl.gim.testscripts.exgratia_language_allowance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId:TYSS_GIM_0003
 *Claim Type:Exgatia - Language allowance
 *TestScript Name: TYSS_GIM_ELA_0003
 *Description:Verify that user is able to cancel Exgratia-Language allowance claim 
 *            which the data has been uploaded by the GEO HR
 *Author : Harsha K
 * 
 * */
public class TYSS_GIM_ELA_0003 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from ExgratiaLanguageAllowance where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that user is able to cancel Exgratia-Language allowance claim which the data has been uploaded by the GEO HR")
	public synchronized void TC_TYSS_GIM_ELA_0003(String slNo, String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String rate, String noOfInstallments,
			String remarks) throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_exgratia_la_login"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/* Get login details for role Geo HR */
		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

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

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_exgratia"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,Rate,NoofInstalments,Remarks from ExgratiaLanguageAllowance WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";
		// Copy the Data From the Excel file and Paste it in the Place saying 'Paste
		// your content here
		pages.initiateClaimPage.clkPasteYourContentHereText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_exgratia_la_sheetname"), strQuery, downloadedDocumentName);

		// Upload .msg file
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_msg")));

		// Click on UPLOAD Button
		pages.initiateClaimPage.clkUploadButton();

		// Click on 'YES'
		pages.initiateClaimPage.clkYesButton();

		// Click on Menu Bar and Click on Initiate Claims
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		// Get the claim id
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		
		/* Click on initiated claim check box */
		pages.initiateClaimsPage.clkchkClaim(claimId);

		/* Click on cancel */
		pages.initiateClaimsPage.clkCancel();

		/* Set remark */
		pages.initiateClaimsPage.setCancelClaimRemark(remarks);

		/* Click on submit button */
		pages.initiateClaimsPage.clkCancelSubmit();

	}
}
