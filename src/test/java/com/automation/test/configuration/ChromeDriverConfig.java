package com.automation.test.configuration;

import com.automation.test.actions.Get;
import com.tidal.wave.browser.Browser;
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;

public class ChromeDriverConfig {

	public static void chromeDriverSetup() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("window-size=1920,1080");
		chromeOptions.addArguments("--ignore-ssl-errors=yes");
		chromeOptions.addArguments("--ignore-certificate-errors");
		//chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("window-position=10,10");
		Browser.withOptions(chromeOptions)
                    .withWaitTime(Duration.ofSeconds(15))
                    .open(Get.url());
	}
}
