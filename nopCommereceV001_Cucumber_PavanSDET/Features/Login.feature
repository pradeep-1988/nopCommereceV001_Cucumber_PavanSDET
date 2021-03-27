Feature: Login

  @Sanity
  Scenario: Successfull login with valid credentials
    Given User launch chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And Clicks on login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User clicks on logout
    Then Page title should be "Your store. Login"
    And Closes browser

  @Regression
  Scenario Outline: Login data driven
    Given User launch chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "<email>" and password as "<password>"
    And Clicks on login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User clicks on logout
    Then Page title should be "Your store. Login"
    And Closes browser

    Examples: 
      | email               | password |
      | admin@yourstore.com | admin    |
      | admin@yourstore.com | admin2   |
