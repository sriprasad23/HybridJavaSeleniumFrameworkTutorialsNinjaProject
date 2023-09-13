package PageObjects.com.tnp.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
	WebDriver driver;
	
	@FindBy (xpath = "//div[@id='content']/h1")
	private WebElement accountSuccessPageHeading;
	
	public AccountSuccessPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Action for Assertion of Account Success
	
	public String retriveaccountSuccessPageHeading()
	{
		String successheading =accountSuccessPageHeading.getText();
		return successheading;
	}
}
