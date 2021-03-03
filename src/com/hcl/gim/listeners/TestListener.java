package com.hcl.gim.listeners;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.hcl.gim.baseutil.BaseTest;
import com.hcl.gim.reports.ExtentHCLManager;
import com.hcl.gim.reports.ExtentHCLTest;
import com.hcl.gim.util.WebActionUtil;
import com.sun.istack.logging.Logger;

/**
 * Description: This class implements ITestListener and overrides methods like
 * onFinish, onTestFailure,onTestSkipped,onTestSuccess which are used in Extent
 * report and Emailable report.
 * 
 * @author Shreya U,Aatish S
 * 
 */
public class TestListener implements ITestListener {

	String className;
	public static int iPassCount = 0;
	public static int iFailCount = 0;
	public static int iSkippedCount = 0;
	public static String profile = null;
	public static ArrayList sTestName = new ArrayList<String>();
	public static ArrayList sStatus = new ArrayList<String>();
	public static long totPassedTime = 0;
	public static long totFailedTime = 0;
	public static long totSkippedTime = 0;
	public static long totalTimeTaken = 0;
	public static String pass_Time = "0";
	public static String fail_Time = "0";
	public static String skip_Time = "0";
	public static String tot_Time = "0";
	public static Properties prop;
	public static String sDirPath = System.getProperty("user.dir");
	public static final String PDFREPORTPATH=sDirPath +"./docs"+".pdf";
	public static final String CONFIGPATH = sDirPath + "./config/config.properties";
	static {
		BaseTest.logger.info("");
		profile = System.getProperty("profile");
		profile = "JavaMail";
		System.setProperty("profile", profile);
		BaseTest.logger.info("Running locally " + profile);
	}
	
	static Map<String, String> reportMailBody = new HashMap<String, String>();
	File pdfReports = new File(PDFREPORTPATH);
	
	/**
	 * Description :Flushes the Extent report and sends an email of the report.
	 * 
	 * @author Aatish Slathia,shreya Ugavekar
	 * @paran context
	 * 
	 */
	
	public void onFinish(ITestContext context) {
		
		iPassCount = context.getPassedTests().size();
		iFailCount = context.getFailedTests().size();
		iSkippedCount = context.getSkippedTests().size();
		int iTotal_Executed = iPassCount + iFailCount + iSkippedCount;
		totalTimeTaken = totPassedTime + totFailedTime + totSkippedTime;
		tot_Time = WebActionUtil.formatDuration(totalTimeTaken);
    	//sendMail(iPassCount, iFailCount, iSkippedCount, iTotal_Executed, pdfReports, profile);
		ExtentHCLTest.getExtent().flush();

	}

	public void onStart(ITestContext context) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	/**
	 * Description :Increases the fail count and add fail result in Extent
	 * report,adds screenshots to the  Extent report on Test case failure.
	 * 
	 * @author Aatish Slathia,shreya Ugavekar
	 * @param result
	 * 
	 */
	public void onTestFailure(ITestResult result) {
		
		iFailCount =iFailCount + 1;
		ExtentHCLManager.getTestReport().log(Status.FAIL, result.getMethod().getMethodName() + "-Test case failed");
		try {
			ExtentHCLManager.getTestReport().addScreenCaptureFromPath(
					WebActionUtil.getScreenShot(System.getProperty("user.dir") + "/reports" + "/ScreenShots-"
							+ WebActionUtil.getCurrentDateTime() + "/screenshots/"));
		} catch (IOException e) {
			BaseTest.logger.error("Unable to attach the screenshot");
		}

	}
	/**
	 * Description :Increases the skip count and adds the skip result in Extent report.
	 * 
	 * @author Aatish Slathia,shreya Ugavekar
	 * @param result
	 * 
	 */
	public void onTestSkipped(ITestResult result) {
		iSkippedCount = iSkippedCount + 1;
		ExtentHCLManager.getTestReport().log(Status.SKIP, result.getMethod().getMethodName() + "-Test case Skipped");
		
	}

	public void onTestStart(ITestResult result) {

	}
	/**
	 * Description:Increases the pass count and adds the pass result in Extent report
	 * 
	 * @author Aatish Slathia,shreya Ugavekar
	 * @param result
	 * 
	 */
	public void onTestSuccess(ITestResult result) {
		iPassCount = iPassCount + 1;
		ExtentHCLManager.getTestReport().log(Status.PASS, result.getMethod().getMethodName() + "-Test case passed");
	}

	/**
	 * Description :Sends email of the test execution as a report to the recipient specified in
	 * the property file.
	 * 
	 * @author Aatish Slathia
	 * @param iPassCount
	 * @param iFailCount
	 * @param skippedCount
	 * @param iTotal_Executed profile
	 */
	public static void sendMail(int iPassCount, int iFailCount, int skippedCount, int iTotal_Executed, File pdfReports,
			String profile) {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATH);
			prop.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		final String InternetAddress = prop.getProperty("InternetAddress");
		final String PasswordAuthentication = prop.getProperty("PasswordAuthentication");
		String RecipientType = prop.getProperty("RecipientType");
		String RecipientType2 = prop.getProperty("RecipientType2");
		String SmtpUser = prop.getProperty("SmtpUser");
		String SmtpHost = prop.getProperty("SmtpHost");

		Properties props = new Properties();
		BaseTest.logger.info("Executing sendMail method");
		Date date = new Date();
		SimpleDateFormat sdtf = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String sdate = sdf.format(date);
		String message = "<p>Hi Team,</p><div style=\"font-family:Verdana;\">Please check the Test script execution summary of "
				+ profile + " as shown in below table. </div><p></p><p></p><p></p><p></p>"
				+ "<p><div style=\"font-family:Verdana;\"><b> EXECUTION SUMMARY : </b></div></p>"
				+ "<table bgcolor=\"#f2d285\" style=\"border-radius: 20px; padding: 25px;\"><tr><td>&nbsp;&nbsp;&nbsp;<table style=\"height:180px; width:200px; border-width:2px; border-style:groove; float: left\"><tbody>"
				+ "<tr style=\"outline: thin solid; font-family:Verdana; color: #000000; text-align: left; border-width:2px; \"><th style=\"outline: thin solid;\">Total Executed</th><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ iTotal_Executed
				+ "&nbsp;&nbsp;</td><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ "&nbsp;&nbsp;</td></tr>"
				+ "<tr style=\"outline: thin solid;  font-family:Verdana; color: #000000; text-align: left; border-width:2px; \"><th style=\"outline: thin solid;\">Passed</th><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ iPassCount + "&nbsp;&nbsp;</td><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ "&nbsp;&nbsp;</td></tr>"
				+ "<tr style=\"outline: thin solid; font-family:Verdana; color: #000000; text-align: left; border-width:2px; \"><th style=\"outline: thin solid;\">Failed</th><td style=\"color: Red; outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ iFailCount + "&nbsp;&nbsp;</td><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ "&nbsp;&nbsp;</td></tr>"
				+ "<tr style=\"outline: thin solid; font-family:Verdana; color: #000000; text-align: left; border-width:2px; \"><th style=\"outline: thin solid;\">Skipped</th><td style=\"color: Red; outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ iSkippedCount + "&nbsp;&nbsp;</td><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ "&nbsp;&nbsp;</td></tr>";

		message = message
				+ "<table bgcolor=\"#f2d285\" style=\"border-radius: 20px; padding: 25px;\"><tr><td><table style=\"width:800px; border-width:2px; border-style:groove; float: left\"><tbody>"
				+ "<tr style=\"outline: thin solid;  font-family:Verdana; color: #000000; text-align: left; border-width:2px; \"><th style=\"outline: thin solid;\">Time Taken For Passed Script</th><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ pass_Time + "&nbsp;&nbsp;</td></tr>"
				+ "<tr style=\"outline: thin solid; font-family:Verdana; color: #000000; text-align: left; border-width:2px; \"><th style=\"outline: thin solid;\">Time Taken For Failed Script</th><td style=\"color: Red; outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ fail_Time + "&nbsp;&nbsp;</td></tr>"
				+ "<tr style=\"outline: thin solid; font-family:Verdana; color: #000000; text-align: left; border-width:2px; \"><th style=\"outline: thin solid;\">Time Taken For Skipped Script</th><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ skip_Time + "&nbsp;&nbsp;</td></tr>" + "</tbody></table></td>"
				+ "<tr style=\"outline: thin solid; font-family:Verdana; color: #000000; text-align: left; border-width:2px; \"><th style=\"outline: thin solid;\">Total Time Taken For Suite Execution</th><td style=\"outline: thin solid; font-weight: bold;\">&nbsp;&nbsp;"
				+ tot_Time + "&nbsp;&nbsp;</td></tr>" + "</tbody></table></td>" + "&nbsp;&nbsp;&nbsp;";

		message = message
				+ "<table bgcolor=\"#f2d285\" style=\"border-radius: 20px; padding: 25px;\"><tr><td><table style=\"width:800px; border-width:2px; border-style:groove; float: left\"><tbody>"
				+ "<tr style=\"outline: thin solid; font-family:Verdana; color: #000000; text-align: left; border-width:2px; width:400px; \"><th style=\"outline: thin solid;\">Test Case</th>"
				+ "<th style=\"outline: thin solid; width:400px; \">Reason for failure</th></tr>";
		for (@SuppressWarnings("rawtypes")
		Map.Entry entry : reportMailBody.entrySet()) {
			BaseTest.logger.info(entry.getKey() + " :" + entry.getValue());
			message = message
					+ "<tr style=\"outline: thin solid; font-family:Verdana; color: #000000; text-align: left; border-width:2px; width:400px; \">"
					+ "<td style=\"outline: thin solid; width:2px; border-style:groove; float: left  width:400px; \">"
					+ entry.getKey()
					+ "</td> <td style=\"outline: thin solid; width:2px; border-style:groove; float: left  width:600px; \">"
					+ entry.getValue() + "</td>" + " </tr>";
		}
		message = message + "</tbody></table></td></tr></table>"
				+ "<p></p><div style=\"font-family:Verdana;\">Regards,</div><p></p>"
				+ "<div style=\"font-family:Verdana;\">Test Yantra Software Automation Team</div>";

		props.put("mail.smtp.user", SmtpUser);
		props.put("mail.smtp.host", SmtpHost);
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(InternetAddress, PasswordAuthentication);
			}
		});

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(InternetAddress));
			msg.setRecipients(Message.RecipientType.TO, RecipientType);
			msg.addRecipients(Message.RecipientType.TO, RecipientType2);
			msg.setSubject("Test " + "script execution" + " summary of " + profile + sdate);
			msg.setSentDate(new Date());
			Multipart multipart = new MimeMultipart();
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(message, "text/html");
			multipart.addBodyPart(textPart);
			msg.setContent(multipart);
			Transport.send(msg);
			BaseTest.logger.info("Mail sent successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Description :Sets the status of the test execution in the mail report.
	 * 
	 * @author Aatish Slathia
	 * @param sName
	 * @param sResult
	 * @param sTestName
	 * @param sStatus
	 * 
	 */
	public static void setStatus(String sName, String sResult, ArrayList sTestName, ArrayList sStatus) {
		sName = sName.replace("test", "");
		sTestName.add(sName);
		sStatus.add(sResult);

		if (sResult.equals("Passed")) {
			iPassCount = iPassCount + 1;
		} else if (sResult.equals("Failed")) {
			iFailCount = iFailCount + 1;
		} else {
			iSkippedCount = iSkippedCount + 1;
		}
	}
}
