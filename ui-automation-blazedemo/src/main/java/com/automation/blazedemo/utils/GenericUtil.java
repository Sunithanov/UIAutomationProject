package com.automation.blazedemo.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.activation.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.*;

import com.automation.blazedemo.browser.DriverManager;
import com.automation.blazedemo.constants.FrameworkConstant;
import com.automation.blazedemo.enums.PropertyConfig;
import com.automation.blazedemo.listeners.ListenerClass;
import com.automation.blazedemo.reports.ExtentManager;
import com.automation.blazedemo.reports.LogStatus;
import com.automation.blazedemo.reports.TestReport;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.awt.Desktop;

/**
 * Represents common functionality which used for framework, such as 1. Take the
 * screenshot 2. Get BASE64 image to link in extent report 3. Get current date
 * time format
 * 
 * @author Sunitha
 * 
 */
public final class GenericUtil {

	private GenericUtil() {
		// Avoid creating the object of this class.
	}

	/**
	 * Captures screenshot and returns the screenshot path.
	 * 
	 * @return destination -> return the screenshot path.
	 */
	public static String takeScreenshotAndPullPath() {
		File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		String targetPath = null;
		try {
			if (PropertyFileHelper.get(PropertyConfig.SCREENSHOTPATH).equals("")) {
				targetPath = FrameworkConstant.testCaseScreenShotPath(ListenerClass.getTestcaseName());
			} else {
				targetPath = FrameworkConstant.testCaseScreenShotPath(ListenerClass.getTestcaseName(),
						PropertyFileHelper.get(PropertyConfig.SCREENSHOTPATH));
			}
			FileUtils.copyFile(scrFile, new File(targetPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetPath;
	}

	/**
	 * Gives a base64 image which is used to append the screenshots in the extent
	 * report. Converting to base64 format avoids screenshots broken image.
	 * 
	 * @param screenshotpath --> path of screenshot.
	 * @return base64 -> return the encoded image string.
	 */
	public static String getBase64Image() {
		String base64 = null;
		try {
			byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(takeScreenshotAndPullPath()));
			base64 = ExtentManager.getExtTest()
					.addBase64ScreenShot("data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64;
	}

	/**
	 * This method gives the current date and time on specific format.
	 * 
	 * @return It returns time in specific format.
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_hh_mm_ss");
		return formatter.format(new Date());
	}

	/**
	 * This method open the report automatically.
	 */
	public static void openReport() {
		if (PropertyFileHelper.get(PropertyConfig.OPENTESTRESULT).equalsIgnoreCase(FrameworkConstant.yes)) {
			File htmlFile = new File(TestReport.getReportPath());
			try {
				Desktop.getDesktop().browse(htmlFile.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method generates the random emailid.
	 * 
	 * @return return the generated random mail.
	 */
	public static String generateRandomMail(int numberOfDigits) {
		String randomestring = RandomStringUtils.randomAlphabetic(numberOfDigits);
		return randomestring + "@gmail.com";
	}

	/**
	 * This method helps in generate random number within range
	 * 
	 * @param min
	 * @param max
	 * @return
	 */

	public int getRandomNumberOfRange(int min, int max) {
		int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNumber;
	}

	/**
	 * This method used to generate alphaNumeric value
	 * 
	 * @author ssiddara
	 * @param length
	 */
	/*
	 * public String generateRandomAlphanumericString(int length) { int leftLimit =
	 * 48; int rightLimit = 115; int targetStringLength = length; Random random =
	 * new Random();
	 * 
	 * String generatedString = random.ints(leftLimit, rightLimit + 1) .filter(i ->
	 * (i <= 57 || i >= 65) && (i <= 90 || i >= 115)).limit(targetStringLength)
	 * .collect(StringBuilder::new, StringBuilder::appendCodePoint,
	 * StringBuilder::append).toString(); return generatedString; }
	 */
	/**
	 * 
	 * @param numberOfDigits
	 * @return
	 */
	public static String generateRandomString(int numberOfDigits) {
		String randomestring = RandomStringUtils.randomAlphabetic(numberOfDigits);
		return randomestring;
	}

	public static String[] getCurrentMonthyear() {
		String arr[] = new String[2];
		LocalDate currentdate = LocalDate.now();
		int currentMonth = currentdate.getMonthValue();

		int currentYear = currentdate.getYear();
		arr[0] = String.valueOf(currentMonth);
		arr[1] = String.valueOf(currentYear);

		return arr;
	}

	/**
	 * This Method used to select the value form the combobox and return the
	 * selected value
	 * 
	 * @param locator
	 * @param value
	 * @return
	 */
	public String selectItem(final WebElement locator, String value) {
		Select select = new Select(locator);
		select.selectByValue(value);
		WebElement getSelectItem = select.getFirstSelectedOption();
		String selectedoption = getSelectItem.getText();
		return selectedoption;
	}

	/**
	 * This Method used to check the Checkbox and the status
	 * 
	 * @author sunit
	 * @param locator
	 * @param value
	 * @return
	 */
	public Boolean isChkboxChecked(final WebElement locator) {

		boolean isSelected = locator.isSelected();
		if (isSelected == false) {
			locator.click();
			isSelected = true;
		}
		return isSelected;
	}

	/**
	 * This method used get the Title of the Page
	 * 
	 * @return
	 */
	public static String getTitle() {
		return DriverManager.getDriver().getTitle();
	}

	/**
	 * This method used get the CurrentUrl of the Page
	 * 
	 * @return
	 */
	public static String getCurrentUrl() {
		return DriverManager.getDriver().getCurrentUrl();
	}

	public static String getWindowHandle() {
		return DriverManager.getDriver().getWindowHandle();
	}

	public static void switchToWindow(String windowName) {
		DriverManager.getDriver().switchTo().window(windowName);
	}

	public static Set<String> getAllWindowHandle() {
		return DriverManager.getDriver().getWindowHandles();
	}

	public static void dismissAert() {
		DriverManager.getDriver().switchTo().alert().dismiss();
	}

	/**
	 * 
	 */
	public static void acceptAert() {
		DriverManager.getDriver().switchTo().alert().accept();
	}

	/**
	 * 
	 * @param value
	 */
	public static void sendValueToAlert(String value) {
		DriverManager.getDriver().switchTo().alert().sendKeys(value);
	}

	/**
	 * 
	 * @return
	 */
	public static String getAertText() {
		return DriverManager.getDriver().switchTo().alert().getText();
	}

	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String randomNumber(int length) {
		new RandomStringUtils();
		return RandomStringUtils.randomNumeric(length);
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomNumberBetween(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}

	/**
	 * 
	 * @param length
	 * @return
	 */

	public static String randomString(int length) {
		new RandomStringUtils();
		return RandomStringUtils.randomAlphabetic(length);
	}

	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String randomAlphaNumeric(int length) {
		new RandomStringUtils();
		return RandomStringUtils.randomAlphanumeric(length);
	}

	/**
	 * 
	 * @param seconds
	 */
	public static void hardWait(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * 
	 * @param driver
	 * @param seconds
	 */
	public static void implicitWait(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
}
