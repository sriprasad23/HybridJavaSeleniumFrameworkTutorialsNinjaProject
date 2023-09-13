package Listeners.com.tnp.qa;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.com.tnp.qa.ExtentReporter;
import Utils.com.tnp.qa.Utilities;

public class FrameWorkListeners implements ITestListener{

	ExtentReports extentreport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
	
		extentreport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
	
		 extentTest = extentreport.createTest(result.getName());
		 extentTest.log(Status.INFO, result.getName()+"  Started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS,result.getName()+"  got Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null ;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+"  got Failed"); 
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got Skipped"); 
	}


	@Override
	public void onFinish(ITestContext context) {
		
		extentreport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); // to open report automatically on browser after test Execution complete
		} catch (IOException e) {
					e.printStackTrace();
		}

	}
	
}
