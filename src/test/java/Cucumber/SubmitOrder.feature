@tag
Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> add submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page




    Examples:
      | name             | password     | productName |
      | frank0@gmail.com | Georgesmith1 | ZARA COAT 3 |