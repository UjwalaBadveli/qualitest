@web @orderDetails
Feature:OrderHistory - Desktop
#As a user I should be view the trip details and proceed to book

#validate the payment amount as per the accommodation selected
  Scenario: Verify the payment amount is as per the accommodation selected
    Given User is on the homepage
    When user places an order for "T-Shirt"
    Then validate the order is displayed in order history