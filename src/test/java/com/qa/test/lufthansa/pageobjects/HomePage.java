package com.qa.test.lufthansa.pageobjects;

import com.qa.test.lufthansa.base.DriverClass;

public class HomePage extends DriverClass{

	String oneWayButton = "css=#flightmanagerFlightsFormOnewayLabel";
	String sourceTextbox = "flightmanagerFlightsFormOrigin";
	String destinationTextbox = "flightmanagerFlightsFormDestination";
	String calendarIcon = "flightmanagerFlightsFormOutboundDateDisplay";
	String calendarDays = "//*[@class='months-wrapper']/div[contains(@class,'month month')][1]//*[@data-kosa-calendar-date='%s']";
	String searchFlights = "//form[@id='flightmanagerFlightsForm']//button[@type='submit']";

	public void clickOnOneWayButton() throws Exception{
		clickElement(oneWayButton);
	}
	
	public void enterSourceCity(String cityName) throws Exception{
		typeOnElement(sourceTextbox, cityName);
	}
	
	public void enterDestinationCity(String cityName) throws Exception{
		typeOnElement(destinationTextbox, cityName);
	}
	
	public void selectDate(String date) throws Exception{
		clickElement(calendarIcon);
		clickElement(String.format(calendarDays, date));
	}
	
	public void clickOnSearchFlights() throws Exception{
		Thread.sleep(5000);
		clickElement(searchFlights);
	}
	
	
}
