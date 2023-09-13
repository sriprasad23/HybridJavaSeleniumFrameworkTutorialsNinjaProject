package TestCases.com.tnp.qa;
/*
 Notes: 
 1. Declare WebDriver driver as in global level by that no need to instantiate WebDriver in every test can access driver instance
 2. creat tearDown class with @AfterMethod so that will close browsers instances once test is complited even Assertion Fails this class use to close browsers
 3. for gmail with timestamp generation under Utils package class is created and make it as static we can access anywhere no need to extend the class in child class  
 */


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.com.tnp.qa.Base;
import PageObjects.com.tnp.qa.AccountPage;
import PageObjects.com.tnp.qa.HomePage;
import PageObjects.com.tnp.qa.LoginPage;
import Utils.com.tnp.qa.Utilities;

public class LoginTest extends Base {

	LoginPage loginpage;   // As globalVariable
	
	public LoginTest()
	{
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		
		driver = initializeBrowserAndOPenApplicationURL(prop.getProperty("browserChrome"));
		HomePage homepage= new HomePage(driver);
		homepage.clickOnMyAccount();
		loginpage = homepage.clickOnLoginOption(); // in HomePage LoginPage returned and kept as global variable
	}
	
	// This 1st Method is use to drive data from properties file 
	
	/*@Test(priority=0)
	public void validateLoginWithValidCredentials() throws InterruptedException
	{
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}*/
	
	// This 2nd Method applying here to drive data from excel file 
	
	@Test(priority=0,dataProvider="validCredntialsData")
	public void validateLoginWithValidCredentials(String email, String password) throws InterruptedException
	{
												// LoginPage constructor
		loginpage.enterEmailAddress(email);				// POM 
		loginpage.enterPassword(password);
		AccountPage accountPage = loginpage.clickonLoginButton(); // return type is given object of account page
		Assert.assertTrue(accountPage.getDisplayStatusofEditAccountInformationStatus()); // PageObjects - AccountPage its actions declared
	}
	
	// This method is Hard coded from Parameters direct values assigned to the object array
	/*@DataProvider(name="validCredntialsData")
	public Object[][] supplyTestData()
	{
		Object [][]data = {{"sriprasad1@gmail.com", "Dhruva"},{"sriprasad2@gmail.com", "Dhruva"},{"sriprasad3@gmail.com", "Dhruva"}};
		return data;
	}*/
	
	
	@DataProvider(name="validCredntialsData")
	public Object[][] supplyTestData()
	{
		Object [][]data = Utilities.getTestDataFromExcel("Login");  // Read Test data from Excel File
		return data;
	}

	@Test(priority=1)
	public void validateLoginWithInValidCredentials() throws InterruptedException
	{
		
		loginpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		loginpage.clickonLoginButton();
		String actualWarningMessage = loginpage.retriveEmailPasswordNotMatchingWarningMesssageText();
		String expectedWarningMessage= dataprop.getProperty("invalidPasswordNotMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is Not Displayed");
	}
	
	@Test(priority=2)
	public void validateLoginWithinvalidEmailAndValidPassword() throws InterruptedException
	{
		
		loginpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickonLoginButton();
		
		String actualWarningMessage = loginpage.retriveEmailPasswordNotMatchingWarningMesssageText();
		String expectedWarningMessage= dataprop.getProperty("invalidEmailNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is Not Displayed");	
	}
	
	@Test(priority=3)
	public void validateLoginWithvalidEmailAndInValidPassword() throws InterruptedException
	{
		
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		loginpage.clickonLoginButton();		
		String actualWarningMessage = loginpage.retriveEmailPasswordNotMatchingWarningMesssageText();
		String expectedWarningMessage=dataprop.getProperty("invalidPasswordNotMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is Not Displayed");		
	}

	@Test(priority=4)
	public void validateLoginWithBlankEmailAndBlankPassword()
	{
		
		loginpage.clickonLoginButton();	
		String actualWarningMessage = loginpage.retriveEmailPasswordNotMatchingWarningMesssageText();
		String expectedWarningMessage=dataprop.getProperty("invalidPasswordNotMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is Not Displayed");	
	}
}
 