Feature: To verify store function APIs.

  Scenario Outline: Verify, place an order for a pet with valid details.
    Given I want to place an order for pet at "<url>"
    And   with the valid request body
    When  I submit a Post request
    Then  I should get the success "<status code>"
    And   the valid response body
    Examples:
      | url         | status code |
      | store/order | 200         |



  Scenario Outline: Verify, place an order for a pet with invalid details.
    Given I want to place an order for pet at "<url>"
    And   with the invalid request body request body
    When  I submit a Post request
    Then  I should get the bad request "<status code>"
    Examples:
      | url         | status code |
      | store/order | 400         |


