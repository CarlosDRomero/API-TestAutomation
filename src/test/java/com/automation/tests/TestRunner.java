package com.automation.tests;

import com.automation.config.Config;
import lombok.Getter;
import org.testng.annotations.BeforeSuite;

public class TestRunner {
    @Getter
    private static String baseUrl;

    @BeforeSuite
    public void setUpEnvironment() {
        baseUrl = Config.getProperty("url.base");
    }
}
