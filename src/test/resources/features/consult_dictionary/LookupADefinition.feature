Feature: Lookup a definition
  In order to check api requests
  As an api user
  I want to verify responses are corresponded


  Scenario: get inventory positive test : run test with -Denv.config.file=env1.properties
    Given the user has contentType
    When the user GET request
    And the user save response from property
    Then the order has status '200'

  Scenario: get inventory negative test with wrong id : run test with -Denv.config.file=env2.properties
    Given the user has contentType
    When the user GET request
    And the user save response from property
    Then the order has status '404'

  Scenario: get inventory negative test with missed id : run test with -Denv.config.file=env3.properties
    Given the user has contentType
    When the user GET request
    And the user save response from property
    Then the order has status '405'

#  Scenario Outline: get inventory negative test
#    Given the user has contentType
#    When the user GET request
#    And the user save response from property
#    Then the order has status '<statusCode>'
#
#    Examples:
#      | statusCode |
#      | 200        |
#      | 404        |
#      | 405        |

  Scenario: Place an order for a pet : run test with -Denv.config.file=env4.properties
    Given the user has contentType
    When the user posts an order
    And the user save response for post
    Then the order has status '200'
#   And the order has id '35'

  Scenario: Place an order for a pet : run test with -Denv.config.file=env4.properties
    Given the user has contentType
    When the user posts id '45' petId '2' quantity '2'
    Then the order has status '200'
#   And the order has id '35'