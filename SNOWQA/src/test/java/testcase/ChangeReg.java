package testcase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.ProblemReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

import pages.ChangePage;
import pages.ProblemPage;

public class ChangeReg {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	static String crNumber=null;
	static String prNumber=null;
	static String cTask=null;
	@BeforeTest
	public void startReport(){
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport11.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Amreen Noor");
		htmlReporter.config().setDocumentTitle("Change Request");
		htmlReporter.config().setReportName("Change Request Smoke suite");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void testCreateChnageRequest() throws IOException, InterruptedException{
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName2(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
	}
	@Test(priority=1,description="Creating change without mandtory field reason for change ",enabled=true)
public void testCreateNormalChangeRequestN() throws IOException, InterruptedException{
		
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName2(driver, "change");
		ChangeReusables.createChange1(driver,1,2);
		if( driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed()){
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
			}else{
				System.out.println("invisible");
			
			}
	
		
	}
	@Test(priority=1,description="Creating change without mandtory field reason for change ",enabled=true)
	public void testCreateStandardChangeRequest1() throws IOException, InterruptedException{
			
			logger = extent.createTest("Creation of a CR Ticket");
			String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
			System.setProperty("webdriver.gecko.driver",FirefoxDriver);
			WebDriver driver=new FirefoxDriver();
			
		    driver.get("http://thomsonreutersqa.service-now.com");
		    SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName2(driver, "change");
			crNumber=ChangeReusables.createStandardChange(driver,1,2);
			
			driver.findElement(By.linkText(crNumber)).click();
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
			
	
	
	}
	
	@Test(priority=1,description="Creating change without mandtory field reason for change ",enabled=true)
	public void testCreateStandardMultiplePhase() throws IOException, InterruptedException{
			
			logger = extent.createTest("Creation of a CR Ticket");
			String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
			System.setProperty("webdriver.gecko.driver",FirefoxDriver);
			WebDriver driver=new FirefoxDriver();
			
		    driver.get("http://thomsonreutersqa.service-now.com");
		    SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName2(driver, "change");
			crNumber=ChangeReusables.createStandardChange(driver,1,2);
			
			driver.findElement(By.linkText(crNumber)).click();
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
			
			ChangePage.getSubmitForPlanningBtn(driver).click();
			Thread.sleep(10000);
			ChangeReusables.moveToAssessmentStatestand(driver);
			ChangeReusables.schedule(driver);
			ChangePage.getUpdateBtn(driver).click();
			ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
			ChangePage.getRequestImplementationBtn(driver).click();
			ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
			 ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		    
		    Thread.sleep(5000);
			WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
			ChangePage.getScheduleTab(driver).click();
			String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
			String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
			ChangePage.getChangeTaskTab(driver).click();
			System.out.println("success");
			ChangePage.getChangeTaskLnk(driver, 1).click();
			ChangePage.getExpectedStartBtn(driver).sendKeys(start);
			ChangePage.getExpectedEndBtn(driver).sendKeys(end);
			Thread.sleep(5000);
			ChangePage.getCompleteImplementationBtn(driver).click();
			ChangeReusables.AssignedTo(driver);
		    WebElement re=ChangePage.getImplementationResult(driver);
			DropDowns.selectDropdownByIndex(re, 1, "result");
			ChangePage.getActualStartBtn(driver).sendKeys(start);
			ChangePage.getActualEndBtn(driver).sendKeys(end);
			Thread.sleep(5000);
			ChangePage.getSaveBtn(driver).click();
			ChangePage.getCloseTaskBtn(driver).click();
			ChangePage.getUpdateBtn(driver).click();
			
			 ChangeReusables.FinalReport(driver, "Closed", crNumber, 7, 2);
			 ChangeReusables.FinalReport(driver, "Closed", crNumber, 8, 2);
				driver.close();	
				 Assert.assertTrue(true);
					logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is moving the change task to different state and validating the closure of change task ", ExtentColor.GREEN));
				
			//ChangeReusables.moveToApprovalState(driver);
			//ChangePage.getUpdateBtn(driver).click();
			//ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			//ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
			//ChangePage.getGroupApprovalTab(driver).click();
			//Thread.sleep(5000);
			//ChangePage.getChangeRequestedLnk(driver).click();
			//Thread.sleep(5000);
			//ChangePage.getApproveBtn(driver).click();
		    //Thread.sleep(5000);
		    ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
		 //ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		    // driver.close();
			// Assert.assertTrue(true);
			// logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Approve of change ticket", ExtentColor.GREEN));
			
	
	
	
}
	
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void testCreateEmergencyChnageRequest() throws IOException, InterruptedException{
		
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName2(driver, "change");
		crNumber = ChangeReusables.createEmerChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		//ChangeReusables.FinalReport(driver, "Draft", crNumber, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
	}



@Test(priority=2,description="Approval of chnage ticket and Moving the Change to Multiple phase",enabled=true)
public void testMovingtoMultiplePhasesEmer() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createEmerChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
    ChangeReusables.schedule(driver);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangeReusables.moveToApprovalState(driver);
	ChangeReusables.FinalReport(driver, "Approval", crNumber, 2, 2);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangePage.getGroupApprovalTab(driver).click();
	Thread.sleep(5000);
	ChangePage.getChangeRequestedLnk(driver).click();
	Thread.sleep(5000);
	ChangePage.getApproveBtn(driver).click();
	ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
	Thread.sleep(5000);
	WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
	ChangePage.getScheduleTab(driver).click();
	String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
	String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
	ChangePage.getChangeTaskTab(driver).click();
	System.out.println("success");
	ChangePage.getChangeTaskLnk(driver, 1).click();
	ChangePage.getExpectedStartBtn(driver).sendKeys(start);
	ChangePage.getExpectedEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getCompleteImplementationBtn(driver).click();
	ChangeReusables.AssignedTo(driver);
    WebElement re=ChangePage.getImplementationResult(driver);
	DropDowns.selectDropdownByIndex(re, 1, "result");
	ChangePage.getActualStartBtn(driver).sendKeys(start);
	ChangePage.getActualEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getSaveBtn(driver).click();
	ChangePage.getCloseTaskBtn(driver).click();
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,1,2);
	ChangeReusables.FinalReport(driver, "Closed", crNumber, 2, 2);
	driver.close();	
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Moving the Change to Multiple phases ", ExtentColor.GREEN));
	}

@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
public void testCreateNormalChnageRequestN() throws IOException, InterruptedException{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	ChangeReusables.creatChange2(driver,1,2);
	//ChangeReusables.createChange1(driver,1,2);
	if( driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
		}
	

}

@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
public void testCreateNormalChnageRequestfromproblem() throws Exception{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
    ServiceNowUtils.navigateToModuleName2(driver, "Problem");
    prNumber=ProblemReusables.createProblem(driver,1,2);
	   //ProblemReusables.verifyProblemCreation1(driver, prNumber); 
	   ProblemPage.getProblemNumberFromQueue(driver, prNumber).click();
	   WaitUtils.waitForPageToLoad(driver, 30);
	   
	   //ProblemPage.getMenu(driver).click();
	   Thread.sleep(3000);
	   //ProblemPage.getChangeLink(driver).click();
	    WebElement element=driver.findElement(By.xpath("/html/body/div[8]/div[5]"));
	    new Actions(driver).moveToElement(element).perform();

	   
	    
	 // ProblemPage.getChangeLink(driver).click();
    /*WaitUtils.waitForPageToLoad(driver, 30);
    String source = ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 5);
    String assignmentGroup = ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 6);
    String configurationItem = ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 7);
    String shortDescription = ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 10);
    String description = ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 11);
    WaitUtils.waitForIdPresent(driver, "sys_readonly.problem.number");
    String problemId = driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
    System.out.println(problemId);
    int sNum = 0;
   
	int cellNum = 0;
	ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 2, problemId);
   // ExtentReport.reportLog(LogStatus.INFO, "Problem number captured: "+problemId);
   // ReporterLogs.log("Problem number captured: "+problemId, "info");     
    WaitUtils.waitForIdPresent(driver, "problem.contact_type");
    DropDowns.selectDropdownByVisibleText(ProblemPage.getSourceDropdown(driver), source, "Source");
    //Thread.sleep(3000);                                 
    WaitUtils.waitForIdPresent(driver, "sys_display.problem.assignment_group");
    TextBoxes.enterTextValue(ProblemPage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignment Group");                    
    ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
    ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.DOWN);
    ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
    Thread.sleep(5000);
    ReporterLogs.log("Entering Assignment group field value "+assignmentGroup, "info");                 
    TextBoxes.enterTextValue(ProblemPage.getShortdescriptionEdt(driver), shortDescription, "Short Description");
    Thread.sleep(2000);
    TextBoxes.enterTextValue(ProblemPage.getDescriptionEdt(driver), description, "Description");
    Thread.sleep(2000);
    WaitUtils.waitForIdPresent(driver, "sys_display.problem.cmdb_ci"); 
    TextBoxes.enterTextValue(ProblemPage.getConfigurationitemEdt(driver), configurationItem, "Configuration Item");
    ProblemPage.getConfigurationitemEdt(driver).sendKeys(Keys.ENTER);
    ReporterLogs.log("Entering Configuration Item field value "+configurationItem, "info");
    Thread.sleep(5000);
    ProblemPage.getSubmitBtn(driver).click();*/


}

@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
public void testButtonVerification() throws IOException, InterruptedException{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	if( driver.findElement(By.xpath("//button[text()='Update']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Copy Change']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Submit for Planning']")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
		}
	
}

@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
public void testDefaulttaskvalidation() throws IOException, InterruptedException{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
	ChangePage.getChangeTaskTab(driver).click();
	System.out.println("success");
	ChangePage.getChangeTaskLnk(driver, 1).click();
	cTask=ChangePage.getTaskChangeEdt(driver).getAttribute("value");
	if(cTask!=null)
	{
		System.out.println(cTask);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
	}
	else
	{
		System.out.println("fail");
	}
	
}
@Test(priority=2,description="Approval of chnage ticket and Moving the Change to Multiple phase",enabled=true)
public void testStartAndEnd() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createEmerChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
    ChangePage.getSaveBtn(driver).click();
    if( driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
		}
}
@Test(priority=2,description="Approval of chnage ticket and Moving the Change to Multiple phase",enabled=true)
public void testFollowUp() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createEmerChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
    ChangeReusables.schedule(driver);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangeReusables.moveToApprovalState(driver);
	if( driver.findElement(By.xpath("//button[text()='Follow Up']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Update CI']")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
	
		}
	
	
}
@Test(priority=2,description="Approval of chnage ticket and Moving the Change to Multiple phase",enabled=true)
public void testUpdateCIErrorMessage() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createEmerChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
    ChangeReusables.schedule(driver);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangeReusables.moveToApprovalState(driver);
	/*if( driver.findElement(By.xpath("//button[text()='Follow Up']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Update CI']")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
	
		}*/	
	ChangePage.getUpdateCIBtn(driver).click();
	String e=driver.switchTo().alert().getText();
	System.out.println(e);
	if(e.equalsIgnoreCase("Updates to the CI's will cause approvals to be reset.  Select OK to Continue or Cancel to remain in the current Change State."));
	{
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
	}

	
}

@Test(priority=2,description="Approval of chnage ticket and Moving the Change to Multiple phase",enabled=true)
public void testFollowUpListView() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createEmerChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
    ChangeReusables.schedule(driver);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangeReusables.moveToApprovalState(driver);
	ChangePage.getFollowUpBtn(driver).click();
    Thread.sleep(5000);
	String actualTitle = driver.getTitle();
	System.out.println(actualTitle);
	String expectedTitle = "Change Requests | ServiceNow";
	assertEquals(expectedTitle,actualTitle);
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
	
}
@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
public void testFollowUpPlanningState() throws IOException, InterruptedException{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
    ChangeReusables.schedule(driver);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangeReusables.moveToApprovalState(driver);
	ChangePage.getFollowUpBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
    //String state=ChangePage.getStateField(driver).getAttribute("value");
    System.out.println(ChangePage.getStateField(driver).getText());
	//ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	//driver.close();
	//Assert.assertTrue(true);
	//logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
}


}