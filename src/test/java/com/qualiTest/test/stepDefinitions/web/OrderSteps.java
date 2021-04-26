package com.qualiTest.test.stepDefinitions.web;

import com.qualiTest.test.model.CustomerDetail;
import com.qualiTest.test.pageObjects.AccountPage;
import com.qualiTest.test.pageObjects.CheckoutPage;
import com.qualiTest.test.pageObjects.HomePage;
import com.qualiTest.test.pageObjects.SearchResultsPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import static org.testng.Assert.assertEquals;

import java.util.Locale;

public class OrderSteps {
    HomePage homePage = new HomePage();
    SearchResultsPage searchPage = new SearchResultsPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    AccountPage accountPage = new AccountPage();

    @When("^user places an order for \"([^\"]*)\"$")
    public void userPlacesAnOrderFor(String searchProduct) {
        homePage.enterSearchWord(searchProduct);
        homePage.clickSearchButton();
        searchPage.addItemToCart(searchPage.getSearchResults().get(0));
        searchPage.proceedToCheckout();
        checkoutPage.proceedToCheckout();

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
        checkoutPage.createAccount(customerDetail);
        checkoutPage.fillNewCustomerDetail(customerDetail);
        checkoutPage.proceedToRegister();
        checkoutPage.proceedFromAddress();
        checkoutPage.proceedFromShipping();
        checkoutPage.payUsingPaymentMethod("wire");
    }

    @Then("^validate the order is displayed in order history$")
    public void validateTheOrderIsDisplayedInOrderHistory() {
        homePage.viewAccountDetails();
        accountPage.viewOrderHistory();
        assertEquals(accountPage.getNoOFOrders(),1,"Order not displayed in order history");
    }
}
