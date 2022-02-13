package com.automation.blazedemo.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.constants.Constants;
import com.relevantcodes.extentreports.ExtentReports;
import com.automation.blazedemo.constants.FrameworkConstant;
import com.automation.blazedemo.enums.PropertyConfig;
import com.automation.blazedemo.utils.GenericUtil;
import com.automation.blazedemo.utils.PropertyFileHelper;

/**
 * Represents custom modification in the extent report.
 * 
 * @author Sunitha
 * 
 */
public class TestReport {

	public static ExtentReports report = null;
	public static String reportFilePath = "";

	/**
	 * Basic configuration in extent report.
	 * Private access to avoid external initialization.
	 */
	private TestReport() {
		report = new ExtentReports(getReportPath());
		report.loadConfig(new File(FrameworkConstant.extentConfigPath));
		report.addSystemInfo("Testing", FrameworkConstant.reportTitle);
		report.addSystemInfo("Author", FrameworkConstant.author);
		report.addSystemInfo("Environment",FrameworkConstant.environment);
	}

	/** Initialize the extent report. */
	public static void initialize() {
		TestReport report = new TestReport();
		LogStatus.pass("Extent Report is initialized.");
	}
	
	/**
	 * Get the report location.
	 * @return Path of the report.
	 */
	public static String getReportPath() {
		if (reportFilePath.isEmpty()) {
			if (PropertyFileHelper.get(PropertyConfig.OVERRIDETESTREPORT).equalsIgnoreCase(FrameworkConstant.yes)) {
				reportFilePath = FrameworkConstant.editExtentreportPath;
			} else {
				reportFilePath = FrameworkConstant.newExtentReportPath(GenericUtil.getCurrentDateTime());
			}
		}
		return reportFilePath;
	}

}
