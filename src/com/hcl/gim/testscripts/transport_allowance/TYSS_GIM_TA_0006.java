package com.hcl.gim.testscripts.transport_allowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId : TYSS_GIM_0006
 *Claim Type : Transport Allowance
 *TestScript Name : TYSS_GIM_TA_0006
 *Description : Verify that User is able to Cancel the Transport Allowance claim for which the Data has been uploaded by the HEO HR
 *Author : Vikas
 * 
 * */
public class TYSS_GIM_TA_0006 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from TransportAllowance where SlNo ='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that user is able to cancel the claim for which the Data has been uploaded by the HEO HR")

	public synchronized void TC_TYSS_GIM_TA_0006(String slNo, String employeeCode, String countryCode, String companyCode,  String amount, 
			String fromDate, String toDate, String inputType, String remarks) {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_transport_allowance_login"));
		String[] geoHRLoginCredentials = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

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

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_transport_allowance_dropdown"));;
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
		
		String strQuery = "SELECT EmployeeCode, CountryCode, CompanyCode, Amount, FromDate, ToDate, InputType, Remarks from TransportAllowance WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";
		
		/* Click on Paste Your Content Here */
		pages.initiateClaimPage.clkPasteYourContentHereText(prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"), 
				prop_app_constants.getProperty("claimtype_transport_allowance_sheetname"),
				strQuery, downloadedDocumentName);

		/* Click on Upload Or Drag And Drop Files */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();

		/* Click on Yes Button */
		pages.initiateClaimPage.clkYesButton();

		/* Click on Initiate Claims */
		pages.homePage.goToInitiateClaims();
		
		/* Verify list of claims */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/* Get Claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		
		/* Click on Cancel Claim Under Actions Tab */
		pages.initiateClaimsPage.clkCancelClaim(claimID);

		/* Enter Remarks */
		pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));

		/* Click on Cancel Claim Submit Button */
		pages.initiateClaimsPage.clkCancelSubmit();

	}
}
