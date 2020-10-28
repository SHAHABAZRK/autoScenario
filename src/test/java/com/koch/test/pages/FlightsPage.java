package com.koch.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FlightsPage extends BasePage{
	private WebDriver driver;
	
	public FlightsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}
	
	// Flights option
	@FindBy(xpath="//nav[@class='nav-list']//span//a[@href='/flights']")
	private WebElement navFlightOption;
	
	// From
	@FindBy(xpath="//div[text()='From']/../input")
	private WebElement from;
	
	// To
	@FindBy(xpath="//div[text()='To']/../input")
	private WebElement to;
	
	// Departure
	@FindBy(xpath="//input[@placeholder='Depart']")
	private WebElement departure;
	
	// Return
	@FindBy(xpath="//input[@placeholder='Return']")
	private WebElement r_eturn;
	
	// Travellers | Class
	@FindBy(xpath="//div[text()='Travellers | Class']/../input")
	private WebElement travellersClass;
	
	// Search button
	@FindBy(xpath="//button[text()='Search']")
	private WebElement searchBtn;
	
	public FlightsResultPage FlightSearch(String navigationType, String from, String to, String departuredate, String returndate, String travellers, String c_lass) throws InterruptedException {
		
		navFlightOption.click();

		WebDriverWait wait = new WebDriverWait(driver , 60);
		WebElement trip = driver.findElement(By.xpath("//span[text()='"+navigationType+"']"));
		wait.until(ExpectedConditions.visibilityOf(trip));
		trip.click();
		this.from.sendKeys(Keys.CONTROL+"a");
		this.from.sendKeys(from);
		selectInFromDropdown(driver, from);
		this.from.sendKeys(Keys.CONTROL+"a");
		this.to.sendKeys(to);
		selectInToDropdown(driver, to);
		this.departure.click();
		driver.findElement(By.xpath("//table[@class='rd-days']//tr//td[@data-date='"+departuredate+"']//div[1]")).click();
		this.r_eturn.click();
		WebElement element = driver.findElement(By.xpath("(//table[@class='rd-days']//tr//td[@data-date='"+returndate+"']//div[1])[2]"));
		element.click();
		this.travellersClass.click();
		driver.findElement(By.xpath("//div[text()='Adult']/../..//span[@data-val='"+travellers+"']")).click();
		driver.findElement(By.xpath("//span[text()='"+c_lass+"']/../span[contains(@class,'radio-button')]")).click();
		this.searchBtn.click();
		
		return PageFactory.initElements(driver, FlightsResultPage.class);
	}
}
