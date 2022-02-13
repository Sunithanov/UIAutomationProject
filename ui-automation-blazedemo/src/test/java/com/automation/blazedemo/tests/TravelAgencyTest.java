package com.automation.blazedemo.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.blazedemo.reports.LogStatus;
import com.automation.blazedemo.utils.GenericUtil;
import com.automation.blazedemo.utils.TestBase;

import ui.automatiom.blazedemo.TestData.TestData;
import ui.automation.blazedemo.pages.FlightTicketPurchasePage;
import ui.automation.blazedemo.pages.FlightsFromToPage;
import ui.automation.blazedemo.pages.TravelAgencyWelcomePage;

public class TravelAgencyTest extends TestBase {

	/**
	 * This class used to Test to create booking and verify the confirmation ID
	 * 
	 * @author ssiddara
	 * 
	 */

	@Test(enabled = true, priority = 0, description = "Test to verify the UI and Destination Link on Trvale Agency welcome page")
	public void testVerifyTravelWecomeUI() {

		TravelAgencyWelcomePage welPage = new TravelAgencyWelcomePage();
		Assert.assertTrue(welPage.verifyWelcomePageUI(), "Verify  All elements exists as Expected success fully ");
		welPage.clickonCheckoutDestinationLnk();
		Assert.assertTrue(welPage.isDestinationLnkNavigated(),
				"Verify  All elements exists as Expected success fully ");
	}

	@Test(enabled = true, description = "Test to create booking and verify the confirmation ID E2E")
	public void testCreateandVerifyConfirmId() throws InterruptedException {

		LogStatus.logStep("Select Form and To options and click on FindFlight");

		String from = "Boston", destination = "London";
		TravelAgencyWelcomePage welPage = new TravelAgencyWelcomePage();
		welPage.clickOnFindFlight(from, destination);
		LogStatus.logStep("Navigate to From to");

		FlightsFromToPage fromtoPage = new FlightsFromToPage();
		FlightTicketPurchasePage purchagePage = new FlightTicketPurchasePage();

		GenericUtil.hardWait(2);
		HashMap<String, String> hashMap = fromtoPage.getAirlineandFlightNumber("Price", "200");
		LogStatus.logStep("Status Values:->" + hashMap);

		fromtoPage.ClickChooseFlightBasedonPrice("Price", 200);
		LogStatus.logStep("After choosing the flight It navigate to fromto page");

		purchagePage.isFilghtReserveTitleExists();
		
		String name = TestData.NAME + GenericUtil.generateRandomString(4);
		String[] array = GenericUtil.getCurrentMonthyear();

		LogStatus.logStep("Enter all the fields to Click on Purchase");
		purchagePage.purchaseTicket(name, GenericUtil.randomNumber(10), array[0], array[1], "amex");

		LogStatus.logStep("Verify that confirmation Id generated");
		purchagePage.verifyPurchaseConfirmationID();

	}

	@Test(enabled = true, description = "Test to verify the Airlines and Flight number only for Testing demo")
	public void testVerifyReserveAirlineandNumber() throws InterruptedException {

		LogStatus.logStep("Select Form and To options and click on FindFlight");

		String from = "Boston", destination = "London";
		TravelAgencyWelcomePage welPage = new TravelAgencyWelcomePage();

		welPage.clickOnFindFlight(from, destination);

		FlightsFromToPage fromtoPage = new FlightsFromToPage();
		FlightTicketPurchasePage purchagePage = new FlightTicketPurchasePage();

		GenericUtil.hardWait(2);
		
		LogStatus.logStep("After choosing the flight It navigate to fromto page");

		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("Airline", "United");
		hashMap.put("Flight Number", "UA954");
		fromtoPage.clickOnFirstChooseFlightBtn();

		purchagePage.isFilghtReserveTitleExists();
		purchagePage.verifyAirlinesFlightNumber(hashMap);
		

	}
}
