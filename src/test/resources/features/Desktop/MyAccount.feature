@web @MyAccount
Feature: MyAccount - Desktop

#As a user I should be able to update user details -first name
  Scenario: Verify the payment amount is as per the accommodation selected
    Given User is on the homepage
    When registers as new user
    And user changes the first name
    Then account first name should be updated