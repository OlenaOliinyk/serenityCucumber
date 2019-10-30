Feature: Lookup a definition
  In order to check api requests
  As an api user
  I want to verify responses are corresponded

  Scenario Outline: get inventory positive test
    Given the user has contentType
    When the user sent request '<orderId>'
    Then the order has status '<statusCode>'
    Examples:
      | orderId | statusCode |
      | 3       | 200        |
      | 222      | 404        |

  Scenario: get inventory negative test with missed id
    Given the user has contentType
    When the user GET request
    Then the order has status '405'
    And the error type is 'unknown'

  Scenario: Place an order for a pet
    Given the user has contentType
    When the user posts id '45' petId '1' quantity '1'
    Then the order has status '200'
    And the order has id '45'

  Scenario: Place an order with missed parameter
    Given the user has contentType
    When the user missed parameter
    Then the order has status '400'
    And the error type is 'unknown'
    And the message 'bad input' is displayed in body


  Scenario: Place an order with wrong request
    Given the user has contentType
    When the user type wrong parameter
    Then the order has status '500'
    And the error type is 'unknown'
    And the message 'something bad happened' is displayed in body

