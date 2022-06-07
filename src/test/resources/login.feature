Feature: User can login to an e-commerce site

  Scenario Outline: verify users can login to the portal with valid credentials
    Given user visits e-commerce website
    When user enters valid "<email>" and "<password>"
    Then user can logged in successfully

    Examples:
      | email                 | password    |
      | testuser412@grr.la    | 2t8zmqzL    |
      | testuser4554@test.com | P@ssword123 |
