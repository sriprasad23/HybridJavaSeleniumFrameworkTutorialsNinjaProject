package Base.com.tnp.qa;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;

import Utils.com.tnp.qa.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public Base()   // Base Constructor
	{
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\Configuration\\com\\tnp\\qa\\Config.properties");
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch (Throwable e)
		{
			e.printStackTrace();
		}
		
		dataprop = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\TestData\\com\\tnp\\qa\\testData.properties");
		try {
		FileInputStream datafis = new FileInputStream(dataPropFile);
		dataprop.load(datafis);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndOPenApplicationURL(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("safari")) 
		{
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;

	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
