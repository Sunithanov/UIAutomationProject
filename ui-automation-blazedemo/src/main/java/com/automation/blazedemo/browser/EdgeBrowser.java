package com.automation.blazedemo.browser;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Represents a Edge Browser related activities, such as
 * 1. Create the Edge driver
 * 2. Set the project related settings for edge driver
 * 
 * 
*/
public class EdgeBrowser extends BrowserManager{
	
	/**
	 * This method helps to setup the Edge driver for Testing.
	 * @return driver -> It returns the Edge type WebDriver.
	 */
	@Override
	public WebDriver createDriver(){
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		return driver;
	}
}