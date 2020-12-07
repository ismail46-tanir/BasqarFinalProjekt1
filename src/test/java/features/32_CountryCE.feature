
Feature: Setup, Parameters, Country operations are proceeds

  Background: login to basqar
    Given user on home page
    And   user logged in to basqar

  Scenario: Country operations, create country
    Given user navigate to countries page
    When  user create a country name as "countryName123" and code as "countryCode123"
    Then  country should be created

  Scenario: Country operations, update country
    Given user on countries page
    When  user update the country named "countryName123" to name as "countryName124" and code as "newCode124"
    Then  country should be updated

  Scenario: Country operations, edit and delete country
    Given user on countries page
    When  user edit the country named "countryName124"
    And   click to "close" button on dialog
    And   user navigate to countries page
    And   user delete the country named as "countryName124"
    Then  country should be deleted
