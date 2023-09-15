package com.automation.test.configuration;

import com.automation.test.actions.TestCaseName;
import com.tidal.wave.browser.Browser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

@Slf4j
public class SetupListener implements IInvokedMethodListener {
    static Logger logger = LoggerFactory.getLogger(SetupListener.class);
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        if (method.isTestMethod()) {
            BrowserManager.browserSetup(result);
            logger.info("Thread {} ({}) has set up a new Browser", Thread.currentThread().getId(), TestCaseName.convert(result.getMethod().getMethodName()));
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        Browser.close();
    }
}
