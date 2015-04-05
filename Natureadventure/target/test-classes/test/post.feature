@post
Feature: Create a new person in the database
  In order to create a new person
  As a web page user
  I want to create a new person by populating the text-boxes and clicking the Nuevo button
  Scenario: Create a new person
    Given I'm in the index.html page
    When I write the name of the person
    And I write the surname of the person
    And I write the nif of the person
    And I click the Nuevo button
    Then The person is added to the table