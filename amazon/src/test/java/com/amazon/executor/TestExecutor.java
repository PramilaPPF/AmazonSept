package com.amazon.executor;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.amazon.pageobjects.CatgorySelection;
import com.amazon.pageobjects.LandingPage;
import com.amazon.setup.BaseSetup;

/**
 * Created by Pramila Fulari on 9/11/2019.
 */

public class TestExecutor extends BaseSetup {

	// TestScenario : Launching Amazon app and Checking presence of WebElement
	@Test
	public void testLandingPAndShopByCatgoryage() {
		// Logger Initializing
		Logger logger = Logger.getLogger(Logger.class.getName());
		// Calling Landing Page by instantiating the LandingPage
		LandingPage loginPage = null;
		CatgorySelection cs = null;
		try {
			loginPage = new LandingPage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Hamburger button Clicked");
		// Selecting ShopByCategory and verifying presence of Kindle Element
		try {
			cs = loginPage.clickOnHamburger();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		try {
			cs.clickOnShopByCategory();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Reporter.log("Kindle E-Readers element is verification done");
		// Saving Logs to TestExecutor.log at default project location
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("TestExecutor.log", true);
		} catch (SecurityException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		logger.addHandler(fileHandler);

	}

}
