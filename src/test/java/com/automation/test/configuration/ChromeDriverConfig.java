package com.automation.test.configuration;

import com.automation.test.actions.Get;
import com.automation.test.data.ConfigData;
import com.tidal.wave.browser.Browser;
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;

public class ChromeDriverConfig {

	public static void setupChromeDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("window-size=1920,1080");
		chromeOptions.addArguments("--ignore-ssl-errors=yes");
		chromeOptions.addArguments("--ignore-certificate-errors");
		boolean headless = Boolean.parseBoolean(Get.globalProperty(ConfigData.HEADLESS));
		if (headless) {
			chromeOptions.addArguments("--headless=new");
		}
		Browser.withOptions(chromeOptions)
                    .withWaitTime(Duration.ofSeconds(15))
                    .open(Get.url());
	}
}
