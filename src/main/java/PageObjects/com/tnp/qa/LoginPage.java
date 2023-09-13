package PageObjects.com.tnp.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	// Objects of Login Page
	
	@FindBy (id = "input-email" )
	private WebElement emailAddressField;
	
	@FindBy (id = "input-password" )
	private WebElement enterPasswordField;
	
	@FindBy (xpath = "//input[@class='btn btn-primary']" )
	private WebElement clickLoginButton;
	
	@FindBy (xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver)   			// Constructor for LoginPage to load all webelements to variable
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);   // instantiate All 
	}
	
	
	// Actions of Login Page
	
	public void enterEmailAddress(String emailText)
	{ 
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword(String password)
	{
		enterPasswordField.sendKeys(password);
	}
	
	public AccountPage clickonLoginButton()
	{ 
		clickLoginButton.click();
		return new AccountPage(driver);
	}
	
	public String retriveEmailPasswordNotMatchingWarningMesssageText()
	{
		String EmailPasswordWarningText = emailPasswordNotMatchingWarning.getText();
		return EmailPasswordWarningText;
	}

}
