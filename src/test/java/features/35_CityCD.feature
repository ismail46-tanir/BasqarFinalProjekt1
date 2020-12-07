@Regression
Feature: Setup, Parameters, City are proceeds

  Background: login to basqar
    Given user on home page
    And   user logged in to basqar

  Scenario: City operations, create a city with country name
    Given user navigate to cities page
    When  user create a city name as "TurkeyCityName" that belong to the country "Turkey"
    Then  city should be created

  Scenario: City operations, delete a city
    Given user on cities page
    When  user delete the city named as "TurkeyCityName"
    Then  city should be deleted

  Scenario: City operations, create a city with country index
    Given user on cities page
    When  user create a city which name is "OptionCity" to the country with option 10
    Then  city should be created

  Scenario: City operations, delete a city
    Given user on cities page
    When  user delete the city named as "OptionCity"
    Then  city should be deleted

