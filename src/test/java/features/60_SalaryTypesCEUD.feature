@Regression
Feature: Human Resourses, Setup, Salary Types operations

  Background: login to basqar
    Given user on home page
    And   user logged in to basqar

  Scenario: Salary Types operations, create
    Given user navigate to salary types page
    When  user create a salary type name as "salaryType1" and user type as "Teacher"
    Then  salary type should be created

  Scenario: Salary Types operations, update
    Given user on salary types page
    When  user update the salary type named "salaryType1" to name as "salaryType2" and user type as "Student"
    Then  salary type should be updated

  Scenario: Salary Types operations, edit and update
    Given user on salary types page
    When  user edit the salary type named "salaryType2"
    And   click to "save" button on dialog
    Then  salary type should be updated

  Scenario: Salary Types operations, delete
    Given user on salary types page
    When  user delete the salary type named as "salaryType2"
    Then  salary type should be deleted