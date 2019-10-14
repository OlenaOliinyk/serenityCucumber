Feature: Lookup a definition
  In order to talk better
  As an English student
  I want to look up word definitions

#  Scenario: Looking up the definition of 'apple'
#    Given the user is on the Wikionary home page
#    When the user looks up the definition of the word 'apple'
#    Then they should see the definition 'A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.'
#
#  Scenario: Looking up the definition of 'pear'
#    Given the user is on the Wikionary home page
#    When the user looks up the definition of the word 'pear'
#    Then they should see the definition 'An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.'


#  Scenario: Place an order for a pet
#    Given the user is on the Inventory home page
#    When the user posts an order with id '35' petId '1' quantity '2'
#    Then the order has statusCode '400'

  Scenario: get inventory by id simple
    Given the user has contentType
    When the user send request 'https://petstore.swagger.io/v2/store/order/3'
    And the user save response
    Then the order has status '200'

  Scenario: get inventory by id
    Given the user has contentType
    When GET request
#    And the user save response
##   Then the order has status '200'

    #  Scenario: post inventory by id
#    Given the user has baseURL
#    When the user post order
##   Then the order is placed