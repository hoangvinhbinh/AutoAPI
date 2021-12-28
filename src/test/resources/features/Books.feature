Feature: Automation Test Exercise for API
  Scenario: Get list of books by API
    When I look up a list books
    Then the searching executed successfully
    And the resulting for getting list of books should match expectations

  Scenario: Add a books by API
    When I add a book
    Then the searching executed successfully
    And the resulting of adding new book should match expectations

  Scenario Outline: Get Book IDs by API
    When I look up a book by id <ID>
    Then the searching executed successfully
    And the resulting should be id <ID> and title <Book Title> and matches JsonSchema
    Examples:
      | ID       | Book Title |
      | 99       | Book 99    |
      | 100      | Book 100   |
      | 101      | Book 101   |
      | 102      | Book 102   |