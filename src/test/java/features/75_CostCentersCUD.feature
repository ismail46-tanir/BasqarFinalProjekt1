@Regression
Feature: Budgets, Setup, Cost Centers operations

  Background: login to basqar
    Given user on home page
    And   user logged in to basqar


  Scenario: Cost Centers operations, create
    Given user navigate to cost centers page
    When  user create a cost center as follows
      | name    | name123 |
      | code    | code123 |
      | type    | Service |
      | orderNo | 100     |
      | key     | key123  |
      | value   | 100     |
      | expense | 2       |

    Then  cost center should be created

  Scenario: Cost Centers operations, update
    Given user on cost centers page
    When  user update the cost center name as "name123" as follows
      | name    | name124 |
      | code    | code124 |
      | type    | 2       |
      | orderNo | 99      |
      | key     | key124  |
      | value   | 99      |
      | expense | 3       |

    Then  cost center should be updated

  Scenario: Cost Centers operations, delete
    Given user on cost centers page
    When  user delete the cost center named as "name124"
    Then  cost center should be deleted