package PageObjects.com.tnp.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy (linkText = "HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy (xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	@FindBy (xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement blankInSearchTextFieldClickSearchButton;
	
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Actio for Search
	
	public boolean searchForValidProduct()
	{
		boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retriveNoProductTextMessage()
	{
		String noProductTextMessage = noProductMessage.getText();
		return noProductTextMessage;
	}
	
	public String retriverblankInSearchTextFieldClickSearchButton()
	{
		String blankSearch = blankInSearchTextFieldClickSearchButton.getText();
		return blankSearch;
	}
}
