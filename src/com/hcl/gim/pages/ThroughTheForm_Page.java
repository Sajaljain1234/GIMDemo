package com.hcl.gim.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gim.util.WebActionUtil;

/**
 * Description: This class implements the methods for ThroughTheForm
 * 
 * @author Shreya U
 * 
 */
public class ThroughTheForm_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public ThroughTheForm_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* From Date Text Box */
	@FindBy(id = "dtFromDate")
	private WebElement txtFromdate;

	/* To Date Text Box */
	@FindBy(id = "dtToDate")
	private WebElement txtToDate;

	/* Employee Code Text Box */
	@FindBy(id = "vcEmpCode")
	private WebElement txtEmployeeCode;

	/* Country Code Text Box */
	@FindBy(id = "vcCountryCode")
	private WebElement txtCountryCode;

	/* Company CodeText Box */
	@FindBy(id = "vcCompanyCode")
	private WebElement txtCompanyCode;

	/* Amount Text Box */
	@FindBy(id = "vcAmount")
	private WebElement txtAmount;

	/* Remarks Text Box */
	@FindBy(id = "vcRemarks")
	private WebElement txtRemarks;

	/* Through Form Upload Link */
	@FindBy(xpath = "//label[@title='Upload Documents']/parent::div/descendant::div[@class='pull-left text']")
	private WebElement lnkThroughFormUpload;

	/* Through Form Back Button */
	@FindBy(xpath = "//button[@id='ThroughFormSubmit']/preceding-sibling::button[text()='BACK']")
	private WebElement btnThroughFormBack;

	/* Through Form Reset Button */
	@FindBy(xpath = "//button[@id='ThroughFormSubmit']/following-sibling::button[text()='RESET']")
	private WebElement btnThroughFormReset;

	/* Through Form Submit Button */
	@FindBy(id = "ThroughFormSubmit")
	private WebElement btnThroughFormSubmit;

	/* Periodicity Text Box */
	@FindBy(xpath = "//input[@placeholder='Periodicity(Monthly(M)/Queterly(Q)/Annually(A))']")
	private WebElement txtPeriodicity;

	/* Number Of Installments TextBox */
	@FindBy(xpath = "//label[@title='No of Instalments']/following-sibling::input")
	private WebElement txtNumberOfInstallments;

	/* Bonus Period Text Box */
	@FindBy(xpath = "//label[text()='Bonus Period ']/following-sibling::input")
	private WebElement txtBonusPeriod;

	/* Rate Text Box */
	@FindBy(xpath = "//label[text()='Rate ']/following-sibling::input")
	private WebElement txtRate;

	/* Payments Text Box */
	@FindBy(xpath = "//label[@title='Payment Type']/following-sibling::input")
	private WebElement txtPayments;

	/* Project Code Text Box */
	@FindBy(xpath = "//label[@title='Project Code']/following-sibling::input")
	private WebElement txtProjectcode;

	/* Number Of Vouchers Text Box */
	@FindBy(xpath = "//label[@title='No of Vouchers Entitled']/following-sibling::input")
	private WebElement txtNoOfVauchers;

	/* Cost of Entitled Vouchers Text Box */
	@FindBy(xpath = "/label[@title='Cost of Entitled Vouchers']/following-sibling::input")
	private WebElement txtCostofEntitledVouchers;

	/* Taxable Value Text Box */
	@FindBy(xpath = "//label[@title='Taxable Value']/following-sibling::input")
	private WebElement txtTaxableValue;

	/* Total value Of Vouchers Consumed Text Box */
	@FindBy(xpath = "//label[@title='Total Value of Vouchers Consumed']/following-sibling::input")
	private WebElement txtTotalvalueOfVouchersConsumed;

	/* Month Text Box */
	@FindBy(xpath = "//label[@title='Month']/following-sibling::input")
	private WebElement txtMonth;

	/* Project Name Text Box */
	@FindBy(xpath = "//label[@title='Project Name']/following-sibling::input")
	private WebElement txtProjectName;

	/* Input Type Text Box */
	@FindBy(xpath = "//label[text()='Input Type ']/following-sibling::input")
	private WebElement txtInputType;

	/* Payment Type Text Box */
	@FindBy(xpath = "//label[text()='Payment Type ']/following-sibling::input")
	private WebElement txtPaymentType;

	/* Patent Id Text Box */
	@FindBy(xpath = "//label[contains(text(),'Patent Id')]/following-sibling::input")
	private WebElement txtPatentID;

	/* Type of Payment Text Box */
	@FindBy(xpath = "//label[text()='Type of Payment ']/following-sibling::input")
	private WebElement txtTypeofPayment;

	/* Files Text box */
	@FindBy(xpath = "//div[@class='dz-filename']")
	private List<WebElement> txtfiles;

	/* Are you sure you want to proceed? Pop up text */
	@FindBy(xpath = "//p[text()='Are you sure you want to proceed?']")
	private WebElement txtPopUpMsg;

	/* Yes button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYes;

	/* No button */
	@FindBy(xpath = "//button[text()='NO']")
	private WebElement btnNO;

	/* Ok button */
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement btnOk;

	/* Claim requests has been successfully raised Pop up text */
	@FindBy(xpath = "//div[text()='Claim requests has been successfully raised.']")
	private WebElement txtClaimSuccessMsg;

	/*Spinner*/
	@FindBy(xpath = "//div[contains(@class,'spinner')]")
	private WebElement spinner;

	/*Pop up*/
	@FindBy(xpath = "//div[contains(@class,'sweet-alert')]")
	private WebElement popup;

	/**
	 * Description: This class implements the common method for Through the form
	 * 
	 * @author Shreya U
	 * @param fromDate
	 * @param toDate
	 * @param empCode
	 * @param countryCode
	 * @param companyCode
	 * @param amount
	 * @param remarks
	 */
	public synchronized void commonForm(String fromDate, String toDate, String empCode, String countryCode,
			String companyCode, String amount, String remarks) {
		try {
			WebActionUtil.waitForElement(txtEmployeeCode, "Employee code text box");
			WebActionUtil.typeText(txtEmployeeCode, empCode, "Employee code text box");
			String expectedemployeecode = WebActionUtil.getTextUsingJS("vcEmpCode");
			WebActionUtil.validateEnteredValue(expectedemployeecode, empCode, "Employee code text box",
					empCode + " entered in Employee code text box", empCode + " not entered in Employee code text box",
					"blue");

			WebActionUtil.waitForElement(txtCountryCode, "Country code text box");
			WebActionUtil.typeText(txtCountryCode, countryCode, "Country code text box");

			String expectedCountryCode = WebActionUtil.getTextUsingJS("vcCountryCode");
			WebActionUtil.validateEnteredValue(expectedCountryCode, countryCode, "Country code text box",
					countryCode + " entered in Country code text box",
					countryCode + " not entered in Country code text box", "blue");
			WebActionUtil.waitForElement(txtCompanyCode, "Company code text box");
			WebActionUtil.typeText(txtCompanyCode, companyCode, "Company code text box");

			String expectedCompanyCode = WebActionUtil.getTextUsingJS("vcCompanyCode");
			WebActionUtil.validateEnteredValue(expectedCompanyCode, companyCode, "Company code text box",
					companyCode + " entered in Company code text box",
					companyCode + " not entered in Company code text box", "blue");

			WebActionUtil.waitForElement(txtAmount, " Amount text box");
			WebActionUtil.typeText(txtAmount, amount, " Amount text box");

			String expectedAmount = WebActionUtil.getTextUsingJS("vcAmount");
			WebActionUtil.validateEnteredValue(expectedAmount, amount, "Amount text box",
					amount + " entered in Amount text box", amount + " not entered in Amount text box", "blue");

			WebActionUtil.waitForElement(txtFromdate, "From Date text box");
			WebActionUtil.selectGIM_CalendarRangeDates("dtFromDate", fromDate);
			String expectedfromdate = WebActionUtil.getTextUsingJS("dtFromDate");
			WebActionUtil.validateEnteredValue(expectedfromdate, fromDate, "From Date text box",
					fromDate + " entered in from date text box", fromDate + " not entered in from date text box",
					"blue");
			WebActionUtil.waitForElement(txtToDate, "To Date text box");
			WebActionUtil.selectGIM_CalendarRangeDates("dtToDate", toDate);

			String expectedtodate = WebActionUtil.getTextUsingJS("dtToDate");
			WebActionUtil.validateEnteredValue(expectedtodate, toDate, "To Date text box",
					toDate + " entered in To date text box", toDate + " not entered in To date text box", "blue");
			WebActionUtil.waitForElement(txtRemarks, "Remarks text box");
			WebActionUtil.typeText(txtRemarks, remarks, "Remarks text box");
			String expectedRemarks = WebActionUtil.getTextUsingJS("vcRemarks");
			WebActionUtil.validateEnteredValue(expectedRemarks, remarks, "Remarks text box",
					remarks + " entered in Remarks text box", remarks + " not entered in Remarks text box", "blue");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the common form for initiating the claim using Through the Form type");
			Assert.fail("Unable to fill the common form for initiating the claim using Through the Form type");
		}
	}

	/**
	 * Description: Method to click on Back button
	 * 
	 * @author Shreya U
	 */
	public synchronized void clkThroughFormBackButton() {
		try {
			WebActionUtil.waitForElement(btnThroughFormBack, "Through Form Back button");
			WebActionUtil.clickOnElement(btnThroughFormBack, "Through Form Back button",
					"Unable to click Through Form Back button");
			} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click the Through Form Back button");
			Assert.fail("Unable to click the Through Form Back button");
		}
	}

	/**
	 * Description: Method to click on Reset button
	 * 
	 * @author Shreya U
	 */
	public synchronized void clkThroughFormResetButton() {
		try {
			WebActionUtil.waitForElement(btnThroughFormReset, "Through Form Reset button");
			WebActionUtil.clickOnElement(btnThroughFormReset, "Through Form Reset button",
					"Unable to click Through Form Reset button");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Through Form Reset button");
			Assert.fail("Unable to click Through Form Reset button");
		}
	}

	/**
	 * Description: Method to click on Submit button
	 * 
	 * @author Shreya U
	 */
	public synchronized void clkThroughFormSubmitButton() {
		try {
			WebActionUtil.waitForElement(btnThroughFormSubmit, "Through Form Submit button");
			WebActionUtil.clickOnElement(btnThroughFormSubmit, "Through Form Submit button",
					"Unable to click Through Form Submit button");
			try {
				new WebDriverWait(driver, 4).until(ExpectedConditions.invisibilityOf(btnThroughFormSubmit));
			} catch (Exception e) {

			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click Through Form Submit button");
			Assert.fail("Unable to click Through Form Submit button ");
		}
	}

	/**
	 * Description: Method to set Meal voucher form details
	 * 
	 * @author Shreya U
	 * @param projectcode
	 * @param noOfVouchers
	 * @param costofEntitledVouchers
	 * @param taxableValue
	 * @param totalvalueOfVouchersConsumed
	 * @param month
	 * @param projectName
	 */
	public synchronized void setMealVoucher(String projectcode, String noOfVouchers, String costofEntitledVouchers,
			String taxableValue, String totalvalueOfVouchersConsumed, String month, String projectName) {
		try {
			WebActionUtil.waitForElement(txtProjectcode, "Project Code text box");
			WebActionUtil.typeText(txtProjectcode, projectcode, "Project Code text box");
			WebActionUtil.waitForElement(txtMonth, "Month text box");
			WebActionUtil.typeText(txtMonth, month, "Month text box");
			WebActionUtil.waitForElement(txtNoOfVauchers, "No Of Vouchers text box");
			WebActionUtil.typeText(txtNoOfVauchers, noOfVouchers, "No Of Vouchers text box");
			WebActionUtil.waitForElement(txtTaxableValue, "Taxable text box");
			WebActionUtil.typeText(txtTaxableValue, taxableValue, "Taxable text box");
			WebActionUtil.waitForElement(txtCostofEntitledVouchers, "Cost of Entitlement text box");
			WebActionUtil.typeText(txtCostofEntitledVouchers, costofEntitledVouchers, "Cost of Entitlement text box");
			WebActionUtil.waitForElement(txtTotalvalueOfVouchersConsumed, "Total value of vouchers text box");
			WebActionUtil.typeText(txtTotalvalueOfVouchersConsumed, totalvalueOfVouchersConsumed,
					"Total value of vouchers text box");
			WebActionUtil.waitForElement(txtProjectName, "Project text box");
			WebActionUtil.typeText(txtProjectName, projectName, "Project text box");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Failed to set the details of Meal Voucher");
			Assert.fail("Failed to set the details of Meal Voucher");
		}
	}

	/**
	 * Description: Method to upload the file
	 * 
	 * @author Shreya U
	 * @param path
	 */
	public synchronized void uploadFile(String path) {
		try {
			WebActionUtil.waitForElement(lnkThroughFormUpload, "Through Form Upload link");
			WebActionUtil.clickOnElement(lnkThroughFormUpload, "Through Form Upload link",
					"Unable to click Through Form Upload link");
			WebActionUtil.upload(path);

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to upload the file");
			Assert.fail("Unable to upload the file");
		}
	}

	/**
	 * Description: Fill the Transport allowance form
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 */
	public synchronized void filltheTransportAllowance(String fromDate, String toDate, String empCode,
			String countryCode, String companyCode, String amount, String remarks, String inputType, String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.waitForElement(txtInputType, "Input Type text box");
			WebActionUtil.typeText(txtInputType, inputType, " Input Type text box");

			WebActionUtil.validateAttribute(txtInputType, "value", inputType, "Input type text box",
					inputType + " entered in Input type text box ", inputType + " not entered in Input type text box",
					"blue");
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Transport Allowance");
			Assert.fail("Unable to fill the Transport Allowance");
		}
	}

	/**
	 * Description: Validate the reset of Transport allowance form
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetTransportAllowance() {
		try {
			resetcommonForm();
			WebActionUtil.waitForElement(txtInputType, "Input Type text box");
			WebActionUtil.validateAttributeIsEmpty(txtInputType, "value", "Input type text box",
					"The text entered in Input type text box is reset",
					"The text entered in Input type text box is not reset", "blue");
			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate the Transport Allowance reset");
			Assert.fail("Unable to validate the Transport Allowance reset");
		}
	}

	/**
	 * Description: Fill the Car parking deduction form
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param periodicity
	 * @param noOfInstallments
	 * @param filepath
	 */
	public synchronized void filltheCarParkingDeduction(String fromDate, String toDate, String empCode,
			String countryCode, String companyCode, String amount, String remarks, String periodicity,
			String noOfInstallments, String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.waitForElement(txtNumberOfInstallments, "Number of installments text box");
			WebActionUtil.typeText(txtNumberOfInstallments, noOfInstallments, "Number of installments text box");
			WebActionUtil.validateAttribute(txtNumberOfInstallments, "value", noOfInstallments,
					"Number of installments text box",
					noOfInstallments + " entered in Number of installments text box ",
					noOfInstallments + " is not entered in Number of installments text box", "blue");
			WebActionUtil.waitForElement(txtPeriodicity, "Periodicity text box");
			WebActionUtil.typeText(txtPeriodicity, periodicity, "Periodicity text box");
			WebActionUtil.validateAttribute(txtPeriodicity, "value", periodicity, "Periodicity text box",
					periodicity + " entered in Periodicity text box",
					periodicity + " is not entered in Periodicity text box", "blue");
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Car parking deduction claim form");
			Assert.fail("Unable to fill the Car parking deduction claim form");
		}
	}

	/**
	 * Description: validate Reset Car Parking Deduction
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetCarParkingDeduction() {
		try {
			resetcommonForm();
			WebActionUtil.waitForElement(txtNumberOfInstallments, "Number of installments text box");

			WebActionUtil.validateAttributeIsEmpty(txtNumberOfInstallments, "value", "Number of installments text box",
					"The text entered in Number of installments text box is reset",
					"The text entered in Number of installments text box is not reset", "blue");
			WebActionUtil.waitForElement(txtPeriodicity, "Periodicity text box");
			WebActionUtil.validateAttributeIsEmpty(txtPeriodicity, "value", "Periodicity text box",
					"The text entered in Periodicity text box is reset",
					"The text entered in Periodicity text box is not reset", "blue");

			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate the Car parking deduction claim form reset");
			Assert.fail("Unable to validate the Car parking deduction claim form reset");
		}
	}

	/**
	 * Description: Fill the Exgratia Language allowance
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param noOfInstallments
	 * @param rate
	 */
	public synchronized void fillTheExgratiaLanguageAllowance(String fromDate, String toDate, String empCode,
			String countryCode, String companyCode, String amount, String remarks, String rate, String noOfInstallments,
			String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.waitForElement(txtRate, "Rate text box");
			WebActionUtil.typeText(txtRate, rate, "Rate text box");
			WebActionUtil.validateAttribute(txtRate, "value", rate, "Rate text box",
					rate + " entered in Rate text box ", rate + " not entered in Rate text box", "blue");
			
			WebActionUtil.waitForElement(txtNumberOfInstallments, "Number of installments text box");
			WebActionUtil.typeText(txtNumberOfInstallments, noOfInstallments, "Number of installments text box");
			WebActionUtil.validateAttribute(txtNumberOfInstallments, "value", noOfInstallments,
					"Number of installments text box",
					noOfInstallments + " entered in Number of installments text box ",
					noOfInstallments + " not entered in Number of installments text box ", "blue");
		
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Exgratia Language Allowance form");
			Assert.fail("Unable to fill the Exgratia Language Allowanceclaim form");
		}
	}

	/**
	 * Description: validate Reset Exgratia Language Allowance
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateReseteExgratiaLanguageAllowance() {
		try {
			resetcommonForm();
			WebActionUtil.waitForElement(txtNumberOfInstallments, "Number of installments text box");
			WebActionUtil.validateAttributeIsEmpty(txtNumberOfInstallments, "value", "Number of installments text box",
					"The text entered in Number of installments text box is reset",
					"The text entered in Number of installments text box is not reset", "blue");
			WebActionUtil.waitForElement(txtRate, "Rate text box");
			WebActionUtil.validateAttributeIsEmpty(txtRate, "value", "Rate text box",
					"The text entered in Rate text box is reset", "The text entered in Rate text box is not reset",
					"blue");
			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate the Exgratia Language Allowance form reset");
			Assert.fail("Unable to validate the Exgratia Language Allowance form reset");
		}
	}

	/**
	 * Description: Fill the Exgratia Other
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param noOfInstallments
	 */
	public synchronized void fillTheExgratiaOther(String fromDate, String toDate, String empCode, String countryCode,
			String companyCode, String amount, String remarks, String rate, String noOfInstallments, String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.waitForElement(txtNumberOfInstallments, "Number of installments text box");
			WebActionUtil.typeText(txtNumberOfInstallments, noOfInstallments, "Number of installments text box");
			WebActionUtil.validateAttribute(txtNumberOfInstallments, "value", noOfInstallments,
					"Number of installments text box",
					noOfInstallments + " entered in Number of installments text box ",
					noOfInstallments + " not entered in Number of installments text box ", "blue");
			WebActionUtil.waitForElement(txtRate, "Rate text box");
			WebActionUtil.typeText(txtRate, rate, "Rate text box");
			WebActionUtil.validateAttribute(txtRate, "value", rate, "Rate text box",
					rate + " entered in Rate text box ", rate + " is not entered in Rate text box", "blue");
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Exgratia others form ");
			Assert.fail("Unable to fill the Exgratia others form");
		}
	}

	
	/**
	 * Description: Fill the Exgratia Other
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetExgratiaOther() {
		try {
			resetcommonForm();
			WebActionUtil.waitForElement(txtNumberOfInstallments, "Number of installments text box");

			WebActionUtil.validateAttributeIsEmpty(txtNumberOfInstallments, "value", "Number of installments text box",
					"The text entered in Number of installments text box is reset",
					"The text entered in Number of installments text box is not reset", "blue");
			WebActionUtil.waitForElement(txtRate, "Rate text box");
			WebActionUtil.validateAttributeIsEmpty(txtRate, "value", "Rate text box",
					"The text entered in Rate text box is reset", "The text entered in Rate text box is not reset",
					"blue");
			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Exgratia Other form reset");
			Assert.fail("Unable to validate the Exgratia Other form reset");
		}
	}

	/**
	 * Description: Fill Foundation Bonus Geo
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param paymentType
	 */
	public synchronized void fillTheFoundationBonusGeo(String fromDate, String toDate, String empCode,
			String countryCode, String companyCode, String amount, String remarks, String paymentType,
			String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.waitForElement(txtPaymentType, "Payments type text box");
			WebActionUtil.typeText(txtPaymentType, paymentType, "Payments type text box");
			WebActionUtil.validateAttribute(txtPaymentType, "value", paymentType, "Payments type text box",
					paymentType + " entered in Payments type text box",
					paymentType + " not entered in Payments type text box", "blue");
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Foundation Bonus Geo form");
			Assert.fail("Unable to fill the Foundation Bonus Geo form");
		}
	}

	/**
	 * Description: validate Reset Foundation Bonus Geo
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetFoundationBonusGeo() {
		try {
			resetcommonForm();
			WebActionUtil.waitForElement(txtPaymentType, "Payments type text box");
			WebActionUtil.typeText(txtPaymentType, "", "Payments type text box");
			WebActionUtil.validateAttributeIsEmpty(txtPaymentType, "value", "Payments type text box",
					"The text entered in Payments type text box is reset",
					"The text entered in Payments type text box is not reset", "blue");
			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Foundation Bonus Geo reset");
			Assert.fail("Unable to validate Foundation Bonus Geo reset");
		}
	}

	/**
	 * Description: fill the Ikahya Safety bonus form
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param noOfInstallments
	 * @param rate
	 */
	public synchronized void fillTheIkahyaSafetybonus(String fromDate, String toDate, String empCode,
			String countryCode, String companyCode, String amount, String remarks, String noOfInstallments, String rate,
			String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);

			WebActionUtil.waitForElement(txtNumberOfInstallments, "Number of installments text box");
			WebActionUtil.typeText(txtNumberOfInstallments, noOfInstallments, "Number of installments text box");
			WebActionUtil.validateAttribute(txtNumberOfInstallments, "value", noOfInstallments,
					"Number of installments text box",
					noOfInstallments + " entered in Number of installments text box ",
					noOfInstallments + " not entered in Number of installments text box ", "blue");
			WebActionUtil.waitForElement(txtRate, "Rate text box");
			WebActionUtil.typeText(txtRate, rate, "Rate text box");
			WebActionUtil.validateAttribute(txtRate, "value", rate, "Rate text box",
					rate + " entered in Rate text box ", rate + " not entered in Rate text box", "blue");
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Ikahya Safety bonus form");
			Assert.fail("Unable to fill the Ikahya Safety bonus form");
		}
	}

	/**
	 * Description: validate Reset Ikahya Safety bonus
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetIkahyaSafetybonus() {
		try {
			WebActionUtil.waitForElement(txtNumberOfInstallments, "Number of installments text box");
			WebActionUtil.validateAttributeIsEmpty(txtNumberOfInstallments, "value", "Number of installments text box",
					"The text entered in Number of installments text box is reset",
					"The text entered in Number of installments text box is not reset", "blue");
			WebActionUtil.waitForElement(txtRate, "Rate text box");
			WebActionUtil.validateAttributeIsEmpty(txtRate, "value", "Rate text box",
					"The text entered in rate text box is reset", "The text entered in rate text box is not reset",
					"blue");
			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Ikahya Safety bonus reset");
			Assert.fail("Unable to validate Reset Ikahya Safety bonus reset");
		}
	}

	/**
	 * Description: fill the Retention Bonus claim form
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param noOfInstallments
	 * @param rate
	 */
	public synchronized void fillTheRetentionBonus(String fromDate, String toDate, String empCode, String countryCode,
			String companyCode, String amount, String remarks, String bonusPeriod, String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.waitForElement(txtBonusPeriod, "Bonus Period text box");
			WebActionUtil.typeText(txtBonusPeriod, bonusPeriod, "Bonus Period text box");
			WebActionUtil.validateAttribute(txtBonusPeriod, "value", bonusPeriod, "Bonus Period text box",
					bonusPeriod + " entered in Bonus Period text box ",
					bonusPeriod + " not entered in Bonus Period text box", "blue");

			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Retention Bonus Form");
			Assert.fail("Unable to fill the Retention Bonus Form");
		}
	}

	/**
	 * Description: Validate Reset Retention Bonus
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetRetentionBonus() {
		try {
			resetcommonForm();
			WebActionUtil.waitForElement(txtBonusPeriod, "Bonus Period text box");
			WebActionUtil.validateAttributeIsEmpty(txtBonusPeriod, "value", "Bonus Period text box",
					"The text entered in Bonus Period text box is reset",
					"The text entered in Bonus Period text box is not reset", "blue");
			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Retention Bonus reset");
			Assert.fail("Unable to validate Retention Bonus reset");
		}
	}

	/**
	 * Description: fill the Client Bonus Reward Incentive
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param payementType
	 */
	public synchronized void fillTheClientBonusRewardIncentive(String fromDate, String toDate, String empCode,
			String countryCode, String companyCode, String amount, String remarks, String paymentType,
			String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.waitForElement(txtTypeofPayment, "Type of Payment text box");
			WebActionUtil.typeText(txtTypeofPayment, paymentType, "Type of Payment text box");
			WebActionUtil.validateAttribute(txtTypeofPayment, "value", paymentType, "Type of Payment text box",
					paymentType + " entered in Type of Payment text box",
					paymentType + " not entered in Type of Payment text box", "blue");
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill Client Bonus Reward Incentive form");
			Assert.fail("Unable to fill Client Bonus Reward Incentive form");
		}
	}

	/**
	 * Description: validate Reset Client Bonus Reward Incentive
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetClientBonusRewardIncentive() {
		try {
			resetcommonForm();
			WebActionUtil.waitForElement(txtTypeofPayment, "Type of Payment text box");
			WebActionUtil.validateAttributeIsEmpty(txtTypeofPayment, "value", "Type of Payment text box",
					"The text entered in Type of Payment text box is reset",
					"The text entered in Type of Payment text box is not reset", "blue");

			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Client Bonus Reward Incentive reset");
			Assert.fail("Unable to validate Client Bonus Reward Incentive reset");
		}
	}

	/**
	 * Description: fill the HCL Jewel form
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param paymentType
	 */
	public synchronized void fillTheHCLJewels(String fromDate, String toDate, String empCode, String countryCode,
			String companyCode, String amount, String remarks, String paymentType, String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.typeText(txtPaymentType, paymentType, "Payment type text box");
			WebActionUtil.validateAttribute(txtPaymentType, "value", paymentType, "Payment type text box",
					paymentType + " entered in Payment type text box",
					paymentType + " not entered in Payment type text box", "blue");
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill HCL Jewels Form");
			Assert.fail("Unable to fill HCL Jewels Form");
		}
	}

	/**
	 * Description: fill the HCL Jewel form
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetHCLJewel() {
		try {
			resetcommonForm();
			WebActionUtil.validateAttributeIsEmpty(txtPaymentType, "value", "Payment type text box",
					"The text entered in Payment type text box is reset",
					"The text entered in Payment type text box is not reset", "blue");
			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate HCL Jewels reset");
			Assert.fail("Unable to validate HCL Jewels reset");
		}
	}

	/**
	 * Description: fill the RSU Payments form
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 */
	public synchronized void fillTheRSUPayment(String fromDate, String toDate, String empCode, String countryCode,
			String companyCode, String amount, String remarks, String filepath) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill RSU Payment Form");
			Assert.fail("Unable to fill RSU Payment Form");
		}
	}

	/**
	 * Description: Validate Reset of the RSU payments
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetRSUPayment() {
		try {
			resetcommonForm();
			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate RSU Payment reset");
			Assert.fail("Unable to validate RSU Payment reset");
		}
	}

	/**
	 * Description: fill the Patent Award form
	 * 
	 * @author Shreya U
	 * @param inputType
	 * @param remarks
	 * @param amount
	 * @param companyCode
	 * @param countryCode
	 * @param empCode
	 * @param toDate
	 * @param fromDate
	 * @param patentid
	 */
	public synchronized void fillThePatentAward(String fromDate, String toDate, String empCode, String countryCode,
			String companyCode, String amount, String remarks, String filepath, String patentid) {
		try {
			commonForm(fromDate, toDate, empCode, countryCode, companyCode, amount, remarks);
			WebActionUtil.waitForElement(txtPatentID, "Patent Id text box");
			WebActionUtil.typeText(txtPatentID, patentid, "Patent Id text box");
			WebActionUtil.validateAttribute(txtPatentID, "value", patentid, "Patent Id text box",
					patentid + " entered in Patent Id text box", patentid + " not entered in Patent Id text box",
					"blue");
			uploadFile(filepath);
			validateFileUploaded();
			WebActionUtil.validationinfo("All the details is entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill Patent Award form");
			Assert.fail("Unable to fill Patent Award form");
		}
	}

	/**
	 * Description: validate Reset Patent Award
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateResetThePatentAward() {
		try {
			resetcommonForm();
			WebActionUtil.waitForElement(txtPatentID, "Patent Id text box");
			WebActionUtil.validateAttributeIsEmpty(txtPatentID, "value", "Patent Id text box",
					"The text entered in Patent Id text box is reset",
					"The text entered in Patent Id text box is not reset", "blue");

			validateFileUploaded();
			WebActionUtil.validationinfo("All the data is cleared", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Patent Award reset");
			Assert.fail("Unable to validate Patent Award reset");
		}
	}

	/**
	 * Description: Validate Are you sure you want to proceed pop up
	 * 
	 * @author Shreya U
	 */
	public synchronized void validatePopup() {
		try {
			try {
				new WebDriverWait(driver, 7).until(ExpectedConditions.visibilityOf(popup));
			} catch (Exception e) {

			}
			WebActionUtil.validateisElementDisplayed(txtPopUpMsg, "Are you sure you want to proceed pop up",
					"Are you sure you want to proceed pop up is displayed",
					"Are you sure you want to proceed pop up is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Are you sure you want to proceed pop up");
			Assert.fail("Unable to validate Are you sure you want to proceed pop up");
		}
	}

	/**
	 * Description: Validate Claim requests has been successfully raised Toast
	 * message
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateClaimSuccessMessage() {
		try {

			WebActionUtil.waitForElement(txtClaimSuccessMsg,
					"Claim requests has been successfully raised success message");
			WebActionUtil.validateisElementDisplayed(txtClaimSuccessMsg,
					"Claim requests has been successfully raised success message",
					"Claim requests has been successfully raised success message is displayed",
					"Claim requests has been successfully raised success message is not displayed", "green");
			if (spinner.isDisplayed()) {
				new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(spinner));
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Claim requests has been successfully raised success message");
			Assert.fail("Unable to validate Claim requests has been successfully raised success message");
		}
	}

	/**
	 * Description: Method to click on No button in pop up
	 * 
	 * @author Shreya U
	 */
	public synchronized void clkPopUpNoButton() {
		try {

			WebActionUtil.waitForElement(btnNO, "No button");
			WebActionUtil.clickOnElement(btnNO, "No button", "Unable to click on No button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No button");
			Assert.fail("Unable to click on No button");
		}
	}

	/**
	 * Description: Method to click on Yes button in pop up
	 * 
	 * @author Shreya U
	 */
	public synchronized void clkPopUpYesButton() {
		try {
			WebActionUtil.waitForAngularPageload();
			WebActionUtil.waitForElement(btnYes, "Yes button");
			WebActionUtil.poll(2000l);
			WebActionUtil.clickOnElement(btnYes, "Yes button", "Unable to click on Yes button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes button");
			Assert.fail("Unable to click on Yes button");
		}
	}

	/**
	 * Description: Method to validate file upload
	 * 
	 * @author Shreya U
	 */
	public synchronized void validateFileUploaded() {
		if (txtfiles.size() > 0) {
			WebActionUtil.validationinfo("The file is uploaded ", "blue");
			WebActionUtil.info("The file is uploaded in the file upload text area");
		} else {
			WebActionUtil.validationinfo("The file upload text area is reset", "blue");
			WebActionUtil.info("The file upload text area is reset");
		}
	}

	/**
	 * Description: This method implements validation of common form reset
	 * 
	 * @author Shreya U
	 */
	public synchronized void resetcommonForm() {
		try {
			WebActionUtil.waitForElement(txtFromdate, "From Date text box");
			String expectedfromdate = WebActionUtil.getTextUsingJS("dtFromDate");
			WebActionUtil.validateEnteredValue(expectedfromdate, "", "From Date text box",
					"The text entered in From Date text box is reset",
					"The text entered in From Date text box is not reset ", "blue");
			WebActionUtil.waitForElement(txtToDate, "To Date text box");
			String expectedtodate = WebActionUtil.getTextUsingJS("dtToDate");
			WebActionUtil.validateEnteredValue(expectedtodate, "", "To Date text box",
					"The text entered in To Date text box is reset",
					"The text entered in To Date text box is not reset", "blue");
			WebActionUtil.waitForElement(txtEmployeeCode, "Employee code text box");
			String expectedemployeecode = WebActionUtil.getTextUsingJS("vcEmpCode");
			WebActionUtil.validateEnteredValue(expectedemployeecode, "", "Employee code text box",
					"The text entered in Employee code text box is reset",
					"The text entered in Employee code text box is not reset", "blue");
			WebActionUtil.waitForElement(txtCountryCode, "Country code text box");
			String expectedCountryCode = WebActionUtil.getTextUsingJS("vcCountryCode");
			WebActionUtil.validateEnteredValue(expectedCountryCode, "", "Country code text box",
					"The text entered in Country code text box is reset",
					"The text entered in Country code text box is not reset", "blue");
			WebActionUtil.waitForElement(txtCompanyCode, "Company code text box");
			String expectedCompanyCode = WebActionUtil.getTextUsingJS("vcCompanyCode");
			WebActionUtil.validateEnteredValue(expectedCompanyCode, "", "Company code text box",
					"The text entered in Company code text box is reset",
					"The text entered in Company code text box is not reset", "blue");
			WebActionUtil.waitForElement(txtAmount, "Amount text box");
			String expectedAmount = WebActionUtil.getTextUsingJS("vcAmount");
			WebActionUtil.validateEnteredValue(expectedAmount, "", "Amount text box",
					"The text entered in Amount text box is reset", "The text entered in Amount text box is not reset",
					"blue");
			WebActionUtil.waitForElement(txtRemarks, "Remarks text box");
			String expectedRemarks = WebActionUtil.getTextUsingJS("vcRemarks");
			WebActionUtil.validateEnteredValue(expectedRemarks, "", "Remarks text box",
					"The text entered in Remarks text box is reset",
					"The text entered in Remarks text box is not reset", "blue");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate common form reset");
			Assert.fail("Unable to validate common form reset");
		}
	}
}