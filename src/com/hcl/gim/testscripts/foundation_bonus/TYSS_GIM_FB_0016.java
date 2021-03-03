package com.hcl.gim.testscripts.foundation_bonus;
import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*
 * TestCaseId:TYSS_GIM_0016
 * Claim Type:Foundation Bonus Geo allowance TestSCript
 * Name:TYSS_GIM_FB_0016 
 * Description:Verify that user is able to Reset the Data for 'Foundation Bonus Geo allowance 'Claim after valid Data and .jpeg
 * Extension is uploaded.
 * 
 * @author Suganthini
 *
 */
public class TYSS_GIM_FB_0016 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GIMTestData_OtherFlows.xlsx", sql = "Select * from FoundationBonusGeo where SlNo ='16'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description = "Description: Verify that user is able to reset the data for 'Foundation Bonus' allowance claim after valid data and .jpeg extension is uploaded in through the form option")
	public void TC_TYSS_GIM_FB_0016(String slNo,String employeeCode, String countryCode, String companyCode, String amount,
			String fromDate, String toDate, String paymentType, String remarks) throws InterruptedException {

		Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_login"));
		String[] geohrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

		/******* GEO HR Login *********/

		/* Enter user name and Password and login as GEO HR */
		pages.loginpage.loginToApplication(geohrLoginDetails[0], geohrLoginDetails[1]);

		/* Click on Close button in HCL Partner Manual Pop up */
		pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));

		/* Click on Initiate on Behalf of Employee link */
		pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),
				prop_app_constants.getProperty("expectedHomePageTitle"),
				prop_app_constants.getProperty("expectedHomeUrl"), prop_app_constants.getProperty("roleEmployee"));

		/* Select Claim Type Drop down */
		pages.initiateClaimPage.clkSelectClaimtypeDropdown(
				prop_app_constants.getProperty("claimtype_foundation_bonus_geo_dropdown"),
				prop_app_constants.getProperty("expectedClaimPageText"),
				prop_app_constants.getProperty("expectedInitiateClaimUrl"),
				prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),
				prop_app_constants.getProperty("roleGEOHR"));

		/* Through the form*/
		pages.initiateClaimPage
				.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));

		/* fill the form */
		pages.throughTheFormPage.fillTheFoundationBonusGeo(fromDate, toDate, employeeCode, countryCode, companyCode,
				amount, remarks, paymentType,
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_jpeg")));

		/* Click on submit button */
		pages.throughTheFormPage.clkThroughFormResetButton();

		/* Validate reset Foundation Bonus */
		pages.throughTheFormPage.validateResetFoundationBonusGeo();
	}
}
