package com.hcl.gim.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gim.util.WebActionUtil;

/* * Description: This class implements the methods for accessing elements of Home page 
					 * 
					 * @author Abhilash b
					 * 
					 */
public class Home_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Home_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Menu Drop Down */
	@FindBy(xpath = "//span[@class='icon-menu3 glb-pnl-localNav-ico']/parent::a")
	private WebElement ddMenu;

	/* Logout */
	@FindBy(xpath = "//span[text()='LOGOUT ']/parent::a")
	private WebElement btnLogout;

	/* Home */
	@FindBy(xpath = "//a[text()='Home']")
	private WebElement lnkHome;

	/* previous records */
	@FindBy(xpath = "//a[text()='Previous Records']")
	private WebElement lnkPreviousRecords;

	/* Iniatiate Claims */
	@FindBy(xpath = "//a[text()='Initaite Claims']")
	private WebElement lnkInitiateClaims;

	/* Profile Drop Down */
	@FindBy(xpath = "//a[@class='dropdown-toggle glb-pnl-userInf-box1-pic']")
	private WebElement ddProfile;

	/* Select Role */
	public WebElement selectRole(String var) {
		String xpath = "//a[text()='" + var + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Child Education Allowance */
	@FindBy(xpath = "//p[text()='Child Education Allowance']/parent::div")
	private WebElement btnChildEducationAllowance;

	/* New Request */
	@FindBy(xpath = "//button[contains(text(),'RAISE NEW')]")
	private WebElement btnRaiseNewRequest;

	/* Search Filter */
	@FindBy(xpath = "//a[@class='searhFilter']/i")
	private WebElement btnSearchFilter;

	/* Search Button */
	@FindBy(id = "//input[@id='searchbox']/parent::li")
	private WebElement btnSearch;

	/* Search Box */
	@FindBy(id = "searchbox")
	private WebElement txtSearchBox;

	/* Close Button of pop up */
	@FindBy(xpath = "//h4[text()='HR Partner Manual']/../button")
	private WebElement btnClosePopUp;

	/* Yes Button of Logout pop up */
	@FindBy(xpath = "//p[contains(text(),' logout')]/parent::div/descendant::button[text()='YES']")
	private WebElement btnYesLogout;

	/* Selects the Edit button of claim */
	public WebElement selectEditButton(String claimID) {
		String xpath = "//a[@id='viewClaim']/u[text()='" + claimID
				+ "']/../../..//i[@class='iRem-edit']/parent::button";
		return driver.findElement(By.xpath(xpath));
	}

	/* Selects the cancel button of claim */
	public WebElement selectCancelButton(String claimID) {
		String xpath = "//a[@id='viewClaim']/u[text()='" + claimID
				+ "']/../../..//i[@class='icon-close']/parent::button";
		return driver.findElement(By.xpath(xpath));
	}

	/* Selects the HIstory button of claim */
	public WebElement selectHistoryButton(String claimID) {
		String xpath = "//a[@id='viewClaim']/u[text()='" + claimID
				+ "']/../../..//i[@class='iRem-history']/parent::button";
		return driver.findElement(By.xpath(xpath));
	}

	/* Selects the Status of claim */
	public WebElement selectStatus(String claimID) {
		String xpath = "//a[@id='viewClaim']/u[text()='" + claimID + "']/../../..//span[@class='pendingText']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Selects the claim ID */
	public WebElement selectClaimID(String claimID) {
		String xpath = "//a[@id='viewClaim']/u[text()='" + claimID + "']/parent::a";
		return driver.findElement(By.xpath(xpath));
	}

	/* Home Menu */
	public WebElement clkHomeMenu(String homeMenuOption) {
		String xpath = "//a[contains(text(),'" + homeMenuOption + "')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* RatingValue */
	public WebElement clkRatingValue(String ratingValue) {
		String xpath = "//a[@data-rating-value='" + ratingValue + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* GeoHR Initiate on behalf of employee */
	@FindBy(xpath = "//div[contains(text(),'GEO HR')]/parent::div/descendant::p[text()='Initiate behalf of Employee']/parent::a")
	private WebElement btnGeoHRInitiateOnBehalfOfEmployee;

	/* C&B initiator Initiate on behalf of employee */
	@FindBy(xpath = "//div[contains(text(),'C&B Initiator')]/parent::div/descendant::p[text()='Initiate behalf of Employee']/parent::a")
	private WebElement btnCBinitiatorInitiateOnBehalfOfEmployee;

	/* LOB HR initiator Initiate on behalf of employee */
	@FindBy(xpath = "//div[contains(text(),'LOB HR')]/parent::div/descendant::p[text()='Initiate behalf of Employee']/parent::a")
	private WebElement btnLOBHRInitiateOnBehalfOfEmployee;

	/* page length */
	@FindBy(name = "myTable_length")
	private WebElement ddTableLength;

	/* pagination next */
	@FindBy(id = "myTable_next")
	private WebElement btnPaginationNext;

	/* pagination previous */
	@FindBy(id = "myTable_previous")
	private WebElement btnPaginationPrevious;

	/* HR Partner Manual */
	@FindBy(xpath = "//h4[text()='HR Partner Manual']")
	private WebElement txtHRPartnerManual;

	/* My claim text */
	@FindBy(xpath = "//label[text()='   My Claims  ']")
	private WebElement txtMyClaims;

	/* Select Input type */
	@FindBy(xpath = "//h4[text()='Select Input Type']")
	private WebElement txtSelectInputType;

	/* Log In text */
	@FindBy(xpath = "//h2[text()='Log in.']")
	private WebElement txtLogin;

	/* cancel confirmation text */
	@FindBy(xpath = "//p[contains(text(),'cancel')]/parent::div/descendant::button[text()='YES']")
	private WebElement btnCancelYes;

	/* popup Claim Number text */
	@FindBy(xpath = "//label[contains(text(),'Claim Number')]")
	private WebElement popupClaimNumber;

	/* popup History Detail */
	@FindBy(xpath = "//div[@class='historyDeatil']//li")
	private List<WebElement> popupHistoryDetails;

	/**
	 * Description:This Method implements to close the HCL Banner
	 * 
	 * @author Abhilash
	 * @param role
	 */
	public synchronized void closeHCLBannerPopUp(String role) {
		try {

			WebActionUtil.validateisElementDisplayed(btnClosePopUp, "Close button on HCL Banner",
					role + "-Log in is successfull", "Failed to Log in", "green");
			WebActionUtil.validatetext("HR Partner Manual", txtHRPartnerManual, "HR partner manual pop up",
					"HR Partner Manual popup is displayed", "HR Partner Manual popup is not displayed", "blue");
			WebActionUtil.clickOnElement(btnClosePopUp, "Close button on HCL Banner",
					"Unable to click on close button");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to close HCL pop up");
			Assert.fail("Unable to close HCL pop up");
		}
	}

	/**
	 * Description:This Method implements to change the role
	 * 
	 * @author Abhilash
	 * @param role
	 */
	public synchronized void changeRole(String role) {
		try {
			WebActionUtil.actionMouseOver(ddProfile, "profile");
			WebActionUtil.clickOnElement(selectRole(role), "Role Link", "Unable to click on" + role + "link");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.fail("Unable to select the Role");
			Assert.fail("Unable to select the Role");
		}

	}

	/**
	 * Description:This Method implements navigation to the home page
	 * 
	 * @author Abhilash
	 */
	public synchronized void goToHome() {
		try {
			WebActionUtil.actionMouseOver(ddMenu, "Menu");
			WebActionUtil.waitForElement(lnkHome, "Home link");
			WebActionUtil.clickOnElement(lnkHome, "Home link", "Unable to click on Home link");
			//WebActionUtil.waitForAngularPageToLoad();
			new WebDriverWait(driver, 7).until(ExpectedConditions.invisibilityOf(lnkHome));
		} catch (Exception e) {
			WebActionUtil.fail("Unable to go to the Home page");
			Assert.fail("Unable to go to the Home page");
		}

	}

	/**
	 * Description:This Method implements navigation to the Previous records page
	 * 
	 * @author Abhilash
	 */
	public synchronized void goToPreviousRecords() {
		try {
			
			WebActionUtil.actionMouseOver(ddMenu, "Menu");
			WebActionUtil.waitForElement(lnkPreviousRecords, "Previous Records link");
			WebActionUtil.validateisElementDisplayed(lnkHome, "Home link", "Home link is displayed",
					"Home link is not displayed", "blue");
			WebActionUtil.validateisElementDisplayed(lnkPreviousRecords, "Previous Records link",
					"Previous Records link is displayed", "Previous Records link is not displayed", "blue");
			WebActionUtil.clickOnElement(lnkPreviousRecords, "Previous Records link",
					"Unable to click on Prvious Records link");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.fail("Unable to go to the Previous Records page");
			Assert.fail("Unable to go to the Previous Records page");
		}

	}

	/**
	 * Description:This Method implements navigation to the Initiate claims page
	 * 
	 * @author Abhilash
	 */
	public synchronized void goToInitiateClaims() {
		try {
			WebActionUtil.actionMouseOver(ddMenu, "Menu");
			WebActionUtil.waitForElement(lnkInitiateClaims, "Initiate Claims link");
			WebActionUtil.clickOnElement(lnkInitiateClaims, "Initiate Claims link",
					"Unable to click on Initiate Claims link");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.fail("Unable to go to the Initiate claim page");
			Assert.fail("Unable to go to the Initiate claim page");
		}

	}

	/**
	 * Description:This Method implements to log out of the application
	 * 
	 * @author Abhilash
	 */
	public synchronized void logout() {
		try {
			WebActionUtil.actionMouseOver(ddProfile, "Profile");
			WebActionUtil.waitForElement(btnLogout, "Logout button");
			WebActionUtil.clickOnElement(btnLogout, "Logout button", "unable to click on logout button");
			new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(btnLogout));
			WebActionUtil.waitForElement(btnYesLogout, "Yes button");
			WebActionUtil.clickOnElement(btnYesLogout, "Yes button", "Unable to click on Yes button of logout pop up");
			WebActionUtil.waitForElement(txtLogin, "Log In");
			WebActionUtil.validatetext("Log in.", txtLogin, "Log In", "Logged out from the Account",
					"Unable to Log out from the Account", "green");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to Log out from the application");
			Assert.fail("Unable to Log out from the Application");
		}

	}

	/**
	 * Description:This Method implements to Raise New Request
	 * 
	 * @author Abhilash
	 */
	public synchronized void raiseNewRequest() {
		try {
			WebActionUtil.clickOnElement(btnRaiseNewRequest, "New Request button",
					"Unable to click on Raise New Request button");
			WebActionUtil.validateisElementDisplayed(txtSelectInputType, "Select Input Type",
					"Select Input Type pop up is displayed", "Select Input Type pop up is not displayed", "blue");
			WebActionUtil.clickOnElement(btnChildEducationAllowance, "Child Education Allowance button",
					"unable to click on Child Education Allowance button");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.fail("Unable to Raise new Request for Child Education Allowance");
			Assert.fail("Unableto Raise new Request for Child Education Allowance");

		}
	}

	/**
	 * Description:This Method searches the applied claims
	 * 
	 * @author Abhilash
	 * @param value
	 */
	public synchronized void searchClaims(String value) {
		try {
			WebActionUtil.clickOnElement(btnSearch, "Search Button", "Unable to click on Search Button");
			WebActionUtil.typeText(txtSearchBox, value, "Search Box");
			WebActionUtil.extentinfo(value + " entered in Search text box");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to Search claims");
			Assert.fail("Unable to Search claims");
		}

	}

	/**
	 * Description:This Method implements to provide the feedback
	 * 
	 * @author Vikas
	 * @param rating
	 */
	public synchronized void setFeedbackRating(String rating) {
		try {
			WebActionUtil.clickOnElement(clkRatingValue(rating), "Rating Button", "Unable to click on Rating Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to  Set the rating in the feedback");
			Assert.fail("Unable to  Set the rating in the feedback");
		}
	}

	/**
	 * Description: Method to navigate to menu drop down and click on option in drop
	 * down
	 * 
	 * @author Vikas
	 * @param homemenu
	 */
	public synchronized void setHomeMenu(String homemenu) {
		try {
			WebActionUtil.actionMouseOver(ddMenu, "Main Menu");
			WebActionUtil.clickOnElement(clkHomeMenu(homemenu), homemenu + " link",
					"Unable to click on " + homemenu + " link");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to  set the home link ");
			Assert.fail("Unable to  Set the rating in the feedback");
		}

	}

	/**
	 * Description:This Method implements to click on GeoHR Initiate On Behalf Of
	 * Employee
	 * 
	 * @author Abhilash
	 * 
	 * @param expectedHomePageText
	 * @param expectedTitle
	 * @param expectedUrl
	 * @param expectedLoginRole
	 * 
	 */
	public synchronized void clkGeoHRInitiateOnBehalfOfEmployee(String expectedHomePageText, String expectedTitle,
			String expectedUrl, String expectedLoginRole) {
		try {

			validateHomePage(expectedHomePageText, expectedTitle, expectedUrl, expectedLoginRole);
			WebActionUtil.clickOnElement(btnGeoHRInitiateOnBehalfOfEmployee,
					"Geo HR Initiate On Behalf Of Employee button",
					"Unable to click on Geo HR Initiate On Behalf Of Employee button");
			WebActionUtil.waitForAngularPageload();

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Geo HR Initiate On Behalf Of Employee Button");
			Assert.fail("Unable to click on Geo HR Initiate On Behalf Of Employee Button");
		}

	}

	/**
	 * Description:This Method implements to click on C&B initiator Initiate On
	 * Behalf Of Employee
	 * 
	 * @author Abhilash
	 * @param expectedHomePageText
	 * @param expectedTitle
	 * @param expectedUrl
	 * @param expectedLoginRole
	 */
	public synchronized void clkCBInitiatorInitiateOnBehalfOfEmployee(String expectedHomePageText, String expectedTitle,
			String expectedUrl, String expectedLoginRole) {
		try {
			validateHomePage(expectedHomePageText, expectedTitle, expectedUrl, expectedLoginRole);
			WebActionUtil.clickOnElement(btnCBinitiatorInitiateOnBehalfOfEmployee,
					"C&B initiator Initiate On Behalf Of Employee button ",
					"Unable to click on C&B initiator Initiate On Behalf Of Employee button");
			WebActionUtil.waitForAngularPageload();

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on C&B initiator Initiate On Behalf Of Employee button");
			Assert.fail("Unable to click on C&B initiator Initiate On Behalf Of Employee button");
		}

	}

	/**
	 * Description:This Method implements to click on LOB HR Initiate On Behalf Of
	 * Employee
	 * 
	 * @author Abhilash
	 * @param expectedHomePageText
	 * @param expectedTitle
	 * @param expectedUrl
	 * @param expectedLoginRole
	 * 
	 */
	public synchronized void clkLOBHRInitiateOnBehalfOfEmployee(String expectedHomePageText, String expectedTitle,
			String expectedUrl, String expectedLoginRole) {
		try {
			validateHomePage(expectedHomePageText, expectedTitle, expectedUrl, expectedLoginRole);
			WebActionUtil.clickOnElement(btnLOBHRInitiateOnBehalfOfEmployee,
					"LOB HR Initiate On Behalf Of Employee button",
					"Unable to click on LOB HR Initiate On Behalf Of Employee button");
			WebActionUtil.waitForAngularPageload();

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on LOB HR Initiate On Behalf Of Employee button");
			Assert.fail("Unable to click on LOB HR Initiate On Behalf Of Employee button");
		}

	}

	/**
	 * Description:This Method implements to click on edit claim
	 * 
	 * @author Abhilash
	 * @param claimID
	 */

	public synchronized void clickEditClaim(String claimID) {

		try {
			WebActionUtil.waitForElement(selectEditButton(claimID), "Edit button");
			WebActionUtil.clickOnElement(selectEditButton(claimID), "Edit button", "Unable to click on Edit button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Edit button");
			Assert.fail("Unable to click on Edit button");
		}

	}

	/**
	 * Description:This Method implements to click on cancel claim
	 * 
	 * @author Abhilash
	 * @param claimID
	 */
	public synchronized void clickCancelClaim(String claimID) {

		try {
			WebActionUtil.clickOnElement(selectCancelButton(claimID), "Cancel button",
					"Unable to click on Cancel button");
			WebActionUtil.clickOnElement(btnCancelYes, "Yes button", "Unable to click on Yes button of cancel pop up");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on cancel button");
			Assert.fail("Unable to click on cancel button");
		}
	}

	/**
	 * Description:This Method implements to click on claim History
	 * 
	 * @author Abhilash
	 * @param claimID
	 */
	public synchronized void clickClaimHistory(String claimID) {
		try {
			WebActionUtil.clickOnElement(selectHistoryButton(claimID), "History button",
					"Unable to click on History button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on History button");
			Assert.fail("Unable to click on History button");
		}

	}

	/**
	 * Description:This Method implements to view claim
	 * 
	 * @author Abhilash
	 * @param claimID
	 */
	public synchronized void clkviewClaim(String claimID) {
		try {
			WebActionUtil.clickOnElement(selectClaimID(claimID), "View Claim button",
					"Unable to click on View Claim button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on View Claim button");
			Assert.fail("Unable to click on View Claim button");
		}

	}

	/**
	 * Description:This Method implements to return claim ID
	 * 
	 * @author Abhilash
	 * 
	 */
	public synchronized String retrieveClaimId() {
		String claID = null;
		try {
			List<WebElement> claimIds = driver.findElements(By.xpath("//a[@id='viewClaim']/u"));
			ArrayList<Integer> claimIDsInt = new ArrayList<Integer>();
			for (WebElement claimID : claimIds) {
				claimIDsInt.add(Integer.parseInt(claimID.getText()));
			}
			Collections.sort(claimIDsInt, Collections.reverseOrder());
			claID = "" + claimIDsInt.get(0);
			WebActionUtil.info("Claim ID is retrieved");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to retrieve Claim ID");
			Assert.fail("Unable to retrieve Claim ID");
		}
		return claID;
	}

	/**
	 * Description:This Method implements to return Status
	 * 
	 * @author Abhilash
	 * @param claimID
	 */
	public synchronized String retrieveStatus(String claimID) {
		String status = null;
		try {
			status = WebActionUtil.getText(selectStatus(claimID), "Status ");
			WebActionUtil.info("Retrieved the status of " + claimID);
		} catch (Exception e) {
			WebActionUtil.fail("Failed to retrieve the status");
			Assert.fail("Failed to retrieve the status");
		}
		return status;
	}

	/**
	 * Description:This Method implements to return History Data
	 * 
	 * @author Abhilash
	 * @param claimID
	 * @param roles
	 */
	public synchronized List<String[]> fetchHistoryData(String claimID, Set<String> roles) {
		List<String[]> lstHistoryData = new ArrayList<>();

		WebActionUtil.clickOnElement(selectHistoryButton(claimID), "History Link", "Unable to click History link");

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(popupClaimNumber));
		String claimNumber = popupClaimNumber.getText();

		String[] claimNumberSplit = claimNumber.split(":");
		String claimkey = claimNumberSplit[0].trim().replaceAll("[(]", "");
		String claimValue = claimNumberSplit[1].trim().replaceAll("[)]", "");

		lstHistoryData.add(new String[] { claimkey, claimValue });

		// String[] roles =new String[] {"GEO HR","GEO HR RM"};

		Set<String> setUniqueWordsInRoles = uniqueWords(roles);

		List<WebElement> lstHistoryDetails = popupHistoryDetails;

		for (int i = lstHistoryDetails.size() - 1; i >= 0; i--) {

			String strDetail = lstHistoryDetails.get(i).getText();

			strDetail = strDetail.replaceAll("\\s+", " ");
			String[] strHistoryDetailsSplit = strDetail.split(" ");
			String status = strHistoryDetailsSplit[3];
			String role = strHistoryDetailsSplit[5];

			if (setUniqueWordsInRoles.contains(strHistoryDetailsSplit[6])) {
				role = role + " " + strHistoryDetailsSplit[6];
			}

			if (setUniqueWordsInRoles.contains(strHistoryDetailsSplit[7])) {
				role = role + " " + strHistoryDetailsSplit[7];
			}

			String empcode = strDetail.substring(strDetail.indexOf("(") + 1, strDetail.indexOf(")"));
			lstHistoryData.add(new String[] { status, role, empcode });

		}

		return lstHistoryData;
	}

	/**
	 * Description:This Method implements to return unique words
	 * 
	 * @author Abhilash
	 * @param keys
	 * @return unqwords
	 */
	private static Set<String> uniqueWords(Set<String> keys) {
		Set<String> unqwords = new LinkedHashSet<String>();

		for (String key : keys) {
			String[] words = key.split(" ");

			for (String word : words) {
				unqwords.add(word);
			}
		}

		return unqwords;
	}

	/**
	 * Description Method validate Home Page
	 * 
	 * @author Abhilash
	 * @param expectedHomePageText
	 * @param expectedTitle
	 * @param expectedUrl
	 * @param expectedLoginRole
	 */
	public synchronized void validateHomePage(String expectedHomePageText, String expectedTitle, String expectedUrl,
			String expectedLoginRole) {

		try {

			String actualHomePageText = driver.findElement(By.xpath("//p[text()='" + expectedHomePageText + "']"))
					.getText();
			String actualUrl = driver.getCurrentUrl();
			String actualTitle = driver.getTitle();
			String actualRole = driver.findElement(By.xpath("//span[@class='glb-pnl-showRole role']")).getText();

			if (actualHomePageText.equals(expectedHomePageText) && actualUrl.equals(expectedUrl)
					&& actualTitle.equals(expectedTitle)) {
				WebActionUtil
						.info("Home page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle);
				WebActionUtil.validationinfo(
						"Home page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle, "blue");
			} else {

				if (!expectedTitle.equals(actualTitle)) {
					WebActionUtil.validationinfo(
							"Title not matching, Expected= " + expectedTitle + ", Actual= " + actualTitle, "red");
					WebActionUtil.error("Title not matching, Expected= " + expectedTitle + ", Actual= " + actualTitle);
				}
				if (!expectedUrl.equals(actualUrl)) {
					WebActionUtil.validationinfo(
							"Url not matching, Expected= " + expectedUrl + ", Actual= " + actualUrl, "red");
					WebActionUtil.error("Url not matching, Expected= " + expectedUrl + ", Actual= " + actualUrl);
				}
				if (!expectedHomePageText.equals(actualHomePageText)) {
					WebActionUtil.validationinfo(
							"Title not matching, Expected= " + expectedHomePageText + ", Actual= " + actualHomePageText,
							"red");
					WebActionUtil.error("Title not matching, Expected= " + expectedHomePageText + ", Actual= "
							+ actualHomePageText);
				}
			}
			if (expectedLoginRole.equals(actualRole)) {
				WebActionUtil.info("Role: " + actualRole);
				WebActionUtil.validationinfo("Role: " + actualRole, "blue");
			} else {
				WebActionUtil.error("Role not matching, Exptected= " + expectedLoginRole + ", Actual= " + actualRole);
				WebActionUtil.validationinfo(
						"Role not matching, Exptected= " + expectedLoginRole + ", Actual= " + actualRole, "red");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("Unable to validate Home Page");
			Assert.fail("Unable to validate Home Page");
		}

	}

	/**
	 * Description Method to Validate Status Reject Text is displayed
	 * 
	 * @author Sajal jain
	 * @param claimID
	 * @param expectedStatus
	 */
	public synchronized void validateStatusRejectTxt(String claimID, String expectedStatus) {
		try {
			WebActionUtil.waitForAngularPageload();
			WebActionUtil.waitForElement(selectStatus(claimID), "Reject Status text");
			WebActionUtil.validatetext(expectedStatus, selectStatus(claimID), "Reject Status text",
					expectedStatus + " status is displayed", expectedStatus + " status is not displayed", "green");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail(expectedStatus + " is not displayed");
			Assert.fail(expectedStatus + " is not displayed");
		}
	}

	/**
	 * Description Method to Validate Status Approve Text is displayed
	 * 
	 * @author Sajal jain
	 * @param claimID
	 * @param expectedStatus
	 */
	public synchronized void validateStatusApproveTxt(String claimID, String expectedStatus) {
		try {
			WebActionUtil.waitForAngularPageload();
			WebActionUtil.waitForElement(selectStatus(claimID), "Approve Status text");
			WebActionUtil.validatetext(expectedStatus, selectStatus(claimID), "Approve Status text",
					expectedStatus + " status is displayed", expectedStatus + " status is not displayed", "green");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail(expectedStatus + " is not displayed");
			Assert.fail(expectedStatus + " is not displayed");
		}
	}

	/**
	 * Description Method validate My Claims Page
	 * 
	 * @author Abhilash
	 * @param expectedTitle
	 * @param expectedUrl
	 * @param expectedLoginRole
	 */
	public synchronized void validateMyClaimsPage(String expectedTitle, String expectedUrl, String expectedLoginRole) {
		try {
			String actualUrl = driver.getCurrentUrl();
			String actualTitle = driver.getTitle();
			String actualRole = driver.findElement(By.xpath("//span[@class='glb-pnl-showRole role']")).getText();

			if (btnRaiseNewRequest.isDisplayed() && actualUrl.equals(expectedUrl)
					&& actualTitle.equals(expectedTitle)) {
				WebActionUtil
						.info("My Claims page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle);
				WebActionUtil.validationinfo(
						"My Claims page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle,
						"blue");
			} else {

				if (!expectedTitle.equals(actualTitle)) {
					WebActionUtil.validationinfo(
							"Title not matching, Expected= " + expectedTitle + ", Actual= " + actualTitle, "red");
					WebActionUtil.error("Title not matching, Expected= " + expectedTitle + ", Actual= " + actualTitle);
				}
				if (!expectedUrl.equals(actualUrl)) {
					WebActionUtil.validationinfo("Url not matching, Expected= " + expectedUrl + ", Actual= " + actualUrl,
							"red");
					WebActionUtil.error("Url not matching, Expected= " + expectedUrl + ", Actual= " + actualUrl);
				}
				if (!btnRaiseNewRequest.isDisplayed()) {
					WebActionUtil.validationinfo("Raise New Request button is not displayed", "red");
					WebActionUtil.error("Raise New Request button is not displayed");
				}
			}
			if (expectedLoginRole.equals(actualRole)) {
				WebActionUtil.info("Role: " + actualRole);
				WebActionUtil.validationinfo("Role: " + actualRole, "blue");
			} else {
				WebActionUtil.error("Role not matching, Expected= " + expectedLoginRole + ", Actual= " + actualRole);
				WebActionUtil.validationinfo(
						"Role not matching, Expected= " + expectedLoginRole + ", Actual= " + actualRole, "red");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("Unable to validate My Claims Page");
			Assert.fail("Unable to validate My Claims Page");
		}

	}

	/**
	 * Description:This Method implements to close the HCL Banner
	 * 
	 * @author Abhilash
	 */
	public synchronized void closeHomeHCLBannerPopUp() {
		try {
			//WebActionUtil.waitForAngularPageToLoad();
			WebActionUtil.validatetext("HR Partner Manual", txtHRPartnerManual, "HR partner manual",
					"HR Partner Manual popup is displayed", "HR Partner Manual pop up is not displayed", "blue");
			WebActionUtil.waitForElement(btnClosePopUp, "Close button on HCL Banner");
			WebActionUtil.clickOnElement(btnClosePopUp, "Close button on HCL Banner",
					"Unable to Close button on HCL Banner pop up");
			new WebDriverWait(driver, 7).until(ExpectedConditions.invisibilityOf(btnClosePopUp));
		} catch (Exception e) {
			WebActionUtil.fail("Unable to Close button on HCL Banner pop up");
			Assert.fail("Unable to Close button on HCL Banner pop up");
		}
	}

}