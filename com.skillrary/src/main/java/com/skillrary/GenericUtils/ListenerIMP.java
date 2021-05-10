package com.skillrary.GenericUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.qameta.allure.Attachment;

public class ListenerIMP extends BaseClass implements ITestListener {
	@Attachment
	public byte[] saveFailureScreenshot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	ExtentReports reports;
	ExtentTest test;
	 @Override		
	    public void onFinish(ITestContext arg0) {					
	       //After Suite			
	        reports.flush();		
	    }		

	    @Override		
	    public void onStart(ITestContext arg0) {					
	        // BeforeSuite
	    	
	    	ExtentSparkReporter htmlReporter=new ExtentSparkReporter(IPathConstant.htmlPath);
	    	htmlReporter.config().setDocumentTitle("VTiger Application");
	    	htmlReporter.config().setReportName("RegressionTest");
	    	htmlReporter.config().setTheme(Theme.DARK);
	    	reports=new ExtentReports();
	    	reports.attachReporter(htmlReporter);
	    	reports.setSystemInfo("OS", "Windows 10");
	    	reports.setSystemInfo("Platform", "Windows");
	    	reports.setSystemInfo("Reporter Name", "Nithesh H S");
	        		
	    }		

	    @Override		
	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestFailure(ITestResult result) {					
	        test.log(Status.FAIL, result.getMethod().getMethodName()+" is Failed");
	        test.log(Status.FAIL, result.getThrowable());
	        String path=null;
	        try {
				path=wUtil.takeScreenshot(BaseClass.staticDriver, result.getMethod().getMethodName());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        test.addScreenCaptureFromPath(path);
	        	saveFailureScreenshot(BaseClass.staticDriver);	
	    }		

	    @Override		
	    public void onTestSkipped(ITestResult result) {					
	        test.log(Status.SKIP, result.getMethod().getMethodName()+" is Skipped");
	        test.log(Status.SKIP, result.getThrowable());
	        		
	    }		

	    @Override		
	    public void onTestStart(ITestResult result) {	
	    	//@test method
	    	 test=reports.createTest(result.getMethod().getMethodName());
	        		
	    }		

	    @Override		
	    public void onTestSuccess(ITestResult result) {					
	        				
	        test.log(Status.PASS, result.getMethod().getMethodName()+" is passed");		
	    }		

}
