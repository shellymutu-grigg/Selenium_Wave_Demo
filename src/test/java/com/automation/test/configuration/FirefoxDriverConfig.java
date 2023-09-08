package com.automation.test.configuration;

import com.automation.test.actions.Get;
import com.tidal.wave.browser.Browser;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirefoxDriverConfig {

	public static void firefoxDriverSetup() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		//firefoxOptions.addArguments("--headless=new");
		firefoxOptions.addArguments("window-size=1920,1080");
		firefoxOptions.addArguments("window-position=10,10");
		Browser.withOptions(firefoxOptions)
				.type("firefox")
				.withWaitTime(Duration.ofSeconds(15))
				.open(Get.url());
	}
}
