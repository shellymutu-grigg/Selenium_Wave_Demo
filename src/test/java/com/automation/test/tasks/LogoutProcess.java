package com.automation.test.tasks;

import com.automation.test.actions.Get;
import com.automation.test.data.LocalStore;
import com.automation.test.data.PageTitleData;
import com.automation.test.data.TextData;
import com.tidal.wave.browser.Browser;
import com.tidal.wave.page.Page;
import org.openqa.selenium.WebDriver;

import static com.tidal.flow.assertions.Soft.verify;
import static com.tidal.wave.webelement.ElementFinder.find;

public class LogoutProcess {
	
	public void logout(LogoutPage logoutPage) {
		WebDriver webDriver = (WebDriver) LocalStore.getObject(String.valueOf(Thread.currentThread().getId()));

		verify("logout() action has resulted in the page having the title: " + PageTitleData.LANDING_PAGE_TITLE,PageTitleData.LANDING_PAGE_TITLE).equalsIgnoringCase(Page.title());
		String deliverText = find(TextData.LOGGED_IN_TEXT).getText();
		verify("Element with text: " + TextData.LOGGED_IN_TEXT, deliverText).isNotNullOrEmpty();

		logoutPage.openAccountMenu();
		String accountText = find(TextData.LOGGED_IN_TEXT).getText();
		verify("Element with text: " + TextData.LOGGED_IN_TEXT, accountText).isNotNullOrEmpty();

		logoutPage.logout();
		verify("logout() action has resulted in the page having the title: " + PageTitleData.SIGN_IN_PAGE_TITLE,PageTitleData.SIGN_IN_PAGE_TITLE).equalsIgnoringCase(Page.title());
		String logoutText = find(TextData.SIGN_IN_TEXT).getText();
		verify("Element with text: " + TextData.SIGN_IN_TEXT, logoutText).isNotNullOrEmpty();

		Browser.open(Get.url());
	}
}
