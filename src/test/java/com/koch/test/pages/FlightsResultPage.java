package com.koch.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FlightsResultPage extends BasePage{
	private WebDriver driver;
	
	public FlightsResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}
	// Get the page type
	@FindBy(xpath="//body//div[@id='content']//div[contains(@class,'flight-listing-page')]")
	private WebElement pageType;
			
	// Get the Non stop checkbox
	@FindBy(xpath="//div[text()='Stops']/..//div[@class='fltr-cntnt']//div[text()='Non stop']/../..//span[contains(@class,'checkbox-button')]")
	private WebElement nonstopCheckbox;
	
	// Get the 1 stop checkbox
	@FindBy(xpath="//div[text()='Stops']/..//div[@class='fltr-cntnt']//div[text()='1 stop']/../..//span[contains(@class,'checkbox-button')]")
	private WebElement onestopCheckbox;
		
	// Get the 1+ stop checkbox
		@FindBy(xpath="//div[text()='Stops']/..//div[@class='fltr-cntnt']//div[text()='1+ stops']/../..//span[contains(@class,'checkbox-button')]")
		private WebElement oneplusstopCheckbox;	
	
	public FlightsResultPage validateFiltersInPage(String options) {
		WebDriverWait wait = new WebDriverWait(driver , 60);
		String[] opts = options.split(",");
		WebElement element = driver.findElement(By.xpath("//div[@class='fltrs-cntnr']//div[text()='Stops']"));
		wait.until(ExpectedConditions.visibilityOf(element)); 
		for (int i = 0; i < opts.length; i++) {
			validateFilterOptionExist(driver,opts[i]);
		}
		return PageFactory.initElements(driver, FlightsResultPage.class);
	}
	
	public FlightsResultPage getAirlineDetails(String stopsFilterOption, String departureFilterOption, String airlineFilterOption, String fare) {	
		if(stopsFilterOption != "" && stopsFilterOption != null) {
			if(stopsFilterOption.equalsIgnoreCase("Non stop"))
				nonstopCheckbox.click();
			else if(stopsFilterOption.equalsIgnoreCase("1 stop"))
				onestopCheckbox.click();
			else if(stopsFilterOption.equalsIgnoreCase("1+ stop"))
				oneplusstopCheckbox.click();
		}
		List<WebElement> le = driver.findElements(By.xpath("//div[@class='result-wrpr']//div[@class='result-col outr']//div[@class='summary-section']"));
		for (WebElement we: le) {
			double price = Double.parseDouble(we.findElement(By.xpath("//div[@class='price-group']//div[@class='price']//span[@class='icon']//following-sibling::span")).getText());
			if(price<5000) {
				String DepartureTime = we.findElement(By.xpath("//div[@class='time-group']//div[@class='time'][1]")).getText();
				String airline = we.findElement(By.xpath("//div[@class='time-group']//div[@class='airline-text']//div[@class='u-text-ellipsis']")).getText();
				airline = airline.replace('\u00A0',' ');
				String[] al = airline.split(" ");
				String airlineNumber = al[2];
				System.out.println("Airline Number: "+airlineNumber+" Departure time: "+DepartureTime+"Fare: "+price);
			}
		}
		return PageFactory.initElements(driver, FlightsResultPage.class);
	}
}
