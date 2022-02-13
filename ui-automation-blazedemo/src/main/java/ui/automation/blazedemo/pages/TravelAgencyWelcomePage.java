package ui.automation.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.blazedemo.browser.DriverManager;
import com.automation.blazedemo.reports.LogStatus;
//import com.automation.blazedemo.reports.TestReporter;
import com.automation.blazedemo.utils.Constants;
import com.automation.blazedemo.utils.GenericUtil;

/**
 * This class used represent page objects and methods for Travel agency welcome
 * page
 * 
 * @author sunit
 *
 */
public class TravelAgencyWelcomePage extends BasePage {

	@FindBy(xpath = "/*/a[text()='destination of the week! The Beach!']")
	private WebElement destLink;
	@FindBy(xpath = "//*/select[@name='fromPort']")
	private WebElement comboxfrom;
	@FindBy(xpath = "//*[@type='submit' and @value='Find Flights']")
	private WebElement findflightBtn;
	@FindBy(xpath = "//*/select[@name='toPort']")
	private WebElement comboxto;
	@FindBy(xpath = "//*/a[text()='destination of the week! The Beach!' and @href ='vacation.html']")
	private WebElement destinationLnk;
	@FindBy(xpath = "//html/body/div[2]/img")
	private WebElement destinationpgImg;
	// ***//

	String labelxapth = "//*[text()='%s']";

	/**
	 * Constructor
	 * 
	 * @author Sunitha
	 */

	public TravelAgencyWelcomePage() {

		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	/**
	 * This Method used to verify the WTravel Welcomepgae UI
	 * 
	 * @author ssiddara
	 * @return
	 */
	public Boolean verifyWelcomePageUI() {
		
		Constants.TESTVALDATIONFLAG = false;
		WebElement eleLabel = getElementWithoutError(labelxapth, "Welcome to the Simple Travel Agency!");
		WebElement eleLabel1 = getElementWithoutError(labelxapth,
				"The is a sample site you can test with BlazeMeter! ");
		WebElement eleDestination = getElementWithoutError(labelxapth, "Choose your destination city:");
		WebElement eleDepart = getElementWithoutError(labelxapth, "Choose your departure city:");
		if (destinationLnk.isDisplayed() && eleLabel.isDisplayed() && eleLabel1.isDisplayed()
				&& eleDestination.isDisplayed() && eleDepart.isDisplayed() && comboxfrom.isDisplayed()
				&& comboxfrom.isDisplayed()) {
			Constants.TESTVALDATIONFLAG = true;
		}

		return Constants.TESTVALDATIONFLAG;
	}

	/**
	 * This Method used to Select the option form the Selectitem list
	 * 
	 * @author ssiddara
	 * @param fromVal
	 * @return
	 */
	public Boolean isSelectFormport(String fromVal) {
		Constants.TESTVALDATIONFLAG = false;
		String selectVal = seleniumUtil.selectItem(comboxfrom, fromVal);
		if (selectVal.equals(fromVal)) {
			Constants.TESTVALDATIONFLAG = true;
		}

		return Constants.TESTVALDATIONFLAG;
	}

	/**
	 * 
	 * @param toVal
	 * @return
	 */
	public Boolean isSelectToport(String toVal) {
		Constants.TESTVALDATIONFLAG = false;
		String selectVal = seleniumUtil.selectItem(comboxto, toVal);
		if (selectVal.equals(toVal)) {
			Constants.TESTVALDATIONFLAG = true;
		}

		return Constants.TESTVALDATIONFLAG;
	}

	/**
	 * This Method used to Click on the Find Flight Btn
	 * 
	 * @author Sunitha
	 */
	public void clickonFindFlightBtn() {
		seleniumUtil.clickElement(findflightBtn);
		LogStatus.pass("Clicked on Button ", false);
	}

	/**
	 * This Method used to Click on the destination Link
	 * 
	 * @author Sunitha
	 */
	public void clickonCheckoutDestinationLnk() {
		seleniumUtil.clickElement(destinationLnk);
		LogStatus.pass("Clicked on Destination Link ", false);
	}

	/**
	 * This Method used to Click on the destination Link
	 * 
	 * @author Sunitha
	 */
	public boolean isDestinationLnkNavigated() {
		boolean flag =false;
		GenericUtil.hardWait(1);		
		if (destinationpgImg.isDisplayed()) {
			flag =true;
			LogStatus.pass("Clicked on Destination Link ", false);
		}else
		{
			LogStatus.fail("Clicked on Destination Link ", true);
		}
		return flag;
		}
	
	/**
	 * 
	 * @param from
	 * @param destination
	 */
	public void clickOnFindFlight(String from,String destination)
	{
		isSelectFormport(from);
	    isSelectToport(destination);
	    clickonFindFlightBtn();	
	}
}
