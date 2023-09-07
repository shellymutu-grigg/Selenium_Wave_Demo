package com.automation.test.processes;

import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import com.automation.test.data.TextData;
import com.automation.test.actions.Get;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.automation.test.actions.Element;

import java.text.MessageFormat;

@Slf4j
public class LogoutPage {
	static Actions actions;
	
	static By accountLinkBy = By.xpath("//*[contains(text(), '" + TextData.ACCOUNT_MENU_LINK_TEXT + "')]");
	static By logoutLinkBy = By.id("nav-item-signout");

	static Logger logger = LoggerFactory.getLogger(LogoutPage.class);
	
	public static void openAccountMenu() {
		setActions();
		try{
	        actions.moveToElement(Element.getElement(accountLinkBy)).perform();
	    }catch(MoveTargetOutOfBoundsException e){
			logger.error("MoveTargetOutOfBoundsException: {}", e.getMessage());
			throw new MoveTargetOutOfBoundsException(MessageFormat.format("MoveTargetOutOfBoundsException: {0}", e.getMessage()));
	    }
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			logger.error("InterruptedException: {}", e.getMessage());
			throw new RuntimeException(MessageFormat.format("InterruptedException: {0}", e.getMessage()));
		}
	}
	
	public static void logout() {
		WebDriver webDriver = (WebDriver) LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_WEBDRIVER);
		setActions();
		try{
			actions.moveToElement(Element.getElement(logoutLinkBy)).perform();
			Element.click(logoutLinkBy, false);
			webDriver.get(Get.url());
	    }catch(MoveTargetOutOfBoundsException e){
			logger.error("MoveTargetOutOfBoundsException: {}", e.getMessage());
			throw new MoveTargetOutOfBoundsException(MessageFormat.format("MoveTargetOutOfBoundsException: {0}", e.getMessage()));	    }
	}

	public static void setActions(){
		actions = new Actions((WebDriver) LocalStore.getObject(ConfigData.SYSTEM_PROPERTY_WEBDRIVER));
	}
}
