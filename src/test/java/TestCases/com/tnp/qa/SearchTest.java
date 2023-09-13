package TestCases.com.tnp.qa;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.com.tnp.qa.Base;
import PageObjects.com.tnp.qa.HomePage;
import PageObjects.com.tnp.qa.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchpage;
	
	public SearchTest()
	{
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		driver =initializeBrowserAndOPenApplicationURL(prop.getProperty("browserChrome"));
		
	}
	
	@Test(priority =1)
	public void verifySearchWithValidProduct()
	{
		HomePage homepage = new HomePage(driver);
		homepage.enterTextOnSearchBoxField(dataprop.getProperty("validProductSearch"));
		searchpage = homepage.clickOnSearchButton();
	
		Assert.assertTrue(searchpage.searchForValidProduct(),"Validate search product is not displayed");
	}

	@Test(priority =2)
	public void verifySearchWithInValidProduct()
	{
		HomePage homepage = new HomePage(driver);
		
		homepage.enterTextOnSearchBoxField(dataprop.getProperty("invalidProductSearch"));
		searchpage =homepage.clickOnSearchButton();
		
		String actualMessage = searchpage.retriveNoProductTextMessage();
	//	Assert.assertEquals(actualMessage, dataprop.getProperty("productWarningMessage"), "The Search is not Displayed");
		Assert.assertEquals(actualMessage, "abcd1234", "The Search is not Displayed");
	}

	@Test(priority =3, dependsOnMethods = {"verifySearchWithInValidProduct"})
	public void verifySearchWithoutAnyProduct()
	{
		HomePage homepage = new HomePage(driver);
		searchpage =homepage.clickOnSearchButton();
				
		String actualMessage = searchpage.retriverblankInSearchTextFieldClickSearchButton();
		Assert.assertEquals(actualMessage, dataprop.getProperty("productWarningMessage"), "The Search is not Displayed");
	
	}
}
