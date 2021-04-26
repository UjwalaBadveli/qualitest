package com.qualiTest.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features", tags = {"@web"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runWeb",
        "json:target/cucumber-report/runWeb/cucumber.json",
        "rerun:target/cucumber-report/runWeb/rerun.txt"},
        glue = "com.qualiTest.test")
public class RunWebATSuite extends AbstractTestNGCucumberTests {
}
