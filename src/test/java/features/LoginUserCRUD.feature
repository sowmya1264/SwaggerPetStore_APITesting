Feature: To verify the Create,Update,Delete user operation for an logged in user

  Scenario Outline: Verify Login feature for pet store with valid username and valid password
    Given I want to login to an user account at "<url>" with valid "<username>" and valid "<password>"
    When  I submit a get request
    Then  I get "<status code>"
    And   with valid response "<message>"
    Examples:
      | url           | username | password | status code | message                 |
      | /user/login/  | user5    | password | 200         | logged in user session  |


  Scenario Outline: Verify Create user feature for a logged in user
    Given I want to create an user with "<username>" at "<url>" from logged in user
    When  I submit post request
    Then  I get the "<status code>"
    Examples:
      | username     | url    | status code |
      | user20       | /user  | 200         |


  Scenario Outline: Verify Update user feature for a logged in user

    Given I want to update an "<firstname>" of an "<username>" at "<url>" for a logged in user
    When  I submit a put request
    Then  I get the "<status code>"
    Examples:
      | firstname        | username      | url               | status code |
      | TestUserUpdated  | NewTestUser   | user/user2        | 200         |


  Scenario Outline: Verify, delete an user from logged in account
    Given I want to delete an order at "<url>" with an "<username>"
    When  I submit Delete request
    Then  I get the "<status code>"
    Examples:
      | url              | username          | status code |
      | user/user7       | user7             | 200         |


  Scenario Outline: Verify, Get user details of deleted user
    Given I want to get details of an user at "<url>" with an "<username>"
    When  I want submit a get request
    Then  I should get  code "<status code>"
    Examples:
      | url        | username           | status code |
      | user/      | user7              | 404         |


  Scenario Outline: Verify logout feature for pet store
    Given I want to logout from an logged in user account with "<url>"
    When  I submit get request
    Then  I get the "<status code>"
    And   valid response "<message>"
    Examples:
      | url            | status code | message |
      | /user/logout   | 200         | ok      |

