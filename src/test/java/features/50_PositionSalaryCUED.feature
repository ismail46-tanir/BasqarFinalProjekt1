@Regression
Feature: Human Resourses, Setup, Position Salaries operations

  Background: login to basqar
    Given user on home page
    And   user logged in to basqar

  Scenario: Position Salaries operations, create
    Given  user navigate to position salary page
    When  user create a person to position salary name as "posSal1"
    Then  person in position salary should be created

  Scenario: Position Salaries operations, create
    Given user on position salary page
    When  user update the person in position salary named "posSal1" to name as "posSal2"
    Then  person in position salary should be updated

  Scenario: Position Salaries operations, edit and update
    Given user on position salary page
    When  user edit the person in position salary named "posSal2"
    And   click to "save" button on dialog
    Then  person in position salary should be updated

  Scenario: Position Salaries operations, delete
    Given user on position salary page
    And   user delete the person in position salary named as "posSal2"
    Then  person in position salary should be deleted