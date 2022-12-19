Feature: To verify store function APIs.


  Background: Place an order for a pet in store to check valid order.
  Scenario Outline: Verify, get an order details of valid,invalid and not found orders.
    Given I want to get details of an order at "<url>" with an "<orderid>" 
    When  I submit a Get request
    Then  I should get "<status code>"
    And   response body with "<message>" should be displayed
    Examples:
      | url                 | orderid        | status code | message             |
      | store/order/        | 4              | 200         | ok                  |
      | store/order/        | 876            | 404         | Order not found     |
      | store/order/        | 9order         | 400         | Invalid ID supplied |


