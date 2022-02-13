	package com.automation.blazedemo.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.automation.blazedemo.reports.ExtentManager;
import com.automation.blazedemo.reports.LogStatus;
import com.automation.blazedemo.reports.TestReport;
import com.automation.blazedemo.utils.GenericUtil;



/**
 * Represents a ListenerClass derived from ITestListener.
 * 
 * @author sunitha
 *
 */
public class ListenerClass implements ITestListener {

	private static String TestcaseName;
	
	// This will execute before the main test start (@Test)
	public void onTestStart(ITestResult result) {
		TestcaseName = result.getMethod().getDescription();
		ExtentManager.setExtentTest(TestReport.report.startTest(TestcaseName));
		LogStatus.pass("********** " + TestcaseName + " is started successfully. **********", true);

	}

	// This will execute only when the test is pass
	public void onTestSuccess(ITestResult result) {
		LogStatus.pass(result.getMethod().getDescription() + " test case is passed.", false);
		TestReport.report.endTest(ExtentManager.getExtTest());
		LogStatus.pass("********** " + TestcaseName + " is successful. **********", true);
	}

	// This will execute only on the event of fail test
	public void onTestFailure(ITestResult result) {
		LogStatus.fail(result.getMethod().getDescription() + " is failed.", false);
		LogStatus.fail(result.getThrowable().getMessage());
		TestReport.report.endTest(ExtentManager.getExtTest());
		LogStatus.fail("********** " + TestcaseName + " is not successful. **********", false);

	}

	// This will execute only if any of the test(@Test) get skipped
	public void onTestSkipped(ITestResult result) {
		LogStatus.skip(result.getMethod().getDescription() + " is skipped");
		TestReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		TestReport.report.endTest(ExtentManager.getExtTest());
	}

	// This will execute, once the Test set/batch is finished
	public void onFinish(ITestContext context) {
		TestReport.report.endTest(ExtentManager.getExtTest());

	}

	// This will execute before starting of Test set/batch
	public void onStart(ITestContext arg0) {
		// LogStatus.info("Test started : " + arg0.getName());
	}
	
	public static String getTestcaseName() {
		return TestcaseName;
	}

}
