package com.automation.test;

import com.tidal.utils.junit.ResultParser;
import com.tidal.utils.report.ReportBuilder;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/"},
        glue = {"com.automation.test", "com.tidal.cukes"
        },
        plugin = {
                "rerun:target/rerun/failed_scenarios.txt", "html:report"
        }
)
public class TestNgRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterTest
    public void parseTestResult() {
        ResultParser.parseRunnerResult();
        ReportBuilder.createRunnerReport();
    }

}

