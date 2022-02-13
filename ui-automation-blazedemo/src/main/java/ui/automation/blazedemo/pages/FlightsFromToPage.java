package ui.automation.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.blazedemo.browser.DriverManager;
import com.automation.blazedemo.reports.LogStatus;
import com.automation.blazedemo.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;

/**
 * This class represents pageobjects and methods for flight from to page
 * 
 * @author ssiddara
 *
 */
public class FlightsFromToPage extends BasePage {

	// ***//
	private WebDriver driver;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/input")
	private WebElement chooseFstLink;

	String xpathChooseByIndex = "//table/tbody[*]/tr[%s]/td[%s]/*[@value= 'Choose This Flight']";
	String xpathtoCoulmnValue = "//table/tbody[*]/tr[%s]/td[%s]";
	String labelxapth = "//*[text()=%s]";

	/**
	 * 
	 */
	public FlightsFromToPage() {

		PageFactory.initElements(DriverManager.getDriver(), this);
		this.driver = DriverManager.getDriver();
	}

	/**
	 * 
	 * @param form
	 * @param tovalue
	 * @return
	 */
	public Boolean isFlightsFromToExists(String form, String tovalue) {
		Constants.TESTVALDATIONFLAG = false;

		WebElement fomToTitle = driver.findElement(By.xpath("Flights from " + form + " to " + tovalue + ":"));

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (fomToTitle.isDisplayed()) {
			Constants.TESTVALDATIONFLAG = true;
		}

		return Constants.TESTVALDATIONFLAG;
	}

	/**
	 * @author ssiddara
	 * @param coLName
	 * @param price
	 * @return
	 */
	public boolean ClickChooseFlightBasedonPrice(String colName, int inputValue) {
		boolean flag = false;
		int getUIPrice = 0;
		List<WebElement> cols = (List<WebElement>) driver.findElements(By.xpath("//table/thead/tr/th"));
		System.out.println("Check Column count-->" + cols.size());
		List<WebElement> rows = (List<WebElement>) driver.findElements(By.xpath("//table/tbody/tr"));
		int ColIndex = getGridColumnIndxeByColName(colName);

		for (int rowNum = 1; rowNum <= rows.size(); ++rowNum) {
			WebElement cellValue = getElementWithoutError(xpathtoCoulmnValue, rowNum, ColIndex);
			String rowtext = cellValue.getText();
			rowtext = removeFirstChar(rowtext);
			try {
				rowtext = String.valueOf(rowtext).split("\\.")[0];
				int getUIPrices = Integer.parseInt(rowtext);
			} catch (NumberFormatException e) {
				System.out.println("Check Column count-->" + getUIPrice);
			}
			if (getUIPrice <= inputValue) {
				clickonChooseFlight(rowNum);
				flag = true;
				break;
			}

		}

		return flag;
	}

	/**
	 * This method used to click on Choose flight Button in Row
	 * 
	 * @author ssiddara
	 * @param rowNum
	 */
	public void clickonChooseFlight(int rowNum) {
		int chooseFlightCol = getGridColumnIndxeByColName("Choose");
		WebElement editIcon = getElementWithoutError(xpathChooseByIndex, rowNum, chooseFlightCol);		
		if (editIcon.isDisplayed()) {
			editIcon.click();
		}
	}

	/**
	 * 
	 * This method used to get the index of the Column based on CoulmnName
	 * 
	 * @author ssiddara
	 * @param columName
	 * @return
	 */
	public int getGridColumnIndxeByColName(String columName) {
		int columns = 0, colIndex = 0;
		List<WebElement> cols = (List<WebElement>) driver.findElements(By.xpath("//table/thead/tr/th"));
		System.out.println("Check Column count-->" + cols.size());
		for (WebElement col : cols) {
			String colTitle = col.getText();
			++columns;
			if (colTitle.contains(columName)) {
				colIndex = columns;
				System.out.println(columName + ":: Header ColumnIndex-->" + colIndex);
				break;
			}
		}
		return colIndex;
	}

	/**
	 * 
	 * @param columnName
	 * @param value
	 * @return
	 */
	public HashMap<String, String> getAirlineandFlightNumber(String columnName, String value) {
		HashMap<String, String> hm = new HashMap<String, String>();
		List<WebElement> rows = (List<WebElement>) driver.findElements(By.xpath("//table/tbody/tr"));
		int ColIndex = getGridColumnIndxeByColName(columnName);
		int count = 1;
		for (int rowNum = 1; rowNum <= rows.size(); ++rowNum) {
			WebElement cellValue = getElementWithoutError(xpathtoCoulmnValue, rowNum, ColIndex);
			String rowtext = cellValue.getText();
			rowtext = removeFirstChar(rowtext);
			rowtext = String.valueOf(rowtext).split("\\.")[0];
			if (rowtext.contains(value)) {
				hm.put("Airlines", getElementWithoutError(xpathtoCoulmnValue, rowNum, 3).getText());
				hm.put("Flight Number", getElementWithoutError(xpathtoCoulmnValue, rowNum, 2).getText());
				break;
			} else if (count == rows.size()) {
				System.out.println("Value doesn't match");
			}
			count++;
		}
		return hm;
	}

	/**
	 * 
	 * @param column
	 * @param value
	 */
	public void verifyAirlinesDetails(String column, String value) {
		ClickChooseFlightBasedonPrice("Price", Integer.parseInt(value));
		getAirlineandFlightNumber(column, value);
	}

	/**
	 * 
	 */
	public void clickOnFirstChooseFlightBtn() {
		chooseFstLink.click();
		LogStatus.pass("Clicked on Destination Link ", false);
		
	}

}
