package com.hcl.gim.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

/**
 * Description: This class implements the methods for InitiateClaimPage
 * 
 * @author Manish
 * 
 */
public class InitiateClaim_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public InitiateClaim_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Select Claim type Drop down */
	@FindBy(xpath = "//a[@class='selecty-selected']")
	private WebElement ddSelectClaimtype;

	/* Claim type Value */
	public WebElement clkClaimtype(String claimtype) {
		String xpath = "//li[contains(text(),'" + claimtype + "')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Home Button */
	@FindBy(xpath = "//a[@class='iRem-Home icon']")
	private WebElement btnHome;

	/* Download Country Company info Link */
	@FindBy(xpath = "//div[@class='col-md-3 noPaddinLeft']/a[@data-original-title='Download Country Company Info']")
	private WebElement lnkDownloadCountryCompanyinfo;

	/* Menu Drop down */
	@FindBy(xpath = "//span[@class='icon-menu3 glb-pnl-localNav-ico']/parent::a")
	private WebElement ddMenu;

	/* Initiator Text */
	public WebElement clkInitiatorText(String variableinitiator) {
		String xpath = "//a[contains(text(),'" + variableinitiator + "')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Claim type value */
	public WebElement validateClaimType(String claimtype) {
		String xpath = "//a[@class='selecty-selected' and contains(text(),'" + claimtype + "')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Claim type value */
	public WebElement validateAreYouSureYouWantToProceed() {
		String xpath = "//p[contains(text(),'Are you sure you want to proceed?')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Download Form Template link */
	@FindBy(id = "divCopyPasteTemplate")
	private WebElement lnkDownloadFormTemplate;

	/* Upload template text */
	@FindBy(xpath = "//div[@class='uploadLarge mtop']")
	private WebElement txtUploadTemplate;

	/* Paste Your Content Here Text */
	@FindBy(xpath = "//p/strong[text()='Paste your content here']")
	private WebElement txtPasteYourContentHere;

	/* Upload Document Link */
	@FindBy(xpath = "//div[@class='dropzone dropzonePop blueBorder dz-clickable']/div[@class='dz-default dz-message']")
	private WebElement lnkUploadorDragandDropYourFilesHere;

	/* Clear Button */
	@FindBy(id = "btnCopypsteClear")
	private WebElement btnClear;

	/* Back Button */
	@FindBy(xpath = "//input[@id='ClipboardContent']/following-sibling::span/button[text()='BACK']")
	private WebElement btnBack;

	/* Upload Button */
	@FindBy(id = "btnVerifyAndSubmit")
	private WebElement btnUpload;

	/* Sample File Name Text */
	@FindBy(xpath = "(//div[@class='dz-filename']/span)[2]")
	private WebElement txtSampleFileNameExcelUpload;

	/* Sample File Name Text */
	@FindBy(xpath = "//div[@class='dz-filename']/span")
	private WebElement txtSampleFileNameCopyPaste;

	/* Download Form Template link */
	@FindBy(id = "divExcelTemplate")
	private WebElement lnkExcelDownloadFormTemplate;

	/* Claim type Radio button */
	public WebElement clkClaimtypeRadio(String variableclaimtyperadio) {
		String xpath = "//li[@role='presentation']/a[text()='" + variableclaimtyperadio + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Upload Template Link */
	@FindBy(xpath = "//p/strong[text()='Upload Template']")
	private WebElement lnkExcelUploadTemplate;

	/* Excel Upload Document Link */
	@FindBy(xpath = "//div[@id='ExcelUpload']/descendant::i[@class='icon-upload']")
	private WebElement lnkExcelUploadorDragandDropyourfileshere;

	/* Excel Back Button */
	@FindBy(xpath = "//div[@id='divExcelAttachAlert']/following-sibling::span/button[text()='BACK']")
	private WebElement btnExcelBack;

	/* Excel Upload Button */
	@FindBy(id = "btnUploadAndVerifyExcel")
	private WebElement btnExcelUpload;

	/* Excel Clear Button */
	@FindBy(id = "btnExcelClear")
	private WebElement btnExcelClear;

	/* Yes Button */
	@FindBy(xpath = "//button[@class='confirm btn btn-lg btn primary-button' and contains(text(),'YES')]")
	private WebElement btnYes;

	/* No Button */
	@FindBy(xpath = "//button[@class='cancel btn btn-lg btn secondry-button'  and contains(text(),'NO')]")
	private WebElement btnNo;

	/* Please Paste Records Text */
	@FindBy(xpath = "//p[contains(text(),'Please paste records.')]")
	private WebElement txtPleasePasteRecords;

	/* OK Button */
	@FindBy(xpath = "//button[@class='confirm btn btn-lg btn primary-button']")
	private WebElement btnOk;

	/* Please Select AtLeast One Attachment Text */
	@FindBy(xpath = "//p[contains(text(),'Please select at least one attachment.')]")
	private WebElement txtPleaseSelectAtLeastOneAttachment;

	/* Upload FileName */
	@FindBy(xpath = "//div[@class='dz-filename']/span")
	private WebElement txtFileName;

	/* Length Drop down */
	@FindBy(xpath = "//select[@name='tblCopyPaste_length']")
	private WebElement ddLength;

	/* Data Has Been Saved Successfully Toast Text */
	@FindBy(xpath = "//div[text()='Data has been saved successfully.']")
	private WebElement txtDataHasBeenSavedSuccessfully;

	/* Paste Your Content Here text field */
	@FindBy(xpath = "//span[@class='iconUpload marTP10']")
	private WebElement textPasteYourContentHere;

	/* Initiate Claim Text */
	@FindBy(xpath = "//li[contains(text(),'Initiate Claim')]")
	private WebElement txtInitiateClaim;

	/* Spinner */
	@FindBy(xpath = "//div[contains(@class,'spinner')]")
	private WebElement spinner;
	
	/**
	 * Description Method clicks on Select Claim type Drop down
	 * 
	 * @author Manish Kumar C D
	 * @param claimtype
	 * @param expectedClaimPageText
	 * @param exptecedUrl
	 * @param exptectedTitle
	 * @param role
	 * 
	 */
	public synchronized void clkSelectClaimtypeDropdown(String claimtype, String expectedClaimPageText,
			String exptecedUrl, String exptectedTitle, String role) {
		try {
			String[] arrExpectedClaims = null;
			if (role.equals("GEO HR")) {
				arrExpectedClaims = new String[] { "Absence Hours GEO", "Car parking Deduction", "Deferred Bonus",
						"Dental Insurance", "Exgratia - Accidental Insurance", "Exgratia - Christmas Bonus in Dec",
						"Exgratia - IWP Payouts", "Exgratia - Language allowance", "Exgratia - Maternity allowance",
						"Exgratia - others", "Exgratia-401 K allowance", "Foundation Bonus Geo", "Health Insurance",
						"Ikahya safety bonus( client-seriti/elango)", "Meal Voucher", "Notice pay recovery",
						"Profit Sharing GEO", "Redundancy/Severance Payment", "Retention Bonus (Offer letter based)",
						"Transport Allowance", "Union Fee Deduction" };

			} else if (role.equals("C&B Initiator")) {
				arrExpectedClaims = new String[] { "Exgratia - LCA Arrears", "Exgratia - Salary Arrear" };

			} else if (role.equals("LOB HR")) {
				arrExpectedClaims = new String[] { "Advocacy Award", "Client Bonus/Reward/Incentive",
						"Exgratia - Spot Award", "Exgratia-Long service Award", "HCL Jewels", "Patent Award",
						"RSU Payment(Restricted Stock Units)" };
			}

			ArrayList<String> lstExpectedClaims = new ArrayList<>(Arrays.asList(arrExpectedClaims));
			ArrayList<String> lstActualClaims = new ArrayList<>();

			validateInitiateClaimPage(expectedClaimPageText, exptecedUrl, exptectedTitle);
			WebActionUtil.waitForElement(ddSelectClaimtype, "Select Claim type Drop down");
			WebActionUtil.clickOnElement(ddSelectClaimtype, "Select Claim type Drop down",
					"Unable to click on Select Claim type Drop down");
		
			List<WebElement> lstClaims = WebActionUtil.driver
					.findElements(By.xpath("//ul[@class='selecty-options active']/li"));
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElements(lstClaims));
			for (WebElement claims : lstClaims) {
				String txtclaims = claims.getText();
				lstActualClaims.add(txtclaims);
				// WebActionUtil.info(txtclaims);
			}
			if (lstActualClaims.containsAll(lstExpectedClaims)) {
				WebActionUtil.pass("List of all Claim types from the Drop down is displayed.");
				WebActionUtil.info("List of all Claim types from the Drop down is displayed.");
			} else {
				WebActionUtil.fail("List of all Claim types from the Drop down is not displayed.");
				WebActionUtil.error("List of all Claim types from the Drop down is not displayed.");
			}
			WebActionUtil.waitForElement(clkClaimtype(claimtype), "" + claimtype + "");
			WebActionUtil.actionMouseOver(clkClaimtype(claimtype), " " + claimtype + "");
			WebActionUtil.actionClick(clkClaimtype(claimtype), "" + claimtype + "");
			String defaultRadioText = WebActionUtil.driver
					.findElement(By.xpath("//li[@role='presentation' and contains(@class,'active')]/a ")).getText();
			boolean radioCpyPaste = false;

			if (defaultRadioText.equals("Copy & Paste")) {
				WebActionUtil.info("Able to select " + claimtype + " from the Dropdown and default selection is "
						+ defaultRadioText + " ");
				radioCpyPaste = true;

			}
			validateSelectedClaimType(claimtype, radioCpyPaste);
			WebActionUtil.scrollUp();
			if (spinner.isDisplayed()) {
				new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Select Claim type Drop down");
			Assert.fail("Unable to click on Select Claim type Drop down");
		}
	}

	/**
	 * Description Method clicks on Home Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void clkHomeButton() {
		try {
			WebActionUtil.waitForElement(btnHome, "Home button");
			WebActionUtil.clickOnElement(btnHome, "Home button", "Unable to click on Home button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Home button");
			Assert.fail("Unable to click on Home button");
		}
	}

	/**
	 * Description Method clicks on Upload Template Text
	 * 
	 * @author Manish Kumar C D
	 * @param imagePath
	 */

	public synchronized void clkUploadTemplateText(String imagePath) {
		try {
			WebActionUtil.waitForElement(txtUploadTemplate, "Upload Template text");
			WebActionUtil.clickOnElement(txtUploadTemplate, "Upload Template text",
					"Unable to click on upload template text");
			WebActionUtil.upload(imagePath);
			WebActionUtil.pass(imagePath + " document uploaded successfully");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Upload Template Text");
			Assert.fail("Unable to click on Upload Template Text");
		}
	}

	/**
	 * Description Method clicks on Download Form Template Link
	 * 
	 * @author Manish Kumar C D
	 * @param claimtype
	 * 
	 */
	public synchronized String clkDownloadFormTemplateLink(String claimtype) {
		String downloadedDocumentName = null;
		try {
			WebActionUtil.waitForElement(lnkDownloadFormTemplate, "Download " + claimtype + " Form Template link");
			WebActionUtil.clickOnElement(lnkDownloadFormTemplate, "Download " + claimtype + " Form Template link",
					"Unable to click on download form template link of claimtype " + claimtype);
			String userDir = System.getProperty("user.home");
			downloadedDocumentName = WebActionUtil.getDownloadedDocumentName(userDir + "/Downloads", ".xlsx");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on download form template link of claimtype " + claimtype);
			Assert.fail("Unable to click on download form template link of claimtype" + claimtype);
		}
		return downloadedDocumentName;
	}

	/**
	 * Description Method clicks on Download Country Company Info Link
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void clkDownloadCountryCompanyInfoLink() {
		try {
			WebActionUtil.waitForElement(lnkDownloadCountryCompanyinfo, "Download Country Company Info Link");
			WebActionUtil.clickOnElement(lnkDownloadCountryCompanyinfo, "Download Country Company Info Link",
					"Unable to click on Download Country Company Info Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Download Form Template Link");
			Assert.fail("Unable to click on Download Form Template Link");
		}
	}

	/**
	 * Description Method clicks on Paste Your Content Here Text
	 * 
	 * @author Manish Kumar C D
	 * @param claimTypeExcelFilepath
	 * @param dataSheetName
	 * @param strQuery
	 * @param downloadedDocumentName
	 */
	public synchronized void clkPasteYourContentHereText(String claimTypeExcelFilepath, String dataSheetName,
			String strQuery, String downloadedDocumentName) {
		try {
			WebActionUtil.copyPaste(txtPasteYourContentHere, "Paste Your Content Here Text", claimTypeExcelFilepath,
					dataSheetName, strQuery, downloadedDocumentName);
			WebActionUtil.pass("Data is displayed according to Template format");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Paste Your Content Here Text");
			Assert.fail("Unable to click on Paste Your Content Here Text");
		}
	}

	/**
	 * Description Method clicks on Upload Document Link
	 * 
	 * @author Manish Kumar C D
	 * @param imagePath
	 */

	public synchronized void clkUploadorDragandDropYourFilesHereLink(String imagePath) {
		try {
			WebActionUtil.waitForElement(lnkUploadorDragandDropYourFilesHere, "Upload Document Link");
			WebActionUtil.clickOnElement(lnkUploadorDragandDropYourFilesHere, "Upload Document Link",
					"Unable to click on Upload Document Link");
			WebActionUtil.upload(imagePath);
			String filename = txtSampleFileNameCopyPaste.getText();
			WebActionUtil.validatetext(filename, txtSampleFileNameCopyPaste, "File name",
					filename + " file is uploaded", filename + " file is not uploaded", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Upload Document Link");
			Assert.fail("Unable to click on Upload Document Link");
		}
	}

	/**
	 * Description Method clicks on Clear Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void clkClearButton() {
		try {
			WebActionUtil.waitForElement(btnClear, "Clear Button");
			WebActionUtil.clickOnElement(btnClear, "Clear Button", "Unable to click on Clear Button");
			WebActionUtil.waitForAngularPageload();
			WebActionUtil.validateisElementDisplayed(lnkDownloadFormTemplate, "Download Form Template",
					"Download Form Template is reset", "Download Form Template is not reset", "blue");
			WebActionUtil.validateisElementDisplayed(txtPasteYourContentHere, "Paste Your Content Here",
					"Paste Your Content Here is reset", "Paste Your Content Here is not reset", "blue");
			WebActionUtil.validateisElementDisplayed(lnkUploadorDragandDropYourFilesHere,
					"Upload or Drag and Drop Your Files Here", "Upload or Drag and Drop Your Files Here is reset",
					"Upload or Drag and Drop Your Files Here is not reset", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Clear Button");
			Assert.fail("Unable to click on Clear Button");
		}
	}

	/**
	 * Description Method clicks on Back Button
	 * 
	 * @author Manish Kumar C D
	 * @param expectedClaimPageText
	 * @param exptecedUrl
	 * @param exptectedTitle
	 */
	public synchronized void clkBackButton(String expectedClaimPageText, String exptecedUrl, String exptectedTitle) {
		try {
			WebActionUtil.waitForElement(btnBack, "Back button");
			WebActionUtil.clickOnElement(btnBack, "Back button", "Unable to click on Back button");
			validateInitiateClaimPage(expectedClaimPageText, exptecedUrl, exptectedTitle);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Back Button");
			Assert.fail("Unable to click on Back Button");
		}
	}

	/**
	 * Description Method clicks on Upload Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void clkUploadButton() {
		try {
			WebActionUtil.waitForElement(btnUpload, "Upload Button");
			WebActionUtil.clickOnElement(btnUpload, "Upload Button", "Unable to click on Upload Button");
			try {
				new WebDriverWait(driver, 4).until(ExpectedConditions.invisibilityOf(btnUpload));
			} catch (Exception e) {

			}
			} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Upload Button");
			Assert.fail("Unable to click on Upload Button");
		}
	}

	/**
	 * Description Method clicks on Excel Upload Template Link
	 * 
	 * @author Manish Kumar C D
	 * @param data
	 * @param downloadedDocumentName
	 */

	public synchronized void clkExcelUploadTemplateLink(Map<String, String> data, String downloadedDocumentName) {
		try {
			WebActionUtil.writeAndUploadExcel(lnkExcelUploadTemplate, "Upload Template Link", data,
					downloadedDocumentName);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Upload Template Link");
			Assert.fail("Unable to click on Upload Template Link");
		}
	}

	/**
	 * Description Method clicks on Excel Upload or Drag and Drop your files here
	 * Link
	 * 
	 * @author Manish Kumar C D
	 * @param imagePath
	 */

	public synchronized void clkExcelUploadorDragandDropyourfileshereLink(String imagePath) {
		try {
			WebActionUtil.waitForElement(lnkExcelUploadorDragandDropyourfileshere, "Excel Upload Document Link");
			WebActionUtil.clickOnElement(lnkExcelUploadorDragandDropyourfileshere, "Excel Upload Document Link",
					"Unable to click on Excel Upload Document Link");
			WebActionUtil.upload(imagePath);
			String filename = txtSampleFileNameExcelUpload.getText();
			WebActionUtil.validatetext(filename, txtSampleFileNameExcelUpload, "File name",
					filename + " file is uploaded", filename + " file is not uploaded", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Excel Upload Document Link");
			Assert.fail("Unable to click on Excel Upload Document Link");
		}
	}

	/**
	 * Description Method clicks on Excel Download Form Template Link
	 * 
	 * @author Manish Kumar C D
	 * @param claimtype
	 * @return downloadedDocumentName
	 */

	public synchronized String clkExcelDownloadFormTemplateLink(String claimtype) {
		String downloadedDocumentName = null;
		try {
			WebActionUtil.waitForElement(lnkExcelDownloadFormTemplate, "Download Form Template Link");
			WebActionUtil.clickOnElement(lnkExcelDownloadFormTemplate, "Download Form Template Link",
					"Unable to click on Download Form Template Link of claimtype " + claimtype + " ");
			String userDir = System.getProperty("user.home");
			downloadedDocumentName = WebActionUtil.getDownloadedDocumentName(userDir + "/Downloads", ".xlsx");
			} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Download Form Template Link");
			Assert.fail("Unable to click on Download Form Template Link");
		}
		return downloadedDocumentName;
	}

	/**
	 * Description Method clicks on Excel Back Button
	 * 
	 * @author Manish Kumar C D
	 * @param expectedClaimPageText
	 * @param exptecedUrl
	 * @param exptectedTitle
	 */
	public synchronized void clkExcelBackButton(String expectedClaimPageText, String exptecedUrl,
			String exptectedTitle) {
		try {
			WebActionUtil.waitForElement(btnExcelBack, "Excel Back button");
			WebActionUtil.clickOnElement(btnExcelBack, "Excel Back button", "Unable to click on Excel Back button");
			WebActionUtil.waitForAngularPageload();
			validateInitiateClaimPage(expectedClaimPageText, exptecedUrl, exptectedTitle);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Excel Back Button");
			Assert.fail("Unable to click on Excel Back Button");
		}
	}

	/**
	 * Description Method clicks on Excel Clear Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */
	public synchronized void clkExcelClearButton() {
		try {
			WebActionUtil.waitForElement(btnExcelClear, "Excel Clear Button");
			WebActionUtil.clickOnElement(btnExcelClear, "Excel Clear Button", "Unable to click on Excel Clear Button");
			WebActionUtil.validateisElementDisplayed(lnkExcelUploadTemplate, "UploadTemplate",
					"Upload Template is reset", "Upload Template is not reset", "blue");
			WebActionUtil.validateisElementDisplayed(lnkExcelUploadorDragandDropyourfileshere,
					"Upload or Drag and Drop Your Files Here", "Upload or Drag and Drop Your Files Here is reset",
					"Upload or Drag and Drop Your Files Here is not reset", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Excel Clear Button");
			Assert.fail("Unable to click on Excel Clear Button");
		}
	}

	/**
	 * Description Method clicks on Excel Upload Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */
	public synchronized void clkExcelUploadButton() {
		try {
			WebActionUtil.waitForElement(btnExcelUpload, "Excel Upload Button");
			WebActionUtil.clickOnElement(btnExcelUpload, "Excel Upload Button",
					"Unable to click on Excel Upload Button");
			try {
				new WebDriverWait(driver, 6).until(ExpectedConditions.invisibilityOf(btnExcelUpload));
			} catch (Exception e) {

			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Excel Upload Button");
			Assert.fail("Unable to click on Excel Upload Button");
		}
	}

	/**
	 * Description Method clicks on Claim type Radio Button
	 * 
	 * @author Manish Kumar C D
	 * @param strclaimtyperadio
	 */
	public synchronized void clkClaimtypeRadioButton(String strclaimtyperadio) {
		try {
			WebActionUtil.scrollUp();
			if (spinner.isDisplayed()) {
				new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
			}
			WebActionUtil.waitForElement(clkClaimtypeRadio(strclaimtyperadio),
					strclaimtyperadio + " Radio Button");
			WebActionUtil.clickOnElement(clkClaimtypeRadio(strclaimtyperadio), strclaimtyperadio+" Radio Button",
					"Unable to click on " + strclaimtyperadio + " Radio Button");
			WebActionUtil.validateisElementDisplayed(clkClaimtypeRadio(strclaimtyperadio), strclaimtyperadio+" Radio Button",
					strclaimtyperadio + " option is selected", strclaimtyperadio + " option is not selected",
					"blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on " + strclaimtyperadio + " Radio Button");
			Assert.fail("Unable to click on " + strclaimtyperadio + " Radio Button");
		}
	}

	/**
	 * Description Method clicks on Yes Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */
	public synchronized void clkYesButton() {
		try {
			validateAreYouSureYouWantToProccedDialogBox();
			WebActionUtil.waitForElement(btnYes, "Yes Button");
			WebActionUtil.poll(2000);
			WebActionUtil.clickOnElement(btnYes, "Yes Button", "Unable to click on Yes Button");
			validateDataHasBeenSavedSuccessfully();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description Method clicks on No Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */
	public synchronized void clkNoButton() {
		try {
			WebActionUtil.waitForElement(btnNo, "No button");
			WebActionUtil.clickOnElement(btnNo, "No button", "Unable to click on No button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No button");
			Assert.fail("Unable to click on No button");
		}
	}

	/**
	 * Description Method validate Initiate Claim Page
	 * 
	 * @author Manish Kumar C D
	 * @param expectedClaimPageText
	 * @param exptecedUrl
	 * @param exptectedTitle
	 */

	public synchronized void validateInitiateClaimPage(String expectedClaimPageText, String exptecedUrl,
			String exptectedTitle) {
		try {
			String actualClaimPageText = driver
					.findElement(By.xpath("//div[@class='pull-left topHeader']/descendant::li[2]")).getText();
			String actualUrl = driver.getCurrentUrl();
			String actualTitle = driver.getTitle();
			if (actualClaimPageText.equals(expectedClaimPageText) && actualUrl.equals(exptecedUrl)
					&& actualTitle.equals(exptectedTitle)) {
				WebActionUtil.info(
						"Initiate Claim page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle);
				WebActionUtil.validationinfo(
						"Initiate Claim page is displayed, url= " + driver.getCurrentUrl() + ", title= " + actualTitle,
						"blue");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Initiate Claim page");
			Assert.fail("Unable to validate Initiate Claim page");
		}
	}

	/**
	 * Description Method validates Selected ClaimType
	 * 
	 * @author Manish Kumar C D
	 * @param claimtype
	 * @param radioCpyPaste
	 */
	public synchronized void validateSelectedClaimType(String claimtype, boolean radioCpyPaste) {
		try {
			if (radioCpyPaste)
				WebActionUtil.validatetext(claimtype, validateClaimType(claimtype), "Select Claim Type Text",
						claimtype + " is selected and default selection is Copy & Paste",
						claimtype + " is not selected", "blue");
			else
				WebActionUtil.validatetext(claimtype, validateClaimType(claimtype), "Select Claim Type Text",
						claimtype + " is selected and default selection is not Copy & Paste",
						claimtype + " is not selected", "blue");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Select Claim Type Text");
			Assert.fail("Unable to validate Select Claim Type Text");
		}
	}

	/**
	 * Description Method validate Are You Sure You Want To Procced Dialog Box
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void validateAreYouSureYouWantToProccedDialogBox() {
		try {

			WebActionUtil.validatetext("Are you sure you want to proceed?", validateAreYouSureYouWantToProceed(),
					"Are you sure you want to proceed Dialog box",
					"Are you sure you want to proceed Dialog box is displayed",
					"Are you sure you want to proceed Dialog box is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Are you sure you want to proceed Dialog box");
			Assert.fail("Unable to validate Are you sure you want to proceed Dialog box");
		}
	}

	/**
	 * Description Method validate Please Paste Records Pop up
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void validatePleasePasteRecordsPopup() {
		try {
			WebActionUtil.validatetext("Please paste records.", txtPleasePasteRecords, "Please Paste Records Text",
					"Please Paste Records Text is displayed", "Please Paste Records Text is not displayed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Please Paste Records Text");
			Assert.fail("Unable to  validate Please Paste Records Text");
		}
	}

	/**
	 * Description Method clicks on OK Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void clkOkButton() {
		try {
			WebActionUtil.waitForElement(btnOk, "Ok button");
			WebActionUtil.clickOnElement(btnOk, "Ok button", "Unable to click on Ok button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Ok button");
			Assert.fail("Unable to click on Ok button");
		}
	}

	/**
	 * Description Method validate Please Select AtLeast One Attachment Pop up
	 * 
	 * @author Manish Kumar C D
	 */
	public synchronized void validatePleaseSelectAtLeastOneAttachmentPopup() {
		try {
			WebActionUtil.validatetext("Please select at least one attachment.", txtPleaseSelectAtLeastOneAttachment,
					"Please Paste Records Text", "Please select at least one attachment message is displayed",
					"Please select at least one attachment message is not displayed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Please Select At Least One Attachment Text");
			Assert.fail("Unable to  validate Please Select At Least One Attachment Text");
		}
	}

	/**
	 * Description Method Select the Length of data to be displayed
	 * 
	 * @author Manish Kumar C D
	 * @param length
	 */

	public synchronized void SelectLengthDropDown(int length) {
		try {
			WebActionUtil.waitForElement(ddLength, "Length Dropdown");
			Select s = new Select(ddLength);
			s.selectByIndex(length);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select the Length from the Drop down");
			Assert.fail("Unable to Select the Length from the Drop down");
		}
	}

	/**
	 * Description Method validate Please Paste Records Text
	 * 
	 * @author Manish Kumar C D
	 * 
	 */

	public synchronized void validatePleasePasteRecordsText() {
		try {
			WebActionUtil.validatetext("Please paste records.", txtPleasePasteRecords, "Please Paste Records Text",
					"Please Paste Records message is displayed", "Please Paste Records message is not displayed",
					"green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Please Paste Records Text");
			Assert.fail("Unable to validate Please Paste Records Text");
		}
	}

	/**
	 * Description Method validate Data Has Been Saved Successfully
	 * 
	 * @author Manish Kumar C D
	 */
	public synchronized void validateDataHasBeenSavedSuccessfully() {
		try {

			WebActionUtil.validatetext("Data has been saved successfully.", txtDataHasBeenSavedSuccessfully,
					"Data has been saved successfully Text", "Data has been saved successfully message is displayed",
					"Data has been saved successfully message is not displayed", "green");
			if (spinner.isDisplayed()) {
				new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(spinner));
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Data has been saved successfully Text");
			Assert.fail("Unable to validate Data has been saved successfully Text");
		}
	}

	/**
	 * Description Method writes the data into Paste Your Content Here Text field
	 * 
	 * @author Manish Kumar C D
	 * @param data
	 */

	public synchronized void setPasteYourContentHereText(String data) {
		try {
			WebActionUtil.waitForElement(textPasteYourContentHere, "Paste Your Content Here Text field");
			WebActionUtil.typeText(textPasteYourContentHere, data, "Paste Your Content Here Text field");
			WebActionUtil.extentinfo(data+" entered in Paste Your Content Here Text field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to type into Paste Your Content Here Text field");
			Assert.fail("Unable to type into Paste Your ContentHere Textfield");
		}
	}

	/**
	 * Description Method clicks on Paste multiple Content Here Text
	 * 
	 * @author Manish Kumar C D
	 * @param claimTypeExcelFilepath
	 * @param downloadedDocumentName
	 * @param dataSheetName
	 * @param empCodes
	 */
	public synchronized void clkPasteYourContentHereMultipleText(String claimTypeExcelFilepath, String dataSheetName,
			String[] empCodes, String downloadedDocumentName) {
		try {
			WebActionUtil.copyPastemultiplerows(txtPasteYourContentHere, "Paste Your Content Here Text",
					claimTypeExcelFilepath, dataSheetName, empCodes, downloadedDocumentName);
			WebActionUtil.pass("Data is displayed according to Template format");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Paste Your Content Here Text");
			Assert.fail("Unable to click on Paste Your Content Here Text");
		}
	}

}