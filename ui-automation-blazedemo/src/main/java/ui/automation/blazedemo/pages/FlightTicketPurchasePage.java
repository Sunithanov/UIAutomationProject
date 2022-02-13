package ui.automation.blazedemo.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.automatiom.blazedemo.TestData.*;

import com.automation.blazedemo.browser.DriverManager;
import com.automation.blazedemo.reports.LogStatus;
//import com.automation.blazedemo.reports.TestReporter;
import com.automation.blazedemo.utils.Constants;

public class FlightTicketPurchasePage extends BasePage {
	
	
	@FindBy(xpath = "//div[*]/p[1]")
	private WebElement eleAirlineName;
	@FindBy(xpath = "//div[*]/p[2]")
	private WebElement eleFlightNumber;
	@FindBy(xpath = "//*[@id='inputName']")
	private WebElement txtboxFstName;
	@FindBy(xpath = "//*[@id='address']")
	private WebElement txtboxaddress;
	@FindBy(xpath = "//*[@id='city']")
	private WebElement txtboxCity;
	@FindBy(xpath = "//*[@id='state']")
	private WebElement txtboxState;
	@FindBy(xpath = "//*[@id='zipCode']")
	private WebElement txtboxzipCode;
	@FindBy(xpath = "//*[@id='creditCardNumber']")
	private WebElement txtboxCCN;
	@FindBy(xpath = "//*[@id='creditCardMonth']")
	private WebElement txtboxCrdMonth;
	@FindBy(xpath = "//*[@id='creditCardYear']")
	private WebElement txtboxCrdYr;
	@FindBy(xpath = "//*[@id='nameOnCard']")
	private WebElement txtboxNameonCrd;
	@FindBy(xpath = "//*/select[@name='cardType']")
	private WebElement comboxCrdType;
	@FindBy(xpath = "//*[@id='rememberMe']")
	private WebElement chkbxRememberMe;
	@FindBy(xpath = "//*[@type='submit' and @value='Purchase Flight']")
	private WebElement purchaseflightBtn;
	@FindBy(xpath = "//*[text()='Thank you for your purchase today!']")
	private WebElement ticketConfrimMsg;
	@FindBy(xpath = "//*[text()='Your flight from TLV to SFO has been reserved.']")
	private WebElement reservePage;
	
	String xpathtoCoulmnValue = "//table/tbody[*]/tr[%s]/td[%s]";

	public FlightTicketPurchasePage() {
		
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public Boolean isFilghtReserveTitleExists() {
		Constants.TESTVALDATIONFLAG = false;

		if (reservePage.isDisplayed()) {
			Constants.TESTVALDATIONFLAG = true;
		}

		return Constants.TESTVALDATIONFLAG;
	}

	/**
	 * This method used to enter the Address value
	 * 
	 * @author sunitha
	 * @param name
	 * @return
	 * @throws InterruptedException
	 */

	public String enterName(String name) throws InterruptedException {
		seleniumUtil.clearText(txtboxFstName);
		seleniumUtil.setText(txtboxFstName, name);
		String getname = seleniumUtil.getText(txtboxFstName);
		// TestReporter.pass("Entered the Name Successfully : " + name, false);
		return getname;
	}

	/**
	 * This method used to enter the Address value
	 * 
	 * @author sunitha
	 * @param adress
	 * @return
	 */
	public FlightTicketPurchasePage enterAdress(String adress) {
		seleniumUtil.clearText(txtboxaddress);
		seleniumUtil.setText(txtboxaddress, adress);
		// TestReporter.pass("Entered the adress Successfully : " + adress, false);
		return this;
	}

	/**
	 * This method used to enter the City value
	 * 
	 * @author sunitha
	 * @param txtCity
	 * @return
	 */
	public FlightTicketPurchasePage enterCity(String txtCity) {
		seleniumUtil.clearText(txtboxCity);
		seleniumUtil.setText(txtboxCity, txtCity);
		// TestReporter.pass("Entered the adress Successfully : " + txtCity, false);
		return this;
	}

	/**
	 * This method used to enter the Address value
	 * 
	 * @author sunitha
	 * @param state
	 * @return
	 */
	public FlightTicketPurchasePage enterState(String state) {
		seleniumUtil.clearText(txtboxState);
		seleniumUtil.setText(txtboxState, state);
		// TestReporter.pass("Entered the adress Successfully : " + state, false);
		return this;
	}

	/**
	 * This method used to enter the CardNumaber value
	 * 
	 * @author sunitha
	 * @param cardnumber
	 * @return
	 */
	public String enterCardNumber(String cardNum) {
		seleniumUtil.clearText(txtboxCCN);
		seleniumUtil.setText(txtboxCCN, cardNum);
		String cardnumber = seleniumUtil.getText(txtboxCCN);
		// TestReporter.pass("Entered the adress Successfully : " + cardNum, false);
		return cardnumber;
	}

	/**
	 * This method used to enter the Zipcode value
	 * 
	 * @author sunitha
	 * @param zipCode
	 * @return
	 */
	public void enterZipcode(String zipCode) {
		seleniumUtil.clearText(txtboxzipCode);
		seleniumUtil.setText(txtboxzipCode, zipCode);
		// TestReporter.pass("Entered the adress Successfully : " + zipCode, false);

	}

	/**
	 * This method used to enter the cardMonth value
	 * 
	 * @author sunitha
	 * @param crdMonth
	 * @return
	 */
	public void entermonth(String crdMonth) {
		seleniumUtil.clearText(txtboxCrdMonth);
		seleniumUtil.setText(txtboxCrdMonth, crdMonth);
		// TestReporter.pass("Entered the crdMonth Successfully : " + crdMonth, false);

	}

	/**
	 * This method used to enter the crdYr value
	 * 
	 * @author sunitha
	 * @param crdYr
	 * @return
	 */
	public void entercardOfYear(String crdYr) {
		seleniumUtil.clearText(txtboxCrdYr);
		seleniumUtil.setText(txtboxCrdYr, crdYr);
		// TestReporter.pass("Entered the crdYr Successfully : " + crdYr, false);

	}

	/**
	 * This Method used to enter the crdYr value
	 * 
	 * @author nameOnCard
	 * @param crdYr
	 * @return
	 * @throws InterruptedException
	 */
	public String enterNameonCard(String nameOnCard) throws InterruptedException {
		Thread.sleep(300);		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
		seleniumUtil.clearText(txtboxNameonCrd);
		seleniumUtil.setText(txtboxNameonCrd, nameOnCard);
		String name = seleniumUtil.getText(txtboxNameonCrd);
		LogStatus.pass("Entered the nameOnCard Successfully : " + nameOnCard,
		 false);
		return name;
	}

	/**
	 * @author sunitha
	 * @param fromVal
	 * @return
	 */

	public Boolean isSelectCrdType(String typeVal) {
		Constants.TESTVALDATIONFLAG = false;
		String selectVal = seleniumUtil.selectItem(comboxCrdType, typeVal);
		if (selectVal.equals(typeVal)) {
			Constants.TESTVALDATIONFLAG = true;
		}

		return Constants.TESTVALDATIONFLAG;
	}

	/**
	 * @author sunitha
	 * @param fromVal
	 * @return
	 */

	public Boolean isChecked() {
		Constants.TESTVALDATIONFLAG = false;
		Constants.TESTVALDATIONFLAG = seleniumUtil.isChkboxChecked(chkbxRememberMe);

		return Constants.TESTVALDATIONFLAG;
	}

	/**
	 * This Method used to Click on the Find Flight Btn
	 * 
	 * @author sunitha
	 */
	public void clickonPurchaseBtn() {
		seleniumUtil.clickElement(purchaseflightBtn);
		LogStatus.pass("Clicked on Button ", false);
	}

	/**
	 * This method used to enter all the fields for Purchase the Ticket
	 * 
	 * @author ssiddara
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public boolean purchaseTicket(String name, String cardNum, String month, String yrs, String cardType)
			throws InterruptedException {
		String psgName = name;
		Constants.TESTVALDATIONFLAG = false;
		enterName(psgName);
		enterAdress(TestData.STREET_ADDRESS);
		enterCity(TestData.CITY);
		enterState(TestData.STATE);
		enterZipcode(TestData.ZIPCODE);
		enterCardNumber(cardNum);
		entermonth(month);
		entercardOfYear(yrs);
		enterNameonCard(psgName.toUpperCase());
		isSelectCrdType(cardType);
		isChecked();
		clickonPurchaseBtn();
		if (ticketConfrimMsg.isDisplayed()) {
			Constants.TESTVALDATIONFLAG = true;
		}
		return Constants.TESTVALDATIONFLAG;
	}

	/**
	 * This method used to verify the All the Ui Element
	 * 
	 * @author ssiddara
	 * @return
	 */
	public boolean verifyAllUIFeildsExists() {
		Constants.TESTVALDATIONFLAG = false;
		if (txtboxFstName.isDisplayed() && txtboxCrdYr.isDisplayed() && txtboxCrdMonth.isDisplayed()
				&& txtboxzipCode.isDisplayed() && txtboxaddress.isDisplayed() && txtboxCity.isDisplayed()
				&& txtboxState.isDisplayed()) {
			Constants.TESTVALDATIONFLAG = true;
		}
		return Constants.TESTVALDATIONFLAG;

	}
	/**
	 * 
	 * @return
	 */

	public Boolean verifyPurchaseConfirmationID() {	
		HashMap<String, String> hashMap;
		if(isPurcahseDisplayed())
		{
			
	        hashMap = getPurchaseStatusInfo();

        	System.out.println("Status Values-->" +hashMap);
	        for (Entry<String, String> entry : hashMap.entrySet()) {	           
	        	if(entry.getKey().equalsIgnoreCase("Id") &&entry.getKey()!="")
	        	{
	        		System.out.println(entry+"==" +entry.getValue()+":==>Pass");
	        		Constants.TESTVALDATIONFLAG = true;
	        		break;
	        	}
	        }
			
		}else
		{
			LogStatus.fail("Purchase Confirm Page Not displayed",true);
			
		}
return Constants.TESTVALDATIONFLAG;
	}
	/**
	 * This Method used to Verify the Confirmation Message for the Purchase 
	 * @return
	 */
	public boolean isPurcahseDisplayed() {
		Constants.TESTVALDATIONFLAG = false;
		if (ticketConfrimMsg.isDisplayed())
		{
			Constants.TESTVALDATIONFLAG = true;
		}
		return Constants.TESTVALDATIONFLAG;
	}
	
	
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean verifyAirlinesFlightNumber(HashMap<String, String> hm)
	{
		Constants.TESTVALDATIONFLAG = false;		
		String[] flightNumber=eleFlightNumber.getText().split(":");
		String[] airLines=eleAirlineName.getText().split(":");
		if(airLines[1].trim().equals(hm.get("Airline"))&& flightNumber[1].trim().equals(hm.get("Flight Number")))
				{
			System.out.println(flightNumber+": Airline and Flightnumber Matches ss expected :"+flightNumber);
			Constants.TESTVALDATIONFLAG = true;
				}

	
	return Constants.TESTVALDATIONFLAG;
	}
	
	
	

	/**
	 *This method used to get the Status details of the confirmation page using key value pair 
	 *@author ssiddara
	 * @return
	 */
	public HashMap<String, String> getPurchaseStatusInfo()
	{
		HashMap<String, String> hm = new HashMap<String,String>();		
		List<WebElement> rows = (List<WebElement>) driver.findElements(By.xpath("//table/tbody/tr"));	
		for (int rowNum = 1; rowNum <= rows.size(); ++rowNum) {					
			hm.put(getElementWithoutError(xpathtoCoulmnValue, rowNum, 1).getText(),getElementWithoutError(xpathtoCoulmnValue, rowNum, 2).getText());
		}		
		return hm;
	}
	
	
	
}
