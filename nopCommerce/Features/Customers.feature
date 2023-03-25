Feature: Customers

  Background: Below are common steps for every scenario
    Given user launch chrome browser
    When user opens url "http://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And click on login
    Then User can view Dashboard

  @sanity
  Scenario: Add New Customer
    When User click on customers menu
    And click on customers menu item
    And click on add new button
    Then User can view add new customer page
    When User enter customer info
    And click on Save button
    Then User can view confirmation message "The new customer has been added successfully"
    And close browser

  @regression
  Scenario: Search Customer by EmailID
    When User click on customers menu
    And click on customers menu item
    And Enter Customer Email
    When Click on Search button
    Then User should found Email in the search table
    And close browser

  @regression
  Scenario: Search Customer by Name
    When User click on customers menu
    And click on customers menu item
    And Enter Customer FirstName
    And Enter Customer LastName
    When Click on Search button
    Then User should found Name in the search table
    And close browser