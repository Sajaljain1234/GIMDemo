package com.hcl.gim.testscripts.ikhaya;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM_014
 *Claim Type: Ikahya safety bonus
 *TestScript Name: TYSS_GIM_ISB_014
 *Description: Verify that the User is able to cancel the  'Ikahya safety bonus(Client-seriti/elango) allowance' Claim for which the Data has been Uploaded by GEO HR. 
 *            
 *Author: Manish Kumar C D
 */

public class TYSS_GIM_ISB_014 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql="Select * from Ikahyasafetybonus where SlNo='14'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that the User is able to cancel the  'Ikahya safety bonus(Client-seriti/elango) allowance' Claim for which the Data has been Uploaded by GEO HR.")
	public synchronized void TC_TYSS_GIM_ISB_014(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String rate,String noofInstalments,String remarks) throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_login"));

		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		String [] data= {employeeCode,countryCode,companyCode,amount,fromDate,toDate,rate,noofInstalments,remarks};

		String [] format= {"number"	,"number","number","number","date","date","string","number","string"};

		Map<String,String> mapDataAndFormat =WebActionUtil.getData_FormatMap(data, format);

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/************ GEO HR Login ******************/
		/* Login as GEO HR */
		pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);

		/* Close the HCL Banner Pop Up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on behalf of the employee*/
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

		/*Select Ikahya Safety Bonus */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

		/*click on Claimtype Radio Button*/
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_excel_upload"));

		/*Download Form Template*/
		String validDownloadDocumentName = pages.initiateClaimPage.clkExcelDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_dropdown"));
		WebActionUtil.validateDownloadedDocument(validDownloadDocumentName);

		/*uploads the downloaded template to Upload Template*/
		pages.initiateClaimPage.clkExcelUploadTemplateLink(mapDataAndFormat,validDownloadDocumentName);

		/*Upload or Drag and Drop your files here*/
		pages.initiateClaimPage.clkExcelUploadorDragandDropyourfileshereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_pdf")));

		/*click on Upload Button*/
		pages.initiateClaimPage.clkExcelUploadButton();

		/*click on Yes Button*/
		pages.initiateClaimPage.clkYesButton();

		/*click on Initiate Claims*/
		pages.homePage.goToInitiateClaims();

		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);

		/*Validate list of Claims for Approval in Initiate Claims Page*/
		pages.initiateClaimsPage.validateInitiateClaimsPageData();

		/*click on Cancel tool tip*/
		pages.initiateClaimsPage.clkCancelClaim(claimId);

		/*Enter the remarks in Remarks Textareafied*/
		pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));

		/*Click on Submit Button*/
		pages.initiateClaimsPage.clkCancelSubmit();
	}
}
