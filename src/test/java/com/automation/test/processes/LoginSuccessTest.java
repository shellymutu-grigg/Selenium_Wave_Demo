package com.automation.test.processes;

import com.automation.test.TestSetup;
import com.automation.test.actions.TestCaseName;
import com.automation.test.data.ConfigData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.ExtentListeners;

import java.lang.reflect.Method;

@Listeners(ExtentListeners.class)
@Slf4j
public class LoginSuccessTest extends TestSetup{
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

		loginPage.completeLogin(password, loginPage);
		logger.info("{} has successfully entered user email and password", TestCaseName.convert(method.getName()));

		logger.info("{} has successfully completed login process", TestCaseName.convert(method.getName()));

		LogoutPage.openAccountMenu();
		LogoutPage.logout();
		logger.info("{} has successfully completed logout process", TestCaseName.convert(method.getName()));
	}
}
