package PageObjects.com.tnp.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	// Objects of Register Page
	
	@FindBy (id = "input-firstname")
	private WebElement firstNameField;
		
	@FindBy (id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy (id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy (id = "input-telephone")
	private WebElement telephoneNoField;
	
	@FindBy (id = "input-password")
	private WebElement passwordField;
	
	@FindBy (id = "input-confirm")
	private WebElement confirmpasswordField;
	
	@FindBy (xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement subscribeYesNewLetter;
	
	@FindBy (name = "agree")
	private WebElement privacyPolicyCheckboxField;
	
	@FindBy (xpath = "//input[@value='Continue']")
	private WebElement clickContinue;
	
	@FindBy (xpath = "//div[contains(@class,'alert-dismissible')]")                       // S
	private WebElement privacyPolicyWarningField;
	
	@FindBy (xpath = "//input[@id='input-firstname']/following-sibling::div") 
	private WebElement FirstNameWarningField;
	
	@FindBy (xpath = "//input[@id='input-lastname']/following-sibling::div") 
	private WebElement LastNameWarningField;
	
	@FindBy (xpath = "//input[@id='input-email']/following-sibling::div") 
	private WebElement EmailWarningField;
	
	@FindBy (xpath = "//input[@id='input-telephone']/following-sibling::div") 
	private WebElement TelephoneWarningField;
	
	@FindBy (xpath = "//input[@id='input-password']/following-sibling::div") 
	private WebElement PasswordWarningField;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement ExisistingAccount;
	
	// Actions for Register Page
	
	public void enterFirstnameField(String firstName)
	{
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastnameField(String lasttName)
	{
		lastNameField.sendKeys(lasttName);
	}
	
	public void enterEmailAddressield(String email)
	{
		emailAddressField.sendKeys(email);
	}
	
	public void enterTelephoneNoField(String telephoneNo)
	{
		telephoneNoField.sendKeys(telephoneNo);
	}
	
	public void enterPasswordoField(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public void enterConfirmPasswordoField(String confirmpassword)
	{
		confirmpasswordField.sendKeys(confirmpassword);
	}
	
	public void selectSubscribeNewLetterField()
	{
		subscribeYesNewLetter.click();
	}
	
	public void checkprivacyPolicyCheckBoxField()
	{
		privacyPolicyCheckboxField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton()
	{
		clickContinue.click();
		return new AccountSuccessPage(driver);
	}
	
	public String retriveAccountExisisting()
	{
		String accountAlreadyExisist =ExisistingAccount.getText();
		return accountAlreadyExisist;
	}
	
	public String retriveprivacyPolicyWarning()
	{
		String privacyPolicyWarning=privacyPolicyWarningField.getText();
		return privacyPolicyWarning;
	}
	
	public String retriveFirstNameWarning()
	{
		String FirstNameWarning=FirstNameWarningField.getText();
		return FirstNameWarning;
	}
	
	public String retriveLastNameWarning()
	{
		String LastNameWarning=LastNameWarningField.getText();
		return LastNameWarning;
	}
	
	public String retriveEmailWarningWarning()
	{
		String EmailWarningWarning=EmailWarningField.getText();
		return EmailWarningWarning;
	}
	
	public String retriveTelephoneWarning()
	{
		String TelephoneWarning=TelephoneWarningField.getText();
		return TelephoneWarning;
	}
	
	public String retrivePasswordWarning()
	{
		String PasswordWarning=PasswordWarningField.getText();
		return PasswordWarning;
	}
}
