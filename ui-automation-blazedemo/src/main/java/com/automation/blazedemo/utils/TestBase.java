package com.automation.blazedemo.utils;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.blazedemo.browser.Driver;
import com.automation.blazedemo.reports.TestReport;
import com.automation.blazedemo.utils.SuiteConfiguration;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

	/** Initialization process for report. */
	@BeforeSuite
	public void beforeSuite() {
		TestReport.initialize();
	}

	/**
	 * Set up process such us browser initialization and open.
	 * @param browserName -> browser name from xml file
	 */
	@Parameters("browserName")
	@BeforeMethod
	public void setUp(@Optional("chrome")String browserName) {
			Driver.initialize(browserName);
	}

	/** Wrapup process such as closing and quieting the browser. */
	@AfterMethod
	public void wrapUp() {
		Driver.quit();
	}
	
	/**
	 * The afterSuite method clear the extent report.
	 */
	@AfterSuite
	public void afterSuite() {
		TestReport.report.flush();
		//GenericUtil.openReport();
	}
	
	/**
	 * The dataprovider method collects data from ExcelSheet.
	 * @return testRecords -> It returns 2D string object which contains data
	 *         from ExcelSheet.
	 */
	@DataProvider(name = "signupDataProvider")
	public String[][] signupDataProvider() {
		ExcelSheetHelper helper = new ExcelSheetHelper();
		String[][] testRecords = helper.getDataFromSheet("SignUpTest");
		return testRecords;
	}
}
