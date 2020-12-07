@Regression
Feature: Human Resourses, Setup, Positions operations

  Background: login to basqar
    Given user on home page
    And   user logged in to basqar


  Scenario: Positions operations, create
    Given user navigate to positions page
    When  user create a position name as "position1" and short name as "pos1"
    Then  position should be created


  Scenario: Positions operations, update
    Given user on positions page
    When  user update the position named "position1" to name as "position2" and code as "pos2"
    Then  position should be updated


  Scenario: Positions operations, edit and update
    Given user on positions page
    When  user edit the position named "position2"
    And   click to "save" button on dialog
    Then  position should be updated


  Scenario: Positions operations, update
    Given user on positions page
    When  user delete the position named as "position2"
    Then  position should be deleted