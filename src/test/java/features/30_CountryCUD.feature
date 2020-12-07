@Regression
Feature: In this feature Country processes are proceeds

  Background: Login and navigate to basqar
    Given user on home page
    And   user logged in to basqar

  Scenario: create country
    Given user navigate to countries page
    When  user create a country name as "countryName123" and code as "countryCode123"
    Then  country should be created

  Scenario: update country
    Given user on countries page
    When  user update the country named "countryName123" to name as "countryNewName124" and code as "newCode124"
    Then  country should be updated

  Scenario: delete country
    Given user on countries page
    When  user delete the country named as "countryNewName124"
    Then  country should be deleted
