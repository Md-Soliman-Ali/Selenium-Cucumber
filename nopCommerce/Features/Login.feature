#Given: Step is used to set the context
#When: Step is the action/interaction with the system
#Then: Step for representing the outcome
#And: Step for extending the previous step

Feature: Login

  @sanity
  Scenario: Successful login with valid credentials
    Given user launch chrome browser
    When user opens url "http://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And click on login
    Then page title should be "Dashboard / nopCommerce administration"
    When user click on log out link
    Then page title should be "Your store. Login"
    And close browser

  @regression
  Scenario Outline: Login Data Driven
    Given user launch chrome browser
    When user opens url "http://admin-demo.nopcommerce.com/login"
    And user enters email as "<email>" and password as "<password>"
    And click on login
    Then page title should be "Dashboard / nopCommerce administration"
    When user click on log out link
    Then page title should be "Your store. Login"
    And close browser

    Examples:
      | email               | password |
      | admin@yourstore.com | admin    |