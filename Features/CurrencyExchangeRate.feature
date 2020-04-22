#author: Rupak Mansingh

Feature: Api tests for currency exchange rate (GET /query)

  @RegressionTest
  Scenario Outline: Get real time currency exchange rate
    Given set base url and add query param "<function>","<from_currency>","<to_currency>" for real currency exchange
    When user retrieves the real time currency exchange rate
    Then validate status code is 200 for real time currency exchange rate
    And response includes the following "<from_currency>", "<to_currency>" for real time exchange rate
    Examples:
      | function               | from_currency | to_currency |
      | CURRENCY_EXCHANGE_RATE | USD           | JPY         |
      | CURRENCY_EXCHANGE_RATE | EUR           | USD         |

  @RegressionTest
  Scenario Outline: Get currency exchange rate every 5 minutes interval
    Given set base url and add query param "<function>","<from_symbol>","<to_symbol>","<interval>" for currency exchange
    When user retrieves the real time currency exchange rate by every five minutes
    Then validate status code is 200 for currency exchange rate
    And response includes the following "<from_symbol>", "<to_symbol>" for exchange rate
    Examples:
      | function    | from_symbol | to_symbol | interval |
      | FX_INTRADAY | USD         | JPY       | 5min     |

  @RegressionTest
  Scenario Outline: Get currency exchange rate by daily and weekly
    Given set base url and add query param "<function>","<from_symbol>","<to_symbol>" for currency exchange
    When user retrieves the currency exchange rate by daily and weekly
    Then validate status code is 200 for currency exchange rate
    And response includes the following "<from_symbol>", "<to_symbol>" for exchange rate
    Examples:
      | function  | from_symbol | to_symbol |
      | FX_DAILY  | USD         | JPY       |
      | FX_WEEKLY | EUR         | USD       |