#author: Rupak Mansingh

Feature: Api tests for real time stock details (GET /query)

  @RegressionTest
  Scenario Outline: Get stock details for every 5 minutes interval
    Given set base url and add query param "<function>","<symbol>","<interval>"
    When user retrieves the stock details
    Then the status code is 200
    And validate response and "<symbol>", "<interval>" for every five minutes stock details

    Examples:
      | function             | symbol | interval |
      | TIME_SERIES_INTRADAY | IBM    | 5min     |

  @RegressionTest
  Scenario Outline: Get stock details for daily and weekly
    Given set base url and add query param "<function>","<symbol>"
    When user retrieves the stock details
    Then the status code is 200
    And validate response and "<symbol>" for daily and weekly stock details

    Examples:
      | function           | symbol |
      | TIME_SERIES_DAILY  | IBM    |
      | TIME_SERIES_WEEKLY | IBM    |

  @RegressionTest
  Scenario Outline: Get stock details for quote end point
    Given set base url and add query param "<function>","<symbol>"
    When user retrieves the stock details
    Then the status code is 200 for quote end point
    And validate response and "<symbol>" for quote end point

    Examples:
      | function     | symbol     |
      | GLOBAL_QUOTE | IBM        |
      | GLOBAL_QUOTE | 300135.SHZ |