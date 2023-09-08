package com.automation.test;

import java.lang.reflect.Method;
import com.tidal.wave.browser.Browser;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.*;

import com.automation.test.configuration.BrowserManager;
import com.automation.test.tasks.LoginPage;

@Slf4j
public class TestSetup {

	public LoginPage loginPage;

	@BeforeMethod(alwaysRun = true)
	protected void startTest(Method method){
		loginPage = new LoginPage();
	}
	@BeforeClass(alwaysRun = true)
	public void setup(ITestContext context) {
		BrowserManager.browserSetup();
	}

	@AfterClass(alwaysRun = true)
	public void closeWebDriver () {
		Browser.close();
	}
}