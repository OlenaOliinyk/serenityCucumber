Feature: Lookup a definition
  In order to check api requests
  As an api user
  I want to verify responses are corresponded


  Scenario: get inventory negative test with missed id : open.get.inventory.endpoint = https://petstore.swagger.io/v2/store/order
    Given the user has contentType
    When the user GET request
    And the user save response from property
    Then the order has status '405'

  Scenario Outline: get inventory negative test : open.get.inventory.endpoint = https://petstore.swagger.io/v2/store/order
    Given the user has contentType
    When the user sent request '<orderId>'
    Then the order has status '<statusCode>'
    Examples:
      | orderId | statusCode |
      | 3       | 200        |
      | 22      | 404        |

  Scenario: Place an order for a pet :run test with : open.get.inventory.endpoint = https://petstore.swagger.io/v2/store
    Given the user has contentType
    When the user posts an order
    And the user save response for post
    Then the order has status '200'
#    And the order has id '35'

  Scenario: Place an order for a pet : run test with : open.get.inventory.endpoint = https://petstore.swagger.io/v2/store
    Given the user has contentType
    When the user posts id '45' petId '2' quantity '2'
    Then the order has status '200'
#    And the order has id '35'