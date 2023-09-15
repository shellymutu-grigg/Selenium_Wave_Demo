package com.automation.test.processes;

import java.lang.reflect.Method;

import com.automation.test.configuration.SetupListener;
import com.automation.test.tasks.LoginPage;
import com.automation.test.tasks.LoginProcess;
import com.tidal.wave.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.test.actions.TestCaseName;
import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import com.automation.test.data.PageTitleData;
import com.automation.test.data.TextData;

import static com.tidal.flow.assertions.Soft.verify;
import static com.tidal.wave.webelement.ElementFinder.find;

@Listeners({SetupListener.class})
@Slf4j
public class LoginFailureTest {
	LoginProcess loginProcess = new LoginProcess();
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
		LoginPage loginPage = new LoginPage();
		loginPage.navigateToURL();
		logger.info("Thread {} ({}) has navigated to landing page", Thread.currentThread().getId(), TestCaseName.convert(method.getName()));

		loginProcess.completeLogin(password, loginPage);
		logger.info("Thread {} ({}) has successfully completed login process", Thread.currentThread().getId(), TestCaseName.convert(method.getName()));

		String pageTitle;
		if(LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_BROWSER).toString().contains(ConfigData.CHROME_DRIVER) ||
				LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_BROWSER).toString().contains(ConfigData.EDGE_DRIVER)) {
			pageTitle = PageTitleData.SIGN_IN_PAGE_TITLE;
		} else {
			pageTitle = PageTitleData.LANDING_PAGE_TITLE;
		}
		String loginFailureText = loginPage.findLoginFailureText();
		if(loginFailureText.contains(TextData.LOGIN_FAILURE_ALERT_TEXT)) {
			logger.info("Thread {} ({}) has encountered error ", Thread.currentThread().getId(), TestCaseName.convert(method.getName()), loginFailureText);
			verify("enterUserDetails(email, password) with incorrect password action has resulted in the page having the title: " + pageTitle, pageTitle).equalsIgnoringCase(Page.title());
			String loginFailureAlertText = find(TextData.LOGIN_FAILURE_ALERT_TEXT).getText();
			verify("Element with text: " + TextData.LOGIN_FAILURE_ALERT_TEXT, loginFailureAlertText).isNotNullOrEmpty();
		} else if(loginFailureText.contains(TextData.LOGIN_FAILURE_PUZZLE_TEXT)) {
			logger.info("Thread {} ({}) has encountered error ", Thread.currentThread().getId(), TestCaseName.convert(method.getName()), loginFailureText);
			verify("enterUserDetails(email, password) with incorrect password action has resulted in the page having the title: " + PageTitleData.AUTHENTICATION_REQUIRED_PAGE_TITLE, PageTitleData.AUTHENTICATION_REQUIRED_PAGE_TITLE).equalsIgnoringCase(Page.title());
			String loginFailureMessageText = find(TextData.LOGIN_FAILURE_PUZZLE_TEXT).getText();
			verify("Element with text: " + TextData.LOGIN_FAILURE_PUZZLE_TEXT, loginFailureMessageText).isNotNullOrEmpty();
		} else if(loginFailureText.contains(TextData.LOGIN_FAILURE_PUZZLE_TEXT)) {
			logger.info("Thread {} ({}) has encountered error ", Thread.currentThread().getId(), TestCaseName.convert(method.getName()), loginFailureText);
			verify("enterUserDetails(email, password) with incorrect password action has resulted in the page having the title: " + PageTitleData.SIGN_IN_PAGE_TITLE, PageTitleData.SIGN_IN_PAGE_TITLE).equalsIgnoringCase(Page.title());
			String loginFailureMessageText = find(TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT).getText();
			verify("Element with text: " + TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT, loginFailureMessageText).isNotNullOrEmpty();
		}
		logger.info("Thread {} ({}) has successfully failed to login with error: '{}'", Thread.currentThread().getId(), TestCaseName.convert(method.getName()), loginFailureText);
	}
}
