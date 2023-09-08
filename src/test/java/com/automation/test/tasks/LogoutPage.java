package com.automation.test.tasks;

import com.tidal.wave.browser.Browser;
import lombok.extern.slf4j.Slf4j;

import com.automation.test.actions.Get;
import com.automation.test.data.TextData;
import com.automation.test.locators.LogoutLocators;

import static com.tidal.wave.webelement.ElementFinder.find;

@Slf4j
public class LogoutPage {
	
	public static void openAccountMenu() {
		find(TextData.ACCOUNT_MENU_LINK_TEXT).moveToElement();
	}
	
	public static void logout() {
		find(LogoutLocators.LOGOUT_LINK_ID).click();
		Browser.open(Get.url());
	}
}
