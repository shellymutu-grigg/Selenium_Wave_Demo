package com.automation.test.configuration;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverConfig {

	public static FirefoxOptions firefoxOptions() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		//firefoxOptions.addArguments("--headless=new");

		return firefoxOptions;
	}
}
