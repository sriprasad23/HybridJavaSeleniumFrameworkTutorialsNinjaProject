package TestCases.com.tnp.qa;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.com.tnp.qa.Base;
import PageObjects.com.tnp.qa.AccountSuccessPage;
import PageObjects.com.tnp.qa.HomePage;
import PageObjects.com.tnp.qa.RegisterPage;
import Utils.com.tnp.qa.Utilities;

public class RegistrationTest extends Base {
	
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	
	public RegistrationTest()
	{
		super();
	}

	public WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		driver =initializeBrowserAndOPenApplicationURL(prop.getProperty("browserChrome"));
		
		HomePage homepage= new HomePage(driver);
		homepage.clickOnMyAccount();
		registerpage = homepage.clickOnRegisterOption();	
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccouuntWithMandatoryFields() throws InterruptedException
	{
	
		
		registerpage.enterFirstnameField(dataprop.getProperty("firstName"));
		registerpage.enterLastnameField(dataprop.getProperty("lastName"));
		registerpage.enterEmailAddressield(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephoneNoField(dataprop.getProperty("telephoneNo"));
		registerpage.enterPasswordoField(prop.getProperty("validPassword"));
		registerpage.enterConfirmPasswordoField(prop.getProperty("validPassword"));
		registerpage.checkprivacyPolicyCheckBoxField();
		accountsuccesspage = registerpage.clickOnContinueButton();
		String ActualAccountSuccessMessage = accountsuccesspage.retriveaccountSuccessPageHeading();
		Assert.assertEquals(ActualAccountSuccessMessage,dataprop.getProperty("AccountSuccessMessage"),"Expected Account Success Message Not Displayed");
	}
	
	@Test(priority=2)
	public void verifyRegisteringAnAccouuntWithAllFields() throws InterruptedException
	{
		
		registerpage.enterFirstnameField(dataprop.getProperty("firstName"));
		registerpage.enterLastnameField(dataprop.getProperty("lastName"));
		registerpage.enterEmailAddressield(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephoneNoField(dataprop.getProperty("telephoneNo"));
		registerpage.enterPasswordoField(prop.getProperty("validPassword"));
		registerpage.enterConfirmPasswordoField(prop.getProperty("validPassword"));
		registerpage.selectSubscribeNewLetterField();
		registerpage.checkprivacyPolicyCheckBoxField();
		accountsuccesspage = registerpage.clickOnContinueButton();
				
		String ActualAccountSuccessMessage = accountsuccesspage.retriveaccountSuccessPageHeading();
		Assert.assertEquals(ActualAccountSuccessMessage,dataprop.getProperty("AccountSuccessMessage"),"Expected Account Success Message Not Displayed");
	}

	@Test(priority=3)
	public void verifyRegisteringWithExisistingEmailCredentials() throws InterruptedException
	{

		registerpage.enterFirstnameField(dataprop.getProperty("firstName"));
		registerpage.enterLastnameField(dataprop.getProperty("lastName"));
		registerpage.enterEmailAddressield(prop.getProperty("validEmail"));
		registerpage.enterTelephoneNoField(dataprop.getProperty("telephoneNo"));
		registerpage.enterPasswordoField(prop.getProperty("validPassword"));
		registerpage.enterConfirmPasswordoField(prop.getProperty("validPassword"));
		registerpage.selectSubscribeNewLetterField();
		registerpage.checkprivacyPolicyCheckBoxField();
		accountsuccesspage=registerpage.clickOnContinueButton();

		String ActualExisistingAccount = registerpage.retriveAccountExisisting();
		Assert.assertTrue(ActualExisistingAccount.contains(dataprop.getProperty("ExisistingAccount")), "Warning Message: The Email given is already Exisited ");	
	}

	@Test(priority=4)
	public void verifyRegisteringWithBlankCredentials() throws InterruptedException
	{

		accountsuccesspage=registerpage.clickOnContinueButton();
		
		String actualMessageForPrivacyPolicy= registerpage.retriveprivacyPolicyWarning();
		Assert.assertTrue(actualMessageForPrivacyPolicy.contains(dataprop.getProperty("checkPrivacyPolicy")),"Privacy policy warning Message is not displayed");
		
		
				
		String actualFirstNameWarning = registerpage.retriveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning, dataprop.getProperty("FirstNameWarning"),"First Name Warning Message is Not Displayed");
		
		String actualLastNameWarning = registerpage.retriveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning, dataprop.getProperty("LastNameWarning"),"Last Name Warning Message is Not Displayed");
				
		String actualEmailWarning = registerpage.retriveEmailWarningWarning();
		Assert.assertEquals(actualEmailWarning, dataprop.getProperty("EmailWarning"),"Email Warning Message is Not Displayed");
				
		String actualTelephoneWarning = registerpage.retriveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning, dataprop.getProperty("TelephoneWarning"),"Telephone Warning Message is Not Displayed");
				
		String actualPasswordWarning = registerpage.retrivePasswordWarning();
		Assert.assertEquals(actualPasswordWarning, dataprop.getProperty("PasswordWarning"),"Password Warning Message is Not Displayed");
				
	//	String actualConfirmPasswordWarning = driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText();
	//	Assert.assertEquals(actualConfirmPasswordWarning, "Password confirmation does not match password!","Confirm Passwordx Warning Message is Not Displayed");		
	}
}
