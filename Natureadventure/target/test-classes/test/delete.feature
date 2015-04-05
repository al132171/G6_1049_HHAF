@delete
Feature: Delete person
  In order to delete a person
  As a web page user
  I want to delete a person clicking the delete button

  Scenario: Deleta a person
    Given The person is displayed in the browser
    When I click the delete button
    Then The person is deleted from the database