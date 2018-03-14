package com.qa.test.lufthansa.testscripts;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.test.lufthansa.base.DriverClass;
import com.qa.test.lufthansa.pageobjects.HomePage;

public class TestScenario_1 extends DriverClass {

	private HomePage home = new HomePage();

	@Test
	@Parameters(value = { "Browser" })
	private void VerifyBasicSearchFunctionality(String browser) throws Exception {
		try {
			initializeBrowsers(browser);
			launchApp(getProperty("ApplicationURL"));
			home.clickOnOneWayButton();
			home.enterSourceCity(getData("Source"));
			home.enterDestinationCity(getData("Destination"));
			home.selectDate(getData("Date"));
			home.clickOnSearchFlights();
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(enabled = false)
	private void VerifyLoginInvalidScenario() throws Exception {
		launchApp(getProperty("ApplicationURL"));
	}

	@Test(enabled = false)
	public void swap() throws Exception {
		int a = 2;
		int b = 3;
		int temp;
		System.out.println("Before Swap");
		System.out.println("A = " + a + " B = " + b);
		temp = a;
		a = b;
		b = temp;
		System.out.println("After Swap");
		System.out.println("A = " + a + " B = " + b);
		log.info("Swaping");
	}

	@Test(enabled = false)
	public void findArmstrongNumber() throws Exception {
		int number = 54748;
		int quo = number;
		int rem = 0;
		int sum = 0;
		while (quo != 0) {
			rem = quo % 10;
			sum += rem * rem * rem;
			quo = quo / 10;
		}
		if (sum == number)
			System.out.println("Given is Armstrong number");
		else
			System.out.println("Given is Not an Armstrong number");
		log.info("Armstrong");
	}

	@Test(enabled = false)
	public void sorting() throws Exception {
		int[] num = { 4, 7, 2, 3 };
		int temp;
		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				if (num[i] < num[j]) {
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
		}
		for (int nu : num) {
			System.out.println(nu);
		}
	}

	@Test(enabled = false)
	public void factorial() throws Exception {
		long num = 44;
		long fact = 1;
		for (int i = 1; i <= num; i++) {
			fact = fact * i;
		}
		System.out.println("Factorial of " + num + " is : " + fact);
	}

	@Test(enabled = false)
	public void findPrime() throws Exception {
		// int num = new Scanner(System.in).nextInt();
		int num = 55;
		boolean flag = false;
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				flag = false;
				break;
			} else
				flag = true;
		}
		if (flag)
			System.out.println("Number : " + num + " is a Prime number");
		else
			System.out.println("Number : " + num + " is NOT a Prime number");

	}

	@Test(enabled = false)
	public void findPalindrome() throws Exception {
		String name = "malayalam";
		char[] character = name.toCharArray();
		char[] reverse = new char[character.length];
		for (int i = 0; i < character.length; i++) {
			reverse[i] = character[character.length - i - 1];
		}
		System.out.println(character);
		System.out.println(reverse);
		Assert.assertEquals(character, reverse);
	}
}
