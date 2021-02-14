Feature: Demo the API

  Scenario: Add and query data
    When I login as "hans" with password "geheim"
    And "hans" adds some data
    Then the data is returned for "hans"
