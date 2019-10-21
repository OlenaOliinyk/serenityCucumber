Feature: Lookup a definition
  In order to talk better
  As an English student
  I want to look up word definitions


#  Scenario: get inventory positive test
#    Given the user has contentType
#    When the user GET request
#    And the user save response from property
#    Then the order has status '200'

  Scenario Outline: get inventory negative test
    Given the user has contentType
    When the user GET request
    And the user save response from property
    Then the order has status '<statusCode>'

    Examples:
      | statusCode |
      | 200        |
      | 404        |
      | 405        |

  Scenario: Place an order for a pet
    Given the user has contentType
    When the user posts an order
    And the user save response for post
    Then the order has status '200'
#    And the order has id '35'
