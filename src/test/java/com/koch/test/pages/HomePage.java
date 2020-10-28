package com.koch.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class HomePage extends BasePage{

	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}
	
		// Get the Logo
		@FindBy(xpath="//title")
		private WebElement title;
		
		// User Icon
		@FindBy(className="user-login ixi-icon-user")
		private WebElement user;
		
		public FlightsPage validateHomePage(String expectedPageTitleName) {
			
			Assert.assertEquals(title.getText(), expectedPageTitleName);
			return PageFactory.initElements(driver, FlightsPage.class);
		}
}