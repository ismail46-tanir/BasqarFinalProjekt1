@Regression
Feature: Setup, Parameters, City are proceeds

  Background: login and navigate to cities page
    Given user on home page
    And   user logged in to basqar
    And   user navigate to cities page

  Scenario Outline: create and delete city
    When  user create a city name as "<cityName>" that belong to the country "<countryName>"
    Then  city should be created

    When  user delete the city named as "<cityName>"
    Then  city should be deleted

    Examples:
      | countryName | cityName    |
      | Turkey      | Kayseri     |
      | Greece      | Theseloniki |
