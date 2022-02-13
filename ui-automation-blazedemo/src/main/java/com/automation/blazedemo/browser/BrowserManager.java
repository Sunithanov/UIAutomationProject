package com.automation.blazedemo.browser;

import org.openqa.selenium.WebDriver;

import com.automation.blazedemo.reports.LogStatus;

/**
 * Browsers Parent abstract class.
 * 
 * @author 
 * 
 */
public abstract class BrowserManager {

	/** WebDriver instance */
	public WebDriver driver;

	/** Create the WebDriver type object based on the browser. */
	public abstract WebDriver createDriver();

	/** This method close and quite the Webdriver. */
	public void quit() {
		if (driver != null) {
			driver.close();
			driver.quit();
			LogStatus.info("Browser is closed successfully.");
		}
	}

}
