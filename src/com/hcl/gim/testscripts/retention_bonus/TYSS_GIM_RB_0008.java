package com.hcl.gim.testscripts.retention_bonus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.baseutil.InitializePages;
import com.hcl.gim.dataprovider.DataProviderFactory;
import com.hcl.gim.dataprovider.DataProviderFileRowFilter;
/**
 *TestCaseId: TYSS_GIM__0008
 *Claim Type: RetentionBonus
 *TestScript Name: TYSS_GIM_RB_0008
 *Description: verify the status of the Geo HR for Retention Bonus after referred back by ES2
 *Author: Abhilash B
 */
public class TYSS_GIM_RB_0008 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GimTestData_OtherFlows.xlsx", sql = "Select * from RetentionBonus where SlNo ='8'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,description="Description:verify the status of  Retention Bonus claim after referred back by ES2(copypaste)")
	public void TC_TYSS_GIM_RB_0008(String slNo,String employeeCode,String countryCode,String companyCode,String amount,String fromDate,String toDate,String bonusperiod,String remarks) throws InterruptedException  
              {
		            Map<String, String[]> loginDetails = WebActionUtil.getLoginDetailsForAllRolesByClaimType(prop_app_constants.getProperty("claimtype_retention_bonus_login"));
		            String[] geoHrLoginDetails = loginDetails.get(prop_app_constants.getProperty("roleGEOHR"));
		            String[] es1LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES1"));
		            String[] es2LoginDetails = loginDetails.get(prop_app_constants.getProperty("roleES2"));
					Set<String> roles = loginDetails.keySet();
					List<String[]> lstHistoryExpectedData = new ArrayList<>();					
					InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);

					 /****** Geo HR Login *******/
					/* Enter user name and Password and login as Geo HR */
					pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);
					
					/* Close the HCL Banner Pop Up */
					pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
					
					/* Click on Initiate on behalf of the employee */
					pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
								
					/* Select the Retention Bonus claim type */
					pages.initiateClaimPage.clkSelectClaimtypeDropdown(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"),prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"),prop_app_constants.getProperty("roleGEOHR"));

					/* Download the Template */
					String downloadedDocumentName = pages.initiateClaimPage.clkDownloadFormTemplateLink(prop_app_constants.getProperty("claimtype_retention_bonus_dropdown"));	
					
					/*validates the download*/
					WebActionUtil.validateDownloadedDocument(downloadedDocumentName);
					
					String strQuery = "SELECT EmployeeCode,CountryCode,CompanyCode,Amount,FromDate,ToDate,BonusPeriod,Remarks from RetentionBonus WHERE EmployeeCode='"
							+ employeeCode + "' and SlNo ='" + slNo + "'";
					/* Copy Paste the content from excel sheet */
					pages.initiateClaimPage.clkPasteYourContentHereText(GIMTESTDATAOTHERFLOWEXCELPATH, prop_app_constants.getProperty("claimtype_retention_bonus_sheetname"), strQuery,downloadedDocumentName);

					/* Upload your  file */
					pages.initiateClaimPage.clkUploadorDragandDropYourFilesHereLink(WebActionUtil.getSampleFilePath(prop_app_constants.getProperty("fileFormat_docx")));

					/* Click on Upload Button */
					pages.initiateClaimPage.clkUploadButton();	
					
					/* Click on Yes Button */
					pages.initiateClaimPage.clkYesButton();
					
					/* Navigate to menu and click on Initiate Claims */
					pages.homePage.goToInitiateClaims();
					
					/*validates list of claims*/
					pages.initiateClaimsPage.validateInitiateClaimsPageData();
					
					/* Retrieve the claim ID */
					String claimId = pages.initiateClaimsPage.getClaimID(employeeCode, fromDate, toDate);
					lstHistoryExpectedData.add(new String[] { prop_app_constants.getProperty("historyStatus_claimnumber"), claimId });
					lstHistoryExpectedData.add(new String[] { prop_app_constants.getProperty("historyStatus_uploaded"), prop_app_constants.getProperty("roleGEOHR"), geoHrLoginDetails[0] });
					
					
					/*click on initiated claim check box for cancel*/
					pages.initiateClaimsPage.clkchkClaim(claimId);
					
					/*Click on submit button */
					pages.initiateClaimsPage.clkSubmit();
					
					/* Enter Remarks */
					pages.initiateClaimsPage.setApproveRemark(prop_app_constants.getProperty("approveRemark"));
					
					/*Click on SUBMIT button*/
					pages.initiateClaimsPage.clkApproveSubmit();
				
					/* Click on 'YES' Button. */
					pages.initiateClaimsPage.clkApproveYes(claimId);
					
					lstHistoryExpectedData.add(new String[] {"Approved", "GEO HR", geoHrLoginDetails[0]});
					
					/*logout from application*/
					pages.homePage.logout();
					
					/****** ES1 Login *******/

					/* Enter User ID and Password of ES1 OnSite and Click on Log In. */
					pages.loginpage.loginToApplication(es1LoginDetails[0], es1LoginDetails[1]);

					/* Click on 'X' Button. */
					pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES1"));
					
					/* Validate Home page */
					pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

					/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
					pages.pendingActionsPage.clkPendingforApprovalTab();
		            
					/* Validate Pending Action Page is displayed */
					pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"), prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

					/* Validate Pending Action data is displayed */
					pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES1OnSite"));
					
					/*Click on initiated claim check box*/
					pages.pendingActionsPage.clkPaticularCheckbox(claimId);
					
					/* Click on Submit in Actions */
					pages.pendingActionsPage.referApproveClaim(claimId);
					
					/* Enter Remarks and Click on SUBMIT Button. */
					pages.pendingActionsPage.clickOnSubmitButton();

					/* Click on YES. */
					pages.pendingActionsPage.clickOnYesButton();
						
					lstHistoryExpectedData.add(new String[] {prop_app_constants.getProperty("historyStatus_approved"), prop_app_constants.getProperty("roleES1"), es1LoginDetails[0]});
					
					
					/*
					 * 'Claim Requests have been successfully approved' Message should be Displayed
					 * and the Claim should be removed from the Pending Actions Page and Moved to
					 * the Next Level Approval.
					 */
					pages.pendingActionsPage.validateClaimID(claimId);
					
					/* Logout from the application */
					pages.homePage.logout();
					
					
					/****** ES2 Login *******/
					
					/* Enter user name and Password and login as ES2 */
					pages.loginpage.loginToApplication(es2LoginDetails[0], es2LoginDetails[1]);
				
					/* Click on 'X' Button. */
					pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleES2"));
					
					/* Validate Home page */
					pages.homePage.validateHomePage(prop_app_constants.getProperty("expectedHomePageTextES"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));

					/* Click on 'Pending for Approval' on the ES1 OnSite Card. */
					pages.pendingActionsPage.clkPendingforApprovalTab();
		            
					/* Validate Pending Action Page is displayed */
					pages.pendingActionsPage.validatePendingActionpage(prop_app_constants.getProperty("expectedPendingActionsPageText"), prop_app_constants.getProperty("expectedPendingActionsPageUrl"), prop_app_constants.getProperty("expectedPendingActionsPageTitle"));

					/* Validate Pending Action data is displayed */
					pages.pendingActionsPage.validatePendingActionsData(prop_app_constants.getProperty("roleES2OnSite"));
					
					/*Validate History Popup Details*/
					pages.previousRecordsPage.validateHistoryPopupDetail(claimId, roles, lstHistoryExpectedData);
				
					/*Click on View document icon of the claim */
					pages.pendingActionsPage.clickOnViewDocumentIcon(claimId, WebActionUtil.getSampleFileName(prop_app_constants.getProperty("fileFormat_docx")),prop_app_constants.getProperty("fileFormat_docx"));

					/*Click on initiated claim Check box*/
					pages.pendingActionsPage.clkPaticularCheckbox(claimId);
				
					/*Click on refer back button under actions Tab */
					pages.pendingActionsPage.referBackClaim(claimId);
				
					/*Enter Remark*/
					pages.pendingActionsPage.setReferBackRemarksTextAreafield(prop_app_constants.getProperty("rejectRemark"));
				
					/*Click on Submit button */
					pages.pendingActionsPage.clickOnSubmitReferBackButton();
					
					/*
					 * 'Claim Requests have been successfully referredback' Message should be Displayed
					 * and the Claim should be removed from the Pending Actions Page 
					 */
					pages.pendingActionsPage.validateClaimIDReferBack(claimId);
					
					
					/*logout from application*/
					pages.homePage.logout();
					
					
					/****** Geo HR Login *******/
					
					/* Enter user name and Password and login as Geo HR */
					pages.loginpage.loginToApplication(geoHrLoginDetails[0], geoHrLoginDetails[1]);
					
					/* Close the HCL Banner Pop Up */
					pages.homePage.closeHCLBannerPopUp(prop_app_constants.getProperty("roleGEOHR"));
					
					/* Click on Initiate on behalf of the employee */
					pages.homePage.clkGeoHRInitiateOnBehalfOfEmployee(prop_app_constants.getProperty("expectedHomePageText"),prop_app_constants.getProperty("expectedHomePageTitle"),prop_app_constants.getProperty("expectedHomeUrl"),prop_app_constants.getProperty("roleEmployee"));
					
					/*validate initiate claim page*/
					pages.initiateClaimPage.validateInitiateClaimPage(prop_app_constants.getProperty("expectedClaimPageText"),prop_app_constants.getProperty("expectedInitiateClaimUrl"),prop_app_constants.getProperty("expectedInitiateClaimPageTitle"));
					
					/* Click on Menu and Previous records */
					pages.homePage.goToPreviousRecords();
					

					/* Validate previous records Page displayed */
					pages.previousRecordsPage.validatePreviousRecordsPage(prop_app_constants.getProperty("expectedPreviousRecordsPageText"),prop_app_constants.getProperty("expectedPreviousRecordsPageUrl"),prop_app_constants.getProperty("expectedPreviousRecordsPageTitle"));
					
					/* Validate status of the claim */
					pages.previousRecordsPage.validateStatusRejectTxt(claimId, prop_app_constants.getProperty("expectedStatus_rejected_es_level2"));
					
					
				}

			}
