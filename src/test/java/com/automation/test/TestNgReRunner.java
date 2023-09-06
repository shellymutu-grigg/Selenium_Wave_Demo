package com.automation.test;

import com.tidal.utils.junit.ResultParser;
import com.tidal.utils.report.ReportBuilder;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"@target/rerun/failed_scenarios.txt"},
        glue = {"com.automation.test", "com.tidal.cukes"
        }
)
public class TestNgReRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterTest
    public void parseTestResult() {
        ResultParser.parseReRunnerResult();
        ReportBuilder.createReport();
    }
}
