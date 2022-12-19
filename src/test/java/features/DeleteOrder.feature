Feature: To verify store function APIs.

  Background: Place an order for a pet in store.
  Scenario Outline: Verify, delete an order details with valid order id.
    Given I want to delete an order at "<url>" with a valid "<orderid>"
    When  I submit a Delete request
    Then  I should get code "<status code>"
    And   response body "<message>" should be displayed
    Examples:
      | url                 | orderid        | status code | message             |
      | store/order/        | 4              | 200         | ok                  |
      | store/order/        | 154            | 404         | Order Not Found     |
      | store/order/        | 9order         | 400         | Invalid ID supplied |



