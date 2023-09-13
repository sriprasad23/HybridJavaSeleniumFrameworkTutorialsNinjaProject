package PageObjects.com.tnp.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;  				// Instantiate driver with WebDriver
	
	// Objects for HomePage
	
	@FindBy (xpath = "//span[text()='My Account']") 
	private WebElement myAccountDropMenu;
	
	@FindBy (linkText = "Login" )
	private WebElement loginOption;
	
	@FindBy (linkText = "Register" )
	private WebElement RegisterOption;
	
	@FindBy (name = "search" )
	private WebElement searchBoxField;
	
	@FindBy (xpath = "//div[@id='search']/descendant::button")
	private WebElement SearchButton;
	

	public HomePage(WebDriver driver) 	// Constructor for Homepage 
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	// Actions for the Objects for HomePaage WebElements
	
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}
	
	public LoginPage clickOnLoginOption()
	{
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRegisterOption()
	{
		RegisterOption.click();	
		return new RegisterPage(driver);
	}
	
	public void enterTextOnSearchBoxField(String productText)
	{
		searchBoxField.sendKeys(productText);	
	}
	
	public SearchPage clickOnSearchButton()
	{
		SearchButton.click();
		return new SearchPage(driver);
	}
}
