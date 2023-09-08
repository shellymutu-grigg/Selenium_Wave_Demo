package com.automation.test.processes;

import com.automation.test.TestSetup;
import com.automation.test.actions.TestCaseName;
import com.automation.test.data.ConfigData;
import com.automation.test.tasks.LogoutPage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

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
		logger.info("{} has navigated to landing page", TestCaseName.convert(method.getName()));

		loginPage.checkForPreviousLoginFailure();
		logger.info("{} has checked for previous login failure", TestCaseName.convert(method.getName()));

		loginPage.navigateToLanding();
		logger.info("{} has successfully navigated to landing page", TestCaseName.convert(method.getName()));

		loginPage.enterUserEmail(email);
		logger.info("{} has successfully entered user email", TestCaseName.convert(method.getName()));

		loginPage.enterUserPassword(password);
		logger.info("{} has successfully entered user password", TestCaseName.convert(method.getName()));
		logger.info("{} has successfully completed login process", TestCaseName.convert(method.getName()));

		LogoutPage.openAccountMenu();
		logger.info("{} has successfully opened account menu", TestCaseName.convert(method.getName()));

		LogoutPage.logout();
		logger.info("{} has successfully completed logout process", TestCaseName.convert(method.getName()));
	}
}
