package com.hcl.gim.testscripts.transport_allowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;

/*TestCaseId : TYSS_GIM_0021
 *Claim Type : Transport Allowance
 *TestScript Name : TYSS_GIM_TA_0021
 *Description : Verify that User is able to Cancel the Transport Allowance claim for which the Data has been uploaded by the GEO HR
 *Author : Vikas
 * 
 * */
public class TYSS_GIM_TA_0021 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from TransportAllowance where SlNo ='21'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:  Verify that user is able to cancel the claim for which the Data has been uploaded by the GEO HR")
	
	public synchronized void TC_TYSS_GIM_TA_0021(String slNo, String employeeCode, String countryCode, String companyCode,  String amount, 
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

		/* Click on Through The Form Radio Button */
		pages.initiateClaimPage.clkClaimtypeRadioButton(prop_app_constants.getProperty("claimtyperadio_through_the_form"));
		
		/* Click on Through The Form Reset Button */
		pages.throughTheFormPage.filltheTransportAllowance(fromDate, toDate, employeeCode, countryCode, companyCode, amount, remarks, inputType,
				WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_png")));
		
		/* Click on Through The Form Submit Button */
		pages.throughTheFormPage.clkThroughFormSubmitButton();
		
		/* Message saying 'Are you sure you want to proceed' Should be displayed. */
		pages.throughTheFormPage.validatePopup();
		
		/* Click on Through The Form Yes Button */
		pages.throughTheFormPage.clkPopUpYesButton();
		
		/* Claim requests has been successfully raised. Pop up message should be displayed */
		pages.throughTheFormPage.validateClaimSuccessMessage();
		
		/* Click on Initiate Claims */
		pages.homePage.setHomeMenu(prop_app_constants.getProperty("menu_initiate_claims"));
		
		/* Verify list of claims */
		pages.initiateClaimsPage.validateInitiateClaimsPageData();
		
		/* Get Claim ID */
		String claimID = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
		
		/* Click on Approve Claim ID */
		pages.initiateClaimsPage.clkClaimID(claimID);
		
		/* Enter Remarks */
		pages.initiateClaimsPage.setApproveOrReferBackRemark(prop_app_constants.getProperty("rejectRemark"));
		
		/* Click on Cancel Button */
		pages.initiateClaimsPage.clkAppClaimsPopUpCancel();
		
	}
}
