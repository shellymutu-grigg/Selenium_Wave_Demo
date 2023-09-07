package com.automation.test.configuration;

import com.tidal.wave.browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeDriverConfig {

	public static WebDriver setUpChromeDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("window-size=1920,1080");
		chromeOptions.addArguments("--ignore-ssl-errors=yes");
		chromeOptions.addArguments("--ignore-certificate-errors");
		//chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		Browser.withOptions(chromeOptions)
				//Your custom wait time, otherwise the default wait time of 5 seconds applies
				.withWaitTime(Duration.ofSeconds(15));
		return new ChromeDriver(chromeOptions);
	}
}
