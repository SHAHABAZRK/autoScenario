package com.koch.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BasePage {
	
	public void selectInFromDropdown(WebDriver driver, String place) {
		driver.findElement(By.xpath("//div[@class='c-auto-cmpltr']//div[text()='From']/../..//div[@class='city'][contains(text(),'"+place+"')]")).click();
	}
	public void selectInToDropdown(WebDriver driver, String place) {
		driver.findElement(By.xpath("//div[@class='c-auto-cmpltr']//div[text()='To']/../..//div[@class='city'][contains(text(),'"+place+"')]")).click();
	}
	public void validateFilterOptionExist(WebDriver driver, String option) {
		String actualOption = driver.findElement(By.xpath("//div[contains(@class,'fltr-cntnt')]//div[text()='"+option+"']")).getText();
		Assert.assertEquals(actualOption, option);
	}
}
