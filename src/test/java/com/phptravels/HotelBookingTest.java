package com.phptravels;

import org.testng.annotations.Test;

import pageClasses.HotelsPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageClasses.HotelsPage;
import utilities.Screenshots;

public class HotelBookingTest {

	WebDriver driver;
	HotelsPage hp;
	ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Timmy\\workspace\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		report = new ExtentReports("C:\\Users\\Timmy\\Desktop\\UDEMY\\Selenium 2\\PHP-Travels\\hotelBooking.html",false);
		test = report.startTest("Verify Hotel Booking");
		hp = new HotelsPage(driver, test);
		String baseUrl = "http://phptravels.net/";
		driver.get(baseUrl);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}
	
	@DataProvider(name = "fieldsInput")
	public Object [][] searchData() {
	
		return new Object[][] {
								{"Dubai","12","23"},
								{"singapore","13","24"}
//								,
//								{"London","14","25"},
//								{"Islama","12","23"},
//								{"Manches","10","20"}
								
							};
	}
	
	

	
	@Parameters({ "city", "checkIn", "checkOut" })
	@Test
	public void parametersTest(String city, String checkIn, String checkOut) throws Exception {
		hp.HOTELS_Tab.click();
		 //driver.findElement(By.xpath(".//*[@id='s2id_hotelicon']/a/span[2]/b")).click();

		hp.clear();
		test.log(LogStatus.INFO, "Cleared all fields");
		
		hp.fillInLocation( city);
		test.log(LogStatus.INFO, "Entered location...");
		
		
		hp.setDates(checkIn, checkOut);
		
		hp.clickSearchButton();


	}
	
	
	

	@Test(dataProvider = "fieldsInput")
	public void testWithMultipleData(String city, String checkIn, String checkOut) throws Exception {
		hp.HOTELS_Tab.click();
		 //driver.findElement(By.xpath(".//*[@id='s2id_hotelicon']/a/span[2]/b")).click();

		hp.clear();
		test.log(LogStatus.INFO, "Cleared all fields");
		
		hp.fillInLocation( city);
		test.log(LogStatus.INFO, "Entered location...");
		
		
		hp.setDates(checkIn, checkOut);
		
		hp.clickSearchButton();

	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) throws IOException{
		
		if (testResult.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.INFO, "Hotel booking failed...");
			String path =  Screenshots.takeScreenshot(driver, "bookingFailed", "C:\\Users\\Timmy\\Desktop\\UDEMY\\Selenium 2\\PHP-Travels\\Screenshots\\hotelBooking\\");
			String imagePath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Hotel booking failed...",imagePath);
			
		  }else{
			  test.log(LogStatus.INFO, "Hotel booking failed...");
				String path =  Screenshots.takeScreenshot(driver, "bookingPassed", "C:\\Users\\Timmy\\Desktop\\UDEMY\\Selenium 2\\PHP-Travels\\Screenshots\\hotelBooking\\");
				String imagePath = test.addScreenCapture(path);
				test.log(LogStatus.PASS, "Hotel booking successful...",imagePath);
			  
		  }
		  
	}
	
	@AfterClass
	public void afterClass() {
		
		driver.close();
		report.endTest(test);
		report.flush();
	}

}
