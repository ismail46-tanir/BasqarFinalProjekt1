@Regression
Feature: Eduation, Setup, Subject Category operations

  Background: login to basqar
    Given user on home page
    And   user logged in to basqar

  Scenario: Subject Category operations, create with data table
    Given user navigate to subjects page
    When  user create a subject as follows
      | name     | subject521 |
      | code     | sCode521   |
      | category | 2          |
      | style    | 5          |

    Then  subject should be created

  Scenario: Subject Category operations, delete
    Given user navigate to subjects page
    When  user delete the subject name as "subject521"
    Then  subject should be deleted

