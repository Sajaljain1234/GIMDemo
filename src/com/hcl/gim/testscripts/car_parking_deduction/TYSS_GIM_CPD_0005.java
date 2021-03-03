package com.hcl.gim.testscripts.car_parking_deduction;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/**
 * TestCaseId:TYSS_GIM_0005 Claim Type:Car parking Deduction TestSCript
 * Name:TYSS_GIM_CPD_0005 Description:Verify that USer is able to Cancel the
 * 'Car Parking Deduction' allowance' claim for which the Data Has been upload
 * by the GEO HR.
 * 
 * @author Vivek Dogra
 *
 */
public class TYSS_GIM_CPD_0005 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from CarParkingDeduction where SlNo='5'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:Verify that USer is able to Cancel the 'Car Parking Deduction' allowance' claim for which the Data Has been upload by the GEO HR.")
	public synchronized void TC_TYSS_GIM_CPD_0005(String slNo, String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String periodicity,
			String noofInstalments, String remarks) {

		Map<String, String[]> logindata = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"));

		String[] getGeoHRLoginCredentials = logindata.get(prop_app_constants.getProperty("roleGEOHR"));
		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

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

		/* Download Form Template */
		String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(
				prop_app_constants.getProperty("claimtype_car_parking_deduction_sheetname"));
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/* SQL query */
		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,Periodicity,Noofinstalments,Remarks from CarParkingDeduction WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";

		/* Copy Paste the content from excel sheet */
		pages.initiateClaimPage.clkPasteYourContentHereText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_car_parking_deduction_login"), strQuery,
				downloadedDocumentName);

		/* Upload the file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_msg")));

		/* Click on Upload Button */
		pages.initiateClaimPage.clkUploadButton();
		/* Click on Yes Button */
		WebActionUtil.waitForAngularPageToLoad();
		pages.initiateClaimPage.clkYesButton();
		/* Navigate to menu and click on Initiate Claims */
		WebActionUtil.waitForAngularPageToLoad();
		pages.homePage.goToInitiateClaims();

		/* Validate List of claims need approval is displayed */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();
		/* Retrieve the claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		/* click on check box for cancel claim id */
		pages.initiateClaimsPage.clkchkClaim(claimID);
		/* Click on Cancel button */
		pages.initiateClaimsPage.clkCancel();
		/* Enter Remark */
		pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));
		/* Click cancel submit */
		pages.initiateClaimsPage.clkCancelSubmit();
	}
}
