package com.automation.blazedemo.utils;

import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Constants {
	
	
	
	public static final String PROJECTPATH = System.getProperty("user.dir");
	public static final String RESOURCEPATH = PROJECTPATH + File.separator + "src" + File.separator + "test"
			+ File.separator + "resources" + File.separator;

	// Property file
	public static final String propertyFilePath = RESOURCEPATH + "TestRunDetails.properties";

	// ExcelSheet file path - Data Handling
	public static final String DataSheetFileName = RESOURCEPATH + "TestData.xlsx";
	  /** The current path of the project */
    public static final String CURRENT_DIR = determineCurrentPath();
	 public static final String DIR_SEPARATOR = File.separator;
	 public static final String SCREENSHOT_FOLDER = CURRENT_DIR + "selenium-reports" + DIR_SEPARATOR + "html" + DIR_SEPARATOR + "screenshots";
	    public static final String SCREENSHOT_FOLDERS_NAME = "selenium-reports" + DIR_SEPARATOR + "html" + DIR_SEPARATOR + "screenshots";
	
	// Wait timing
	//Duration waitTime = Duration.ofMillis(13000);
	public static final long WAITTIME = Duration.of(5,ChronoUnit.SECONDS).toMillis();
	
	// Screenshot paths
	public static final String screenShotPath = PROJECTPATH + File.separator + "screenShots" + File.separator;
	public static final String testCaseScreenShotPath(String testCaseName) {
		return screenShotPath + testCaseName + File.separator + System.currentTimeMillis() + new Random().nextInt(20) + ".png";
	}
	public static final String testCaseScreenShotPath(String testCaseName, String selectedPath) {
		return selectedPath + File.separator + "screenShots" + File.separator + testCaseName + File.separator
				+ System.currentTimeMillis() + new Random().nextInt(20) + ".png";
	}

	// Extent reporting
	public static final String extentreportPath = PROJECTPATH + File.separator + "report" + File.separator;
	public static final String editExtentreportPath = extentreportPath + "Extent-TestReport.html";
	public static final String extentConfigPath = RESOURCEPATH + "extentreport.xml";
	public static final String newExtentReportPath(String currentDate) {
		return extentreportPath + "Extent-TestReport-" + currentDate + ".html";
	}
	public static final String REPORTTITLE = "Test Report";
	public static final String AUTHOR = "";
	public static final String ENVIRONMENT = "QA";	
	public static final String YES = "yes";
	
	public static  boolean TESTVALDATIONFLAG =false ;
	 final private static String determineCurrentPath() {
	        try {
	            return (new File(".").getCanonicalPath()) + Constants.DIR_SEPARATOR;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return "." + Constants.DIR_SEPARATOR;
	    }
}
