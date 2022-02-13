package com.automation.blazedemo.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.automation.blazedemo.enums.PropertyConfig;
import com.automation.blazedemo.reports.LogStatus;
import com.automation.blazedemo.utils.PropertyFileHelper;

/**
 * Logic to retry test cases and no of retries depends on parameter from properties file.
 * This Class decides how many time failure tests need to rerun.
 * @author Sunitha
 * 
 */
public class RetryFailedTestCases implements IRetryAnalyzer {
	
	/** Variable to keep counter of retry */
	private int retryCount = 0;
	/** Variable to define max retry count */
	private int maxRetryCount = Integer.parseInt(PropertyFileHelper.get(PropertyConfig.NUMBEROFENTRIES));
	
	/**
	 * This method decides how many times a test needs to be rerun. TestNg will
	 * call this method every time a test fails. 
	 * @author Sunitha
	 * @param result -> The result of the test method that just ran.
	 * @return boolean -> Returns true if the test method has to be retried, false otherwise.
	 */
	public boolean retry(ITestResult result) {
		// We need to implement a cap, otherwise execution might go indefinite loop. 
		if (retryCount < maxRetryCount) {
			LogStatus.pass("Retrying test " + result.getName() + " with status " + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			retryCount++;
			return true;
		}
		return false;
	}
	
	/**
	 * This method get the status name based on status code. 
	 * @author Sunitha
	 * @param result -> Status in integer form.
	 * @return resultName -> Returns result name.
	 */
	public String getResultStatusName(int status) {
		String resultName = null;
		//Check status for Success
		if (status == 1)
			resultName = "SUCCESS";
		//Check status for Failure
		if (status == 2)
			resultName = "FAILURE";
		//Check status for Skip
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}
	
	
}