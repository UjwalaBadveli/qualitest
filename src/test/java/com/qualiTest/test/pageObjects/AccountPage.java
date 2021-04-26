package com.qualiTest.test.pageObjects;

import com.qualiTest.test.framework.PageObject;
import com.qualiTest.test.model.CustomerDetail;
import org.openqa.selenium.By;

import java.util.stream.Collectors;

public class AccountPage extends PageObject {
   private By orderHistoryLink = By.cssSelector("[title='Orders']");
   private By orders = By.cssSelector("table[id='order-list'] > tbody > tr");
   private By personalDetails = By.cssSelector("[title='Information']");
    private By createAccountEmailInput = By.id("email_create");
    private By createAccountButton = By.id("SubmitCreate");
    private By genderRadioButtons = By.cssSelector(".radio-inline");
    private By customerFirstName = By.id("customer_firstname");
    private By customerLastName = By.id("customer_lastname");
    private By customerPassword = By.id("passwd");
    private By customerDOBday = By.id("days");
    private By customerDOBmonth = By.id("months");
    private By customerDOByear = By.id("years");
    private By contactAddress1 = By.id("address1");
    private By contactCity = By.id("city");
    private By contactState = By.id("id_state");
    private By contactPostcode = By.id("postcode");
    private By contactMobileNo = By.id("phone_mobile");
    private By registerButton = By.id("submitAccount");
    private By savePersonalDetails = By.name("submitIdentity");
    private By accountFirstName = By.id("firstname");
    private By currentPassword = By.id("old_passwd");

    public void viewOrderHistory() {
       waitForExpectedElement(orderHistoryLink).click();
    }

    public int getNoOFOrders() {
        return waitForExpectedElements(orders).size();
    }

    public void createAccount(CustomerDetail person){
        waitForExpectedElement(createAccountEmailInput).sendKeys(person.getEmail());
        waitForExpectedElement(createAccountButton).click();
        waitForPageLoad();
    }

    public void fillNewCustomerDetail(CustomerDetail customerDetail) {
        //fill the account creating details
        waitForExpectedElements(genderRadioButtons)
                .stream()
                .filter(element -> element.getText().equalsIgnoreCase(customerDetail.getTitle()))
                .collect(Collectors.toList()).get(0)
                .click();
        waitForExpectedElements(customerFirstName).get(0).sendKeys(customerDetail.getFirstName());
        waitForExpectedElements(customerLastName).get(0).sendKeys(customerDetail.getLastName());
        waitForExpectedElement(customerPassword).sendKeys("1qazZAQ!");
        dropDownSelectByValue(waitForElementToPresent(customerDOBday), customerDetail.getDateOfBirth().split("-")[0]);
        dropDownSelectByValue(waitForElementToPresent(customerDOBmonth), customerDetail.getDateOfBirth().split("-")[1]);
        dropDownSelectByValue(waitForElementToPresent(customerDOByear), customerDetail.getDateOfBirth().split("-")[2]);

        //fill address details
        waitForExpectedElement(contactAddress1).sendKeys(customerDetail.getAddressFirstLine());
        waitForExpectedElement(contactCity).sendKeys(customerDetail.getCity());
        dropDownSelectByVisualText(waitForElementToPresent(contactState), customerDetail.getState());
        waitForExpectedElement(contactPostcode).sendKeys(customerDetail.getPostcode());
        waitForExpectedElement(contactMobileNo).sendKeys(customerDetail.getMobileNo());
    }

    public void proceedToRegister() {
        waitForExpectedElement(registerButton).click();
        waitForPageLoad();
    }

    public void viewPersonalInformation(){
        waitForExpectedElement(personalDetails).click();
    }

    public void savePersonalDetails(){
        waitForExpectedElement(savePersonalDetails).click();
    }

    public void enterPassword(String password){
        waitForExpectedElement(currentPassword).sendKeys(password);
    }

    public void updateFirstName(String firstName) {
        waitForExpectedElement(accountFirstName).clear();
        waitForExpectedElement(accountFirstName).sendKeys(firstName);
        enterPassword("1qazZAQ!");
        savePersonalDetails();
    }

}
