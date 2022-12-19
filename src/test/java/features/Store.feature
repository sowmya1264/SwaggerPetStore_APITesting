Feature: To verify store function APIs.
  Scenario Outline: Verify get inventories by status Get Method
    Given I want to make a get request "<url>"
    When  I submit a GET request
    Then  I should get success status code "<status code>"
    Examples:
      |url             | status code  |
      |store/inventory | 200          |




