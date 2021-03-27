Feature: Customers

  Background: Below are the common steps for each scenario
    Given User launch chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And Clicks on login button
    Then User can view dashboard
    When User cliks on Customers menu
    And cliks on Customers menu item

  @Sanity
  Scenario: Add a new customer
    And cliks on Add new button
    Then User can view add new customer page
    When user enters customer info
    And clicks on save button
    Then user can view confirmation message "The new customer has been added successfully."
    And Closes browser

  @Regression
  Scenario: Search Customer by Email Id
    And Enters customer email
    When click on search button
    Then User should found email in the search table
    And Closes browser

  @Regression
  Scenario: Search Customer by Name
    And Enters customer first name
    And Enters customer last name
    When click on search button
    Then User should found name in the search table
    And Closes browser
