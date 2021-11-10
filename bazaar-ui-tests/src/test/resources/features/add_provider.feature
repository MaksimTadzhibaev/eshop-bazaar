Feature: Add provider

  Scenario Outline: Add provider successful
    Given I open web browser
    When I navigate to providers.html page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then I see button "Add Provider"
    And I click on Add Provider button
    And I provide Name as "<titleProvider>"
    And I click on Submit button
    Then in table i see provider "<titleProvider>"


    Examples:
      | username | password | titleProvider |
      | maxim    | 123    | USA         |