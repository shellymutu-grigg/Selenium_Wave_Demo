package com.automation.test.configuration;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverConfig {

	public static ChromeOptions chromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("window-size=1920,1080");
		chromeOptions.addArguments("--ignore-ssl-errors=yes");
		chromeOptions.addArguments("--ignore-certificate-errors");
		//chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		return chromeOptions;
	}
}
