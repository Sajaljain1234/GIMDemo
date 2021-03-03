package com.hcl.gim.baseutil;

import org.openqa.selenium.WebDriver;

import com.hcl.gim.pages.ChildEducationAllowance_Page;
import com.hcl.gim.pages.Home_Page;
import com.hcl.gim.pages.InitiateClaim_Page;
import com.hcl.gim.pages.InitiateClaims_Page;
import com.hcl.gim.pages.Login_Page;
import com.hcl.gim.pages.PendingActions_Page;
import com.hcl.gim.pages.PreviousRecords_Page;
import com.hcl.gim.pages.ThroughTheForm_Page;
import com.hcl.gim.util.WebActionUtil;
/**
 *Description: Initializes all pages with driver instance ,Explicit Time out, WebAactionUtility
 *             using variables driver,ETO,WebactionUtil
 *@author  :   Shreya U,Vivek Dogra,Aatish Slathia 
 */
public class InitializePages {
	
	

	public Home_Page homePage;

	public Login_Page loginpage;
	
	public ChildEducationAllowance_Page childEducationAllowancePage;
	
	public InitiateClaim_Page initiateClaimPage ;
	
	public InitiateClaims_Page initiateClaimsPage ;
	
	public PendingActions_Page pendingActionsPage ;
	 
	public PreviousRecords_Page previousRecordsPage;
	
	public ThroughTheForm_Page throughTheFormPage ;
	
 
	public InitializePages(WebDriver driver,long ETO,WebActionUtil WebActionUtil) {
	
		homePage=new Home_Page(driver, ETO, WebActionUtil);
		loginpage=new Login_Page(driver, ETO, WebActionUtil);
		initiateClaimPage =new InitiateClaim_Page(driver, ETO, WebActionUtil);
		initiateClaimsPage=new InitiateClaims_Page(driver, ETO, WebActionUtil);
		pendingActionsPage=new PendingActions_Page(driver, ETO, WebActionUtil);
		previousRecordsPage=new PreviousRecords_Page(driver, ETO, WebActionUtil);
		throughTheFormPage =new ThroughTheForm_Page(driver, ETO, WebActionUtil);
		childEducationAllowancePage =new ChildEducationAllowance_Page(driver, ETO, WebActionUtil);
	}
	

}
