package com.automation.test.configuration;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverConfig {

	public static EdgeOptions edgeOptions() {
		EdgeOptions edgeOptions = new EdgeOptions();
		//edgeOptions.addArguments("--headless=new");

		return edgeOptions;
	}
}
