package com.qualiTest.test.pageObjects;

import com.qualiTest.test.framework.PageObject;
import com.qualiTest.test.model.CustomerDetail;
import org.openqa.selenium.By;

import java.util.stream.Collectors;

public class CheckoutPage extends PageObject {
   private By proceedToCheckout = By.cssSelector(".cart_navigation [title='Proceed to checkout']");
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
   private By proceedToCheckoutFrmAddress = By.cssSelector("[name='processAddress']");
   private By termsAndConditions = By.id("cgv");
   private By getProceedToCheckoutFrmShipping = By.cssSelector("[name='processCarrier']");
   private By paymentMethods = By.cssSelector(".payment_module");
   private By confirmOrderButton = By.cssSelector("#cart_navigation button[type='submit']");

   public void proceedToCheckout(){
      waitForExpectedElement(proceedToCheckout).click();
   }

   public void createAccount(CustomerDetail person){
      waitForExpectedElement(createAccountEmailInput).sendKeys(person.getEmail());
      waitForExpectedElement(createAccountButton).click();
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

   public void proceedFromAddress() {
      waitForExpectedElement(proceedToCheckoutFrmAddress).click();
   }

   public void selectTermsAndConditions() {
      waitForElementToPresent(termsAndConditions).click();
   }

   public void proceedFromShipping() {
      selectTermsAndConditions();
      waitForExpectedElement(getProceedToCheckoutFrmShipping).click();
   }

   public void payUsingPaymentMethod(String paymentMethod) {
      waitForExpectedElements(paymentMethods).stream()
              .filter(element -> element.getText().contains(paymentMethod))
              .collect(Collectors.toList())
              .get(0)
              .click();
      waitForExpectedElement(confirmOrderButton).click();
   }
}
