Feature: Job Search on Pracuj.pl

  Background:
    Given I open Pracuj.pl homepage
    And I accept cookies
    And I close modal if present

  Scenario: Search for QA Automation jobs in Warsaw
    When I search for "QA Automation" jobs in "Warszawa"
    Then search results should be displayed
    And results count should be greater than 0

  Scenario: Search for Java Developer jobs in Warsaw
    When I search for "Java Developer" jobs in "Warszawa"
    Then search results should be displayed
    And results count should be greater than 0

  Scenario: Search results URL contains search parameters
    When I search for "Tester" jobs in "Warszawa"
    Then search results should be displayed
    And current URL should contain "warszawa"

  Scenario: Pagination is visible for popular search
    When I search for "Tester" jobs in "Warszawa"
    Then search results should be displayed
    And next page button should be visible