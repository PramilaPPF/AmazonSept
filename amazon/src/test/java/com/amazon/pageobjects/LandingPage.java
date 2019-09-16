package com.amazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

import com.amazon.setup.BaseSetup;

/**
 * Created by Pramila Fulari on 9/11/2019.
 */

public class LandingPage extends BaseSetup {

	LandingPageObjects landingpage;

	public LandingPage() throws InterruptedException {
		super();
		landingpage = new LandingPageObjects();
		//Managing wait by using AjaxElementLocatorFactory
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 100), landingpage);
		//Reporter.log("Webelements for Landing page initiated successfully");
	}

	public CatgorySelection clickOnHamburger() throws InterruptedException {
		try {
			log.info("hamburger button is enabled");
			implicitWait(30);
			landingpage.hamburger.click();
			log.info("Hamburger button Clicked");
			return new CatgorySelection();
		} catch (Exception e) {
			System.out.println(e);
			return new CatgorySelection();
		}
	}

	class LandingPageObjects {

		@FindBy(xpath = "//android.widget.ImageView[@resource-id='in.amazon.mShop.android.shopping:id/action_bar_burger_icon']")
		public WebElement hamburger;

		@FindBy(xpath = "//android.widget.ImageView[@resource-id='in.amazon.mShop.android.shopping:id/action_bar_cart_image']")
		public WebElement cart;

		@FindBy(xpath = "//android.widget.TextView[contains(starts-with(@text,'Shop')]")
		public WebElement dropDown;

		@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='in.amazon.mShop.android.shopping:id/search_bar_plate']")
		public WebElement searchText;

	}
}