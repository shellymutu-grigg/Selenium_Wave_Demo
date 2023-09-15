package com.automation.test.configuration;

import com.automation.test.actions.Get;
import com.tidal.wave.browser.Browser;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class EdgeDriverConfig {

	public static void setupEdgeDriver() {
		EdgeOptions edgeOptions = new EdgeOptions();
		//edgeOptions.addArguments("--headless=new");
		edgeOptions.addArguments("window-size=1920,1080");
		edgeOptions.addArguments("window-position=10,10");
		Browser.withOptions(edgeOptions)
				.type("edge")
				.withWaitTime(Duration.ofSeconds(15))
				.open(Get.url());
	}
}
