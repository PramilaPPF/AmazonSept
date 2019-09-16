package com.amazon.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.amazon.setup.BaseSetup;

/**
 * Created by Pramila Fulari on 9/11/2019.
 */

public class CatgorySelection extends BaseSetup {

	CatgorySelectionObjects catgoryselectionpage;

	public CatgorySelection() {
		catgoryselectionpage = new CatgorySelectionObjects();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 100), catgoryselectionpage);
		Reporter.log("Webelements for Shop by category page initiated successfully");

	}

	public void clickOnShopByCategory() throws InterruptedException {
		try {
			log.info("ShopByCategory is enabled");
			implicitWait(10);
			catgoryselectionpage.shopByCatgory.click();
			implicitWait(10);
			log.info("ShopByCategory is tapped suucessfully");
			Reporter.log("ShopByCategory selected");
			log.info("electronics is enabled");
			//Asserting webElement by using respective text attribute
			Assert.assertEquals(catgoryselectionpage.electronics.getText(), "TV, Appliances, Electronics");
			implicitWait(10);
			catgoryselectionpage.electronics.click();
			log.info("electronics is tapped suucessfully");
			Reporter.log("electronics selected");
			log.info("scrolling to kindle");
			log.info("kindle is enabled");
			//Asserting webElement by using respective text attribute
			Assert.assertEquals(catgoryselectionpage.kindle.getText(), "Kindle E-Readers & eBooks");
			implicitWait(10);
			catgoryselectionpage.kindle.click();
			log.info("kindle is tapped suucessfully");
			Reporter.log("kindle selected");
			log.info("Started checking presence of Kindle EReader");
			presenceOfKindle();
			log.info("Kindle E-Readers element is verfied");
			Reporter.log("Kindle E-Readers element is verfied");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public boolean presenceOfKindle() throws InterruptedException {
		boolean elements = false;
		//Exception handling while checking presence of webElement
		try {
			catgoryselectionpage.kindleEreader.getText();
			return elements;

		} catch (NoSuchElementException e) {
			return elements;
		}
	}

	class CatgorySelectionObjects {		
		@FindBy(xpath = "//android.widget.TextView[contains(@text, 'Shop by Category')]")
		public WebElement shopByCatgory;
		
		@FindBy(xpath = "//android.view.View[contains(@text, 'TV, Appliances, Electronics')]")
		public WebElement electronics;
		
		@FindBy(xpath = "//android.view.View[contains(@text, 'Kindle E-Readers & eBooks')]")
		public WebElement kindle;
		
		@FindBy(xpath = "//android.view.View[contains(@text, 'Kindle E-Readers ')]")
		public WebElement kindleEreader;

	}
}