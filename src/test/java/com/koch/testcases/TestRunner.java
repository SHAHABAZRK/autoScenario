package com.koch.testcases;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.koch.base.BaseTest;
import com.koch.test.pages.FlightsPage;
import com.koch.test.pages.FlightsResultPage;
import com.koch.test.pages.HomePage;

public class TestRunner extends BaseTest{	
	HomePage homePage;
	FlightsPage flightsPage;
	FlightsResultPage flightsResultPage;
	
	
	
	@BeforeClass
	//Launch App
	public void launchApplication() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\shaha.rk\\Desktop\\Java DS and Algorithms\\Test\\koch_test\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get(repo.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	//Test Scenario
	public void getDepartureAirlineDetailsWithFareLessthan5000() throws InterruptedException{
		//homePage = PageFactory.initElements(driver, HomePage.class);
		// Validation of Home page
		//flightsPage = homePage.validateHomePage(getTestData("homePageTitle"));
		flightsPage = PageFactory.initElements(driver, FlightsPage.class);
		//Enter places and search for flights
		flightsResultPage = flightsPage.FlightSearch(getTestData("navigationType"), getTestData("from"), getTestData("to"), getTestData("departuredate"),
				getTestData("returndate"), getTestData("travellers"), getTestData("c_lass"));
		
		//Validation of Sorts options
		flightsResultPage.validateFiltersInPage(getTestData("expectedSortOptions"));
		//Validation of Departure options
		flightsResultPage.validateFiltersInPage(getTestData("expectedDepartureOptions"));
		//Validation of Airlines options
		flightsResultPage.validateFiltersInPage(getTestData("expectedAirlineOptions"));
		
		//Print the list of airlines details having fare < 5000
		Reporter.log("Below are the departure flight details from "+getTestData("from")+" to "+getTestData("to"));
		flightsResultPage.getAirlineDetails(getTestData("stopsFilterOption"), getTestData("departureFilterOption"), getTestData("airlineFilterOption"), getTestData("fareLimit"));
	}
	
	@AfterClass
	//Close browser
	public void closeBrowser() {
		driver.close();
	}
}
