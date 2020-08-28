Feature: As a user I want to verify that the city Frankfurt is in Germany and return their corresponded latitude and longitude

@citybike
Scenario: To verify that the city Frankfurt is in Germany return corresponded latitude and longitude
    Given user is on correct webpage
    When city Frankfurt is in Germany
    Then verify Frankfurt is in Germany
    And it should return corresponding latitude and longitude

