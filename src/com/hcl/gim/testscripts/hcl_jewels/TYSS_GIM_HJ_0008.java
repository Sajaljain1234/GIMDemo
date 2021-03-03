package com.hcl.gim.testscripts.hcl_jewels;
import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId:TYSS_GIM_0008 
 * Claim Type:HCL Jewels TestSCript
 * Name:TYSS_GIM_HJ_0008 
 * Description:Verify that User is able to Clear the Data for 'HCL jewels allowance'
   Claim after valid Data and .msg Extension is uploaded.
 * 
 * @author suganthini
 *
 */
public class TYSS_GIM_HJ_0008 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from HCLJewels where EmployeeCode='40165690' and SlNo='8'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that user is able to clear the data for 'HCL Jewles' allowance claim after valid data and pdf extension is uploaded in copy & paste option")
	public synchronized void TC_TYSS_GIM_HJ_0008(String slNo,String employeeCode, String countryCode,
			String companyCode, String amount, String fromDate, String toDate, String payment, String remarks)
			throws InterruptedException {
		Map<String, String[]> loginDetails = WebActionUtil
				.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_hcl_jewels_login"));
		/* Multiple Employee */
		String[] employeeCodes =  prop_app_constants.getProperty("hj_empcode").split(",");
		String[] lobhrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleLOBHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/******* LOB HR Login*********/

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

		/* Click on Download form template link */
		String downloadedDocumentName = pages.initiateClaimPage
				.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_hcl_jewels_dropdown"));

		/* Validate Download template */
		WebActionUtil.validateDownloadedDocument(downloadedDocumentName);

		/*
		 * Copy the Data From the Excel file and Paste it in the Place saying Paste your
		 * content here
		 */
		pages.initiateClaimPage.clkPasteYourContentHereMultipleText(
				prop_app_constants.getProperty("GIMTESTDATAOTHERFLOWEXCELPATH"),
				prop_app_constants.getProperty("claimtype_hcl_jewels_sheetname"), employeeCodes,
				downloadedDocumentName);

		/* Upload your .msg file */
		pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_msg")));

		/* Click on Clear button */
		pages.initiateClaimPage.clkClearButton();

	}

}
