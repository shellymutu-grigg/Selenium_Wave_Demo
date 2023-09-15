package com.automation.test.processes;

import com.automation.test.actions.TestCaseName;
import com.automation.test.configuration.SetupListener;
import com.automation.test.data.ConfigData;
import com.automation.test.data.PageTitleData;
import com.automation.test.data.TextData;

import com.automation.test.locators.LogoutLocators;
import com.automation.test.tasks.LoginPage;
import com.automation.test.tasks.LoginProcess;
import com.automation.test.tasks.LogoutPage;
import com.automation.test.tasks.LogoutProcess;
import com.tidal.wave.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Objects;

import static com.tidal.flow.assertions.Soft.verify;
import static com.tidal.wave.webelement.ElementFinder.find;

@Listeners({SetupListener.class})
@Slf4j
public class LoginSuccessTest {
	LoginProcess loginProcess = new LoginProcess();
	LogoutProcess logoutProcess = new LogoutProcess();
	String password = System.getenv(ConfigData.AMAZON_PASSWORD_SUCCESS);

	Logger logger = LoggerFactory.getLogger(LoginSuccessTest.class);
	
	@Test(groups = { "LoginSuccess", "Smoke" },
			priority = 1,
			description = "Successful login scenario")
	/*
		GIVEN a user enters a correct email and password combination
		WHEN they access the Enter Email and Enter Password screens
		THEN the application will log the user in
			AND the user will be able to see the logout option in the account menu
	 */
	public void loginSuccessTest(Method method) {
		LoginPage loginPage = new LoginPage();
		loginPage.navigateToURL();
		logger.info("Thread {} ({}) has navigated to landing page", Thread.currentThread().getId(), TestCaseName.convert(method.getName()));

		loginProcess.completeLogin(password, loginPage);
		logger.info("Thread {} ({}) has successfully entered user email and password", Thread.currentThread().getId(), TestCaseName.convert(method.getName()));

		if(Objects.equals(Page.title(), PageTitleData.SIGN_IN_PAGE_TITLE)){
			String loginFailureText = loginPage.findLoginFailureText();
			logger.info("Thread {} ({}) has loginFailureText of '{}'", Thread.currentThread().getId(), TestCaseName.convert(method.getName()), loginFailureText);
			if(loginFailureText !=""){
				if(loginFailureText.contains(TextData.LOGIN_FAILURE_ALERT_TEXT)) {
					logger.info("Thread {} ({}) has encountered error ", Thread.currentThread().getId(), TestCaseName.convert(method.getName()), loginFailureText);
					verify("completeLogin() action has resulted in the page having the title: " + PageTitleData.SIGN_IN_PAGE_TITLE, PageTitleData.SIGN_IN_PAGE_TITLE).equalsIgnoringCase(Page.title());
					String loginFailureAlertText = find(TextData.LOGIN_FAILURE_ALERT_TEXT).getText();
					verify("Element with text: " + TextData.LOGIN_FAILURE_ALERT_TEXT, loginFailureAlertText).isNotNullOrEmpty();
				} else if(loginFailureText.contains(TextData.LOGIN_FAILURE_PUZZLE_TEXT)) {
					logger.info("Thread {} ({}) has encountered error ", Thread.currentThread().getId(), TestCaseName.convert(method.getName()), loginFailureText);
					verify("completeLogin() action has resulted in the page having the title: " + PageTitleData.SIGN_IN_PAGE_TITLE, PageTitleData.SIGN_IN_PAGE_TITLE).equalsIgnoringCase(Page.title());
					String loginFailureMessageText = find(TextData.LOGIN_FAILURE_PUZZLE_TEXT).getText();
					verify("Element with text: " + TextData.LOGIN_FAILURE_PUZZLE_TEXT, loginFailureMessageText).isNotNullOrEmpty();
				} else if(loginFailureText.contains(TextData.LOGIN_FAILURE_PUZZLE_TEXT)) {
					logger.info("Thread {} ({}) has encountered error ", Thread.currentThread().getId(), TestCaseName.convert(method.getName()), loginFailureText);
					verify("completeLogin() action has resulted in the page having the title: " + PageTitleData.SIGN_IN_PAGE_TITLE, PageTitleData.SIGN_IN_PAGE_TITLE).equalsIgnoringCase(Page.title());
					String loginFailureMessageText = find(TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT).getText();
					verify("Element with text: " + TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT, loginFailureMessageText).isNotNullOrEmpty();
				}
			}
		}
		logger.info("Thread {} ({}) has successfully completed login process", Thread.currentThread().getId(), TestCaseName.convert(method.getName()));

		LogoutPage.openAccountMenu();
		String accountText = find(LogoutLocators.SIGN_OUT_LINK_ID).getText();
		verify("Element with text: " + TextData.SIGN_OUT_TEXT, accountText).isNotNullOrEmpty();

		LogoutPage logoutPage = loginPage.initialiseLogoutPage();
		logoutProcess.logout(logoutPage);
		logger.info("Thread {} ({}) has successfully completed logout process", Thread.currentThread().getId(), TestCaseName.convert(method.getName()));
	}
}
