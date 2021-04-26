package com.qualiTest.test.stepDefinitions.web;

import com.qualiTest.test.framework.Helpers.Props;
import com.qualiTest.test.pageObjects.HomePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    @Then("^validate the homepage title$")
    public void validateTheHomepageTitle() {
        assertEquals(homePage.getCurrentPageTitle(),Props.getMessage("homePageTitle"),"Miss match in homePage Title");
    }

    @When("^User searches for \"([^\"]*)\" holidays$")
    public void userSearchesForHolidays(String holidayName) throws Throwable {
        homePage.enterSearchWord(holidayName);
        homePage.clickSearchButton();
    }
}
