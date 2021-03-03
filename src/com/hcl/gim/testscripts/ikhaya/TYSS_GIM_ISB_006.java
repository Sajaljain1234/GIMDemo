package com.hcl.gim.testscripts.ikhaya;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM_006
 *Claim Type: Ikahya safety bonus
 *TestScript Name: TYSS_GIM_ISB_006
 *Description: Verify that User is able to Cancel the 'Ikahya safety bonus(client-seriti/elango) allowance' Claim for which the Data has been uploaded by the GEO HR.
 *            
 *Author: Manish Kumar C D
 */

public class TYSS_GIM_ISB_006 extends BaseTest{
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql="Select * from Ikahyasafetybonus where SlNo='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that User is able to Cancel the 'Ikahya safety bonus(client-seriti/elango) allowance' Claim for which the Data has been uploaded by the GEO HR." )
	public synchronized void TC_TYSS_GIM_ISB_006(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String rate,String noofInstalments,String remarks) throws InterruptedException {
		
		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_login"));

		String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

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

		/*Download Form Template*/
		String validDownloadDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_dropdown"));
		WebActionUtil.validateDownloadedDocument(validDownloadDocumentName);

		/* SQL query */
		String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,Rate,NoofInstalments,Remarks from Ikahyasafetybonus WHERE EmployeeCode='"
				+ employeeCode + "' and SlNo ='" + slNo + "'";

		/*Paste Your Content Here Text*/
		pages.initiateClaimPage.clkPasteYourContentHereText(prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"), prop_app_constants.getProperty("claimtype_ikahya_safety_bonus_sheetname"),strQuery,validDownloadDocumentName);

		/*Upload or Drag and Drop Your Files Here Link*/
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));

		/*click on Upload Button*/
		pages.initiateClaimPage.clkUploadButton();
		
		/*click on Yes Button*/
		pages.initiateClaimPage.clkYesButton();
		
		/*Select Initiate Claims from Dropdown*/
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));
	
		String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		 
		/*Validate list of Claims for Approval in Initiate Claims Page*/
    	pages.initiateClaimsPage.validateInitiateClaimsPageData();
		  
		/*click on particular Claim checkbox*/
		pages.initiateClaimsPage.clkchkClaim(claimId);
		
		/*click Cancel Button*/
		pages.initiateClaimsPage.clkCancel();

		/*Enter the remarks*/
		pages.initiateClaimsPage.setCancelClaimRemark(prop_app_constants.getProperty("rejectRemark"));
		
		/*Click on Submit Button*/
		pages.initiateClaimsPage.clkCancelSubmit();
	}
}