package com.automation.test.processes;

import java.lang.reflect.Method;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.automation.test.actions.TestCaseName;
import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import com.automation.test.data.PageTitleData;
import com.automation.test.data.TextData;
import com.automation.test.TestSetup;

@Slf4j
public class LoginFailureTest extends TestSetup {
	String email = System.getenv(ConfigData.AMAZON_USERNAME);
	String password = System.getenv(ConfigData.AMAZON_PASSWORD_FAIL);

	Logger logger = LoggerFactory.getLogger(LoginFailureTest.class);
	
	@Test(groups = { "LoginFailure" },
			priority = 1,
			description = "Entering an incorrect password will result in an error page being displayed to the user")
	/*
		GIVEN a user enters an incorrect password
		WHEN they are on the Enter Password screen
		THEN the application will return an error page
			AND the user will not be logged in
	 */
	public void loginFailureTest(Method method) {
		loginPage.navigateToURL();
		logger.info("{} has navigated to landing page", TestCaseName.convert(method.getName()));

		loginPage.checkForPreviousLoginFailure();
		logger.info("{} has checked for previous login failure", TestCaseName.convert(method.getName()));

		loginPage.navigateToLanding();
		logger.info("{} has successfully navigated to landing page", TestCaseName.convert(method.getName()));

		loginPage.enterUserEmail(email);
		logger.info("{} has successfully entered user email", TestCaseName.convert(method.getName()));

		loginPage.enterUserPassword(password);
		logger.info("{} has successfully entered incorrect user password", TestCaseName.convert(method.getName()));

		String loginFailStatus = loginPage.loginFail();
		String pageTitle;
		if(LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_BROWSER).toString().contains(ConfigData.CHROME_DRIVER) ||
				LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_BROWSER).toString().contains(ConfigData.EDGE_DRIVER)) {
			pageTitle = PageTitleData.SIGN_IN_PAGE_TITLE;
		} else {
			pageTitle = PageTitleData.LANDING_PAGE_TITLE;
		}
		if(Objects.equals(loginFailStatus, TextData.LOGIN_FAILURE_ALERT_TEXT)) {
			// TODO complete functionality
			logger.info("loginFailStatus is: {} & TextData.LOGIN_FAILURE_ALERT_TEXT is: {} & pageTitle is: {}", loginFailStatus, TextData.LOGIN_FAILURE_ALERT_TEXT, pageTitle);
		} else if(Objects.equals(loginFailStatus, TextData.LOGIN_PUZZLE_TEXT)) {
			// TODO complete functionality
			logger.info("loginFailStatus is: {} & TextData.LOGIN_FAILURE_ALERT_TEXT is: {} & pageTitle is: {}", loginFailStatus, TextData.LOGIN_PUZZLE_TEXT, pageTitle);
		} else if(Objects.equals(loginFailStatus, TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT)) {
			// TODO complete functionality
			logger.info("loginFailStatus is: {} & TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT is: {} & pageTitle is: {}", loginFailStatus, TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT, pageTitle);
		}
		logger.info("{} has successfully failed to login", TestCaseName.convert(method.getName()));
	}
}
