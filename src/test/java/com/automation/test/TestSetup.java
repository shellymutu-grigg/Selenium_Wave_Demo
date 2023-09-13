package com.automation.test;

import com.automation.test.configuration.BrowserManager;
import com.automation.test.data.ConfigData;
import com.automation.test.tasks.LoginPage;
import com.tidal.wave.browser.Browser;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

@Slf4j
public class TestSetup {

	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
	Logger logger = LoggerFactory.getLogger(TestSetup.class);
	public LoginPage loginPage;

	public static WebDriverWait getWebDriverWait () {
		return wait.get();
	}

	@BeforeTest
	public void threadId(){
		logger.info("@BeforeTest ThreadID: {}", Thread.currentThread().getId());
	}

	@BeforeMethod(alwaysRun = true)
	protected void startTest(Method method){
		loginPage = new LoginPage();
		logger.info("@BeforeMethod ThreadID: {}", Thread.currentThread().getId());
	}

	@BeforeClass(alwaysRun = true)
	public void setup(ITestContext context) {
		BrowserManager.browserSetup();
		context.setAttribute(ConfigData.SYSTEM_PROPERTY_WEBDRIVERWAIT, getWebDriverWait());

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		logger.info("@AfterClass ThreadID: {}", Thread.currentThread().getId());
		wait.remove();
		Browser.close();
	}
}