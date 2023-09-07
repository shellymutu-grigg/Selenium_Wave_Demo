package com.automation.test.processes;

import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import com.automation.test.actions.Get;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestListener;

import com.automation.test.data.PageTitleData;
import com.automation.test.data.TextData;
import com.automation.test.actions.Element;
import com.automation.test.locators.LoginLocators;

import java.util.Objects;

import static com.tidal.wave.webelement.ElementFinder.findAll;

@Slf4j
public class LoginPage implements ITestListener{
	String email = System.getenv(ConfigData.AMAZON_USERNAME);
	By continueButtonBy = By.id("continue");
	By accountMenuBy = By.id("nav-link-accountList-nav-line-1");
	By landingPageBy = By.cssSelector(".navFooterLine");
	By landingPageAltBy = By.cssSelector(".nav-footer-copyright");
	By loginButtonBy = By.id("signInSubmit");
	By loginFailPuzzle = By.id("cvf-page-content");
	By loginFailPuzzleMessageText = By.cssSelector(".a-spacing-mini");
	By loginFailImportantMessage = By.xpath("//*[contains(text(), '" + TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT +"')]");
	By loginFailImportantMessageText = By.xpath("//*[contains(text(), '" + TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT +"')]");
	By loginFailAlert = By.id("auth-error-message-box");
	By loginFailAlertMessageText = By.cssSelector(".a-alert-content");

	By tryDifferentImageBy = By.xpath("//*[contains(text(), '"+ TextData.TRY_DIFFERENT_IMAGE_TEXT +"')]");
	By signInLinkBy = By.xpath("//span[normalize-space()='"+ TextData.ACCOUNT_MENU_LINK_TEXT+"']");
	By userEmailBy = By.id("ap_email");
	By userPasswordBy = By.id("ap_password");
	By yourAccountLinkBy = By.linkText(TextData.YOUR_ACCOUNT_TEXT);
	
	public LogoutPage initialiseLogoutPage() {
		return new LogoutPage();
	}

	static Logger logger = LoggerFactory.getLogger(Element.class);

	public void navigateToURL() {
		if (findAll(LoginLocators.TRY_DIFFERENT_IMAGE_ID).isPresent()) {
			logger.info("{} is the elementText and isPresent(): {}", LoginLocators.TRY_DIFFERENT_IMAGE_ID, findAll(LoginLocators.TRY_DIFFERENT_IMAGE_ID).isPresent());
			Element.click(tryDifferentImageBy, false);
		}
		else if(Element.getElement(landingPageBy).isDisplayed()) {
			Element.getElement(landingPageBy);
		}
		else if(Element.getElement(landingPageAltBy).isDisplayed()) {
			Element.getElement(landingPageAltBy);
			if(Element.getElement(yourAccountLinkBy).isDisplayed()) {
				LocalStore.setObject(ConfigData.SYSTEM_PROPERTY_YOUR_ACCOUNT, TextData.YOUR_ACCOUNT_TEXT);
				Element.click(yourAccountLinkBy, false);
			}
		}
	}
	
	public void enterUserEmail(String email) {
		Element.sendKeys(userEmailBy, email);
		Element.click(continueButtonBy, false);
	}
	
	public void enterUserPassword(String password) {
		Element.sendKeys(userPasswordBy, password);
		Element.click(loginButtonBy, false);
	}

	public void navigateToLanding() {
		Element.click(signInLinkBy, false);
	}
	
	public void checkForPreviousLoginFailure() {
		WebDriver webDriver = (WebDriver) LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_WEBDRIVER);
		String accountListText = Objects.requireNonNull(Element.getElement(accountMenuBy)).getText();
		if(!accountListText.contains(TextData.LOGGED_OUT_TEXT)) {
			initialiseLogoutPage().openAccountMenu();
			initialiseLogoutPage().logout();
			webDriver.get(Get.url());
		}
	}
	
	public String findLoginFailureText() {
		String failureText = "";
		if(Element.isPresent(LoginLocators.LOGIN_FAIL_ALERT_ID)) {
			failureText = TextData.LOGIN_FAILURE_ALERT_TEXT;
		}
		else if(Element.isPresent(LoginLocators.LOGIN_FAIL_PUZZLE_ID)) {
			failureText =  TextData.LOGIN_PUZZLE_TEXT;
		}
		else if(Element.isPresent(LoginLocators.LOGIN_FAIL_IMPORANT_MESSAGE_ID)) {
			failureText =  TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT;
		}
		return failureText;
	}
	
	public String loginFail() {
		String loginFailStatus = "";
		if(Element.isPresent(LoginLocators.LOGIN_FAIL_ALERT_ID)) {
			loginFailStatus = TextData.LOGIN_FAILURE_ALERT_TEXT;
			validateLoginFailure(loginFailAlertMessageText, TextData.LOGIN_FAILURE_ALERT_TEXT);
		}
		else if(Element.isPresent(LoginLocators.LOGIN_FAIL_PUZZLE_ID)) {
			loginFailStatus =  TextData.LOGIN_PUZZLE_TEXT;
			validateLoginFailure(loginFailPuzzleMessageText, TextData.LOGIN_PUZZLE_TEXT);
		}
		else if(Element.isPresent(LoginLocators.LOGIN_FAIL_IMPORANT_MESSAGE_ID)) {
			loginFailStatus = TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT;
			validateLoginFailure(loginFailImportantMessageText, TextData.LOGIN_FAILURE_IMPORTANT_MESSAGE_TEXT);
		}
		return loginFailStatus;
	}
	
	public void validateLoginFailure(By elementMessageText, String expectedText) {
		String messageText = Objects.requireNonNull(Element.getElement(elementMessageText)).getText();
		Assert.assertEquals(messageText, expectedText);
	}

	public void completeLogin(String password, LoginPage loginPage) {
		loginPage.checkForPreviousLoginFailure();

		String pageTitle;
		String pageText;
		// If the landing page has the "Your Account" link in it, process it
		if(LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_YOUR_ACCOUNT) == TextData.YOUR_ACCOUNT_TEXT) {
			pageTitle = PageTitleData.LOGGED_IN_LANDING_PAGE_TITLE;
			pageText = TextData.DELIVER_TO_TEXT;
		} else {
			pageTitle = PageTitleData.LANDING_PAGE_TITLE;
			pageText = TextData.LANDING_PAGE_SIGN_IN_TEXT;
		}
		loginPage.navigateToLanding();

		loginPage.enterUserEmail(email);

		loginPage.enterUserPassword(password);
	}
}