package com.automation.test.tasks;

import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import com.automation.test.data.PageTitleData;
import com.automation.test.data.TextData;
import com.automation.test.locators.LoginLocators;
import com.tidal.wave.page.Page;

import static com.tidal.flow.assertions.Soft.verify;
import static com.tidal.wave.webelement.ElementFinder.find;

public class LoginProcess {
	String email = System.getenv(ConfigData.AMAZON_USERNAME);
	
	public void completeLogin(String password, LoginPage loginPage) {
		loginPage.checkForPreviousLoginFailure();
		
		String pageTitle;
		String pageText;
		// If the landing page has the "Your Account" link in it, process it
		if(LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_YOUR_ACCOUNT) == TextData.YOUR_ACCOUNT_TEXT) {
			pageTitle = PageTitleData.LOGGED_IN_LANDING_PAGE_TITLE;
			pageText = TextData.LANDING_PAGE_SIGN_IN_TEXT;
		} else {
			pageTitle = PageTitleData.LANDING_PAGE_TITLE;
			pageText = TextData.LANDING_PAGE_SIGN_IN_TEXT;
		}

		verify("navigateToURL() action has resulted in the page having the title: " + pageTitle, pageTitle).equalsIgnoringCase(Page.title());
		String pageTitleText = find(pageText).getText();
		verify("Element with text: " + pageText, pageTitleText).isNotNullOrEmpty();

		loginPage.navigateToLanding();
		verify("navigateToLanding() action has resulted in the page having the title: " + PageTitleData.SIGN_IN_PAGE_TITLE, PageTitleData.SIGN_IN_PAGE_TITLE).equalsIgnoringCase(Page.title());
		String signInText = find(LoginLocators.SIGN_IN_ID).getText();
		verify("Element with text: " + TextData.SIGN_IN_TEXT, signInText).isNotNullOrEmpty();

		loginPage.enterUserEmail(email);
		String keepSignedInText = find(LoginLocators.KEEP_SIGNED_IN_ID).getText();
		verify("Element with text: " + TextData.KEEP_SIGNED_IN_TEXT, keepSignedInText).isNotNullOrEmpty();

		loginPage.enterUserPassword(password);
	}
}
