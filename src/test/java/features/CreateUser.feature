Feature: To verify User Operation APIs.

  Scenario Outline: Verify, create list of user with input array.
    Given I want to create an user at "<url>"
    When  I submit a post request
    Then  should get the success "<status code>"
    And   the valid response "<message>"
    Examples:
      | url                        | status code | message |
      | user/createWithArray       | 200         | ok      |


  Scenario Outline: Verify, create list of user with input list.
    Given I want to create an user at "<url>" with list
    When  I submit a post request
    Then  should get the success "<status code>"
    And   the valid response "<message>"
    Examples:
      | url                        | status code | message |
      | user/createWithList        | 200         | ok      |

