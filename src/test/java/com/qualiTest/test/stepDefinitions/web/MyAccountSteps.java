package com.qualiTest.test.stepDefinitions.web;

import com.qualiTest.test.model.CustomerDetail;
import com.qualiTest.test.pageObjects.AccountPage;
import com.qualiTest.test.pageObjects.HomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

import static org.testng.Assert.assertEquals;

public class MyAccountSteps {
    AccountPage accountPage = new AccountPage();
    HomePage homePage = new HomePage();

    @When("^registers as new user$")
    public void registersAsNewUser() throws Throwable {
        homePage.selectLoginUser();
        Person person = Fairy.create(Locale.US).person(PersonProperties.minAge(18));
        DateTimeFormatter fmt = DateTimeFormat.forPattern("d-M-yyyy");
        CustomerDetail customerDetail = CustomerDetail.builder()
                .title("Mr.")
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .mobileNo(person.getTelephoneNumber())
                .addressFirstLine(person.getAddress().getAddressLine1())
                .city(person.getAddress().getCity())
                .dateOfBirth(person.getDateOfBirth().toLocalDate().toString(fmt))
                .postcode(person.getAddress().getPostalCode())
                .state("Georgia")
                .build();
        accountPage.createAccount(customerDetail);
        accountPage.fillNewCustomerDetail(customerDetail);
        accountPage.proceedToRegister();
    }

    @And("^user changes the first name$")
    public void userChangesTheFirstName() {
        homePage.viewAccountDetails();
        accountPage.viewPersonalInformation();
        accountPage.updateFirstName("Hello");
    }

    @Then("^account first name should be updated$")
    public void accountFirstNameShouldBeUpdated() {
        assertEquals(homePage.getUserNameFromHeader().split(" ")[0],"Hello","first name is not updated");
    }
}
