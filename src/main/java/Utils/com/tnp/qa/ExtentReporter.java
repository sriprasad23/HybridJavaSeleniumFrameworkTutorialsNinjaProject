package Utils.com.tnp.qa;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentreport = new ExtentReports(); 
		File extentreportfile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentreportfile);
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("Hybrid Framework Java Selenium Tutorials Ninja Test Automation Results Report");
		sparkreporter.config().setDocumentTitle("Tutorial Ninja Project Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentreport.attachReporter(sparkreporter);
		
		Properties configprop = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\Configuration\\com\\tnp\\qa\\Config.properties");
		try {
		FileInputStream fisconfigprop = new FileInputStream(configPropFile);
		configprop.load(fisconfigprop);
		} catch(Throwable e)
		{
			e.printStackTrace();
		}
		extentreport.setSystemInfo("Application URL", configprop.getProperty("url"));
		extentreport.setSystemInfo("Browser Name", configprop.getProperty("browserChrome"));
		extentreport.setSystemInfo("Email", configprop.getProperty("validEmail"));
		extentreport.setSystemInfo("Password", configprop.getProperty("validPassword"));
		extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreport.setSystemInfo("Java Version", System.getProperty("java.vm.specification.version"));
	//	extentreport.setSystemInfo("Sriprasad R", System.getProperty("user.name"));
		extentreport.setSystemInfo(System.getProperty("user.name"),"Sriprasad R");
		
		return extentreport;
		
	}

}
