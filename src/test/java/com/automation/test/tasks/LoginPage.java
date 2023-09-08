package com.automation.test.tasks;

import com.tidal.wave.browser.Browser;
import static com.tidal.wave.webelement.ElementFinder.find;
import static com.tidal.wave.webelement.ElementFinder.findAll;
import org.testng.ITestListener;

import com.automation.test.actions.Get;
import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import com.automation.test.data.TextData;
import com.automation.test.locators.LoginLocators;

import java.util.Objects;

public class LoginPage implements ITestListener{

	public void navigateToURL() {
		// If the user encounters an amazon security prompt click 'Try different image' link to navigate to landing page
		if (findAll(TextData.TRY_DIFFERENT_IMAGE_TEXT).isPresent()) {
			find(TextData.TRY_DIFFERENT_IMAGE_TEXT).click();
		}
		// If the user encounters the 'Your Account' alternative landing page navigate to landing page
		else if(findAll(LoginLocators.LANDING_PAGE_ALT_ID).isPresent()) {
			if(findAll(LoginLocators.YOUR_ACCOUNT_LINK_ID).isPresent()) {
				LocalStore.setObject(ConfigData.SYSTEM_PROPERTY_YOUR_ACCOUNT, TextData.YOUR_ACCOUNT_TEXT);
				find(LoginLocators.YOUR_ACCOUNT_LINK_ID).click();
			}
		}
	}
	
	public void enterUserEmail(String email) {
		find(LoginLocators.USER_EMAIL_ID).sendKeys(email);
		find(LoginLocators.CONTINUE_BUTTON_ID).click();
	}
	
	public void enterUserPassword(String password) {
		find(LoginLocators.USER_PASSWORD_ID).sendKeys(password);
		find(LoginLocators.LOGIN_BUTTON_ID).click();
	}

	public void navigateToLanding() {
		find(TextData.ACCOUNT_MENU_LINK_TEXT).click();
	}
	
	public void checkForPreviousLoginFailure() {
		String accountListText = Objects.requireNonNull(find(LoginLocators.ACCOUNT_MENU_ID).getText());
		if(!accountListText.contains(TextData.LOGGED_OUT_TEXT)) {
			LogoutPage.openAccountMenu();
			LogoutPage.logout();
			Browser.open(Get.url());
		}
	}
}