package com.qualiTest.test.stepDefinitions.web;

import com.qualiTest.test.framework.Helpers.UrlBuilder;
import cucumber.api.java.en.Given;

public class NavigationSteps {
    @Given("^User is on the homepage$")
    public void userIsOnTheHomepage() throws Throwable {
        UrlBuilder.openHomePage();
    }
}
