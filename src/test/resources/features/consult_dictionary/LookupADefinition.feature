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
      | 22      | 404        |

  Scenario: get inventory negative test with missed id
    Given the user has contentType
    When the user GET request
    And the user save response from property
    Then the order has status '405'


  Scenario: Place an order for a pet
    Given the user has contentType
    When the user posts id '45' petId '2' quantity '2'
    Then the order has status '200'
    And the order has id '45'

  Scenario: Place an order with missed parameter
    Given the user has contentType
    When the user missed parameter
    And the user save response for post
    Then the order has status '400'

  Scenario: Place an order with wrong parameter- test is failed because Actual status=400
    Given the user has contentType
    When the user type wrong parameter
    And the user save response for post
    Then the order has status '500'
