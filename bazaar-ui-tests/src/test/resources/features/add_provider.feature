Feature: Add provider

  Scenario Outline: Add provider successful
    Given I open web browser
    When I navigate to login.html page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    When I navigate to providers.html page
    And I click on Add Provider button
    And I provide title as "<titleProvider>"
    And I click on Submit button
    Then in table i see provider "<titleProvider>"


    Examples:
      | username | password | titleProvider |
      | maxim    | 123    | Russia         |