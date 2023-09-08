package com.automation.test.processes;

import com.automation.test.actions.TestCaseName;
import com.automation.test.data.ConfigData;
import com.automation.test.data.PageTitleData;
import com.automation.test.data.TextData;
import com.automation.test.locators.LoginLocators;
import com.automation.test.locators.LogoutLocators;
import com.automation.test.tasks.LogoutPage;
import com.automation.test.TestSetup;
import com.tidal.wave.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.tidal.wave.webelement.ElementFinder.find;

@Slf4j
public class LoginSuccessTest extends TestSetup{
	String email = System.getenv(ConfigData.AMAZON_USERNAME);
	String password = System.getenv(ConfigData.AMAZON_PASSWORD_SUCCESS);

	Logger logger = LoggerFactory.getLogger(LoginSuccessTest.class);
	
	@Test(groups = { "LoginSuccess" },
			priority = 1,
			description = "Successful login scenario")
	/*
		GIVEN a user enters a correct email and password combination
		WHEN they access the Enter Email and Enter Password screens
		THEN the application will log the user in
			AND the user will be able to review their account
	 */
	public void loginSuccessTest(Method method) {
		loginPage.navigateToURL();
		Assert.assertNotNull(find(TextData.LANDING_PAGE_SIGN_IN_TEXT).getText());
		Assert.assertEquals(PageTitleData.LANDING_PAGE_TITLE, Page.title());
		logger.info("{} has navigated to landing page", TestCaseName.convert(method.getName()));

		loginPage.checkForPreviousLoginFailure();
		logger.info("{} has checked for previous login failure", TestCaseName.convert(method.getName()));

		loginPage.navigateToLanding();
		Assert.assertNotNull(find(LoginLocators.USER_EMAIL_ID).getText());
		Assert.assertEquals(PageTitleData.SIGN_IN_PAGE_TITLE, Page.title());
		logger.info("{} has successfully navigated to landing page", TestCaseName.convert(method.getName()));

		loginPage.enterUserEmail(email);
		Assert.assertNotNull(find(LoginLocators.USER_PASSWORD_ID).getText());
		Assert.assertEquals(PageTitleData.SIGN_IN_PAGE_TITLE, Page.title());
		logger.info("{} has successfully entered user email", TestCaseName.convert(method.getName()));

		loginPage.enterUserPassword(password);
		Assert.assertNotNull(find(TextData.LOGGED_IN_TEXT).getText());
		Assert.assertEquals(PageTitleData.LANDING_PAGE_TITLE, Page.title());
		logger.info("{} has successfully entered user password", TestCaseName.convert(method.getName()));
		logger.info("{} has successfully completed login process", TestCaseName.convert(method.getName()));

		LogoutPage.openAccountMenu();
		Assert.assertNotNull(find(LogoutLocators.LOGOUT_LINK_ID).getText());
		logger.info("{} has successfully opened account menu", TestCaseName.convert(method.getName()));

		LogoutPage.logout();
		Assert.assertEquals(PageTitleData.LANDING_PAGE_TITLE, Page.title());
		logger.info("{} has successfully completed logout process", TestCaseName.convert(method.getName()));
	}
}
