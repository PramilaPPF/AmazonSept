package com.amazon.setup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * Created by Pramila Fulari on 9/11/2019.
 */

public class BaseSetup {

	static AndroidDriver<MobileElement> androidDriver = null;
	DesiredCapabilities dsrdCaps = new DesiredCapabilities();
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private DesiredCapabilities cap;
	public Logger log = Logger.getLogger(Logger.class.getName());

	@BeforeSuite
	public void launchAppiumServer() {
		int port = 4725;
		if (!checkIfServerIsRunnning(port)) {
			startServer();
		} else {
			System.out.println("Appium Server already running on Port - " + port);
		}
	}
	@Parameters({ "deviceName_","UDID_","platformVersion_", "ip_","port_" })
	@BeforeTest
	public void setup(String deviceName_,String UDID_,String platformVersion_,String ip_,String port_) {
		 log.info("Capabilities setting is started");
		dsrdCaps.setCapability("automationName", "UiAutomator2");
		dsrdCaps.setCapability("platformVersion", platformVersion_);
		dsrdCaps.setCapability("platformName", "Android");
		dsrdCaps.setCapability("deviceName", deviceName_);
		dsrdCaps.setCapability("udid", UDID_);
		// dsrdCaps.setCapability("noReset", "false");
		dsrdCaps.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		dsrdCaps.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		dsrdCaps.setCapability("app", "C:\\Amazon\\amazon\\resources\\Apks\\Amazon.apk");
		System.out.println("Capabilities setting is completed");
		String serverUrl = "http://"+ip_+":"+port_+"/wd/hub";
		String ip=ip_;

		try {
			androidDriver = new AndroidDriver<MobileElement>(new URL(serverUrl), dsrdCaps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Android Driver init done");

	}

	public void startServer() {
		// Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");
		// Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

		// Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	public void stopServer() {
		service.stop();
	}

	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void implicitWait(int sec) {
		androidDriver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

	public AndroidDriver<MobileElement> getDriver() {
		return androidDriver;
	}

	@AfterTest
	public void quitDriver() {
		androidDriver.quit();
		// log.info("quit done successly");
	}

	@AfterSuite
	public void tearDown() {
		System.out.println("inside aftersuite");
		// appiumServer.stopServer();
	}

}
