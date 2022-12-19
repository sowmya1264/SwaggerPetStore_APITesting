Feature: To verify User Login Operation APIs.


  Scenario Outline: Verify login feature for pet store with valid username and valid password
    Given I want to login to an user account at "<url>" with valid "<username>" and valid "<password>"
    When  I submit a get request
    Then  I get "<status code>"
    And   with valid response "<message>"
    Examples:
      | url          | username      | password | status code      | message                           |
      | /user/login  | user20        | password | 200              | logged in user session            |
      | /user/login  | user875       | invalid  | 400              | Invalid username/password supplied|
      | /user/login  | userinvalid   | invalid  | 400              | Invalid username/password supplied|
