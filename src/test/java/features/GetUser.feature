Feature: To verify User Operation APIs.

  Scenario Outline: Verify, get user details of valid,invalid and not found users.
    Given I want to get details of an user at "<url>" with an "<username>"
    When  I want submit a get request
    Then  I should get  code "<status code>"
    And   response body with "<message>" must be displayed
    Examples:
      | url        | username       | status code | message             |
      | user/      | user4          | 200         | ok                  |
      | user/      | user200        | 404         | User not found      |
      | user/      | #null%         | 400         | Invalid ID supplied |


