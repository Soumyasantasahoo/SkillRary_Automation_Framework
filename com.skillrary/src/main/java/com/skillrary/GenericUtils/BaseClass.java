package com.skillrary.GenericUtils;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver staticDriver;
	public ExcelUtility eUtil=new ExcelUtility();
	public FileUtility fUtil=new FileUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public DataBaseUtilities dblib = new DataBaseUtilities();
	
	@BeforeSuite
	public void configBS() throws Throwable {
		//connect to DB
		dblib.connectToDB();
	}
	
	@BeforeTest
	public void configBT() {
		//launch browser in parallel mode
	}
	
	//@Parameters("browser")
	@BeforeClass
	public void configBC() throws Throwable {
		
		String browserName=fUtil.getPropertyKeyValue("browser");
		
		if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("IE")) {
			driver=new InternetExplorerDriver();
		}
		staticDriver=driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	@BeforeMethod
	public void configBM() throws Throwable {
		
	}
	
	
	
	@AfterMethod
	public void configAM() throws Throwable {
		
	}
	
	
	
	@AfterClass
	public void configAC() {
		driver.quit();
	}
	
	@AfterTest
	public void configAT() {
		//close driver ref in parallel mode
	}
	
	@AfterSuite
	public void configAS() throws Throwable {
		// close DB connection
		dblib.closeDb();
	}


}
