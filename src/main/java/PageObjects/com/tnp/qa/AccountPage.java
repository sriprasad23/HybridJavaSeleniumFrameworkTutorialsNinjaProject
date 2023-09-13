package PageObjects.com.tnp.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	@FindBy (linkText =  "Edit your account information")
	private WebElement editYourAccountInformation;
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// Action for Account page
	
	public boolean getDisplayStatusofEditAccountInformationStatus()  // validateLoginWithValidCredentials method Assertion maped
	{
		boolean displayStatus = editYourAccountInformation.isDisplayed();
		return displayStatus;
	}
	
	
	
	
}
